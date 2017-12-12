package com.hhly.lyygame.data.net.protocol.user;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by ${HELY} on 17/2/9.
 * 邮箱：heli.lixiong@gmail.com
 */

public class MsgStateReq extends BaseReq {

    private Integer msgId;

    public Integer getMsgId() {
        return msgId;
    }

    public void setMsgId(Integer msgId) {
        this.msgId = msgId;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();

        if (msgId != null){
            params.put("msgId", String.valueOf(msgId));
        }
        return params;
    }
}
