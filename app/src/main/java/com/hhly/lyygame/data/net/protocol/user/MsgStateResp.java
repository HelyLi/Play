package com.hhly.lyygame.data.net.protocol.user;

import com.hhly.lyygame.data.net.protocol.BaseResp;

/**
 * Created by ${HELY} on 17/2/9.
 * 邮箱：heli.lixiong@gmail.com
 */

public class MsgStateResp extends BaseResp {

    private Object unreads;
    private Object pager;
    private Object messInfoList;
    private Object userInfo;
    private Object messageInfo;
    private Object userMessage;

    public Object getUnreads() {
        return unreads;
    }

    public void setUnreads(Object unreads) {
        this.unreads = unreads;
    }

    public Object getPager() {
        return pager;
    }

    public void setPager(Object pager) {
        this.pager = pager;
    }

    public Object getMessInfoList() {
        return messInfoList;
    }

    public void setMessInfoList(Object messInfoList) {
        this.messInfoList = messInfoList;
    }

    public Object getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(Object userInfo) {
        this.userInfo = userInfo;
    }

    public Object getMessageInfo() {
        return messageInfo;
    }

    public void setMessageInfo(Object messageInfo) {
        this.messageInfo = messageInfo;
    }

    public Object getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(Object userMessage) {
        this.userMessage = userMessage;
    }
}
