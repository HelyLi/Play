package com.hhly.lyygame.presentation.view.pay;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

/**
 * Created by ${HELY} on 17/1/20.
 * 邮箱：heli.lixiong@gmail.com
 */

public class PlatformItemModel {

    @DrawableRes
    private int iconResId;
    @StringRes
    private int stringId;
    @DrawableRes
    private int choiceResId;

    private boolean checked;

    public PlatformItemModel(int iconResId, int stringId, int choiceResId, boolean checked) {
        this.iconResId = iconResId;
        this.stringId = stringId;
        this.choiceResId = choiceResId;
        this.checked = checked;
    }

    public int getIconResId() {
        return iconResId;
    }

    public int getStringId() {
        return stringId;
    }

    public int getChoiceResId() {
        return choiceResId;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
