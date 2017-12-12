package com.hhly.lyygame.data.net.protocol.game;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by ${HELY} on 17/2/17.
 * 邮箱：heli.lixiong@gmail.com
 */

public class GameByTypeReq extends BaseReq {

    private Integer pageSize;

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setGameType(Integer gameType) {
        this.gameType = gameType;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    private Integer gameType;

    private Integer terminal;

    public void setTerminal(Integer terminal) {
        this.terminal = terminal;
    }

    private Integer pageNo;

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();

        if (pageSize != null) {
            params.put("pageSize", String.valueOf(pageSize));
        }
        if (gameType != null) {
            params.put("gameType", String.valueOf(gameType));
        }
        if (terminal != null) {
            params.put("terminal", String.valueOf(terminal));
        }
        if (pageNo != null) {
            params.put("pageNo", String.valueOf(pageNo));
        }

        return params;
    }
}
