package com.hhly.lyygame.data.net.protocol.pay;

import com.hhly.lyygame.data.net.protocol.BaseResp;

/**
 * description :
 * Created by Flynn
 * 2017/5/13
 */

public class QuickPayApplyResp extends BaseResp {


    /**
     * businessNo : hhly20170513192128514717
     * data : {"sign":"614fd6882b027b57a43915aa2974082a","tn":"2017051300192217629455","respCode":"0000","businessNo":"hhly20170513192128514717","respMsg":"接受通知成功","outTradeNo":"hhly292322","platformId":"31111"}
     * outTradeNo : hhly292322
     */

    private String businessNo;
    private DataBean data;
    private String outTradeNo;

    public String getBusinessNo() {
        return businessNo;
    }

    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public static class DataBean {
        /**
         * sign : 614fd6882b027b57a43915aa2974082a
         * tn : 2017051300192217629455
         * respCode : 0000
         * businessNo : hhly20170513192128514717
         * respMsg : 接受通知成功
         * outTradeNo : hhly292322
         * platformId : 31111
         */

        private String sign;
        private String tn;
        private String respCode;
        private String businessNo;
        private String respMsg;
        private String outTradeNo;
        private String platformId;

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getTn() {
            return tn;
        }

        public void setTn(String tn) {
            this.tn = tn;
        }

        public String getRespCode() {
            return respCode;
        }

        public void setRespCode(String respCode) {
            this.respCode = respCode;
        }

        public String getBusinessNo() {
            return businessNo;
        }

        public void setBusinessNo(String businessNo) {
            this.businessNo = businessNo;
        }

        public String getRespMsg() {
            return respMsg;
        }

        public void setRespMsg(String respMsg) {
            this.respMsg = respMsg;
        }

        public String getOutTradeNo() {
            return outTradeNo;
        }

        public void setOutTradeNo(String outTradeNo) {
            this.outTradeNo = outTradeNo;
        }

        public String getPlatformId() {
            return platformId;
        }

        public void setPlatformId(String platformId) {
            this.platformId = platformId;
        }
    }
}
