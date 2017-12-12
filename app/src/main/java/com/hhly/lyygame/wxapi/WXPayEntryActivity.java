package com.hhly.lyygame.wxapi;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.github.xmxu.cf.Config;
import com.hhly.lyygame.App;
import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.utils.ActivityUtil;
import com.hhly.lyygame.presentation.utils.SharedPrefsFavouriteUtil;
import com.hhly.lyygame.presentation.view.BaseActivity;
import com.hhly.lyygame.presentation.view.immersive.IImmersiveApply;
import com.hhly.lyygame.presentation.view.main.MainTabActivity;
import com.hhly.lyygame.presentation.view.order.PayOrderDetailsFragment;
import com.hhly.lyygame.presentation.view.paydetails.PayDetailsFragment;
import com.hhly.lyygame.presentation.view.paydetails.PayDetailsPresenter;
import com.hhly.lyygame.presentation.view.share.ShareContent;
import com.hhly.lyygame.presentation.view.shareactivity.ShareWebActivity;
import com.hhly.lyygame.presentation.view.shareactivity.ShareWebBuilder;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;


public class WXPayEntryActivity extends BaseActivity implements IWXAPIEventHandler, IImmersiveApply {

    private static final String TAG = "WXPayEntryActivity";
    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, Config.get().getWechatAppId());
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {

    }

    @Override
    public void onResp(BaseResp resp) {
        Log.d(TAG, "onPayFinish, errCode = " + resp.errCode);
        if (resp.errCode == 1) {
            jumpToView();
        } else if (resp.errCode == -2) {
//            ToastUtil.showTip(App.getContext(), "您已取消了支付");
//            Logger.d("WeChat = cancel ");
//            finish();
            jumpToView();
        } else {
            // ToastUtil.showTip(App.getContext(), "支付失败");
            jumpToView();
        }

//        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
//            OrderApi.onWxPayResult(resp.errCode);
//            onBackPressed();
//        }
    }

    void jumpToView() {
        final int type = App.type;
        if (type == 1) {
            PayOrderDetailsFragment fragment = (PayOrderDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
            if (fragment == null) {
                fragment = PayOrderDetailsFragment.newInstance();
                fragment.setArguments(getIntent().getExtras());
                ActivityUtil.addFragment(getSupportFragmentManager(), fragment, R.id.content_container);
            }
            if (mToolbarHelper != null) {
                mToolbarHelper.setTitle(getString(R.string.lyy_game_download_title));
            }
        } else {
            PayDetailsFragment fragment = (PayDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
            if (fragment == null) {
                fragment = PayDetailsFragment.newInstance();
                fragment.setArguments(getIntent().getExtras());
                ActivityUtil.addFragment(getSupportFragmentManager(), fragment, R.id.content_container);
            }
            new PayDetailsPresenter(fragment);
            if (mToolbarHelper != null) {
                mToolbarHelper.setTitle(getString(R.string.lyy_game_pay_detail_detail));
            }
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.lyy_pay_result;
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
    public void onBackPressed() {
        final int type = App.type;
        if (type == 0) {
            if (SharedPrefsFavouriteUtil.getBooleanValue(this, SharedPrefsFavouriteUtil.PAY_COINS, false)) {
                SharedPrefsFavouriteUtil.putBooleanValue(this, SharedPrefsFavouriteUtil.PAY_COINS, false);

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

                ActivityCompat.startActivity(this, ShareWebActivity.getCallingIntent(this, shareContent), null);
            } else {
                startActivity(MainTabActivity.getCallIntent(this));
            }
        } else {
            finish();
        }
    }
}