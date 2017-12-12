package com.hhly.lyygame.data.net.protocol.banner;

import com.google.gson.annotations.SerializedName;
import com.hhly.lyygame.data.net.protocol.BaseResp;

import java.util.List;

/**
 * Created by Simon on 2016/12/7.
 */

public class HomeBannerResp extends BaseResp {

    @SerializedName("picture")
    private Object picture;
    @SerializedName("pager")
    private Pager pager;

    public Object getPicture() {
        return picture;
    }

    public void setPicture(Object picture) {
        this.picture = picture;
    }

    public Pager getPager() {
        return pager;
    }

    public void setPager(Pager pager) {
        this.pager = pager;
    }

    public static class Pager {

        @SerializedName("totalRows")
        private int totalRows;
        @SerializedName("startRow")
        private int startRow;
        @SerializedName("pageSize")
        private int pageSize;
        @SerializedName("totalPages")
        private int totalPages;
        @SerializedName("pageNo")
        private String pageNo;
        @SerializedName("pageScale")
        private Object pageScale;
        @SerializedName("list")
        private List<BannerInfo> list;

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

        public Object getPageScale() {
            return pageScale;
        }

        public void setPageScale(Object pageScale) {
            this.pageScale = pageScale;
        }

        public List<BannerInfo> getList() {
            return list;
        }

        public void setList(List<BannerInfo> list) {
            this.list = list;
        }

        @Override
        public String toString() {
            return "Pager{" +
                    "totalRows=" + totalRows +
                    ", startRow=" + startRow +
                    ", pageSize=" + pageSize +
                    ", totalPages=" + totalPages +
                    ", pageNo='" + pageNo + '\'' +
                    ", pageScale=" + pageScale +
                    ", list=" + list +
                    '}';
        }

        public static class BannerInfo {

            @SerializedName("id")
            private int id;
            @SerializedName("time")
            private long time;
            @SerializedName("pictureUrl")
            private String pictureUrl;
            @SerializedName("jumpUrl")
            private String jumpUrl;
            @SerializedName("pictureOrder")
            private int pictureOrder;
            @SerializedName("remarks")
            private String remarks;
            @SerializedName("country")
            private String country;
            @SerializedName("platformTerminal")
            private int platformTerminal;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public String getPictureUrl() {
                return pictureUrl;
            }

            public void setPictureUrl(String pictureUrl) {
                this.pictureUrl = pictureUrl;
            }

            public String getJumpUrl() {
                return jumpUrl;
            }

            public void setJumpUrl(String jumpUrl) {
                this.jumpUrl = jumpUrl;
            }

            public int getPictureOrder() {
                return pictureOrder;
            }

            public void setPictureOrder(int pictureOrder) {
                this.pictureOrder = pictureOrder;
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

            public int getPlatformTerminal() {
                return platformTerminal;
            }

            public void setPlatformTerminal(int platformTerminal) {
                this.platformTerminal = platformTerminal;
            }
        }
    }

    @Override
    public String toString() {
        return "HomeBannerResp{" +
                "picture=" + picture +
                ", pager=" + pager +
                '}';
    }
}
