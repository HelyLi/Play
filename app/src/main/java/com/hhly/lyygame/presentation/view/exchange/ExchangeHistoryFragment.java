package com.hhly.lyygame.presentation.view.exchange;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.db.manager.AccountManager;
import com.hhly.lyygame.data.net.protocol.goods.GoodsExchangeHistoryResp;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.immersive.IImmersiveApply;
import com.hhly.lyygame.presentation.view.widget.RecyclerViewListDivide;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;

/**
 * 兑换历史
 * Created by Simon on 2016/11/29.
 */

public class ExchangeHistoryFragment extends BaseFragment implements IImmersiveApply,
        SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener,
        ExchangeHistoryContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
//    @BindView(R.id.refreshLayout)
//    SwipeRefreshLayout mRefreshLayout;

    private ExchangeHistoryAdapter mHistoryAdapter;
    private ExchangeHistoryContract.Presenter mPresenter;

    private int pageNo = 1;
    private int PAGE_SIZE = 10;

    public static ExchangeHistoryFragment newInstance() {
        return new ExchangeHistoryFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setRefreshing(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setSelected(false);
        mRecyclerView.setClickable(false);
        mRecyclerView.addItemDecoration(new RecyclerViewListDivide(getActivity(), R.color.color_f5f5f5, RecyclerViewListDivide.VERTICAL_LIST, 1));

        initAdapter();
        postDelay(new Runnable() {
            @Override
            public void run() {
                fetchData(false);
            }
        }, 500);
    }

    private void initAdapter() {
        mHistoryAdapter = new ExchangeHistoryAdapter();
        mHistoryAdapter.setOnLoadMoreListener(this, mRecyclerView);

        mRecyclerView.setAdapter(mHistoryAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_exchange_history_layout;
    }

    @Override
    public void onRefresh() {
        pageNo = 1;
        mHistoryAdapter.setEnableLoadMore(false);
        fetchData(false);
    }

    @Override
    protected void fetchData(boolean loadMore) {
        mPresenter.getExchangeHistory(AccountManager.getInstance().getUserId(), pageNo, PAGE_SIZE);
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
    public void onLoadMoreRequested() {
        mRefreshLayout.setEnabled(false);
        fetchData(true);
    }

    @Override
    public void setPresenter(ExchangeHistoryContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public void showExchangeHistory(List<GoodsExchangeHistoryResp.ExchangeBean> exchangeBeanList, int totalSize) {

        Logger.d("pageNo=" + pageNo + ",dataSize=" + exchangeBeanList.size());

        if (pageNo == 1) {//onRefresh
            mHistoryAdapter.setNewData(exchangeBeanList);
            mRefreshLayout.setRefreshing(false);
            mHistoryAdapter.setEnableLoadMore(true);
        } else {//
            mHistoryAdapter.addData(exchangeBeanList);
            mHistoryAdapter.loadMoreComplete();
            mRefreshLayout.setEnabled(true);
        }
        if (mHistoryAdapter.getData().size() >= totalSize) {
            mHistoryAdapter.loadMoreEnd(true);
        }
        pageNo++;
    }

    @Override
    public void onFailure() {
        mRefreshLayout.setRefreshing(false);
        mHistoryAdapter.loadMoreFail();
    }

    @Override
    public void onEmptyView() {
        mRefreshLayout.setRefreshing(false);
        mHistoryAdapter.setEmptyView(LayoutInflater.from(getActivity()).inflate(R.layout.widget_empty_view, null, false));
    }
}
