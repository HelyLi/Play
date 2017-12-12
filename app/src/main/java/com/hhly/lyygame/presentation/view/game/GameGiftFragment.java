package com.hhly.lyygame.presentation.view.game;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.protocol.game.GiftBagResp;
import com.hhly.lyygame.presentation.utils.ActivityUtil;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.order.OrderActivity;
import com.hhly.lyygame.presentation.view.widget.RecyclerViewListDivide;
import com.hhly.lyygame.presentation.view.widget.RecyclerViewListFirstDivide;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 游戏介绍礼包
 * Created by Simon on 2016/12/2.
 */

public class GameGiftFragment extends BaseFragment implements GameContract.GiftView {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.item_content_tv)
    TextView mItemContentTv;
    @BindView(R.id.item_opt_btn)
    Button mItemOptBtn;
    @BindView(R.id.item_use_description_tv)
    TextView mItemUseDescriptionTv;

    private GameGiftAdapter mGiftAdapter;
    private GameContract.GiftPresenter mPresenter;

    public GameGiftFragment() {
        new GiftPresenterImpl(this);
    }

    public static GameGiftFragment newInstance(int gameId) {
        GameGiftFragment fragment = new GameGiftFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(GameIntroFragment.GAME_INTRO_ID, gameId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGiftAdapter = new GameGiftAdapter();
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.addItemDecoration(new RecyclerViewListDivide(getActivity(), R.color.transparent, RecyclerViewListDivide.HORIZONTAL_LIST, 15));
        mRecyclerView.addItemDecoration(new RecyclerViewListFirstDivide(getActivity(), RecyclerViewListDivide.HORIZONTAL_LIST, 15));
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.addOnItemTouchListener(mOnItemClickListener);
        mRecyclerView.setHasFixedSize(false);

        mRecyclerView.setAdapter(mGiftAdapter);

        postDelay(new Runnable() {
            @Override
            public void run() {
              fetchData(false);
            }
        }, 500);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_game_gift_layout;
    }

    private GiftBagResp.GiftBagBean mGiftBagBean = null;

    @OnClick(R.id.item_opt_btn)
    public void onClick() {
        //礼品兑换
        if (mGiftBagBean == null)
            return;
        if (ActivityUtil.checkLoginAndRequestLogin(getActivity())) {
            ActivityCompat.startActivity(getActivity(), OrderActivity.getOrderGiftCallingIntent(getActivity(), mGiftBagBean.getId()), null);
        }
    }

    @Override
    protected void fetchData(boolean loadMore) {
        mPresenter.getGameGift(getArguments().getInt(GameIntroFragment.GAME_INTRO_ID));
    }

    private OnItemClickListener mOnItemClickListener = new OnItemClickListener() {
        @Override
        public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
            //switch content
            GiftBagResp.GiftBagBean giftBagBean = (GiftBagResp.GiftBagBean) baseQuickAdapter.getItem(position);
            mGiftBagBean = giftBagBean;
            mItemContentTv.setText(mGiftBagBean.getContent());
            List<GiftBagResp.GiftBagBean> list = baseQuickAdapter.getData();
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setCheck(i == position);
            }
            updateBtn();
            baseQuickAdapter.notifyDataSetChanged();
        }
    };

    @Override
    public void showGameGift(List<GiftBagResp.GiftBagBean> giftBagBeanList) {
        mGiftAdapter.setNewData(giftBagBeanList);
        giftBagBeanList.get(0).setCheck(true);
        mGiftBagBean = giftBagBeanList.get(0);
        mItemContentTv.setText(mGiftBagBean.getContent());
        updateBtn();
    }

    private void updateBtn() {
        if ("1".equalsIgnoreCase(mGiftBagBean.getAppliedCount())) {
            mItemOptBtn.setEnabled(false);
        } else if (mGiftBagBean.getCanUse() <= 0) {
            mItemOptBtn.setEnabled(false);
        } else if (mGiftBagBean.getCanUse() > 0 && ("0".equalsIgnoreCase(mGiftBagBean.getAppliedCount()) || mGiftBagBean.getAppliedCount() == null)) {
            mItemOptBtn.setEnabled(true);
        }
    }

    @Override
    public void onFailureGift() {
        mItemOptBtn.setEnabled(false);
    }

    @Override
    public void setPresenter(GameContract.GiftPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return false;
    }
}
