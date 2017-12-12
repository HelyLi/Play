package com.hhly.lyygame.data.net.protocol.goods;

import com.hhly.lyygame.data.net.protocol.BaseResp;

import java.util.List;

/**
 * Created by ${HELY} on 17/2/10.
 * 邮箱：heli.lixiong@gmail.com
 */

public class GoodsExchangeHistoryResp extends BaseResp {
    /**
     * msg : null
     * total : 56
     * list : [{"exchangeRecordId":3757,"orderId":"cacf442973e04638a7336d3f13fb8b04","userId":"hhly91599","goodsId":1561,"count":1,"createTime":1496385269000,"updateTime":1496385269000,"status":1,"deliveredTime":null,"buyOrder":null,"deliverCompany":null,"deliverNum":null,"payToAccount":null,"phone":null,"username":null,"province":null,"city":null,"street":null,"detail":null,"goodsName":"2345","type":3,"picUrl":"http://public.13322.com/7ae30704.png","addressId":null,"exchangePrice":"0乐盈券","country":0,"nickname":"*罒▽罒*啊监控"},{"exchangeRecordId":3756,"orderId":"aa158473e08d40bfb6435bec93d07552","userId":"hhly91599","goodsId":1561,"count":1,"createTime":1496385234000,"updateTime":1496385234000,"status":1,"deliveredTime":null,"buyOrder":null,"deliverCompany":null,"deliverNum":null,"payToAccount":null,"phone":null,"username":null,"province":null,"city":null,"street":null,"detail":null,"goodsName":"2345","type":3,"picUrl":"http://public.13322.com/7ae30704.png","addressId":null,"exchangePrice":"0乐盈券","country":0,"nickname":"*罒▽罒*啊监控"},{"exchangeRecordId":3335,"orderId":"2647f61ed68742438f8875637b333d65","userId":"hhly91599","goodsId":1286,"count":1,"createTime":1495533908000,"updateTime":1495533908000,"status":1,"deliveredTime":null,"buyOrder":null,"deliverCompany":null,"deliverNum":null,"payToAccount":null,"phone":null,"username":null,"province":null,"city":null,"street":null,"detail":null,"goodsName":"幸运摇一摇","type":3,"picUrl":"http://public.13322.com/2a61c3e4.jpg","addressId":null,"exchangePrice":"1乐盈券","country":0,"nickname":"*罒▽罒*啊监控"}]
     */
    private int total;
    private List<ExchangeBean> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ExchangeBean> getList() {
        return list;
    }

    public void setList(List<ExchangeBean> list) {
        this.list = list;
    }

    public static class ExchangeBean {
        /**
         * exchangeRecordId : 3757
         * orderId : cacf442973e04638a7336d3f13fb8b04
         * userId : hhly91599
         * goodsId : 1561
         * count : 1
         * createTime : 1496385269000
         * updateTime : 1496385269000
         * status : 1
         * deliveredTime : null
         * buyOrder : null
         * deliverCompany : null
         * deliverNum : null
         * payToAccount : null
         * phone : null
         * username : null
         * province : null
         * city : null
         * street : null
         * detail : null
         * goodsName : 2345
         * type : 3
         * picUrl : http://public.13322.com/7ae30704.png
         * addressId : null
         * exchangePrice : 0乐盈券
         * country : 0
         * nickname : *罒▽罒*啊监控
         */

        private int exchangeRecordId;
        private String orderId;
        private String userId;
        private int goodsId;
        private int count;
        private long createTime;
        private long updateTime;
        private int status;
        private Object deliveredTime;
        private Object buyOrder;
        private Object deliverCompany;
        private Object deliverNum;
        private Object payToAccount;
        private Object phone;
        private Object username;
        private Object province;
        private Object city;
        private Object street;
        private Object detail;
        private String goodsName;
        private int type;
        private String picUrl;
        private Object addressId;
        private String exchangePrice;
        private int country;
        private String nickname;

        public int getExchangeRecordId() {
            return exchangeRecordId;
        }

