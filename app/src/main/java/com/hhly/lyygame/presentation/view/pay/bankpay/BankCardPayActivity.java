package com.hhly.lyygame.presentation.view.pay.bankpay;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.utils.ActivityUtil;
import com.hhly.lyygame.presentation.view.BaseActivity;

/**
 * 银行卡支付
 * Created by dell on 2017/5/13.
 */

public class BankCardPayActivity extends BaseActivity {
    public static final String AMOUNT_EXTRA_KEY = "amount_extra_key";

    public static Intent getCallIntent(Context context, int amount) {
        Intent intent = new Intent(context, BankCardPayActivity.class);
        intent.putExtra(AMOUNT_EXTRA_KEY, amount);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BankCardPayFragment bankCardPayFragment = (BankCardPayFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (bankCardPayFragment == null) {
            bankCardPayFragment = BankCardPayFragment.newInstance(getIntent().getIntExtra(AMOUNT_EXTRA_KEY, 0));
            ActivityUtil.addFragment(getSupportFragmentManager(), bankCardPayFragment, R.id.content_container);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        BankCardPayFragment bankCardPayFragment = (BankCardPayFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (bankCardPayFragment != null) {
            bankCardPayFragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}
