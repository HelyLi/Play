package com.hhly.lyygame.data.net.protocol.game;

import com.hhly.lyygame.data.net.protocol.BaseResp;

import java.util.List;

/**
 * Created by ${HELY} on 17/2/15.
 * 邮箱：heli.lixiong@gmail.com
 */

public class GameByIdInfoResp extends BaseResp {
    /**
     * data : {"port":"8000","desc":"游戏apk：OSU音乐游戏\r\n蛛网发射器：帕克自己制作的蛛丝发射器配合特殊研发的蛛丝一起使用，蛛丝柔韧度极强且会在喷射出后在一个小时之后自动融化。\r\n蜘蛛战衣：蜘蛛侠的制服，封闭了视觉、嗅觉等，穿上战衣的蜘蛛侠完全通过蜘蛛感应来进行信息摄入。\r\n另外蜘蛛侠在不同的平行宇宙中，也配备有其它.","gameType":[{"gameType":"8003","gameTypeName":"战棋游戏"}],"packageName":"com.sunwater.majiang2","iconUrl":"http://public.13322.com/1206705f.png","versionCode":"V1.0","gameHallPic":"http://public.13322.com/286154ec.png","countryCurrency":"CNY","allImg":"http://public.13322.com/448cd843.png","id":10442,"name":"DOTA2 游戏礼包测试","sourUrl":"http://file.liqucn.com/upload/2013/qipai/com.sunwater.majiang2_2.6_liqucn.com.apk","POSITION":3,"giftbagUrl":"http://m.keyouxi.com/","boutiqueImg":"http://public.13322.com/347797ca.png","isOneself":0,"hotImg":"http://public.13322.com/3c665de6.png","status":1,"updateTime":1492138419000,"conntype":"ssh","developers":"乐视","havingGameas":0,"packageSize":"6M","country":0,"ip":" ","isRebate":0,"popularitVal":44,"officalwebUrl":"http://m.keyouxi.com/","isRechtoplatform":1,"platformType":1,"onlineTime":1486915200000,"hottraveltImg":"http://public.13322.com/6e9924d.png","platformTerminal":2,"alphabet":"aa","tittleimgUrl":"http://public.13322.com/43c24d52.png","timeout":3,"registTime":1488275128000,"updateUser":"mrdeepen"}
     */

    private GameByIdInfo data;

    public GameByIdInfo getData() {
        return data;
    }

    public void setData(GameByIdInfo data) {
        this.data = data;
    }

    public static class GameByIdInfo {
        /**
         * port : 8000
         * desc : 游戏apk：OSU音乐游戏
         蛛网发射器：帕克自己制作的蛛丝发射器配合特殊研发的蛛丝一起使用，蛛丝柔韧度极强且会在喷射出后在一个小时之后自动融化。
         蜘蛛战衣：蜘蛛侠的制服，封闭了视觉、嗅觉等，穿上战衣的蜘蛛侠完全通过蜘蛛感应来进行信息摄入。
         另外蜘蛛侠在不同的平行宇宙中，也配备有其它.
         * gameType : [{"gameType":"8003","gameTypeName":"战棋游戏"}]
         * packageName : com.sunwater.majiang2
         * iconUrl : http://public.13322.com/1206705f.png
         * versionCode : V1.0
         * gameHallPic : http://public.13322.com/286154ec.png
         * countryCurrency : CNY
         * allImg : http://public.13322.com/448cd843.png
         * id : 10442
         * name : DOTA2 游戏礼包测试
         * sourUrl : http://file.liqucn.com/upload/2013/qipai/com.sunwater.majiang2_2.6_liqucn.com.apk
         * POSITION : 3
         * giftbagUrl : http://m.keyouxi.com/
         * boutiqueImg : http://public.13322.com/347797ca.png
         * isOneself : 0
         * hotImg : http://public.13322.com/3c665de6.png
         * status : 1
         * updateTime : 1492138419000
         * conntype : ssh
         * developers : 乐视
         * havingGameas : 0
         * packageSize : 6M
         * country : 0
         * ip :
         * isRebate : 0
         * popularitVal : 44
         * officalwebUrl : http://m.keyouxi.com/
         * isRechtoplatform : 1
         * platformType : 1
         * onlineTime : 1486915200000
         * hottraveltImg : http://public.13322.com/6e9924d.png
         * platformTerminal : 2
         * alphabet : aa
         * tittleimgUrl : http://public.13322.com/43c24d52.png
         * timeout : 3
         * registTime : 1488275128000
         * updateUser : mrdeepen
         * private String packeChannelUrl;
         */

        private String port;
        private String desc;
        private String packageName;
        private String iconUrl;
        private String versionCode;
        private String gameHallPic;
        private String countryCurrency;
        private String allImg;
        private int id;
        private String name;
        private String sourUrl;
        private int POSITION;
        private String giftbagUrl;
        private String boutiqueImg;
        private int isOneself;
        private String hotImg;
        private int status;
        private long updateTime;
        private String conntype;
        private String developers;
        private int havingGameas;
        private String packageSize;
        private int country;
        private String ip;
        private int isRebate;
        private int popularitVal;
        private String officalwebUrl;
        private int isRechtoplatform;
        private int platformType;
        private long onlineTime;
        private String hottraveltImg;
        private int platformTerminal;
        private String alphabet;
        private String tittleimgUrl;
        private int timeout;
        private long registTime;
        private String updateUser;
        private List<GameTypeBean> gameType;
        private String packeChannelUrl;

