package com.hhly.lyygame.presentation.view.gamehall.model;

/**
 * Created by Simon on 2016/11/25.
 */

public class CategoryItem {

    private int mIconResId;
    private int mTitleResId;
    private int mType;

    public CategoryItem(int iconResId, int titleResId, int type) {
        mIconResId = iconResId;
        mTitleResId = titleResId;
        mType = type;
    }

    public int getIconResId() {
        return mIconResId;
    }

    public int getTitleResId() {
        return mTitleResId;
    }

    public int getType() {
        return mType;
    }
}
