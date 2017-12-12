package com.hhly.lyygame.presentation.view.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hhly.lyygame.App;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.db.manager.AccountManager;
import com.hhly.lyygame.data.net.protocol.game.IndexActivityByModelIdResp;
import com.hhly.lyygame.data.net.protocol.game.NewsListResp;
import com.hhly.lyygame.presentation.downloadutils.DownloadItem;
import com.hhly.lyygame.presentation.utils.ActivityUtil;
import com.hhly.lyygame.presentation.utils.DisplayUtil;
import com.hhly.lyygame.presentation.utils.NetworkUtil;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.activity.ActivitiesCenterActivity;
import com.hhly.lyygame.presentation.view.banner.BannerHeader;
import com.hhly.lyygame.presentation.view.game.GameIntroActivity;
import com.hhly.lyygame.presentation.view.immersive.IImmersiveApply;
import com.hhly.lyygame.presentation.view.immersive.ToolbarHelper;
import com.hhly.lyygame.presentation.view.messagedetail.WebDetailActivity;
import com.hhly.lyygame.presentation.view.notification.NotificationActivity;
import com.hhly.lyygame.presentation.view.search.SearchActivity;
import com.hhly.lyygame.presentation.view.share.ShareContent;
import com.hhly.lyygame.presentation.view.shareactivity.ShareWebActivity;
import com.hhly.lyygame.presentation.view.shareactivity.ShareWebBuilder;
import com.hhly.lyygame.presentation.view.task.TaskActivity;
import com.hhly.lyygame.presentation.view.transfer.CouponTransferActivity;
import com.hhly.lyygame.presentation.view.transfer.GameTransferActivity;
import com.hhly.lyygame.presentation.view.widget.NoticeView;
import com.hhly.lyygame.presentation.view.widget.RecyclerViewListDivide;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.hhly.lyygame.R.id.menu_notify;

/**
 * 游戏首页
 * Created by Simon on 2016/11/19.
 */