        public String getPort() {
            return port;
        }

        public void setPort(String port) {
            this.port = port;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public String getIconUrl() {
            return iconUrl;
        }

        public void setIconUrl(String iconUrl) {
            this.iconUrl = iconUrl;
        }

        public String getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(String versionCode) {
            this.versionCode = versionCode;
        }

        public String getGameHallPic() {
            return gameHallPic;
        }

        public void setGameHallPic(String gameHallPic) {
            this.gameHallPic = gameHallPic;
        }

        public String getCountryCurrency() {
            return countryCurrency;
        }

        public void setCountryCurrency(String countryCurrency) {
            this.countryCurrency = countryCurrency;
        }

        public String getAllImg() {
            return allImg;
        }

        public void setAllImg(String allImg) {
            this.allImg = allImg;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSourUrl() {
            return sourUrl;
        }

        public void setSourUrl(String sourUrl) {
            this.sourUrl = sourUrl;
        }

        public int getPOSITION() {
            return POSITION;
        }

        public void setPOSITION(int POSITION) {
            this.POSITION = POSITION;
        }

        public String getGiftbagUrl() {
            return giftbagUrl;
        }

        public void setGiftbagUrl(String giftbagUrl) {
            this.giftbagUrl = giftbagUrl;
        }

        public String getBoutiqueImg() {
            return boutiqueImg;
        }

        public void setBoutiqueImg(String boutiqueImg) {
            this.boutiqueImg = boutiqueImg;
        }

        public int getIsOneself() {
            return isOneself;
        }

        public void setIsOneself(int isOneself) {
            this.isOneself = isOneself;
        }

        public String getHotImg() {
            return hotImg;
        }

        public void setHotImg(String hotImg) {
            this.hotImg = hotImg;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public String getConntype() {
            return conntype;
        }

        public void setConntype(String conntype) {
            this.conntype = conntype;
        }

        public String getDevelopers() {
            return developers;
        }

        public void setDevelopers(String developers) {
            this.developers = developers;
        }

        public int getHavingGameas() {
            return havingGameas;
        }

        public void setHavingGameas(int havingGameas) {
            this.havingGameas = havingGameas;
        }

        public String getPackageSize() {
            return packageSize;
        }

        public void setPackageSize(String packageSize) {
            this.packageSize = packageSize;
        }

        public int getCountry() {
            return country;
        }

        public void setCountry(int country) {
            this.country = country;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public int getIsRebate() {
            return isRebate;
        }

        public void setIsRebate(int isRebate) {
            this.isRebate = isRebate;
        }

        public int getPopularitVal() {
            return popularitVal;
        }

        public void setPopularitVal(int popularitVal) {
            this.popularitVal = popularitVal;
        }

        public String getOfficalwebUrl() {
            return officalwebUrl;
        }

        public void setOfficalwebUrl(String officalwebUrl) {
            this.officalwebUrl = officalwebUrl;
        }

        public int getIsRechtoplatform() {
            return isRechtoplatform;
        }

        public void setIsRechtoplatform(int isRechtoplatform) {
            this.isRechtoplatform = isRechtoplatform;
        }

        public int getPlatformType() {
            return platformType;
        }

        public void setPlatformType(int platformType) {
            this.platformType = platformType;
        }

        public long getOnlineTime() {
            return onlineTime;
        }

        public void setOnlineTime(long onlineTime) {
            this.onlineTime = onlineTime;
        }

        public String getHottraveltImg() {
            return hottraveltImg;
        }

        public void setHottraveltImg(String hottraveltImg) {
            this.hottraveltImg = hottraveltImg;
        }

        public int getPlatformTerminal() {
            return platformTerminal;
        }

        public void setPlatformTerminal(int platformTerminal) {
            this.platformTerminal = platformTerminal;
        }

        public String getAlphabet() {
            return alphabet;
        }

        public void setAlphabet(String alphabet) {
            this.alphabet = alphabet;
        }

        public String getTittleimgUrl() {
            return tittleimgUrl;
        }

        public void setTittleimgUrl(String tittleimgUrl) {
            this.tittleimgUrl = tittleimgUrl;
        }

        public int getTimeout() {
            return timeout;
        }

        public void setTimeout(int timeout) {
            this.timeout = timeout;
        }

        public long getRegistTime() {
            return registTime;
        }

        public void setRegistTime(long registTime) {
            this.registTime = registTime;
        }

        public String getUpdateUser() {
            return updateUser;
        }

        public void setUpdateUser(String updateUser) {
            this.updateUser = updateUser;
        }

        public String getPackeChannelUrl() {
            return packeChannelUrl;
        }

        public void setPackeChannelUrl(String packeChannelUrl) {
            this.packeChannelUrl = packeChannelUrl;
        }

        public List<GameTypeBean> getGameType() {
            return gameType;
        }

        public void setGameType(List<GameTypeBean> gameType) {
            this.gameType = gameType;
        }

        public static class GameTypeBean {
            /**
             * gameType : 8003
             * gameTypeName : 战棋游戏
             */

            private String gameType;
            private String gameTypeName;

            public String getGameType() {
                return gameType;
            }

            public void setGameType(String gameType) {
                this.gameType = gameType;
            }

            public String getGameTypeName() {
                return gameTypeName;
            }

            public void setGameTypeName(String gameTypeName) {
                this.gameTypeName = gameTypeName;
            }
        }
    }

}
