package com.hhly.lyygame.presentation.downloadutils;

import android.Manifest;

import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.App;
import com.hhly.lyygame.domain.config.SystemConfig;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadBean;
import zlc.season.rxdownload2.entity.DownloadEvent;
import zlc.season.rxdownload2.entity.DownloadFlag;
import zlc.season.rxdownload2.entity.DownloadRecord;
import zlc.season.rxdownload2.function.Utils;

/**
 * Created by ${HELY} on 17/3/22.
 * 邮箱：heli.lixiong@gmail.com
 */

public class DownloadHelper {

    private RxDownload mRxDownload;
    private static DownloadHelper sInstance = null;
    private static final int MAX_DOWNLOAD_COUNT = 2;
    private static final int MAX_RETRY_COUNT = 2;
    private static final int MAX_THREAD = 2;

    private DownloadHelper() {
        String defaultSavePath = SystemConfig.get().getPath() + File.separator + "apks";
        File file = new File(defaultSavePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        mRxDownload = RxDownload.getInstance(App.getContext()).maxDownloadNumber(MAX_DOWNLOAD_COUNT)
                .maxRetryCount(MAX_RETRY_COUNT)
                .maxThread(MAX_THREAD);
    }

    public static DownloadHelper get() {
        if (sInstance == null) {
            synchronized (DownloadHelper.class) {
                if (sInstance == null) {
                    sInstance = new DownloadHelper();
                }
            }
        }
        return sInstance;
    }

    /**
     * 添加下载 apkItem 不能为null
     *
     * @param apkData
     * @return
     */
    public Observable<Object> start(DownloadBean apkData) {

        return new RxPermissions(App.getTopActivity()).request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .doOnNext(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean granted) throws Exception {
                        if (!granted) {
                            throw new RuntimeException("no permission");
                        }
                    }
                })
                .compose(mRxDownload.<Boolean>transformService(apkData));
    }

    /**
     * 获取apk 安装的信息
     *
     * @return
     */
    public Observable<DownloadEvent> receiveDownloadEvent(DownloadBean apkData) {
        return mRxDownload.receiveDownloadStatus(apkData);
    }

    /**
     * 继续／暂停下载
     *
     * @param apkData
     */
    public void pause(DownloadBean apkData) {
        mRxDownload.pauseServiceDownload(apkData.getUrl()).subscribe();
    }

    /**
     * 获取正在下载apk
     *
     * @return
     */
    public Observable<List<DownloadItem>> getDownloadingRecord() {

        return mRxDownload.getTotalDownloadRecords()
                .compose(RxUtil.<List<DownloadRecord>>applySchedulers(RxUtil.IO_TRANSFORMER))
                .flatMap(new Function<List<DownloadRecord>, ObservableSource<List<DownloadItem>>>() {
                    @Override
                    public ObservableSource<List<DownloadItem>> apply(@NonNull List<DownloadRecord> downloadRecords) throws Exception {

                        return Observable.just(downloadRecords).map(new Function<List<DownloadRecord>, List<DownloadItem>>() {
                            @Override
                            public List<DownloadItem> apply(@NonNull List<DownloadRecord> downloadRecords) throws Exception {
                                List<DownloadItem> apkDataList = new ArrayList<DownloadItem>();

                                for (DownloadRecord record : downloadRecords) {
                                    int flag = record.getFlag();
                                    if ((flag == DownloadFlag.NORMAL ||
                                            flag == DownloadFlag.WAITING ||
                                            flag == DownloadFlag.STARTED ||
                                            flag == DownloadFlag.COMPLETED ||
                                            flag == DownloadFlag.PAUSED ||
                                            flag == DownloadFlag.FAILED) && !Utils.isInstalled(App.getContext(), record.getPackageName())) {
                                        DownloadItem item = new DownloadItem();
                                        item.record = record;
                                        apkDataList.add(item);
                                    }
                                }

                                return apkDataList;
                            }
                        });
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 获取已经安装apk
     */
    public Observable<List<DownloadItem>> getDownloadedRecord() {

        return mRxDownload.getTotalDownloadRecords()
                .compose(RxUtil.<List<DownloadRecord>>applySchedulers(RxUtil.IO_TRANSFORMER))
                .flatMap(new Function<List<DownloadRecord>, ObservableSource<List<DownloadItem>>>() {
                    @Override
                    public ObservableSource<List<DownloadItem>> apply(@NonNull List<DownloadRecord> downloadRecords) throws Exception {

                        return Observable.just(downloadRecords).map(new Function<List<DownloadRecord>, List<DownloadItem>>() {
                            @Override
                            public List<DownloadItem> apply(@NonNull List<DownloadRecord> downloadRecords) throws Exception {

                                List<DownloadItem> apkDataList = new ArrayList<DownloadItem>();

                                for (DownloadRecord record : downloadRecords) {
                                    int flag = record.getFlag();
                                    if (flag == DownloadFlag.INSTALLED || Utils.isInstalled(App.getContext(), record.getPackageName())) {

                                        if (Utils.isInstalled(App.getContext(), record.getPackageName())){
                                            DownloadItem item = new DownloadItem();
                                            item.record = record;
                                            item.record.setFlag(DownloadFlag.INSTALLED);
                                            apkDataList.add(item);
                                        }else{
                                            Logger.d("record=" + record);
                                            //删除记录
                                            mRxDownload.deleteServiceDownload(record.getUrl(), true).subscribe();
                                        }
                                    }
                                }
                                return apkDataList;
                            }
                        });
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }

    public RxDownload rxDownload() {
        return mRxDownload;
    }

}
