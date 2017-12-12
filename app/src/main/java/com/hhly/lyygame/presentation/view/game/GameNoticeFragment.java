package com.hhly.lyygame.presentation.view.game;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.protocol.game.GameNoticeResp;
import com.hhly.lyygame.presentation.utils.CollectionUtil;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.messagedetail.WebDetailActivity;
import com.hhly.lyygame.presentation.view.widget.RecyclerViewListDivide;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;

/**
 * 游戏公告
 * Created by Simon on 2016/12/2.
 */

public class GameNoticeFragment extends BaseFragment implements GameContract.NoticeView {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private GameNoticeAdapter mNoticeAdapter;
    private GameContract.NoticePresenter mPresenter;

    public static final String GAME_NOTICE = "game_notice";

    public GameNoticeFragment() {
        new NoticePresenterImpl(this);
    }

    public static GameNoticeFragment newInstance(int[] ids) {
        GameNoticeFragment fragment = new GameNoticeFragment();
        Bundle bundle = new Bundle();
        bundle.putIntArray(GameNoticeFragment.GAME_NOTICE, ids);
        fragment.setArguments(bundle);
        return fragment;
    }

    private int[] mIds = null;//ids[0] gameId;ids[1] noticeType

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mIds = getArguments().getIntArray(GameNoticeFragment.GAME_NOTICE);
        Logger.d("mIds=" + mIds);
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new RecyclerViewListDivide(getActivity(), R.color.color_e9e9e9, RecyclerViewListDivide.VERTICAL_LIST, 0.8f));
        mRecyclerView.addOnItemTouchListener(mOnItemClickListener);

        mNoticeAdapter = new GameNoticeAdapter();
        mRecyclerView.setAdapter(mNoticeAdapter);

        postDelay(new Runnable() {
            @Override
            public void run() {
                fetchData(false);
            }
        }, 500);
    }

    @Override
    protected void fetchData(boolean loadMore) {
        mPresenter.getGameNotice(mIds[0], mIds[1]);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_game_notice_layout;
    }

    private OnItemClickListener mOnItemClickListener = new OnItemClickListener() {
        @Override
        public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
            //公告
            GameNoticeResp.GameNoticePage.GameNotice notice = (GameNoticeResp.GameNoticePage.GameNotice) baseQuickAdapter.getItem(i);
            if (mIds[1] == 1) {//公告
                ActivityCompat.startActivity(getActivity(), WebDetailActivity.getCallingIntent(getActivity(), new int[]{0, 0, notice.getId()}), null);
            } else if (mIds[1] == 2) {//活动
                ActivityCompat.startActivity(getActivity(), WebDetailActivity.getCallingIntent(getActivity(), new int[]{0, notice.getId(), 0}), null);
            }
        }
    };

    @Override
    public void showGameNotice(List<GameNoticeResp.GameNoticePage.GameNotice> gameNoticeList) {
        if (CollectionUtil.isEmpty(gameNoticeList)) return;
        mNoticeAdapter.setNewData(gameNoticeList);
    }

    @Override
    public void setPresenter(GameContract.NoticePresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return false;
    }
}
