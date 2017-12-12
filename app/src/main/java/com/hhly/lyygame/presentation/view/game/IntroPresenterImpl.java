package com.hhly.lyygame.presentation.view.game;

import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.App;
import com.hhly.lyygame.data.net.client.BaseSubscriber;
import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.game.GameByIdInfoReq;
import com.hhly.lyygame.data.net.protocol.game.GameByIdInfoResp;
import com.hhly.lyygame.data.repository.GameRepository;
import com.hhly.lyygame.presentation.utils.TelephonyUtil;

/**
 * Created by ${HELY} on 17/2/16.
 * 邮箱：heli.lixiong@gmail.com
 */

public class IntroPresenterImpl implements GameContract.IntroPresenter {

    final GameContract.IntrolView mView;
    final GameRepository mGameRepository;

    public IntroPresenterImpl(GameContract.IntrolView view) {
        mView = view;
        mGameRepository = new GameRepository();
        mView.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void getGameByIdInfo(int gameId) {
        GameByIdInfoReq req = new GameByIdInfoReq();
        req.setChannelId(TelephonyUtil.getChannelId(App.getContext()));
        req.setGameId(gameId);

        mGameRepository.getGameById(req.params())
                .compose(RxUtil.<GameByIdInfoResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<GameByIdInfoResp>bindToLife())
                //.compose(RetrofitManager.<GameByIdInfoResp>composeBackpressureError())
                .subscribe(new BaseSubscriber<GameByIdInfoResp>() {
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
                    public void onNext(GameByIdInfoResp gameByIdInfoResp) {
                        if (gameByIdInfoResp != null && gameByIdInfoResp.isOk()) {
                            mView.showGameInfo(gameByIdInfoResp.getData());
                        }
                    }
                });

    }

}
