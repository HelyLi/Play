package com.hhly.lyygame.data.net.protocol.user;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by ${HELY} on 17/2/10.
 * 邮箱：heli.lixiong@gmail.com
 */

public class UserAddressReq extends BaseReq {
//country=0&queryReq2.userId=hhly91480

//    private Integer country;
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();

        if (userId != null){
            params.put("userId", userId);
        }

        return params;
    }
}
