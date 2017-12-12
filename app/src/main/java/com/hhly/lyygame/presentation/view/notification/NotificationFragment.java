package com.hhly.lyygame.presentation.view.notification;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.SectionEntity;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.protocol.user.MsgPager;
import com.hhly.lyygame.data.net.protocol.user.NotificationActivityResp;
import com.hhly.lyygame.presentation.utils.CollectionUtil;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.activity.ActivitiesCenterActivity;
import com.hhly.lyygame.presentation.view.message.MessageListActivity;
import com.hhly.lyygame.presentation.view.messagedetail.WebDetailActivity;
import com.hhly.lyygame.presentation.view.widget.RecyclerViewListDivide;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import butterknife.BindView;

/**
 * Created by Simon on 2016/11/30.
 */

public class NotificationFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, NotificationContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
//    @BindView(R.id.refreshLayout)
//    SwipeRefreshLayout mRefreshLayout;

    private NotificationAdapter mAdapter;
    private NotificationContract.Presenter mPresenter;
    private NotificationSection mNotificationSection;
    private boolean isMsgLoadFinshed;
    private boolean isNoticeLoadFinshed;

    public static NotificationFragment newInstance() {
        return new NotificationFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new NotificationAdapter();
        CopyOnWriteArrayList<SectionEntity> list = new CopyOnWriteArrayList();
        list.add(new NotificationSection(R.drawable.ic_notification_activities, getString(R.string.lyy_notification_activities), mActivityAction));
        mNotificationSection = new NotificationSection(R.drawable.ic_notification_all, getString(R.string.lyy_notification_all), mAllAction);
        list.add(mNotificationSection);
        mAdapter.setNewData(list);
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {

        mRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new RecyclerViewListDivide(getActivity(), R.color.color_e9e9e9, RecyclerViewListDivide.VERTICAL_LIST, 0.8f));

        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                SectionEntity entity = (SectionEntity) baseQuickAdapter.getItem(i);
                if (entity.isHeader) {
                    post(((NotificationSection) (entity)).getAction());
                } else if (entity instanceof MsgItem) {//消息
                    //                    MsgItem item = (MsgItem) entity;
                    MsgPager.MsgBean item = ((MsgItem) entity).getData();
                    ActivityCompat.startActivity(getActivity(), WebDetailActivity.getCallingIntent(getActivity(), new int[]{item.getId(), 0, 0}), null);

                } else if (entity instanceof ActivityItem) {//活动
                    //                    ActivityItem item = (ActivityItem) entity;
                    NotificationActivityResp.ActivityBean item = ((ActivityItem) entity).getData();
                    ActivityCompat.startActivity(getActivity(), WebDetailActivity.getCallingIntent(getActivity(), new int[]{item.getId(), 0, 0}), null);
                }
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        fetchData(false);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_notification_layout;
    }

    @Override
    public void onRefresh() {
        isMsgLoadFinshed = false;
        isNoticeLoadFinshed = false;
        fetchData(false);
    }

    private final Runnable mActivityAction = new Runnable() {
        @Override
        public void run() {
            ActivityCompat.startActivity(getActivity(), ActivitiesCenterActivity.getCallingIntent(getActivity()), null);
        }
    };

    private final Runnable mAllAction = new Runnable() {
        @Override
        public void run() {
            ActivityCompat.startActivity(getActivity(), MessageListActivity.getCallingIntent(getActivity()), null);
        }
    };

    @Override
    public void showNotifications(List<NotificationActivityResp.ActivityBean> activitys) {
        isNoticeLoadFinshed = true;
        closeRefreshLayout();
        if (CollectionUtil.isEmpty(activitys))
            return;
        if (activitys.size() > 4) {
            activitys.subList(4, activitys.size()).clear();
        }

        List<SectionEntity> list = mAdapter.getData();
        for (SectionEntity section : list) {
            if (section instanceof ActivityItem) {
                list.remove(section);
            }
        }
        List<SectionEntity> notificationList = new CopyOnWriteArrayList<>();

        for (NotificationActivityResp.ActivityBean bean : activitys) {
            notificationList.add(new ActivityItem(bean));
        }
        list.addAll(1, notificationList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void getNotificationsFail() {
        isNoticeLoadFinshed = true;
        closeRefreshLayout();
    }

    @Override
    public void showMsgs(List<MsgPager.MsgBean> msgBeans, int unreads) {
        isMsgLoadFinshed = true;
        closeRefreshLayout();
        if (CollectionUtil.isEmpty(msgBeans))
            return;
        Collections.sort(msgBeans);
        if (msgBeans.size() > 4) {
            msgBeans.subList(4, msgBeans.size()).clear();
        }

        List<SectionEntity> list = mAdapter.getData();
        for (SectionEntity section : list) {
            if (section instanceof MsgItem) {
                list.remove(section);
            }
        }

        List<SectionEntity> msgList = new CopyOnWriteArrayList<>();
        for (MsgPager.MsgBean bean : msgBeans) {
            msgList.add(new MsgItem(bean));
        }
        mNotificationSection.setUnreadCount(unreads);
        list.addAll(msgList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void getMsgFail() {
        isMsgLoadFinshed = true;
        closeRefreshLayout();
    }

    @Override
    public void setPresenter(NotificationContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    protected void fetchData(boolean isLoadMore) {
        mRefreshLayout.setRefreshing(!isLoadMore);
        mPresenter.getMsgPage();
        mPresenter.getNotificationActivity();
    }

    private void closeRefreshLayout() {
        if (isNoticeLoadFinshed && isMsgLoadFinshed) {
            mRefreshLayout.setRefreshing(false);
        }
    }
}
