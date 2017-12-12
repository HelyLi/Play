package com.hhly.lyygame.data.net.protocol.user;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by dell on 2017/5/13.
 */

public class BatchDelMsgReq extends BaseReq {

    private String platformTerminal;

    private String msgIds;

    public String getPlatformTerminal() {
        return platformTerminal;
    }

    public void setPlatformTerminal(String platformTerminal) {
        this.platformTerminal = platformTerminal;
    }

    public String getMsgIds() {
        return msgIds;
    }

    public void setMsgIds(String msgIds) {
        this.msgIds = msgIds;
    }

    @Override
    public Map<String, String> params() {
        Map<String , String> params = super.params();

        if (platformTerminal != null){
            params.put("platformTerminal", platformTerminal);
        }
        if (msgIds != null){
            params.put("msgIds", msgIds);
        }

        return params;
    }
}
