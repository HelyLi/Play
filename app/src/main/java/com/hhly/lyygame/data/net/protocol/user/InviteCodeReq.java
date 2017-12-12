package com.hhly.lyygame.data.net.protocol.user;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by ${HELY} on 17/3/4.
 * 邮箱：heli.lixiong@gmail.com
 */

public class InviteCodeReq extends BaseReq {

    private String userId;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();

        if (userId != null){
            params.put("userId", userId);
        }

        return params;
    }
}
