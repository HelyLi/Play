package com.hhly.lyygame.data.net.protocol.game;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by ${HELY} on 17/2/15.
 * 邮箱：heli.lixiong@gmail.com
 */

public class GameByIdInfoReq extends BaseReq {

    private Integer platformId;

    private String channelId;

    public void setGameId(Integer gameId) {
        this.platformId = gameId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();

        if (platformId != null) {
            params.put("platformId", String.valueOf(platformId));
        }
        if (channelId != null){
            params.put("channelId", channelId);
        }

        return params;
    }
}
