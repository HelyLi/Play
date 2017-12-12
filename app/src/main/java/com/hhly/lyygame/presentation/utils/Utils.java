package com.hhly.lyygame.presentation.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;

import com.hhly.lyygame.R;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

/**
 * Created by ${HELY} on 17/2/14.
 * 邮箱：heli.lixiong@gmail.com
 */

public class Utils {

    public static String getMapValue(Map<String, String> params) {

        StringBuilder builder = new StringBuilder();

        for (Map.Entry entry : params.entrySet()) {
            String key = (String) entry.getKey();
            builder.append(key + "=");
            String value = (String) entry.getValue();
            builder.append(value + "&");
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    public static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 根据人气数获取星星
     *
     * @param popularityVal
     * @return
     */
    public static float getRatingProgress(int popularityVal) {

        float star = 0;

        if (popularityVal <= 100) {
            star = popularityVal / 100.0f;
        } else if (popularityVal <= 500) {
            star = 1 + (popularityVal - 100) / 400;
        } else if (popularityVal <= 2000) {
            star = 2 + (popularityVal - 500) / 1500;
        } else if (popularityVal <= 5000) {
            star = 3 + (popularityVal - 2000) / 3000;
        } else if (popularityVal <= 50000) {
            star = 4 + (popularityVal - 5000) / 45000;
        } else {
            star = 5;
        }
        return star;
    }

    private static final int[] APPCOMPAT_CHECK_ATTRS = {R.attr.colorPrimary};

    public static void checkAppCompatTheme(Context context) {
        TypedArray a = context.obtainStyledAttributes(APPCOMPAT_CHECK_ATTRS);
        final boolean failed = !a.hasValue(0);
        if (a != null) {
            a.recycle();
        }
        if (failed) {
            throw new IllegalArgumentException("You need to use a Theme.AppCompat theme "
                    + "(or descendant) with the design library.");
        }
    }

}
