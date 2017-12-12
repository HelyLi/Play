package com.hhly.lyygame.presentation.view.me;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hhly.lyygame.App;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.db.entry.UserInfo;
import com.hhly.lyygame.data.db.manager.AccountManager;
import com.hhly.lyygame.presentation.utils.ActivityUtil;
import com.hhly.lyygame.presentation.utils.GlideUtils;
import com.hhly.lyygame.presentation.utils.NetworkUtil;
import com.hhly.lyygame.presentation.utils.SharedPrefsFavouriteUtil;
import com.hhly.lyygame.presentation.utils.StatisticalUtil;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.DialogFactory;
import com.hhly.lyygame.presentation.view.ToastUtil;
import com.hhly.lyygame.presentation.view.address.AddressManagerActivity;
import com.hhly.lyygame.presentation.view.contact.ContactActivity;
import com.hhly.lyygame.presentation.view.download.DownloadManagerActivity;
import com.hhly.lyygame.presentation.view.exchange.ExchangeHistoryActivity;
import com.hhly.lyygame.presentation.view.favourite.FavouriteActivity;
import com.hhly.lyygame.presentation.view.feedback.FeedbackActivity;
import com.hhly.lyygame.presentation.view.info.InfoActivity;
import com.hhly.lyygame.presentation.view.main.MainTabActivity;
import com.hhly.lyygame.presentation.view.message.MessageListActivity;
import com.hhly.lyygame.presentation.view.pay.PayActivity;
import com.hhly.lyygame.presentation.view.paylist.PayListActivity;
import com.hhly.lyygame.presentation.view.share.ShareContent;
import com.hhly.lyygame.presentation.view.shareactivity.ShareWebActivity;
import com.hhly.lyygame.presentation.view.shareactivity.ShareWebBuilder;
import com.hhly.lyygame.presentation.view.signin.SignInActivity;
import com.hhly.lyygame.presentation.view.task.TaskActivity;
import com.hhly.lyygame.presentation.view.transfer.CouponTransferActivity;
import com.hhly.lyygame.presentation.view.transfer.GameTransferActivity;
import com.hhly.lyygame.presentation.view.update.VersionUpdateHelper;
import com.hhly.lyygame.presentation.view.widget.menu.MenuItem;
import com.hhly.lyygame.presentation.view.widget.menu.MenuTwoAdapter;
import com.hhly.lyygame.presentation.view.widget.menu.MenuTwoDivide;
import com.hhly.lyygame.presentation.view.widget.menu.MenuTwoGroup;
import com.hhly.lyygame.presentation.view.widget.menu.MenuTwoItem;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Simon on 2016/11/22.
 */

public class MeFragment extends BaseFragment implements MeContract.View {

    public static final int REFRESH_INFO_REQUEST_CODE = 0;

