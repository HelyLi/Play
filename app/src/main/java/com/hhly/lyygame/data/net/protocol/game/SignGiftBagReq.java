package com.hhly.lyygame.data.net.protocol.game;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by ${HELY} on 17/2/18.
 * 邮箱：heli.lixiong@gmail.com
 * 礼包详情
 */

public class SignGiftBagReq extends BaseReq {

    private Integer giftBag;

    public void setGiftBag(Integer giftBag) {
        this.giftBag = giftBag;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();

        if (giftBag != null){
            params.put("giftBag", String.valueOf(giftBag));
        }

        return params;
    }

    @Override
    public String toString() {
        return "SignGiftBagReq{" +
                "giftBag=" + giftBag +
                '}';
    }
}
