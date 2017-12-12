package com.hhly.lyygame.presentation.view.activity;

import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.GameApi;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.client.BaseSubscriber;
import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.game.GameNoticeReq;
import com.hhly.lyygame.data.net.protocol.game.GameNoticeResp;
import com.hhly.lyygame.data.net.protocol.game.IndexActivityByModelIdReq;
import com.hhly.lyygame.data.net.protocol.game.IndexActivityByModelIdResp;
import com.hhly.lyygame.data.repository.BannerRepository;
import com.hhly.lyygame.presentation.utils.CollectionUtil;
import com.hhly.lyygame.presentation.utils.TelephonyUtil;

import static com.classic.android.rx.RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE;

/**
 * 活动Presenter
 * Created by Simon on 2016/12/10.
 */

public class ActivitiesPresenter implements ActivitiesContract.Presenter {

    private final BannerRepository mBannerRepository;
    private final GameApi mGameApi;
    private final ActivitiesContract.View mView;

    public ActivitiesPresenter(ActivitiesContract.View view) {
        mView = view;
        mBannerRepository = new BannerRepository();
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
    public void getActivities(final boolean refresh) {

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
                        mView.onFailure();
                    }

                    @Override
                    public void onNext(IndexActivityByModelIdResp resp) {

                        if (resp == null || !resp.isOk()){
                            mView.onFailure();
                        }else if (resp.getData() == null || CollectionUtil.isEmpty(resp.getData().getList())){
                            mView.onEmptyView();
                        }else {
                            mView.showActivityList(resp.getData().getList());
                        }

                    }
                });
    }

    @Override
    public void loadGameActivity(int gameId, int noticeType) {
        GameNoticeReq req = new GameNoticeReq();
        req.setPlatformTerminal(TelephonyUtil.getOsTypeInt());
        req.setNoticeType(noticeType);
        req.setPlatformId(gameId);

        mGameApi.getGameNotice(req.params())
                .compose(RxUtil.<GameNoticeResp>applySchedulers(IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<GameNoticeResp>bindToLife())
                .compose(RetrofitManager.<GameNoticeResp>composeBackpressureError())
                .subscribe(new BaseSubscriber<GameNoticeResp>() {
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
                    public void onNext(GameNoticeResp resp) {

                        if (resp == null || !resp.isOk()){
                            mView.onFailure();
                        }else if (resp.getPager() == null || CollectionUtil.isEmpty(resp.getPager().getList())){
                            mView.onEmptyView();
                        }else {
                            mView.showGameActive(resp.getPager().getList());
                        }
                    }
                });
    }

}
