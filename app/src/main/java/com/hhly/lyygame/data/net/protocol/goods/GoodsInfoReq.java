package com.hhly.lyygame.data.net.protocol.goods;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by ${HELY} on 17/2/20.
 * 邮箱：heli.lixiong@gmail.com
 */

public class GoodsInfoReq extends BaseReq {

    private Integer goodsId;

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    @Override
    public Map<String, String> params() {

        Map<String, String> params = super.params();
        if (goodsId != null){
            params.put("goodsId", String.valueOf(goodsId));
        }
        return params;
    }
}