public class GameHomeFragment extends BaseFragment implements IImmersiveApply,
        SwipeRefreshLayout.OnRefreshListener, GameHomeContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.toolbar_title_tv)
    TextView mToolbarTitleTv;
    @BindView(R.id.search_container)
    FrameLayout mSearchContainer;

    private BannerHeader mBannerHeader;
    private NoticeView mNoticeView;
    private GameHomeAdapter mGameHomeAdapter;
    private GameHomeContract.Presenter mGamePresenter;

    private int mCurrentScrolledY = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new GameHomePresenter(this);
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        mSearchContainer.setVisibility(View.GONE);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new RecyclerViewListDivide(getActivity(), R.color.transparent, RecyclerViewListDivide.VERTICAL_LIST, 15));
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //监听滑动距离，修改toolbar透明度
                mCurrentScrolledY += dy;
                mToolbarHelper.applyScroll(mCurrentScrolledY, getActivity().getResources().getDimensionPixelSize(R.dimen.home_scroll_delta));
            }
        });

        ViewGroup viewGroup = null;
        if (view instanceof ViewGroup) {
            viewGroup = (ViewGroup) view;
        }
        if (viewGroup == null) {
            Logger.d("viewGroup is null");
        }
        View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_game_home1_1_header_layout, viewGroup, false);
        mBannerHeader = (BannerHeader) headerView.findViewById(R.id.banner_header);
        mNoticeView = (NoticeView) headerView.findViewById(R.id.notice_view);
        mNoticeView.setOnNoticeClickListener(mOnNoticeClickListener);

        mGameHomeAdapter = new GameHomeAdapter(getActivity());
        mGameHomeAdapter.addHeaderView(headerView);
        mGameHomeAdapter.setOnItemChildClickListener(mOnItemChildClickListener);
        mGameHomeAdapter.updateSelect();
        mRecyclerView.setAdapter(mGameHomeAdapter);

        mRefreshLayout.setColorSchemeResources(R.color.color_a965f4, R.color.color_3c4444, R.color.color_f60808);
        mRefreshLayout.setProgressViewOffset(false, -DisplayUtil.dip2px(getActivity(), 40), getResources().getDimensionPixelSize(R.dimen.home_refresh_offset));
        mRefreshLayout.setOnRefreshListener(this);

        if (!ToolbarHelper.hasKitkat()) {
            //            FrameLayout.LayoutParams flp = (FrameLayout.LayoutParams) mRecyclerView.getLayoutParams();
            ViewGroup.LayoutParams params = mRecyclerView.getLayoutParams();
            ViewGroup.MarginLayoutParams marginParams = null;
            //获取view的margin设置参数
            if (params instanceof ViewGroup.MarginLayoutParams) {
                marginParams = (ViewGroup.MarginLayoutParams) params;
            } else {
                //不存在时创建一个新的参数
                //基于View本身原有的布局参数对象
                marginParams = new ViewGroup.MarginLayoutParams(params);
            }
            marginParams.topMargin = ToolbarHelper.getToolbarHeight(getActivity());
            mRecyclerView.setLayoutParams(marginParams);
        }

        //获取数据
        showRefresh();
        fetchData(false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mBannerHeader != null) {
            mBannerHeader.onStart();
        }
    }

    MenuItem menuItem = null;

    protected void initToolbarView(View view) {
        super.initToolbarView(view);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolBar);
        if (toolbar != null) {
            toolbar.setNavigationIcon(null);
            toolbar.setTitle("");
            toolbar.inflateMenu(R.menu.home_menu);
            /*toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    if (item.getItemId() == R.id.menu_notify) {
                        ActivityCompat.startActivity(getActivity(), NotificationActivity.getCallingIntent(getActivity()), null);
                        return true;
                    }
                    return false;
                }
            });*/
            menuItem = toolbar.getMenu().findItem(menu_notify);
            menuItem.setVisible(AccountManager.getInstance().isLogin());
            View actionView = menuItem.getActionView();
            if (actionView != null) {
                actionView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //                        processMenuItem(menuItem, 0);
                        ActivityCompat.startActivity(getActivity(), NotificationActivity.getCallingIntent(getActivity()), null);
                    }
                });
            }
        }
    }

    @Override
    protected boolean enableHomeAsUp() {
        return false;
    }

    @Override
    protected boolean enableSetActionBar() {
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        mGamePresenter.subscribe();
        mToolbarTitleTv.setText(R.string.lyy_search_hint);
        if (menuItem != null) {
            menuItem.setVisible(AccountManager.getInstance().isLogin());
        }
        if (NetworkUtil.isAvailable(App.getContext()) && AccountManager.getInstance().isLogin()) {
            mGamePresenter.getMsgPage();
        }
        if (mGameHomeAdapter != null) {
            mGameHomeAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mGamePresenter.unsubscribe();
        if (mRefreshLayout != null && mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mBannerHeader != null) {
            mBannerHeader.onStop();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mGameHomeAdapter != null) {
            mGameHomeAdapter.disposeGame();
        }
    }

    @Override
    protected void fetchData(boolean refresh) {
        if (mBannerHeader != null) {
            mBannerHeader.fetchHomeData();
        }
        postDelay(new Runnable() {
            @Override
            public void run() {
                mGamePresenter.getNews(false);
            }
        }, 100);
        postDelay(new Runnable() {
            @Override
            public void run() {
                mGamePresenter.getOnlyGames(false);
            }
        }, 200);
        postDelay(new Runnable() {
            @Override
            public void run() {
                mGamePresenter.getActivitis(false);
            }
        }, 300);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_game_home_layout;
    }

    @Override
    public boolean applyImmersive() {
        return true;
    }

    @Override
    public boolean applyScroll() {
        return true;
    }

    @Override
    public float initAlpha() {
        return 0.0f;
    }

    @Override
    public void onRefresh() {
        fetchData(true);
    }

    @OnClick(R.id.search_container)
    public void onClick() {
        ActivityCompat.startActivity(getActivity(), SearchActivity.getCallingIntent(getActivity()), null);
    }

    @Override
    public void showOnlyGameList(List<DownloadItem> gameInfoList) {
        mGameHomeAdapter.updateOnlyGame(gameInfoList);
    }

    @Override
    public void showActivityList(List<IndexActivityByModelIdResp.ActivityPage.ActivityInfo> list) {
        mGameHomeAdapter.updateActivity(list);
    }

    @Override
    public void onMsgSize(int size) {
        if (menuItem != null) {
            processMenuItem(menuItem, size);
        }
    }

    @Override
    public void hideRefresh() {
        postDelay(new Runnable() {
            @Override
            public void run() {
                if (mRefreshLayout != null && mRefreshLayout.isRefreshing()) {
                    mRefreshLayout.setRefreshing(false);
                }
            }
        }, 300);
    }

    @Override
    public void showNews(List<NewsListResp.NewsPage.NewsInfo> list) {
        Logger.d("news.size=" + list.size());
        if (mNoticeView == null) return;
        mNoticeView.addNotice(list);
        if (list.size() > 1)
            mNoticeView.startFlipping();
    }

    private void processMenuItem(MenuItem menuItem, int num) {
        FrameLayout frameLayout = (FrameLayout) menuItem.getActionView();
        TextView notity = (TextView) frameLayout.findViewById(R.id.tab_notify_text);
        if (num <= 99) {
            notity.setText(String.valueOf(num));
        } else {
            notity.setText("99+");
        }
        if (num != 0) {
            notity.setVisibility(View.VISIBLE);
        } else {
            notity.setVisibility(View.GONE);
        }
    }

    public void setPresenter(GameHomeContract.Presenter gamePresenter) {
        mGamePresenter = gamePresenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    void showRefresh() {
        if (mRefreshLayout != null && !mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(true);
        }
    }

    NoticeView.OnNoticeClickListener mOnNoticeClickListener = new NoticeView.OnNoticeClickListener() {
        @Override
        public void onNotieClick(int position, NewsListResp.NewsPage.NewsInfo notice) {
            ActivityCompat.startActivity(getActivity(), WebDetailActivity.getCallingIntent(getActivity(), new int[]{0, 0, notice.getId()}), null);
        }
    };

    private BaseQuickAdapter.OnItemChildClickListener mOnItemChildClickListener = new BaseQuickAdapter.OnItemChildClickListener() {
        @Override
        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            Logger.d("position=" + position);
            switch (view.getId()) {
                case R.id.select_item_ll://
                    toSelectItem(position);
                    break;
                case R.id.game_item_pic_iv://独家游戏
                    toOnlyGameItem((DownloadItem) adapter.getItem(position));
                    break;
                case R.id.select_item_icon_iv:
                    toActivityItem((IndexActivityByModelIdResp.ActivityPage.ActivityInfo) adapter.getItem(position));
                    break;
            }
        }
    };

    private void toSelectItem(int position) {
        switch (position) {
            case 0://
                if (ActivityUtil.checkLoginAndRequestLogin(getActivity())) {
                    ActivityCompat.startActivity(getActivity(), TaskActivity.getCallingIntent(getActivity()), null);
                }
                break;
            case 1:
                ActivityCompat.startActivity(getActivity(), ActivitiesCenterActivity.getCallingIntent(getActivity()), null);
                break;
            case 2:
                if (ActivityUtil.checkLoginAndRequestLogin(getActivity())) {
                    ActivityCompat.startActivity(getActivity(), GameTransferActivity.getCallingIntent(getActivity()), null);
                }
                break;
            case 3:
                if (ActivityUtil.checkLoginAndRequestLogin(getActivity())) {
                    ActivityCompat.startActivity(getActivity(), CouponTransferActivity.getCallingIntent(getActivity()), null);
                }
                break;
            default:
                break;
        }
    }

    private void toOnlyGameItem(DownloadItem downloadItem) {
        ActivityCompat.startActivity(getActivity(), GameIntroActivity.getCallingIntent(getActivity(), downloadItem.record.getGameId(), false), null);
    }

    private void toActivityItem(IndexActivityByModelIdResp.ActivityPage.ActivityInfo activityInfo) {

        ShareContent shareContent = new ShareContent();
        if (activityInfo.getREDIRECTURL().equals(ShareWebBuilder.tourUrl)){

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
    }

}
