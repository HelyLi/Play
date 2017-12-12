package com.hhly.lyygame.presentation.view.pay;

import android.support.v7.widget.AppCompatCheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhly.lyygame.R;

import java.util.ArrayList;

/**
 * Created by ${HELY} on 17/1/21.
 * 邮箱：heli.lixiong@gmail.com
 */

public class PlatformItemAdapter extends BaseQuickAdapter<PlatformItemModel, BaseViewHolder> {

    public PlatformItemAdapter() {
        super(R.layout.lyy_pay_platform_item_layout, new ArrayList<PlatformItemModel>());
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, PlatformItemModel platformItemModel) {



        baseViewHolder.setImageResource(R.id.item_icon_iv, platformItemModel.getIconResId());
        baseViewHolder.setText(R.id.item_tv, platformItemModel.getStringId());

        AppCompatCheckBox checkBox = (AppCompatCheckBox) baseViewHolder.itemView.findViewById(R.id.favourite_cb);
        checkBox.setChecked(platformItemModel.isChecked());

    }
}
