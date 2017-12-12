package com.hhly.lyygame.data.net.protocol.user;

import java.util.List;

/**
 * Created by ${HELY} on 17/2/10.
 * 邮箱：heli.lixiong@gmail.com
 */

public class AccountScorePager {

    private int totalRows;
    private int startRow;
    private int pageSize;
    private int totalPages;
    private String pageNo;
    private String pageScale;
    private List<AccountScoreBean> list;

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

    public List<AccountScoreBean> getList() {
        return list;
    }

    public void setList(List<AccountScoreBean> list) {
        this.list = list;
    }

    public static class AccountScoreBean {
        /**
         * orderId : hhly_re170210151719760827
         * point : 10.0
         * userId : hhly91480
         * beforeAccPoint : 1.4999999999978123E19
         * afterAccPoint : 1.4999999999978123E19
         * changeType : 1
         * recordTime : 1486711039000
         * oppositeOrderId : null
         * oppositePlateformId : null
         * oppositeInfo : 兑换：虹焰之火游戏点卡100
         * tradeType : 1
         * remark : 兑换：虹焰之火游戏点卡100
         * terminalsType : 0
         */

        private String orderId;
        private double point;
        private String userId;
        private double beforeAccPoint;
        private double afterAccPoint;
        private int changeType;
        private long recordTime;
        private Object oppositeOrderId;
        private Object oppositePlateformId;
        private String oppositeInfo;
        private int tradeType;
        private int TRADETYPE;
        private String remark;
        private String NAME;
        private String RECORDTIME;
        private String APPLYAMOUNT;
        private int terminalsType;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public double getPoint() {
            return point;
        }

        public void setPoint(double point) {
            this.point = point;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public double getBeforeAccPoint() {
            return beforeAccPoint;
        }

        public void setBeforeAccPoint(double beforeAccPoint) {
            this.beforeAccPoint = beforeAccPoint;
        }

        public double getAfterAccPoint() {
            return afterAccPoint;
        }

        public void setAfterAccPoint(double afterAccPoint) {
            this.afterAccPoint = afterAccPoint;
        }

        public int getChangeType() {
            return changeType;
        }

        public void setChangeType(int changeType) {
            this.changeType = changeType;
        }

        public long getRecordTime() {
            return recordTime;
        }

        public void setRecordTime(long recordTime) {
            this.recordTime = recordTime;
        }

        public Object getOppositeOrderId() {
            return oppositeOrderId;
        }

        public void setOppositeOrderId(Object oppositeOrderId) {
            this.oppositeOrderId = oppositeOrderId;
        }

        public Object getOppositePlateformId() {
            return oppositePlateformId;
        }

        public void setOppositePlateformId(Object oppositePlateformId) {
            this.oppositePlateformId = oppositePlateformId;
        }

        public String getOppositeInfo() {
            return oppositeInfo;
        }

        public void setOppositeInfo(String oppositeInfo) {
            this.oppositeInfo = oppositeInfo;
        }

        public int getTradeType() {
            return tradeType;
        }

        public void setTradeType(int tradeType) {
            this.tradeType = tradeType;
        }

        public int getTRADETYPE() {
            return TRADETYPE;
        }

        public void setTRADETYPE(int TRADETYPE) {
            this.TRADETYPE = TRADETYPE;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getNAME() {
            return NAME;
        }

        public void setNAME(String NAME) {
            this.NAME = NAME;
        }

        public String getRECORDTIME() {
            return RECORDTIME;
        }

        public void setRECORDTIME(String RECORDTIME) {
            this.RECORDTIME = RECORDTIME;
        }

        public String getAPPLYAMOUNT() {
            return APPLYAMOUNT;
        }

        public void setAPPLYAMOUNT(String APPLYAMOUNT) {
            this.APPLYAMOUNT = APPLYAMOUNT;
        }

        public int getTerminalsType() {
            return terminalsType;
        }

        public void setTerminalsType(int terminalsType) {
            this.terminalsType = terminalsType;
        }
    }
}
