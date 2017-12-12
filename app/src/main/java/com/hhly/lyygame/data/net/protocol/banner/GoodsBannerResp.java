package com.hhly.lyygame.data.net.protocol.banner;

import com.hhly.lyygame.data.net.protocol.BaseResp;

import java.util.List;

/**
 * Created by ${HELY} on 17/2/5.
 * 邮箱：heli.lixiong@gmail.com
 */

public class GoodsBannerResp extends BaseResp {

    private int total;
    private List<GoodsBanner> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<GoodsBanner> getList() {
        return list;
    }

    public void setList(List<GoodsBanner> list) {
        this.list = list;
    }

    public static class GoodsBanner {

        private int rotationId;
        private int country;
        private int position;
        private String detail;
        private String imageUrl;
        private String linkUrl;
        private int enable;
        private long createTime;
        private long updateTime;
        private int platform;

        public int getRotationId() {
            return rotationId;
        }

        public void setRotationId(int rotationId) {
            this.rotationId = rotationId;
        }

        public int getCountry() {
            return country;
        }

        public void setCountry(int country) {
            this.country = country;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getLinkUrl() {
            return linkUrl;
        }

        public void setLinkUrl(String linkUrl) {
            this.linkUrl = linkUrl;
        }

        public int getEnable() {
            return enable;
        }

        public void setEnable(int enable) {
            this.enable = enable;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public int getPlatform() {
            return platform;
        }

        public void setPlatform(int platform) {
            this.platform = platform;
        }
    }

    @Override
    public String toString() {
        return "GoodsBannerResp{" +
                "total=" + total +
                ", list=" + list +
                '}';
    }
}
