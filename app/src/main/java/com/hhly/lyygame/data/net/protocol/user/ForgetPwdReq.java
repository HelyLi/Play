package com.hhly.lyygame.data.net.protocol.user;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by ${HELY} on 17/2/21.
 * 邮箱：heli.lixiong@gmail.com
 */

public class ForgetPwdReq extends BaseReq {
//    country=0&resetPassword.account=18688968616&resetPassword.accountType=3&resetPassword.newPassword=315EB115D98FCBAD39FFC5EDEBD669C9
    private String phone;
    private Integer accountType;
    private String newPassword;

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();

        if (phone != null){
            params.put("account", phone);
        }
        if (accountType != null){
            params.put("accountType", String.valueOf(accountType));
        }
        if (newPassword != null){
            params.put("newPassword", newPassword);
        }

        return params;
    }
}
