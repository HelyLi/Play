package com.hhly.lyygame.presentation.view.message;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildLongClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.protocol.user.MsgPager;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.DialogFactory;
import com.hhly.lyygame.presentation.view.messagedetail.WebDetailActivity;
import com.hhly.lyygame.presentation.view.messagedetail.WebDetailFragment;
import com.hhly.lyygame.presentation.view.widget.RecyclerViewDividerItem;
import com.hhly.lyygame.presentation.view.widget.RecyclerViewListDivide;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;

/**
 * 我的消息
 * Created by Simon on 2016/12/6.
 */

public class MessageListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener, MessageListContract.View, MessageDialog.MsgCallback {

    public static final String EXTRA_CATEGORY = "extra_category";
    public static final int SYSTEM_MESSAGE = 1;
    public static final int ACTIVITY_NOTICE = 3;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
//    @BindView(R.id.refreshLayout)
//    SwipeRefreshLayout mRefreshLayout;

    private MessageListContract.Presenter mPresenter;

    private MessageListAdapter mListAdapter;

    private int mCategory = 0;

    private int pageNo = 1;
    private int PAGE_SIZE = 10;

    public static MessageListFragment newInstance() {
        return new MessageListFragment();
    }

    public MessageListFragment() {
        new MessageListPresenter(this);
    }

    public static MessageListFragment newInstance(int category) {
        MessageListFragment fragment = new MessageListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_CATEGORY, category);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCategory = getArguments().getInt(EXTRA_CATEGORY);
        }
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new RecyclerViewDividerItem(getActivity(), R.color.color_e9e9e9, RecyclerViewListDivide.VERTICAL_LIST, 0.8f));
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setRefreshing(true);
        initAdapter();
//        mPresenter.loadMessage(mCategory, pageNo, PAGE_SIZE);
    }

    private void initAdapter() {
        mListAdapter = new MessageListAdapter();
        mListAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mRecyclerView.setAdapter(mListAdapter);
        mListAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                DialogFactory.createMsg(getActivity(), position, MessageListFragment.this).show();
                return true;
            }
        });
        mListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MsgPager.MsgBean msgBean = (MsgPager.MsgBean) adapter.getItem(position);
                ActivityCompat.startActivityForResult(getActivity(), WebDetailActivity.getCallingIntent(getActivity(), new int[]{msgBean.getId(), 0, 0}), WebDetailFragment.REFRESH_INFO_REQUEST_CODE, null);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_recyclerview_with_refresh;
    }

    @Override
    public void onRefresh() {
        pageNo = 1;
        mListAdapter.setEnableLoadMore(false);
        mPresenter.loadMessage(mCategory, pageNo, PAGE_SIZE);
    }

    @Override
    public void onLoadMoreRequested() {
        Logger.d("onLoadMoreRequested.pageno=" + pageNo);
        mRefreshLayout.setEnabled(false);
        mPresenter.loadMessage(mCategory, pageNo, PAGE_SIZE);
    }

    @Override
    public void setPresenter(MessageListContract.Presenter presenter) {
        this.mPresenter = presenter;
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
        mPresenter.loadMessage(mCategory, pageNo, PAGE_SIZE);
    }

    @Override
    public void showMessages(List<MsgPager.MsgBean> messages, int totalPage) {
        if (pageNo == 1) {//onRefresh
            mListAdapter.setNewData(messages);
            mRefreshLayout.setRefreshing(false);
            mListAdapter.setEnableLoadMore(true);
        } else {//
            mListAdapter.addData(messages);
            mListAdapter.loadMoreComplete();
            mRefreshLayout.setEnabled(true);
        }
        if (pageNo >= totalPage) {
            mListAdapter.loadMoreEnd(true);
        }
        pageNo++;
    }

    @Override
    public void onFailure() {
        mRefreshLayout.setRefreshing(false);
        mListAdapter.loadMoreFail();
    }

    @Override
    public void onEmptyView() {
        if (mRefreshLayout != null){
            mRefreshLayout.setRefreshing(false);
        }
        View emptyView = LayoutInflater.from(getActivity()).inflate(R.layout.widget_empty_imageview, null, false);
        mListAdapter.setEmptyView(emptyView);
    }

    @Override
    public void deleteSuccess(int position) {
        mListAdapter.remove(position);
    }

    private OnItemClickListener mOnItemClickListener = new OnItemClickListener() {
        @Override
        public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

            MsgPager.MsgBean msgBean = (MsgPager.MsgBean) baseQuickAdapter.getItem(i);
            ActivityCompat.startActivityForResult(getActivity(), WebDetailActivity.getCallingIntent(getActivity(), new int[]{msgBean.getId(), 0, 0}), WebDetailFragment.REFRESH_INFO_REQUEST_CODE, null);
        }
    };

    private OnItemChildLongClickListener mOnItemChildLongClickListener = new OnItemChildLongClickListener() {
        @Override
        public void onSimpleItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
            DialogFactory.createMsg(getActivity(), position, MessageListFragment.this).show();
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        Logger.d("requestCode= " + requestCode + ",resultCode= " + resultCode);

        if (requestCode == WebDetailFragment.REFRESH_INFO_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            //更新
            Bundle bundle = data.getExtras();
            int msgId = bundle.getInt(WebDetailFragment.MSG_ID);

            Logger.d("msgId=" + msgId);
            updateView(msgId);
        }
    }

    private void updateView(int msgId) {

        List<MsgPager.MsgBean> list = (List<MsgPager.MsgBean>) mListAdapter.getData();
        for (MsgPager.MsgBean msgBean : list) {
            if (msgBean.getId() == msgId) {
                msgBean.setReadStatus(0);
                break;
            }
        }
        mListAdapter.notifyDataSetChanged();
    }

    @Override
    public void msgSure(int position) {
        if (mListAdapter.getItem(position) != null)
            mPresenter.deleteMessage(mListAdapter.getItem(position).getId(), position);
    }
}
