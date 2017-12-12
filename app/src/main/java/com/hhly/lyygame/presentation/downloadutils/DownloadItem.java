package com.hhly.lyygame.presentation.downloadutils;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import io.reactivex.disposables.Disposable;
import zlc.season.rxdownload2.entity.DownloadBean;
import zlc.season.rxdownload2.entity.DownloadRecord;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/10/28
 * Time: 09:30
 * FIXME
 */
public class DownloadItem {

    public Disposable disposable;
    public DownloadRecord record;

    @DrawableRes
    public int resGameTypeId;//游戏类型的图标
    public int popularitVal;//
    @NonNull
//    public String packageSize;
    public String versionCode;
    @StringRes
    public int resGameType;//
    public DownloadBean bean;

    public String type;

}
