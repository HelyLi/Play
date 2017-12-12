package com.hhly.lyygame.presentation.view.pay.bankpay;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.protocol.pay.QuickPayApplyReq;
import com.hhly.lyygame.presentation.utils.ActivityUtil;
import com.hhly.lyygame.presentation.view.BaseActivity;

/**
 * 验证银行卡信息
 * Created by dell on 2017/5/13.
 */

public class VerifyBankCardInfoActivity extends BaseActivity {
    public static final String INFO_EXTRA_KEY="info_extra_key";
    public static Intent getCallIntent(Context context, QuickPayApplyReq req) {
        Intent intent = new Intent(context, VerifyBankCardInfoActivity.class);
        intent.putExtra(INFO_EXTRA_KEY,req);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        VerifyBankCardInfoFragment fragment = (VerifyBankCardInfoFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (fragment == null) {
            fragment = VerifyBankCardInfoFragment.newInstance((QuickPayApplyReq) getIntent().getSerializableExtra(INFO_EXTRA_KEY));
            ActivityUtil.addFragment(getSupportFragmentManager(), fragment, R.id.content_container);
        }
    }
}
