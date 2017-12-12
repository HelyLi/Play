package com.hhly.lyygame.data.net.protocol.transfer;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by ${HELY} on 17/4/24.
 * 邮箱：heli.lixiong@gmail.com
 */

public class TransferFindSonPlatformBalanceReq extends BaseReq {

    private String userId;
    private String platformId;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();

        if (userId != null){
            params.put("userId", userId);
        }
        if (platformId != null){
            params.put("platformId", platformId);
        }

        return params;
    }
}
