package com.hhly.lyygame.presentation.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;

import com.hhly.lyygame.BuildConfig;
import com.hhly.lyygame.service.LyyAccessibilityService;

import java.io.*;

/**
 * Created by asia on 10/12/16.
 */


public class AppInstaller extends Handler {
    private static final String TAG = AppInstaller.class.getSimpleName();
    private static volatile AppInstaller appInstaller;
    private Context context;

    public enum MODE {
        ROOT_ONLY,
        AUTO_ONLY,
        BOTH,
        NONE
    }

    private MODE mode = MODE.NONE;

    private AppInstaller(Context context) {
        this.context = context;
    }

    /**
     * 获取类实例
     *
     * @param context 上下文
     * @return
     */
    public static AppInstaller getDefault(Context context) {
        if (appInstaller == null) {
            synchronized (AppInstaller.class) {
                if (appInstaller == null) {
                    appInstaller = new AppInstaller(context);
                }
            }
        }
        return appInstaller;
    }


    public interface OnStateChangedListener {
        void onStart();

        void onComplete();

        void onNeed2OpenService();
    }

    private OnStateChangedListener mOnStateChangedListener;

    public void setOnStateChangedListener(OnStateChangedListener onStateChangedListener) {
        mOnStateChangedListener = onStateChangedListener;
    }

    /**
     * 使用root安装
     *
     * @param filePath apk路径
     * @return
     */
    private boolean installUseRoot(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return false;
        }

        if (!checkRooted()) {
            return false;
        }

        boolean result = false;
        Process process = null;
        OutputStream outputStream = null;
        BufferedReader errorStream = null;
        try {
            process = Runtime.getRuntime().exec("su");
            outputStream = process.getOutputStream();

            String command = "pm install -r " + filePath + "\n";
            outputStream.write(command.getBytes());
            outputStream.flush();
            outputStream.write("exit\n".getBytes());
            outputStream.flush();
            process.waitFor();
            errorStream = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            StringBuilder msg = new StringBuilder();
            String line;
            while ((line = errorStream.readLine()) != null) {
                msg.append(line);
            }
            Log.d(TAG, "install msg is " + msg);
            if (!msg.toString().contains("Failure")) {
                result = true;
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (errorStream != null) {
                    errorStream.close();
                }
            } catch (IOException e) {
                outputStream = null;
                errorStream = null;
                process.destroy();
            }
        }
        return result;
    }

    /**
     * 使用AccessibilityService安装
     *
     * @param filePath apk路径
     */
    private void installUseAS(String filePath) {
        if (isAccessibilitySettingsOn(context)) {
            installApp(filePath);
        } else {
            sendEmptyMessage(3);
            toAccessibilityService(context);
        }
    }

    /**
     * 安装APP
     *
     * @param filePath 文件路径
     */
    private void installApp(String filePath) {
        Intent intent = new Intent(Intent.ACTION_VIEW);

        File file = new File(filePath);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".file_provider", file);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            Uri uri = Uri.fromFile(file);
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
            // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }

//        intent.setDataAndType(Uri.fromFile(new File(filePath)), "application/vnd.android.package-archive");
//        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            try {
                context.startActivity(intent);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * 跳转到辅助功能界面
     *
     * @param context 上下文
     */
    public static void toAccessibilityService(Context context) {
        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        context.startActivity(intent);
    }

    /**
     * 判断当前AccessibilityService是否开启
     *
     * @param context 上下文
     * @return
     */
    public static boolean isAccessibilitySettingsOn(Context context) {
        int accessibilityEnabled = 0;
        final String service = context.getPackageName() + "/" + LyyAccessibilityService.class.getCanonicalName();
        try {
            accessibilityEnabled = Settings.Secure.getInt(
                    context.getApplicationContext().getContentResolver(),
                    Settings.Secure.ACCESSIBILITY_ENABLED);
        } catch (Settings.SettingNotFoundException e) {
            Log.e(TAG, "Error finding setting, default accessibility to not found: "
                    + e.getMessage());
        }
        TextUtils.SimpleStringSplitter mStringColonSplitter = new TextUtils.SimpleStringSplitter(':');

        if (accessibilityEnabled == 1) {
            String settingValue = Settings.Secure.getString(
                    context.getApplicationContext().getContentResolver(),
                    Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            if (settingValue != null) {
                mStringColonSplitter.setString(settingValue);
                while (mStringColonSplitter.hasNext()) {
                    String accessibilityService = mStringColonSplitter.next();
                    if (accessibilityService.equalsIgnoreCase(service)) {
                        return true;
                    }
                }
            }
        } else {
            Log.v(TAG, "***ACCESSIBILITY IS DISABLED***");
        }

        return false;
    }

    /**
     * 安装APP   有root权限则静默安装 , 否则有AccessibilityService权限则智能安装 , 否则普通安装
     *
     * @param filePath apk路径
     */
    public void install(final String filePath) {
        if (TextUtils.isEmpty(filePath) || !filePath.endsWith(".apk")) {
            return;
        }

        boolean accessibility = isAccessibilitySettingsOn(context);
        boolean root = checkRooted();
        //        boolean root = false;
        if (accessibility && root) {
            mode = MODE.BOTH;
        } else if (accessibility) {
            mode = MODE.AUTO_ONLY;
        } else if (root) {
            mode = MODE.ROOT_ONLY;
        } else {
            mode = MODE.NONE;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {

                sendEmptyMessage(1);

                switch (mode) {
                    case BOTH:
                        if (!checkRooted() || !installUseRoot(filePath))
                            installUseAS(filePath);
                        break;
                    case ROOT_ONLY:
                        boolean res = installUseRoot(filePath);
                        if (!res) {
                            installApp(filePath);
                        }
                        break;
                    case AUTO_ONLY:
                        installUseAS(filePath);
                        break;
                    case NONE:
                        installApp(filePath);
                        break;
                }
                sendEmptyMessage(0);

            }
        }).start();
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what) {
            case 0:
                if (mOnStateChangedListener != null)
                    mOnStateChangedListener.onComplete();
                break;
            case 1:
                if (mOnStateChangedListener != null)
                    mOnStateChangedListener.onStart();
                break;

            case 3:
                if (mOnStateChangedListener != null)
                    mOnStateChangedListener.onNeed2OpenService();
                break;
        }
    }

    /**
     * 安装APP   有root权限则静默安装 , 否则有AccessibilityService权限则智能安装 , 否则普通安装
     *
     * @param file apk文件
     */
    public void install(File file) {
        if (file == null)
            throw new IllegalArgumentException("file is null");
        install(file.getAbsolutePath());
    }

    public static boolean checkRooted() {
        boolean result = false;
        try {
            result = new File("/system/bin/su").exists() || new File("/system/xbin/su").exists();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
