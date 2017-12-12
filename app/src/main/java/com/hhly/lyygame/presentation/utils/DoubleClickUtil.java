package com.hhly.lyygame.presentation.utils;

/**
 * Created by dell on 2017/3/17.
 */

public class DoubleClickUtil {

    public static final int MIN_CLICK_DELAY_TIME = 1000;
    private static long lastClickTime = 0;

    /**
     *
     * @return
     */
    public static boolean isFastClick() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            return false;
        } else {
            return true;
        }
    }
}
