package com.hhly.lyygame.presentation.view.webpay;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.utils.ActivityUtil;
import com.hhly.lyygame.presentation.view.BaseActivity;

/**
 * Created by dell on 2017/6/1.
 */

public class WebPayActivity extends BaseActivity {

    public static final String PAY_URL = "pay_url";
    public static final String PAY_TITLE = "pay_title";

    public static Intent getCallingIntent(Context context, String url, String title) {
        Intent intent = new Intent(context, WebPayActivity.class);
        intent.putExtra(PAY_URL, url);
        intent.putExtra(PAY_TITLE, title);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        WebPayFragment fragment = (WebPayFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (fragment == null) {
            fragment = WebPayFragment.newInstance(intent.getStringExtra(PAY_URL), intent.getStringExtra(PAY_TITLE));
            ActivityUtil.addFragment(getSupportFragmentManager(), fragment, R.id.content_container);
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_base_layout;
    }

}