        public void setExchangeRecordId(int exchangeRecordId) {
            this.exchangeRecordId = exchangeRecordId;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Object getDeliveredTime() {
            return deliveredTime;
        }

        public void setDeliveredTime(Object deliveredTime) {
            this.deliveredTime = deliveredTime;
        }

        public Object getBuyOrder() {
            return buyOrder;
        }

        public void setBuyOrder(Object buyOrder) {
            this.buyOrder = buyOrder;
        }

        public Object getDeliverCompany() {
            return deliverCompany;
        }

        public void setDeliverCompany(Object deliverCompany) {
            this.deliverCompany = deliverCompany;
        }

        public Object getDeliverNum() {
            return deliverNum;
        }

        public void setDeliverNum(Object deliverNum) {
            this.deliverNum = deliverNum;
        }

        public Object getPayToAccount() {
            return payToAccount;
        }

        public void setPayToAccount(Object payToAccount) {
            this.payToAccount = payToAccount;
        }

        public Object getPhone() {
            return phone;
        }

        public void setPhone(Object phone) {
            this.phone = phone;
        }

        public Object getUsername() {
            return username;
        }

        public void setUsername(Object username) {
            this.username = username;
        }

        public Object getProvince() {
            return province;
        }

        public void setProvince(Object province) {
            this.province = province;
        }

        public Object getCity() {
            return city;
        }

        public void setCity(Object city) {
            this.city = city;
        }

        public Object getStreet() {
            return street;
        }

        public void setStreet(Object street) {
            this.street = street;
        }

        public Object getDetail() {
            return detail;
        }

        public void setDetail(Object detail) {
            this.detail = detail;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public Object getAddressId() {
            return addressId;
        }

        public void setAddressId(Object addressId) {
            this.addressId = addressId;
        }

        public String getExchangePrice() {
            return exchangePrice;
        }

        public void setExchangePrice(String exchangePrice) {
            this.exchangePrice = exchangePrice;
        }

        public int getCountry() {
            return country;
        }

        public void setCountry(int country) {
            this.country = country;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }


//    private int total;
//    private List<ExchangeBean> list;
//
//    public int getTotal() {
//        return total;
//    }
//
//    public void setTotal(int total) {
//        this.total = total;
//    }
//
//    public List<ExchangeBean> getList() {
//        return list;
//    }
//
//    public void setList(List<ExchangeBean> list) {
//        this.list = list;
//    }
//
//    public static class ExchangeBean {
//        /**
//         * exchangeRecordId : 1550
//         * orderId : 581867fdfdb44705bbdc7efc365f7596
//         * userId : hhly91599
//         * goodsId : 1023
//         * count : 1
//         * createTime : 1488709570000
//         * updateTime : 1488709570000
//         * status : 1
//         * deliveredTime : null
//         * buyOrder : null
//         * deliverCompany : null
//         * deliverNum : null
//         * payToAccount : null
//         * phone : null
//         * username : null
//         * province : null
//         * city : null
//         * street : null
//         * detail : null
//         * goodsName : 虹焰之火游戏点卡100
//         * type : 3
//         * picUrl : http://public.13322.com/1f7a6430.jpg
//         * addressId : null
//         * exchangePrice : 10积分
//         * country : 0
//         * nickname : hhly91599
//         */
//
//        private int exchangeRecordId;
//        private String orderId;
//        private String userId;
//        private int goodsId;
//        private int count;
//        private long createTime;
//        private long updateTime;
//        private int status;
//        private Object deliveredTime;
//        private Object buyOrder;
//        private Object deliverCompany;
//        private Object deliverNum;
//        private Object payToAccount;
//        private Object phone;
//        private Object username;
//        private Object province;
//        private Object city;
//        private Object street;
//        private Object detail;
//        private String goodsName;
//        private int type;
//        private String picUrl;
//        private Object addressId;
//        private double exchangePrice;
//        private int country;
//        private String nickname;
//
//        public int getExchangeRecordId() {
//            return exchangeRecordId;
//        }
//
//        public void setExchangeRecordId(int exchangeRecordId) {
//            this.exchangeRecordId = exchangeRecordId;
//        }
//
//        public String getOrderId() {
//            return orderId;
//        }
//
//        public void setOrderId(String orderId) {
//            this.orderId = orderId;
//        }
//
//        public String getUserId() {
//            return userId;
//        }
//
//        public void setUserId(String userId) {
//            this.userId = userId;
//        }
//
//        public int getGoodsId() {
//            return goodsId;
//        }
//
//        public void setGoodsId(int goodsId) {
//            this.goodsId = goodsId;
//        }
//
//        public int getCount() {
//            return count;
//        }
//
//        public void setCount(int count) {
//            this.count = count;
//        }
//
//        public long getCreateTime() {
//            return createTime;
//        }
//
//        public void setCreateTime(long createTime) {
//            this.createTime = createTime;
//        }
//
//        public long getUpdateTime() {
//            return updateTime;
//        }
//
//        public void setUpdateTime(long updateTime) {
//            this.updateTime = updateTime;
//        }
//
//        public int getStatus() {
//            return status;
//        }
//
//        public void setStatus(int status) {
//            this.status = status;
//        }
//
//        public Object getDeliveredTime() {
//            return deliveredTime;
//        }
//
//        public void setDeliveredTime(Object deliveredTime) {
//            this.deliveredTime = deliveredTime;
//        }
//
//        public Object getBuyOrder() {
//            return buyOrder;
//        }
//
//        public void setBuyOrder(Object buyOrder) {
//            this.buyOrder = buyOrder;
//        }
//
//        public Object getDeliverCompany() {
//            return deliverCompany;
//        }
//
//        public void setDeliverCompany(Object deliverCompany) {
//            this.deliverCompany = deliverCompany;
//        }
//
//        public Object getDeliverNum() {
//            return deliverNum;
//        }
//
//        public void setDeliverNum(Object deliverNum) {
//            this.deliverNum = deliverNum;
//        }
//
//        public Object getPayToAccount() {
//            return payToAccount;
//        }
//
//        public void setPayToAccount(Object payToAccount) {
//            this.payToAccount = payToAccount;
//        }
//
//        public Object getPhone() {
//            return phone;
//        }
//
//        public void setPhone(Object phone) {
//            this.phone = phone;
//        }
//
//        public Object getUsername() {
//            return username;
//        }
//
//        public void setUsername(Object username) {
//            this.username = username;
//        }
//
//        public Object getProvince() {
//            return province;
//        }
//
//        public void setProvince(Object province) {
//            this.province = province;
//        }
//
//        public Object getCity() {
//            return city;
//        }
//
//        public void setCity(Object city) {
//            this.city = city;
//        }
//
//        public Object getStreet() {
//            return street;
//        }
//
//        public void setStreet(Object street) {
//            this.street = street;
//        }
//
//        public Object getDetail() {
//            return detail;
//        }
//
//        public void setDetail(Object detail) {
//            this.detail = detail;
//        }
//
//        public String getGoodsName() {
//            return goodsName;
//        }
//
//        public void setGoodsName(String goodsName) {
//            this.goodsName = goodsName;
//        }
//
//        public int getType() {
//            return type;
//        }
//
//        public void setType(int type) {
//            this.type = type;
//        }
//
//        public String getPicUrl() {
//            return picUrl;
//        }
//
//        public void setPicUrl(String picUrl) {
//            this.picUrl = picUrl;
//        }
//
//        public Object getAddressId() {
//            return addressId;
//        }
//
//        public void setAddressId(Object addressId) {
//            this.addressId = addressId;
//        }
//
//        public double getExchangePrice() {
//            return exchangePrice;
//        }
//
//        public void setExchangePrice(double exchangePrice) {
//            this.exchangePrice = exchangePrice;
//        }
//
//        public int getCountry() {
//            return country;
//        }
//
//        public void setCountry(int country) {
//            this.country = country;
//        }
//
//        public String getNickname() {
//            return nickname;
//        }
//
//        public void setNickname(String nickname) {
//            this.nickname = nickname;
//        }
//    }
}
