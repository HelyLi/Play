package com.hhly.lyygame.data.net.protocol.game;

import com.hhly.lyygame.data.net.protocol.BaseResp;

/**
 * Created by ${HELY} on 17/2/17.
 * 邮箱：heli.lixiong@gmail.com
 */

public class NoticeDetailResp extends BaseResp {

    private NoticeDetail notice;
    private Object pager;

    public NoticeDetail getNotice() {
        return notice;
    }

    public void setNotice(NoticeDetail notice) {
        this.notice = notice;
    }

    public Object getPager() {
        return pager;
    }

    public void setPager(Object pager) {
        this.pager = pager;
    }

    public static class NoticeDetail {

        private int id;
        private String title;
        private long creatorTime;
        private String creator;
        private int type;
        private int status;
        private long handTime;
        private String content;
        private int isTop;
        private String titleHref;
        private int terminalsType;
        private String remarks;
        private String country;
        private int plateformId;
        private int isHref;
        private String imgUrl;

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

        public long getCreatorTime() {
            return creatorTime;
        }

        public void setCreatorTime(long creatorTime) {
            this.creatorTime = creatorTime;
        }

        public String getCreator() {
            return creator;
        }

        public void setCreator(String creator) {
            this.creator = creator;
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

        public long getHandTime() {
            return handTime;
        }

        public void setHandTime(long handTime) {
            this.handTime = handTime;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getIsTop() {
            return isTop;
        }

        public void setIsTop(int isTop) {
            this.isTop = isTop;
        }

        public String getTitleHref() {
            return titleHref;
        }

        public void setTitleHref(String titleHref) {
            this.titleHref = titleHref;
        }

        public int getTerminalsType() {
            return terminalsType;
        }

        public void setTerminalsType(int terminalsType) {
            this.terminalsType = terminalsType;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public int getPlateformId() {
            return plateformId;
        }

        public void setPlateformId(int plateformId) {
            this.plateformId = plateformId;
        }

        public int getIsHref() {
            return isHref;
        }

        public void setIsHref(int isHref) {
            this.isHref = isHref;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }
    }
}
