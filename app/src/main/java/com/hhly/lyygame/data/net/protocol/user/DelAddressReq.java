package com.hhly.lyygame.data.net.protocol.user;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by ${HELY} on 17/2/13.
 * 邮箱：heli.lixiong@gmail.com
 */

public class DelAddressReq extends BaseReq {

    private Integer addressId;

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();

        if (addressId != null){
            params.put("addressId", String.valueOf(addressId));
        }

        return params;
    }
}
