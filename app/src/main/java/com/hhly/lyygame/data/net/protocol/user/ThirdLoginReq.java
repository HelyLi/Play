package com.hhly.lyygame.data.net.protocol.user;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by ${HELY} on 17/3/2.
 * 邮箱：heli.lixiong@gmail.com
 */

public class ThirdLoginReq extends BaseReq {

    private String accessToken;

    private String deviceId;

    private Integer type;// qq:0;wechat:1;weibo:2;

    public void setAppId(String appId) {
        this.appId = appId;
    }

    /**
     * QQ和微信此处传appId , 微博传uid
     */
    private String appId;

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();

        if (accessToken != null) {
            params.put("accessToken", accessToken);
        }
        if (deviceId != null) {
            params.put("deviceId", deviceId);
        }
        if (type != null) {
            params.put("type", String.valueOf(type));
        }
        if (appId != null) {
            params.put("appId", appId);
        }

        return params;
    }


}
