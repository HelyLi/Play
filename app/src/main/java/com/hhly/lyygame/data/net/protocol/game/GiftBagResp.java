package com.hhly.lyygame.data.net.protocol.game;

import com.hhly.lyygame.data.net.protocol.BaseResp;

import java.util.List;

/**
 * Created by ${HELY} on 17/2/15.
 * 邮箱：heli.lixiong@gmail.com
 */

public class GiftBagResp extends BaseResp {

    private int total;
    private List<GiftBagBean> giftBag;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<GiftBagBean> getGiftBag() {
        return giftBag;
    }

    public void setGiftBag(List<GiftBagBean> giftBag) {
        this.giftBag = giftBag;
    }

    public static class GiftBagBean {
        /**
         * id : 267
         * title : 高级游戏角色
         * groupName : 8
         * rotationImgUrl : http://public.13322.com/4bea75e7.jpg
         * startDate : 1488384000000
         * endDate : 1504108800000
         * type : 1
         * imgUrl : null
         * redirectUrl : http://baidu.com
         * exchange : 充值10元即可。。。
         * content : 游戏装备，
         《王者荣耀》是腾讯第一5V5英雄公平对战手游,拥有超过2亿注册用户,每天有5000万玩家在王者峡谷中开团战斗!多样玩法一键体验

         ,热血竞技尽享快感!10秒实时跨区匹配,与...
         * country : 0
         * remark :
         《王者荣耀》是腾讯第一5V5英雄公平对战手游,拥有超过2亿注册用户,每天有5000万玩家在
         * needScoreId : 0
         * needScore : 0.0
         * enable : 0
         * game : 10441
         * area : null
         * server : null
         * platform : 2
         * position : 1
         * canUse : 0
         * used : 5
         * gameInfo : {"name":"王者荣耀","id":10441,"ip":" ","registUser":5,"registTime":1488274922000,"updateTime":1489974086000,"status":1,"port":"8000","timeout":3,"connType":"ssh","rate":1,"indexPageAddress":null,"gamePageAddress":null,"isRechargePlatform":1,"iceConnectType":null,"indexImage":null,"curtypeName":"元宝","th_name":null,"vi_name":null,"in_name":null,"gameType":null,"sourUrl":null,"giftbagUrl":null,"officalwebUrl":null,"updateUser":null,"desc":null,"developers":null,"onlineTime":1488211200000,"platformTerminal":2,"platformType":1,"isOneself":0,"havingAs":0,"isRebate":0,"boutiqueImg":"http://public.13322.com/6ded4807.jpg","hotImg":"http://public.13322.com/a9775a3.jpg","hottraveltImg":"http://public.13322.com/7cf2063c.jpg","allImg":"http://public.13322.com/58a46847.jpg"}
         * appliedCount : null
         */

        private int id;
        private String title;
        private int groupName;
        private String rotationImgUrl;
        private long startDate;
        private long endDate;
        private int type;
        private Object imgUrl;
        private String redirectUrl;
        private String exchange;
        private String content;
        private int country;
        private String remark;
        private int needScoreId;
        private double needScore;
        private int enable;
        private int game;
        private Object area;
        private Object server;
        private int platform;
        private int position;
        private int canUse;
        private int used;
        private String appliedCount;
        private boolean isCheck;

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

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

        public void setImgUrl(Object imgUrl) {
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

        public String getAppliedCount() {
            return appliedCount;
        }

        public void setAppliedCount(String appliedCount) {
            this.appliedCount = appliedCount;
        }

    }

}
