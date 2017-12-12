package com.hhly.lyygame.data.net.protocol.user;

import java.util.List;

/**
 * Created by ${HELY} on 17/2/9.
 * 邮箱：heli.lixiong@gmail.com
 */

public class MsgPager {

    private int totalRows;
    private int startRow;
    private int pageSize;
    private int totalPages;
    private String pageNo;
    private String pageScale;
    private List<MsgBean> list;

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getPageScale() {
        return pageScale;
    }

    public void setPageScale(String pageScale) {
        this.pageScale = pageScale;
    }

    public List<MsgBean> getList() {
        return list;
    }

    public void setList(List<MsgBean> list) {
        this.list = list;
    }

    public static class MsgBean implements Comparable<MsgBean>{

        private int id;
        private String title;
        private String content;
        private long time;
        private int creator;
        private String titleHref;
        private int type;
        private int status;
        private long handTime;
        private String image;
        private int isMass;
        private int plateFormId;
        private int stick;
        private String synopsis;
        private String userId;
        private int isPush;
        private int language;
        private int readStatus;//0:已读；1:未读

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

        public String getTitleHref() {
            return titleHref;
        }

        public void setTitleHref(String titleHref) {
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

        public long getHandTime() {
            return handTime;
        }

        public void setHandTime(long handTime) {
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

        public int getIsPush() {
            return isPush;
        }

        public void setIsPush(int isPush) {
            this.isPush = isPush;
        }

        public int getLanguage() {
            return language;
        }

        public void setLanguage(int language) {
            this.language = language;
        }

        public int getReadStatus() {
            return readStatus;
        }

        public void setReadStatus(int readStatus) {
            this.readStatus = readStatus;
        }

        @Override
        public int compareTo(MsgBean msgBean) {
            return msgBean.getTime() > this.getTime()? 1 : (msgBean.getTime() == this.getTime() ? 0 : -1);
        }
    }

}
