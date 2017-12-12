package com.hhly.lyygame.presentation.utils;

import android.content.Context;
import android.text.TextUtils;

import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;

/**
 * 对友盟统计进行封装,方便后期更换
 * <p>
 */

public class StatisticalUtil {


    /**
     * @param isOpenActivityDurationTrack false禁止默认的页面统计方式，这样将不会再自动统计Activity
     * @param isCatchException            捕获程序崩溃日志 false关闭
     * @param enable                      设置是否对日志信息进行加密, 默认false(不加密)
     */
    public static void init(Boolean isOpenActivityDurationTrack, boolean isCatchException, boolean enable) {
        MobclickAgent.openActivityDurationTrack(isOpenActivityDurationTrack);
        MobclickAgent.setCatchUncaughtExceptions(isCatchException);
        MobclickAgent.enableEncrypt(enable);
        //是否开启集成测试
        //        MobclickAgent.setDebugMode( true );
    }

    /**
     * session的统计
     * 在Activity的onResume里调用
     * <p>
     * 如果您的Activity之间有继承或者控制关系请不要同时在父和子Activity中重复添加onPause和onResume方法，
     * 否则会造成重复统计，导致启动次数异常增高。(eg.使用TabHost、TabActivity、ActivityGroup时)。
     * 当应用在后台运行超过30秒（默认）再回到前端，将被认为是两个独立的session(启动)，
     * 例如用户回到home，或进入其他程序，经过一段时间后再返回之前的应用。
     * 可通过接口：MobclickAgent.setSessionContinueMillis(long interval) 来自定义这个间隔（参数单位为毫秒）。
     * 如果开发者调用Process.kill或者System.exit之类的方法杀死进程，
     * 请务必在此之前调用MobclickAgent.onKillProcess(Context context)方法，用来保存统计数据。
     *
     * @param context 上下文 指当前Activity 传this
     */
    public static void onResume(Context context) {
        MobclickAgent.onResume(context);
    }

    /**
     * session的统计
     * 在Activity的onPause里调用
     *
     * @param context 上下文 指当前Activity 传this
     */
    public static void onPause(Context context) {
        MobclickAgent.onPause(context);
    }


    /**
     * @param userID 用户账号ID，长度小于64字节
     */
    public static void onProfileSignIn(String userID) {
        MobclickAgent.onProfileSignIn(userID);
    }

    /**
     * @param provider 账号来源。如果用户通过第三方账号登陆，可以调用此接口进行统计。
     *                 支持自定义，不能以下划线"_"开头，使用大写字母和数字标识，长度小于32 字节;
     *                 如果是上市公司，建议使用股票代码。
     * @param userID   用户账号ID，长度小于64字节
     */
    public static void onProfileSignIn(String provider, String userID) {
        if (TextUtils.isEmpty(provider)) {
            provider = "3";
        }
        switch (provider) {
            case "0": //表示QQ
                provider = "QQ";
                break;
            case "1": //表示微信
                provider = "WECHAT";
                break;
            case "2"://表示微博
                provider = "WEIBO";
                break;
            case "3": //表示自己应用
                provider = "SELF";
        }
        MobclickAgent.onProfileSignIn(provider, userID);
    }

    /**
     * 在用户帐号退出时调用
     */
    public static void onProfileSignOff() {
        MobclickAgent.onProfileSignOff();
    }

    /**
     * @param pageName 页面名称，可自定义
     */
    public static void onPageStart(String pageName) {
        MobclickAgent.onPageStart(pageName);
    }

    /**
     * @param pageName 页面名称，可自定义
     */
    public static void onPageEnd(String pageName) {
        MobclickAgent.onPageEnd(pageName);
    }

    /**
     * @param context 指当前的Activity
     * @param eventId 为当前统计的事件ID
     */
    public static void onEvent(Context context, String eventId) {
        MobclickAgent.onEvent(context, eventId);
    }

    /**
     * @param context 指当前的Activity
     * @param eventId 为当前统计的事件ID
     * @param map     map 为当前事件的属性和取值（Key-Value键值对）.
     *                <p>
     *                eg:统计电商应用中“购买”事件发生的次数,以及购买的商品类型及数量,那么在购买的函数里调用:
     *                HashMap<String,String> map = new HashMap<String,String>();
     *                map.put("type","book");
     *                map.put("quantity","3");
     *                MobclickAgent.onEvent(mContext, "purchase", map);
     */
    public static void onEvent(Context context, String eventId, HashMap<String, String> map) {
        MobclickAgent.onEvent(context, eventId, map);
    }

}
