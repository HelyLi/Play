package com.hhly.lyygame.data.net.protocol.game;

import com.hhly.lyygame.data.net.protocol.BaseResp;

import java.util.List;

/**
 * Created by ${HELY} on 17/1/3.
 * 邮箱：heli.lixiong@gmail.com
 */

public class NewsListResp extends BaseResp {

    private NewsPage pager;

    public NewsPage getPager() {
        return pager;
    }

    public void setPager(NewsPage pager) {
        this.pager = pager;
    }

    public static class NewsPage {
        /**
         * totalRows : 1
         * startRow : 0
         * pageSize : 10
         * totalPages : 1
         * list : [{"id":287,"title":"我国中东部大降温或狂跌16℃ 10省市迎初雪","creatorTime":1479722553000,"creator":"mrdeepen","type":1,"status":1,"content":"&nbsp; &nbsp;&nbsp;<span style=\"font-family:'Microsoft Yahei', Helvetica, sans-serif;font-size:16px;line-height:28px;background-color:#FFFFFF;\">中国天气网讯 从今天（21日）起，下半年来最强的冷空气影响将正式发威，中央气象台为此发布了寒潮预警。受寒潮天气影响，21-24日，我国中东部大部自北向南将普遍出现6-10℃的降温和4-6级大风天气，局地降温幅度可超16℃，此次寒潮天气将致使我国多地出现今年入冬以来最低气温。在此期间，北京、天津、石家庄、太原、西安、郑州、济南、武汉、合肥、南京等十大省会级城市将陆续迎来2016年初雪。<\/span><a target=\"_blank\" href=\"http://news.qq.com/a/20161121/002145.htm#p=1\">&gt;&gt;北京迎来今冬初雪 网友纷纷晒雪景<\/a>","isTop":1,"terminalsType":2,"country":"0","explain":"我国中东部大降温或狂跌16℃ 10省市迎初雪123456","imageUrl":"http://public.13322.com/2a0010d.jpg"}]
         * pageNo : 1
         */

        private int totalRows;
        private int startRow;
        private int pageSize;
        private int totalPages;
        private String pageNo;
        private List<NewsInfo> list;

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

        public List<NewsInfo> getList() {
            return list;
        }

        public void setList(List<NewsInfo> list) {
            this.list = list;
        }

        public static class NewsInfo {
            /**
             * id : 287
             * title : 我国中东部大降温或狂跌16℃ 10省市迎初雪
             * creatorTime : 1479722553000
             * creator : mrdeepen
             * type : 1
             * status : 1
             * content : &nbsp; &nbsp;&nbsp;<span style="font-family:'Microsoft Yahei', Helvetica, sans-serif;font-size:16px;line-height:28px;background-color:#FFFFFF;">中国天气网讯 从今天（21日）起，下半年来最强的冷空气影响将正式发威，中央气象台为此发布了寒潮预警。受寒潮天气影响，21-24日，我国中东部大部自北向南将普遍出现6-10℃的降温和4-6级大风天气，局地降温幅度可超16℃，此次寒潮天气将致使我国多地出现今年入冬以来最低气温。在此期间，北京、天津、石家庄、太原、西安、郑州、济南、武汉、合肥、南京等十大省会级城市将陆续迎来2016年初雪。</span><a target="_blank" href="http://news.qq.com/a/20161121/002145.htm#p=1">&gt;&gt;北京迎来今冬初雪 网友纷纷晒雪景</a>
             * isTop : 1
             * terminalsType : 2
             * country : 0
             * explain : 我国中东部大降温或狂跌16℃ 10省市迎初雪123456
             * imageUrl : http://public.13322.com/2a0010d.jpg
             */

            private int id;
            private String title;
            private long creatorTime;
            private String creator;
            private int type;
            private int status;
            private String content;
            private int isTop;
            private int terminalsType;
            private String country;
            private String explain;
            private String imageUrl;

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

            public int getTerminalsType() {
                return terminalsType;
            }

            public void setTerminalsType(int terminalsType) {
                this.terminalsType = terminalsType;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public String getExplain() {
                return explain;
            }

            public void setExplain(String explain) {
                this.explain = explain;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }
        }
    }
}
