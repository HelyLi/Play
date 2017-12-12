package com.hhly.lyygame.presentation.view.gamehall.category;

import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.client.BaseSubscriber;
import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.game.GameByModelIdReq;
import com.hhly.lyygame.data.net.protocol.game.GameByModelIdResp;
import com.hhly.lyygame.data.net.protocol.game.GameByTypeReq;
import com.hhly.lyygame.data.net.protocol.game.GameByTypeResp;
import com.hhly.lyygame.data.net.protocol.user.State;
import com.hhly.lyygame.data.repository.GameRepository;
import com.hhly.lyygame.presentation.downloadutils.DownloadItem;
import com.hhly.lyygame.presentation.utils.CollectionUtil;
import com.hhly.lyygame.presentation.utils.TelephonyUtil;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import zlc.season.rxdownload2.entity.DownloadRecord;

/**
 * Created by ${HELY} on 17/2/25.
 * 邮箱：heli.lixiong@gmail.com
 */

public class GategoryListPresenter implements GategoryListContract.Presenter {

    private final GategoryListContract.View mView;
    private final GameRepository mGameRepository;

    public GategoryListPresenter(GategoryListContract.View view) {
        this.mView = view;
        mGameRepository = new GameRepository();
        this.mView.setPresenter(this);
    }

    @Override
    public void getGameList(boolean refresh, GameCategory category, int pageNo, int pageSize) {
        switch (category) {
            case HOT:
                getIndexGameByModelId(refresh, pageNo, pageSize, "2");
                break;
            case QUA:
                getIndexGameByModelId(refresh, pageNo, pageSize, "1");
                break;
            case ONLY:
            case REC:
            case OFFLINE:
            case ONLINE:
            case ROLE:
            case SHOOT:
            case ARPG:
            case ADVE:
            case SLG:
            case CHESS:
            case RAC:
            case ACTION:
            case SIM:
            case LVG:
            case FLY:
            case SPORT:
            case FIGHT:
            case PUZ:
            case FILLER:
            case OTHER:
                getGameByType(refresh, category, pageNo, pageSize);
                break;
            default:
                break;
        }
    }

