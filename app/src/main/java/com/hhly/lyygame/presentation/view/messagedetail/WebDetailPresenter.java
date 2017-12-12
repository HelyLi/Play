package com.hhly.lyygame.presentation.view.messagedetail;

import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.GameApi;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.UserApi;
import com.hhly.lyygame.data.net.client.BaseSubscriber;
import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.game.NewsDetailsReq;
import com.hhly.lyygame.data.net.protocol.game.NewsDetailsResp;
import com.hhly.lyygame.data.net.protocol.game.NoticeDetailReq;
import com.hhly.lyygame.data.net.protocol.user.MsgInfoReq;
import com.hhly.lyygame.data.net.protocol.user.MsgInfoResp;
import com.hhly.lyygame.data.net.protocol.user.MsgStateReq;
import com.hhly.lyygame.data.net.protocol.user.MsgStateResp;
import com.orhanobut.logger.Logger;

/**
 * Created by HELY on 17/2/9.
 * 邮箱：heli.lixiong@gmail.com
 */

public class WebDetailPresenter implements WebDetailContract.Presenter {

    private final WebDetailContract.View mView;
    private final UserApi mUserApi;
    private final GameApi mGameApi;

    WebDetailPresenter(WebDetailContract.View view) {
        mView = view;
        mView.setPresenter(this);
        mUserApi = RetrofitManager.getInstance(ApiType.USER_API).getUserApi();
        mGameApi = RetrofitManager.getInstance(ApiType.GAME_API).getGameApi();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void getMessageDetail(int msgId) {
        MsgInfoReq req = new MsgInfoReq();
        req.setMsgId(msgId);

        mUserApi.getMsgInfo(req.params())
                .compose(RxUtil.<MsgInfoResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                //.compose(RetrofitManager.<MsgInfoResp>composeBackpressureError())
                .compose(mView.<MsgInfoResp>bindToLife())
                .subscribe(new BaseSubscriber<MsgInfoResp>() {
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
                    public void onNext(MsgInfoResp msgInfoResp) {
                        if (msgInfoResp != null && msgInfoResp.isOk()){
                            mView.showMessageDetail(msgInfoResp.getUserMessage());
                        }
                    }
                });

    }

    @Override
    public void updateMessage(int msgId) {
        MsgStateReq req = new MsgStateReq();
        req.setMsgId(msgId);

        mUserApi.updateUserMsg(req.params())
                .compose(RxUtil.<MsgStateResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                //.compose(RetrofitManager.<MsgStateResp>composeBackpressureError())
                .compose(mView.<MsgStateResp>bindToLife())
                .subscribe(new BaseSubscriber<MsgStateResp>() {
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
                    public void onNext(MsgStateResp msgStateResp) {
                        if (msgStateResp != null && msgStateResp.isOk()){
                            mView.updateMessageSuccess();
                        }
                    }
                });

    }

    @Override
    public void getActivityDetail(int activityId) {
        NewsDetailsReq req = new NewsDetailsReq();
        req.setNoticeId(activityId);

        mGameApi.getNewsDetails(req.params())
                .compose(RxUtil.<NewsDetailsResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                //.compose(RetrofitManager.<NewsDetailsResp>composeBackpressureError())
                .compose(mView.<NewsDetailsResp>bindToLife())
                .subscribe(new BaseSubscriber<NewsDetailsResp>() {
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
                    public void onNext(NewsDetailsResp newsDetailsResp) {
                        if (newsDetailsResp != null && newsDetailsResp.isOk()){
                            mView.showActivityDetail(newsDetailsResp.getNotice());
                        }
                    }
                });

    }

    @Override
    public void getNoticeDetail(int noticyId) {
        NoticeDetailReq req = new NoticeDetailReq();
        req.setNoticeId(noticyId);

        mGameApi.getGameNotieById(req.params())
                .compose(RxUtil.<NewsDetailsResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                //.compose(RetrofitManager.<NewsDetailsResp>composeBackpressureError())
                .compose(mView.<NewsDetailsResp>bindToLife())
                .subscribe(new BaseSubscriber<NewsDetailsResp>() {
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
                    public void onNext(NewsDetailsResp newsDetailsResp) {
                        if (newsDetailsResp != null && newsDetailsResp.isOk()){
                            mView.showNoticeDetail(newsDetailsResp.getNotice());
                        }
                    }
                });

    }


}
