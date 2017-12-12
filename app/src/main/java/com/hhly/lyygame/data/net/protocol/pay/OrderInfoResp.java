package com.hhly.lyygame.data.net.protocol.pay;

import com.hhly.lyygame.data.net.protocol.BaseResp;

/**
 * description :
 * Created by Flynn
 * 2017/5/13
 */

public class OrderInfoResp extends BaseResp {


    /**
     * data : {"applyTime":1494674561950,"outTradeNo":"hhly170513192241128888"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * applyTime : 1494674561950
         * outTradeNo : hhly170513192241128888
         */

        private long applyTime;
        private String outTradeNo;

        public long getApplyTime() {
            return applyTime;
        }

        public void setApplyTime(long applyTime) {
            this.applyTime = applyTime;
        }

        public String getOutTradeNo() {
            return outTradeNo;
        }

        public void setOutTradeNo(String outTradeNo) {
            this.outTradeNo = outTradeNo;
        }
    }
}