    @BindView(R.id.avatar_iv)
    CircleImageView mAvatarIv;
    @BindView(R.id.nickname_tv)
    TextView mNicknameTv;
    @BindView(R.id.coins_tv)
    TextView mCoinsTv;
    @BindView(R.id.coupon_tv)
    TextView mCouponTv;
    @BindView(R.id.score_tv)
    TextView mScoreTv;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.not_login_tip_tv)
    TextView mNotLoginTipTv;

    @BindView(R.id.exit_btn)
    Button mExitBtn;

    private MeContract.Presenter mPresenter;

    @Override
    protected void fetchData(boolean loadMore) {
        //获取用户数据
        post(new Runnable() {
            @Override
            public void run() {
                UserInfo userInfo = AccountManager.getInstance().getUserInfo();
                if (userInfo != null) {
                    switchToLogon(userInfo);
                } else {
                    switchToNotLogin();
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me_layout;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new MePresenter(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchData(false);
        mPresenter.subscribe();
        if (NetworkUtil.isAvailable(App.getContext())) {
            mPresenter.getUserInfo();
        }
        Logger.d("Me.onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {

//        mCoinsBtn.setBackgroundColor(getResources().getColor(R.color.color_e7384a));
//        mScoreBtn.setBackgroundColor(getResources().getColor(R.color.color_e7384a));

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayout.VERTICAL, false));
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.addItemDecoration(new MenuTwoDivide(getActivity()));
        MenuTwoAdapter menuAdapter = new MenuTwoAdapter(getMenu());
        mRecyclerView.setAdapter(menuAdapter);

        menuAdapter.notifyDataSetChanged();

        post(new Runnable() {
            @Override
            public void run() {
                fetchData(false);
            }
        });
    }

    public List<MenuTwoGroup> getMenu() {
        int size = 3;
        List<MenuTwoGroup> menuGroupList = new ArrayList<>();
        MenuTwoGroup group = new MenuTwoGroup();
        group.setIndex(0);
        group.setGroupName("Private");
        ArrayList<MenuTwoItem> itemList = new ArrayList<>();

        itemList.add(new MenuTwoItem(new MenuItem(0, R.drawable.ic_me_coin, R.string.lyy_task_title, mTaskAction),
                new MenuItem(0, R.drawable.ic_me_message, R.string.lyy_me_my_message, mMessageAction)));
        itemList.add(new MenuTwoItem(new MenuItem(0, R.drawable.ic_me_favourite, R.string.lyy_me_my_favourite, mFavouriteAction),
                new MenuItem(0, R.drawable.ic_me_exchange, R.string.lyy_me_my_buy_history, mExchangeAction)));
        group.setChildList(itemList);
        menuGroupList.add(group);
        
        group = new MenuTwoGroup();
        group.setIndex(1);
        group.setGroupName("Private");
        ArrayList<MenuTwoItem> itemList1 = new ArrayList<>();

        itemList1.add(new MenuTwoItem(new MenuItem(0, R.drawable.ic_me_game_transfer, R.string.lyy_me_game_transfer_details, mGameTransferAction),
                new MenuItem(0, R.drawable.ic_me_coupon_transfer, R.string.lyy_me_coupon_transfer_details, mCouponTransferAction)));
        itemList1.add(new MenuTwoItem(new MenuItem(0, R.drawable.ic_me_recharge, R.string.lyy_me_recharge_details, mRechargeDetailsAction),
                new MenuItem(0, R.drawable.ic_me_address, R.string.lyy_me_my_address, mAddressAction)));
        itemList1.add(new MenuTwoItem(new MenuItem(0, R.drawable.ic_me_download_manager, R.string.lyy_me_download_manager, mDownloadManagerAction),
                new MenuItem(0, R.drawable.ic_me_update, R.string.lyy_me_my_update, mUpdateAction)));

        group.setChildList(itemList1);
        menuGroupList.add(group);

        group = new MenuTwoGroup();
        group.setIndex(2);
        group.setGroupName("Private");
        ArrayList<MenuTwoItem> itemList2 = new ArrayList<>();
        itemList2.add(new MenuTwoItem(new MenuItem(0, R.drawable.ic_me_feedback, R.string.lyy_me_my_feedback, mFeedbackAction),
                new MenuItem(0, R.drawable.ic_me_contact, R.string.lyy_me_my_contact, mContactAction)));

        group.setChildList(itemList2);
        menuGroupList.add(group);
        return menuGroupList;
    }

    @OnClick(R.id.exit_btn)
    void onExitAccountClick(View view) {

        DialogFactory.createExit(getActivity(), new ExitLoginDialog.ExitCallback() {
            @Override
            public void exitSure() {
                if (NetworkUtil.isAvailable(getActivity())) {
                    mPresenter.logout();
                    StatisticalUtil.onProfileSignOff();
                }
                AccountManager.getInstance().clearCurrentUserInfo();
                fetchData(false);
            }
        }).show();
    }

    /**
     * 用户信息
     *
     */
    @OnClick(R.id.avatar_iv)
    void onAvatarClick(View view) {
        HashMap<String, String> map = new HashMap<>();
        map.put("type", "InfoActivity");
        StatisticalUtil.onEvent(getActivity(), "", map);
        ActivityCompat.startActivityForResult(getActivity(), InfoActivity.getCallingIntent(getActivity()), MeFragment.REFRESH_INFO_REQUEST_CODE, null);
    }

    /**
     * 签到
     */
    @OnClick(R.id.sign_in_iv)
    void onSignInClick(View view) {

        if (ActivityUtil.checkLoginAndRequestLogin(getActivity())) {
            ActivityCompat.startActivity(getActivity(), SignInActivity.getCallingIntent(getActivity()), null);
        }
    }

    /**
     * 登录／注册
     */
    @OnClick(R.id.not_login_tip_tv)
    void onNotLoginClick(View view) {
        ActivityUtil.checkLoginAndRequestLoginNormal(getActivity());
    }

    /**
     * 跳转充值
     */
    @OnClick(R.id.coins_tv)
    void onCoinsTvClick(View view) {

        if (ActivityUtil.checkLoginAndRequestLogin(getActivity())) {
            SharedPrefsFavouriteUtil.putBooleanValue(getActivity(), SharedPrefsFavouriteUtil.PAY_COINS, false);
            ActivityCompat.startActivity(getContext(), PayActivity.getCallingIntent(getContext()), null);
        }
    }

    @OnClick(R.id.coupon_tv)
    void onCouponTvClick(View view) {
        if (ActivityUtil.checkLoginAndRequestLogin(getActivity())) {
            ActivityCompat.startActivity(getActivity(), CouponTransferActivity.getCallingIntent(getActivity()), null);
        }
    }

    /**
     * 跳转积分任务
     */
    @OnClick(R.id.score_tv)
    void onScoreTvClick(View view) {

        if (ActivityUtil.checkLoginAndRequestLogin(getActivity())) {
            ActivityCompat.startActivity(getActivity(), TaskActivity.getCallingIntent(getActivity()), null);
        }
    }

    /**
     * 我的消息
     */
    private final Runnable mMessageAction = new Runnable() {
        @Override
        public void run() {
            if (ActivityUtil.checkLoginAndRequestLogin(getActivity())) {
                ActivityCompat.startActivity(getActivity(), MessageListActivity.getCallingIntent(getActivity()), null);
            }
        }
    };

    /**
     * 收货地址
     */
    private final Runnable mAddressAction = new Runnable() {
        @Override
        public void run() {
            if (ActivityUtil.checkLoginAndRequestLogin(getActivity())) {
                ActivityCompat.startActivity(getActivity(), AddressManagerActivity.getCallingIntent(getActivity()), null);
            }
        }
    };

    /**
     * 兑换纪录
     */
    private final Runnable mExchangeAction = new Runnable() {
        @Override
        public void run() {
            if (ActivityUtil.checkLoginAndRequestLogin(getActivity())) {
                ActivityCompat.startActivity(getActivity(), ExchangeHistoryActivity.getCallingIntent(getActivity()), null);
            }
        }
    };

    /**
     * 我的收藏
     */
    private final Runnable mFavouriteAction = new Runnable() {
        @Override
        public void run() {

            if (ActivityUtil.checkLoginAndRequestLogin(getActivity())) {
                ActivityCompat.startActivity(getActivity(), FavouriteActivity.getCallingIntent(getActivity()), null);
            }
        }
    };

    /**
     * 意见反馈
     */
    private final Runnable mFeedbackAction = new Runnable() {
        @Override
        public void run() {
            if (ActivityUtil.checkLoginAndRequestLogin(getActivity())) {
                ActivityCompat.startActivity(getActivity(), FeedbackActivity.getCallingIntent(getActivity()), null);
            }
        }
    };

    /**
     * 联系我们
     */
    private final Runnable mContactAction = new Runnable() {
        @Override
        public void run() {
            ActivityCompat.startActivity(getActivity(), ContactActivity.getCallingIntent(getActivity()), null);
        }
    };

    /**
     * 检查更新
     */
    private final Runnable mUpdateAction = new Runnable() {
        @Override
        public void run() {

            if (MeFragment.this.getActivity() instanceof MainTabActivity){
                Logger.d("mefragment.update");
//                ((MainTabActivity)MeFragment.this.getActivity()).checkUpdateVersion();
                checkUpdateVersion();
            }
        }
    };

    private VersionUpdateHelper versionUpdateHelper;

    void checkUpdateVersion(){
        if(versionUpdateHelper == null)
            versionUpdateHelper = new VersionUpdateHelper(this.getActivity(), true);
        versionUpdateHelper.resetCancelFlag();
        versionUpdateHelper.startUpdateVersion();
    }

    /**
     * 充值明细
     */
    private final Runnable mRechargeDetailsAction = new Runnable() {
        @Override
        public void run() {
            if (ActivityUtil.checkLoginAndRequestLogin(getActivity())) {
                ActivityCompat.startActivity(getActivity(), PayListActivity.getCallingIntent(getActivity()), null);
            }
        }
    };

    /**
     * 积分任务
     */
    private final Runnable mTaskAction = new Runnable() {
        @Override
        public void run() {

            if (ActivityUtil.checkLoginAndRequestLogin(getActivity())) {
                ActivityCompat.startActivity(getActivity(), TaskActivity.getCallingIntent(getActivity()), null);
            }
        }
    };

    /**
     * 下载管理
     */
    private final Runnable mDownloadManagerAction = new Runnable() {
        @Override
        public void run() {

            ActivityCompat.startActivity(getActivity(), DownloadManagerActivity.getCallingIntent(getActivity()), null);
        }
    };

    /**
     * 游戏划账
     */
    private final Runnable mGameTransferAction = new Runnable() {
        @Override
        public void run() {
            if (ActivityUtil.checkLoginAndRequestLogin(getActivity())) {
                ActivityCompat.startActivity(getActivity(), GameTransferActivity.getCallingIntent(getActivity()), null);
            }
        }
    };

    /**
     * 乐盈劵兑换
     */
    private final Runnable mCouponTransferAction = new Runnable() {
        @Override
        public void run() {
            if (ActivityUtil.checkLoginAndRequestLogin(getActivity())) {
                ActivityCompat.startActivity(getActivity(), CouponTransferActivity.getCallingIntent(getActivity()), null);
            }
        }
    };

    /**
     *
     */
    private final Runnable mTourAction = new Runnable() {
        @Override
        public void run() {

//            ShareContent shareContent = new ShareContent();
//            shareContent.setWebTitle("泰国游活动");
//            shareContent.setWebUrl(ShareWebBuilder.tourUrl);
//            shareContent.setAppName(getString(R.string.app_name));
//            shareContent.setImage("http://public.13322.com/23c192a0.png");
//            shareContent.setLink(ShareWebBuilder.shareUrl);
//            shareContent.setContent("");
//            shareContent.setTitle(getString(R.string.lyy_wyx));
//            shareContent.setShowShare(false);

            ShareContent shareContent = new ShareContent();
            shareContent.setWebTitle("泰国游活动");
            shareContent.setWebUrl(ShareWebBuilder.tourUrl);
            shareContent.setAppName(getString(R.string.app_name));
            shareContent.setImage("http://public.13322.com/23c192a0.png");
            shareContent.setLink(ShareWebBuilder.shareUrl);
            shareContent.setDescription("邀请朋友抽取玩乐大礼包，100%中奖！更有免费泰国游豪华六日游！");
            shareContent.setContent("邀请朋友抽取玩乐大礼包，100%中奖！更有免费泰国游豪华六日游！");
            shareContent.setTitle("免费体验泰国豪华六日游啦！");
            shareContent.setShowShare(false);

            ActivityCompat.startActivity(getActivity(), ShareWebActivity.getCallingIntent(getActivity(), shareContent), null);
        }
    };

    private void switchToNotLogin() {

        mExitBtn.setVisibility(View.GONE);
        mAvatarIv.setVisibility(View.GONE);
        mNicknameTv.setVisibility(View.GONE);
        mNotLoginTipTv.setVisibility(View.VISIBLE);
        Spannable span = new SpannableString(getString(R.string.lyy_not_login_tip));
        span.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_fff000)), 7, 13, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mNotLoginTipTv.setText(span);
        mCoinsTv.setText(String.valueOf(0));
        mCouponTv.setText(String.valueOf(0));
        mScoreTv.setText(String.valueOf(0));
    }

    private void switchToLogon(UserInfo userInfo) {

        mExitBtn.setVisibility(View.VISIBLE);
        mAvatarIv.setVisibility(View.VISIBLE);
        GlideUtils.loadImage(this.getActivity(), userInfo.getHeadIcon(), mAvatarIv, null, R.drawable.ic_game_pic_default_01, R.drawable.ic_game_pic_default_01);
        mNicknameTv.setVisibility(View.VISIBLE);
        mNicknameTv.setText(userInfo.getNickname());
        mNotLoginTipTv.setVisibility(View.GONE);
        //        ExtraUserInfo extraUserInfo = AccountManager.getInstance().getExtraUserInfo();
        mScoreTv.setText(TextUtils.isEmpty(userInfo.getJf()) ? "0" : userInfo.getJf());
        mCouponTv.setText(TextUtils.isEmpty(userInfo.getLyq()) ? "0" : userInfo.getLyq());
        mCoinsTv.setText(TextUtils.isEmpty(userInfo.getLyb()) ? "0" : userInfo.getLyb());
    }

    @Override
    public void setPresenter(MeContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (AccountManager.getInstance().isLogin()) {
            mPresenter.getUserInfo();
        }
    }

    @Override
    public void getUserInfoSuccess() {
        fetchData(false);
    }

    @Override
    public void getUserInfoFailure(String msg) {
        dismissLoading();
        ToastUtil.showTip(getActivity(), TextUtils.isEmpty(msg) ? getString(R.string.lyy_game_network_state) : msg);
    }

}
