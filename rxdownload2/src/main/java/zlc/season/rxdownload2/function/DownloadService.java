package zlc.season.rxdownload2.function;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.schedulers.Schedulers;
import zlc.season.rxdownload2.db.DataBaseHelper;
import zlc.season.rxdownload2.entity.DownloadBean;
import zlc.season.rxdownload2.entity.DownloadEvent;
import zlc.season.rxdownload2.entity.DownloadFlag;
import zlc.season.rxdownload2.entity.DownloadMission;
import zlc.season.rxdownload2.entity.DownloadRecord;
import zlc.season.rxdownload2.entity.DownloadStatus;
import zlc.season.rxdownload2.entity.MultiMission;
import zlc.season.rxdownload2.entity.SingleMission;

import static zlc.season.rxdownload2.function.Constant.WAITING_FOR_MISSION_COME;
import static zlc.season.rxdownload2.function.DownloadEventFactory.createEvent;
import static zlc.season.rxdownload2.function.DownloadEventFactory.installed;
import static zlc.season.rxdownload2.function.DownloadEventFactory.normal;
import static zlc.season.rxdownload2.function.Utils.createProcessor;
import static zlc.season.rxdownload2.function.Utils.deleteFiles;
import static zlc.season.rxdownload2.function.Utils.dispose;
import static zlc.season.rxdownload2.function.Utils.getFiles;
import static zlc.season.rxdownload2.function.Utils.log;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/11/10
 * Time: 09:49
 * FIXME
 */
public class DownloadService extends Service {
    public static final String INTENT_KEY = "zlc_season_rxdownload_max_download_number";

    private DownloadBinder mBinder;

    private Semaphore semaphore;
    private BlockingQueue<DownloadMission> downloadQueue;
    private Map<String, DownloadMission> missionMap;
    private Map<String, FlowableProcessor<DownloadEvent>> processorMap;

    private Disposable disposable;
    private DataBaseHelper dataBaseHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        mBinder = new DownloadBinder();
        downloadQueue = new LinkedBlockingQueue<>();
        processorMap = new ConcurrentHashMap<>();
        missionMap = new ConcurrentHashMap<>();

