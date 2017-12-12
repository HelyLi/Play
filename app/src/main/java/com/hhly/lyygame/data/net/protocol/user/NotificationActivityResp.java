package com.hhly.lyygame.data.net.protocol.user;

import com.hhly.lyygame.data.net.protocol.BaseResp;

import java.util.List;

/**
 * Created by ${HELY} on 17/2/23.
 * 邮箱：heli.lixiong@gmail.com
 */

public class NotificationActivityResp extends BaseResp {

    /**
     * msg : null
     * activity : [{"id":282,"actType":2,"title":"请你一起看侏罗纪","rotationImgUrl":"http://public.13322.com/43611286.jpg","startDate":1487174400000,"endDate":1498752000000,"type":1,"imgUrl":null,"redirectUrl":"http://baidu.com","description":"1积分","weight":null,"createTime":null,"country":0,"remark":"门票有限，要来干脆","platform":4,"game":10145,"area":null,"server":null,"position":1,"gameInfo":{"name":"生化危机 ","id":10145,"ip":"192.168.1.235","registUser":4,"registTime":1481251378000,"updateTime":1482215500000,"status":1,"port":"8000","timeout":3,"connType":"tcp","rate":1,"indexPageAddress":null,"gamePageAddress":null,"isRechargePlatform":1,"iceConnectType":null,"indexImage":null,"curtypeName":"积分","th_name":null,"vi_name":null,"in_name":null,"gameType":null,"sourUrl":null,"giftbagUrl":null,"officalwebUrl":null,"updateUser":null,"desc":null,"developers":null,"onlineTime":1480694400000,"platformTerminal":4,"platformType":1,"isOneself":0,"havingAs":0,"isRebate":0,"boutiqueImg":"http://public.13322.com/263abe2e.jpg","hotImg":"http://public.13322.com/7a9c0c6b.jpg","hottraveltImg":"http://public.13322.com/2c122f7f.jpg","allImg":"http://public.13322.com/4375243.jpg"}},{"id":239,"actType":1,"title":"ipad","rotationImgUrl":"http://public.13322.com/5d62861a.jpg","startDate":1486137600000,"endDate":1504108800000,"type":1,"imgUrl":null,"redirectUrl":"http://weibo.com/ttarticle/p/show?id=2309351002674058319273774796&u=6078428072&m=4058319289305584&cu=6078428072","description":" 欢迎前来挑战！领取你的专属！","weight":null,"createTime":null,"country":0,"remark":"\r\n欢迎前来挑战！领取你的专属！","platform":4,"game":10342,"area":null,"server":null,"position":null,"gameInfo":{"name":"乐盈电竞","id":10342,"ip":"127.0.0.1","registUser":5,"registTime":1484900980000,"updateTime":1484901238000,"status":1,"port":"8000","timeout":3,"connType":"tcp","rate":null,"indexPageAddress":null,"gamePageAddress":null,"isRechargePlatform":1,"iceConnectType":null,"indexImage":null,"curtypeName":null,"th_name":null,"vi_name":null,"in_name":null,"gameType":null,"sourUrl":null,"giftbagUrl":null,"officalwebUrl":null,"updateUser":null,"desc":null,"developers":null,"onlineTime":1485619200000,"platformTerminal":4,"platformType":1,"isOneself":0,"havingAs":0,"isRebate":0,"boutiqueImg":"http://public.13322.com/7f1a3ec1.jpg","hotImg":"http://public.13322.com/6800034a.jpg","hottraveltImg":"http://public.13322.com/572d1d88.jpg","allImg":"http://public.13322.com/43ecf7c6.jpg"}},{"id":159,"actType":2,"title":"兰博基尼试跑","rotationImgUrl":"http://public.13322.com/647d73bf.jpg","startDate":1480694400000,"endDate":1496160000000,"type":1,"imgUrl":null,"redirectUrl":"http://mgame.1332255.com/#/car","description":"德国机械有多么无敌","weight":null,"createTime":null,"country":0,"remark":"德国机械有多么无敌","platform":4,"game":10163,"area":null,"server":null,"position":null,"gameInfo":{"name":"死侍","id":10163,"ip":"192.168.1.235","registUser":4,"registTime":1482216999000,"updateTime":1487386449000,"status":1,"port":"8000","timeout":3,"connType":"tcp","rate":null,"indexPageAddress":null,"gamePageAddress":null,"isRechargePlatform":1,"iceConnectType":null,"indexImage":null,"curtypeName":null,"th_name":null,"vi_name":null,"in_name":null,"gameType":null,"sourUrl":null,"giftbagUrl":null,"officalwebUrl":null,"updateUser":null,"desc":null,"developers":null,"onlineTime":1480694400000,"platformTerminal":4,"platformType":1,"isOneself":0,"havingAs":0,"isRebate":0,"boutiqueImg":"http://public.13322.com/10890623.jpg","hotImg":"http://public.13322.com/5a7a5a31.jpg","hottraveltImg":"http://public.13322.com/1c718285.jpg","allImg":"http://public.13322.com/73d2502c.jpg"}}]
     * total : 3
     */

