package com.hhly.lyygame.service;

import android.accessibilityservice.AccessibilityService;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.hhly.lyygame.R;

import java.util.*;

/**
 * 智能安装服务类
 */
public class LyyAccessibilityService extends AccessibilityService {


    private static final String TAG = LyyAccessibilityService.class.getSimpleName();

    private final Set<String> installViewSet = new HashSet<>(Arrays.asList(new String[]{"com.android.packageinstaller.PackageInstallerActivity",
            "com.android.packageinstaller.OppoPackageInstallerActivity","com.android.packageinstaller.InstallAppProgress",
            "com.lenovo.safecenter.install.InstallerActivity","com.lenovo.safecenter.defense.install.fragment.InstallInterceptActivity",
            "com.lenovo.safecenter.install.InstallProgress","com.lenovo.safecenter.install.InstallAppProgress",
            "com.lenovo.safecenter.defense.fragment.install.InstallInterceptActivity"}));

    private final Set<String> installPkgSet = new HashSet<>(Arrays.asList(new String[]{"com.samsung.android.packageinstaller",
            "com.android.packageinstaller", "com.google.android.packageinstaller", "com.lenovo.safecenter", "com.lenovo.security"
            , "com.xiaomi.gamecenter"}));

    private final Set<String> uninstallPkgSet = new HashSet<>(Arrays.asList(new String[]{"com.android.packageinstaller.UninstallAppProgress"
            , "android.app.AlertDialog"}));

    boolean isInstallOrUninstall = true;

    private List<String> nodeContents;
    private List<String> completeTexts;
    private List<String> installTexts;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onServiceConnected() {
        nodeContents = new ArrayList<>(Arrays.asList(new String[]{
                getResources().getString(R.string.lyy_auto_service_install),
                getResources().getString(R.string.lyy_auto_service_ensure),
                getResources().getString(R.string.lyy_auto_service_next),
                getResources().getString(R.string.lyy_auto_service_exchange),
                getResources().getString(R.string.lyy_auto_service_delete),
                getResources().getString(R.string.lyy_auto_service_install__material_),
                getResources().getString(R.string.lyy_auto_service_ensure__material_),
                getResources().getString(R.string.lyy_auto_service_next__material_),
                getResources().getString(R.string.lyy_auto_service_complete__delete_),
                getResources().getString(R.string.lyy_auto_service_exchange__material_)}));

        completeTexts = new ArrayList<>(Arrays.asList(new String[]{
                getResources().getString(R.string.lyy_auto_service_complete),
                getResources().getString(R.string.lyy_auto_service_delete),
                getResources().getString(R.string.lyy_auto_service_complete__delete_),
                getResources().getString(R.string.lyy_auto_service_complete__material_)}));
        installTexts = new ArrayList<>(Arrays.asList(new String[]{
                getResources().getString(R.string.lyy_auto_service_install),
                getResources().getString(R.string.lyy_auto_service_delete),
                getResources().getString(R.string.lyy_auto_service_complete__delete_),
                getResources().getString(R.string.lyy_auto_service_install__material_)}));
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        doAccessibilityEvent(event);
    }

