package com.hhly.lyygame.data.net.protocol.pay;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * description :
 * Created by Flynn
 * 2017/5/13
 */

public class OrderInfoReq extends BaseReq {

    private Integer platformTerminal = 2;

    private String bankName;
    private String bankCardNo;

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public void setPlatformTerminal(Integer platformTerminal) {
        this.platformTerminal = platformTerminal;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();
        if (platformTerminal != null) {
            params.put("platformTerminal", String.valueOf(platformTerminal));
        }
        if (bankName != null) {
            params.put("bankName", bankName);
        }
        if (bankCardNo != null) {
            params.put("bankCardNo", bankCardNo);
        }
        return params;
    }
}
