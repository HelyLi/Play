package com.hhly.lyygame.data.net.protocol.goods;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by ${HELY} on 17/2/28.
 * 邮箱：heli.lixiong@gmail.com
 */

public class CancelStoreGoodsReq extends BaseReq {

    private Integer goodsId;

    private String userId;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public Map<String, String> params() {
        Map<String , String> params = super.params();

        if (goodsId != null){
            params.put("goodsId", String.valueOf(goodsId));
        }
        if (userId != null){
            params.put("userId", userId);
        }

        return params;
    }
}
