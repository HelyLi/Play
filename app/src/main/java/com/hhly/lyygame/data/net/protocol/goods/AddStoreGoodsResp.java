package com.hhly.lyygame.data.net.protocol.goods;

import com.hhly.lyygame.data.db.entry.UserInfo;
import com.hhly.lyygame.data.net.protocol.BaseResp;

/**
 * Created by ${HELY} on 17/2/28.
 * 邮箱：heli.lixiong@gmail.com
 */

public class AddStoreGoodsResp extends BaseResp {

    private UserInfo user;

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }
}
