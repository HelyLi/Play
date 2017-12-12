package com.hhly.lyygame.data.net.protocol.game;

import com.hhly.lyygame.data.net.protocol.BaseResp;

import java.util.List;

/**
 * Created by ${HELY} on 17/1/3.
 * 邮箱：heli.lixiong@gmail.com
 */

public class RecentlyGameListResp extends BaseResp {


    /**
     * pager : {"totalRows":1,"startRow":0,"pageSize":10,"totalPages":1,"list":[{"MIDTAB_ID":1,"gameType":8007,"COUNTRY":0,"gameId":10446,"TERMINAL":2,"gameName":"红警5","areaId":null,"serviceName":null,"imgUrl":"http://public.13322.com/4bfc6b48.png","playTime":1490955779000,"areaName":null,"serviceId":null,"userId":"hhly91599","ID":739,"sourUrl":"http://filelx.liqucn.com/upload/2017/yangcheng/bllxmxmwdg_liqu_balala_V1.2.5_net3_cmgc_dataeyeAndxg_cop_ulpay_02_22_1.2.5_70.apk"}],"pageNo":"1","pageScale":"10"}
     */

    private MyGamePage pager;

    public MyGamePage getPage() {
        return pager;
    }

    public void setPage(MyGamePage pager) {
        this.pager = pager;
    }

    public static class MyGamePage {
        /**
         * totalRows : 1
         * startRow : 0
         * pageSize : 10
         * totalPages : 1
         * list : [{"MIDTAB_ID":1,"gameType":8007,"COUNTRY":0,"gameId":10446,"TERMINAL":2,"gameName":"红警5","areaId":null,"serviceName":null,"imgUrl":"http://public.13322.com/4bfc6b48.png","playTime":1490955779000,"areaName":null,"serviceId":null,"userId":"hhly91599","ID":739,"sourUrl":"http://filelx.liqucn.com/upload/2017/yangcheng/bllxmxmwdg_liqu_balala_V1.2.5_net3_cmgc_dataeyeAndxg_cop_ulpay_02_22_1.2.5_70.apk"}]
         * pageNo : 1
         * pageScale : 10
         */

        private int totalRows;
        private int startRow;
        private int pageSize;
        private int totalPages;
        private String pageNo;
        private String pageScale;
        private List<MyGameInfo> list;

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

        public List<MyGameInfo> getList() {
            return list;
        }

        public void setList(List<MyGameInfo> list) {
            this.list = list;
        }

        public static class MyGameInfo {

            /**
             * MIDTAB_ID : 1
             * gameType : 8007
             * COUNTRY : 0
             * gameId : 10446
             * TERMINAL : 2
             * gameName : 红警5
             * areaId : null
             * serviceName : null
             * imgUrl : http://public.13322.com/4bfc6b48.png
             * playTime : 1490955779000
             * areaName : null
             * serviceId : null
             * userId : hhly91599
             * ID : 739
             * sourUrl : http://filelx.liqucn.com/upload/2017/yangcheng/bllxmxmwdg_liqu_balala_V1.2.5_net3_cmgc_dataeyeAndxg_cop_ulpay_02_22_1.2.5_70.apk
             */

            private int MIDTAB_ID;
            private int gameType;
            private int COUNTRY;
            private int gameId;
            private int TERMINAL;
            private String gameName;
            private Object areaId;
            private Object serviceName;
            private String imgUrl;
            private long playTime;
            private Object areaName;
            private Object serviceId;
            private String userId;
            private int ID;
            private String sourUrl;

            public int getMIDTAB_ID() {
                return MIDTAB_ID;
            }

            public void setMIDTAB_ID(int MIDTAB_ID) {
                this.MIDTAB_ID = MIDTAB_ID;
            }

            public int getGameType() {
                return gameType;
            }

            public void setGameType(int gameType) {
                this.gameType = gameType;
            }

            public int getCOUNTRY() {
                return COUNTRY;
            }

            public void setCOUNTRY(int COUNTRY) {
                this.COUNTRY = COUNTRY;
            }

            public int getGameId() {
                return gameId;
            }

            public void setGameId(int gameId) {
                this.gameId = gameId;
            }

            public int getTERMINAL() {
                return TERMINAL;
            }

            public void setTERMINAL(int TERMINAL) {
                this.TERMINAL = TERMINAL;
            }

            public String getGameName() {
                return gameName;
            }

            public void setGameName(String gameName) {
                this.gameName = gameName;
            }

            public Object getAreaId() {
                return areaId;
            }

            public void setAreaId(Object areaId) {
                this.areaId = areaId;
            }

            public Object getServiceName() {
                return serviceName;
            }

            public void setServiceName(Object serviceName) {
                this.serviceName = serviceName;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public long getPlayTime() {
                return playTime;
            }

            public void setPlayTime(long playTime) {
                this.playTime = playTime;
            }

            public Object getAreaName() {
                return areaName;
            }

            public void setAreaName(Object areaName) {
                this.areaName = areaName;
            }

            public Object getServiceId() {
                return serviceId;
            }

            public void setServiceId(Object serviceId) {
                this.serviceId = serviceId;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public int getID() {
                return ID;
            }

            public void setID(int ID) {
                this.ID = ID;
            }

            public String getSourUrl() {
                return sourUrl;
            }

            public void setSourUrl(String sourUrl) {
                this.sourUrl = sourUrl;
            }
        }
    }
}
