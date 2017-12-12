package com.hhly.lyygame.data.net.protocol.user;

import com.google.gson.Gson;
import com.hhly.lyygame.data.net.protocol.BaseResp;

/**
 * Created by dell on 2017/3/21.
 */

public class PayResp extends BaseResp {


    /**
     * wechat
     * businessNo : hhly20170322172200626201
     * data : {"sign":"4A261BBD52EC8846149ADC7E1321EAA8","timestamp":"1490174520","noncestr":"ccfc8dda8b5c16c8a51868f6a592c3ef","partnerid":"1450159802","prepayid":"wx20170322172915ee4c8b0bdb0277100936","package":"Sign=WXPay","appid":"wxdd4a10d4e8c7cac0"}
     * type : 2
     * order_no : hhly170322172200125798
     */


    private String businessNo;
    private String data;
    private int type;
    private String order_no;

    public static PayResp objectFromData(String str) {

        return new Gson().fromJson(str, PayResp.class);
    }

    public String getBusinessNo() {
        return businessNo;
    }

    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    @Override
    public String toString() {
        return "PayResp{" +
                "businessNo='" + businessNo + '\'' +
                ", data='" + data + '\'' +
                ", type=" + type +
                ", order_no='" + order_no + '\'' +
                '}';
    }
}
