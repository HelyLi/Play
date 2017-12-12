package com.hhly.lyygame.data.net.protocol.user;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by ${HELY} on 17/3/2.
 * 邮箱：heli.lixiong@gmail.com
 */

public class TokenLoginReq extends BaseReq {

    private String token;
    private String deviceId;

    public void setToken(String token) {
        this.token = token;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getToken() {
        return token;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();

        if (token != null){
            params.put("token", token);
        }
        if (deviceId != null){
            params.put("deviceId", deviceId);
        }

        return params;
    }
}
