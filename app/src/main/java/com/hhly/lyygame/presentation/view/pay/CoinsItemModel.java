package com.hhly.lyygame.presentation.view.pay;

import android.support.annotation.DrawableRes;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by ${HELY} on 17/1/20.
 * 邮箱：heli.lixiong@gmail.com
 */

public class CoinsItemModel implements MultiItemEntity ,CoinsItemAdapter.BasePayAmountItem{

    @DrawableRes
    private int iconResId;

    private int rmb;

    private int lyb;

    private int index;

    private boolean checked;

    public CoinsItemModel(int iconResId, int rmb, int lyb, int index, boolean checked) {
        this.iconResId = iconResId;
        this.rmb = rmb;
        this.lyb = lyb;
        this.index = index;
        this.checked = checked;
    }

    public int getIconResId() {
        return iconResId;
    }

    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }

    public int getRmb() {
        return rmb;
    }

    public void setRmb(int rmb) {
        this.rmb = rmb;
    }

    public int getLyb() {
        return lyb;
    }

    public void setLyb(int lyb) {
        this.lyb = lyb;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public int getItemType() {
        return CoinsItemAdapter.TYPE_DEFAULT;
    }
}
