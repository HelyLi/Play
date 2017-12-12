package com.hhly.lyygame.data.repository.userinfo;

import com.hhly.lyygame.data.db.entry.UserInfo;
import com.hhly.lyygame.data.db.manager.UserInfoOpe;
import com.hhly.lyygame.data.net.protocol.BaseReq;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by ${HELY} on 16/12/28.
 * 邮箱：heli.lixiong@gmail.com
 */

public class LocalUserInfoDataSource implements UserInfoDataSource {

    public LocalUserInfoDataSource() {

    }

    @Override
    public Flowable<UserInfo> getUserInfo(BaseReq req) {
        return Flowable.just(UserInfoOpe.getUserInfoSync(req.getToken())).subscribeOn(Schedulers.io());
    }

}
