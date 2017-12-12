package com.hhly.lyygame.presentation.view.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.utils.ActivityUtil;
import com.hhly.lyygame.presentation.view.BaseActivity;
import com.hhly.lyygame.presentation.view.main.MainTabActivity;

/**
 * Created by dell on 2017/6/1.
 */

public class PayOrderDetailsActivity extends BaseActivity {

    private final static String TRANSACTION_ID = "transaction_id";

    public static Intent getPayOrderDetailsIntent(Context context, String type, String bussinessNo) {
        Intent intent = new Intent(context, PayOrderDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        bundle.putString(TRANSACTION_ID, bussinessNo);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PayOrderDetailsFragment fragment = (PayOrderDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (fragment == null) {
            fragment = PayOrderDetailsFragment.newInstance();
            fragment.setArguments(getIntent().getExtras());
            ActivityUtil.addFragment(getSupportFragmentManager(), fragment, R.id.content_container);
        }

        // new PayOrderDetailsPresenter(fragment);
    }

//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_pay_details_layout;
//    }

//    @Override
//    public boolean applyImmersive() {
//        return true;
//    }
//
//    @Override
//    public boolean applyScroll() {
//        return false;
//    }
//
//    @Override
//    public float initAlpha() {
//        return 1.0f;
//    }

    @Override
    public void onBackPressed() {
        startActivity(MainTabActivity.getCallIntent(this));
    }
}
