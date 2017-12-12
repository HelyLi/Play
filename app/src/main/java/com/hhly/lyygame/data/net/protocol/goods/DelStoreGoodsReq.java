package com.hhly.lyygame.data.net.protocol.goods;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by ${HELY} on 17/3/5.
 * 邮箱：heli.lixiong@gmail.com
 */

public class DelStoreGoodsReq extends BaseReq {

    private Integer storeGoodsId;

    public void setStoreGoodsId(Integer storeGoodsId) {
        this.storeGoodsId = storeGoodsId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private String userId;

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();

        if (storeGoodsId != null){
            params.put("storeGoodsId", String.valueOf(storeGoodsId));
        }
        if (userId != null){
            params.put("userId", userId);
        }

        return params;
    }
}
