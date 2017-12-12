package com.hhly.lyygame.presentation.view.home;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

/**
 * Created by ${HELY} on 17/1/10.
 * 邮箱：heli.lixiong@gmail.com
 */

public class HomeSelectItem {

    @DrawableRes
    private int srcIconId;
    @StringRes
    private int cnstringId;

    public HomeSelectItem(@DrawableRes int srcIconId, @StringRes int cnstringId) {
        this.srcIconId = srcIconId;
        this.cnstringId = cnstringId;
    }

    public int getSrcIconId() {
        return srcIconId;
    }

    public int getCnstringId() {
        return cnstringId;
    }

}
