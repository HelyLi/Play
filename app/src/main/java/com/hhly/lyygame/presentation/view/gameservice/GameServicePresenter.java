package com.hhly.lyygame.presentation.view.gameservice;

import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.GameApi;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.client.BaseSubscriber;
import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.game.GameAreaReq;
import com.hhly.lyygame.data.net.protocol.game.GameAreaResp;
import com.hhly.lyygame.data.net.protocol.user.State;
import com.hhly.lyygame.presentation.utils.CollectionUtil;

/**
 * Created by ${HELY} on 17/1/18.
 * 邮箱：heli.lixiong@gmail.com
 */

public class GameServicePresenter implements GameServiceContact.Persenter {

    private final GameServiceContact.View mView;
    private final GameApi mGameApi;

    public GameServicePresenter(GameServiceContact.View view) {
        this.mView = view;
        mGameApi = RetrofitManager.getInstance(ApiType.GAME_API).getGameApi();
        this.mView.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void getGameArea(final int serviceType) {
        GameAreaReq req = new GameAreaReq();
        req.setPlatformTerminal(4);
        req.setServerType(serviceType);

        mGameApi.getGameArea(req.params())
                .compose(RxUtil.<GameAreaResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<GameAreaResp>bindToLife())
                //.compose(RetrofitManager.<GameAreaResp>composeBackpressureError())
                .subscribe(new BaseSubscriber<GameAreaResp>() {
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
                    public void onNext(GameAreaResp gameAreaResp) {
                        if (gameAreaResp != null && gameAreaResp.isOk() && CollectionUtil.isNotEmpty(gameAreaResp.getListGameServerArea())) {
                            switch (serviceType) {
                                case State.ServiceType.NEW:
                                    mView.showNewGameArea(gameAreaResp.getListGameServerArea());
                                    break;
                                case State.ServiceType.FUTURE:
                                    mView.showFutureGameArea(gameAreaResp.getListGameServerArea());
                                    break;
                                default:
                                    break;
                            }
                        } else {
                            mView.onFailure();
                        }
                    }
                });
    }

}
