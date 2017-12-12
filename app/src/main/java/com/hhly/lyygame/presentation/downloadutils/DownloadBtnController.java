package com.hhly.lyygame.presentation.downloadutils;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.widget.Toast;

import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.App;
import com.hhly.lyygame.BuildConfig;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.UserApi;
import com.hhly.lyygame.data.net.protocol.BaseResp;
import com.hhly.lyygame.data.net.protocol.user.AddRecentlyPlayedReq;
import com.hhly.lyygame.presentation.utils.AppUtil;
import com.hhly.lyygame.presentation.utils.NetworkUtil;
import com.hhly.lyygame.presentation.utils.TelephonyUtil;
import com.hhly.lyygame.presentation.view.ToastUtil;
import com.hhly.lyygame.presentation.view.download.DownloadDialog;
import com.hhly.lyygame.presentation.view.widget.DownloadProgressButton;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadBean;
import zlc.season.rxdownload2.entity.DownloadEvent;
import zlc.season.rxdownload2.entity.DownloadFlag;
import zlc.season.rxdownload2.entity.DownloadStatus;
import zlc.season.rxdownload2.function.Utils;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

/**
 * Created by ${HELY} on 17/3/22.
 * 邮箱：heli.lixiong@gmail.com
 */

public class DownloadBtnController implements DownloadCallback, DialogInterface.OnClickListener {

    private DownloadProgressButton mButton;
    private DownloadState mState = new DownloadBtnController.Normal();

    private Activity mActivity;

    private RxDownload mRxDownload;
    private int flag;

    private UserApi mUserApi;
    private DownloadItem data;

    public DownloadBtnController(DownloadProgressButton button, Activity activity) {
        this.mButton = button;
        mActivity = activity;
        mUserApi = RetrofitManager.getInstance(ApiType.USER_API).getUserApi();
        mRxDownload = DownloadHelper.get().rxDownload();
    }

    private void setState(DownloadState state) {
        mState = state;
        mState.setText(mButton);
    }

    private void setEvent(DownloadEvent event, DownloadStatus status) {
        int flag = event.getFlag();
        switch (flag) {
            case DownloadFlag.NORMAL:
//                Logger.d("NORMAL,record=" + this.data.record);
                setState(new DownloadBtnController.Normal());
                break;
            case DownloadFlag.WAITING:
                Logger.d("WAITING");
                setState(new DownloadBtnController.Waiting());
                break;
            case DownloadFlag.STARTED:
                Logger.d("STARTED");
                setState(new DownloadBtnController.Started());
                mButton.setProgress(status.getPercentNumber());
                break;
            case DownloadFlag.PAUSED:
                Logger.d("PAUSED");
                setState(new DownloadBtnController.Paused());
                break;
            case DownloadFlag.COMPLETED:
                Logger.d("COMPLETED");
                setState(new DownloadBtnController.Completed());
                break;
            case DownloadFlag.FAILED:
                Logger.d("FAILED");
                setState(new DownloadBtnController.Failed());
                break;
            case DownloadFlag.DELETED:
                Logger.d("DELETED");
                setState(new DownloadBtnController.Deleted());
                break;
            case DownloadFlag.INSTALLED:
                Logger.d("INSTALLED,record=" + this.data.record);
                setState(new DownloadBtnController.Installed());
                break;
        }
    }

