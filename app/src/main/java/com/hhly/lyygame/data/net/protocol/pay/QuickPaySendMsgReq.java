package com.hhly.lyygame.data.net.protocol.pay;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * description :
 * Created by Flynn
 * 2017/5/13
 */

public class QuickPaySendMsgReq extends BaseReq {
    private Integer platformTerminal = 2;
    private Integer platformId = 31111;
    private String tn;

    public void setPlatformTerminal(Integer platformTerminal) {
        this.platformTerminal = platformTerminal;
    }

    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    public void setTn(String tn) {
        this.tn = tn;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();
        if (platformTerminal != null) {
            params.put("platformTerminal", String.valueOf(platformTerminal));
        }
        if (platformId != null) {
            params.put("platformId", String.valueOf(platformId));
        }
        if (tn != null) {
            params.put("tn", tn);
        }
        return params;
    }

}
