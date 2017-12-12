package com.hhly.lyygame.data.net.protocol.pay;

import com.hhly.lyygame.data.net.protocol.BaseResp;

/**
 * description :
 * Created by Flynn
 * 2017/5/13
 */

public class OrderInfoForShopResp extends BaseResp {


    /**
     * data : {"applyTime":1494674561950,"orderNo":"hhly170513192241128888"}
     */

    private PayDataBean data;

    public PayDataBean getData() {
        return data;
    }

    public void setData(PayDataBean data) {
        this.data = data;
    }

    public static class PayDataBean {
        /**
         * applyTime : 1494674561950
         * orderNo : hhly170513192241128888
         */

        private long applyTime;
        private String orderNo;

        public long getApplyTime() {
            return applyTime;
        }

        public void setApplyTime(long applyTime) {
            this.applyTime = applyTime;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }
    }
}