    private void doAccessibilityEvent(AccessibilityEvent event) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {

            String className = event.getClassName().toString();
            if (uninstallPkgSet.contains(className)) {
                isInstallOrUninstall = false;
            }

            if(installViewSet.contains(event.getClassName().toString())) {
                isInstallOrUninstall = true;
            }

            if (installViewSet.contains(event.getPackageName().toString())) {
                isInstallOrUninstall = true;
            }

            AccessibilityNodeInfo rootNodeInfo = getRootInActiveWindow();

            if (rootNodeInfo != null && isInstallOrUninstall) {
                String pkgName = (String) rootNodeInfo.getPackageName();

                if (installPkgSet.contains(pkgName)) {
                    for (int i = 0; i < nodeContents.size(); i++) {
                        List<AccessibilityNodeInfo> textNodeInfo = new ArrayList<>();
                        for (int k = 0; k < completeTexts.size(); k++) {
                            textNodeInfo.addAll(rootNodeInfo.findAccessibilityNodeInfosByText(completeTexts.get(k)));
                        }

                        if (textNodeInfo.size() > 0) {
                            for (int j = 0; j < textNodeInfo.size(); j++) {
                                String text = textNodeInfo.get(j).getText().toString();
                                if (completeTexts.contains(text)) {
                                    clickInstall(textNodeInfo.get(j));
                                }
                            }
                        }
                    }
                }
            }

            AccessibilityNodeInfo nodeInfo = event.getSource();

            if (nodeInfo != null && isInstallOrUninstall) {
                for (int i = 0; i < nodeContents.size(); i++) {
                    List<AccessibilityNodeInfo> textNodeInfo = nodeInfo.findAccessibilityNodeInfosByText(nodeContents.get(i));
                    List<AccessibilityNodeInfo> installNodeInfo = new ArrayList<>();
                    for (int k = 0; k < completeTexts.size(); k++) {
                        installNodeInfo.addAll(nodeInfo.findAccessibilityNodeInfosByText(installTexts.get(k)));
                    }

                    boolean isInstall = installNodeInfo.size() != 0;

                    if (textNodeInfo != null && textNodeInfo.size() > 0) {
                        for (int j = 0; j < textNodeInfo.size(); j++) {
                            String text = textNodeInfo.get(j).getText().toString();
                            if (nodeContents.contains(text) && isInstall) {
                                clickInstall(textNodeInfo.get(j));
                            }
                        }
                    }
                }
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void clickInstall(AccessibilityNodeInfo nodeInfo) {
        nodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }


    @Override
    public void onInterrupt() {
    }

















































    //
//    Map<Integer, Boolean> handledMap = new HashMap<>();
//    ArrayList<AccessibilityNodeInfo> mCache = new ArrayList<>();
//    private static int num = 1;
//
//    public LyyAccessibilityService() {
//    }
//
//    public static final String TAG = "LyyAccessibilityService";
//
//    public void log(String message) {
//        Log.d("LyyAccessibilityService", message);
//    }
//
//    @Override
//    protected void onServiceConnected() {
//        log("service connect success ");
//        AccessibilityServiceInfo accessibilityServiceInfo = new AccessibilityServiceInfo();
//        // accessibilityServiceInfo.packageNames = PACKAGES;
//        accessibilityServiceInfo.eventTypes = AccessibilityEvent.TYPES_ALL_MASK;
//        accessibilityServiceInfo.feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN;
//        accessibilityServiceInfo.notificationTimeout = 100;
//        setServiceInfo(accessibilityServiceInfo);
//    }
//
//
//    @Override
//    public void onAccessibilityEvent(AccessibilityEvent event) {
//
//        log("====================  " + num++ + "  ==========================");
//        int eventType = event.getEventType();
//        log("packageName:" + event.getPackageName() + "");
//        log("source:" + event.getSource());
//        log("source class: " + event.getClassName() + "");
//        log("event type(int):" + eventType + "");
//        Logger.d(mCache);
//
//        switch (eventType) {
//
//            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:
//                log("event type: TYPE_NOTIFICATION_STATE_CHANGED");
//                break;
//            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
//                log("event type: TYPE_WINDOW_STATE_CHANGED");
//                break;
//            case AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUSED:
//                log("event type: TYPE_VIEW_ACCESSIBILITY_FOCUSED");
//                break;
//            case AccessibilityEvent.TYPE_GESTURE_DETECTION_START:
//                log("event type: TYPE_GESTURE_DETECTION_START");
//                break;
//            case AccessibilityEvent.TYPE_GESTURE_DETECTION_END:
//                log("event type: TYPE_GESTURE_DETECTION_END");
//                break;
//            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
//                log("event type: TYPE_WINDOW_CONTENT_CHANGED");
//                break;
//            case AccessibilityEvent.TYPE_VIEW_CLICKED:
//                log("event type: TYPE_VIEW_CLICKED");
//                break;
//            case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED:
//                log("event type: TYPE_VIEW_TEXT_CHANGED");
//                break;
//            case AccessibilityEvent.TYPE_VIEW_SCROLLED:
//                log("event type: TYPE_VIEW_SCROLLED");
//                break;
//            case AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED:
//                log("event type: TYPE_VIEW_TEXT_SELECTION_CHANGED");
//                break;
//        }
//        for (CharSequence txt : event.getText()) {
//            log("text: " + txt);
//        }
//
//        log("===============================================================");
//
//
//        AccessibilityNodeInfo nodeInfo = event.getSource();
//        if (nodeInfo != null) {
//            //            int eventType = event.getEventType();
//            if (eventType == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED
//                    || eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED
//                    || eventType == AccessibilityEvent.TYPE_VIEW_SCROLLED
//                    ) {
//
//
//                if (handledMap.get(event.getWindowId()) == null) {
//                    boolean handled = iterateNodesAndHandle(nodeInfo);
//                    if (handled) {
//                        handledMap.put(event.getWindowId(), true);
//                    }
//                }
//
//                //====================================
//
//                if (mCache != null) {
//                    Logger.d("mCache != null");
//                    for (int i = 0; i < mCache.size(); i++) {
//                        AccessibilityNodeInfo info = mCache.get(i);
//                        if (info != null && info.getText() != null) {
//                            String content = info.getText().toString();
//                            Logger.d("mCache != null  content = " + content);
//                            if ("安装".equals(content)
//                                    || "完成".equals(content)
//                                    || "确定".equals(content)
//                                    || "继续".equals(content)
//                                    || "删除".equals(content)
//                                    || "下一步".equals(content)) {
//                                nodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
//                                return;
//                            }
//                        }
//
//                    }
//                }
//
//                //====================================
//
//            }
//        }
//
//        //        findAndPerformActionButton(event,"安装");
//        //        findAndPerformActionButton(event,"继续");
//        //        findAndPerformActionButton(event,"下一步");
//        //        findAndPerformActionButton(event,"确定");
//        //        findAndPerformActionButton(event,"删除");
//
//    }
//
//    private void findAndPerformActionButton(AccessibilityEvent event, String text) {
//        //        if (getRootInActiveWindow() == null){
//        //            return;
//        //        }
//        //        List<AccessibilityNodeInfo> nodes = getRootInActiveWindow().findAccessibilityNodeInfosByText(text);
//        if (event != null && event.getSource() != null) {
//            List<AccessibilityNodeInfo> nodes = event.getSource().findAccessibilityNodeInfosByText(text);
//            if (nodes != null && !nodes.isEmpty()) {
//                AccessibilityNodeInfo node;
//                for (int i = 0; i < nodes.size(); i++) {
//                    node = nodes.get(i);
//                    if ((node.getClassName().equals("android.widget.Button") || "android.widget.TextView".equals(node.getClassName())) && node.isEnabled()) {
//                        node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
//                    }
//                }
//            }
//        }
//    }
//
//    @TargetApi(Build.VERSION_CODES.KITKAT)
//    private boolean iterateNodesAndHandle(AccessibilityNodeInfo nodeInfo) {
//        if (nodeInfo != null) {
//            int childCount = nodeInfo.getChildCount();
//            if ("android.widget.Button".equals(nodeInfo.getClassName()) || "android.widget.TextView".equals(nodeInfo.getClassName())) {
//                if (!TextUtils.isEmpty(nodeInfo.getText())) {
//                    mCache.add(nodeInfo);
//                    String nodeContent = nodeInfo.getText().toString();
//                    //                Logger.d("content is " + nodeContent);
//                    if ("安装".equals(nodeContent)
//                            || "完成".equals(nodeContent)
//                            || "确定".equals(nodeContent)
//                            || "继续".equals(nodeContent)
//                            || "删除".equals(nodeContent)
//                            || "下一步".equals(nodeContent)) {
//                        nodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
//                        return true;
//                    }
//                    if ("应用安装完成".equals(nodeContent)){
//                        nodeInfo.setDismissable(true);
//
//                    }
//                } else if ("android.widget.ScrollView".equals(nodeInfo.getClassName())) {
//                    nodeInfo.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
//                }
//            }
//            for (int i = 0; i < childCount; i++) {
//                AccessibilityNodeInfo childNodeInfo = nodeInfo.getChild(i);
//                if (iterateNodesAndHandle(childNodeInfo)) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public void onInterrupt() {
//
//    }
//
//    //    private void onProcessAccessibilityEvent(AccessibilityEvent event) {
//    //
//    //        if (event.getSource() == null) {
//    //            return;
//    //        }
//    //        String packageName = event.getPackageName().toString();
//    //        String className = event.getClassName().toString();
//    //        String sourceText = event.getSource().getText() == null ? BuildConfig.VERSION_NAME : event.getSource().getText().toString().trim();
//    //
//    //        if (packageName.equals("com.android.packageinstaller")) {
//    //
//    //            if (isApplicationInstallEvent(event, className, sourceText)) {
//    //
//    //                // 准备安装
//    //
//    //                onApplicationInstall(event, className);
//    //
//    //            } else if (hasAccessibilityNodeInfoByText(event, getString(R.string.str_accessibility_install_blocked))) {
//    //
//    //                // 准备安装
//    //
//    //                onApplicationInstall(event, false);
//    //
//    //            } else if (isApplicationInstalledEvent(event, className, sourceText)) {
//    //
//    //                // 安装完成
//    //
//    //                onApplicationInstalled(event);
//    //
//    //            }
//    //
//    //
//    //        }
//    //    }
//    //
//    //    private boolean isApplicationInstallEvent(AccessibilityEvent event, String className, String sourceText) {
//    //
//    //        return className.equalsIgnoreCase("com.android.packageinstaller.PackageInstallerActivity")
//    //
//    //                || sourceText.contains(getString(R.string.btn_accessibility_install));
//    //
//    //    }
//    //
//    //    private void onApplicationInstall( final AccessibilityEvent event, final String nodeClassName) {
//    //
//    //        //开始安装
//    //
//    //        AccessibilityNodeInfo nodeInfo = getAccessibilityNodeInfoByText(event, nodeClassName, getString(R.string.btn_accessibility_install)); // 安装按钮
//    //
//    //        if(nodeInfo != null) {
//    //
//    //            performClick(nodeInfo);
//    //
//    //            nodeInfo.recycle();
//    //
//    //            return;
//    //
//    //        }
//    //
//    //        nodeInfo = getAccessibilityNodeInfoByText(event, nodeClassName, getString(R.string.btn_accessibility_allow_once)); // 允许按钮
//    //
//    //        if(nodeInfo != null) {
//    //
//    //            performClick(nodeInfo);
//    //
//    //            nodeInfo.recycle();
//    //
//    //            return;
//    //
//    //        }
//    //
//    //        nodeInfo = getAccessibilityNodeInfoByText(event, CLASS_NAME_WIDGET_TEXTVIEW, getString(R.string.btn_accessibility_next)); // 下一步按钮
//    //
//    //        if(nodeInfo != null) {
//    //
//    //            performClick(nodeInfo);
//    //
//    //            onApplicationInstall(event);
//    //
//    //            nodeInfo.recycle();
//    //
//    //        }
//    //
//    //
//    //    }
//    //
//    //    private AccessibilityNodeInfo getAccessibilityNodeInfoByText(AccessibilityEvent event, String className, String text) {
//    //
//    //        List<AccessibilityNodeInfo> nodes = null;
//    //
//    //        String.valueOf(event.getSource()!= null));
//    //
//    //        try{
//    //
//    //            if(event != null&& event.getSource() != null) {
//    //
//    //                nodes = event.getSource().findAccessibilityNodeInfosByText(text);
//    //
//    //            }
//    //
//    //        } catch(Exception e) {
//    //
//    //            //ignore
//    //
//    //        }
//    //
//    //        if(nodes == null|| nodes.size() == 0) {
//    //
//    //            AccessibilityNodeInfo info = getRootInActiveWindow();
//    //
//    //            if(info != null) {
//    //
//    //                nodes = info.findAccessibilityNodeInfosByText(text);
//    //
//    //            }
//    //
//    //        }
//    //
//    //        if(nodes != null&& nodes.size() > 0) {
//    //
//    //            for(AccessibilityNodeInfo nodeInfo : nodes) {
//    //
//    //                String nodeText = nodeInfo.getText() == null? BuildConfig.VERSION_NAME : nodeInfo.getText().toString();
//    //
//    //                if(nodeInfo.getClassName().equals(className) && nodeText.equalsIgnoreCase(text)) {
//    //
//    //                    return nodeInfo;
//    //
//    //                }
//    //
//    //                nodeInfo.recycle();
//    //
//    //            }
//    //
//    //        }
//    //
//    //        return null;
//    //
//    //    }
//

}
