package com.hhly.lyygame.data.net.protocol.user;

import com.hhly.lyygame.data.net.protocol.BaseResp;

/**
 * Created by ${HELY} on 17/1/3.
 * 邮箱：heli.lixiong@gmail.com
 */

public class MsgListResp extends BaseResp {

    private int unreads;
    private MsgPager pager;
    private Object messInfoList;
    private Object userInfo;
    private Object messageInfo;
    private Object userMessage;

    public int getUnreads() {
        return unreads;
    }

    public void setUnreads(int unreads) {
        this.unreads = unreads;
    }

    public MsgPager getPager() {
        return pager;
    }

    public void setPager(MsgPager pager) {
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
