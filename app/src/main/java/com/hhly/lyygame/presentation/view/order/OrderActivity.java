package com.hhly.lyygame.presentation.view.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.utils.ActivityUtil;
import com.hhly.lyygame.presentation.view.BaseActivity;
import com.hhly.lyygame.presentation.view.immersive.IImmersiveApply;
import com.orhanobut.logger.Logger;

/**
 * 确认订单
 * Created by Simon on 2016/12/1.
 */

public class OrderActivity extends BaseActivity implements IImmersiveApply {

    public static final String ORDER_TYPE = "extra_order_type";

    public static final String ORDER_ID = "extra_order_id";//实物
    public static final String ORDER_ID_NUM = "extra_order_id_num";//实物
    public static final String ORDER_GIFT_ID = "extra_order_gift_id";//虚拟礼物
    public static final String ORDER_GOODS_ID = "extra_order_goods_id";//goodsId 充值卡

    public static Intent getOrderCallingIntent(Context context, int[] ids, int[] num) {
        Intent intent = new Intent(context, OrderActivity.class);
        intent.putExtra(ORDER_TYPE, 0);
        intent.putExtra(ORDER_ID, ids);
        intent.putExtra(ORDER_ID_NUM, num);
        return intent;
    }

    public static Intent getOrderCardCallingIntent(Context context, int goodsId) {
        Intent intent = new Intent(context, OrderActivity.class);
        intent.putExtra(ORDER_TYPE, 1);
        intent.putExtra(ORDER_GOODS_ID, goodsId);
        return intent;
    }

    public static Intent getOrderGiftCallingIntent(Context context, int giftIds) {
        Logger.d("giftId=" + giftIds);
        Intent intent = new Intent(context, OrderActivity.class);
        intent.putExtra(ORDER_TYPE, 2);
        intent.putExtra(ORDER_GIFT_ID, giftIds);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int type = getIntent().getIntExtra(ORDER_TYPE, -1);

        switch (type) {
            case 0://实物
                ActivityUtil.addFragment(getSupportFragmentManager(),
                        OrderFragment.newInstance(getIntent().getIntArrayExtra(ORDER_ID),
                                getIntent().getIntArrayExtra(ORDER_ID_NUM)),
                        R.id.content_container);
                break;
            case 1://电话卡
                ActivityUtil.addFragment(getSupportFragmentManager(), OrderCardFragment.newInstance(getIntent().getIntExtra(ORDER_GOODS_ID, 0)), R.id.content_container);
                break;
            case 2://礼包QQ充值卡
                ActivityUtil.addFragment(getSupportFragmentManager(), OrderGiftFragment.newInstance(getIntent().getIntExtra(ORDER_GIFT_ID, 0)), R.id.content_container);
                break;
            default:
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_confirm_order_layout;
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
