package com.hhly.lyygame.presentation.view.notification;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.utils.ActivityUtil;
import com.hhly.lyygame.presentation.view.BaseActivity;
import com.hhly.lyygame.presentation.view.immersive.IImmersiveApply;

/**
 * Created by Simon on 2016/11/30.
 */

public class NotificationActivity extends BaseActivity implements IImmersiveApply {

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, NotificationActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NotificationFragment fragment = (NotificationFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (fragment == null) {
            fragment = NotificationFragment.newInstance();
            ActivityUtil.addFragment(getSupportFragmentManager(), fragment, R.id.content_container);
        }

        new NotificationPresenter(fragment);

//        ActivityUtil.addFragment(getSupportFragmentManager(), NotificationFragment.newInstance(), R.id.content_container);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_notification_layout;
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
}
