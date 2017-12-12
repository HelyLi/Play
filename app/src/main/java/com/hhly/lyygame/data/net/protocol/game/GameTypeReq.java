package com.hhly.lyygame.data.net.protocol.game;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by ${HELY} on 17/1/3.
 * 邮箱：heli.lixiong@gmail.com
 * 游戏类型
 */

public class GameTypeReq extends BaseReq {

    private Integer dataSize;

    public void setDataSize(Integer dataSize) {
        this.dataSize = dataSize;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();

        if (dataSize != null) {
            params.put("pageSize", String.valueOf(dataSize));
        }

        return params;
    }
}
