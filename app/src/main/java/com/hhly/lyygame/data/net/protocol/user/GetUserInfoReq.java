package com.hhly.lyygame.data.net.protocol.user;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by ${HELY} on 16/12/28.
 * 邮箱：heli.lixiong@gmail.com
 */

public class GetUserInfoReq extends BaseReq{

    public GetUserInfoReq(String userId){
        this.userId = userId;
    }

    private String userId;

    public String getUserId() {
        return userId;
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
