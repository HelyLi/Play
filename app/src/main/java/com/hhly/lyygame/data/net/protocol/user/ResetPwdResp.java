package com.hhly.lyygame.data.net.protocol.user;

import com.hhly.lyygame.data.db.entry.UserInfo;
import com.hhly.lyygame.data.net.protocol.BaseResp;

/**
 * Created by ${HELY} on 16/12/22.
 * 邮箱：heli.lixiong@gmail.com
 */

public class ResetPwdResp extends BaseResp {

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
