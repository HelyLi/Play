package com.hhly.lyygame.presentation.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ${HELY} on 17/2/24.
 * 邮箱：heli.lixiong@gmail.com
 */

/**
 * 本地sharepres 存储
 */
public class SharedPrefsFavouriteUtil {

    /**
     * 数据存储的XML名称
     **/
    public final static String SETTING = "SharedPrefsFavourite";

    public final static String PAY_COINS = "pay_coins";

    /**
     * 存储数据(Int)
     *
     * @param context
     * @param key
     * @param value
     */
    public static void putBooleanValue(Context context, String key, boolean value) {
        SharedPreferences.Editor sp = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE)
                .edit();
        sp.putBoolean(key, value);
        sp.commit();
    }

    /**
     * 存储数据(String)
     *
     * @param context
     * @param key
     * @param value
     */
    public static void putStringValue(Context context, String key, String value) {
        SharedPreferences.Editor sp = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE)
                .edit();
        sp.putString(key, value);
        sp.commit();
    }


    /**
     * 取出数据（int)
     *
     * @param context
     * @param key
     * @param defValue 默认值
     * @return
     */
    public static boolean getBooleanValue(Context context, String key, boolean defValue) {
        SharedPreferences sp = context.getSharedPreferences(SETTING,
                Context.MODE_PRIVATE);
        boolean value = sp.getBoolean(key, defValue);
        return value;
    }

    /**
     * 取出数据（String)
     *
     * @param context
     * @param key
     * @param defValue 默认值
     * @return
     */
    public static String getStringValue(Context context, String key,
                                         String defValue) {
        SharedPreferences sp = context.getSharedPreferences(SETTING,
                Context.MODE_PRIVATE);
        String value = sp.getString(key, defValue);
        return value;
    }


    /**
     * 清空对应key数据
     *
     * @param context
     * @param key
     */
    public static void remove(Context context, String key) {
        SharedPreferences.Editor sp = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE)
                .edit();
        sp.remove(key);
        sp.commit();
    }

    /**
     * 清空所有数据
     *
     * @param context
     */
    public static void clear(Context context) {
        SharedPreferences.Editor sp = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();
        sp.clear();
        sp.commit();
    }

}
