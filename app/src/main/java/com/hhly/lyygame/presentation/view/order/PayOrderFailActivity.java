package com.hhly.lyygame.presentation.view.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.view.BaseActivity;
import com.hhly.lyygame.presentation.view.contact.ContactActivity;
import com.hhly.lyygame.presentation.view.immersive.IImmersiveApply;
import com.hhly.lyygame.presentation.view.main.MainTabActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by dell on 2017/6/1.
 */

public class PayOrderFailActivity extends BaseActivity implements IImmersiveApply {
    private final static String TRANSACTION_ID = "transaction_id";
    @BindView(R.id.detail_state_iv)
    ImageView mDetailStateIv;
    @BindView(R.id.detail_tv)
    TextView mDetailTv;

    public static Intent getPayOrderFailDetailsIntent(Context context, String type, String bussinessNo) {
        Intent intent = new Intent(context, PayOrderFailActivity.class);
        Bundle bundle = new Bundle();
        //            bundle.putString(RECHARGE_TYPE, strs[0]);
        bundle.putString("type", type);
        bundle.putString(TRANSACTION_ID, bussinessNo);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mToolbarHelper != null) {
            mToolbarHelper.setTitle(getString(R.string.lyy_game_pay_order_detail_detail));
        }

        mDetailStateIv.setImageResource(R.drawable.transfer_failure);
        mDetailTv.setText(getString(R.string.lyy_game_order_fail));
    }

    @OnClick({R.id.btn_back, R.id.btn_agent})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                //startActivity(MainTabActivity.getCallIntent(this, 0));
                startActivity(ContactActivity.getCallingIntent(this));
                // finish();
                break;
            case R.id.btn_agent:
                startActivity(MainTabActivity.getCallIntent(this, 1));
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pay_order_fail_details_layout;
    }

    @Override
    public boolean applyImmersive() {
        return true;
    }

    @Override
    public boolean applyScroll() {
        return false;
    }

    @Override
    public float initAlpha() {
        return 1.0f;
    }

    @Override
    public void onBackPressed() {
        startActivity(MainTabActivity.getCallIntent(this));
    }
}