    /**
     * 使用has-a 代理模式
     */
    public void handleClick(boolean two) {
        if (this.data == null || this.data.bean == null) return;
        mState.handleClick(this);

        if (this.data.disposable != null) {
            Utils.dispose(this.data.disposable);
        }

//        Logger.d("setData.out.2");

        this.data.disposable = mRxDownload.receiveDownloadStatusFlowable(this.data.bean)
                .subscribe(new Consumer<DownloadEvent>() {
                    @Override
                    public void accept(@NonNull DownloadEvent downloadEvent) throws Exception {
                        if (flag != downloadEvent.getFlag()) {
                            flag = downloadEvent.getFlag();
                        }
//                        Logger.d("hely-this.data.disposable=" + data.record);
                        setEvent(downloadEvent, downloadEvent.getDownloadStatus());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {

                    }
                });
    }

    public void setData(DownloadItem param) {
        this.data = param;
        
        this.data.bean = new DownloadBean.Builder(data.record.getUrl())
                .setApkName(data.record.getApkName())
                .setPackage(data.record.getPackageName())
                .setPicUrl(data.record.getPicUrl())
                .setSize(data.record.getSize())
                .setGameId(data.record.getGameId())
                .build();

        if (this.data.disposable != null) {
            Utils.dispose(this.data.disposable);
        }

        this.data.disposable = mRxDownload.receiveDownloadStatusFlowable(this.data.bean)
                .subscribe(new Consumer<DownloadEvent>() {
                    @Override
                    public void accept(@NonNull DownloadEvent downloadEvent) throws Exception {
                        if (flag != downloadEvent.getFlag()) {
                            flag = downloadEvent.getFlag();
                        }
                        setEvent(downloadEvent, downloadEvent.getDownloadStatus());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {

                    }
                });
    }

    public void release() {
        if (this.data != null && this.data.disposable != null)
            Utils.dispose(data.disposable);
    }

    @Override
    public void startDownload() {
        Logger.d("startDownload.1");
        if (NetworkUtil.isMobile(App.getContext())) {
            Logger.d("startDownload.2");

            DownloadDialog.showNotWifiDownloadDialog(mActivity, data.record.getSize(), this, this);
        } else if (NetworkUtil.isWifi(App.getContext())) {
            Logger.d("startDownload.3");

            new RxPermissions(App.getTopActivity())
                    .request(WRITE_EXTERNAL_STORAGE)
                    .doOnNext(new Consumer<Boolean>() {
                        @Override
                        public void accept(Boolean granted) throws Exception {
                            if (!granted) {
                                throw new RuntimeException("no permission");
                            }
                        }
                    })
                    .compose(mRxDownload.<Boolean>transformService(this.data.bean))
                    .subscribe(new Consumer<Object>() {
                        @Override
                        public void accept(Object o) throws Exception {
                            Logger.d("startDownload.4");

                        }
                    });
            Logger.d("startDownload.5");

        } else {
            ToastUtil.showTip(App.getContext(), R.string.lyy_network_notwork);
        }
    }

    @Override
    public void pauseDownload() {
        mRxDownload.pauseServiceDownload(data.record.getUrl()).subscribe();
    }

    @Override
    public void install() {
        File[] files = mRxDownload.getRealFiles(data.record.getUrl());
        if (files != null) {
            Intent intent = new Intent(Intent.ACTION_VIEW);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                Uri contentUri = FileProvider.getUriForFile(mActivity, BuildConfig.APPLICATION_ID + ".file_provider", files[0]);
                intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
            } else {
                Uri uri = Uri.fromFile(files[0]);
                intent.setDataAndType(uri, "application/vnd.android.package-archive");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            mActivity.startActivity(intent);
        } else {
            Toast.makeText(App.getContext(), "File not exists", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void open() {
        AddRecentlyPlayedReq req = new AddRecentlyPlayedReq();
        req.setGameId(data.record.getGameId());
        req.setTerminal(TelephonyUtil.getOsTypeInt());

        mUserApi.addRecentlyPlayed(req.params())
                .compose(RxUtil.<BaseResp>applySchedulers(RxUtil.IO_TRANSFORMER_BACK_PRESSURE))
                .subscribe(new Consumer<BaseResp>() {
                    @Override
                    public void accept(@NonNull BaseResp baseResp) throws Exception {
                        Logger.d("open.result=" + baseResp);
                    }
                });

        AppUtil.startApp(App.getContext(), data.record.getPackageName());
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case DialogInterface.BUTTON_NEGATIVE://土豪下载
                new RxPermissions(App.getTopActivity())
                        .request(WRITE_EXTERNAL_STORAGE)
                        .doOnNext(new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean granted) throws Exception {
                                if (!granted) {
                                    throw new RuntimeException("no permission");
                                }
                            }
                        })
                        .compose(mRxDownload.<Boolean>transformService(this.data.bean))
                        .subscribe(new Consumer<Object>() {
                            @Override
                            public void accept(Object o) throws Exception {

                            }
                        });
                break;
            case DialogInterface.BUTTON_POSITIVE://wifi下载
                this.data.record.setIsWifi(1);
                new RxPermissions(App.getTopActivity())
                        .request(WRITE_EXTERNAL_STORAGE)
                        .doOnNext(new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean granted) throws Exception {
                                if (!granted) {
                                    throw new RuntimeException("no permission");
                                }
                            }
                        })
                        .compose(mRxDownload.<Boolean>transformService(this.data.bean))
                        .subscribe(new Consumer<Object>() {
                            @Override
                            public void accept(Object o) throws Exception {

                            }
                        });
                break;
            default:
                break;
        }
    }

