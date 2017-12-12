package com.hhly.lyygame.presentation.view.pay.bankpay;

import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import com.hhly.lyygame.presentation.view.gamehall.category.GameCategory;

/**
 * description :
 * Created by Flynn
 * 2017/5/13
 */

public class BankItem {

    /**
     * 银行图标
     */
    @DrawableRes
    private int iconIdRes;
    /**
     * 银行名
     */
    @StringRes
    private int bankNameIdRes;
    /**
     * 银行描述
     */
    @StringRes
    private int bankDescRes;

    private int bankValue;

    public BankItem(@DrawableRes int iconIdRes, @StringRes int bankNameIdRes, @StringRes int bankDescRes, int bankValue) {
        this.iconIdRes = iconIdRes;
        this.bankNameIdRes = bankNameIdRes;
        this.bankDescRes = bankDescRes;
        this.bankValue = bankValue;
    }

    public int getIconIdRes() {
        return iconIdRes;
    }

    public void setIconIdRes(int iconIdRes) {
        this.iconIdRes = iconIdRes;
    }

    public int getBankNameIdRes() {
        return bankNameIdRes;
    }

    public void setBankNameIdRes(int bankNameIdRes) {
        this.bankNameIdRes = bankNameIdRes;
    }

    public int getBankDescRes() {
        return bankDescRes;
    }

    public void setBankDescRes(int bankDescRes) {
        this.bankDescRes = bankDescRes;
    }

    public int getBankValue() {
        return bankValue;
    }

    public void setBankValue(int bankValue) {
        this.bankValue = bankValue;
    }
}
