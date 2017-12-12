package com.hhly.lyygame.presentation.view.activity;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.protocol.game.GameNoticeResp;
import com.hhly.lyygame.data.net.protocol.game.IndexActivityByModelIdResp;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.game.GameIntroActivity;
import com.hhly.lyygame.presentation.view.messagedetail.WebDetailActivity;
import com.hhly.lyygame.presentation.view.share.ShareContent;
import com.hhly.lyygame.presentation.view.shareactivity.ShareWebActivity;
import com.hhly.lyygame.presentation.view.shareactivity.ShareWebBuilder;
import com.hhly.lyygame.presentation.view.widget.RecyclerViewDividerItem;
import com.hhly.lyygame.presentation.view.widget.RecyclerViewListDivide;
import com.hhly.lyygame.presentation.view.widget.RecyclerViewListFirstDivide;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;

/**
 * 活动中心
 * Created by Simon on 2016/11/29.
 */

public class ActivitiesCenterFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener, ActivitiesContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
//    @BindView(R.id.refreshLayout)
//    SwipeRefreshLayout mRefreshLayout;

    private ActivitiesAdapter mActivitiesAdapter;
    private ActivitiesContract.Presenter mPresenter;

    public ActivitiesCenterFragment(){
        new ActivitiesPresenter(this);
    }

    public static ActivitiesCenterFragment newInstance(int[] ids) {
        ActivitiesCenterFragment fragment = new ActivitiesCenterFragment();
        Bundle bundle = new Bundle();
        bundle.putIntArray(ActivitiesCenterActivity.ACTIVITY_ID, ids);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static ActivitiesCenterFragment newInstance() {
        return new ActivitiesCenterFragment();
    }

    private int[] mIds = null;//ids[0] gameId;ids[1] noticeType

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mIds = getArguments().getIntArray(ActivitiesCenterActivity.ACTIVITY_ID);
        Logger.d("mIds=" + mIds);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_activities_center_layout;
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new RecyclerViewDividerItem(getActivity(), R.color.transparent, RecyclerViewListDivide.VERTICAL_LIST, 10));
        mRecyclerView.addItemDecoration(new RecyclerViewListFirstDivide(getActivity(), RecyclerViewListDivide.VERTICAL_LIST, 10));
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setRefreshing(true);
        mActivitiesAdapter = new ActivitiesAdapter();

        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

                if (baseQuickAdapter.getItem(i) instanceof IndexActivityByModelIdResp.ActivityPage.ActivityInfo) {

                    IndexActivityByModelIdResp.ActivityPage.ActivityInfo activityInfo = (IndexActivityByModelIdResp.ActivityPage.ActivityInfo) baseQuickAdapter.getItem(i);
                    
                    ShareContent shareContent = new ShareContent();
                    if (ShareWebBuilder.tourUrl.equals(activityInfo.getREDIRECTURL())){

                        shareContent.setWebTitle("泰国游活动");
                        shareContent.setWebUrl(ShareWebBuilder.tourUrl);
                        shareContent.setAppName(getString(R.string.app_name));
                        shareContent.setImage("http://public.13322.com/23c192a0.png");
                        shareContent.setLink(ShareWebBuilder.shareUrl);
                        shareContent.setDescription("邀请朋友抽取玩乐大礼包，100%中奖！更有免费泰国游豪华六日游！");
                        shareContent.setContent("邀请朋友抽取玩乐大礼包，100%中奖！更有免费泰国游豪华六日游！");
                        shareContent.setTitle("免费体验泰国豪华六日游啦！");
                        shareContent.setShowShare(false);
                    }else {
                        shareContent.setWebTitle(activityInfo.getTITLE());
                        shareContent.setWebUrl(activityInfo.getREDIRECTURL());
                        shareContent.setAppName(getString(R.string.app_name));
                        shareContent.setImage("http://public.13322.com/23c192a0.png");
                        shareContent.setLink(activityInfo.getREDIRECTURL());
                        shareContent.setContent(activityInfo.getDESCRIPTION());
                        shareContent.setDescription(activityInfo.getDESCRIPTION());
                        shareContent.setTitle(getString(R.string.lyy_wyx));
                        shareContent.setShowShare(true);

                        if (activityInfo.getREDIRECTURL() == null || activityInfo.getREDIRECTURL().length() < 4){
                            return;
                        }
                    }

                    ActivityCompat.startActivity(getActivity(), ShareWebActivity.getCallingIntent(getActivity(), shareContent), null);

                }else if (baseQuickAdapter.getItem(i) instanceof GameNoticeResp.GameNoticePage.GameNotice){
                    GameNoticeResp.GameNoticePage.GameNotice notice = (GameNoticeResp.GameNoticePage.GameNotice)baseQuickAdapter.getItem(i);

                    ActivityCompat.startActivity(getActivity(), WebDetailActivity.getCallingIntent(getActivity(), new int[]{0, 0, notice.getId()}), null);//公告
                }
            }

        });

        mRecyclerView.setAdapter(mActivitiesAdapter);

        int recyclerViewBackgroundAttr;
        if (getActivity() instanceof GameIntroActivity) {
            mRefreshLayout.setEnabled(false);
            recyclerViewBackgroundAttr = R.attr.containerBackgroundLight;
        } else {
            recyclerViewBackgroundAttr = R.attr.containerBackground;
        }

        TypedArray ta = getActivity().obtainStyledAttributes(new int[]{recyclerViewBackgroundAttr});
        Drawable drawable = ta.getDrawable(0);
        ta.recycle();
        if (drawable != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                mRecyclerView.setBackground(drawable);
            } else {
                mRecyclerView.setBackgroundDrawable(drawable);
            }
        }
        postDelay(new Runnable() {
            @Override
            public void run() {
                fetchData(false);
            }
        }, 500);
    }

    @Override
    protected void fetchData(boolean loadMore) {
        Logger.d("fetchData");
        if (mIds == null){
            mPresenter.getActivities(loadMore);
        }else {
            Logger.d("gameActivity.mids[0]=" + mIds[0] +",mids[1]=" + mIds[1]);
            mPresenter.loadGameActivity(mIds[0], mIds[1]);
        }
    }

    @Override
    public void onRefresh() {
        fetchData(false);
    }

    @Override
    public void onLoadMoreRequested() {

    }

    @Override
    public void setPresenter(ActivitiesContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter != null)
            mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mPresenter != null)
            mPresenter.unsubscribe();
    }

    @Override
    public void showGameActive(List gameNoticeList) {
        if (mRefreshLayout != null)
        mRefreshLayout.setRefreshing(false);
        mActivitiesAdapter.setNewData(gameNoticeList);
    }

    @Override
    public void showActivityList(List list) {
        if (mRefreshLayout != null)
        mRefreshLayout.setRefreshing(false);
        mActivitiesAdapter.setNewData(list);
    }

    @Override
    public void onFailure() {
        if (mRefreshLayout != null)
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onEmptyView() {
        onFailure();
        View emptyView = LayoutInflater.from(getActivity()).inflate(R.layout.widget_empty_imageview, null, false);
        mActivitiesAdapter.setEmptyView(emptyView);
    }
}
