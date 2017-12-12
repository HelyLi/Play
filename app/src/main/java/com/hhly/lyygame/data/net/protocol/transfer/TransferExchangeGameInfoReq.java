package com.hhly.lyygame.data.net.protocol.transfer;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by ${HELY} on 17/4/24.
 * 邮箱：heli.lixiong@gmail.com
 */

public class TransferExchangeGameInfoReq extends BaseReq {

    private String platformId;

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();

        if (platformId != null) {
            params.put("platformId", platformId);
        }

        return params;
    }
}
