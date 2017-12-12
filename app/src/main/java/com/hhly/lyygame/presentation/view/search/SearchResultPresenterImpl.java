package com.hhly.lyygame.presentation.view.search;

import android.text.TextUtils;

import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.App;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.GameApi;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.client.BaseSubscriber;
import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.game.SearchGameListReq;
import com.hhly.lyygame.data.net.protocol.game.SearchGameListResp;
import com.hhly.lyygame.data.net.protocol.user.State;
import com.hhly.lyygame.presentation.downloadutils.DownloadItem;
import com.hhly.lyygame.presentation.utils.SharedPrefsStrListUtil;
import com.hhly.lyygame.presentation.utils.TelephonyUtil;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import zlc.season.rxdownload2.entity.DownloadRecord;

/**
 * Created by ${HELY} on 17/2/23.
 * 邮箱：heli.lixiong@gmail.com
 */

public class SearchResultPresenterImpl implements SearchContract.SearchResultPresenter {

    private final SearchContract.SearchResultView mView;
    private final GameApi mGameApi;

    public SearchResultPresenterImpl(SearchContract.SearchResultView view) {
        this.mView = view;
        mGameApi = RetrofitManager.getInstance(ApiType.GAME_API).getGameApi();
        mView.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void searchGameList(String keyword, int pageNo, int pageSize) {

        SearchGameListReq req = new SearchGameListReq();
        req.setPlatformTerminal(TelephonyUtil.getOsTypeInt());
        req.setKeyword(keyword);
        req.setPageNo(pageNo);
        req.setDataSize(pageSize);

        mGameApi.getIndexAllGame(req.params())
                .compose(RxUtil.<SearchGameListResp>applySchedulers(RxUtil.IO_TRANSFORMER_BACK_PRESSURE))
                //.compose(RetrofitManager.<String>composeBackpressureError())
                .compose(mView.<SearchGameListResp>bindToLife())
                .map(new Function<SearchGameListResp, List<SearchGameListResp.SearchPage.SearchGameInfo>>() {

                    @Override
                    public List<SearchGameListResp.SearchPage.SearchGameInfo> apply(@NonNull SearchGameListResp searchGameListResp) throws Exception {

                        List<SearchGameListResp.SearchPage.SearchGameInfo> list = searchGameListResp.getPager().getList();

                        return list;
                    }
                })
                .map(new Function<List<SearchGameListResp.SearchPage.SearchGameInfo>, List<DownloadItem>>() {
                    @Override
                    public List<DownloadItem> apply(@NonNull List<SearchGameListResp.SearchPage.SearchGameInfo> gameInfos) throws Exception {

                        List<DownloadItem> list = new ArrayList<DownloadItem>();

                        for (SearchGameListResp.SearchPage.SearchGameInfo gameInfo : gameInfos) {
                            DownloadItem data = new DownloadItem();
                            data.record = new DownloadRecord();

                            data.record.setPackageName(gameInfo.getPackageName());
                            data.record.setApkName(gameInfo.getName());
                            data.record.setPicUrl(gameInfo.getIconUrl());
                            data.record.setUrl(gameInfo.getSourUrl());
                            data.record.setSize(gameInfo.getPackageSize());

                            data.popularitVal = gameInfo.getPopularitVal();
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

                            if (gameInfo.getPlatformTerminal() == State.PlatformTerminalType.ANDROID ||
                                    gameInfo.getPlatformTerminal() == State.PlatformTerminalType.H5) {
                                list.add(data);
                            }
                        }

                        Logger.d("second.list.size=" + list.size());

                        return list;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<List<DownloadItem>>() {
                    @Override
                    protected void hideDialog() {
                        mView.onFailure();
                    }

                    @Override
                    protected void showDialog() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {

                    }

                    @Override
                    public void onNext(List<DownloadItem> list) {
                        Logger.d("list.size=" + list.size());
                        mView.showSearchGameList(list);
                    }
                });

    }

    @Override
    public void saveSearchRecord(String record) {

        Flowable.just(record)
                .compose(RxUtil.<String>applySchedulers(RxUtil.IO_TRANSFORMER_BACK_PRESSURE))
                //.compose(RetrofitManager.<String>composeBackpressureError())
                .compose(mView.<String>bindToLife())
                .subscribe(new BaseSubscriber<String>() {
                    @Override
                    protected void hideDialog() {

                    }

                    @Override
                    protected void showDialog() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        Logger.d(e.message + e.code);
                    }

                    @Override
                    public void onNext(String record) {
                        if (TextUtils.isEmpty(record)) return;
                        List<String> records = SharedPrefsStrListUtil.getStrListValue(App.getContext(), "search_record");
                        if (records.contains(record)) return;
                        records.add(0, record);
                        SharedPrefsStrListUtil.putStrListValue(App.getContext(), "search_record", records);
                    }
                });
    }

}