    private int total;
    private List<ActivityBean> activity;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ActivityBean> getActivity() {
        return activity;
    }

    public void setActivity(List<ActivityBean> activity) {
        this.activity = activity;
    }

    public static class ActivityBean {
        /**
         * id : 282
         * actType : 2
         * title : 请你一起看侏罗纪
         * rotationImgUrl : http://public.13322.com/43611286.jpg
         * startDate : 1487174400000
         * endDate : 1498752000000
         * type : 1
         * imgUrl : null
         * redirectUrl : http://baidu.com
         * description : 1积分
         * weight : null
         * createTime : null
         * country : 0
         * remark : 门票有限，要来干脆
         * platform : 4
         * game : 10145
         * area : null
         * server : null
         * position : 1
         * gameInfo : {"name":"生化危机 ","id":10145,"ip":"192.168.1.235","registUser":4,"registTime":1481251378000,"updateTime":1482215500000,"status":1,"port":"8000","timeout":3,"connType":"tcp","rate":1,"indexPageAddress":null,"gamePageAddress":null,"isRechargePlatform":1,"iceConnectType":null,"indexImage":null,"curtypeName":"积分","th_name":null,"vi_name":null,"in_name":null,"gameType":null,"sourUrl":null,"giftbagUrl":null,"officalwebUrl":null,"updateUser":null,"desc":null,"developers":null,"onlineTime":1480694400000,"platformTerminal":4,"platformType":1,"isOneself":0,"havingAs":0,"isRebate":0,"boutiqueImg":"http://public.13322.com/263abe2e.jpg","hotImg":"http://public.13322.com/7a9c0c6b.jpg","hottraveltImg":"http://public.13322.com/2c122f7f.jpg","allImg":"http://public.13322.com/4375243.jpg"}
         */

        private int id;
        private int actType;
        private String title;
        private String rotationImgUrl;
        private long startDate;
        private long endDate;
        private int type;
        private Object imgUrl;
        private String redirectUrl;
        private String description;
        private Object weight;
        private Object createTime;
        private int country;
        private String remark;
        private int platform;
        private int game;
        private Object area;
        private Object server;
        private int position;
        private GameInfoBean gameInfo;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getActType() {
            return actType;
        }

