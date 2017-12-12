package com.hhly.lyygame.presentation.view.pay;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.utils.ActivityUtil;
import com.hhly.lyygame.presentation.utils.SharedPrefsFavouriteUtil;
import com.hhly.lyygame.presentation.view.BaseActivity;
import com.hhly.lyygame.presentation.view.immersive.IImmersiveApply;
import com.hhly.lyygame.presentation.view.main.MainTabActivity;

/**
 * Created by ${HELY} on 17/1/18.
 * 邮箱：heli.lixiong@gmail.com
 */

public class PayActivity extends BaseActivity implements IImmersiveApply {

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, PayActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PayFragment payFragment = (PayFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (payFragment == null) {
            payFragment = PayFragment.getInstance();
            ActivityUtil.addFragment(getSupportFragmentManager(), payFragment, R.id.content_container);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pay_layout;
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            startActivity(MainTabActivity.getCallIntent(this, 2));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (SharedPrefsFavouriteUtil.getBooleanValue(this, SharedPrefsFavouriteUtil.PAY_COINS, false)){
            SharedPrefsFavouriteUtil.putBooleanValue(this, SharedPrefsFavouriteUtil.PAY_COINS, false);
        }else {
            startActivity(MainTabActivity.getCallIntent(this, 2));
        }
        super.onBackPressed();
    }

}
