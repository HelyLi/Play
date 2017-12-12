package com.hhly.lyygame.data.net.protocol.transfer;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by ${HELY} on 17/4/24.
 * 邮箱：heli.lixiong@gmail.com
 */

public class TransferGameListReq extends BaseReq {

    private String terminal;//（1pc端 、2ios端、 3安卓 、4h5）
    private String drawType;//(1:划入,2:划出,3:兑换;默认为划入)

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public void setDrawType(String drawType) {
        this.drawType = drawType;
    }

    @Override
    public Map<String, String> params() {
        Map<String , String> params = super.params();

        if (terminal != null){
            params.put("terminal", terminal);
        }
        if (drawType != null){
            params.put("drawType", drawType);
        }
        return params;
    }

    @Override
    public String toString() {
        return "TransferGameListReq{" +
                "terminal='" + terminal + '\'' +
                ", drawType='" + drawType + '\'' +
                '}';
    }
}
