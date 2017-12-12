package com.hhly.lyygame.presentation.view.signin;

import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.UserApi;
import com.hhly.lyygame.data.net.client.BaseSubscriber;
import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.user.MonthSignReq;
import com.hhly.lyygame.data.net.protocol.user.MonthSignResp;
import com.hhly.lyygame.data.net.protocol.user.UserSignReq;
import com.hhly.lyygame.data.net.protocol.user.UserSignResp;
import com.orhanobut.logger.Logger;

/**
 * Created by ${HELY} on 17/2/6.
 * 邮箱：heli.lixiong@gmail.com
 */

public class SignInPresenter implements SignInContract.Presenter {

    SignInContract.View mView;
    UserApi mUserApi;

    public SignInPresenter(SignInContract.View view) {
        this.mView = view;
        mUserApi = RetrofitManager.getInstance(ApiType.USER_API).getUserApi();
        this.mView.setPresenter(this);
    }

    @Override
    public void getSignInInfo() {
        MonthSignReq req = new MonthSignReq();

        mUserApi.queryMonthSign(req.params())
                .compose(RxUtil.<MonthSignResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(RetrofitManager.<MonthSignResp>composeBackpressureOther())
                .compose(RetrofitManager.<MonthSignResp>composeBackpressureError())
                .compose(mView.<MonthSignResp>bindToLife())
                .subscribe(new BaseSubscriber<MonthSignResp>() {
                    @Override
                    protected void hideDialog() {
                        mView.dismissLoading();
                    }

                    @Override
                    protected void showDialog() {
                        mView.showLoading();
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        Logger.d(e.message + e.code);
                    }

                    @Override
                    public void onNext(MonthSignResp monthSignResp) {
                        mView.dismissLoading();
                        if (monthSignResp != null && monthSignResp.isOk()) {
                            mView.showSignInfo(monthSignResp.getContinueDay(), monthSignResp.getDay());
                        } else {
                            mView.showMsg(monthSignResp != null ? monthSignResp.getMsg() : "");
                        }
                    }
                });

    }

    @Override
    public void signIn(int day) {
        UserSignReq req = new UserSignReq();

        mUserApi.userSign(req.params())
                .compose(RxUtil.<UserSignResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(RetrofitManager.<UserSignResp>composeBackpressureOther())
                .compose(RetrofitManager.<UserSignResp>composeBackpressureError())
                .compose(mView.<UserSignResp>bindToLife())
                .subscribe(new BaseSubscriber<UserSignResp>() {
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
                    public void onNext(UserSignResp userSignResp) {
                        if (userSignResp != null && userSignResp.isOk()) {
                            mView.signInSuccess(userSignResp.getPoint());
                        } else {
                            mView.showMsg(userSignResp != null ? userSignResp.getMsg() : "");
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
