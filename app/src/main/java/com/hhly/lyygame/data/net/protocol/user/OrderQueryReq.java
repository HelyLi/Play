package com.hhly.lyygame.data.net.protocol.user;


import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by dell on 2017/3/31.
 */

public class OrderQueryReq extends BaseReq {
    private String bussinessNo;

    public OrderQueryReq() {

    }

    public String getBussinessNo() {
        return bussinessNo;
    }

    public void setBussinessNo(String bussinessNo) {
        this.bussinessNo = bussinessNo;
    }

    @Override
    public Map<String, String> params() {
        final Map<String, String> maps = super.params();
        if (bussinessNo != null) {
            maps.put("businessNo", bussinessNo);
        }
        return maps;
    }
}
