package com.hhly.lyygame.data.net.protocol.user;

import com.hhly.lyygame.data.db.entry.UserInfo;
import com.hhly.lyygame.data.net.protocol.BaseResp;

/**
 * Created by ${HELY} on 17/1/3.
 * 邮箱：heli.lixiong@gmail.com
 */

public class UpdateUserInfoResp extends BaseResp {

    private UserInfo user;

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UpdateUserInfoResp{" +
                "user=" + user +
                '}';
    }
}
