package com.hhly.lyygame.data.net.protocol.game;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by ${HELY} on 17/2/15.
 * 邮箱：heli.lixiong@gmail.com
 * 游戏图片
 */

public class GamePictureInfoReq extends BaseReq {

    private Integer imageType;

    private Integer platformId;

    private Integer platformTerminal;

    public void setPlatformTerminal(Integer platformTerminal) {
        this.platformTerminal = platformTerminal;
    }

    public void setImageType(Integer imageType) {
        this.imageType = imageType;
    }

    public void setGameId(Integer gameId) {
        this.platformId = gameId;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();

        if (imageType != null){
            params.put("imageType", String.valueOf(imageType));
        }
        if (platformId != null){
            params.put("platformId", String.valueOf(platformId));
        }
        if (platformTerminal != null){
            params.put("terminal", String.valueOf(platformTerminal));
        }

        return params;
    }
}
