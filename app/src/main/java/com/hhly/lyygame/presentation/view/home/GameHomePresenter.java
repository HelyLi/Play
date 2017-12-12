package com.hhly.lyygame.presentation.view.home;

import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.App;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.GameApi;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.UserApi;
import com.hhly.lyygame.data.net.client.BaseSubscriber;
import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.game.GameByModelIdReq;
import com.hhly.lyygame.data.net.protocol.game.GameByModelIdResp;
import com.hhly.lyygame.data.net.protocol.game.IndexActivityByModelIdReq;
import com.hhly.lyygame.data.net.protocol.game.IndexActivityByModelIdResp;
import com.hhly.lyygame.data.net.protocol.game.NewsListReq;
import com.hhly.lyygame.data.net.protocol.game.NewsListResp;
import com.hhly.lyygame.data.net.protocol.user.MsgListReq;
import com.hhly.lyygame.data.net.protocol.user.MsgListResp;
import com.hhly.lyygame.data.net.protocol.user.State;
import com.hhly.lyygame.data.repository.GameRepository;
import com.hhly.lyygame.presentation.downloadutils.DownloadItem;
import com.hhly.lyygame.presentation.utils.CollectionUtil;
import com.hhly.lyygame.presentation.utils.TelephonyUtil;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import zlc.season.rxdownload2.entity.DownloadRecord;


/**
 * Created by ${HELY} on 17/1/17.
 * 邮箱：heli.lixiong@gmail.com
 */

public class GameHomePresenter implements GameHomeContract.Presenter {

    private GameHomeContract.View mView;
    private final GameRepository mGameRepository;
    private final UserApi mUserApi;
    private final GameApi mGameApi;

