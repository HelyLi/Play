package com.hhly.lyygame.data.net.protocol.pay;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.io.Serializable;
import java.util.Map;

/**
 * description :
 * Created by Flynn
 * 2017/5/13
 */

public class QuickPayApplyReq extends BaseReq implements Serializable{

    private String platformId = "31111";// 接入支付的平台ID
    private String totalFee;// 交易金额total_fee
    private String currencyType;// 交易币种
    private String buyerId;// 买家唯一标识ID
    private String outTradeNo;// 商户网站订单系统中唯一订单号
    private String extendParams;// 扩展字段
    private String subject;// 订单名称
    private String body;// 订单描述
    private String accNo;// 银行账号
    private String certifTp;// 证件类型
    private String certify_id;// 证件号码
    private String customerNm;// 姓名
    private String phoneNo;// 手机号
    private String dcType; // 借贷类型
    private String expired;// 有效期(信用卡时必填)
    private String CVV2;
    private Integer platformTerminal = 2;

    private String bankName;
    private Long transactionTime;

    public void setTransactionTime(Long transactionTime) {
        this.transactionTime = transactionTime;
    }

    public Long getTransactionTime() {
        return transactionTime;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setPlatformTerminal(Integer platformTerminal) {
        this.platformTerminal = platformTerminal;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public void setExtendParams(String extendParams) {
        this.extendParams = extendParams;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public void setCertifTp(String certifTp) {
        this.certifTp = certifTp;
    }

    public void setCertify_id(String certify_id) {
        this.certify_id = certify_id;
    }

    public void setCustomerNm(String customerNm) {
        this.customerNm = customerNm;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setDcType(String dcType) {
        this.dcType = dcType;
    }

    public void setExpired(String expired) {
        this.expired = expired;
    }

    public void setCVV2(String CVV2) {
        this.CVV2 = CVV2;
    }

    public String getPlatformId() {
        return platformId;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public String getExtendParams() {
        return extendParams;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public String getAccNo() {
        return accNo;
    }

    public String getCertifTp() {
        return certifTp;
    }

    public String getCertify_id() {
        return certify_id;
    }

    public String getCustomerNm() {
        return customerNm;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getDcType() {
        return dcType;
    }

    public String getExpired() {
        return expired;
    }

    public String getCVV2() {
        return CVV2;
    }

    public Integer getPlatformTerminal() {
        return platformTerminal;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();
        if (platformTerminal != null) {
            params.put("platformTerminal", String.valueOf(platformTerminal));
        }
        if (platformId != null) {
            params.put("platformId", platformId);
        }
        if (totalFee != null) {
            params.put("totalFee", totalFee);
        }
        if (currencyType != null) {
            params.put("currencyType", currencyType);
        }
        if (buyerId != null) {
            params.put("buyerId", buyerId);
        }
        if (outTradeNo != null) {
            params.put("outTradeNo", outTradeNo);
        }
        if (extendParams != null) {
            params.put("extendParams", extendParams);
        }
        if (subject != null) {
            params.put("subject", subject);
        }
        if (body != null) {
            params.put("body", body);
        }
        if (accNo != null) {
            params.put("accNo", accNo);
        }
        if (certifTp != null) {
            params.put("certifTp", certifTp);
        }
        if (certify_id != null) {
            params.put("certify_id", certify_id);
        }
        if (customerNm != null) {
            params.put("customerNm", customerNm);
        }
        if (phoneNo != null) {
            params.put("phoneNo", phoneNo);
        }
        if (dcType != null) {
            params.put("dcType", dcType);
        }
        if (expired != null) {
            params.put("expired", expired);
        }
        if (CVV2 != null) {
            params.put("CVV2", CVV2);
        }
        return params;
    }


    @Override
    public String toString() {
        return "QuickPayApplyReq{" +
                "platformId='" + platformId + '\'' +
                ", totalFee='" + totalFee + '\'' +
                ", currencyType='" + currencyType + '\'' +
                ", buyerId='" + buyerId + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", extendParams='" + extendParams + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", accNo='" + accNo + '\'' +
                ", certifTp='" + certifTp + '\'' +
                ", certify_id='" + certify_id + '\'' +
                ", customerNm='" + customerNm + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", dcType='" + dcType + '\'' +
                ", expired='" + expired + '\'' +
                ", CVV2='" + CVV2 + '\'' +
                ", platformTerminal=" + platformTerminal +
                ", bankName='" + bankName + '\'' +
                ", transactionTime=" + transactionTime +
                '}';
    }
}
