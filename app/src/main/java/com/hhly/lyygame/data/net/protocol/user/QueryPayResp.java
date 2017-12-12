package com.hhly.lyygame.data.net.protocol.user;

/**
 * Created by dell on 2017/6/3.
 */

public class QueryPayResp {
    /**
     * result : 0
     * msg : SUCESS
     * payBussinessInfo : {"id":6983,"appId":"10040","appName":"游戏竞猜","buyerId":"hhly91024","outTradeNo":"hhly161125121836921259","tradeName":null,"tradeNo":"201611251218362281608","tradeStatus":"3","tradeType":null,"orderAmount":1,"totalFee":1,"sellerPrivilege":null,"createTime":1480047516000,"payTime":1489978999000,"updateTime":1490029200000,"paymentType":"3 ","paymentPlatform":"11111","businessNo":"hhly20161125121836079934","payTimeOut":"20161126121836","currencyType":null,"cardNum":null,"cardId":null,"chKey":null,"vendor":null,"extendParams":null}
     */

    private int result;
    private String msg;
    private PayBussinessInfoBean payBussinessInfo;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public PayBussinessInfoBean getPayBussinessInfo() {
        return payBussinessInfo;
    }

    public void setPayBussinessInfo(PayBussinessInfoBean payBussinessInfo) {
        this.payBussinessInfo = payBussinessInfo;
    }

    public static class PayBussinessInfoBean {
        /**
         * id : 6983
         * appId : 10040
         * appName : 游戏竞猜
         * buyerId : hhly91024
         * outTradeNo : hhly161125121836921259
         * tradeName : null
         * tradeNo : 201611251218362281608
         * tradeStatus : 3
         * tradeType : null
         * orderAmount : 1
         * totalFee : 1
         * sellerPrivilege : null
         * createTime : 1480047516000
         * payTime : 1489978999000
         * updateTime : 1490029200000
         * paymentType : 3
         * paymentPlatform : 11111
         * businessNo : hhly20161125121836079934
         * payTimeOut : 20161126121836
         * currencyType : null
         * cardNum : null
         * cardId : null
         * chKey : null
         * vendor : null
         * extendParams : null
         */

        private int id;
        private String appId;
        private String appName;
        private String buyerId;
        private String outTradeNo;
        private String tradeName;
        private String tradeNo;
        private String tradeStatus;
        private String tradeType;
        private double orderAmount;
        private double totalFee;
        private String sellerPrivilege;
        private long createTime;
        private long payTime;
        private long updateTime;
        private String paymentType;
        private String paymentPlatform;
        private String businessNo;
        private String payTimeOut;
        private String currencyType;
        private String cardNum;
        private String cardId;
        private String chKey;
        private String vendor;
        private String extendParams;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getAppName() {
            return appName;
        }

        public void setAppName(String appName) {
            this.appName = appName;
        }

        public String getBuyerId() {
            return buyerId;
        }

        public void setBuyerId(String buyerId) {
            this.buyerId = buyerId;
        }

        public String getOutTradeNo() {
            return outTradeNo;
        }

        public void setOutTradeNo(String outTradeNo) {
            this.outTradeNo = outTradeNo;
        }

        public String getTradeName() {
            return tradeName;
        }

        public void setTradeName(String tradeName) {
            this.tradeName = tradeName;
        }

        public String getTradeNo() {
            return tradeNo;
        }

        public void setTradeNo(String tradeNo) {
            this.tradeNo = tradeNo;
        }

        public String getTradeStatus() {
            return tradeStatus;
        }

        public void setTradeStatus(String tradeStatus) {
            this.tradeStatus = tradeStatus;
        }

        public String getTradeType() {
            return tradeType;
        }

        public void setTradeType(String tradeType) {
            this.tradeType = tradeType;
        }

        public double getOrderAmount() {
            return orderAmount;
        }

        public void setOrderAmount(double orderAmount) {
            this.orderAmount = orderAmount;
        }

        public double getTotalFee() {
            return totalFee;
        }

        public void setTotalFee(double totalFee) {
            this.totalFee = totalFee;
        }

        public String getSellerPrivilege() {
            return sellerPrivilege;
        }

        public void setSellerPrivilege(String sellerPrivilege) {
            this.sellerPrivilege = sellerPrivilege;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public long getPayTime() {
            return payTime;
        }

        public void setPayTime(long payTime) {
            this.payTime = payTime;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public String getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(String paymentType) {
            this.paymentType = paymentType;
        }

        public String getPaymentPlatform() {
            return paymentPlatform;
        }

        public void setPaymentPlatform(String paymentPlatform) {
            this.paymentPlatform = paymentPlatform;
        }

        public String getBusinessNo() {
            return businessNo;
        }

        public void setBusinessNo(String businessNo) {
            this.businessNo = businessNo;
        }

        public String getPayTimeOut() {
            return payTimeOut;
        }

        public void setPayTimeOut(String payTimeOut) {
            this.payTimeOut = payTimeOut;
        }

        public String getCurrencyType() {
            return currencyType;
        }

        public void setCurrencyType(String currencyType) {
            this.currencyType = currencyType;
        }

        public String getCardNum() {
            return cardNum;
        }

        public void setCardNum(String cardNum) {
            this.cardNum = cardNum;
        }

        public String getCardId() {
            return cardId;
        }

        public void setCardId(String cardId) {
            this.cardId = cardId;
        }

        public String getChKey() {
            return chKey;
        }

        public void setChKey(String chKey) {
            this.chKey = chKey;
        }

        public String getVendor() {
            return vendor;
        }

        public void setVendor(String vendor) {
            this.vendor = vendor;
        }

        public String getExtendParams() {
            return extendParams;
        }

        public void setExtendParams(String extendParams) {
            this.extendParams = extendParams;
        }
    }
}
