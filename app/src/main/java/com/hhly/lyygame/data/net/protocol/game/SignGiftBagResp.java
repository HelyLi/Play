package com.hhly.lyygame.data.net.protocol.game;

import com.hhly.lyygame.data.net.protocol.BaseResp;

/**
 * Created by ${HELY} on 17/2/18.
 * 邮箱：heli.lixiong@gmail.com
 */

public class SignGiftBagResp extends BaseResp {

    private GiftBagBean giftBag;

    public GiftBagBean getGiftBag() {
        return giftBag;
    }

    public void setGiftBag(GiftBagBean giftBag) {
        this.giftBag = giftBag;
    }

    public static class GiftBagBean {
        /**
         * id : 242
         * title : 游戏装备
         * groupName : 7
         * rotationImgUrl : http://public.13322.com/708eac2b.jpg
         * startDate : 1487174400000
         * endDate : 1517328000000
         * type : 1
         * imgUrl : null
         * redirectUrl : http://baidu.com
         * exchange : 登录上线充值6元限制免费获取一套游戏装备哦
         * content : 来吧，领取你的个人游戏专属
         * country : 0
         * remark : 来吧，领取你的个人游戏专属
         * needScoreId : 0
         * needScore : 0.0
         * enable : 0
         * game : 10163
         * area : null
         * server : null
         * platform : 4
         * position : 5
         * canUse : 98
         * used : 2
         * gameInfo : null
         */

        private int id;
        private String title;
        private int groupName;
        private String rotationImgUrl;
        private long startDate;
        private long endDate;
        private int type;
        private String imgUrl;
        private String redirectUrl;
        private String exchange;
        private String content;
        private int country;
        private String remark;
        private int needScoreId;
        private double needScore;
        private int enable;
        private int game;
        private String area;
        private String server;
        private int platform;
        private int position;
        private int canUse;
        private int used;
        private String gameInfo;

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

        public int getGroupName() {
            return groupName;
        }

        public void setGroupName(int groupName) {
            this.groupName = groupName;
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

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getRedirectUrl() {
            return redirectUrl;
        }

        public void setRedirectUrl(String redirectUrl) {
            this.redirectUrl = redirectUrl;
        }

        public String getExchange() {
            return exchange;
        }

        public void setExchange(String exchange) {
            this.exchange = exchange;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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

        public int getNeedScoreId() {
            return needScoreId;
        }

        public void setNeedScoreId(int needScoreId) {
            this.needScoreId = needScoreId;
        }

        public double getNeedScore() {
            return needScore;
        }

        public void setNeedScore(double needScore) {
            this.needScore = needScore;
        }

        public int getEnable() {
            return enable;
        }

        public void setEnable(int enable) {
            this.enable = enable;
        }

        public int getGame() {
            return game;
        }

        public void setGame(int game) {
            this.game = game;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getServer() {
            return server;
        }

        public void setServer(String server) {
            this.server = server;
        }

        public int getPlatform() {
            return platform;
        }

        public void setPlatform(int platform) {
            this.platform = platform;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public int getCanUse() {
            return canUse;
        }

        public void setCanUse(int canUse) {
            this.canUse = canUse;
        }

        public int getUsed() {
            return used;
        }

        public void setUsed(int used) {
            this.used = used;
        }

        public String getGameInfo() {
            return gameInfo;
        }

        public void setGameInfo(String gameInfo) {
            this.gameInfo = gameInfo;
        }
    }
}
