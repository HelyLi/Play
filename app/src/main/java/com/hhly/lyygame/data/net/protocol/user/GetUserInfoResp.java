package com.hhly.lyygame.data.net.protocol.user;

import com.hhly.lyygame.data.db.entry.UserInfo;
import com.hhly.lyygame.data.net.protocol.BaseResp;

/**
 * Created by ${HELY} on 16/12/28.
 * 邮箱：heli.lixiong@gmail.com
 */

public class GetUserInfoResp extends BaseResp{

    private UserInfo user;
    private String lyb;//个人乐盈币
    private String jf;//个人积分
    private String lyq;//个人乐盈券
    private Integer integrity;//资料完整度
    private Integer safeLevel;//安全等级
    private Integer bindFlag;//绑定标志
    private Integer paypwdFlag;//是否设置支付密码

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public String getLyb() {
        return lyb;
    }

    public void setLyb(String lyb) {
        this.lyb = lyb;
    }

    public String getJf() {
        return jf;
    }

    public void setJf(String jf) {
        this.jf = jf;
    }

    public String getLyq() {
        return lyq;
    }

    public void setLyq(String lyq) {
        this.lyq = lyq;
    }

    public Integer getIntegrity() {
        return integrity;
    }

    public void setIntegrity(Integer integrity) {
        this.integrity = integrity;
    }

    public Integer getSafeLevel() {
        return safeLevel;
    }

    public void setSafeLevel(Integer safeLevel) {
        this.safeLevel = safeLevel;
    }

    public Integer getBindFlag() {
        return bindFlag;
    }

    public void setBindFlag(Integer bindFlag) {
        this.bindFlag = bindFlag;
    }

    public Integer getPaypwdFlag() {
        return paypwdFlag;
    }

    public void setPaypwdFlag(Integer paypwdFlag) {
        this.paypwdFlag = paypwdFlag;
    }

    @Override
    public String toString() {
        return "GetUserInfoResp{" +
                "user=" + user +
                ", lyb='" + lyb + '\'' +
                ", jf='" + jf + '\'' +
                ", lyq='" + lyq + '\'' +
                ", integrity=" + integrity +
                ", safeLevel=" + safeLevel +
                ", bindFlag=" + bindFlag +
                ", paypwdFlag=" + paypwdFlag +
                '}';
    }
}