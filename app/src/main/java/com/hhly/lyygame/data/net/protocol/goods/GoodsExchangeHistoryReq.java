package com.hhly.lyygame.data.net.protocol.goods;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by ${HELY} on 17/2/10.
 * 邮箱：heli.lixiong@gmail.com
 */

public class GoodsExchangeHistoryReq extends BaseReq {

    private String userId;

    public void setCountry(String country) {
        this.country = country;
    }

    private String country;

    private Integer pageNo;

    public void setDataSize(Integer dataSize) {
        this.dataSize = dataSize;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    private Integer dataSize;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();

        if (userId != null){
            params.put("userId", userId);
        }
        if (country != null){
            params.put("country", country);
        }
        if (pageNo != null){
            params.put("pageNo", String.valueOf(pageNo));
        }
        if (dataSize != null){
            params.put("pageSize", String.valueOf(dataSize));
        }

        return params;
    }
}
