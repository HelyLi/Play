package com.hhly.lyygame.presentation.view.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;

import com.hhly.lyygame.App;
import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.view.BaseActivity;
import com.hhly.lyygame.presentation.view.ToastUtil;
import com.hhly.lyygame.presentation.view.home.GameHomeFragment;
import com.hhly.lyygame.presentation.view.mall.coupon.CouponFragment;
import com.hhly.lyygame.presentation.view.me.MeFragment;
import com.hhly.lyygame.presentation.view.update.VersionUpdateHelper;
import com.hhly.lyygame.presentation.view.widget.BottomBar;
import com.hhly.lyygame.presentation.view.widget.FFragmentTabHost;
import com.orhanobut.logger.Logger;

import butterknife.BindView;

/**
 * 首页
 * Created by Simon on 2016/11/19.
 */

public class MainTabActivity extends BaseActivity implements BottomBar.OnTabSelectListener {

    @BindView(R.id.tab_host)
    FFragmentTabHost mTabHost;
    @BindView(R.id.tab_bar)
    BottomBar mBottomBar;

    public static final String TAG_HOME = "TAG_HOME";
    //    public static final String TAG_GAME = "TAG_GAME";
    public static final String TAG_MALL = "TAG_MALL";
    public static final String TAG_ME = "TAG_ME";
    public static final String EXTRA_NEED_START_MAIN = "extra_need_start_main";
    public static final String TARGET_PAGE = "target_page";

    public static Intent getCallIntent(Context context) {
        Intent intent = new Intent(context, MainTabActivity.class);
        return intent;
    }

    /**
     * @param context
     * @param isNeedStartMain 登录完成后是否需要跳转首页
     * @return
     */
    public static Intent getCallIntent(Context context, boolean isNeedStartMain) {
        Intent intent = new Intent(context, MainTabActivity.class);
        intent.putExtra(EXTRA_NEED_START_MAIN, isNeedStartMain);
        return intent;
    }

    public static Intent getCallIntent(Context context, int targetPage) {
        Intent intent = new Intent(context, MainTabActivity.class);
        intent.putExtra(TARGET_PAGE, targetPage);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBottomBar.addTab(R.id.tab_home, R.drawable.ic_tab_home, R.string.tab_home);
//        mBottomBar.addTab(R.id.tab_game, R.drawable.ic_tab_game, R.string.tab_game);
        mBottomBar.addTab(R.id.tab_mall, R.drawable.ic_tab_mall, R.string.tab_mall);
        mBottomBar.addTab(R.id.tab_me, R.drawable.ic_tab_me, R.string.tab_me);
        mBottomBar.setTabSelectListener(this);

        int targetPage = getIntent().getIntExtra(TARGET_PAGE, -1);
        if (targetPage != -1) {
            mBottomBar.selectTabAtPosition(targetPage);
        } else {
            mBottomBar.selectTabAtPosition(0);
        }

        mTabHost.setup(getContext(), getSupportFragmentManager(), android.R.id.tabcontent);
        mTabHost.addTab(mTabHost.newTabSpec(TAG_HOME).setIndicator(TAG_HOME), GameHomeFragment.class, null);
//        mTabHost.addTab(mTabHost.newTabSpec(TAG_GAME).setIndicator(TAG_GAME), GameRoomFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec(TAG_MALL).setIndicator(TAG_MALL), CouponFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec(TAG_ME).setIndicator(TAG_ME), MeFragment.class, null);


    }



    private VersionUpdateHelper versionUpdateHelper;

    /**
     * 应用更新
     */
    @Override
    protected void onResume() {
        super.onResume();
        if (versionUpdateHelper == null)
            versionUpdateHelper = new VersionUpdateHelper(this, false);
        versionUpdateHelper.startUpdateVersion();
    }

//    public void checkUpdateVersion(){
//        if(versionUpdateHelper == null)
//            versionUpdateHelper = new VersionUpdateHelper(this, false);
//        versionUpdateHelper.resetCancelFlag();
//        versionUpdateHelper.startUpdateVersion();
//    }

    @Override
    protected void onPause() {
        super.onPause();
        if (versionUpdateHelper != null)
            versionUpdateHelper.stopUpdateVersion();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_layout;
    }

    @Override
    public void onTabSelected(int tabId) {
        switch (tabId) {
            case R.id.tab_home:
                mTabHost.setCurrentTabByTag(TAG_HOME);
                break;
//            case R.id.tab_game:
//                mTabHost.setCurrentTabByTag(TAG_GAME);
//                break;
            case R.id.tab_mall:
                mTabHost.setCurrentTabByTag(TAG_MALL);
                break;
            case R.id.tab_me:
                mTabHost.setCurrentTabByTag(TAG_ME);
                break;
        }
    }

    private long mLastBackEventTime = 0;
    private static final long INTERVAL = 3000;
    private int mBackCount = 0;
    private CountDownTimer mExitCd = new CountDownTimer(INTERVAL, INTERVAL) {
        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            mBackCount = 0;
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        Logger.d("onKeyDown=" + keyCode);

        if (KeyEvent.KEYCODE_BACK == keyCode) {
            if (!(TAG_HOME.equals(mTabHost.getCurrentTabTag()))) {
                mBottomBar.selectTabWithId(R.id.tab_home);
                return true;
            }
            long current = System.currentTimeMillis();
            if (mBackCount == 0) {
                mLastBackEventTime = current;
                mBackCount++;
                ToastUtil.showTip(getContext(), getString(R.string.exit_interval_tip));
                mExitCd.start();
            } else {
                if (current - mLastBackEventTime <= INTERVAL) {
                    //exit
                    mExitCd.cancel();
                    super.onKeyDown(keyCode, event);
                    App.exit();
                } else {
                    mBackCount = 0;
                }
                mLastBackEventTime = current;
            }
            return true;
        }
        mBackCount = 0;
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Logger.d("MainTab.onNewIntent");

        if (intent.hasExtra(TARGET_PAGE)) {
            mBottomBar.selectTabAtPosition(intent.getIntExtra(TARGET_PAGE, 0));
            return;
        }
        boolean isNeedStartMain = intent.getBooleanExtra(EXTRA_NEED_START_MAIN, false);
        if (isNeedStartMain) {
            mBottomBar.selectTabAtPosition(0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Logger.d("requestCode" + requestCode + ",resultCode" + requestCode);

        for (FFragmentTabHost.TabInfo tabInfo : mTabHost.getTabs()) {
            Fragment fragment = tabInfo.getFragment();
            if (fragment != null)
                fragment.onActivityResult(requestCode, resultCode, data);
        }
    }


}
