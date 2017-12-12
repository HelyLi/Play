package com.hhly.lyygame.data.repository;

import com.hhly.lyygame.App;
import com.hhly.lyygame.data.db.entry.UserInfo;
import com.hhly.lyygame.data.db.manager.AccountManager;
import com.hhly.lyygame.data.net.protocol.BaseReq;
import com.hhly.lyygame.data.repository.userinfo.LocalUserInfoDataSource;
import com.hhly.lyygame.data.repository.userinfo.RemoteUserInfoDataSource;
import com.hhly.lyygame.data.repository.userinfo.UserInfoDataSource;
import com.hhly.lyygame.presentation.utils.NetworkUtil;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by ${HELY} on 16/12/28.
 * 邮箱：heli.lixiong@gmail.com
 */

public class UserInfoRepository implements UserInfoDataSource {

    private final UserInfoDataSource local;
    private final UserInfoDataSource remote;

    public UserInfoRepository() {
        local = new LocalUserInfoDataSource();
        remote = new RemoteUserInfoDataSource();
    }

    @Override
    public Flowable<UserInfo> getUserInfo(final BaseReq req) {
        if (AccountManager.getInstance().getUserInfo() != null){
            return Flowable.just(AccountManager.getInstance().getUserInfo()).subscribeOn(Schedulers.io());
        }
        if (NetworkUtil.isAvailable(App.getContext())){
            return remote.getUserInfo(req);
        }
        return local.getUserInfo(req);
    }

}
