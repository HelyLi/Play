package com.hhly.lyygame.data.net.protocol.game;

import com.hhly.lyygame.data.net.protocol.BaseResp;

import java.util.List;

/**
 * Created by ${HELY} on 17/1/3.
 * 邮箱：heli.lixiong@gmail.com
 */

public class GameAreaResp extends BaseResp {

    private Object list;
    private Object listGameAreaConfig;
    private Object listGameServiceConfig;
    private Object gac;
    private Object gsc;
    private Object pl;
    private List<ListGameServerAreaBean> listGameServerArea;

    public Object getList() {
        return list;
    }

    public void setList(Object list) {
        this.list = list;
    }

    public Object getListGameAreaConfig() {
        return listGameAreaConfig;
    }

    public void setListGameAreaConfig(Object listGameAreaConfig) {
        this.listGameAreaConfig = listGameAreaConfig;
    }

    public Object getListGameServiceConfig() {
        return listGameServiceConfig;
    }

    public void setListGameServiceConfig(Object listGameServiceConfig) {
        this.listGameServiceConfig = listGameServiceConfig;
    }

    public Object getGac() {
        return gac;
    }

    public void setGac(Object gac) {
        this.gac = gac;
    }

    public Object getGsc() {
        return gsc;
    }

    public void setGsc(Object gsc) {
        this.gsc = gsc;
    }

    public Object getPl() {
        return pl;
    }

    public void setPl(Object pl) {
        this.pl = pl;
    }

    public List<ListGameServerAreaBean> getListGameServerArea() {
        return listGameServerArea;
    }

    public void setListGameServerArea(List<ListGameServerAreaBean> listGameServerArea) {
        this.listGameServerArea = listGameServerArea;
    }

    public static class ListGameServerAreaBean {
        /**
         * id : 154
         * serviceId : 123
         * serviceName : 雷电一服
         * areaId : 117
         * areaName : 雷电3区
         * platformId : 10113
         * platformName : 雷电
         * rate : null
         * status : 1
         * onlineTime : null
         * updateTime : 1486537640000
         * createTime : 1480921432000
         * remark : null
         * type : null
         * gameServerUrl : http://baidu.com
         * serverNum : 雷电二服
         * serverStatus : 1
         * payStatus : 1
         * isDisplay : 1
         * startMaintanceTime : 1480575784000
         * endMaintanceTime : 1486537396000
         * startUpTime : 1486191800000
         * closeSureUrl : http://baidu.com
         * officalwebUrl : http://baidu.com
         * maintanceUrl : http://baidu.com
         */

        private int id;
        private int serviceId;
        private String serviceName;
        private int areaId;
        private String areaName;
        private int platformId;
        private String platformName;
        private Object rate;
        private int status;
        private Object onlineTime;
        private long updateTime;
        private long createTime;
        private Object remark;
        private Object type;
        private String gameServerUrl;
        private String serverNum;
        private int serverStatus;
        private int payStatus;
        private int isDisplay;
        private long startMaintanceTime;
        private long endMaintanceTime;
        private long startUpTime;
        private String closeSureUrl;
        private String officalwebUrl;
        private String maintanceUrl;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getServiceId() {
            return serviceId;
        }

        public void setServiceId(int serviceId) {
            this.serviceId = serviceId;
        }

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }

        public int getAreaId() {
            return areaId;
        }

        public void setAreaId(int areaId) {
            this.areaId = areaId;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public int getPlatformId() {
            return platformId;
        }

        public void setPlatformId(int platformId) {
            this.platformId = platformId;
        }

        public String getPlatformName() {
            return platformName;
        }

        public void setPlatformName(String platformName) {
            this.platformName = platformName;
        }

        public Object getRate() {
            return rate;
        }

        public void setRate(Object rate) {
            this.rate = rate;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Object getOnlineTime() {
            return onlineTime;
        }

        public void setOnlineTime(Object onlineTime) {
            this.onlineTime = onlineTime;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public Object getType() {
            return type;
        }

        public void setType(Object type) {
            this.type = type;
        }

        public String getGameServerUrl() {
            return gameServerUrl;
        }

        public void setGameServerUrl(String gameServerUrl) {
            this.gameServerUrl = gameServerUrl;
        }

        public String getServerNum() {
            return serverNum;
        }

        public void setServerNum(String serverNum) {
            this.serverNum = serverNum;
        }

        public int getServerStatus() {
            return serverStatus;
        }

        public void setServerStatus(int serverStatus) {
            this.serverStatus = serverStatus;
        }

        public int getPayStatus() {
            return payStatus;
        }

        public void setPayStatus(int payStatus) {
            this.payStatus = payStatus;
        }

        public int getIsDisplay() {
            return isDisplay;
        }

        public void setIsDisplay(int isDisplay) {
            this.isDisplay = isDisplay;
        }

        public long getStartMaintanceTime() {
            return startMaintanceTime;
        }

        public void setStartMaintanceTime(long startMaintanceTime) {
            this.startMaintanceTime = startMaintanceTime;
        }

        public long getEndMaintanceTime() {
            return endMaintanceTime;
        }

        public void setEndMaintanceTime(long endMaintanceTime) {
            this.endMaintanceTime = endMaintanceTime;
        }

        public long getStartUpTime() {
            return startUpTime;
        }

        public void setStartUpTime(long startUpTime) {
            this.startUpTime = startUpTime;
        }

        public String getCloseSureUrl() {
            return closeSureUrl;
        }

        public void setCloseSureUrl(String closeSureUrl) {
            this.closeSureUrl = closeSureUrl;
        }

        public String getOfficalwebUrl() {
            return officalwebUrl;
        }

        public void setOfficalwebUrl(String officalwebUrl) {
            this.officalwebUrl = officalwebUrl;
        }

        public String getMaintanceUrl() {
            return maintanceUrl;
        }

        public void setMaintanceUrl(String maintanceUrl) {
            this.maintanceUrl = maintanceUrl;
        }
    }
}
