package com.hhly.lyygame.presentation.view.update;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.support.v4.content.FileProvider;

import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.App;
import com.hhly.lyygame.BuildConfig;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.UpdateApi;
import com.hhly.lyygame.data.net.client.BaseSubscriber;
import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.update.CheckUpdateReq;
import com.hhly.lyygame.data.net.protocol.update.CheckUpdateResp;
import com.hhly.lyygame.presentation.utils.TelephonyUtil;
import com.hhly.lyygame.presentation.view.ToastUtil;
import com.hhly.lyygame.presentation.view.update.download.DownloadUtil;
import com.hhly.lyygame.presentation.view.update.download.ProgressListener;
import com.orhanobut.logger.Logger;
import com.sina.weibo.sdk.utils.LogUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class VersionUpdateService extends Service {
    private static final String TAG = VersionUpdateService.class.getSimpleName();
    private LocalBinder binder = new LocalBinder();

    private DownLoadListener downLoadListener;
    private boolean downLoading;

    public VersionUpdateService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.d(TAG, "onCreate called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.d(TAG, "onDestroy called");
        setDownLoadListener(null);
        setCheckVersionCallBack(null);
        stopDownLoadForeground();
        downLoading = false;
    }

    public interface DownLoadListener {
        void begin(VersionUpdateModel model);

        void inProgress(int progress, int total);

        void downLoadLatestSuccess(File file);

        void downLoadLatestFailed();
    }

    public interface CheckVersionCallBack {
        void onSuccess(VersionUpdateModel model);

        void onError();
    }

    private CheckVersionCallBack checkVersionCallBack;

    public void setCheckVersionCallBack(CheckVersionCallBack checkVersionCallBack) {
        this.checkVersionCallBack = checkVersionCallBack;
    }

    public boolean isDownLoading() {
        return downLoading;
    }

    public void setDownLoading(boolean downLoading) {
        this.downLoading = downLoading;
    }


    private void stopDownLoadForeground() {
        stopForeground(true);
    }

    private UpdateApi mUpdateApi;

    public void doCheckUpdateTask(final boolean flag) {

        if (mUpdateApi == null) {
            mUpdateApi = RetrofitManager.getInstance(ApiType.UPDATE_API).getUpdateApi();
        }

        final CheckUpdateReq req = new CheckUpdateReq();
        req.setAppId(TelephonyUtil.getAppId());
        req.setOsType(TelephonyUtil.getOsType());
        req.setVersionCode(String.valueOf(TelephonyUtil.getAppVersionCode(App.getContext())));

        mUpdateApi.getUpdateInfo(req.params())
                .compose(RxUtil.<CheckUpdateResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                //.compose(RetrofitManager.<CheckUpdateResp>composeBackpressureError())
                .subscribe(new BaseSubscriber<CheckUpdateResp>() {
                    @Override
                    protected void hideDialog() {

                    }

                    @Override
                    protected void showDialog() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        if (checkVersionCallBack != null) {
                            checkVersionCallBack.onError();
                        }
                    }

                    @Override
                    public void onNext(CheckUpdateResp resp) {
                        VersionUpdateModel versionUpdateModel = new VersionUpdateModel();
                        if (resp.isOk() && resp.getData() != null) {
                            Logger.d("data=" + resp.getData());
                            versionUpdateModel.setTitle(resp.getData().getTitle());
                            versionUpdateModel.setDescription(resp.getData().getContent());
                            versionUpdateModel.setDownloadUrl(resp.getData().getUrl());
                            //强制升级
                            versionUpdateModel.setMustUpgrade(resp.getData().getUpdateType() == 1);
                            versionUpdateModel.setNeedUpgrade(true);
                        } else {
                            if (flag) {
                                ToastUtil.showTip(VersionUpdateService.this, "暂无新版本");
                            }
                            versionUpdateModel.setNeedUpgrade(false);
                        }
                        if (checkVersionCallBack != null) {
                            checkVersionCallBack.onSuccess(versionUpdateModel);
                        }
                    }
                });
    }

    private File outputFile;

    public void doDownLoadTask(VersionUpdateModel model) {
        if (model==null)return;
        final String apkUrl=model.getDownloadUrl();
        final String fileName_ = apkUrl.substring(apkUrl.lastIndexOf("/") + 1);
        final String fileName = fileName_ + ".apk";

        downLoading = true;

        if (downLoadListener != null) {
            downLoadListener.begin(model);
        }

        outputFile = new File(Environment.getExternalStoragePublicDirectory
                (Environment.DIRECTORY_DOWNLOADS), fileName);

        if (outputFile.exists()) {
            outputFile.delete();
        }

        new RxPermissions(App.getTopActivity())
                .request(WRITE_EXTERNAL_STORAGE)
                .doOnNext(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean granted) throws Exception {
                        if (!granted) {
                            throw new RuntimeException("no permission");
                        }
                    }
                }).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Boolean aBoolean) {
                DownloadUtil.create(apkUrl, outputFile, new ProgressListener() {
                    @Override
                    public void onProgress(long currentBytes, long totalBytes, boolean isDone) {
                        if (downLoadListener != null) {
                            downLoadListener.inProgress((int) (currentBytes), (int) totalBytes);
                        }
                    }

                    @Override
                    public void onSuccess(File file) {
                        if (downLoadListener != null) {
                            downLoadListener.downLoadLatestSuccess(outputFile);
                        }
                        downLoading = false;
                        installApk(outputFile, VersionUpdateService.this);
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        downLoading = false;
                        if (downLoadListener != null) {
                            downLoadListener.downLoadLatestFailed();
                        }
                    }
                });
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });


    }

    public void setDownLoadListener(DownLoadListener downLoadListener) {
        this.downLoadListener = downLoadListener;
    }

    //安装apk
    public void installApk(File file, Context context) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".file_provider", file);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            Uri uri = Uri.fromFile(file);
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
//        intent.setAction(Intent.ACTION_VIEW);
//        intent.addCategory(Intent.CATEGORY_DEFAULT);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        //执行的数据类型
//        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class LocalBinder extends Binder {
        public VersionUpdateService getService() {
            return VersionUpdateService.this;
        }

    }
}
