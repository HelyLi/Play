package com.hhly.lyygame.data.net.protocol.user;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * 注册请求
 * Created by Simon on 2016/12/6.
 */

public class RegisterReq extends BaseReq {

    private String account;
    private String password;
    private Integer type;//用户名，手机，邮箱
    private Integer platformId;//平台id
    private String channelId;//渠道id
    private String smsCode;//短信验证码
    private String inviteCode;//邀请码
    private Integer operateType;//操作类型1手机注册2绑定手机3修改登录密码4忘记密码

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public Integer getOperateType() {
        return operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @Override
    public String toString() {
        return "RegisterReq{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                ", platformId=" + platformId +
                ", channelId='" + channelId + '\'' +
                ", smsCode='" + smsCode + '\'' +
                ", inviteCode='" + inviteCode + '\'' +
                ", operateType=" + operateType +
                '}';
    }

    @Override
    public Map<String, String> params() {
        final Map<String, String> maps = super.params();
        if (account != null) {
            maps.put("account", account);
        }
        if (password != null) {
            maps.put("password", password);
        }
        if (type != null) {
            maps.put("type", String.valueOf(type));
        }
        if (platformId != null) {
            maps.put("platformId", String.valueOf(platformId));
        }
        if (smsCode != null) {
            maps.put("smsCode", smsCode);
        }
        if (inviteCode != null) {
            maps.put("partnerNo", inviteCode);
        }
        if (operateType != null) {
            maps.put("operateType", String.valueOf(operateType));
        }
        if (channelId != null){
            maps.put("channelId", channelId);
        }
        return maps;
    }

}
