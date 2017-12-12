package com.hhly.lyygame.data.net.protocol.goods;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by ${HELY} on 17/2/5.
 * 邮箱：heli.lixiong@gmail.com
 */

public class GoodsListReq extends BaseReq {

    private Integer goodsListCountry;

    private Integer goodsListEnable;

    private Integer goodsListPlatform;

    private Integer goodsListType;

    private Integer pageNo;

    private Integer pageSize;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private String userId;

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public void setDataSize(Integer dataSize) {
        this.pageSize = dataSize;
    }

    public Integer getGoodsListCountry() {
        return goodsListCountry;
    }

    public void setGoodsListCountry(Integer goodsListCountry) {
        this.goodsListCountry = goodsListCountry;
    }

    public Integer getGoodsListEnable() {
        return goodsListEnable;
    }

    public void setGoodsListEnable(Integer goodsListEnable) {
        this.goodsListEnable = goodsListEnable;
    }

    public Integer getGoodsListPlatform() {
        return goodsListPlatform;
    }

    public void setGoodsListPlatform(Integer goodsListPlatform) {
        this.goodsListPlatform = goodsListPlatform;
    }

    public Integer getGoodsListType() {
        return goodsListType;
    }

    public void setGoodsListType(Integer goodsListType) {
        this.goodsListType = goodsListType;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();

        if (goodsListCountry != null){
            params.put("country", String.valueOf(goodsListCountry));
        }
        if (goodsListEnable != null){
            params.put("enable", String.valueOf(goodsListEnable));
        }
        if (goodsListPlatform != null){
            params.put("terminal",String.valueOf(goodsListPlatform));
        }
        if (goodsListType != null){
            params.put("type",String.valueOf(goodsListType));
        }
        if (pageNo != null){
            params.put("pageNo", String.valueOf(pageNo));
        }
        if (pageSize != null){
            params.put("pageSize", String.valueOf(pageSize));
        }
        if (userId != null){
            params.put("userId", userId);
        }

        return params;
    }

    @Override
    public String toString() {
        return "GoodsListReq{" +
                "goodsListCountry=" + goodsListCountry +
                ", goodsListEnable=" + goodsListEnable +
                ", goodsListPlatform=" + goodsListPlatform +
                ", goodsListType=" + goodsListType +
                '}';
    }
}
