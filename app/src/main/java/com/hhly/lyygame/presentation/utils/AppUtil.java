package com.hhly.lyygame.presentation.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;

import com.hhly.lyygame.BuildConfig;

import java.io.File;

import static zlc.season.rxdownload2.function.Utils.isInstalled;

/**
 * 应用程序相关工具，检查是否安装，启动某个app，启动安装、卸载等
 */

public class AppUtil {

//    private static PackageManager pm;

    /**
     * 检查某个包名的app是否已安装
     *
     * @param context
     * @param packageName 完整包名
     * @return true 已安装  false 未安装
     */
//    public synchronized static boolean   isInstalled(Context context, String packageName) {
//        if (context == null) {
//            return false;
//        }
//
//        if (pm == null){
//            pm = context.getPackageManager();
//        }
////        synchronized (AppUtil.class){
//            try {
//                PackageInfo info = pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
//                return info != null;
//            } catch (PackageManager.NameNotFoundException e) {
//                return false;
//            }
////        }
//    }

    /**
     * 通过安装包获取包名并检查是否已安装
     *
     * @param context
     * @param filePath 完整路径
     * @return true 已安装  false 未安装
     */
    public static boolean isInstalledByFile(Context context, String filePath) {
        if (context == null) {
            return false;
        }
        final PackageManager pm = context.getPackageManager();
        PackageInfo info = pm.getPackageArchiveInfo(filePath, PackageManager.GET_ACTIVITIES);
        if (info != null) {
            return isInstalled(context, info.applicationInfo.packageName);
        }
        return false;
    }

    /**
     * 获取指定安装包的包名
     *
     * @param context
     * @param filePath
     * @return
     */
    public static String getPackageName(Context context, String filePath) {
        if (context == null) {
            return null;
        }
        final PackageManager pm = context.getPackageManager();
        PackageInfo info = pm.getPackageArchiveInfo(filePath, PackageManager.GET_ACTIVITIES);
        if (info != null) {
            return info.applicationInfo.packageName;
        }
        return null;
    }

    /**
     * 启动指定包名的app
     *
     * @param context
     * @param packageName 包名
     */
    public static void startApp(Context context, String packageName) {
        if (!isInstalled(context, packageName)) {
            return;
        }
        final PackageManager pm = context.getPackageManager();
        Intent intent = pm.getLaunchIntentForPackage(packageName);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        context.startActivity(intent);
    }


    /**
     * 启动安装界面
     *
     * @param context
     * @param filePath 要安装的文件路径
     */
    public static void startInstall(Context context, String filePath) {
        startInstall(context, new File(filePath));
    }

    /**
     * 启动安装界面
     *
     * @param context
     * @param apkFile 要安装的文件
     */
    public static void startInstall(Context context, File apkFile) {
        if (context == null) {
            return;
        }
//        Uri dataUri = Uri.fromFile(apkFile);
        Intent intent = new Intent(Intent.ACTION_VIEW);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".file_provider", apkFile);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            Uri uri = Uri.fromFile(apkFile);
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }

//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.setDataAndType(dataUri, "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    /**
     * 启动卸载界面
     *
     * @param context
     * @param packageName 卸载包名
     */
    public static void startUninstall(Context context, String packageName) {
        if (context == null) {
            return;
        }
        Uri dataUri = Uri.parse("package:" + packageName);
        Intent intent = new Intent(Intent.ACTION_DELETE);
        intent.setData(dataUri);
        context.startActivity(intent);
    }

    /**
     * 获取当前版本号
     *
     * @param context
     * @return
     */
    public static String getAppVersionName(Context context, String packageName) {
        String versionName = "";
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            versionName = packageInfo.versionName;
            if (TextUtils.isEmpty(versionName)) {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionName;
    }


}
