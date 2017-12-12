package com.hhly.lyygame.data.net.protocol.user;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by ${HELY} on 17/1/3.
 * 邮箱：heli.lixiong@gmail.com
 */

public class MsgListReq extends BaseReq {

    private Integer pageNo = 1;//从1开始

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    private Integer pageSize = 50;
    private String startTime;
    private String endTime;
    private Integer status = 2;// 1待发布 2已发布 3已关闭
    private Integer plateFormId;
    private Integer msgType;//类型(1系统消息 2 活动推广  3活动公告类型)

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPlateFormId() {
        return plateFormId;
    }

    public void setPlateFormId(Integer plateFormId) {
        this.plateFormId = plateFormId;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public Integer getPageNo() {

        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    @Override
    public Map<String, String> params() {

        Map<String, String> params = super.params();

        if (pageNo != null){
            params.put("pageNo", String.valueOf(pageNo));
        }
        if (pageSize != null){
            params.put("pageSize", String.valueOf(pageSize));
        }
        if (startTime != null){
            params.put("startTime", startTime);
        }
        if (endTime != null){
            params.put("endTime", endTime);
        }
        if (status != null){
            params.put("status", String.valueOf(status));
        }
        if (plateFormId != null){
            params.put("platformId", String.valueOf(plateFormId));
        }
        if (msgType != null){
            params.put("msgType", String.valueOf(msgType));
        }

        return params;
    }
}
