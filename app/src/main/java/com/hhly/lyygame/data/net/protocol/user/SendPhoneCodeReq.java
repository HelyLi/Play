package com.hhly.lyygame.data.net.protocol.user;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by Simon on 2016/12/6.
 */

public class SendPhoneCodeReq extends BaseReq {

    private String phone;
    private Integer platformId;
    private Integer operateType;

    public SendPhoneCodeReq() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    public Integer getOperateType() {
        return operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SendPhoneCodeReq{");
        sb.append("phone='").append(phone).append('\'');
        sb.append(", platformId=").append(platformId);
        sb.append(", operateType=").append(operateType);
        sb.append('}');
        return sb.toString();
    }

    public Map<String, String> params() {
        final Map<String, String> maps = super.params();
        if (phone != null){
            maps.put("account", phone);
        }
        if (platformId!= null){
            maps.put("platformId", String.valueOf(platformId));
        }
        if (operateType != null){
            maps.put("operateType", String.valueOf(operateType));
        }
        return maps;
    }

}
