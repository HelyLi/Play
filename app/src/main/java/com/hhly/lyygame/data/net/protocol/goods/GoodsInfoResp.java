package com.hhly.lyygame.data.net.protocol.goods;

import com.hhly.lyygame.data.model.GoodsInfo;
import com.hhly.lyygame.data.net.protocol.BaseResp;

/**
 * Created by ${HELY} on 17/2/20.
 * 邮箱：heli.lixiong@gmail.com
 */

public class GoodsInfoResp extends BaseResp {

    private GoodsInfo goods;

    public GoodsInfo getGoods() {
        return goods;
    }

    public void setGoods(GoodsInfo goods) {
        this.goods = goods;
    }

}
