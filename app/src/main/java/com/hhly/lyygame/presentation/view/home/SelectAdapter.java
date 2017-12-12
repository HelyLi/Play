package com.hhly.lyygame.presentation.view.home;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhly.lyygame.R;

import java.util.ArrayList;

/**
 * Created by ${HELY} on 17/1/10.
 * 邮箱：heli.lixiong@gmail.com
 */

public class SelectAdapter extends BaseQuickAdapter<HomeSelectItem, BaseViewHolder> {

    public SelectAdapter() {
        super(R.layout.lyy_home1_1_select_item_layout, new ArrayList<HomeSelectItem>());
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, HomeSelectItem homeSelectItem) {

        baseViewHolder.addOnClickListener(R.id.select_item_ll);

        baseViewHolder.setText(R.id.select_item_icon_tv, homeSelectItem.getCnstringId());
        baseViewHolder.setImageResource(R.id.select_item_icon_iv, homeSelectItem.getSrcIconId());
    }

}
