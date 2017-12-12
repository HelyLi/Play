package com.hhly.lyygame.data.net.protocol.game;

import com.hhly.lyygame.data.net.protocol.BaseResp;

import java.util.List;

/**
 * Created by ${HELY} on 17/2/15.
 * 邮箱：heli.lixiong@gmail.com
 */

public class GamePictureInfoResp extends BaseResp {


    private Object list;
    private Object gpInfo;
    private GamePicPager pager;

    public Object getList() {
        return list;
    }

    public void setList(Object list) {
        this.list = list;
    }

    public Object getGpInfo() {
        return gpInfo;
    }

    public void setGpInfo(Object gpInfo) {
        this.gpInfo = gpInfo;
    }

    public GamePicPager getPager() {
        return pager;
    }

    public void setPager(GamePicPager pager) {
        this.pager = pager;
    }

    public static class GamePicPager {
        /**
         * totalRows : 1
         * startRow : 0
         * pageSize : 10
         * totalPages : 1
         * list : [{"id":405,"url":"http://public.13322.com/4b48ffce.jpg","jump_url":"http://mgame.1332255.com/#/car","type":2,"order":1,"remark":"生化危机 ","create_time":1481251908000,"platform_id":10145,"platformInfo":{"name":"生化危机 ","id":10145,"ip":"192.168.1.235","registUser":4,"registTime":1481251378000,"updateTime":1482215500000,"status":1,"port":"8000","timeout":3,"connType":"tcp","rate":1,"indexPageAddress":null,"gamePageAddress":null,"isRechargePlatform":1,"iceConnectType":null,"indexImage":null,"curtypeName":"积分","th_name":null,"vi_name":null,"in_name":null,"gameType":null,"sourUrl":null,"giftbagUrl":null,"officalwebUrl":null,"updateUser":null,"desc":null,"developers":null,"onlineTime":1480694400000,"platformTerminal":4,"platformType":1,"isOneself":0,"havingAs":0,"isRebate":0,"boutiqueImg":null,"hotImg":null,"hottraveltImg":null,"allImg":null,"country":null},"terminalsType":4,"creator":"mrdeepen","updator":null,"updateTime":null,"country":"0"}]
         * pageNo : 1
         * pageScale : null
         */

        private int totalRows;
        private int startRow;
        private int pageSize;
        private int totalPages;
        private String pageNo;
        private Object pageScale;
        private List<GamePic> list;

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

        public List<GamePic> getList() {
            return list;
        }

        public void setList(List<GamePic> list) {
            this.list = list;
        }

        public static class GamePic {
            /**
             * id : 405
             * url : http://public.13322.com/4b48ffce.jpg
             * jump_url : http://mgame.1332255.com/#/car
             * type : 2
             * order : 1
             * remark : 生化危机
             * create_time : 1481251908000
             * platform_id : 10145
             * platformInfo : {"name":"生化危机 ","id":10145,"ip":"192.168.1.235","registUser":4,"registTime":1481251378000,"updateTime":1482215500000,"status":1,"port":"8000","timeout":3,"connType":"tcp","rate":1,"indexPageAddress":null,"gamePageAddress":null,"isRechargePlatform":1,"iceConnectType":null,"indexImage":null,"curtypeName":"积分","th_name":null,"vi_name":null,"in_name":null,"gameType":null,"sourUrl":null,"giftbagUrl":null,"officalwebUrl":null,"updateUser":null,"desc":null,"developers":null,"onlineTime":1480694400000,"platformTerminal":4,"platformType":1,"isOneself":0,"havingAs":0,"isRebate":0,"boutiqueImg":null,"hotImg":null,"hottraveltImg":null,"allImg":null,"country":null}
             * terminalsType : 4
             * creator : mrdeepen
             * updator : null
             * updateTime : null
             * country : 0
             */

            private int id;
            private String url;
            private String jump_url;
            private int type;
            private int order;
            private String remark;
            private long create_time;
            private int platform_id;
            private PlatformInfoBean platformInfo;
            private int terminalsType;
            private String creator;
            private Object updator;
            private Object updateTime;
            private String country;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getJump_url() {
                return jump_url;
            }

