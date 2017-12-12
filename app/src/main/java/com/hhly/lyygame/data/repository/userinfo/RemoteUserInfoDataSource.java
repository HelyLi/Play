package com.hhly.lyygame.data.repository.userinfo;

import com.hhly.lyygame.data.db.entry.UserInfo;
import com.hhly.lyygame.data.db.manager.AccountManager;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.UserApi;
import com.hhly.lyygame.data.net.protocol.BaseReq;
import com.hhly.lyygame.data.net.protocol.user.GetUserInfoResp;
import com.orhanobut.logger.Logger;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by ${HELY} on 16/12/28.
 * 邮箱：heli.lixiong@gmail.com
 */

public class RemoteUserInfoDataSource implements UserInfoDataSource {

    private UserApi mUserApi;

    private volatile String mToken;

    public RemoteUserInfoDataSource() {
        this.mUserApi = RetrofitManager.getInstance(ApiType.USER_API).getUserApi();
    }

    @Override
    public Flowable<UserInfo> getUserInfo(final BaseReq req) {
        return mUserApi.getUserInfo(req.params())
                .compose(RetrofitManager.<GetUserInfoResp>composeBackpressureOther())
                .filter(new Predicate<GetUserInfoResp>() {
                    @Override
                    public boolean test(@NonNull GetUserInfoResp getUserInfoResp) throws Exception {
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
                        String token = AccountManager.getInstance().getToken();
                        extra.setToken(token);

                        AccountManager.getInstance().saveUserInfo(extra);
                        Logger.d("login.saveUserInfo");
                        return extra;
                    }
                });
    }
}
