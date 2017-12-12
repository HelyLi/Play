package com.hhly.lyygame.presentation.view.account;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.utils.ActivityUtil;
import com.hhly.lyygame.presentation.view.BaseActivity;
import com.orhanobut.logger.Logger;

/**
 * Created by Simon on 2016/11/24.
 */

public class LoginActivity extends BaseActivity {

    public static final String EXTRA_TYPE = "extra_type";
    public static final int TYPE_NORMAL = 0;//正常进入app登录
    public static final int TYPE_REQUEST = 1;//已经在浏览时,需要登录

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        boolean isNeedStartMain = true;
        int type = 0;
        if (intent != null) {
            type = intent.getIntExtra(EXTRA_TYPE, 0);
        }
        if (type == TYPE_REQUEST) {
            isNeedStartMain = false;
        }

        Logger.d("TYPE=" + type);

        LoginFragment fragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (fragment == null) {
            fragment = LoginFragment.newInstance(isNeedStartMain);
            ActivityUtil.addFragment(getSupportFragmentManager(), fragment, R.id.content_container);
        }

        new LoginPresenter(fragment);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_base_layout;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LoginFragment fragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LoginFragment fragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (fragment != null) {
            fragment.onNewIntent(intent);
        }
    }

}
