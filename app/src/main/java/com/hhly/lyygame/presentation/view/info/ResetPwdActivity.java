package com.hhly.lyygame.presentation.view.info;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.utils.ActivityUtil;
import com.hhly.lyygame.presentation.view.BaseActivity;

/**
 * Created by Simon on 2016/11/26.
 */

public class ResetPwdActivity extends BaseActivity {

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, ResetPwdActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityUtil.addFragment(getSupportFragmentManager(), new ResetPwdFragment(), R.id.content_container);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_base_layout;
    }
}
