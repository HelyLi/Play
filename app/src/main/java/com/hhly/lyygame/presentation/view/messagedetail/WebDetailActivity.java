package com.hhly.lyygame.presentation.view.messagedetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.utils.ActivityUtil;
import com.hhly.lyygame.presentation.view.BaseActivity;

/**
 * 我的消息
 * Created by Simon on 2016/12/6.
 */

public class WebDetailActivity extends BaseActivity {

    public static Intent getCallingIntent(Context context, int[] webIds) {
        Intent intent = new Intent(context, WebDetailActivity.class);
        intent.putExtra(WebDetailFragment.EXTRA_WEB_ID, webIds);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int[] webIds = getIntent().getIntArrayExtra(WebDetailFragment.EXTRA_WEB_ID);//

        WebDetailFragment fragment = (WebDetailFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (fragment == null) {
            fragment = WebDetailFragment.newInstance(webIds);
            ActivityUtil.addFragment(getSupportFragmentManager(), fragment, R.id.content_container);
        }
        new WebDetailPresenter(fragment);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message_detail_layout;
    }

    @Override
    public void finish() {

        WebDetailFragment fragment = (WebDetailFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (fragment != null) {
            fragment.finish();
        }

        super.finish();
    }

}
