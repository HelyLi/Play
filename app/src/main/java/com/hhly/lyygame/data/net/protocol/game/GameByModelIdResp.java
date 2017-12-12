package com.hhly.lyygame.data.net.protocol.game;

import com.hhly.lyygame.data.net.protocol.BaseResp;

import java.util.List;

/**
 * Created by ${HELY} on 17/4/7.
 * 邮箱：heli.lixiong@gmail.com
 */

public class GameByModelIdResp extends BaseResp {

    private GameByModeIdPage data;

    public GameByModeIdPage getData() {
        return data;
    }

    public void setData(GameByModeIdPage data) {
        this.data = data;
    }

    public static class GameByModeIdPage {

        private int totalRows;
        private int pageNo;
        private int pageSize;
        private int totalPages;
        private List<GameByModeIdInfo> list;

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

        public List<GameByModeIdInfo> getList() {
            return list;
        }

        public void setList(List<GameByModeIdInfo> list) {
            this.list = list;
        }

        public static class GameByModeIdInfo {
            /**
             * updatebTime : 2017-03-30 09:51:09
             * port : 8001
             * sort : 1
             * gameType : []
             * imageUrl : http://public.13322.com/73399182.jpg
             * curtypeName : 游戏币
             * allImg : http://public.13322.com/1100e774.jpg
             * rate : 10
             * name : 海妖直播
             * sourUrl : http://texas.1332255.com:4007/VideoGameWeb/1/index/all.html?time=1490838433941
             * modeId : 1
             * giftbagUrl : http://texas.1332255.com:4007/VideoGameWeb/1/index/all.html?time=1490838433941
             * boutiqueImg : http://public.13322.com/375a93cf.jpg
             * updater : mrdeepen
             * hotImg : http://public.13322.com/54884a47.jpg
             * updateTime : 2017-03-15 17:03:05
             * status : 1
             * gameId : 10088
             * developers : 乐盈盈公司
             * ip : 183.61.172.89
             * popularitVal : 65
             * iceConnecttype : 2
             * officalwebUrl : http://texas.1332255.com:4007/VideoGameWeb/1/index/all.html?time=1490838433941
             * isRechtoplatform : 1
             * hottraveltImg : http://public.13322.com/1c41837.jpg
             * ROWNUM_ : 1
             * connType : TCP
             * platformTerminal : 1
             * tittleimgUrl : http://public.13322.com/7e2d8ad4.jpg
             * registUser : 1
             * iconURL : http://public.13322.com/472b3fbd.jpg
             * registTime : 2016-06-08 10:22:46
             * updateUser : mrdeepen
             * desc : 游戏介绍游戏介绍:项目团队成员除了每天的编码工作、参加站立会议之外，还有一个工作就是在禅道里面更新自己所负责任务的状态以及它的预计剩余时间
             * creater : mrdeepen
             * createTime : 2017-03-01 10:49:34
             * packageName : com.pokercity.ddz
             * versionCode : 1.0
             * packageSize : 6M
             * packeChannelUrl: 渠道地址
             */

            private String updatebTime;
            private String port;
            private int sort;
            private String imageUrl;
            private String curtypeName;
            private String allImg;
            private int rate;
            private String name;
            private String sourUrl;
            private int modeId;
            private String giftbagUrl;
            private String boutiqueImg;
            private String updater;
            private String hotImg;
            private String updateTime;
            private int status;
            private int gameId;
            private String developers;
            private String ip;
            private int popularitVal;
            private int iceConnecttype;
            private String officalwebUrl;
            private int isRechtoplatform;
            private String hottraveltImg;
            private int ROWNUM_;
            private String connType;
            private int platformTerminal;
            private String tittleimgUrl;
            private String registUser;
            private String iconURL;
            private String registTime;
            private String updateUser;
            private String desc;
            private String creater;
            private String createTime;
            private String packageName;
            private String versionCode;
            private String packageSize;
            private List<GameTypeBean> gameType;

            public String getPackeChannelUrl() {
                return packeChannelUrl;
            }

            public void setPackeChannelUrl(String packeChannelUrl) {
                this.packeChannelUrl = packeChannelUrl;
            }

            private String packeChannelUrl;

            public String getUpdatebTime() {
                return updatebTime;
            }

            public void setUpdatebTime(String updatebTime) {
                this.updatebTime = updatebTime;
            }

            public String getPort() {
                return port;
            }

