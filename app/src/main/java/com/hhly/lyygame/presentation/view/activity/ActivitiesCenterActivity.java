package com.hhly.lyygame.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.utils.ActivityUtil;
import com.hhly.lyygame.presentation.view.BaseActivity;
import com.hhly.lyygame.presentation.view.immersive.IImmersiveApply;
import com.orhanobut.logger.Logger;

/**
 * Created by Simon on 2016/11/29.
 */

public class ActivitiesCenterActivity extends BaseActivity implements IImmersiveApply {

    public final static String ACTIVITY_ID = "extra_activity_id";

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, ActivitiesCenterActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int[] ids = getIntent().getIntArrayExtra(ACTIVITY_ID);
        Logger.d("ids=" + ids);
        ActivitiesCenterFragment fragment = (ActivitiesCenterFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (fragment == null) {
            fragment = (ids == null) ? ActivitiesCenterFragment.newInstance() : ActivitiesCenterFragment.newInstance(ids);
            ActivityUtil.addFragment(getSupportFragmentManager(), fragment, R.id.content_container);
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_activities_center_layout;
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
