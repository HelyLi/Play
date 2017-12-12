package com.hhly.lyygame.data.net.protocol.goods;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by ${HELY} on 17/2/10.
 * 邮箱：heli.lixiong@gmail.com
 */

public class StoreGoodsReq extends BaseReq {

    private Integer enable;

    private String userId;

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    //country=0&getStoreGoods.enable=0&getStoreGoods.userId=hhly91599

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();

        if (enable != null){
            params.put("enable", String.valueOf(enable));
        }
        if (userId != null){
            params.put("userId", userId);
        }

        return params;
    }
}
