package com.hhly.lyygame.presentation.view.game;

import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.GameApi;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.client.BaseSubscriber;
import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.game.GameNoticeReq;
import com.hhly.lyygame.data.net.protocol.game.GameNoticeResp;
import com.hhly.lyygame.presentation.utils.CollectionUtil;
import com.hhly.lyygame.presentation.utils.TelephonyUtil;


/**
 * Created by ${HELY} on 17/2/16.
 * 邮箱：heli.lixiong@gmail.com
 */

public class NoticePresenterImpl implements GameContract.NoticePresenter {

    private final GameContract.NoticeView mView;
    private final GameApi mGameApi;

    public NoticePresenterImpl(GameContract.NoticeView view) {
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
    public void getGameNotice(int gameId, int noticeType) {
        GameNoticeReq req = new GameNoticeReq();
        req.setPlatformTerminal(TelephonyUtil.getOsTypeInt());
        req.setNoticeType(noticeType);
        req.setPlatformId(gameId);

        mGameApi.getGameNotice(req.params())
                .compose(RxUtil.<GameNoticeResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<GameNoticeResp>bindToLife())
                //.compose(RetrofitManager.<GameNoticeResp>composeBackpressureError())
                .subscribe(new BaseSubscriber<GameNoticeResp>() {
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
                    public void onNext(GameNoticeResp gameNoticeResp) {
                        if (gameNoticeResp != null && gameNoticeResp.isOk() && gameNoticeResp.getPager() != null && CollectionUtil.isNotEmpty(gameNoticeResp.getPager().getList())) {
                            mView.showGameNotice(gameNoticeResp.getPager().getList());
                        }
                    }
                });
    }

}
