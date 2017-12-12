package com.hhly.lyygame.data.net.protocol.user;

import com.hhly.lyygame.data.net.protocol.BaseResp;

/**
 * Created by ${HELY} on 17/1/3.
 * 邮箱：heli.lixiong@gmail.com
 *
 * continueDay：连续签到天数（只针对当月）
 * day:用分号隔开的当月签到天数
 *
 */
public class UserSignResp extends BaseResp {

    /**
     * point : 50.0
     */
    private double point;

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }
}