    public GameHomePresenter(GameHomeContract.View view) {
        this.mView = view;
        mGameRepository = new GameRepository();
        mUserApi = RetrofitManager.getInstance(ApiType.USER_API).getUserApi();
        mGameApi = RetrofitManager.getInstance(ApiType.GAME_API).getGameApi();
        this.mView.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    public void concat(){

    }

    @Override
    public void getMsgPage() {

        MsgListReq req = new MsgListReq();
        req.setPlateFormId(TelephonyUtil.getOsTypeInt());

        mUserApi.getMsgPage(req.params())
                .compose(RxUtil.<MsgListResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(RetrofitManager.<MsgListResp>composeBackpressureOther())
                .compose(mView.<MsgListResp>bindToLife())
                .subscribe(new BaseSubscriber<MsgListResp>() {
                    @Override
                    protected void hideDialog() {

                    }

                    @Override
                    protected void showDialog() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {

                    }

                    @Override
                    public void onNext(MsgListResp msgListResp) {
                        if (msgListResp != null && msgListResp.isOk()) {
                            mView.onMsgSize(msgListResp.getUnreads());
                        }
                    }
                });
    }

    @Override
    public void getOnlyGames(boolean refresh) {

        GameByModelIdReq req = new GameByModelIdReq();
        req.setModelId("5");
        req.setPageNo(1);
        req.setTerminal(TelephonyUtil.getOsTypeInt());
        req.setChannelId(TelephonyUtil.getChannelId(App.getContext()));

        Logger.d("req=" + req);

        mGameRepository.getIndexGameByModelId(req.params())
                .compose(RxUtil.<GameByModelIdResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<GameByModelIdResp>bindToLife())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<GameByModelIdResp>() {
                    @Override
                    public boolean test(@NonNull GameByModelIdResp boutiqueGameListResp) throws Exception {
                        Logger.d("GET.hotReq");
                        if (boutiqueGameListResp == null ||
                                boutiqueGameListResp.getResult() != 0 ||
                                boutiqueGameListResp.getData() == null ||
                                CollectionUtil.isEmpty(boutiqueGameListResp.getData().getList())) {
                            mView.hideRefresh();
                        }
                        return boutiqueGameListResp != null && boutiqueGameListResp.isOk() && boutiqueGameListResp.getData() != null && CollectionUtil.isNotEmpty(boutiqueGameListResp.getData().getList());
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Function<GameByModelIdResp, List<GameByModelIdResp.GameByModeIdPage.GameByModeIdInfo>>() {
                    @Override
                    public List<GameByModelIdResp.GameByModeIdPage.GameByModeIdInfo> apply(@NonNull GameByModelIdResp boutiqueGameListResp) throws Exception {

                        List<GameByModelIdResp.GameByModeIdPage.GameByModeIdInfo> list = boutiqueGameListResp.getData().getList();
                        return list;
                    }
                })
                .map(new Function<List<GameByModelIdResp.GameByModeIdPage.GameByModeIdInfo>, List<DownloadItem>>() {
                    @Override
                    public List<DownloadItem> apply(@NonNull List<GameByModelIdResp.GameByModeIdPage.GameByModeIdInfo> gameInfos) throws Exception {

                        List<DownloadItem> list = new ArrayList<DownloadItem>();

                        for (GameByModelIdResp.GameByModeIdPage.GameByModeIdInfo gameInfo : gameInfos) {
                            DownloadItem data = new DownloadItem();
                            data.type = "2";
                            data.record = new DownloadRecord();
                            
                            data.record.setPackageName(gameInfo.getPackageName());
                            data.record.setApkName(gameInfo.getName());
                            data.record.setPicUrl(gameInfo.getImageUrl());
                            data.record.setUrl(gameInfo.getPackeChannelUrl());
                            data.popularitVal = gameInfo.getPopularitVal();
                            data.record.setGameId(gameInfo.getGameId());
                            data.record.setSize(gameInfo.getPackageSize());

                            data.versionCode = gameInfo.getVersionCode();

                            data.resGameTypeId = R.drawable.ic_game_android;
                            data.resGameType = R.string.lyy_game_android_type;

                            switch (gameInfo.getPlatformTerminal()) {
                                case State.PlatformTerminalType.ANDROID:
                                    data.resGameTypeId = R.drawable.ic_game_android;
                                    data.resGameType = R.string.lyy_game_android_type;
                                    break;
                                case State.PlatformTerminalType.IOS:
                                    data.resGameTypeId = R.drawable.ic_game_ios;
                                    data.resGameType = R.string.lyy_game_ios_type;
                                    break;
                                case State.PlatformTerminalType.H5:
                                    data.resGameTypeId = R.drawable.ic_game_h5;
                                    data.resGameType = R.string.lyy_game_h5_type;
                                    break;
                                case State.PlatformTerminalType.PC:
                                    data.resGameTypeId = R.drawable.ic_game_pc;
                                    data.resGameType = R.string.lyy_game_pc_type;
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
                        mView.hideRefresh();
                    }

                    @Override
                    public void onNext(List<DownloadItem> list) {
                        mView.hideRefresh();
                        mView.showOnlyGameList(list);
                    }
                });

    }

    /**
     * 活动专题
     *
     * @param refresh
     */
    @Override
    public void getActivitis(boolean refresh) {

        IndexActivityByModelIdReq req = new IndexActivityByModelIdReq();
        req.setTerminal(String.valueOf(TelephonyUtil.getOsTypeInt()));
        req.setModelId("9");

        mGameApi.getIndexActivityByModelId(req.params())
                .compose(RxUtil.<IndexActivityByModelIdResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<IndexActivityByModelIdResp>bindToLife())
                .subscribe(new BaseSubscriber<IndexActivityByModelIdResp>() {
                    @Override
                    protected void hideDialog() {

                    }

                    @Override
                    protected void showDialog() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {

                    }

                    @Override
                    public void onNext(IndexActivityByModelIdResp resp) {
                        if (resp != null && resp.isOk() && resp.getData() != null) {
                            mView.showActivityList(resp.getData().getList());
                        }
                    }
                });

    }

    @Override
    public void getNews(final boolean refresh) {

        NewsListReq req = new NewsListReq();
        req.setTerminal(String.valueOf(TelephonyUtil.getOsTypeInt()));
        req.setType("1");

        mGameApi.getNewsList(req.params())
                .compose(RxUtil.<NewsListResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<NewsListResp>bindToLife())
                .subscribe(new BaseSubscriber<NewsListResp>() {
                    @Override
                    protected void hideDialog() {

                    }

                    @Override
                    protected void showDialog() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {

                    }

                    @Override
                    public void onNext(NewsListResp resp) {
                        Logger.d("news.resp=" + resp);
                        if (resp != null && resp.isOk() && resp.getPager() != null) {
                            mView.showNews(resp.getPager().getList());
                        }
                    }
                });
    }

}
