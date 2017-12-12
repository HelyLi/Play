package com.hhly.lyygame.data.net.protocol.transfer;

import com.hhly.lyygame.data.net.protocol.BaseResp;

import java.util.List;

/**
 * Created by ${HELY} on 17/4/24.
 * 邮箱：heli.lixiong@gmail.com
 */

public class TransferGameListResp extends BaseResp {


    private List<TransferGameInfo> platformGameList;

    public List<TransferGameInfo> getPlatformGameList() {
        return platformGameList;
    }

    public void setPlatformGameList(List<TransferGameInfo> platformGameList) {
        this.platformGameList = platformGameList;
    }

    public static class TransferGameInfo {
        /**
         * name : 乐盈电竞
         * id : 10110
         * ip :  192.168.74.155
         * registUser : 5
         * registTime : 1487411304000
         * updateTime : 1493026820000
         * onlineTime : 1487347200000
         * status : 1
         * port : 4061
         * timeout : 50000
         * connType : TCP
         * rate : 1.0E34
         * isRechargePlatform : 1
         * iceConnectType : 1
         * curtypeName : 金币
         * gameType : 8010
         * sourUrl : http://m.cnyxjc.1332255.com
         * giftbagUrl : http://m.cnyxjc.1332255.com
         * officalwebUrl : http://m.cnyxjc.1332255.com
         * updateUser : mrdeepen
         * desc : hahahah
         * developers : 13322
         * iconUrl : http://public.13322.com/6f9c7ab.gif
         * tittleImgUrl : http://public.13322.com/77fa2cdc.jpg
         * platformTerminal : 4
         * platformType : 1
         * isOneself : 0
         * havingAs : 0
         * isRebate : 0
         * popularitVal : 109
         * boutiqueImg : http://public.13322.com/3ff5a4ed.gif
         * hotImg : http://public.13322.com/6ceef10c.gif
         * hottraveltImg : http://public.13322.com/65246a83.gif
         * allImg : http://public.13322.com/75abf852.gif
         * position : 32
         * alphabet : lykc
         * country : 0
         * gameLabelList : []
         * isRemitOut : 1
         * isRemitIn : 1
         * isExchange : 1
         * gameHallPic : http://public.13322.com/2acf369a.jpg
         */

        private String name;
        private int id;
        private String ip;
        private int registUser;
        private long registTime;
        private long updateTime;
        private long onlineTime;
        private int status;
        private String port;
        private int timeout;
        private String connType;
        private double rate;
        private int isRechargePlatform;
        private int iceConnectType;
        private String curtypeName;
        private int gameType;
        private String sourUrl;
        private String giftbagUrl;
        private String officalwebUrl;
        private String updateUser;
        private String desc;
        private String developers;
        private String iconUrl;
        private String tittleImgUrl;
        private int platformTerminal;
        private int platformType;
        private int isOneself;
        private int havingAs;
        private int isRebate;
        private int popularitVal;
        private String boutiqueImg;
        private String hotImg;
        private String hottraveltImg;
        private String allImg;
        private int position;
        private String alphabet;
        private int country;
        private int isRemitOut;
        private int isRemitIn;
        private int isExchange;
        private String gameHallPic;
        private List<?> gameLabelList;

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

        public long getOnlineTime() {
            return onlineTime;
        }

        public void setOnlineTime(long onlineTime) {
            this.onlineTime = onlineTime;
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

        public int getIsRechargePlatform() {
            return isRechargePlatform;
        }

        public void setIsRechargePlatform(int isRechargePlatform) {
            this.isRechargePlatform = isRechargePlatform;
        }

        public int getIceConnectType() {
            return iceConnectType;
        }

        public void setIceConnectType(int iceConnectType) {
            this.iceConnectType = iceConnectType;
        }

        public String getCurtypeName() {
            return curtypeName;
        }

        public void setCurtypeName(String curtypeName) {
            this.curtypeName = curtypeName;
        }

        public int getGameType() {
            return gameType;
        }

        public void setGameType(int gameType) {
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

        public String getIconUrl() {
            return iconUrl;
        }

        public void setIconUrl(String iconUrl) {
            this.iconUrl = iconUrl;
        }

        public String getTittleImgUrl() {
            return tittleImgUrl;
        }

        public void setTittleImgUrl(String tittleImgUrl) {
            this.tittleImgUrl = tittleImgUrl;
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

        public int getPopularitVal() {
            return popularitVal;
        }

        public void setPopularitVal(int popularitVal) {
            this.popularitVal = popularitVal;
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

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public String getAlphabet() {
            return alphabet;
        }

        public void setAlphabet(String alphabet) {
            this.alphabet = alphabet;
        }

        public int getCountry() {
            return country;
        }

        public void setCountry(int country) {
            this.country = country;
        }

        public int getIsRemitOut() {
            return isRemitOut;
        }

        public void setIsRemitOut(int isRemitOut) {
            this.isRemitOut = isRemitOut;
        }

        public int getIsRemitIn() {
            return isRemitIn;
        }

        public void setIsRemitIn(int isRemitIn) {
            this.isRemitIn = isRemitIn;
        }

        public int getIsExchange() {
            return isExchange;
        }

        public void setIsExchange(int isExchange) {
            this.isExchange = isExchange;
        }

        public String getGameHallPic() {
            return gameHallPic;
        }

        public void setGameHallPic(String gameHallPic) {
            this.gameHallPic = gameHallPic;
        }

        public List<?> getGameLabelList() {
            return gameLabelList;
        }

        public void setGameLabelList(List<?> gameLabelList) {
            this.gameLabelList = gameLabelList;
        }
    }
}
