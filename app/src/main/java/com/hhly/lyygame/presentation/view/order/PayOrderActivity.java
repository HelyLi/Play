package com.hhly.lyygame.presentation.view.order;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hhly.lyygame.R;
import com.hhly.lyygame.data.model.GoodsInfo;
import com.hhly.lyygame.presentation.utils.ActivityUtil;
import com.hhly.lyygame.presentation.view.BaseActivity;
import com.hhly.lyygame.presentation.view.immersive.IImmersiveApply;
import com.orhanobut.logger.Logger;

/**
 * Created by dell on 2017/6/1.
 */

public class PayOrderActivity extends BaseActivity implements IImmersiveApply {

    public static final String GOODS_INFO = "goodsInfo";//价格
    public static final String ADDRESS_ID = "addressId";//价格
    public static final String PHONE = "phone";//价格
    public static final String PAY_TO_ACCOUNT = "payToAccount";//价格

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GoodsInfo goodsInfo = getIntent().getParcelableExtra(GOODS_INFO);
        Logger.e("PayOrderActivity goodsInfo: " + goodsInfo);
        int addressId = getIntent().getIntExtra(ADDRESS_ID, -1);
        String phone = getIntent().getStringExtra(PHONE);
        String payToAccount = getIntent().getStringExtra(PAY_TO_ACCOUNT);
        ActivityUtil.addFragment(getSupportFragmentManager(), PayOrderFragment.newInstance(goodsInfo, addressId, phone, payToAccount), R.id.content_container);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pay_order_layout;
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
}
