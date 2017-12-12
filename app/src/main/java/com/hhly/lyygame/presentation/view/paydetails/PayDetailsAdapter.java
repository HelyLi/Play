package com.hhly.lyygame.presentation.view.paydetails;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhly.lyygame.R;

import java.util.ArrayList;

/**
 * Created by ${HELY} on 17/1/18.
 * 邮箱：heli.lixiong@gmail.com
 */

public class PayDetailsAdapter extends BaseQuickAdapter<DetailItem, BaseViewHolder> {

    public PayDetailsAdapter(ArrayList<DetailItem> data) {
        super(R.layout.pay_details_item_layout, data == null ? new ArrayList<DetailItem>() : data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, DetailItem detailItem) {
        baseViewHolder.setText(R.id.item_left_tv, detailItem.getLable());
        baseViewHolder.setText(R.id.item_right_tv, detailItem.getContent());
    }
}
