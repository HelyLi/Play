package com.hhly.lyygame.data.net.protocol.game;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by ${HELY} on 17/2/18.
 * 邮箱：heli.lixiong@gmail.com
 */

public class ExchangeGiftBagReq extends BaseReq {

    private Integer giftBag;

    private String userId;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setGiftBag(Integer giftBag) {
        this.giftBag = giftBag;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();

        if (giftBag != null){
            params.put("giftBag", String.valueOf(giftBag));
        }
        if (userId != null){
            params.put("userId", userId);
        }

        return params;
    }
}
