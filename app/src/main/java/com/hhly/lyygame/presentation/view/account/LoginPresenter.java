package com.hhly.lyygame.presentation.view.account;

import android.text.TextUtils;

import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.App;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.db.entry.UserInfo;
import com.hhly.lyygame.data.db.manager.AccountManager;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.UserApi;
import com.hhly.lyygame.data.net.client.BaseSubscriber;
import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.BaseReq;
import com.hhly.lyygame.data.net.protocol.user.GetUserInfoResp;
import com.hhly.lyygame.data.net.protocol.user.LoginReq;
import com.hhly.lyygame.data.net.protocol.user.LoginResp;
import com.hhly.lyygame.data.net.protocol.user.State;
import com.hhly.lyygame.data.net.protocol.user.ThirdLoginReq;
import com.hhly.lyygame.presentation.utils.Installation;
import com.hhly.lyygame.presentation.utils.MD5;
import com.hhly.lyygame.presentation.utils.NetworkUtil;
import com.hhly.lyygame.presentation.utils.TelephonyUtil;
import com.hhly.lyygame.presentation.view.ToastUtil;

import org.reactivestreams.Publisher;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Simon on 2016/12/7.
 */

public class LoginPresenter implements LoginContact.Presenter {

    private final LoginContact.View mView;
    private final UserApi mUserApi;

    public LoginPresenter(LoginContact.View view) {
        mView = view;
        mView.setPresenter(this);
        mUserApi = RetrofitManager.getInstance(ApiType.USER_API).getUserApi();
    }

    @Override
    public void login(String account, final String pwd) {
        if (!NetworkUtil.isAvailable(App.getContext())) {
            ToastUtil.showTip(App.getContext(), R.string.lyy_network_notwork);
            return;
        }
        final LoginReq loginReq = new LoginReq();

        loginReq.setUserId(account);
        loginReq.setPassword(new MD5(pwd).getMd5_32());
        loginReq.setPlatformTerminal(String.valueOf(TelephonyUtil.getOsTypeInt()));
        loginReq.setChannelId(TelephonyUtil.getChannelId(App.getContext()));
        loginReq.setDeviceId(Installation.id(App.getContext()));

        mUserApi.login(loginReq.params())
                .compose(RxUtil.<LoginResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<LoginResp>bindToLife())
                .subscribeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<LoginResp>() {
                    @Override
                    public boolean test(@NonNull LoginResp loginResp) throws Exception {
                        if (loginResp == null || (loginResp.getResult() != State.OK && loginResp.getResult() != 401 && loginResp.getResult() != 402)) {
                            mView.dismissLoading();
                            mView.showMsg(loginResp == null? "" : loginResp.getMsg());
                        }
                        return loginResp != null && (loginResp.getResult() == State.OK || loginResp.getResult() == 401 || loginResp.getResult() == 402);
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Function<LoginResp, BaseReq>() {
                    @Override
                    public BaseReq apply(@NonNull LoginResp loginResp) throws Exception {
                        mToken = loginResp.getToken();
                        BaseReq req = new BaseReq();
                        req.setToken(mToken);
                        return req;
                    }
                })
                .flatMap(new Function<BaseReq, Publisher<GetUserInfoResp>>() {
                    @Override
                    public Publisher<GetUserInfoResp> apply(@NonNull BaseReq req) throws Exception {
                        return mUserApi.getUserInfo(req.params());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<GetUserInfoResp>() {
                    @Override
                    public boolean test(@NonNull GetUserInfoResp getUserInfoResp) throws Exception {
                        if (getUserInfoResp != null && getUserInfoResp.getResult() != State.OK) {
                            mView.dismissLoading();
                            mView.showMsg(getUserInfoResp.getMsg());
                        }
                        return getUserInfoResp != null && getUserInfoResp.isOk();
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Function<GetUserInfoResp, UserInfo>() {
                    @Override
                    public UserInfo apply(@NonNull GetUserInfoResp getUserInfoResp) throws Exception {
                        UserInfo extra = getUserInfoResp.getUser();

                        extra.setId(getUserInfoResp.getUser().getId());
                        extra.setJf(getUserInfoResp.getJf());
                        extra.setBindFlag(getUserInfoResp.getBindFlag());
                        extra.setLyb(getUserInfoResp.getLyb());
                        extra.setLyq(getUserInfoResp.getLyq());
                        extra.setPaypwdFlag(getUserInfoResp.getPaypwdFlag());
                        extra.setSafeLevel(getUserInfoResp.getSafeLevel());
                        extra.setToken(mToken);

                        AccountManager.getInstance().saveUserInfo(extra);
                        return extra;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<UserInfo>() {
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
                    public void onNext(UserInfo userInfo) {
                        mView.dismissLoading();
                        mView.doLoginSuccess();
                    }
                });

    }

    private volatile String mToken;

    @Override
    public void thirdLogin(String accessToken, int type, String appId) {

        ThirdLoginReq req = new ThirdLoginReq();
        req.setAccessToken(accessToken);
        req.setDeviceId(Installation.id(App.getContext()));
        req.setType(type);
        req.setAppId(appId);

        mUserApi.thirdLogin(req.params())
                .compose(RxUtil.<LoginResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<LoginResp>bindToLife())
                //.compose(RetrofitManager.<LoginResp>composeBackpressureError())
                .filter(new Predicate<LoginResp>() {
                    @Override
                    public boolean test(@NonNull LoginResp loginResp) throws Exception {

                        if (loginResp.getResult() != State.OK) {
                            mView.showMsg(loginResp.getMsg());
                        }

                        return loginResp.isOk() && !TextUtils.isEmpty(loginResp.getToken());
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Function<LoginResp, UserInfo>() {
                    @Override
                    public UserInfo apply(@NonNull LoginResp loginResp) throws Exception {

                        mToken = loginResp.getToken();
                        return loginResp.getUser();
                    }
                })
                .map(new Function<UserInfo, BaseReq>() {
                    @Override
                    public BaseReq apply(@NonNull UserInfo userInfo) throws Exception {
                        BaseReq req = new BaseReq();
                        return req;
                    }
                })
                .flatMap(new Function<BaseReq, Publisher<GetUserInfoResp>>() {
                    @Override
                    public Publisher<GetUserInfoResp> apply(@NonNull BaseReq req) throws Exception {
                        return mUserApi.getUserInfo(req.params());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<GetUserInfoResp>() {
                    @Override
                    public boolean test(@NonNull GetUserInfoResp getUserInfoResp) throws Exception {
                        return getUserInfoResp.getResult() == State.OK;
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Function<GetUserInfoResp, UserInfo>() {
                    @Override
                    public UserInfo apply(@NonNull GetUserInfoResp getUserInfoResp) throws Exception {
                        UserInfo extra = getUserInfoResp.getUser();

                        extra.setId(getUserInfoResp.getUser().getId());
                        extra.setJf(getUserInfoResp.getJf());
                        extra.setBindFlag(getUserInfoResp.getBindFlag());
                        extra.setLyb(getUserInfoResp.getLyb());
                        extra.setLyq(getUserInfoResp.getLyq());
                        extra.setPaypwdFlag(getUserInfoResp.getPaypwdFlag());
                        extra.setSafeLevel(getUserInfoResp.getSafeLevel());
                        extra.setToken(mToken);

                        AccountManager.getInstance().saveUserInfo(extra);
                        return extra;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<UserInfo>() {
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
                        mView.showMsg(R.string.lyy_network_exception);
                    }

                    @Override
                    public void onNext(UserInfo userInfo) {
                        mView.dismissLoading();
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
