package com.hhly.lyygame.data.net.protocol.goods;

import com.hhly.lyygame.data.net.protocol.BaseResp;

import java.util.List;

/**
 * Created by ${HELY} on 17/2/10.
 * 邮箱：heli.lixiong@gmail.com
 */

public class StoreGoodsResp extends BaseResp {

    private List<StoreGoodsBean> storeGoods;

    public List<StoreGoodsBean> getStoreGoods() {
        return storeGoods;
    }

    public void setStoreGoods(List<StoreGoodsBean> storeGoods) {
        this.storeGoods = storeGoods;
    }

    public static class StoreGoodsBean {
        /**
         * id : 787
         * userId : hhly91480
         * goodsId : 1023
         * createTime : 1486605474000
         * name : 虹焰之火游戏点卡100
         * type : 4
         * picUrl : http://public.13322.com/1f7a6430.jpg
         * needScoreId : 3
         * needScore : 10
         * total : 1000
         * enable : 0
         * position : 7
         * price : 30.0
         * country : 0
         */

        private int id;
        private String userId;
        private int goodsId;
        private long createTime;
        private String name;
        private int type;//奖品类型 1:实物 2:虚拟物品
        private String picUrl;
        private int needScoreId;//1乐盈币 2乐盈券 3积分
        private double needScore;
        private Long total;
        private int enable;
        private int position;
        private double price;
        private int country;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public int getNeedScoreId() {
            return needScoreId;
        }

        public void setNeedScoreId(int needScoreId) {
            this.needScoreId = needScoreId;
        }

        public double getNeedScore() {
            return needScore;
        }

        public void setNeedScore(double needScore) {
            this.needScore = needScore;
        }

        public Long getTotal() {
            return total;
        }

        public void setTotal(Long total) {
            this.total = total;
        }

        public int getEnable() {
            return enable;
        }

        public void setEnable(int enable) {
            this.enable = enable;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getCountry() {
            return country;
        }

        public void setCountry(int country) {
            this.country = country;
        }

        @Override
        public String toString() {
            return "StoreGoodsBean{" +
                    "id=" + id +
                    ", userId='" + userId + '\'' +
                    ", goodsId=" + goodsId +
                    ", createTime=" + createTime +
                    ", name='" + name + '\'' +
                    ", type=" + type +
                    ", picUrl='" + picUrl + '\'' +
                    ", needScoreId=" + needScoreId +
                    ", needScore=" + needScore +
                    ", total=" + total +
                    ", enable=" + enable +
                    ", position=" + position +
                    ", price=" + price +
                    ", country=" + country +
                    '}';
        }
    }
}
