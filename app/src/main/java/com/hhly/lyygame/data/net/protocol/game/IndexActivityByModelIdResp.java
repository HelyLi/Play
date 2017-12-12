package com.hhly.lyygame.data.net.protocol.game;

import com.hhly.lyygame.data.net.protocol.BaseResp;

import java.util.List;

/**
 * Created by dell on 2017/5/15.
 */

public class IndexActivityByModelIdResp extends BaseResp {

    /**
     * data : {"totalRows":3,"pageNo":1,"pageSize":20,"list":[{"updater":"mrdeepen","createTime":"2017-05-13 10:24:56","ROTATIONIMGURL":"http://public.13322.com/528cbad3.jpg","sort":1,"DESCRIPTION":"积分1000","imageUrl":"http://public.13322.com/525fda68.jpg","updateTime":"2017-05-13 14:43:35","remark":"新赛事竞技","COUNTRY":0,"REMARK":"新赛事竞技","creater":"mrdeepen","REDIRECTURL":"http://baidu.com","STARTDATE":1489334400000,"PLATFORM":2,"ROWNUM_":1,"ID":319,"GAME":10441,"POSITION":1,"TYPE":1,"ACTTYPE":1,"ENDDATE":1501430400000,"TITLE":"新赛事竞技","IMGURL":"http://public.13322.com/4c6c128a.jpg"},{"updater":"mrdeepen","createTime":"2017-05-13 14:35:05","ROTATIONIMGURL":"http://public.13322.com/536228ef.gif","sort":2,"DESCRIPTION":"苹果商务笔记本电脑一部","imageUrl":"http://public.13322.com/4ea52290.gif","updateTime":"2017-05-13 14:35:05","remark":"老司鸡来了","COUNTRY":0,"REMARK":"老司鸡来了","creater":"mrdeepen","REDIRECTURL":"http://baidu.com","STARTDATE":1482854400000,"PLATFORM":4,"ROWNUM_":2,"ID":180,"GAME":10113,"IMGURL":"http://public.13322.com/4c6c128a.jpg","POSITION":5,"TYPE":1,"ACTTYPE":2,"ENDDATE":1483113600000,"TITLE":"老司鸡来了，请小心"},{"updater":"mrdeepen","createTime":"2017-05-13 14:41:02","ROTATIONIMGURL":"http://public.13322.com/3467f328.jpg","sort":3,"DESCRIPTION":"1111111111111111111111111111111111111111111111111111111111111111111111","imageUrl":"http://public.13322.com/19f7f1ab.jpg","updateTime":"2017-05-13 14:41:02","remark":"未来颠覆性技术有个很明显的发展趋势","COUNTRY":0,"REMARK":"未来颠覆性技术有个很明显的发展趋势","creater":"mrdeepen","REDIRECTURL":"http://baidu.com","STARTDATE":1491494400000,"PLATFORM":3,"ROWNUM_":3,"ID":359,"GAME":10212,"POSITION":1,"TYPE":1,"ACTTYPE":1,"ENDDATE":1509120000000,"TITLE":"未来颠覆性技术有个很明显的发展趋势"}],"totalPages":1}
     */

    private ActivityPage data;

    public ActivityPage getData() {
        return data;
    }

    public void setData(ActivityPage data) {
        this.data = data;
    }

    public static class ActivityPage {
        /**
         * totalRows : 3
         * pageNo : 1
         * pageSize : 20
         * list : [{"updater":"mrdeepen","createTime":"2017-05-13 10:24:56","ROTATIONIMGURL":"http://public.13322.com/528cbad3.jpg","sort":1,"DESCRIPTION":"积分1000","imageUrl":"http://public.13322.com/525fda68.jpg","updateTime":"2017-05-13 14:43:35","remark":"新赛事竞技","COUNTRY":0,"REMARK":"新赛事竞技","creater":"mrdeepen","REDIRECTURL":"http://baidu.com","STARTDATE":1489334400000,"PLATFORM":2,"ROWNUM_":1,"ID":319,"GAME":10441,"POSITION":1,"TYPE":1,"ACTTYPE":1,"ENDDATE":1501430400000,"TITLE":"新赛事竞技"},{"updater":"mrdeepen","createTime":"2017-05-13 14:35:05","ROTATIONIMGURL":"http://public.13322.com/536228ef.gif","sort":2,"DESCRIPTION":"苹果商务笔记本电脑一部","imageUrl":"http://public.13322.com/4ea52290.gif","updateTime":"2017-05-13 14:35:05","remark":"老司鸡来了","COUNTRY":0,"REMARK":"老司鸡来了","creater":"mrdeepen","REDIRECTURL":"http://baidu.com","STARTDATE":1482854400000,"PLATFORM":4,"ROWNUM_":2,"ID":180,"GAME":10113,"IMGURL":"http://public.13322.com/4c6c128a.jpg","POSITION":5,"TYPE":1,"ACTTYPE":2,"ENDDATE":1483113600000,"TITLE":"老司鸡来了，请小心"},{"updater":"mrdeepen","createTime":"2017-05-13 14:41:02","ROTATIONIMGURL":"http://public.13322.com/3467f328.jpg","sort":3,"DESCRIPTION":"1111111111111111111111111111111111111111111111111111111111111111111111","imageUrl":"http://public.13322.com/19f7f1ab.jpg","updateTime":"2017-05-13 14:41:02","remark":"未来颠覆性技术有个很明显的发展趋势","COUNTRY":0,"REMARK":"未来颠覆性技术有个很明显的发展趋势","creater":"mrdeepen","REDIRECTURL":"http://baidu.com","STARTDATE":1491494400000,"PLATFORM":3,"ROWNUM_":3,"ID":359,"GAME":10212,"POSITION":1,"TYPE":1,"ACTTYPE":1,"ENDDATE":1509120000000,"TITLE":"未来颠覆性技术有个很明显的发展趋势"}]
         * totalPages : 1
         */

