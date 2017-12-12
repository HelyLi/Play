package com.hhly.lyygame.presentation.view.gamehall;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.view.gamehall.model.CategoryItem;

import java.util.ArrayList;

/**
 * Created by Simon on 2016/11/25.
 */

public class GameHallCategoryAdapter extends BaseQuickAdapter<CategoryItem, BaseViewHolder> {

    public GameHallCategoryAdapter() {
        super(R.layout.fragment_game_hall_category_item_layout, new ArrayList<CategoryItem>());
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, CategoryItem categoryItem) {
        baseViewHolder.setImageResource(R.id.item_category_iv, categoryItem.getIconResId());
        baseViewHolder.setText(R.id.item_category_name_tv, categoryItem.getTitleResId());
    }
}
