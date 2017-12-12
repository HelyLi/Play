package com.hhly.lyygame.presentation.view.pay.bankpay;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.protocol.pay.QuickPayConfirmReq;
import com.hhly.lyygame.presentation.utils.ActivityUtil;
import com.hhly.lyygame.presentation.view.BaseActivity;

/**
 * description :
 * Created by Flynn
 * 2017/5/13
 */

public class SmsValidateActivity extends BaseActivity {


    /**
     * @param context
     * @param phone
     * @param tn
     * @return
     */
    public static Intent getCallingIntent(Context context, String phone, String tn, QuickPayConfirmReq req) {
        Intent intent = new Intent(context, SmsValidateActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(SmsValidateFragment.KEY_PHONE, phone);
        bundle.putString(SmsValidateFragment.KEY_TN, tn);
        bundle.putSerializable(SmsValidateFragment.KEY_REQ, req);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUtil.addFragment(getSupportFragmentManager(), SmsValidateFragment.getInstance(getIntent().getExtras()), R.id.content_container);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_base_layout;
    }

}
