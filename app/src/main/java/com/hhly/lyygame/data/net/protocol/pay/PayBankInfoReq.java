package com.hhly.lyygame.data.net.protocol.pay;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * description :
 * Created by Flynn
 * 2017/5/13
 */

public class PayBankInfoReq extends BaseReq {

    private Integer platformTerminal = 2;

    public void setPlatformTerminal(Integer platformTerminal) {
        this.platformTerminal = platformTerminal;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();
        if (platformTerminal != null) {
            params.put("platformTerminal", String.valueOf(platformTerminal));
        }
        return params;
    }
}