        dataBaseHelper = DataBaseHelper.getSingleton(getApplicationContext());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        log("start Download Service");
        dataBaseHelper.repairErrorFlag();
        if (intent != null) {
            int maxDownloadNumber = intent.getIntExtra(INTENT_KEY, 5);
            semaphore = new Semaphore(maxDownloadNumber);
        }
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        log("destroy Download Service");
        destroy();
        dataBaseHelper.closeDataBase();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        log("bind Download Service");
        startDispatch();
        return mBinder;
    }

    /**
     * Receive the url download event.
     * <p>
     * Will receive the following event:
     * {@link DownloadFlag#NORMAL}、{@link DownloadFlag#WAITING}、
     * {@link DownloadFlag#STARTED}、{@link DownloadFlag#PAUSED}、
     * {@link DownloadFlag#COMPLETED}、{@link DownloadFlag#FAILED};
     * <p>
     * Every event has {@link DownloadStatus}, you can get it and display it on the interface.
     *
     * @return DownloadEvent
     */
    public FlowableProcessor<DownloadEvent> receiveDownloadEvent(DownloadBean bean) {
        FlowableProcessor<DownloadEvent> processor = createProcessor(bean.getUrl(), processorMap);
        
        Utils.log("receiveDownloadEvent.1.bean=" + bean.toString());
        if (Utils.isInstalled(getApplicationContext(), bean.getPackageName())) {
            DownloadMission mission = missionMap.get(bean.getUrl());
            if (mission == null) {
                DownloadRecord record = dataBaseHelper.readSingleRecord(bean.getUrl());
                if (record == null) {
                    dataBaseHelper.insertRecord(bean, DownloadFlag.INSTALLED);
                } else {
                    dataBaseHelper.updateRecord(bean.getUrl(), DownloadFlag.INSTALLED);
                }
            } else {
                dataBaseHelper.updateRecord(bean.getUrl(), DownloadFlag.INSTALLED, mission.getUrl());
            }
            processor.onNext(installed(dataBaseHelper.readStatus(bean.getUrl())));
        } else {
            Utils.log("receiveDownloadEvent.2.bean=" + bean.toString());

            DownloadMission mission = missionMap.get(bean.getUrl());
            if (mission == null) {  //Not yet add this url mission.
                Utils.log("receiveDownloadEvent.3"  + bean.toString());

                DownloadRecord record = dataBaseHelper.readSingleRecord(bean.getUrl());
                if (record == null) {
                    Utils.log("receiveDownloadEvent.4" + bean.toString());

                    processor.onNext(normal(null));
                } else {
                    Utils.log("receiveDownloadEvent.5" + bean.toString());

                    if (record.getFlag() == DownloadFlag.INSTALLED) {
                        Utils.log("receiveDownloadEvent.6" + bean.toString());

                        dataBaseHelper.deleteRecord(bean.getUrl());
                        processor.onNext(normal(dataBaseHelper.readStatus(bean.getUrl())));
                    }else {
                        Utils.log("receiveDownloadEvent.7" + bean.toString());

                        File file = getFiles(record.getSaveName(), record.getSavePath())[0];
                        if (file.exists()) {
                            Utils.log("receiveDownloadEvent.8" + bean.toString());

                            processor.onNext(createEvent(record.getFlag(), record.getStatus()));
                        } else {
                            Utils.log("receiveDownloadEvent.9" + bean.toString());

                            processor.onNext(normal(null));
                        }
                    }
                }
            }else {
                Utils.log("receiveDownloadEvent.10" + bean.toString());

                DownloadRecord record = dataBaseHelper.readSingleRecord(bean.getUrl());
                if (record == null) {
                    Utils.log("receiveDownloadEvent.11" + bean.toString());
                    processor.onNext(normal(null));
                }else {
                    Utils.log("receiveDownloadEvent.12" + bean.toString());

                    Utils.log("record.getFlag()=" + record.getFlag());

                    if (record.getFlag() == DownloadFlag.INSTALLED) {
                        Utils.log("receiveDownloadEvent.13" + bean.toString());

                        //// FIXME: 17/4/22
//                        deleteDownload(record.getUrl(), true);
//                        processor.onNext(normal(null));

                        //// FIXME: 17/4/22
                        File file = getFiles(record.getSaveName(), record.getSavePath())[0];
                        if (file.exists()) {
                            Utils.log("receiveDownloadEvent.14" + bean.toString());
                            processor.onNext(createEvent(DownloadFlag.COMPLETED, record.getStatus()));
                        } else {
                            Utils.log("receiveDownloadEvent.15" + bean.toString());
                            processor.onNext(normal(null));
                        }
                    }else {
                        Utils.log("receiveDownloadEvent.16" + bean.toString());

                        File file = getFiles(record.getSaveName(), record.getSavePath())[0];
                        if (file.exists()) {
                            Utils.log("receiveDownloadEvent.17" + bean.toString());

                            processor.onNext(createEvent(record.getFlag(), record.getStatus()));
                        } else {
                            Utils.log("receiveDownloadEvent.18" + bean.toString());

                            processor.onNext(normal(null));
                        }
                    }
                }
            }
        }
        return processor;
    }

    /**
     * Receive the url download event.
     * <p>
     * Will receive the following event:
     * {@link DownloadFlag#NORMAL}、{@link DownloadFlag#WAITING}、
     * {@link DownloadFlag#STARTED}、{@link DownloadFlag#PAUSED}、
     * {@link DownloadFlag#COMPLETED}、{@link DownloadFlag#FAILED};
     * <p>
     * Every event has {@link DownloadStatus}, you can get it and display it on the interface.
     *
     * @param url url
     * @return DownloadEvent
     */
    public FlowableProcessor<DownloadEvent> receiveDownloadEvent(String url) {
        FlowableProcessor<DownloadEvent> processor = createProcessor(url, processorMap);
        DownloadMission mission = missionMap.get(url);
        if (mission == null) {  //Not yet add this url mission.
            DownloadRecord record = dataBaseHelper.readSingleRecord(url);
            if (record == null) {
                processor.onNext(normal(null));
            } else {
                File file = getFiles(record.getSaveName(), record.getSavePath())[0];
                if (file.exists()) {
                    processor.onNext(createEvent(record.getFlag(), record.getStatus()));
                } else {
                    processor.onNext(normal(null));
                }
            }
        }
        return processor;
    }

    /**
     * Add this mission into download queue.
     *
     * @param mission mission
     * @throws InterruptedException Blocking queue
     */
    public void addDownloadMission(DownloadMission mission) throws InterruptedException {
        mission.init(missionMap, processorMap);
        mission.insertOrUpdate(dataBaseHelper);
        mission.sendWaitingEvent(dataBaseHelper);

        downloadQueue.put(mission);
    }

    /**
     * Pause download.
     * <p>
     * Pause a url or all tasks belonging to missionId.
     *
     * @param url url or missionId
     */
    public void pauseDownload(String url) {
        DownloadMission mission = missionMap.get(url);
        if (mission != null && mission instanceof SingleMission) {
            mission.pause(dataBaseHelper);
        }
    }

    /**
     * Delete download.
     * <p>
     * Delete a url or all tasks belonging to missionId.
     *
     * @param url        url or missionId
     * @param deleteFile whether delete file
     */
    public void deleteDownload(String url, boolean deleteFile) {
        DownloadMission mission = missionMap.get(url);
        if (mission != null && mission instanceof SingleMission) {
            mission.delete(dataBaseHelper, deleteFile);
            missionMap.remove(url);
        } else {
            createProcessor(url, processorMap).onNext(normal(null));

            if (deleteFile) {
                DownloadRecord record = dataBaseHelper.readSingleRecord(url);
                if (record != null) {
                    deleteFiles(getFiles(record.getSaveName(), record.getSavePath()));
                }
            }
            dataBaseHelper.deleteRecord(url);
        }
    }

    /**
     * Start all mission. Not include MultiMission.
     *
     * @throws InterruptedException interrupt
     */
    public void startAll() throws InterruptedException {
        for (DownloadMission each : missionMap.values()) {
            if (each.isCompleted()) {
                continue;
            }

            if (each instanceof SingleMission) {
                addDownloadMission(new SingleMission((SingleMission) each, null));
            }

//            if (each instanceof MultiMission) {
//                addDownloadMission(new MultiMission((MultiMission) each));
//            }
        }
    }

    /**
     * Pause all mission.Not include MultiMission.
     */
    public void pauseAll() {
        for (DownloadMission each : missionMap.values()) {
            if (each instanceof SingleMission) {
                each.pause(dataBaseHelper);
            }
        }
        downloadQueue.clear();
    }

    /**
     * Start all mission which associate with missionId.
     *
     * @param missionId missionId
     * @throws InterruptedException interrupt
     */
    public void startAll(String missionId) throws InterruptedException {
        DownloadMission mission = missionMap.get(missionId);
        if (mission == null) {
            log("mission not exists");
            return;
        }

        if (mission.isCompleted()) {
            log("mission complete");
            return;
        }

        if (mission instanceof MultiMission) {
            addDownloadMission(new MultiMission((MultiMission) mission));
        }
    }

    /**
     * Pause all mission which associate with missionId
     *
     * @param missionId missionId
     */
    public void pauseAll(String missionId) {
        DownloadMission mission = missionMap.get(missionId);
        if (mission == null) {
            log("mission not exists");
            return;
        }

        if (mission.isCompleted()) {
            log("mission complete");
            return;
        }

        if (mission instanceof MultiMission) {
            mission.pause(dataBaseHelper);
        }
    }


    /**
     * Delete all mission which associate with missionId.
     *
     * @param missionId  missionId
     * @param deleteFile deleteFile?
     */
    public void deleteAll(String missionId, boolean deleteFile) {
        DownloadMission mission = missionMap.get(missionId);
        if (mission != null && mission instanceof MultiMission) {
            mission.delete(dataBaseHelper, deleteFile);
            missionMap.remove(missionId);
        } else {
            createProcessor(missionId, processorMap).onNext(normal(null));

            if (deleteFile) {
                List<DownloadRecord> list = dataBaseHelper.readMissionsRecord(missionId);
                for (DownloadRecord each : list) {
                    deleteFiles(getFiles(each.getSaveName(), each.getSavePath()));
                    dataBaseHelper.deleteRecord(each.getUrl());
                }
            }
        }
    }

    /**
     * start dispatch download queue.
     */
    private void startDispatch() {
        disposable = Observable
                .create(new ObservableOnSubscribe<DownloadMission>() {
                    @Override
                    public void subscribe(ObservableEmitter<DownloadMission> emitter) throws Exception {
                        DownloadMission mission;
                        while (!emitter.isDisposed()) {
                            try {
                                log(WAITING_FOR_MISSION_COME);
                                mission = downloadQueue.take();
                                log(Constant.MISSION_COMING);
                            } catch (InterruptedException e) {
                                log("Interrupt blocking queue.");
                                continue;
                            }
                            emitter.onNext(mission);
                        }
                        emitter.onComplete();
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Consumer<DownloadMission>() {
                    @Override
                    public void accept(DownloadMission mission) throws Exception {
                        mission.start(semaphore);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        log(throwable);
                    }
                });
    }

    /**
     * Call when service is onDestroy.
     */
    private void destroy() {
        dispose(disposable);
        for (DownloadMission each : missionMap.values()) {
            each.pause(dataBaseHelper);
        }
        downloadQueue.clear();
    }

    public class DownloadBinder extends Binder {
        public DownloadService getService() {
            return DownloadService.this;
        }
    }
}
