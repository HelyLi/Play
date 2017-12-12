package com.hhly.lyygame.data.net.protocol.transfer;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by ${HELY} on 17/4/24.
 * 邮箱：heli.lixiong@gmail.com
 */

public class TransferRemitInfoReq extends BaseReq {

    private String fplatformId;
    private String tplatformId;

    public void setFplatformId(String fplatformId) {
        this.fplatformId = fplatformId;
    }

    public void setTplatformId(String tplatformId) {
        this.tplatformId = tplatformId;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();

        if (fplatformId != null){
            params.put("fplatformId", fplatformId);
        }
        if (tplatformId != null){
            params.put("tplatformId", tplatformId);
        }

        return params;
    }
}
