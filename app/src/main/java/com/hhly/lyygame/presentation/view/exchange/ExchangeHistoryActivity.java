package com.hhly.lyygame.presentation.view.exchange;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.utils.ActivityUtil;
import com.hhly.lyygame.presentation.view.BaseActivity;

/**
 * 兑换记录
 * Created by Simon on 2016/11/29.
 */

public class ExchangeHistoryActivity extends BaseActivity {

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, ExchangeHistoryActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ExchangeHistoryFragment fragment = (ExchangeHistoryFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (fragment == null) {
            fragment = ExchangeHistoryFragment.newInstance();
            ActivityUtil.addFragment(getSupportFragmentManager(), fragment, R.id.content_container);
        }
        new ExchangeHistoryPresenter(fragment);
    }
}
