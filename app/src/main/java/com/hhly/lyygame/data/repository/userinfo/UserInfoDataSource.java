package com.hhly.lyygame.data.repository.userinfo;

import com.hhly.lyygame.data.db.entry.UserInfo;
import com.hhly.lyygame.data.net.protocol.BaseReq;

import io.reactivex.Flowable;

/**
 * Created by ${HELY} on 16/12/28.
 * 邮箱：heli.lixiong@gmail.com
 */

public interface UserInfoDataSource {

    Flowable<UserInfo> getUserInfo(final BaseReq req);

}
