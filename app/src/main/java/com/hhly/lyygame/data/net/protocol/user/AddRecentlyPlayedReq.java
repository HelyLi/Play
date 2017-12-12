package com.hhly.lyygame.data.net.protocol.user;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by ${HELY} on 17/3/27.
 * 邮箱：heli.lixiong@gmail.com
 */

public class AddRecentlyPlayedReq extends BaseReq {

    Integer gameId;
    
    Integer terminal;

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public void setTerminal(Integer terminal) {
        this.terminal = terminal;
    }

    @Override
    public Map<String, String> params() {

        Map<String, String> params = super.params();

        if (getCountry() != null){
            params.put("country", String.valueOf(getCountry()));
        }

        if (gameId != null){
            params.put("gameId", String.valueOf(gameId));
        }

        if (terminal != null){
            params.put("terminal", String.valueOf(terminal));
        }

        return params;
    }
}
