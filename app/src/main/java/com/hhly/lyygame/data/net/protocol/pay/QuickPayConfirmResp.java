package com.hhly.lyygame.data.net.protocol.pay;

import com.hhly.lyygame.data.net.protocol.BaseResp;

/**
 * description :
 * Created by Flynn
 * 2017/5/13
 */

public class QuickPayConfirmResp extends BaseResp {

    /**
     * platformId : 31111
     * sign : 6b0ecdb5d8c24fc22cabb4d6530a32e7
     * outTradeNo : null
     * businessNo : null
     * respCode : 0000
     * respMsg : 交易成功
     */

    private String platformId;
    private String sign;
    private String outTradeNo;
    private String businessNo;
    private String respCode;
    private String respMsg;

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getBusinessNo() {
        return businessNo;
    }

    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }
}
