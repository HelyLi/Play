package com.hhly.lyygame.presentation.view;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Simon on 2016/11/15.
 */

public class ToastUtil {

    public static void showTip(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void showTip(Context context, int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }

}
