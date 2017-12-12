package com.hhly.lyygame.data.net.protocol.user;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by Simon on 2016/12/6.
 */

public class ValidatePhoneCodeReq extends BaseReq {

    private String phone;
    private String smsCode;
    private Integer platformId;
    private Integer type;
    private Integer operateType;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public Integer getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getOperateType() {
        return operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ValidatePhoneCodeReq{");
        sb.append("phone='").append(phone).append('\'');
        sb.append(", smsCode='").append(smsCode).append('\'');
        sb.append(", platformId=").append(platformId);
        sb.append(", type=").append(type);
        sb.append(", operateType=").append(operateType);
        sb.append('}');
        return sb.toString();
    }

    public Map<String, String> params() {
        final Map<String, String> maps = super.params();
        if (phone != null) {
            maps.put("account", phone);
        }
        if (smsCode != null) {
            maps.put("smsCode", smsCode);
        }
        if (platformId != null) {
            maps.put("platformId", String.valueOf(platformId));
        }
        if (type != null) {
            maps.put("type", String.valueOf(type));
        }
        if (operateType != null) {
            maps.put("operateType", String.valueOf(operateType));
        }
        return maps;
    }
}
