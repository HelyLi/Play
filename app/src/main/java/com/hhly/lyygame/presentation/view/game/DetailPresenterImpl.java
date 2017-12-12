package com.hhly.lyygame.presentation.view.game;

import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.GameApi;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.client.BaseSubscriber;
import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.game.GameByIdInfoReq;
import com.hhly.lyygame.data.net.protocol.game.GameByIdInfoResp;
import com.hhly.lyygame.data.net.protocol.game.GamePictureInfoReq;
import com.hhly.lyygame.data.net.protocol.game.GamePictureInfoResp;
import com.hhly.lyygame.data.repository.GameRepository;
import com.hhly.lyygame.presentation.utils.CollectionUtil;
import com.hhly.lyygame.presentation.utils.TelephonyUtil;

/**
 * Created by ${HELY} on 17/2/16.
 * 邮箱：heli.lixiong@gmail.com
 */

public class DetailPresenterImpl implements GameContract.DetailPresenter {

    final GameContract.DetailView mView;
    private final GameRepository mGameRepository;
    private final GameApi mGameApi;

    public DetailPresenterImpl(GameContract.DetailView view){
        mView = view;
        mGameRepository = new GameRepository();
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
    public void getGamePic(final int gameId, int imageType) {
        GamePictureInfoReq req = new GamePictureInfoReq();
        req.setPlatformTerminal(TelephonyUtil.getOsTypeInt());
        req.setGameId(gameId);
        req.setImageType(imageType);

        mGameApi.getGamePictureInfo(req.params())
                .compose(RxUtil.<GamePictureInfoResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<GamePictureInfoResp>bindToLife())
                //.compose(RetrofitManager.<GamePictureInfoResp>composeBackpressureError())
                .subscribe(new BaseSubscriber<GamePictureInfoResp>() {
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
                    public void onNext(GamePictureInfoResp gamePictureInfoResp) {
                        if (gamePictureInfoResp != null && gamePictureInfoResp.isOk() && gamePictureInfoResp.getPager() != null && CollectionUtil.isNotEmpty(gamePictureInfoResp.getPager().getList())){
                            mView.showGamePic(gamePictureInfoResp.getPager().getList());
                        }
                    }
                });

    }

    @Override
    public void getGameByIdInfo(int gameId) {
        GameByIdInfoReq req = new GameByIdInfoReq();
        req.setGameId(gameId);
        //mGameApi.getGameById
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
