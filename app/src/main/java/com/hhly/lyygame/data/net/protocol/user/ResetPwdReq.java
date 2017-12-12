package com.hhly.lyygame.data.net.protocol.user;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by ${HELY} on 16/12/22.
 * 邮箱：heli.lixiong@gmail.com
 */

public class ResetPwdReq extends BaseReq {

//    Integer country;
    String oldPassword;
    String password;

//    public Integer getCountry() {
//        return country;
//    }

//    public void setCountry(Integer country) {
//        this.country = country;
//    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ResetPwdReq{" +
                ", oldPassword='" + oldPassword + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();

        if (oldPassword != null){
            params.put("oldPassword", oldPassword);
        }
        if (password != null){
            params.put("password", password);
        }

        return params;
    }
}
