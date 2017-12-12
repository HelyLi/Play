package com.hhly.lyygame.presentation.view.game;

import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.data.db.manager.AccountManager;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.GameApi;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.client.BaseSubscriber;
import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.game.GiftBagReq;
import com.hhly.lyygame.data.net.protocol.game.GiftBagResp;
import com.hhly.lyygame.presentation.utils.CollectionUtil;


/**
 * Created by ${HELY} on 17/2/16.
 * 邮箱：heli.lixiong@gmail.com
 */

public class GiftPresenterImpl implements GameContract.GiftPresenter {

    private final GameContract.GiftView mView;
    private final GameApi mGameApi;

    public GiftPresenterImpl(GameContract.GiftView view) {
        mView = view;
        mGameApi = RetrofitManager.getInstance(ApiType.GAME_API).getGameApi();
        mView.setPresenter(this);
    }

    @Override
    public void getGameGift(int gameId) {
        GiftBagReq req = new GiftBagReq();
        req.setGameId(gameId);
        req.setUserId(AccountManager.getInstance().getUserId());

        mGameApi.getGiftBag(req.params())
                .compose(RxUtil.<GiftBagResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<GiftBagResp>bindToLife())
                //.compose(RetrofitManager.<GiftBagResp>composeBackpressureError())
                .subscribe(new BaseSubscriber<GiftBagResp>() {
                    @Override
                    protected void hideDialog() {

                    }

                    @Override
                    protected void showDialog() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        mView.onFailureGift();
                    }

                    @Override
                    public void onNext(GiftBagResp giftBagResp) {
                        if (giftBagResp != null && giftBagResp.isOk() && CollectionUtil.isNotEmpty(giftBagResp.getGiftBag())) {
                            mView.showGameGift(giftBagResp.getGiftBag());
                        } else {
                            mView.onFailureGift();
                        }
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
