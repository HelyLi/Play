package com.hhly.lyygame.data.net.protocol.user;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by ${HELY} on 17/1/3.
 * 邮箱：heli.lixiong@gmail.com
 *
 * 获取充值明细
 */

public class AccountRechargeDetailReq extends BaseReq {

    private String startTime;
    private String endTime;
    private Integer pageNo = 1;// 页码 默认页码为1
    private Integer pageSize = 50;
    private Integer type = 1; // 1：充值明细、2：乐盈币明细、3：积分明细

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();

        if (startTime != null){
            params.put("startTime", startTime);
        }
        if (endTime != null){
            params.put("startTime", startTime);
        }
        if (pageNo != null){
            params.put("pageNo", String.valueOf(pageNo));
        }
        if (pageSize != null){
            params.put("pageSize", String.valueOf(pageSize));
        }
        if (type != null){
            params.put("type", String.valueOf(type));
        }

        return params;
    }
}