        public void setActType(int actType) {
            this.actType = actType;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getRotationImgUrl() {
            return rotationImgUrl;
        }

        public void setRotationImgUrl(String rotationImgUrl) {
            this.rotationImgUrl = rotationImgUrl;
        }

        public long getStartDate() {
            return startDate;
        }

        public void setStartDate(long startDate) {
            this.startDate = startDate;
        }

        public long getEndDate() {
            return endDate;
        }

        public void setEndDate(long endDate) {
            this.endDate = endDate;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public Object getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(Object imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getRedirectUrl() {
            return redirectUrl;
        }

        public void setRedirectUrl(String redirectUrl) {
            this.redirectUrl = redirectUrl;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Object getWeight() {
            return weight;
        }

        public void setWeight(Object weight) {
            this.weight = weight;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public int getCountry() {
            return country;
        }

        public void setCountry(int country) {
            this.country = country;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getPlatform() {
            return platform;
        }

        public void setPlatform(int platform) {
            this.platform = platform;
        }

        public int getGame() {
            return game;
        }

        public void setGame(int game) {
            this.game = game;
        }

        public Object getArea() {
            return area;
        }

        public void setArea(Object area) {
            this.area = area;
        }

        public Object getServer() {
            return server;
        }

        public void setServer(Object server) {
            this.server = server;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public GameInfoBean getGameInfo() {
            return gameInfo;
        }

        public void setGameInfo(GameInfoBean gameInfo) {
            this.gameInfo = gameInfo;
        }

        public static class GameInfoBean {
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
             * boutiqueImg : http://public.13322.com/263abe2e.jpg
             * hotImg : http://public.13322.com/7a9c0c6b.jpg
             * hottraveltImg : http://public.13322.com/2c122f7f.jpg
             * allImg : http://public.13322.com/4375243.jpg
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
            private Object indexPageAddress;
            private Object gamePageAddress;
            private int isRechargePlatform;
            private Object iceConnectType;
            private Object indexImage;
            private String curtypeName;
            private Object th_name;
            private Object vi_name;
            private Object in_name;
            private Object gameType;
            private Object sourUrl;
            private Object giftbagUrl;
            private Object officalwebUrl;
            private Object updateUser;
            private Object desc;
            private Object developers;
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

            public Object getIndexPageAddress() {
                return indexPageAddress;
            }

            public void setIndexPageAddress(Object indexPageAddress) {
                this.indexPageAddress = indexPageAddress;
            }

            public Object getGamePageAddress() {
                return gamePageAddress;
            }

            public void setGamePageAddress(Object gamePageAddress) {
                this.gamePageAddress = gamePageAddress;
            }

            public int getIsRechargePlatform() {
                return isRechargePlatform;
            }

            public void setIsRechargePlatform(int isRechargePlatform) {
                this.isRechargePlatform = isRechargePlatform;
            }

            public Object getIceConnectType() {
                return iceConnectType;
            }

            public void setIceConnectType(Object iceConnectType) {
                this.iceConnectType = iceConnectType;
            }

            public Object getIndexImage() {
                return indexImage;
            }

            public void setIndexImage(Object indexImage) {
                this.indexImage = indexImage;
            }

            public String getCurtypeName() {
                return curtypeName;
            }

            public void setCurtypeName(String curtypeName) {
                this.curtypeName = curtypeName;
            }

            public Object getTh_name() {
                return th_name;
            }

            public void setTh_name(Object th_name) {
                this.th_name = th_name;
            }

            public Object getVi_name() {
                return vi_name;
            }

            public void setVi_name(Object vi_name) {
                this.vi_name = vi_name;
            }

            public Object getIn_name() {
                return in_name;
            }

            public void setIn_name(Object in_name) {
                this.in_name = in_name;
            }

            public Object getGameType() {
                return gameType;
            }

            public void setGameType(Object gameType) {
                this.gameType = gameType;
            }

            public Object getSourUrl() {
                return sourUrl;
            }

            public void setSourUrl(Object sourUrl) {
                this.sourUrl = sourUrl;
            }

            public Object getGiftbagUrl() {
                return giftbagUrl;
            }

            public void setGiftbagUrl(Object giftbagUrl) {
                this.giftbagUrl = giftbagUrl;
            }

            public Object getOfficalwebUrl() {
                return officalwebUrl;
            }

            public void setOfficalwebUrl(Object officalwebUrl) {
                this.officalwebUrl = officalwebUrl;
            }

            public Object getUpdateUser() {
                return updateUser;
            }

            public void setUpdateUser(Object updateUser) {
                this.updateUser = updateUser;
            }

            public Object getDesc() {
                return desc;
            }

            public void setDesc(Object desc) {
                this.desc = desc;
            }

            public Object getDevelopers() {
                return developers;
            }

            public void setDevelopers(Object developers) {
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
        }
    }
}