            public void setPort(String port) {
                this.port = port;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public String getCurtypeName() {
                return curtypeName;
            }

            public void setCurtypeName(String curtypeName) {
                this.curtypeName = curtypeName;
            }

            public String getAllImg() {
                return allImg;
            }

            public void setAllImg(String allImg) {
                this.allImg = allImg;
            }

            public int getRate() {
                return rate;
            }

            public void setRate(int rate) {
                this.rate = rate;
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

            public int getModeId() {
                return modeId;
            }

            public void setModeId(int modeId) {
                this.modeId = modeId;
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

            public String getUpdater() {
                return updater;
            }

            public void setUpdater(String updater) {
                this.updater = updater;
            }

            public String getHotImg() {
                return hotImg;
            }

            public void setHotImg(String hotImg) {
                this.hotImg = hotImg;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getGameId() {
                return gameId;
            }

            public void setGameId(int gameId) {
                this.gameId = gameId;
            }

            public String getDevelopers() {
                return developers;
            }

            public void setDevelopers(String developers) {
                this.developers = developers;
            }

            public String getIp() {
                return ip;
            }

            public void setIp(String ip) {
                this.ip = ip;
            }

            public int getPopularitVal() {
                return popularitVal;
            }

            public void setPopularitVal(int popularitVal) {
                this.popularitVal = popularitVal;
            }

            public int getIceConnecttype() {
                return iceConnecttype;
            }

            public void setIceConnecttype(int iceConnecttype) {
                this.iceConnecttype = iceConnecttype;
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

            public String getHottraveltImg() {
                return hottraveltImg;
            }

            public void setHottraveltImg(String hottraveltImg) {
                this.hottraveltImg = hottraveltImg;
            }

            public int getROWNUM_() {
                return ROWNUM_;
            }

            public void setROWNUM_(int ROWNUM_) {
                this.ROWNUM_ = ROWNUM_;
            }

            public String getConnType() {
                return connType;
            }

            public void setConnType(String connType) {
                this.connType = connType;
            }

            public int getPlatformTerminal() {
                return platformTerminal;
            }

            public void setPlatformTerminal(int platformTerminal) {
                this.platformTerminal = platformTerminal;
            }

            public String getTittleimgUrl() {
                return tittleimgUrl;
            }

            public void setTittleimgUrl(String tittleimgUrl) {
                this.tittleimgUrl = tittleimgUrl;
            }

            public String getRegistUser() {
                return registUser;
            }

            public void setRegistUser(String registUser) {
                this.registUser = registUser;
            }

            public String getIconURL() {
                return iconURL;
            }

            public void setIconURL(String iconURL) {
                this.iconURL = iconURL;
            }

            public String getRegistTime() {
                return registTime;
            }

            public void setRegistTime(String registTime) {
                this.registTime = registTime;
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

            public String getCreater() {
                return creater;
            }

            public void setCreater(String creater) {
                this.creater = creater;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
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

            public List<GameTypeBean> getGameType() {
                return gameType;
            }

            public void setGameType(List<GameTypeBean> gameType) {
                this.gameType = gameType;
            }

            public static class GameTypeBean {
                /**
                 * gameType : 8012
                 * gameTypeName : 冒险游戏
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
//    /**
//     * data : {"totalRows":12,"pageNo":1,"pageSize":10,"list":[{"updatebTime":"2017-04-06 14:11:01","port":"80","sort":1,"desc":"王者荣耀13413413413413241234","gameType":[{"gameType":"8012","gameTypeName":"冒险游戏"}],"imageUrl":"http://public.13322.com/5ae8fde0.jpg","creater":"mrdeepen","curtypeName":"乐盈币","allImg":"http://public.13322.com/26027144.jpg","rate":1,"name":"王者荣耀","sourUrl":"http://163.177.158.81/imtt.dd.qq.com/16891/630A9B0CC688192EA3A95E771A450D84.apk?mkey=58b7a0fc08fe6a81&f=d073&c=0&fsname=com.cnvcs.xiangqi_1.70_70170.apk&csr=4d5s&p=.apk","modeId":2,"giftbagUrl":"http://nc-release.wdjcdn.com/files/jupiter/5.51.20.13150/wandoujia-jiuyuan3_ad.apk","boutiqueImg":"http://public.13322.com/5785149e.jpg","hotImg":"http://public.13322.com/20a6e1c4.jpg","updateTime":"2017-03-02 10:06:42","status":1,"gameId":10440,"developers":"乐盈盈","ip":" ","popularitVal":100,"officalwebUrl":"http://nc-release.wdjcdn.com/files/jupiter/5.51.20.13150/wandoujia-jiuyuan3_ad.apk","isRechtoplatform":1,"hottraveltImg":"http://public.13322.com/4484078a.jpg","ROWNUM_":1,"connType":"tcp","platformTerminal":4,"tittleimgUrl":"http://public.13322.com/428426c8.jpg","registUser":"5","iconURL":"http://public.13322.com/3445f0d1.jpg","registTime":"2017-02-27 14:58:31","updateUser":"mrdeepen","updater":"mrdeepen"},{"port":"8000","updatebTime":"2017-04-06 14:08:34","sort":2,"desc":"来战斗吧1.我告诉你！真的很好玩，你看我玩得现在还没吃饭呢！2.告诉你！自从玩了这款游戏，你家老奶奶都不去跳广场舞了，跟你的邻居比起技能竞级了3.你还不赶紧","gameType":[{"gameType":"8003","gameTypeName":"战棋游戏"}],"imageUrl":"http://public.13322.com/1fbc7edf.jpg","creater":"mrdeepen","curtypeName":"积分","allImg":"http://public.13322.com/48e2f217.jpg","rate":1,"name":"神秘X","sourUrl":"http://sd.1332255.com","modeId":2,"giftbagUrl":"http://sd.1332255.com","boutiqueImg":"http://public.13322.com/754d3516.jpg","hotImg":"http://public.13322.com/7784aa4f.jpg","status":1,"gameId":10105,"developers":"BOSS","ip":" ","popularitVal":90,"officalwebUrl":"https://baidu.com","isRechtoplatform":1,"hottraveltImg":"http://public.13322.com/5be8dbba.jpg","ROWNUM_":2,"connType":"tcp","platformTerminal":4,"tittleimgUrl":"http://public.13322.com/5661e655.jpg","registUser":"4","registTime":"2016-12-03 17:35:23","iconURL":"http://public.13322.com/1d1137dc.jpg","updateUser":"mrdeepen"},{"port":"8000","updatebTime":"2017-03-30 12:45:53","sort":3,"desc":" 蛛蛛侠大战奥特曼，奥特曼太无聊经常毁坏城市，看不惯的蛛蛛侠再也忍耐不住，决定不能再袖手旁观要，于是他们的故事开始了...\r\n自身能力\r\n蜘蛛感应：像蜘蛛一样通过空气震动来获取信息，感知周围的一切，甚以可以躲避子弹攻击。\r\n力量：蜘蛛等比例放大的力量可轻松举起出租车等重物，最大可达15吨。\r\n攀爬：手掌上制造出强大的静电，以产生吸附力。\r\n武器装备\r\n蛛网发射器：帕克自己制作的蛛丝发射器配合特殊研发的蛛丝一起使用，蛛丝柔韧度极强且会在喷射出后在一个小时之后自动融化。\r\n蜘蛛战衣：蜘蛛侠的制服，封闭了视觉、嗅觉等，穿上战衣的蜘蛛侠完全通过蜘蛛感应来进行信息摄入。\r\n另外蜘蛛侠在不同的平行宇宙中，也配备有其它.","gameType":[{"gameType":"8005","gameTypeName":"角色扮演"},{"gameType":"8017","gameTypeName":"飞行游戏"}],"imageUrl":"http://public.13322.com/42d58302.jpg","creater":"mrdeepen","curtypeName":"金币","allImg":"http://public.13322.com/73d2502c.jpg","rate":1,"name":"死侍H5","sourUrl":"http://m.keyouxi.com/","modeId":2,"giftbagUrl":"http://m.keyouxi.com/","boutiqueImg":"http://public.13322.com/10890623.jpg","hotImg":"http://public.13322.com/5a7a5a31.jpg","status":1,"gameId":10163,"developers":"乐盈盈","ip":" ","popularitVal":103,"officalwebUrl":"http://m.keyouxi.com/","isRechtoplatform":1,"hottraveltImg":"http://public.13322.com/1c718285.jpg","ROWNUM_":3,"connType":"tcp","platformTerminal":4,"tittleimgUrl":"http://public.13322.com/a4950eb.jpg","registUser":"4","registTime":"2016-12-20 14:56:39","iconURL":"http://public.13322.com/71f526b9.jpg","updateUser":"mrdeepen"},{"updatebTime":"2017-04-06 14:12:36","port":"800","sort":4,"desc":"雷电，一款抵御外星入侵太空大战，出自地球星际联盟进行自卫防御，气势磅礴战斗场面超乎你的想象，自卫部队不断的升级越来越先进，典型恒星量子武器可一发摧毁一个星球...","gameType":[{"gameType":"8017","gameTypeName":"飞行游戏"}],"imageUrl":"http://public.13322.com/772c4510.jpg","creater":"mrdeepen","curtypeName":"积分","allImg":"http://public.13322.com/34b6fb81.jpg","rate":1,"name":"雷电","sourUrl":"http://file.liqucn.com/upload/2015/qipai/com.pokercity.ddz_3.14_liqcn.com.apk","modeId":2,"giftbagUrl":"http://baidu.com","boutiqueImg":"http://public.13322.com/42ad04c.jpg","updater":"mrdeepen","hotImg":"http://public.13322.com/306f07ca.jpg","updateTime":"2017-01-07 17:21:54","status":1,"gameId":10113,"developers":"乐盈盈","ip":" ","popularitVal":6,"officalwebUrl":"http://baidu.com","isRechtoplatform":1,"hottraveltImg":"http://public.13322.com/1d581836.jpg","ROWNUM_":4,"connType":"tcp","platformTerminal":4,"tittleimgUrl":"http://public.13322.com/5dd4ee4b.jpg","registUser":"4","iconURL":"http://public.13322.com/311e9fdf.jpg","registTime":"2016-12-05 14:59:54","updateUser":"mrdeepen"},{"port":"8000","updatebTime":"2017-04-06 14:13:13","sort":5,"desc":"血撕盖亚洛，一个血腥部落，为部落统一而欣起了一场自由之地，为此此发了...","gameType":[{"gameType":"8015","gameTypeName":"模拟经营"},{"gameType":"8018","gameTypeName":"格斗游戏"}],"imageUrl":"http://public.13322.com/3bda26f6.jpg","creater":"mrdeepen","allImg":"http://public.13322.com/3aca7f3a.jpg","name":"血撕盖亚洛","sourUrl":"http://mgame.1332255.com/#/gameHall","modeId":2,"giftbagUrl":"http://mgame.1332255.com/#/car","boutiqueImg":"http://public.13322.com/4b102fa3.jpg","hotImg":"http://public.13322.com/427676e4.jpg","status":1,"gameId":10162,"developers":"乐盈盈","ip":" ","popularitVal":88,"officalwebUrl":"http://mgame.1332255.com","isRechtoplatform":1,"hottraveltImg":"http://public.13322.com/4ab1e11c.jpg","ROWNUM_":5,"connType":"tcp","platformTerminal":4,"registUser":"4","tittleimgUrl":"http://public.13322.com/417100f1.jpg","registTime":"2016-12-20 14:50:37","iconURL":"http://public.13322.com/52fea682.jpg","updateUser":"mrdeepen"},{"port":"8000","updatebTime":"2017-04-06 14:14:26","sort":6,"desc":"幻影一代","gameType":[{"gameType":"8020","gameTypeName":"对战游戏"}],"imageUrl":"http://public.13322.com/b4c26b4.jpg","creater":"mrdeepen","curtypeName":"乐盈券","allImg":"http://public.13322.com/2ae73efd.jpg","rate":1,"name":"幻影一代","sourUrl":"http://mgame.1332255.com","modeId":2,"giftbagUrl":"http://mgame.1332255.com/pack","boutiqueImg":"http://public.13322.com/182e40a.jpg","hotImg":"http://public.13322.com/61ed5433.jpg","status":1,"gameId":10143,"developers":"乐盈盈","ip":" ","popularitVal":68,"officalwebUrl":"http://mgame.1332255.com","isRechtoplatform":1,"hottraveltImg":"http://public.13322.com/3fa454e0.jpg","ROWNUM_":6,"connType":"tcp","platformTerminal":4,"tittleimgUrl":"http://public.13322.com/655114b9.jpg","registUser":"4","registTime":"2016-12-09 10:17:31","iconURL":"http://public.13322.com/4352aed9.jpg","updateUser":"mrdeepen"},{"port":"8000","updatebTime":"2017-04-06 14:13:35","sort":7,"desc":"袖珍版中国象棋，超酷的三维设计，磅礴壮阔的场面超乎你的视觉体验...","gameType":[{"gameType":"8018","gameTypeName":"格斗游戏"}],"imageUrl":"http://public.13322.com/33eefb85.jpg","creater":"mrdeepen","curtypeName":"积分","allImg":"http://public.13322.com/4cc21eba.jpg","rate":1,"name":"袖珍版中国象棋","sourUrl":"http://mgame.1332255.com","modeId":2,"giftbagUrl":"http://mgame.1332255.com/pack","boutiqueImg":"http://public.13322.com/368df6e2.jpg","hotImg":"http://public.13322.com/15e5887b.jpg","status":1,"gameId":10132,"developers":"乐盈盈","ip":" ","popularitVal":33,"officalwebUrl":"http://mgame.1332255.com","isRechtoplatform":1,"hottraveltImg":"http://public.13322.com/a54ad3a.jpg","ROWNUM_":7,"connType":"tcp","platformTerminal":4,"tittleimgUrl":"http://public.13322.com/1fbbc48c.jpg","registUser":"4","registTime":"2016-12-07 17:07:16","iconURL":"http://public.13322.com/2ef0c2dc.jpg","updateUser":"mrdeepen"},{"port":"8000","updatebTime":"2017-04-06 14:11:18","sort":8,"desc":"机甲战神，最新火爆上线，一起尝尝鲜吧...","gameType":[{"gameType":"8013","gameTypeName":"竞速游戏"}],"imageUrl":"http://public.13322.com/7cf7ad24.jpg","creater":"mrdeepen","curtypeName":"乐盈券","allImg":"http://public.13322.com/3a056e87.jpg","rate":1,"name":"机甲战神","sourUrl":"http://mgame.1332255.com","modeId":2,"giftbagUrl":"http://mgame.1332255.com/pack","boutiqueImg":"http://public.13322.com/3730d3ba.jpg","hotImg":"http://public.13322.com/7a26dabf.jpg","status":1,"gameId":10137,"developers":"乐盈盈","ip":" ","popularitVal":1,"officalwebUrl":"http://mgame.1332255.com","isRechtoplatform":1,"hottraveltImg":"http://public.13322.com/3b23902.jpg","ROWNUM_":8,"connType":"tcp","platformTerminal":4,"tittleimgUrl":"http://public.13322.com/2e27a93c.jpg","registUser":"4","registTime":"2016-12-07 17:41:45","iconURL":"http://public.13322.com/1af2cbcc.jpg","updateUser":"mrdeepen"},{"port":"5646","updatebTime":"2017-04-06 19:39:50","sort":9,"desc":"1.嘿！看这里!有钱掉了！ 2.我告诉你！真的很好玩，你看我玩得现在还没吃饭呢！ 3.告诉你！自从玩了这款游戏，你家老奶奶都不去跳广场舞了，跟你的邻居比起技能竞级了4.真的很好玩真","gameType":[{"gameType":"8021","gameTypeName":"其他游戏"},{"gameType":"8022","gameTypeName":"独家游戏"},{"gameType":"8002","gameTypeName":"即时战斗"}],"imageUrl":"http://public.13322.com/1e8f6105.jpg","creater":"mrdeepen","curtypeName":"积分","allImg":"http://public.13322.com/46ca7951.jpg","rate":1,"name":"崩溃的神经猫","sourUrl":"http://mgame.1332255.com","modeId":2,"giftbagUrl":"http://mgame.1332255.com","boutiqueImg":"http://public.13322.com/2f505a74.jpg","hotImg":"http://public.13322.com/2254aaa3.jpg","status":1,"gameId":10107,"developers":"乐盈盈公司","ip":" ","popularitVal":7,"officalwebUrl":"http://mgame.1332255.com","isRechtoplatform":1,"hottraveltImg":"http://public.13322.com/7090c088.jpg","ROWNUM_":9,"connType":"tcp","platformTerminal":4,"tittleimgUrl":"http://public.13322.com/6de8715b.jpg","registUser":"4","registTime":"2016-12-03 18:46:44","iconURL":"http://public.13322.com/5d5e0ed0.jpg","updateUser":"mrdeepen"},{"updatebTime":"2017-04-06 14:12:11","port":"8000","sort":10,"desc":"冰川世纪\r\n是一款快速手势操作的游戏，游戏循序渐进，难度越来越大，夸度越来越大，直至您出动双手同时操作，好玩，刺激，当你每过一关的时候估计您都会大叹一口气，为了更","gameType":[{"gameType":"8015","gameTypeName":"模拟经营"}],"imageUrl":"http://public.13322.com/6dd79234.jpg","creater":"mrdeepen","curtypeName":"积分","allImg":"http://public.13322.com/1322b0b8.jpg","rate":1,"name":"冰河世纪","sourUrl":"http://mgame.1332255.com/#/gameHall","modeId":2,"giftbagUrl":"http://mgame.1332255.com/pack","boutiqueImg":"http://public.13322.com/51878916.jpg","updater":"mrdeepen","hotImg":"http://public.13322.com/489c4121.jpg","updateTime":"2017-02-16 11:21:08","status":1,"gameId":10146,"developers":"乐盈盈","ip":" ","popularitVal":90,"officalwebUrl":"http://mgame.1332255.com","isRechtoplatform":1,"hottraveltImg":"http://public.13322.com/6452bcc7.jpg","ROWNUM_":10,"connType":"tcp","platformTerminal":4,"tittleimgUrl":"http://public.13322.com/3ba9398b.jpg","registUser":"4","iconURL":"http://public.13322.com/79859fac.jpg","registTime":"2016-12-09 10:55:39","updateUser":"mrdeepen"}],"totalPages":2}
//     */
//
//    private GameByModeIdPage data;
//
//    public GameByModeIdPage getData() {
//        return data;
//    }
//
//    public void setData(GameByModeIdPage data) {
//        this.data = data;
//    }
//
//    public static class GameByModeIdPage {
//        /**
//         * totalRows : 12
//         * pageNo : 1
//         * pageSize : 10
//         * list : [{"updatebTime":"2017-04-06 14:11:01","port":"80","sort":1,"desc":"王者荣耀13413413413413241234","gameType":[{"gameType":"8012","gameTypeName":"冒险游戏"}],"imageUrl":"http://public.13322.com/5ae8fde0.jpg","creater":"mrdeepen","curtypeName":"乐盈币","allImg":"http://public.13322.com/26027144.jpg","rate":1,"name":"王者荣耀","sourUrl":"http://163.177.158.81/imtt.dd.qq.com/16891/630A9B0CC688192EA3A95E771A450D84.apk?mkey=58b7a0fc08fe6a81&f=d073&c=0&fsname=com.cnvcs.xiangqi_1.70_70170.apk&csr=4d5s&p=.apk","modeId":2,"giftbagUrl":"http://nc-release.wdjcdn.com/files/jupiter/5.51.20.13150/wandoujia-jiuyuan3_ad.apk","boutiqueImg":"http://public.13322.com/5785149e.jpg","hotImg":"http://public.13322.com/20a6e1c4.jpg","updateTime":"2017-03-02 10:06:42","status":1,"gameId":10440,"developers":"乐盈盈","ip":" ","popularitVal":100,"officalwebUrl":"http://nc-release.wdjcdn.com/files/jupiter/5.51.20.13150/wandoujia-jiuyuan3_ad.apk","isRechtoplatform":1,"hottraveltImg":"http://public.13322.com/4484078a.jpg","ROWNUM_":1,"connType":"tcp","platformTerminal":4,"tittleimgUrl":"http://public.13322.com/428426c8.jpg","registUser":"5","iconURL":"http://public.13322.com/3445f0d1.jpg","registTime":"2017-02-27 14:58:31","updateUser":"mrdeepen"},{"port":"8000","updatebTime":"2017-04-06 14:08:34","sort":2,"desc":"来战斗吧1.我告诉你！真的很好玩，你看我玩得现在还没吃饭呢！2.告诉你！自从玩了这款游戏，你家老奶奶都不去跳广场舞了，跟你的邻居比起技能竞级了3.你还不赶紧","gameType":[{"gameType":"8003","gameTypeName":"战棋游戏"}],"imageUrl":"http://public.13322.com/1fbc7edf.jpg","creater":"mrdeepen","curtypeName":"积分","allImg":"http://public.13322.com/48e2f217.jpg","rate":1,"name":"神秘X","sourUrl":"http://sd.1332255.com","modeId":2,"giftbagUrl":"http://sd.1332255.com","boutiqueImg":"http://public.13322.com/754d3516.jpg","hotImg":"http://public.13322.com/7784aa4f.jpg","status":1,"gameId":10105,"developers":"BOSS","ip":" ","popularitVal":90,"officalwebUrl":"https://baidu.com","isRechtoplatform":1,"hottraveltImg":"http://public.13322.com/5be8dbba.jpg","ROWNUM_":2,"connType":"tcp","platformTerminal":4,"tittleimgUrl":"http://public.13322.com/5661e655.jpg","registUser":"4","registTime":"2016-12-03 17:35:23","iconURL":"http://public.13322.com/1d1137dc.jpg","updateUser":"mrdeepen"},{"port":"8000","updatebTime":"2017-03-30 12:45:53","sort":3,"desc":" 蛛蛛侠大战奥特曼，奥特曼太无聊经常毁坏城市，看不惯的蛛蛛侠再也忍耐不住，决定不能再袖手旁观要，于是他们的故事开始了...\r\n自身能力\r\n蜘蛛感应：像蜘蛛一样通过空气震动来获取信息，感知周围的一切，甚以可以躲避子弹攻击。\r\n力量：蜘蛛等比例放大的力量可轻松举起出租车等重物，最大可达15吨。\r\n攀爬：手掌上制造出强大的静电，以产生吸附力。\r\n武器装备\r\n蛛网发射器：帕克自己制作的蛛丝发射器配合特殊研发的蛛丝一起使用，蛛丝柔韧度极强且会在喷射出后在一个小时之后自动融化。\r\n蜘蛛战衣：蜘蛛侠的制服，封闭了视觉、嗅觉等，穿上战衣的蜘蛛侠完全通过蜘蛛感应来进行信息摄入。\r\n另外蜘蛛侠在不同的平行宇宙中，也配备有其它.","gameType":[{"gameType":"8005","gameTypeName":"角色扮演"},{"gameType":"8017","gameTypeName":"飞行游戏"}],"imageUrl":"http://public.13322.com/42d58302.jpg","creater":"mrdeepen","curtypeName":"金币","allImg":"http://public.13322.com/73d2502c.jpg","rate":1,"name":"死侍H5","sourUrl":"http://m.keyouxi.com/","modeId":2,"giftbagUrl":"http://m.keyouxi.com/","boutiqueImg":"http://public.13322.com/10890623.jpg","hotImg":"http://public.13322.com/5a7a5a31.jpg","status":1,"gameId":10163,"developers":"乐盈盈","ip":" ","popularitVal":103,"officalwebUrl":"http://m.keyouxi.com/","isRechtoplatform":1,"hottraveltImg":"http://public.13322.com/1c718285.jpg","ROWNUM_":3,"connType":"tcp","platformTerminal":4,"tittleimgUrl":"http://public.13322.com/a4950eb.jpg","registUser":"4","registTime":"2016-12-20 14:56:39","iconURL":"http://public.13322.com/71f526b9.jpg","updateUser":"mrdeepen"},{"updatebTime":"2017-04-06 14:12:36","port":"800","sort":4,"desc":"雷电，一款抵御外星入侵太空大战，出自地球星际联盟进行自卫防御，气势磅礴战斗场面超乎你的想象，自卫部队不断的升级越来越先进，典型恒星量子武器可一发摧毁一个星球...","gameType":[{"gameType":"8017","gameTypeName":"飞行游戏"}],"imageUrl":"http://public.13322.com/772c4510.jpg","creater":"mrdeepen","curtypeName":"积分","allImg":"http://public.13322.com/34b6fb81.jpg","rate":1,"name":"雷电","sourUrl":"http://file.liqucn.com/upload/2015/qipai/com.pokercity.ddz_3.14_liqcn.com.apk","modeId":2,"giftbagUrl":"http://baidu.com","boutiqueImg":"http://public.13322.com/42ad04c.jpg","updater":"mrdeepen","hotImg":"http://public.13322.com/306f07ca.jpg","updateTime":"2017-01-07 17:21:54","status":1,"gameId":10113,"developers":"乐盈盈","ip":" ","popularitVal":6,"officalwebUrl":"http://baidu.com","isRechtoplatform":1,"hottraveltImg":"http://public.13322.com/1d581836.jpg","ROWNUM_":4,"connType":"tcp","platformTerminal":4,"tittleimgUrl":"http://public.13322.com/5dd4ee4b.jpg","registUser":"4","iconURL":"http://public.13322.com/311e9fdf.jpg","registTime":"2016-12-05 14:59:54","updateUser":"mrdeepen"},{"port":"8000","updatebTime":"2017-04-06 14:13:13","sort":5,"desc":"血撕盖亚洛，一个血腥部落，为部落统一而欣起了一场自由之地，为此此发了...","gameType":[{"gameType":"8015","gameTypeName":"模拟经营"},{"gameType":"8018","gameTypeName":"格斗游戏"}],"imageUrl":"http://public.13322.com/3bda26f6.jpg","creater":"mrdeepen","allImg":"http://public.13322.com/3aca7f3a.jpg","name":"血撕盖亚洛","sourUrl":"http://mgame.1332255.com/#/gameHall","modeId":2,"giftbagUrl":"http://mgame.1332255.com/#/car","boutiqueImg":"http://public.13322.com/4b102fa3.jpg","hotImg":"http://public.13322.com/427676e4.jpg","status":1,"gameId":10162,"developers":"乐盈盈","ip":" ","popularitVal":88,"officalwebUrl":"http://mgame.1332255.com","isRechtoplatform":1,"hottraveltImg":"http://public.13322.com/4ab1e11c.jpg","ROWNUM_":5,"connType":"tcp","platformTerminal":4,"registUser":"4","tittleimgUrl":"http://public.13322.com/417100f1.jpg","registTime":"2016-12-20 14:50:37","iconURL":"http://public.13322.com/52fea682.jpg","updateUser":"mrdeepen"},{"port":"8000","updatebTime":"2017-04-06 14:14:26","sort":6,"desc":"幻影一代","gameType":[{"gameType":"8020","gameTypeName":"对战游戏"}],"imageUrl":"http://public.13322.com/b4c26b4.jpg","creater":"mrdeepen","curtypeName":"乐盈券","allImg":"http://public.13322.com/2ae73efd.jpg","rate":1,"name":"幻影一代","sourUrl":"http://mgame.1332255.com","modeId":2,"giftbagUrl":"http://mgame.1332255.com/pack","boutiqueImg":"http://public.13322.com/182e40a.jpg","hotImg":"http://public.13322.com/61ed5433.jpg","status":1,"gameId":10143,"developers":"乐盈盈","ip":" ","popularitVal":68,"officalwebUrl":"http://mgame.1332255.com","isRechtoplatform":1,"hottraveltImg":"http://public.13322.com/3fa454e0.jpg","ROWNUM_":6,"connType":"tcp","platformTerminal":4,"tittleimgUrl":"http://public.13322.com/655114b9.jpg","registUser":"4","registTime":"2016-12-09 10:17:31","iconURL":"http://public.13322.com/4352aed9.jpg","updateUser":"mrdeepen"},{"port":"8000","updatebTime":"2017-04-06 14:13:35","sort":7,"desc":"袖珍版中国象棋，超酷的三维设计，磅礴壮阔的场面超乎你的视觉体验...","gameType":[{"gameType":"8018","gameTypeName":"格斗游戏"}],"imageUrl":"http://public.13322.com/33eefb85.jpg","creater":"mrdeepen","curtypeName":"积分","allImg":"http://public.13322.com/4cc21eba.jpg","rate":1,"name":"袖珍版中国象棋","sourUrl":"http://mgame.1332255.com","modeId":2,"giftbagUrl":"http://mgame.1332255.com/pack","boutiqueImg":"http://public.13322.com/368df6e2.jpg","hotImg":"http://public.13322.com/15e5887b.jpg","status":1,"gameId":10132,"developers":"乐盈盈","ip":" ","popularitVal":33,"officalwebUrl":"http://mgame.1332255.com","isRechtoplatform":1,"hottraveltImg":"http://public.13322.com/a54ad3a.jpg","ROWNUM_":7,"connType":"tcp","platformTerminal":4,"tittleimgUrl":"http://public.13322.com/1fbbc48c.jpg","registUser":"4","registTime":"2016-12-07 17:07:16","iconURL":"http://public.13322.com/2ef0c2dc.jpg","updateUser":"mrdeepen"},{"port":"8000","updatebTime":"2017-04-06 14:11:18","sort":8,"desc":"机甲战神，最新火爆上线，一起尝尝鲜吧...","gameType":[{"gameType":"8013","gameTypeName":"竞速游戏"}],"imageUrl":"http://public.13322.com/7cf7ad24.jpg","creater":"mrdeepen","curtypeName":"乐盈券","allImg":"http://public.13322.com/3a056e87.jpg","rate":1,"name":"机甲战神","sourUrl":"http://mgame.1332255.com","modeId":2,"giftbagUrl":"http://mgame.1332255.com/pack","boutiqueImg":"http://public.13322.com/3730d3ba.jpg","hotImg":"http://public.13322.com/7a26dabf.jpg","status":1,"gameId":10137,"developers":"乐盈盈","ip":" ","popularitVal":1,"officalwebUrl":"http://mgame.1332255.com","isRechtoplatform":1,"hottraveltImg":"http://public.13322.com/3b23902.jpg","ROWNUM_":8,"connType":"tcp","platformTerminal":4,"tittleimgUrl":"http://public.13322.com/2e27a93c.jpg","registUser":"4","registTime":"2016-12-07 17:41:45","iconURL":"http://public.13322.com/1af2cbcc.jpg","updateUser":"mrdeepen"},{"port":"5646","updatebTime":"2017-04-06 19:39:50","sort":9,"desc":"1.嘿！看这里!有钱掉了！ 2.我告诉你！真的很好玩，你看我玩得现在还没吃饭呢！ 3.告诉你！自从玩了这款游戏，你家老奶奶都不去跳广场舞了，跟你的邻居比起技能竞级了4.真的很好玩真","gameType":[{"gameType":"8021","gameTypeName":"其他游戏"},{"gameType":"8022","gameTypeName":"独家游戏"},{"gameType":"8002","gameTypeName":"即时战斗"}],"imageUrl":"http://public.13322.com/1e8f6105.jpg","creater":"mrdeepen","curtypeName":"积分","allImg":"http://public.13322.com/46ca7951.jpg","rate":1,"name":"崩溃的神经猫","sourUrl":"http://mgame.1332255.com","modeId":2,"giftbagUrl":"http://mgame.1332255.com","boutiqueImg":"http://public.13322.com/2f505a74.jpg","hotImg":"http://public.13322.com/2254aaa3.jpg","status":1,"gameId":10107,"developers":"乐盈盈公司","ip":" ","popularitVal":7,"officalwebUrl":"http://mgame.1332255.com","isRechtoplatform":1,"hottraveltImg":"http://public.13322.com/7090c088.jpg","ROWNUM_":9,"connType":"tcp","platformTerminal":4,"tittleimgUrl":"http://public.13322.com/6de8715b.jpg","registUser":"4","registTime":"2016-12-03 18:46:44","iconURL":"http://public.13322.com/5d5e0ed0.jpg","updateUser":"mrdeepen"},{"updatebTime":"2017-04-06 14:12:11","port":"8000","sort":10,"desc":"冰川世纪\r\n是一款快速手势操作的游戏，游戏循序渐进，难度越来越大，夸度越来越大，直至您出动双手同时操作，好玩，刺激，当你每过一关的时候估计您都会大叹一口气，为了更","gameType":[{"gameType":"8015","gameTypeName":"模拟经营"}],"imageUrl":"http://public.13322.com/6dd79234.jpg","creater":"mrdeepen","curtypeName":"积分","allImg":"http://public.13322.com/1322b0b8.jpg","rate":1,"name":"冰河世纪","sourUrl":"http://mgame.1332255.com/#/gameHall","modeId":2,"giftbagUrl":"http://mgame.1332255.com/pack","boutiqueImg":"http://public.13322.com/51878916.jpg","updater":"mrdeepen","hotImg":"http://public.13322.com/489c4121.jpg","updateTime":"2017-02-16 11:21:08","status":1,"gameId":10146,"developers":"乐盈盈","ip":" ","popularitVal":90,"officalwebUrl":"http://mgame.1332255.com","isRechtoplatform":1,"hottraveltImg":"http://public.13322.com/6452bcc7.jpg","ROWNUM_":10,"connType":"tcp","platformTerminal":4,"tittleimgUrl":"http://public.13322.com/3ba9398b.jpg","registUser":"4","iconURL":"http://public.13322.com/79859fac.jpg","registTime":"2016-12-09 10:55:39","updateUser":"mrdeepen"}]
//         * totalPages : 2
//         */
//
//        private int totalRows;
//        private int pageNo;
//        private int pageSize;
//        private int totalPages;
//        private List<GameByModeIdInfo> list;
//
//        public int getTotalRows() {
//            return totalRows;
//        }
//
//        public void setTotalRows(int totalRows) {
//            this.totalRows = totalRows;
//        }
//
//        public int getPageNo() {
//            return pageNo;
//        }
//
//        public void setPageNo(int pageNo) {
//            this.pageNo = pageNo;
//        }
//
//        public int getPageSize() {
//            return pageSize;
//        }
//
//        public void setPageSize(int pageSize) {
//            this.pageSize = pageSize;
//        }
//
//        public int getTotalPages() {
//            return totalPages;
//        }
//
//        public void setTotalPages(int totalPages) {
//            this.totalPages = totalPages;
//        }
//
//        public List<GameByModeIdInfo> getList() {
//            return list;
//        }
//
//        public void setList(List<GameByModeIdInfo> list) {
//            this.list = list;
//        }
//
//        public static class GameByModeIdInfo {
//            /**
//             * updatebTime : 2017-04-06 14:11:01
//             * port : 80
//             * sort : 1
//             * desc : 王者荣耀13413413413413241234
//             * gameType : [{"gameType":"8012","gameTypeName":"冒险游戏"}]
//             * imageUrl : http://public.13322.com/5ae8fde0.jpg
//             * creater : mrdeepen
//             * curtypeName : 乐盈币
//             * allImg : http://public.13322.com/26027144.jpg
//             * rate : 1
//             * name : 王者荣耀
//             * sourUrl : http://163.177.158.81/imtt.dd.qq.com/16891/630A9B0CC688192EA3A95E771A450D84.apk?mkey=58b7a0fc08fe6a81&f=d073&c=0&fsname=com.cnvcs.xiangqi_1.70_70170.apk&csr=4d5s&p=.apk
//             * modeId : 2
//             * giftbagUrl : http://nc-release.wdjcdn.com/files/jupiter/5.51.20.13150/wandoujia-jiuyuan3_ad.apk
//             * boutiqueImg : http://public.13322.com/5785149e.jpg
//             * hotImg : http://public.13322.com/20a6e1c4.jpg
//             * updateTime : 2017-03-02 10:06:42
//             * status : 1
//             * gameId : 10440
//             * developers : 乐盈盈
//             * ip :
//             * popularitVal : 100
//             * officalwebUrl : http://nc-release.wdjcdn.com/files/jupiter/5.51.20.13150/wandoujia-jiuyuan3_ad.apk
//             * isRechtoplatform : 1
//             * hottraveltImg : http://public.13322.com/4484078a.jpg
//             * ROWNUM_ : 1
//             * connType : tcp
//             * platformTerminal : 4
//             * tittleimgUrl : http://public.13322.com/428426c8.jpg
//             * registUser : 5
//             * iconURL : http://public.13322.com/3445f0d1.jpg
//             * registTime : 2017-02-27 14:58:31
//             * updateUser : mrdeepen
//             * updater : mrdeepen
//             */
//
//            private String updatebTime;
//            private String port;
//            private int sort;
//            private String desc;
//            private String imageUrl;
//            private String creater;
//            private String curtypeName;
//            private String allImg;
//            private int rate;
//            private String name;
//            private String sourUrl;
//            private int modeId;
//            private String giftbagUrl;
//            private String boutiqueImg;
//            private String hotImg;
//            private String updateTime;
//            private int status;
//            private int gameId;
//            private String developers;
//            private String ip;
//            private int popularitVal;
//            private String officalwebUrl;
//            private int isRechtoplatform;
//            private String hottraveltImg;
//            private int ROWNUM_;
//            private String connType;
//            private int platformTerminal;
//            private String tittleimgUrl;
//            private String registUser;
//            private String iconURL;
//            private String registTime;
//            private String updateUser;
//            private String updater;
//            private List<GameTypeBean> gameType;
//
//            public String getUpdatebTime() {
//                return updatebTime;
//            }
//
//            public void setUpdatebTime(String updatebTime) {
//                this.updatebTime = updatebTime;
//            }
//
//            public String getPort() {
//                return port;
//            }
//
//            public void setPort(String port) {
//                this.port = port;
//            }
//
//            public int getSort() {
//                return sort;
//            }
//
//            public void setSort(int sort) {
//                this.sort = sort;
//            }
//
//            public String getDesc() {
//                return desc;
//            }
//
//            public void setDesc(String desc) {
//                this.desc = desc;
//            }
//
//            public String getImageUrl() {
//                return imageUrl;
//            }
//
//            public void setImageUrl(String imageUrl) {
//                this.imageUrl = imageUrl;
//            }
//
//            public String getCreater() {
//                return creater;
//            }
//
//            public void setCreater(String creater) {
//                this.creater = creater;
//            }
//
//            public String getCurtypeName() {
//                return curtypeName;
//            }
//
//            public void setCurtypeName(String curtypeName) {
//                this.curtypeName = curtypeName;
//            }
//
//            public String getAllImg() {
//                return allImg;
//            }
//
//            public void setAllImg(String allImg) {
//                this.allImg = allImg;
//            }
//
//            public int getRate() {
//                return rate;
//            }
//
//            public void setRate(int rate) {
//                this.rate = rate;
//            }
//
//            public String getName() {
//                return name;
//            }
//
//            public void setName(String name) {
//                this.name = name;
//            }
//
//            public String getSourUrl() {
//                return sourUrl;
//            }
//
//            public void setSourUrl(String sourUrl) {
//                this.sourUrl = sourUrl;
//            }
//
//            public int getModeId() {
//                return modeId;
//            }
//
//            public void setModeId(int modeId) {
//                this.modeId = modeId;
//            }
//
//            public String getGiftbagUrl() {
//                return giftbagUrl;
//            }
//
//            public void setGiftbagUrl(String giftbagUrl) {
//                this.giftbagUrl = giftbagUrl;
//            }
//
//            public String getBoutiqueImg() {
//                return boutiqueImg;
//            }
//
//            public void setBoutiqueImg(String boutiqueImg) {
//                this.boutiqueImg = boutiqueImg;
//            }
//
//            public String getHotImg() {
//                return hotImg;
//            }
//
//            public void setHotImg(String hotImg) {
//                this.hotImg = hotImg;
//            }
//
//            public String getUpdateTime() {
//                return updateTime;
//            }
//
//            public void setUpdateTime(String updateTime) {
//                this.updateTime = updateTime;
//            }
//
//            public int getStatus() {
//                return status;
//            }
//
//            public void setStatus(int status) {
//                this.status = status;
//            }
//
//            public int getGameId() {
//                return gameId;
//            }
//
//            public void setGameId(int gameId) {
//                this.gameId = gameId;
//            }
//
//            public String getDevelopers() {
//                return developers;
//            }
//
//            public void setDevelopers(String developers) {
//                this.developers = developers;
//            }
//
//            public String getIp() {
//                return ip;
//            }
//
//            public void setIp(String ip) {
//                this.ip = ip;
//            }
//
//            public int getPopularitVal() {
//                return popularitVal;
//            }
//
//            public void setPopularitVal(int popularitVal) {
//                this.popularitVal = popularitVal;
//            }
//
//            public String getOfficalwebUrl() {
//                return officalwebUrl;
//            }
//
//            public void setOfficalwebUrl(String officalwebUrl) {
//                this.officalwebUrl = officalwebUrl;
//            }
//
//            public int getIsRechtoplatform() {
//                return isRechtoplatform;
//            }
//
//            public void setIsRechtoplatform(int isRechtoplatform) {
//                this.isRechtoplatform = isRechtoplatform;
//            }
//
//            public String getHottraveltImg() {
//                return hottraveltImg;
//            }
//
//            public void setHottraveltImg(String hottraveltImg) {
//                this.hottraveltImg = hottraveltImg;
//            }
//
//            public int getROWNUM_() {
//                return ROWNUM_;
//            }
//
//            public void setROWNUM_(int ROWNUM_) {
//                this.ROWNUM_ = ROWNUM_;
//            }
//
//            public String getConnType() {
//                return connType;
//            }
//
//            public void setConnType(String connType) {
//                this.connType = connType;
//            }
//
//            public int getPlatformTerminal() {
//                return platformTerminal;
//            }
//
//            public void setPlatformTerminal(int platformTerminal) {
//                this.platformTerminal = platformTerminal;
//            }
//
//            public String getTittleimgUrl() {
//                return tittleimgUrl;
//            }
//
//            public void setTittleimgUrl(String tittleimgUrl) {
//                this.tittleimgUrl = tittleimgUrl;
//            }
//
//            public String getRegistUser() {
//                return registUser;
//            }
//
//            public void setRegistUser(String registUser) {
//                this.registUser = registUser;
//            }
//
//            public String getIconURL() {
//                return iconURL;
//            }
//
//            public void setIconURL(String iconURL) {
//                this.iconURL = iconURL;
//            }
//
//            public String getRegistTime() {
//                return registTime;
//            }
//
//            public void setRegistTime(String registTime) {
//                this.registTime = registTime;
//            }
//
//            public String getUpdateUser() {
//                return updateUser;
//            }
//
//            public void setUpdateUser(String updateUser) {
//                this.updateUser = updateUser;
//            }
//
//            public String getUpdater() {
//                return updater;
//            }
//
//            public void setUpdater(String updater) {
//                this.updater = updater;
//            }
//
//            public List<GameTypeBean> getGameType() {
//                return gameType;
//            }
//
//            public void setGameType(List<GameTypeBean> gameType) {
//                this.gameType = gameType;
//            }
//
//            public static class GameTypeBean {
//                /**
//                 * gameType : 8012
//                 * gameTypeName : 冒险游戏
//                 */
//
//                private String gameType;
//                private String gameTypeName;
//
//                public String getGameType() {
//                    return gameType;
//                }
//
//                public void setGameType(String gameType) {
//                    this.gameType = gameType;
//                }
//
//                public String getGameTypeName() {
//                    return gameTypeName;
//                }
//
//                public void setGameTypeName(String gameTypeName) {
//                    this.gameTypeName = gameTypeName;
//                }
//            }
//        }
//    }

//    private GameByModeIdPage data;
//
//    public GameByModeIdPage getData() {
//        return data;
//    }
//
//    public void setData(GameByModeIdPage data) {
//        this.data = data;
//    }
//
//    public static class GameByModeIdPage {
//
//        private int totalRows;
//        private int pageNo;
//        private int pageSize;
//        private int totalPages;
//        private List<GameByModeIdInfo> list;
//
//        public int getTotalRows() {
//            return totalRows;
//        }
//
//        public void setTotalRows(int totalRows) {
//            this.totalRows = totalRows;
//        }
//
//        public int getPageNo() {
//            return pageNo;
//        }
//
//        public void setPageNo(int pageNo) {
//            this.pageNo = pageNo;
//        }
//
//        public int getPageSize() {
//            return pageSize;
//        }
//
//        public void setPageSize(int pageSize) {
//            this.pageSize = pageSize;
//        }
//
//        public int getTotalPages() {
//            return totalPages;
//        }
//
//        public void setTotalPages(int totalPages) {
//            this.totalPages = totalPages;
//        }
//
//        public List<GameByModeIdInfo> getList() {
//            return list;
//        }
//
//        public void setList(List<GameByModeIdInfo> list) {
//            this.list = list;
//        }
//
//        public static class GameByModeIdInfo {
//            /**
//             * updatebTime : 2017-04-05 10:25:17
//             * port : 8000
//             * createTime : 2017-03-01 10:49:34
//             * sourURL : http://file.liqucn.com/upload/2015/qipai/com.pokercity.ddz_3.14_liqcn.com.apk
//             * desc : 游戏APK:波克斗地主
//             蛛网发射器：帕克自己制作的蛛丝发射器配合特殊研发的蛛丝一起使用，蛛丝柔韧度极强且会在喷射出后在一个小时之后自动融化。
//             蜘蛛战衣：蜘蛛侠的制服，封闭了视觉、嗅觉等，穿上战衣的蜘蛛侠完全通过蜘蛛感应来进行信息摄入。
//
//             * sort : 1
//             * packageName : com.pokercity.ddz
//             * gameType : 8006
//             * imageUrl : http://public.13322.com/73c8327d.jpg
//             * giftbagURL : http://d.ltss8.com/t/wandoujia-juwan1_ad.apk
//             * versionCode : 1.0
//             * creater : mrdeepen
//             * officalwebURL : http://nc-release.wdjcdn.com/files/jupiter/5.51.20.13150/wandoujia-jiuyuan_ad.apk
//             * curtypeName : 元宝
//             * allImg : http://public.13322.com/58a46847.jpg
//             * rate : 1
//             * name : 王者荣耀 android版本/安装包大小测试
//             * gameTypeName : 射击游戏
//             * modeId : 1
//             * boutiqueImg : http://public.13322.com/6ded4807.jpg
//             * updater : mrdeepen
//             * hotImg : http://public.13322.com/a9775a3.jpg
//             * tittleimgURL : http://public.13322.com/78b4543.jpg
//             * updateTime : 2017-03-01 11:17:24
//             * status : 1
//             * gameId : 10441
//             * developers : 测试
//             * packageSize : 6M
//             * ip :
//             * popularitVal : 46
//             * isRechtoplatform : 1
//             * hottraveltImg : http://public.13322.com/7cf2063c.jpg
//             * ROWNUM_ : 1
//             * connType : ssh
//             * platformTerminal : 2
//             * registUser : 5
//             * iconURL : http://public.13322.com/1db7c758.jpg
//             * registTime : 2017-02-28 17:42:02
//             * updateUser : mrdeepen
//             */
//
//            private String updatebTime;
//            private String port;
//            private String createTime;
//            private String sourURL;
//            private String desc;
//            private int sort;
//            private String packageName;
//            private String gameType;
//            private String imageUrl;
//            private String giftbagURL;
//            private String versionCode;
//            private String creater;
//            private String officalwebURL;
//            private String curtypeName;
//            private String allImg;
//            private int rate;
//            private String name;
//            private String gameTypeName;
//            private int modeId;
//            private String boutiqueImg;
//            private String updater;
//            private String hotImg;
//            private String tittleimgURL;
//            private String updateTime;
//            private int status;
//            private int gameId;
//            private String developers;
//            private String packageSize;
//            private String ip;
//            private int popularitVal;
//            private int isRechtoplatform;
//            private String hottraveltImg;
//            private int ROWNUM_;
//            private String connType;
//            private int platformTerminal;
//            private String registUser;
//            private String iconURL;
//            private String registTime;
//            private String updateUser;
//
//            public String getUpdatebTime() {
//                return updatebTime;
//            }
//
//            public void setUpdatebTime(String updatebTime) {
//                this.updatebTime = updatebTime;
//            }
//
//            public String getPort() {
//                return port;
//            }
//
//            public void setPort(String port) {
//                this.port = port;
//            }
//
//            public String getCreateTime() {
//                return createTime;
//            }
//
//            public void setCreateTime(String createTime) {
//                this.createTime = createTime;
//            }
//
//            public String getSourURL() {
//                return sourURL;
//            }
//
//            public void setSourURL(String sourURL) {
//                this.sourURL = sourURL;
//            }
//
//            public String getDesc() {
//                return desc;
//            }
//
//            public void setDesc(String desc) {
//                this.desc = desc;
//            }
//
//            public int getSort() {
//                return sort;
//            }
//
//            public void setSort(int sort) {
//                this.sort = sort;
//            }
//
//            public String getPackageName() {
//                return packageName;
//            }
//
//            public void setPackageName(String packageName) {
//                this.packageName = packageName;
//            }
//
//            public String getGameType() {
//                return gameType;
//            }
//
//            public void setGameType(String gameType) {
//                this.gameType = gameType;
//            }
//
//            public String getImageUrl() {
//                return imageUrl;
//            }
//
//            public void setImageUrl(String imageUrl) {
//                this.imageUrl = imageUrl;
//            }
//
//            public String getGiftbagURL() {
//                return giftbagURL;
//            }
//
//            public void setGiftbagURL(String giftbagURL) {
//                this.giftbagURL = giftbagURL;
//            }
//
//            public String getVersionCode() {
//                return versionCode;
//            }
//
//            public void setVersionCode(String versionCode) {
//                this.versionCode = versionCode;
//            }
//
//            public String getCreater() {
//                return creater;
//            }
//
//            public void setCreater(String creater) {
//                this.creater = creater;
//            }
//
//            public String getOfficalwebURL() {
//                return officalwebURL;
//            }
//
//            public void setOfficalwebURL(String officalwebURL) {
//                this.officalwebURL = officalwebURL;
//            }
//
//            public String getCurtypeName() {
//                return curtypeName;
//            }
//
//            public void setCurtypeName(String curtypeName) {
//                this.curtypeName = curtypeName;
//            }
//
//            public String getAllImg() {
//                return allImg;
//            }
//
//            public void setAllImg(String allImg) {
//                this.allImg = allImg;
//            }
//
//            public int getRate() {
//                return rate;
//            }
//
//            public void setRate(int rate) {
//                this.rate = rate;
//            }
//
//            public String getName() {
//                return name;
//            }
//
//            public void setName(String name) {
//                this.name = name;
//            }
//
//            public String getGameTypeName() {
//                return gameTypeName;
//            }
//
//            public void setGameTypeName(String gameTypeName) {
//                this.gameTypeName = gameTypeName;
//            }
//
//            public int getModeId() {
//                return modeId;
//            }
//
//            public void setModeId(int modeId) {
//                this.modeId = modeId;
//            }
//
//            public String getBoutiqueImg() {
//                return boutiqueImg;
//            }
//
//            public void setBoutiqueImg(String boutiqueImg) {
//                this.boutiqueImg = boutiqueImg;
//            }
//
//            public String getUpdater() {
//                return updater;
//            }
//
//            public void setUpdater(String updater) {
//                this.updater = updater;
//            }
//
//            public String getHotImg() {
//                return hotImg;
//            }
//
//            public void setHotImg(String hotImg) {
//                this.hotImg = hotImg;
//            }
//
//            public String getTittleimgURL() {
//                return tittleimgURL;
//            }
//
//            public void setTittleimgURL(String tittleimgURL) {
//                this.tittleimgURL = tittleimgURL;
//            }
//
//            public String getUpdateTime() {
//                return updateTime;
//            }
//
//            public void setUpdateTime(String updateTime) {
//                this.updateTime = updateTime;
//            }
//
//            public int getStatus() {
//                return status;
//            }
//
//            public void setStatus(int status) {
//                this.status = status;
//            }
//
//            public int getGameId() {
//                return gameId;
//            }
//
//            public void setGameId(int gameId) {
//                this.gameId = gameId;
//            }
//
//            public String getDevelopers() {
//                return developers;
//            }
//
//            public void setDevelopers(String developers) {
//                this.developers = developers;
//            }
//
//            public String getPackageSize() {
//                return packageSize;
//            }
//
//            public void setPackageSize(String packageSize) {
//                this.packageSize = packageSize;
//            }
//
//            public String getIp() {
//                return ip;
//            }
//
//            public void setIp(String ip) {
//                this.ip = ip;
//            }
//
//            public int getPopularitVal() {
//                return popularitVal;
//            }
//
//            public void setPopularitVal(int popularitVal) {
//                this.popularitVal = popularitVal;
//            }
//
//            public int getIsRechtoplatform() {
//                return isRechtoplatform;
//            }
//
//            public void setIsRechtoplatform(int isRechtoplatform) {
//                this.isRechtoplatform = isRechtoplatform;
//            }
//
//            public String getHottraveltImg() {
//                return hottraveltImg;
//            }
//
//            public void setHottraveltImg(String hottraveltImg) {
//                this.hottraveltImg = hottraveltImg;
//            }
//
//            public int getROWNUM_() {
//                return ROWNUM_;
//            }
//
//            public void setROWNUM_(int ROWNUM_) {
//                this.ROWNUM_ = ROWNUM_;
//            }
//
//            public String getConnType() {
//                return connType;
//            }
//
//            public void setConnType(String connType) {
//                this.connType = connType;
//            }
//
//            public int getPlatformTerminal() {
//                return platformTerminal;
//            }
//
//            public void setPlatformTerminal(int platformTerminal) {
//                this.platformTerminal = platformTerminal;
//            }
//
//            public String getRegistUser() {
//                return registUser;
//            }
//
//            public void setRegistUser(String registUser) {
//                this.registUser = registUser;
//            }
//
//            public String getIconURL() {
//                return iconURL;
//            }
//
//            public void setIconURL(String iconURL) {
//                this.iconURL = iconURL;
//            }
//
//            public String getRegistTime() {
//                return registTime;
//            }
//
//            public void setRegistTime(String registTime) {
//                this.registTime = registTime;
//            }
//
//            public String getUpdateUser() {
//                return updateUser;
//            }
//
//            public void setUpdateUser(String updateUser) {
//                this.updateUser = updateUser;
//            }
//        }
//    }



}
