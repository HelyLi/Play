package com.hhly.lyygame.data.net.protocol.user;

import com.hhly.lyygame.data.net.protocol.BaseResp;

/**
 * Created by ${HELY} on 17/1/3.
 * 邮箱：heli.lixiong@gmail.com
 */

public class MsgInfoResp extends BaseResp {

    private Object unreads;
    private Object pager;
    private Object messInfoList;
    private Object userInfo;
    private Object messageInfo;
    private MessageDetailBean userMessage;

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

    public MessageDetailBean getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(MessageDetailBean userMessage) {
        this.userMessage = userMessage;
    }

    public static class MessageDetailBean {

        private int id;
        private String title;
        private String content;
        private long time;
        private int creator;
        private Object titleHref;
        private int type;
        private int status;
        private Object handTime;
        private String image;
        private int isMass;
        private int plateFormId;
        private int stick;
        private String synopsis;
        private String userId;
        private Object msgId;
        private Object remark;
        private int readStatus;
        private long userHandTime;
        private Object nickName;
        private Object phone;
        private Object email;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public int getCreator() {
            return creator;
        }

        public void setCreator(int creator) {
            this.creator = creator;
        }

        public Object getTitleHref() {
            return titleHref;
        }

        public void setTitleHref(Object titleHref) {
            this.titleHref = titleHref;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Object getHandTime() {
            return handTime;
        }

        public void setHandTime(Object handTime) {
            this.handTime = handTime;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getIsMass() {
            return isMass;
        }

        public void setIsMass(int isMass) {
            this.isMass = isMass;
        }

        public int getPlateFormId() {
            return plateFormId;
        }

        public void setPlateFormId(int plateFormId) {
            this.plateFormId = plateFormId;
        }

        public int getStick() {
            return stick;
        }

        public void setStick(int stick) {
            this.stick = stick;
        }

        public String getSynopsis() {
            return synopsis;
        }

        public void setSynopsis(String synopsis) {
            this.synopsis = synopsis;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public Object getMsgId() {
            return msgId;
        }

        public void setMsgId(Object msgId) {
            this.msgId = msgId;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public int getReadStatus() {
            return readStatus;
        }

        public void setReadStatus(int readStatus) {
            this.readStatus = readStatus;
        }

        public long getUserHandTime() {
            return userHandTime;
        }

        public void setUserHandTime(long userHandTime) {
            this.userHandTime = userHandTime;
        }

        public Object getNickName() {
            return nickName;
        }

        public void setNickName(Object nickName) {
            this.nickName = nickName;
        }

        public Object getPhone() {
            return phone;
        }

        public void setPhone(Object phone) {
            this.phone = phone;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }
    }
}