    static abstract class DownloadState {

        abstract void setText(DownloadProgressButton button);

        abstract void handleClick(DownloadCallback callback);
    }

    public static class Normal extends DownloadState {

        @Override
        void setText(DownloadProgressButton button) {
//            Logger.d("normalState.setText");
            button.setCurrentText("下载");
        }

        @Override
        void handleClick(DownloadCallback callback) {
            Logger.d("normalState.callback");
            callback.startDownload();
        }

    }

    public static class Waiting extends DownloadState {

        @Override
        void setText(DownloadProgressButton button) {
//            Logger.d("waiting.setText");
            button.setCurrentText("等待中");
        }

        @Override
        void handleClick(DownloadCallback callback) {
            Logger.d("waiting.callback");
            callback.pauseDownload();
        }

    }

    public static class Started extends DownloadState {

        @Override
        void setText(DownloadProgressButton button) {
//            Logger.d("started.setText");
//            button.setCurrentText("暂停");
        }

        @Override
        void handleClick(DownloadCallback callback) {
            Logger.d("started.callback");
            callback.pauseDownload();
        }

    }

    public static class Paused extends DownloadState {

        @Override
        void setText(DownloadProgressButton button) {
//            Logger.d("paused.setText");
            button.setState(DownloadProgressButton.NORMAL);
            button.setCurrentText("继续");
        }

        @Override
        void handleClick(DownloadCallback callback) {
            Logger.d("paused.callback");
            callback.startDownload();
        }
    }

    public static class Failed extends DownloadState {

        @Override
        void setText(DownloadProgressButton button) {
//            Logger.d("failed.setText");
            button.setCurrentText("继续");
        }

        @Override
        void handleClick(DownloadCallback callback) {
            Logger.d("Failed.callback");
            callback.startDownload();
        }
    }

    public static class Completed extends DownloadState {

        @Override
        void setText(DownloadProgressButton button) {
            button.setState(DownloadProgressButton.NORMAL);
            button.setCurrentText("安装");
        }

        @Override
        void handleClick(DownloadCallback callback) {
            Logger.d("Completed.callback");
            callback.install();
        }
    }

    public static class Deleted extends DownloadState {

        @Override
        void setText(DownloadProgressButton button) {
            button.setCurrentText("下载");
        }

        @Override
        void handleClick(DownloadCallback callback) {
            Logger.d("Deleted.callback");
            callback.startDownload();
        }
    }

    public static class Installed extends DownloadState {

        @Override
        void setText(DownloadProgressButton button) {
            button.setCurrentText("启动");
        }

        @Override
        void handleClick(DownloadCallback callback) {
            Logger.d("Installed.callback");
            callback.open();
        }
    }

}
