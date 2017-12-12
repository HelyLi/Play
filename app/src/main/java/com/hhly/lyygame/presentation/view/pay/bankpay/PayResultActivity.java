package com.hhly.lyygame.presentation.view.pay.bankpay;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.protocol.pay.QuickPayConfirmReq;
import com.hhly.lyygame.presentation.utils.ActivityUtil;
import com.hhly.lyygame.presentation.view.BaseActivity;
import com.hhly.lyygame.presentation.view.pay.PayActivity;

/**
 * description :
 * Created by Flynn
 * 2017/5/18
 */

public class PayResultActivity extends BaseActivity {

    private boolean isFinish;

    public static Intent getCallIntent(Context context, QuickPayConfirmReq req) {
        Intent intent = new Intent(context, PayResultActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(PayResultFragment.KEY, req);
        intent.putExtras(bundle);
        return intent;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PayResultFragment fragment = (PayResultFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (fragment == null) {
            fragment = PayResultFragment.newInstance(getIntent().getExtras());
            ActivityUtil.addFragment(getSupportFragmentManager(), fragment, R.id.content_container);
        }
    }

    public void setFinish() {
        isFinish = true;
    }

    @Override
    public void onBackPressed() {
        if (isFinish) {
            super.onBackPressed();
        } else {
            startActivity(PayActivity.getCallingIntent(this));
        }
    }


}
