package com.hhly.lyygame.data.net.protocol.game;

import com.hhly.lyygame.data.net.protocol.BaseResp;

import java.util.List;

/**
 * Created by ${HELY} on 17/2/17.
 * 邮箱：heli.lixiong@gmail.com
 */

public class GameByTypeResp extends BaseResp {

    private GameByTypePage data;

    public GameByTypePage getData() {
        return data;
    }

    public void setData(GameByTypePage data) {
        this.data = data;
    }

    public static class GameByTypePage {

        private int totalRows;
        private int pageNo;
        private int pageSize;
        private int totalPages;
        private List<GameByTypeInfo> list;

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

        public List<GameByTypeInfo> getList() {
            return list;
        }

        public void setList(List<GameByTypeInfo> list) {
            this.list = list;
        }

        public static class GameByTypeInfo {

            private int id;
            private Long gameType;
            private String gameTypeName;
            private Integer isRechargePlatform;
            private String name;
            private String ip;
            private String registUser;
            private long registTime;
            private long updateTime;
            private int status;
            private String port;
            private String conntype;
            private int timeout;
            private int rate;
            private String indexpageAddress;
            private String gamepageAddress;
            private int isRechtoplatform;
            private int iceConnecttype;
            private String indexImage;
            private String curtypeName;
            private String remark1;
            private String remark2;
            private String thName;
            private String viName;
            private String inName;
            private String sourUrl;
            private String giftbagUrl;
            private String officalwebUrl;
            private String updateUser;
            private String desc;
            private String developers;
            private long onlineTime;
            private int platformType;
            private int platformTerminal;
            private String iconUrl;
            private String tittleimgUrl;
            private int isOneself;
            private int havingGameas;
            private int isRebate;
            private Short isGame;
            private int popularitVal;
            private String boutiqueImg;
            private String hotImg;
            private String hottraveltImg;
            private String allImg;
            private int country;
            private String gameHallPic;
            private int position;
            private String alphabet;
            private String packageName;
            private String versionCode;
            private String packageSize;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Long getGameType() {
                return gameType;
            }

            public void setGameType(Long gameType) {
                this.gameType = gameType;
            }

            public String getGameTypeName() {
                return gameTypeName;
            }

            public void setGameTypeName(String gameTypeName) {
                this.gameTypeName = gameTypeName;
            }

            public Integer getIsRechargePlatform() {
                return isRechargePlatform;
            }

            public void setIsRechargePlatform(Integer isRechargePlatform) {
                this.isRechargePlatform = isRechargePlatform;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getIp() {
                return ip;
            }

            public void setIp(String ip) {
                this.ip = ip;
            }

            public String getRegistUser() {
                return registUser;
            }

            public void setRegistUser(String registUser) {
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

            public String getConntype() {
                return conntype;
            }

            public void setConntype(String conntype) {
                this.conntype = conntype;
            }

            public int getTimeout() {
                return timeout;
            }

            public void setTimeout(int timeout) {
                this.timeout = timeout;
            }

            public int getRate() {
                return rate;
            }

            public void setRate(int rate) {
                this.rate = rate;
            }

            public String getIndexpageAddress() {
                return indexpageAddress;
            }

            public void setIndexpageAddress(String indexpageAddress) {
                this.indexpageAddress = indexpageAddress;
            }

            public String getGamepageAddress() {
                return gamepageAddress;
            }

            public void setGamepageAddress(String gamepageAddress) {
                this.gamepageAddress = gamepageAddress;
            }

            public int getIsRechtoplatform() {
                return isRechtoplatform;
            }

            public void setIsRechtoplatform(int isRechtoplatform) {
                this.isRechtoplatform = isRechtoplatform;
            }

            public int getIceConnecttype() {
                return iceConnecttype;
            }

            public void setIceConnecttype(int iceConnecttype) {
                this.iceConnecttype = iceConnecttype;
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

            public String getRemark1() {
                return remark1;
            }

            public void setRemark1(String remark1) {
                this.remark1 = remark1;
            }

            public String getRemark2() {
                return remark2;
            }

            public void setRemark2(String remark2) {
                this.remark2 = remark2;
            }

            public String getThName() {
                return thName;
            }

            public void setThName(String thName) {
                this.thName = thName;
            }

            public String getViName() {
                return viName;
            }

            public void setViName(String viName) {
                this.viName = viName;
            }

            public String getInName() {
                return inName;
            }

            public void setInName(String inName) {
                this.inName = inName;
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

            public int getPlatformType() {
                return platformType;
            }

            public void setPlatformType(int platformType) {
                this.platformType = platformType;
            }

            public int getPlatformTerminal() {
                return platformTerminal;
            }

            public void setPlatformTerminal(int platformTerminal) {
                this.platformTerminal = platformTerminal;
            }

            public String getIconUrl() {
                return iconUrl;
            }

            public void setIconUrl(String iconUrl) {
                this.iconUrl = iconUrl;
            }

            public String getTittleimgUrl() {
                return tittleimgUrl;
            }

            public void setTittleimgUrl(String tittleimgUrl) {
                this.tittleimgUrl = tittleimgUrl;
            }

            public int getIsOneself() {
                return isOneself;
            }

            public void setIsOneself(int isOneself) {
                this.isOneself = isOneself;
            }

            public int getHavingGameas() {
                return havingGameas;
            }

            public void setHavingGameas(int havingGameas) {
                this.havingGameas = havingGameas;
            }

            public int getIsRebate() {
                return isRebate;
            }

            public void setIsRebate(int isRebate) {
                this.isRebate = isRebate;
            }

            public Short getIsGame() {
                return isGame;
            }

            public void setIsGame(Short isGame) {
                this.isGame = isGame;
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

            public int getCountry() {
                return country;
            }

            public void setCountry(int country) {
                this.country = country;
            }

            public String getGameHallPic() {
                return gameHallPic;
            }

            public void setGameHallPic(String gameHallPic) {
                this.gameHallPic = gameHallPic;
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

            public String getPackageName() {
                return packageName;
            }

            public void setPackageName(String packageName) {
                this.packageName = packageName;
            }

            public String getVersionCode() {
                return versionCode;
            }

            public void setVersionCode(String versionCode) {
                this.versionCode = versionCode;
            }

            public String getPackageSize() {
                return packageSize;
            }

            public void setPackageSize(String packageSize) {
                this.packageSize = packageSize;
            }
        }
    }

}
