package com.hhly.lyygame.data.net.protocol.user;

import com.hhly.lyygame.data.net.protocol.BaseResp;

/**
 * Created by dell on 2017/4/7.
 */

public class OrderQueryResp extends BaseResp {


    /**
     * payBussinessInfo : {"id":0,"appId":"1","appName":"平台","buyerId":"hhly92543","outTradeNo":"hhly170411101505466126","tradeName":"玩一下游戏","tradeNo":"2017041121001004830201262438","tradeStatus":"1","tradeType":"1","orderAmount":1,"totalFee":1,"sellerPrivilege":null,"createTime":1491876904000,"payTime":1491876929000,"updateTime":1491876916000,"paymentType":"1 ","paymentPlatform":"31111","businessNo":"hhly20170411101505594251","payTimeOut":"20170412101505","currencyType":null,"cardNum":null,"cardId":null,"chKey":null,"vendor":null,"extendParams":"hhly92543|1|1|4|31111|0|null","timeOutTime":"20170412102122"}
     */

    private PayBussinessInfoBean data;

    public PayBussinessInfoBean getPayBussinessInfo() {
        return data;
    }

    public void setPayBussinessInfo(PayBussinessInfoBean data) {
        this.data = data;
    }

    public static class PayBussinessInfoBean {

        private int changeType;//交易类型1.充值,2.兑换(用乐盈币支付),3.充值到子平台,4.系统充值（后台手工充值）5.系统扣除乐盈币 6.转入 7.转出 8.转账
        private long lyb;//乐盈币
        private long applyTime;//支付时间
        private String orderno;//订单号
        private long applyAmount;//申请充值金额（单位：人民币

        public int getChangeType() {
            return changeType;
        }

        public void setChangeType(int changeType) {
            this.changeType = changeType;
        }

        public long getLyb() {
            return lyb;
        }

        public void setLyb(long lyb) {
            this.lyb = lyb;
        }

        public long getApplyTime() {
            return applyTime;
        }

        public void setApplyTime(long applyTime) {
            this.applyTime = applyTime;
        }

        public String getOrderno() {
            return orderno;
        }

        public void setOrderno(String orderno) {
            this.orderno = orderno;
        }

        public long getApplyAmount() {
            return applyAmount;
        }

        public void setApplyAmount(long applyAmount) {
            this.applyAmount = applyAmount;
        }
    }
}
