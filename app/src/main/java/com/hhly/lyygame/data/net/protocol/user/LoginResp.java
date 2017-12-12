package com.hhly.lyygame.data.net.protocol.user;

import com.hhly.lyygame.data.db.entry.UserInfo;
import com.hhly.lyygame.data.net.protocol.BaseResp;

/**
 * 登录响应
 * Created by Simon on 2016/12/7.
 */

public class LoginResp extends BaseResp {

    private UserInfo user;
    private String token;
    private Long forbiddenEndTime;

    public Long getForbiddenEndTime() {
        return forbiddenEndTime;
    }

    public void setForbiddenEndTime(Long forbiddenEndTime) {
        this.forbiddenEndTime = forbiddenEndTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "LoginResp{" +
                "user=" + user +
                ", token='" + token + '\'' +
                ", forbiddenEndTime=" + forbiddenEndTime +
                '}';
    }
}
