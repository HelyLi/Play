package com.hhly.lyygame.data.net.protocol.transfer;

import com.hhly.lyygame.data.net.protocol.BaseResp;

/**
 * Created by ${HELY} on 17/4/24.
 * 邮箱：heli.lixiong@gmail.com
 */

public class TransferRemitInfoResp extends BaseResp {

    /**
     * fplatformId : 10110
     * tplatformId : 10110
     * fRate : 1.0
     * tRate : 1.0
     * minAmount : 10.0
     */

    private int fplatformId;// 转出平台id
    private int tplatformId;// 转入平台id
    private double fRate;// 转出平台汇率
    private double tRate;// 转入平台汇率
    private double minAmount;// 最小划出金额
    private double maxAmount;// 最大划出金额
    private int maxTimes;// 每日最大划出次数

    public int getFplatformId() {
        return fplatformId;
    }

    public void setFplatformId(int fplatformId) {
        this.fplatformId = fplatformId;
    }

    public int getTplatformId() {
        return tplatformId;
    }

    public void setTplatformId(int tplatformId) {
        this.tplatformId = tplatformId;
    }

    public double getfRate() {
        return fRate;
    }

    public void setfRate(double fRate) {
        this.fRate = fRate;
    }

    public double gettRate() {
        return tRate;
    }

    public void settRate(double tRate) {
        this.tRate = tRate;
    }

    public double getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(double minAmount) {
        this.minAmount = minAmount;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public int getMaxTimes() {
        return maxTimes;
    }

    public void setMaxTimes(int maxTimes) {
        this.maxTimes = maxTimes;
    }




}
