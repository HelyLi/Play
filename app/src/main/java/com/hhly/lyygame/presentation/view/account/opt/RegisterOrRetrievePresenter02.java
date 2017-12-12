package com.hhly.lyygame.presentation.view.account.opt;

import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.App;
import com.hhly.lyygame.data.db.entry.UserInfo;
import com.hhly.lyygame.data.db.manager.AccountManager;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.UserApi;
import com.hhly.lyygame.data.net.client.BaseSubscriber;
import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.user.ForgetPwdReq;
import com.hhly.lyygame.data.net.protocol.user.ForgetPwdResp;
import com.hhly.lyygame.data.net.protocol.user.LoginReq;
import com.hhly.lyygame.data.net.protocol.user.LoginResp;
import com.hhly.lyygame.data.net.protocol.user.RegisterReq;
import com.hhly.lyygame.data.net.protocol.user.RegisterResp;
import com.hhly.lyygame.data.net.protocol.user.State;
import com.hhly.lyygame.presentation.utils.Installation;
import com.hhly.lyygame.presentation.utils.MD5;
import com.hhly.lyygame.presentation.utils.TelephonyUtil;
import com.orhanobut.logger.Logger;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Predicate;


/**
 * Created by Simon on 16/9/8.
 */
public class RegisterOrRetrievePresenter02 implements RegisterOrRetrieveContract02.Presenter {

    private final RegisterOrRetrieveContract02.View mView;
    private UserApi mUserApi;

    public RegisterOrRetrievePresenter02(RegisterOrRetrieveContract02.View view) {
        mView = view;
        mUserApi = RetrofitManager.getInstance(ApiType.USER_API).getUserApi();
        mView.setPresenter(this);
    }

    @Override
    public void requestRegOrRetrieve(String phone, String pwd, String inviteCode, String smsCode, Integer operateType) {

        RegisterReq req = new RegisterReq();
        req.setAccount(phone);
        req.setPassword(new MD5(pwd).getMd5_32());
        req.setInviteCode(inviteCode);
        req.setType(State.RegisterType.PHONE);
        req.setChannelId(TelephonyUtil.getChannelId(App.getContext()));
        req.setPlatformId(TelephonyUtil.getPlatformId());
        req.setSmsCode(smsCode);
        req.setOperateType(operateType);

        Logger.d("register.req" + req);

        mUserApi.register(req.params())
                .compose(RxUtil.<RegisterResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<RegisterResp>bindToLife())
                .compose(RetrofitManager.<RegisterResp>composeBackpressureError())
                .subscribe(new BaseSubscriber<RegisterResp>() {
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
                        Logger.d("e=" + e.message + ",e.code=" + e.code);
                    }

                    @Override
                    public void onNext(RegisterResp registerResp) {
                        mView.dismissLoading();
                        mView.showMsg(registerResp != null ? registerResp.getMsg() : "");
                        if (registerResp != null && registerResp.isOk()) {
                            mView.onNext();
                        }
                    }
                });
    }

    @Override
    public void requestResetPassword(String phone, String pwd) {
        ForgetPwdReq req = new ForgetPwdReq();

        req.setPhone(phone);
        req.setNewPassword(new MD5(pwd).getMd5_32());
        req.setAccountType(State.LoginType.PHONE);
        mUserApi.forgetPwd(req.params())
                .compose(RxUtil.<ForgetPwdResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<ForgetPwdResp>bindToLife())
                //.compose(RetrofitManager.<ForgetPwdResp>composeBackpressureError())
                .subscribe(new BaseSubscriber<ForgetPwdResp>() {
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
                        Logger.d("e=" + e.message + ",e.code=" + e.code);
                    }

                    @Override
                    public void onNext(ForgetPwdResp forgetPwdResp) {
                        mView.dismissLoading();
                        mView.showMsg(forgetPwdResp.getMsg());
                        if (forgetPwdResp.isOk()) {
                            mView.onNext();
                        }
                    }
                });
    }

    @Override
    public void login(String account, final String pwd) {
        final LoginReq loginReq = new LoginReq();
        loginReq.setUserId(account);
        loginReq.setPassword(new MD5(pwd).getMd5_32());
        loginReq.setPlatformTerminal(String.valueOf(TelephonyUtil.getOsTypeInt()));
        loginReq.setChannelId(TelephonyUtil.getChannelId(App.getContext()));
        loginReq.setDeviceId(Installation.id(App.getContext()));

        mUserApi.login(loginReq.params())
                .compose(RxUtil.<LoginResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<LoginResp>bindToLife())
                .compose(RetrofitManager.<LoginResp>composeBackpressureError())
                .filter(new Predicate<LoginResp>() {
                    @Override
                    public boolean test(@NonNull LoginResp resp) throws Exception {
                        if (resp == null || resp.getResult() != State.OK) {
                            mView.dismissLoading();
                        }
                        return resp != null && resp.isOk();
                    }
                }).subscribe(new BaseSubscriber<LoginResp>() {
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
                mView.showMsg(e.message);
            }

            @Override
            public void onNext(LoginResp resp) {
                UserInfo extra = resp.getUser();
                extra.setToken(resp.getToken());
                AccountManager.getInstance().saveUserInfo(extra);
                mView.doLoginSuccess();
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
