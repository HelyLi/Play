package com.hhly.lyygame.data.net.protocol.update;

import com.hhly.lyygame.data.net.protocol.BaseResp;

/**
 * Created by Simon on 2016/9/30.
 */

public class CheckUpdateResp  extends BaseResp {

    private UpdateInfo data;

    public UpdateInfo getData() {
        return data;
    }

    public void setData(UpdateInfo data) {
        this.data = data;
    }

    public static class UpdateInfo {

        private int country;
        private int id;
        private String appId;
        private String title;
        private int versionCode;
        private String url;
        private String content;
        private int updateType;
        private long createTime;

        public int getCountry() {
            return country;
        }

        public void setCountry(int country) {
            this.country = country;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(int versionCode) {
            this.versionCode = versionCode;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getUpdateType() {
            return updateType;
        }

        public void setUpdateType(int updateType) {
            this.updateType = updateType;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        @Override
        public String toString() {
            return "UpdateInfo{" +
                    "country=" + country +
                    ", id=" + id +
                    ", appId='" + appId + '\'' +
                    ", title='" + title + '\'' +
                    ", versionCode=" + versionCode +
                    ", url='" + url + '\'' +
                    ", content='" + content + '\'' +
                    ", updateType=" + updateType +
                    ", createTime=" + createTime +
                    '}';
        }
    }

}
