package com.hhly.lyygame.presentation.view.transfer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.protocol.transfer.TransferGameListResp;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.immersive.IImmersiveApply;
import com.hhly.lyygame.presentation.view.widget.RecyclerViewListDivide;

import java.util.List;

import butterknife.BindView;

/**
 * Created by ${HELY} on 17/4/24.
 * 邮箱：heli.lixiong@gmail.com
 */

public class TransferFragment extends BaseFragment implements IImmersiveApply, TransferContract.View {

    public final static String TRANSFER_DRAW_TYPE = "transfer_draw_type";

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
//    @BindView(R.id.refreshLayout)
//    SwipeRefreshLayout mRefreshLayout;

    private TransferContract.Presenter mPresenter;

    private TransferAdapter mTransferAdapter;

    private String mDrawType;

    public static TransferFragment newInstance(String drawType) {
        TransferFragment fragment = new TransferFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TRANSFER_DRAW_TYPE, drawType);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDrawType = getArguments().getString(TRANSFER_DRAW_TYPE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_transfer_layout;
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        mRefreshLayout.setEnabled(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new RecyclerViewListDivide(getActivity(), R.color.color_f5f5f5, RecyclerViewListDivide.VERTICAL_LIST, 1));
        initAdapter();

        postDelay(new Runnable() {
            @Override
            public void run() {
                mPresenter.transGameList(mDrawType);
            }
        }, 500);
    }

    private void initAdapter(){
        mTransferAdapter = new TransferAdapter();
        mRecyclerView.setAdapter(mTransferAdapter);
        mRecyclerView.addOnItemTouchListener(mOnItemClickListener);

    }

    OnItemClickListener mOnItemClickListener = new OnItemClickListener() {
        @Override
        public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
            TransferGameListResp.TransferGameInfo gameInfo = (TransferGameListResp.TransferGameInfo)adapter.getItem(position);
            resultGameData(gameInfo);
        }
    };

    private void resultGameData(TransferGameListResp.TransferGameInfo gameInfo){

        Intent intent = new Intent();
        intent.putExtra(GameTransferActivity.TRANSFER_GAMENAME, gameInfo.getName());
        intent.putExtra(GameTransferActivity.TRANSFER_GAMEID, gameInfo.getId());
        getActivity().setResult(Activity.RESULT_OK, intent);
        onBackPressed();
    }

    @Override
    public void onResume() {
        super.onResume();

        switch (mDrawType){
            case "1":
                mToolbarHelper.setTitle(getString(R.string.lyy_game_transfer_in_app_title));
                break;
            case "2":
                mToolbarHelper.setTitle(getString(R.string.lyy_game_transfer_out_app_title));
                break;
            case "3":
                mToolbarHelper.setTitle(getString(R.string.lyy_game_transfer_coupon_app));
                break;
        }

    }

    @Override
    protected void fetchData(boolean isLoadMore) {

    }

    @Override
    public void setPresenter(TransferContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public boolean applyImmersive() {
        return true;
    }

    @Override
    public boolean applyScroll() {
        return false;
    }

    @Override
    public float initAlpha() {
        return 1.0f;
    }

    @Override
    public void showTransGameList(List<TransferGameListResp.TransferGameInfo> transferGameInfoList) {
        mTransferAdapter.setNewData(transferGameInfoList);
    }

    @Override
    public void showFailure() {
        View emptyView = LayoutInflater.from(getActivity()).inflate(R.layout.widget_error_view, null, false);
        mTransferAdapter.setEmptyView(emptyView);
    }

    @Override
    public void onEmptyView() {
        View emptyView = LayoutInflater.from(getActivity()).inflate(R.layout.widget_empty_imageview, null, false);
        mTransferAdapter.setEmptyView(emptyView);
    }

}
