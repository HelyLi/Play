package com.hhly.lyygame.presentation.view.notification;

import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.UserApi;
import com.hhly.lyygame.data.net.client.BaseSubscriber;
import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.user.MsgListReq;
import com.hhly.lyygame.data.net.protocol.user.MsgListResp;
import com.hhly.lyygame.data.net.protocol.user.NotificationActivityReq;
import com.hhly.lyygame.data.net.protocol.user.NotificationActivityResp;
import com.hhly.lyygame.presentation.utils.TelephonyUtil;
import com.orhanobut.logger.Logger;

/**
 * Created by ${HELY} on 17/2/23.
 * 邮箱：heli.lixiong@gmail.com
 */

public class NotificationPresenter implements NotificationContract.Presenter {

    private final NotificationContract.View mView;
    private final UserApi mUserApi;

    public NotificationPresenter(NotificationContract.View view) {
        mView = view;
        mUserApi = RetrofitManager.getInstance(ApiType.USER_API).getUserApi();
        mView.setPresenter(this);
    }

    @Override
    public void getNotificationActivity() {
        NotificationActivityReq req = new NotificationActivityReq();
        req.setIsAll(1);
        req.setType(1);
        req.setPlatform(TelephonyUtil.getOsTypeInt());

        mUserApi.getNotificationActivity(req.params())
                .compose(RxUtil.<NotificationActivityResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                //.compose(RetrofitManager.<NotificationActivityResp>composeBackpressureError())
                .compose(mView.<NotificationActivityResp>bindToLife())
                .subscribe(new BaseSubscriber<NotificationActivityResp>() {
                    @Override
                    protected void hideDialog() {

                    }

                    @Override
                    protected void showDialog() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        Logger.d(e.message + e.code);
                        mView.getNotificationsFail();
                    }

                    @Override
                    public void onNext(NotificationActivityResp resp) {
                        if (resp != null && resp.isOk()) {
                            mView.showNotifications(resp.getActivity());
                        } else {
                            mView.getNotificationsFail();
                        }
                    }
                });

    }

    @Override
    public void getMsgPage() {
        MsgListReq req = new MsgListReq();
        req.setPlateFormId(TelephonyUtil.getOsTypeInt());

        mUserApi.getMsgPage(req.params())
                .compose(RxUtil.<MsgListResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                //.compose(RetrofitManager.<MsgListResp>composeBackpressureError())
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
                        Logger.d(e.message + e.code);
                        mView.getMsgFail();
                    }

                    @Override
                    public void onNext(MsgListResp resp) {
                        if (resp != null && resp.isOk() && resp.getPager() != null) {
                            mView.showMsgs(resp.getPager().getList(), resp.getUnreads());
                        } else {
                            mView.getNotificationsFail();
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