        private int totalRows;
        private int pageNo;
        private int pageSize;
        private int totalPages;
        private List<ActivityInfo> list;

        public int getTotalRows() {
            return totalRows;
        }

        public void setTotalRows(int totalRows) {
            this.totalRows = totalRows;
        }

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
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

        public List<ActivityInfo> getList() {
            return list;
        }

        public void setList(List<ActivityInfo> list) {
            this.list = list;
        }

        public static class ActivityInfo {
            /**
             * updater : mrdeepen
             * createTime : 2017-05-13 10:24:56
             * ROTATIONIMGURL : http://public.13322.com/528cbad3.jpg
             * sort : 1
             * DESCRIPTION : 积分1000
             * imageUrl : http://public.13322.com/525fda68.jpg
             * updateTime : 2017-05-13 14:43:35
             * remark : 新赛事竞技
             * COUNTRY : 0
             * REMARK : 新赛事竞技
             * creater : mrdeepen
             * REDIRECTURL : http://baidu.com
             * STARTDATE : 1489334400000
             * PLATFORM : 2
             * ROWNUM_ : 1
             * ID : 319
             * GAME : 10441
             * POSITION : 1
             * TYPE : 1
             * ACTTYPE : 1
             * ENDDATE : 1501430400000
             * TITLE : 新赛事竞技
             * IMGURL : http://public.13322.com/4c6c128a.jpg
             */

            private String updater;
            private String createTime;
            private String ROTATIONIMGURL;
            private int sort;
            private String DESCRIPTION;
            private String imageUrl;
            private String updateTime;
            private String remark;
            private int COUNTRY;
            private String REMARK;
            private String creater;
            private String REDIRECTURL;
            private long STARTDATE;
            private int PLATFORM;
            private int ROWNUM_;
            private int ID;
            private int GAME;
            private int POSITION;
            private int TYPE;
            private int ACTTYPE;
            private long ENDDATE;
            private String TITLE;
            private String IMGURL;

            public String getUpdater() {
                return updater;
            }

            public void setUpdater(String updater) {
                this.updater = updater;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getROTATIONIMGURL() {
                return ROTATIONIMGURL;
            }

            public void setROTATIONIMGURL(String ROTATIONIMGURL) {
                this.ROTATIONIMGURL = ROTATIONIMGURL;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public String getDESCRIPTION() {
                return DESCRIPTION;
            }

            public void setDESCRIPTION(String DESCRIPTION) {
                this.DESCRIPTION = DESCRIPTION;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public int getCOUNTRY() {
                return COUNTRY;
            }

            public void setCOUNTRY(int COUNTRY) {
                this.COUNTRY = COUNTRY;
            }

            public String getREMARK() {
                return REMARK;
            }

            public void setREMARK(String REMARK) {
                this.REMARK = REMARK;
            }

            public String getCreater() {
                return creater;
            }

            public void setCreater(String creater) {
                this.creater = creater;
            }

            public String getREDIRECTURL() {
                return REDIRECTURL;
            }

            public void setREDIRECTURL(String REDIRECTURL) {
                this.REDIRECTURL = REDIRECTURL;
            }

            public long getSTARTDATE() {
                return STARTDATE;
            }

            public void setSTARTDATE(long STARTDATE) {
                this.STARTDATE = STARTDATE;
            }

            public int getPLATFORM() {
                return PLATFORM;
            }

            public void setPLATFORM(int PLATFORM) {
                this.PLATFORM = PLATFORM;
            }

            public int getROWNUM_() {
                return ROWNUM_;
            }

            public void setROWNUM_(int ROWNUM_) {
                this.ROWNUM_ = ROWNUM_;
            }

            public int getID() {
                return ID;
            }

            public void setID(int ID) {
                this.ID = ID;
            }

            public int getGAME() {
                return GAME;
            }

            public void setGAME(int GAME) {
                this.GAME = GAME;
            }

            public int getPOSITION() {
                return POSITION;
            }

            public void setPOSITION(int POSITION) {
                this.POSITION = POSITION;
            }

            public int getTYPE() {
                return TYPE;
            }

            public void setTYPE(int TYPE) {
                this.TYPE = TYPE;
            }

            public int getACTTYPE() {
                return ACTTYPE;
            }

            public void setACTTYPE(int ACTTYPE) {
                this.ACTTYPE = ACTTYPE;
            }

            public long getENDDATE() {
                return ENDDATE;
            }

            public void setENDDATE(long ENDDATE) {
                this.ENDDATE = ENDDATE;
            }

            public String getTITLE() {
                return TITLE;
            }

            public void setTITLE(String TITLE) {
                this.TITLE = TITLE;
            }

            public String getIMGURL() {
                return IMGURL;
            }

            public void setIMGURL(String IMGURL) {
                this.IMGURL = IMGURL;
            }
        }
    }
}
