package com.hhly.lyygame.data.net.protocol.game;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by ${HELY} on 17/1/3.
 * 邮箱：heli.lixiong@gmail.com
 */
//http://mgame.1332255.com:9030/web/core/user.getRecentlyPlayedList.do?country=0&terminalsType=4
public class RecentlyGameListReq extends BaseReq {

    private Integer terminalsType;

    public Integer getTerminalsType() {
        return terminalsType;
    }

    public void setTerminalsType(Integer terminalsType) {
        this.terminalsType = terminalsType;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();

        if (terminalsType != null){
            params.put("terminal", String.valueOf(terminalsType));
        }

        return params;
    }

    @Override
    public String toString() {
        return "RecentlyGameListReq{" +
                ", terminalsType=" + terminalsType +
                '}';
    }
}
