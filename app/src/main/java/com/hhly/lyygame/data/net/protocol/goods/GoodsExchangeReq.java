package com.hhly.lyygame.data.net.protocol.goods;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by ${HELY} on 17/2/10.
 * 邮箱：heli.lixiong@gmail.com
 */

public class GoodsExchangeReq extends BaseReq {

    private ExGoods mExGoods;

    public GoodsExchangeReq(ExGoods exGoods) {
        mExGoods = exGoods;
    }

    static public class ExGoods{

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        Integer id;
        Integer goodsId;
        String userId;
        Integer count;
        Integer addressId;
        String orderId;
        Integer status;
        String payToAccount;
        String phone;

        public void setCountry(Integer country) {
            this.country = country;
        }

        Integer country;

        public void setGoodsId(Integer goodsId) {
            this.goodsId = goodsId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public void setAddressId(Integer addressId) {
            this.addressId = addressId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public void setPayToAccount(String payToAccount) {
            this.payToAccount = payToAccount;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Integer getGoodsId() {
            return goodsId;
        }

        @Override
        public String toString() {
            return "ExGoods{" +
                    "id=" + id +
                    ", goodsId=" + goodsId +
                    ", userId='" + userId + '\'' +
                    ", count=" + count +
                    ", addressId=" + addressId +
                    ", orderId='" + orderId + '\'' +
                    ", status=" + status +
                    ", payToAccount='" + payToAccount + '\'' +
                    ", phone='" + phone + '\'' +
                    ", country=" + country +
                    '}';
        }

    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();

        if (mExGoods.goodsId != null){
            params.put("goodsId", String.valueOf(mExGoods.goodsId));
        }
        if (mExGoods.userId != null){
            params.put("userId", mExGoods.userId);
        }
        if (mExGoods.count != null){
            params.put("count", String.valueOf(mExGoods.count));
        }
        if (mExGoods.addressId != null){
            params.put("addressId", String.valueOf(mExGoods.addressId));
        }
        if (mExGoods.orderId != null){
            params.put("orderId", mExGoods.orderId);
        }
        if (mExGoods.status != null){
            params.put("status", String.valueOf(mExGoods.status));
        }
        if (mExGoods.payToAccount != null){
            params.put("payToAccount", mExGoods.payToAccount);
        }
        if (mExGoods.phone != null){
            params.put("phone", mExGoods.phone);
        }
        if (mExGoods.country != null){
            params.put("country", String.valueOf(mExGoods.country));
        }

        return params;
    }
}
