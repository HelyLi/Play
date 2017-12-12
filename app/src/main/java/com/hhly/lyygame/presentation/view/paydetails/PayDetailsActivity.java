package com.hhly.lyygame.presentation.view.paydetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.utils.ActivityUtil;
import com.hhly.lyygame.presentation.utils.SharedPrefsFavouriteUtil;
import com.hhly.lyygame.presentation.view.BaseActivity;
import com.hhly.lyygame.presentation.view.immersive.IImmersiveApply;
import com.hhly.lyygame.presentation.view.main.MainTabActivity;
import com.hhly.lyygame.presentation.view.share.ShareContent;
import com.hhly.lyygame.presentation.view.shareactivity.ShareWebActivity;
import com.hhly.lyygame.presentation.view.shareactivity.ShareWebBuilder;

/**
 * Created by ${HELY} on 17/1/18.
 * 邮箱：heli.lixiong@gmail.com
 */

public class PayDetailsActivity extends BaseActivity implements IImmersiveApply {

    private final static String RECHARGE_TYPE = "recharge_type";
    private final static String RECHARGE_COUNT = "recharge_count";
    private final static String TIMESTAMP = "timestamp";
    private final static String TRANSACTION_ID = "transaction_id";


    public static Intent getPayDetailsIntent(Context context) {
        return new Intent(context, PayDetailsActivity.class);
    }

    public static Intent getPayDetailsIntent(Context context, String type, String bussinessNo) {
        Intent intent = new Intent(context, PayDetailsActivity.class);
        Bundle bundle = new Bundle();
        //            bundle.putString(RECHARGE_TYPE, strs[0]);
        bundle.putString("type", type);
        bundle.putString(TRANSACTION_ID, bussinessNo);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PayDetailsFragment fragment = (PayDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (savedInstanceState == null) {
            fragment = PayDetailsFragment.newInstance();
            fragment.setArguments(getIntent().getExtras());
            ActivityUtil.addFragment(getSupportFragmentManager(), fragment, R.id.content_container);
        }
        new PayDetailsPresenter(fragment);
        if (mToolbarHelper != null) {
            mToolbarHelper.setTitle(getString(R.string.lyy_game_pay_detail_detail));
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pay_details_layout;
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

        if(SharedPrefsFavouriteUtil.getBooleanValue(this, SharedPrefsFavouriteUtil.PAY_COINS, false)){
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
        }else {
            startActivity(MainTabActivity.getCallIntent(this));
        }
    }
}
