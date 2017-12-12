package com.hhly.lyygame.data.net.protocol.pay;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.io.Serializable;
import java.util.Map;

/**
 * description :
 * Created by Flynn
 * 2017/5/13
 */

public class QuickPayConfirmReq extends BaseReq implements Serializable {
    private Integer platformTerminal = 2;
    private Integer platformId = 31111;
    private String tn;
    private String smsCode;

    private String extendParams;
    private String outTradeNo;
    private String totalFee;
    private String businessNo;

    public void setExtendParams(String extendParams) {
        this.extendParams = extendParams;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo;
    }

    public void setPlatformTerminal(Integer platformTerminal) {
        this.platformTerminal = platformTerminal;
    }

    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    public void setTn(String tn) {
        this.tn = tn;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public Integer getPlatformTerminal() {
        return platformTerminal;
    }

    public Integer getPlatformId() {
        return platformId;
    }

    public String getTn() {
        return tn;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public String getExtendParams() {
        return extendParams;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public String getBusinessNo() {
        return businessNo;
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
        if (smsCode != null) {
            params.put("smsCode", smsCode);
        }
        if (extendParams != null) {
            params.put("extendParams", extendParams);
        }
        if (outTradeNo != null) {
            params.put("outTradeNo", outTradeNo);
        }
        if (totalFee != null) {
            params.put("totalFee", totalFee);
        }
        if (businessNo != null) {
            params.put("businessNo", businessNo);
        }
        return params;
    }


}
