package com.hhly.lyygame;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.support.annotation.Nullable;

import com.hhly.lyygame.data.db.manager.DaoManager;
import com.hhly.lyygame.domain.config.SystemConfig;
import com.hhly.lyygame.presentation.utils.CollectionUtil;
import com.hhly.lyygame.presentation.utils.TelephonyUtil;
import com.hhly.lyygame.presentation.utils.Utils;
import com.hhly.lyygame.reciver.NetworkConnectChangedReceiver;
import com.miguelbcr.ui.rx_paparazzo2.RxPaparazzo;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Simon on 2016/11/18.
 */

public class App extends Application {

    /**
     * 全局Context
     */
    public static Context mContext;

    /**
     *
     */
    private NetworkConnectChangedReceiver mNetworkConnectChangedReceiver;

    /**
     * 保存活动的activity，退出时统一释放
     */
    private static List<Activity> activityList = new LinkedList<Activity>();

    public static String bundessNo = "";

    public static int type = 0;

    public static String shopName;
    public static String orderNo;
    public static String shopPrice;
    public static String businessNo;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;

        //先初始化环境配置，NetModule会取api地址
        SystemConfig.get().initialize(this.getApplicationContext(), TelephonyUtil.getEnvironment(this.getApplicationContext()));
        RxPaparazzo.register(this);

        //GreenDao数据库管理初始化
        DaoManager.getInstance().init(getApplicationContext());

        //        initReceiver();
        Logger.init("TAG").logLevel(LogLevel.NONE);

        initBugly();
    }

    private void initBugly() {
        // 获取当前包名
        String packageName = mContext.getPackageName();
        // 获取当前进程名
        String processName = Utils.getProcessName(android.os.Process.myPid());
        // 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(mContext);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        // 初始化Bugly
        // CrashReport.initCrashReport(context, "注册时申请的APPID", isDebug, strategy);
        // 如果通过“AndroidManifest.xml”来配置APP信息，初始化方法如下
        CrashReport.initCrashReport(mContext, strategy);
    }

    public static Context getContext() {
        return mContext;
    }

    /**
     * 添加activity
     *
     * @param activity
     */
    public static void addActivity(Activity activity) {
        activityList.add(activity);
    }

    /**
     * 移除activity
     *
     * @param activity
     */
    public static void removeActivity(Activity activity) {
        if (activityList != null && activityList.size() > 0) {
            activityList.remove(activity);
        }
    }

    public static void finishActivity(Class<?> cls) {
        if (activityList != null) {
            Iterator iterator = activityList.iterator();
            while (iterator.hasNext()) {
                Activity activity = (Activity) iterator.next();
                if (activity != null && activity.getClass().equals(cls)) {
                    activity.finish();
                    iterator.remove();
                }
            }
        }
    }

    public static boolean isActivityRunning() {
        return activityList != null && !activityList.isEmpty();
    }

    public static boolean isStandardActivity() {
        Activity topActivity = getTopActivity();
        if (topActivity == null) {
            return false;
        }
        if (!CollectionUtil.isEmpty(activityList)) {
            if (activityList.size() >= 2) {
                Activity activity = activityList.get(activityList.size() - 2);
                if (activity == null) {
                    return false;
                }
                if (topActivity.getClass().getName().equals(activity.getClass().getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 退出应用
     */
    public static void exit() {
        for (Activity activity : activityList) {
            activity.finish();
        }
        activityList.clear();

        //      android.os.Process.killProcess(android.os.Process.myPid());
        //      System.exit(0);
    }

    public static Context getTopContext() {
        if (!CollectionUtil.isEmpty(activityList)) {
            return activityList.get(activityList.size() - 1);
        }
        return App.getContext();
    }

    @Nullable
    public static Activity getTopActivity() {
        if (!CollectionUtil.isEmpty(activityList)) {
            return activityList.get(activityList.size() - 1);
        }
        return null;
    }

    private void initReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        filter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        filter.addAction("android.net.wifi.STATE_CHANGE");
        mNetworkConnectChangedReceiver = new NetworkConnectChangedReceiver();

        registerReceiver(mNetworkConnectChangedReceiver, filter);
    }


}
