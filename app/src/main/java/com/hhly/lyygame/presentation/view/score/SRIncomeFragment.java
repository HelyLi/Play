package com.hhly.lyygame.presentation.view.score;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.protocol.user.AccountScorePager;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.widget.RecyclerViewDividerItem;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;

/**
 * 积分收入
 * Created by Simon on 2016/11/30.
 */

public class SRIncomeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener, ScoreContract.SRIncomeView {


    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
//    @BindView(R.id.refreshLayout)
//    SwipeRefreshLayout mRefreshLayout;

    private SRListAdapter mListAdapter;
    private int pageNo = 1;
    private int PAGE_SIZE = 10;

    public SRIncomeFragment() {
        new SRIncomePresenterImpl(this);
    }

    private ScoreContract.SRIncomePresenter mPresenter;

    public static SRIncomeFragment newInstance() {
        return new SRIncomeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_recyclerview_with_refresh;
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        mRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new RecyclerViewDividerItem(getActivity(), R.color.color_e9e9e9, RecyclerViewDividerItem.VERTICAL_LIST, 1));

        initAdapter();

        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPresenter.getSRIncomeDetail(pageNo, PAGE_SIZE);
            }
        }, 500);
    }

    private void initAdapter() {
        mListAdapter = new SRListAdapter();
        mListAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mListAdapter.setAutoLoadMoreSize(PAGE_SIZE);
//        mListAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        mRecyclerView.setAdapter(mListAdapter);

        View emptyView = LayoutInflater.from(getActivity()).inflate(R.layout.widget_empty_imageview, null, false);
        mListAdapter.setEmptyView(emptyView);
    }

    @Override
    public void onRefresh() {
        pageNo = 1;
        mListAdapter.setEnableLoadMore(false);
        mPresenter.getSRIncomeDetail(pageNo, PAGE_SIZE);
    }

    @Override
    public void onLoadMoreRequested() {
        mRefreshLayout.setEnabled(false);
        mPresenter.getSRIncomeDetail(pageNo, PAGE_SIZE);
    }

    @Override
    public void showSRIncomeDetail(List<AccountScorePager.AccountScoreBean> list, int totalPage) {

        Logger.d("pageNo=" + pageNo + ",totalPage=" + totalPage);

        if (pageNo == 1) {//onRefresh
            mListAdapter.setNewData(list);
            mRefreshLayout.setRefreshing(false);
            mListAdapter.setEnableLoadMore(true);
        } else {//
            mListAdapter.addData(list);
            mListAdapter.loadMoreComplete();
            mRefreshLayout.setEnabled(true);
        }
        if (pageNo >= totalPage) {
            mListAdapter.loadMoreEnd(false);//true 没有提示； false 有提示；
        }
        pageNo++;
    }

    @Override
    public void onFailure() {
        mRefreshLayout.setRefreshing(false);
        mListAdapter.loadMoreFail();
    }

    @Override
    public void setPresenter(ScoreContract.SRIncomePresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    protected void fetchData(boolean isLoadMore) {

    }
}
