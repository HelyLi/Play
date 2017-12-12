package com.hhly.lyygame.presentation.view.paylist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.hhly.lyygame.App;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.protocol.user.AccountCoinsPager;
import com.hhly.lyygame.presentation.utils.NetworkUtil;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.widget.RecyclerViewListDivide;

import java.util.List;

import butterknife.BindView;

/**
 * Created by HELY on 17/1/18.
 * 邮箱：heli.lixiong@gmail.com
 * <p>
 * 加载更多
 */

public class PayListFragment extends BaseFragment implements PayListContact.View,
        SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    public static final String EXTRA_CATEGORY = "extra_category";
    public static final int RECHARGE_CATEGORY=1;
    public static final int CONSUME_CATEGORY=2;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
//    @BindView(R.id.refreshLayout)
//    SwipeRefreshLayout mRefreshLayout;

    private PayListContact.Persenter mPersenter;
    private PayListAdapter mResultAdapter;

    private int mCategory;

    public PayListFragment() {
        new PayListPresenter(this);
    }

    public static PayListFragment newInstance(int category) {
        PayListFragment fragment = new PayListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_CATEGORY, category);
        fragment.setArguments(bundle);
        return fragment;
    }

    //    private boolean loadMore = true;
    private int pagerIndex = 1;
    private int pagerSize = 15;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCategory = getArguments().getInt(EXTRA_CATEGORY, 1);
        }
        mResultAdapter = new PayListAdapter(mCategory);
        mResultAdapter.setEnableLoadMore(true);
        mResultAdapter.setOnLoadMoreListener(this, mRecyclerView);
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new RecyclerViewListDivide(getActivity(), R.color.color_e9e9e9, RecyclerViewListDivide.VERTICAL_LIST, 0.8f));
        mRefreshLayout.setOnRefreshListener(this);

        View emptyView = LayoutInflater.from(getActivity()).inflate(R.layout.widget_empty_imageview, null, false);
        mResultAdapter.setEmptyView(emptyView);

        mRecyclerView.setAdapter(mResultAdapter);
        mRecyclerView.addOnItemTouchListener(mOnItemClickListener);
        prepareFetchData();
    }

    @Override
    protected void fetchData(boolean loadMore) {
        mRefreshLayout.setRefreshing(!loadMore);
        mPersenter.getAccountRechargeDetail(pagerIndex, pagerSize, mCategory);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_recyclerview_with_refresh;
    }

    @Override
    public void setPresenter(PayListContact.Persenter presenter) {
        this.mPersenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void onRefresh() {
        if (!NetworkUtil.isAvailable(App.getContext())) {
            mRefreshLayout.setRefreshing(false);
            return;
        }
        pagerIndex = 1;
        fetchData(false);
    }

    @Override
    public void showAccountRechargeDetail(List<AccountCoinsPager.AccountCoinsBean> rechargeBeanList, int totalPages) {
        mRefreshLayout.setRefreshing(false);
        mResultAdapter.loadMoreComplete();
        if (pagerIndex == 1) {
            mResultAdapter.setNewData(rechargeBeanList);
        } else {
            mResultAdapter.addData(rechargeBeanList);
        }
        if (pagerIndex>=totalPages){
            mResultAdapter.setEnableLoadMore(false);
        }else {
            mResultAdapter.setEnableLoadMore(true);
        }
    }

    @Override
    public void getAccountRechargeDetailFailure() {
        mRefreshLayout.setRefreshing(false);
        mResultAdapter.loadMoreComplete();
    }

    @Override
    public void onLoadMoreRequested() {
        pagerIndex++;
        fetchData(true);
    }

    private OnItemClickListener mOnItemClickListener = new OnItemClickListener() {
        @Override
        public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {


        }
    };

}
