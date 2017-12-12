package com.hhly.lyygame.data.net.protocol.user;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * 登录请求
 * Created by Simon on 2016/12/7.
 */

public class LoginReq extends BaseReq {

    private String userId;
    private String password;
    private String channelId;
    private String deviceId;
    private String platformTerminal;
    private Boolean autologin;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getPlatformTerminal() {
        return platformTerminal;
    }

    public void setPlatformTerminal(String platformTerminal) {
        this.platformTerminal = platformTerminal;
    }

    public Boolean getAutologin() {
        return autologin;
    }

    public void setAutologin(Boolean autologin) {
        this.autologin = autologin;
    }

    public Map<String, String> params() {
        final Map<String, String> maps = super.params();
        if (userId != null) {
            maps.put("account", userId);
        }
        if (password != null) {
            maps.put("password", password);
        }
        if (channelId != null) {
            maps.put("channelId", channelId);
        }
        if (deviceId != null) {
            maps.put("deviceId", deviceId);
        }
        if (platformTerminal != null) {
            maps.put("platformId", platformTerminal);
        }
        if (autologin != null){
            maps.put("autologin", String.valueOf(autologin));
        }
        return maps;
    }

    @Override
    public String toString() {
        return "LoginReq{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", channelId='" + channelId + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", platformTerminal='" + platformTerminal + '\'' +
                ", autologin=" + autologin +
                '}';
    }
}