    private void getIndexGameByModelId(final boolean refresh, int pageNo, int pageSize, String modelId) {

        GameByModelIdReq req = new GameByModelIdReq();
        req.setTerminal(TelephonyUtil.getOsTypeInt());
        req.setPageNo(pageNo);
        req.setPageSize(pageSize);
        req.setModelId(modelId);

        mGameRepository.getIndexGameByModelId(req.params())
                .compose(RxUtil.<GameByModelIdResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<GameByModelIdResp>bindToLife())
                //.compose(RetrofitManager.<GameByModelIdResp>composeBackpressureError())
                .filter(new Predicate<GameByModelIdResp>() {
                    @Override
                    public boolean test(@NonNull GameByModelIdResp hotGameListResp) throws Exception {

                        boolean success = hotGameListResp != null && hotGameListResp.isOk() && hotGameListResp.getData() != null && CollectionUtil.isNotEmpty(hotGameListResp.getData().getList());
                        if (success) {
                            mView.showTotalPage(hotGameListResp.getData().getTotalPages());
                        } else {
                            mView.onFailure();
                        }
                        return success;
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Function<GameByModelIdResp, List<GameByModelIdResp.GameByModeIdPage.GameByModeIdInfo>>() {
                    @Override
                    public List<GameByModelIdResp.GameByModeIdPage.GameByModeIdInfo> apply(@NonNull GameByModelIdResp hotGameListResp) throws Exception {

                        List<GameByModelIdResp.GameByModeIdPage.GameByModeIdInfo> list = hotGameListResp.getData().getList();
                        return list;
                    }
                })
                .map(new Function<List<GameByModelIdResp.GameByModeIdPage.GameByModeIdInfo>, List<DownloadItem>>() {
                    @Override
                    public List<DownloadItem> apply(@NonNull List<GameByModelIdResp.GameByModeIdPage.GameByModeIdInfo> gameInfos) throws Exception {

                        List<DownloadItem> list = new ArrayList<DownloadItem>();

                        for (GameByModelIdResp.GameByModeIdPage.GameByModeIdInfo gameInfo : gameInfos) {
                            DownloadItem data = new DownloadItem();
                            data.record = new DownloadRecord();

                            data.record.setPackageName(gameInfo.getPackageName());
                            data.record.setApkName(gameInfo.getName());
                            data.record.setPicUrl(gameInfo.getIconURL());
                            data.record.setUrl(gameInfo.getSourUrl());
                            data.record.setSize(gameInfo.getPackageSize());
                            data.popularitVal = gameInfo.getPopularitVal();

//                            data.packageSize = gameInfo.getPackageSize();
                            data.versionCode = gameInfo.getVersionCode();

                            data.record.setGameId(gameInfo.getGameId());
                            data.resGameTypeId = R.drawable.ic_game_android;

                            switch (gameInfo.getPlatformTerminal()) {
                                case State.PlatformTerminalType.ANDROID:
                                    data.resGameTypeId = R.drawable.ic_game_android;
                                    break;
                                case State.PlatformTerminalType.IOS:
                                    data.resGameTypeId = R.drawable.ic_game_ios;
                                    break;
                                case State.PlatformTerminalType.H5:
                                    data.resGameTypeId = R.drawable.ic_game_h5;
                                    break;
                                case State.PlatformTerminalType.PC:
                                    data.resGameTypeId = R.drawable.ic_game_pc;
                                    break;
                            }
                            list.add(data);
                        }
                        return list;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<List<DownloadItem>>() {
                    @Override
                    protected void hideDialog() {

                    }

                    @Override
                    protected void showDialog() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        mView.onFailure();
                    }

                    @Override
                    public void onNext(List<DownloadItem> list) {
                        mView.showGameList(list,refresh);
                    }
                });

    }

    public void getGameByType(final boolean refresh, final GameCategory category, int pageNo, int pageSize) {
        GameByTypeReq req = new GameByTypeReq();
        req.setGameType(category.value());
        req.setTerminal(TelephonyUtil.getOsTypeInt());
        req.setPageNo(pageNo);
        req.setPageSize(pageSize);

        mGameRepository.getGameByType(req.params())
                .compose(RxUtil.<GameByTypeResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<GameByTypeResp>bindToLife())
                //.compose(RetrofitManager.<GameByTypeResp>composeBackpressureError())
                .filter(new Predicate<GameByTypeResp>() {
                    @Override
                    public boolean test(@NonNull GameByTypeResp gameByTypeResp) throws Exception {

                        boolean success = gameByTypeResp != null && gameByTypeResp.isOk() && gameByTypeResp.getData() != null && CollectionUtil.isNotEmpty(gameByTypeResp.getData().getList());
                        if (success) {
                            mView.showTotalPage(gameByTypeResp.getData().getTotalPages());
                        } else {
                            mView.onFailure();
                        }
                        return success;
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Function<GameByTypeResp, List<GameByTypeResp.GameByTypePage.GameByTypeInfo>>() {
                    @Override
                    public List<GameByTypeResp.GameByTypePage.GameByTypeInfo> apply(@NonNull GameByTypeResp gameByTypeResp) throws Exception {

                        List<GameByTypeResp.GameByTypePage.GameByTypeInfo> list = gameByTypeResp.getData().getList();

//                        if (category == GameCategory.ONLY) {//独家游戏固定5星
//                            for (GameByTypeResp.GameByTypePage.GameByTypeInfo gameByType : list) {
//                                gameByType.setPopularitVal(50000 + gameByType.getPopularitVal());
//                            }
//                        }

//                        Logger.d("size=" + list.size());

                        return list;
                    }
                })
                .map(new Function<List<GameByTypeResp.GameByTypePage.GameByTypeInfo>, List<DownloadItem>>() {
                    @Override
                    public List<DownloadItem> apply(@NonNull List<GameByTypeResp.GameByTypePage.GameByTypeInfo> gameInfos) throws Exception {

                        List<DownloadItem> list = new ArrayList<DownloadItem>();

                        for (GameByTypeResp.GameByTypePage.GameByTypeInfo gameInfo : gameInfos) {
                            DownloadItem data = new DownloadItem();
                            data.record = new DownloadRecord();

                            data.record.setPackageName(gameInfo.getPackageName());
                            data.record.setApkName(gameInfo.getName());
                            data.record.setPicUrl(gameInfo.getIconUrl());
                            data.record.setUrl(gameInfo.getSourUrl());
                            data.record.setSize(gameInfo.getPackageSize());
                            data.popularitVal = gameInfo.getPopularitVal();

//                            data.packageSize = gameInfo.getPackageSize();
                            data.versionCode = gameInfo.getVersionCode();

                            data.record.setGameId(gameInfo.getId());

                            data.resGameTypeId = R.drawable.ic_game_android;

                            switch (gameInfo.getPlatformTerminal()) {
                                case State.PlatformTerminalType.ANDROID:
                                    data.resGameTypeId = R.drawable.ic_game_android;
                                    break;
                                case State.PlatformTerminalType.IOS:
                                    data.resGameTypeId = R.drawable.ic_game_ios;
                                    break;
                                case State.PlatformTerminalType.H5:
                                    data.resGameTypeId = R.drawable.ic_game_h5;
                                    break;
                                case State.PlatformTerminalType.PC:
                                    data.resGameTypeId = R.drawable.ic_game_pc;
                                    break;
                            }
                            list.add(data);
                        }
                        return list;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<List<DownloadItem>>() {
                    @Override
                    protected void hideDialog() {

                    }

                    @Override
                    protected void showDialog() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        mView.onFailure();
                    }

                    @Override
                    public void onNext(List<DownloadItem> list) {
                        mView.showGameList(list,refresh);
                    }
                });

    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
    }

}
