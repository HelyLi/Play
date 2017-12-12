package com.hhly.lyygame.presentation.view.search;

import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.App;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.GameApi;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.client.BaseSubscriber;
import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.game.GameByModelIdReq;
import com.hhly.lyygame.data.net.protocol.game.GameByModelIdResp;
import com.hhly.lyygame.presentation.utils.CollectionUtil;
import com.hhly.lyygame.presentation.utils.SharedPrefsStrListUtil;
import com.hhly.lyygame.presentation.utils.TelephonyUtil;
import com.orhanobut.logger.Logger;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by ${HELY} on 17/2/23.
 * 邮箱：heli.lixiong@gmail.com
 */

public class SearchInitializePresenterImpl implements SearchContract.SearchInitializePresenter {

    private final SearchContract.SearchInitializeView mView;
    private final GameApi mGameApi;

    public SearchInitializePresenterImpl(SearchContract.SearchInitializeView view) {
        mView = view;
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
    public void getHotGameList() {
        GameByModelIdReq req = new GameByModelIdReq();
        req.setTerminal(TelephonyUtil.getOsTypeInt());
        req.setModelId("3");
        req.setPageNo(1);
        req.setPageSize(4);

        mGameApi.getIndexGameByModelId(req.params())
                .compose(RxUtil.<GameByModelIdResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                //.compose(RetrofitManager.<GameByModelIdResp>composeBackpressureError())
                .compose(mView.<GameByModelIdResp>bindToLife())
                .subscribe(new BaseSubscriber<GameByModelIdResp>() {
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
                    public void onNext(GameByModelIdResp hotGameListResp) {
                        if (hotGameListResp.isOk() && hotGameListResp.getData() != null && CollectionUtil.isNotEmpty(hotGameListResp.getData().getList())) {
                            List<GameByModelIdResp.GameByModeIdPage.GameByModeIdInfo> list = hotGameListResp.getData().getList();
                            if (list.size() > 4) {
                                list.subList(4, list.size()).clear();
                            }
                            mView.showHotGameList(list);
                        }
                    }
                });

    }

    @Override
    public void getSearchRecord() {
        Logger.d("getSearchRecord");

        Flowable.just(SharedPrefsStrListUtil.getStrListValue(App.getContext(), "search_record"))
                .compose(RxUtil.<List<String>>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                //.compose(RetrofitManager.<List<String>>composeBackpressureError())
                .compose(mView.<List<String>>bindToLife())
                .subscribe(new BaseSubscriber<List<String>>() {
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
                    public void onNext(List<String> strings) {

                        if (strings.size() > 4) {
                            strings.subList(4, strings.size()).clear();
                        }
                        mView.showSearchRecord(strings);
                    }
                });

    }

    @Override
    public void clearSearchRecord() {

        SharedPrefsStrListUtil.clear(App.getContext());
        mView.onClearRecord();
    }

}