            public void setJump_url(String jump_url) {
                this.jump_url = jump_url;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getOrder() {
                return order;
            }

            public void setOrder(int order) {
                this.order = order;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public long getCreate_time() {
                return create_time;
            }

            public void setCreate_time(long create_time) {
                this.create_time = create_time;
            }

            public int getPlatform_id() {
                return platform_id;
            }

            public void setPlatform_id(int platform_id) {
                this.platform_id = platform_id;
            }

            public PlatformInfoBean getPlatformInfo() {
                return platformInfo;
            }

            public void setPlatformInfo(PlatformInfoBean platformInfo) {
                this.platformInfo = platformInfo;
            }

            public int getTerminalsType() {
                return terminalsType;
            }

            public void setTerminalsType(int terminalsType) {
                this.terminalsType = terminalsType;
            }

            public String getCreator() {
                return creator;
            }

            public void setCreator(String creator) {
                this.creator = creator;
            }

            public Object getUpdator() {
                return updator;
            }

            public void setUpdator(Object updator) {
                this.updator = updator;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public static class PlatformInfoBean {
                /**
                 * name : 生化危机
                 * id : 10145
                 * ip : 192.168.1.235
                 * registUser : 4
                 * registTime : 1481251378000
                 * updateTime : 1482215500000
                 * status : 1
                 * port : 8000
                 * timeout : 3
                 * connType : tcp
                 * rate : 1.0
                 * indexPageAddress : null
                 * gamePageAddress : null
                 * isRechargePlatform : 1
                 * iceConnectType : null
                 * indexImage : null
                 * curtypeName : 积分
                 * th_name : null
                 * vi_name : null
                 * in_name : null
                 * gameType : null
                 * sourUrl : null
                 * giftbagUrl : null
                 * officalwebUrl : null
                 * updateUser : null
                 * desc : null
                 * developers : null
                 * onlineTime : 1480694400000
                 * platformTerminal : 4
                 * platformType : 1
                 * isOneself : 0
                 * havingAs : 0
                 * isRebate : 0
                 * boutiqueImg : null
                 * hotImg : null
                 * hottraveltImg : null
                 * allImg : null
                 * country : null
                 */

                private String name;
                private int id;
                private String ip;
                private int registUser;
                private long registTime;
                private long updateTime;
                private int status;
                private String port;
                private int timeout;
                private String connType;
                private double rate;
                private String indexPageAddress;
                private String gamePageAddress;
                private int isRechargePlatform;
                private String iceConnectType;
                private String indexImage;
                private String curtypeName;
                private String th_name;
                private String vi_name;
                private String in_name;
                private String gameType;
                private String sourUrl;
                private String giftbagUrl;
                private String officalwebUrl;
                private String updateUser;
                private String desc;
                private String developers;
                private long onlineTime;
                private int platformTerminal;
                private int platformType;
                private int isOneself;
                private int havingAs;
                private int isRebate;
                private String boutiqueImg;
                private String hotImg;
                private String hottraveltImg;
                private String allImg;
                private String country;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getIp() {
                    return ip;
                }

                public void setIp(String ip) {
                    this.ip = ip;
                }

                public int getRegistUser() {
                    return registUser;
                }

                public void setRegistUser(int registUser) {
                    this.registUser = registUser;
                }

                public long getRegistTime() {
                    return registTime;
                }

                public void setRegistTime(long registTime) {
                    this.registTime = registTime;
                }

                public long getUpdateTime() {
                    return updateTime;
                }

                public void setUpdateTime(long updateTime) {
                    this.updateTime = updateTime;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public String getPort() {
                    return port;
                }

                public void setPort(String port) {
                    this.port = port;
                }

                public int getTimeout() {
                    return timeout;
                }

                public void setTimeout(int timeout) {
                    this.timeout = timeout;
                }

                public String getConnType() {
                    return connType;
                }

                public void setConnType(String connType) {
                    this.connType = connType;
                }

                public double getRate() {
                    return rate;
                }

                public void setRate(double rate) {
                    this.rate = rate;
                }

                public String getIndexPageAddress() {
                    return indexPageAddress;
                }

                public void setIndexPageAddress(String indexPageAddress) {
                    this.indexPageAddress = indexPageAddress;
                }

                public String getGamePageAddress() {
                    return gamePageAddress;
                }

                public void setGamePageAddress(String gamePageAddress) {
                    this.gamePageAddress = gamePageAddress;
                }

                public int getIsRechargePlatform() {
                    return isRechargePlatform;
                }

                public void setIsRechargePlatform(int isRechargePlatform) {
                    this.isRechargePlatform = isRechargePlatform;
                }

                public String getIceConnectType() {
                    return iceConnectType;
                }

                public void setIceConnectType(String iceConnectType) {
                    this.iceConnectType = iceConnectType;
                }

                public String getIndexImage() {
                    return indexImage;
                }

                public void setIndexImage(String indexImage) {
                    this.indexImage = indexImage;
                }

                public String getCurtypeName() {
                    return curtypeName;
                }

                public void setCurtypeName(String curtypeName) {
                    this.curtypeName = curtypeName;
                }

                public String getTh_name() {
                    return th_name;
                }

                public void setTh_name(String th_name) {
                    this.th_name = th_name;
                }

                public String getVi_name() {
                    return vi_name;
                }

                public void setVi_name(String vi_name) {
                    this.vi_name = vi_name;
                }

                public String getIn_name() {
                    return in_name;
                }

                public void setIn_name(String in_name) {
                    this.in_name = in_name;
                }

                public String getGameType() {
                    return gameType;
                }

                public void setGameType(String gameType) {
                    this.gameType = gameType;
                }

                public String getSourUrl() {
                    return sourUrl;
                }

                public void setSourUrl(String sourUrl) {
                    this.sourUrl = sourUrl;
                }

                public String getGiftbagUrl() {
                    return giftbagUrl;
                }

                public void setGiftbagUrl(String giftbagUrl) {
                    this.giftbagUrl = giftbagUrl;
                }

                public String getOfficalwebUrl() {
                    return officalwebUrl;
                }

                public void setOfficalwebUrl(String officalwebUrl) {
                    this.officalwebUrl = officalwebUrl;
                }

                public String getUpdateUser() {
                    return updateUser;
                }

                public void setUpdateUser(String updateUser) {
                    this.updateUser = updateUser;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getDevelopers() {
                    return developers;
                }

                public void setDevelopers(String developers) {
                    this.developers = developers;
                }

                public long getOnlineTime() {
                    return onlineTime;
                }

                public void setOnlineTime(long onlineTime) {
                    this.onlineTime = onlineTime;
                }

                public int getPlatformTerminal() {
                    return platformTerminal;
                }

                public void setPlatformTerminal(int platformTerminal) {
                    this.platformTerminal = platformTerminal;
                }

                public int getPlatformType() {
                    return platformType;
                }

                public void setPlatformType(int platformType) {
                    this.platformType = platformType;
                }

                public int getIsOneself() {
                    return isOneself;
                }

                public void setIsOneself(int isOneself) {
                    this.isOneself = isOneself;
                }

                public int getHavingAs() {
                    return havingAs;
                }

                public void setHavingAs(int havingAs) {
                    this.havingAs = havingAs;
                }

                public int getIsRebate() {
                    return isRebate;
                }

                public void setIsRebate(int isRebate) {
                    this.isRebate = isRebate;
                }

                public String getBoutiqueImg() {
                    return boutiqueImg;
                }

                public void setBoutiqueImg(String boutiqueImg) {
                    this.boutiqueImg = boutiqueImg;
                }

                public String getHotImg() {
                    return hotImg;
                }

                public void setHotImg(String hotImg) {
                    this.hotImg = hotImg;
                }

                public String getHottraveltImg() {
                    return hottraveltImg;
                }

                public void setHottraveltImg(String hottraveltImg) {
                    this.hottraveltImg = hottraveltImg;
                }

                public String getAllImg() {
                    return allImg;
                }

                public void setAllImg(String allImg) {
                    this.allImg = allImg;
                }

                public String getCountry() {
                    return country;
                }

                public void setCountry(String country) {
                    this.country = country;
                }
            }
        }
    }

}
