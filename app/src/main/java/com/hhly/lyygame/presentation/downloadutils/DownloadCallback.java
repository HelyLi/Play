package com.hhly.lyygame.presentation.downloadutils;

/**
 * Created by ${HELY} on 17/3/22.
 * 邮箱：heli.lixiong@gmail.com
 */

public interface DownloadCallback {

    void startDownload();

    void pauseDownload();

    void install();

    void open();
}
