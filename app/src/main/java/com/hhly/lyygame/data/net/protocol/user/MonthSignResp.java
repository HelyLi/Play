package com.hhly.lyygame.data.net.protocol.user;

import com.hhly.lyygame.data.net.protocol.BaseResp;

/**
 * Created by ${HELY} on 17/1/3.
 * 邮箱：heli.lixiong@gmail.com
 */

public class MonthSignResp extends BaseResp {

    private int continueDay;
    private String day;

    public int getContinueDay() {
        return continueDay;
    }

    public void setContinueDay(int continueDay) {
        this.continueDay = continueDay;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

}
