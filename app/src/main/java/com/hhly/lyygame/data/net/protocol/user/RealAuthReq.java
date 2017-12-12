package com.hhly.lyygame.data.net.protocol.user;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by ${HELY} on 17/2/7.
 * 邮箱：heli.lixiong@gmail.com
 */

public class RealAuthReq extends BaseReq {

    private String realname;

    private String idcardNo;

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getIdcardNo() {
        return idcardNo;
    }

    public void setIdcardNo(String idcardNo) {
        this.idcardNo = idcardNo;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();

        if (realname != null){
            params.put("realname", realname);
        }
        if (idcardNo != null){
            params.put("idcardNo", idcardNo);
        }

        return params;
    }

    @Override
    public String toString() {
        return "RealAuthReq{" +
                "realname='" + realname + '\'' +
                ", idcardNo='" + idcardNo + '\'' +
                '}';
    }
}
