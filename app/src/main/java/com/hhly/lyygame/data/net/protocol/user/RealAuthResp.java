package com.hhly.lyygame.data.net.protocol.user;

import com.hhly.lyygame.data.db.entry.UserInfo;
import com.hhly.lyygame.data.net.protocol.BaseResp;

/**
 * Created by ${HELY} on 17/2/7.
 * 邮箱：heli.lixiong@gmail.com
 */

public class RealAuthResp extends BaseResp {

    private UserInfo user;

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

}
