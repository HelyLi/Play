package com.hhly.lyygame.data.net.protocol.game;

import com.hhly.lyygame.data.net.protocol.BaseResp;

import java.util.List;

/**
 * Created by ${HELY} on 17/1/3.
 * 邮箱：heli.lixiong@gmail.com
 */

public class GameTypeResp extends BaseResp {

    /**
     * pager : {"totalRows":40,"startRow":0,"pageSize":50,"totalPages":1,"list":[{"dictdataId":8022,"dicttypeCode":"GAME_TYPE","dictdataDiplayname":"H5-独家游戏","sort":null,"pid":null,"dictdataValue":null,"remark":"位置：游戏----首页》独家游戏","creattime":1484740626000,"country":0},{"dictdataId":8021,"dicttypeCode":"GAME_TYPE","dictdataDiplayname":"H5-其他","sort":null,"pid":null,"dictdataValue":null,"remark":"位置：游戏大厅》其他","creattime":1484739480000,"country":0},{"dictdataId":8020,"dicttypeCode":"GAME_TYPE","dictdataDiplayname":"H5-对战游戏","sort":null,"pid":null,"dictdataValue":null,"remark":"位置：游戏大厅》对战游戏","creattime":1484739443000,"country":0},{"dictdataId":8019,"dicttypeCode":"GAME_TYPE","dictdataDiplayname":"H5-益智游戏","sort":null,"pid":null,"dictdataValue":null,"remark":"位置：游戏大厅》益智游戏","creattime":1484739429000,"country":0},{"dictdataId":8018,"dicttypeCode":"GAME_TYPE","dictdataDiplayname":"H5-格斗游戏","sort":null,"pid":null,"dictdataValue":null,"remark":"位置：游戏大厅》格斗游戏","creattime":1484739402000,"country":0},{"dictdataId":8017,"dicttypeCode":"GAME_TYPE","dictdataDiplayname":"H5-飞行游戏","sort":null,"pid":null,"dictdataValue":null,"remark":"位置：游戏大厅》飞行游戏","creattime":1484739361000,"country":0},{"dictdataId":8016,"dicttypeCode":"GAME_TYPE","dictdataDiplayname":"H5-养成游戏","sort":null,"pid":null,"dictdataValue":null,"remark":"位置：游戏大厅》养成游戏","creattime":1484739317000,"country":0},{"dictdataId":8015,"dicttypeCode":"GAME_TYPE","dictdataDiplayname":"H5-模拟经营","sort":null,"pid":null,"dictdataValue":null,"remark":"位置：游戏大厅》模拟经营","creattime":1484739296000,"country":0},{"dictdataId":8014,"dicttypeCode":"GAME_TYPE","dictdataDiplayname":"H5-动作游戏","sort":null,"pid":null,"dictdataValue":null,"remark":"位置：游戏大厅》动作游戏","creattime":1484739262000,"country":0},{"dictdataId":8013,"dicttypeCode":"GAME_TYPE","dictdataDiplayname":"H5-竞速游戏","sort":null,"pid":null,"dictdataValue":null,"remark":"位置：游戏大厅》竞速游戏","creattime":1484739237000,"country":0},{"dictdataId":8012,"dicttypeCode":"GAME_TYPE","dictdataDiplayname":"H5-冒险游戏","sort":null,"pid":null,"dictdataValue":null,"remark":"位置：游戏大厅》冒险游戏","creattime":1484739204000,"country":0},{"dictdataId":282,"dicttypeCode":"GAME_LABEL","dictdataDiplayname":"宫廷","sort":null,"pid":null,"dictdataValue":null,"remark":"是谁","creattime":1482920824000,"country":0},{"dictdataId":281,"dicttypeCode":"GAME_TYPE","dictdataDiplayname":"pc类型","sort":null,"pid":null,"dictdataValue":null,"remark":"就是一个类型，简简单单啦啦","creattime":1482897081000,"country":0},{"dictdataId":8007,"dicttypeCode":"GAME_TYPE","dictdataDiplayname":"H5-推荐","sort":null,"pid":null,"dictdataValue":null,"remark":"位置：游戏----首页》推荐","creattime":1482207047000,"country":0},{"dictdataId":247,"dicttypeCode":"GAME_LABEL","dictdataDiplayname":"最新游戏","sort":null,"pid":null,"dictdataValue":null,"remark":null,"creattime":1481957234000,"country":0},{"dictdataId":241,"dicttypeCode":"GAME_LABEL","dictdataDiplayname":"其它游戏","sort":null,"pid":null,"dictdataValue":null,"remark":"其它游戏","creattime":1481622879000,"country":0},{"dictdataId":237,"dicttypeCode":"GAME_LABEL","dictdataDiplayname":"益智游戏","sort":null,"pid":null,"dictdataValue":null,"remark":null,"creattime":1481594655000,"country":0},{"dictdataId":236,"dicttypeCode":"GAME_LABEL","dictdataDiplayname":"格斗游戏","sort":null,"pid":null,"dictdataValue":null,"remark":null,"creattime":1481594646000,"country":0},{"dictdataId":235,"dicttypeCode":"GAME_LABEL","dictdataDiplayname":"体育游戏","sort":null,"pid":null,"dictdataValue":null,"remark":null,"creattime":1481594629000,"country":0},{"dictdataId":234,"dicttypeCode":"GAME_LABEL","dictdataDiplayname":"飞行游戏","sort":null,"pid":null,"dictdataValue":null,"remark":null,"creattime":1481594620000,"country":0},{"dictdataId":233,"dicttypeCode":"GAME_LABEL","dictdataDiplayname":"养成游戏","sort":null,"pid":null,"dictdataValue":null,"remark":null,"creattime":1481594610000,"country":0},{"dictdataId":232,"dicttypeCode":"GAME_LABEL","dictdataDiplayname":"模拟经营","sort":null,"pid":null,"dictdataValue":null,"remark":null,"creattime":1481594601000,"country":0},{"dictdataId":231,"dicttypeCode":"GAME_LABEL","dictdataDiplayname":"动作游戏","sort":null,"pid":null,"dictdataValue":null,"remark":null,"creattime":1481594587000,"country":0},{"dictdataId":230,"dicttypeCode":"GAME_LABEL","dictdataDiplayname":"竞速游戏","sort":null,"pid":null,"dictdataValue":null,"remark":null,"creattime":1481594579000,"country":0},{"dictdataId":229,"dicttypeCode":"GAME_LABEL","dictdataDiplayname":"战棋游戏","sort":null,"pid":null,"dictdataValue":null,"remark":null,"creattime":1481594569000,"country":0},{"dictdataId":228,"dicttypeCode":"GAME_LABEL","dictdataDiplayname":"策略游戏","sort":null,"pid":null,"dictdataValue":null,"remark":null,"creattime":1481594557000,"country":0},{"dictdataId":227,"dicttypeCode":"GAME_LABEL","dictdataDiplayname":"冒险游戏","sort":null,"pid":null,"dictdataValue":null,"remark":null,"creattime":1481594545000,"country":0},{"dictdataId":226,"dicttypeCode":"GAME_LABEL","dictdataDiplayname":"即时战斗","sort":null,"pid":null,"dictdataValue":null,"remark":null,"creattime":1481594532000,"country":0},{"dictdataId":225,"dicttypeCode":"GAME_LABEL","dictdataDiplayname":"射击游戏","sort":null,"pid":null,"dictdataValue":null,"remark":"射击游戏","creattime":1481594520000,"country":0},{"dictdataId":224,"dicttypeCode":"GAME_LABEL","dictdataDiplayname":"角色扮演","sort":null,"pid":null,"dictdataValue":null,"remark":"角色扮演标签","creattime":1481594500000,"country":0},{"dictdataId":8011,"dicttypeCode":"GAME_TYPE","dictdataDiplayname":"H5-本周推荐","sort":11,"pid":null,"dictdataValue":null,"remark":"位置：游戏大厅》本周推荐","creattime":1480160657000,"country":0},{"dictdataId":8010,"dicttypeCode":"GAME_TYPE","dictdataDiplayname":"H5-最新游戏","sort":10,"pid":null,"dictdataValue":null,"remark":"位置：游戏大厅》最新游戏","creattime":1480160627000,"country":0},{"dictdataId":8009,"dicttypeCode":"GAME_TYPE","dictdataDiplayname":"H5-单机精品","sort":9,"pid":null,"dictdataValue":null,"remark":"位置：游戏----首页》单机","creattime":1480160592000,"country":0},{"dictdataId":8008,"dicttypeCode":"GAME_TYPE","dictdataDiplayname":"H5-网游","sort":8,"pid":null,"dictdataValue":null,"remark":"位置：游戏----首页》网游","creattime":1480160549000,"country":0},{"dictdataId":8006,"dicttypeCode":"GAME_TYPE","dictdataDiplayname":"H5-射击游戏","sort":6,"pid":null,"dictdataValue":null,"remark":"位置：游戏大厅》射击游戏","creattime":1480147762000,"country":0},{"dictdataId":8005,"dicttypeCode":"GAME_TYPE","dictdataDiplayname":"H5-角色扮演","sort":5,"pid":null,"dictdataValue":null,"remark":"位置：游戏大厅》角色扮演","creattime":1480147755000,"country":0},{"dictdataId":8004,"dicttypeCode":"GAME_TYPE","dictdataDiplayname":"H5-休闲竞技","sort":4,"pid":null,"dictdataValue":null,"remark":"位置：游戏大厅》游戏竞技","creattime":1480147703000,"country":0},{"dictdataId":8003,"dicttypeCode":"GAME_TYPE","dictdataDiplayname":"H5-棋牌休闲","sort":3,"pid":null,"dictdataValue":null,"remark":"位置：游戏大厅》棋牌休闲","creattime":1480147697000,"country":0},{"dictdataId":8002,"dicttypeCode":"GAME_TYPE","dictdataDiplayname":"H5-战争策略","sort":2,"pid":null,"dictdataValue":null,"remark":"位置：游戏大厅》热门游戏","creattime":1480147664000,"country":0},{"dictdataId":8001,"dicttypeCode":"GAME_TYPE","dictdataDiplayname":"H5-策略游戏","sort":1,"pid":null,"dictdataValue":null,"remark":"位置：游戏大厅》策略游戏","creattime":1480147625000,"country":0}],"pageNo":"1","pageScale":"50"}
     * dicttype : null
     * dictdata : null
     */

    private GameTypePager pager;
    private Object dicttype;
    private Object dictdata;

    public GameTypePager getPager() {
        return pager;
    }

    public void setPager(GameTypePager pager) {
        this.pager = pager;
    }

    public Object getDicttype() {
        return dicttype;
    }

    public void setDicttype(Object dicttype) {
        this.dicttype = dicttype;
    }

    public Object getDictdata() {
        return dictdata;
    }

    public void setDictdata(Object dictdata) {
        this.dictdata = dictdata;
    }

    public static class GameTypePager {

        private int totalRows;
        private int startRow;
        private int pageSize;
        private int totalPages;
        private String pageNo;
        private String pageScale;
        private List<GameType> list;

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

        public List<GameType> getList() {
            return list;
        }

        public void setList(List<GameType> list) {
            this.list = list;
        }

        public static class GameType {
            /**
             * dictdataId : 8022
             * dicttypeCode : GAME_TYPE
             * dictdataDiplayname : H5-独家游戏
             * sort : null
             * pid : null
             * dictdataValue : null
             * remark : 位置：游戏----首页》独家游戏
             * creattime : 1484740626000
             * country : 0
             */

            private int dictdataId;
            private String dicttypeCode;
            private String dictdataDiplayname;
            private String sort;
            private String pid;
            private String dictdataValue;
            private String remark;
            private long creattime;
            private int country;

            public int getDictdataId() {
                return dictdataId;
            }

            public void setDictdataId(int dictdataId) {
                this.dictdataId = dictdataId;
            }

            public String getDicttypeCode() {
                return dicttypeCode;
            }

            public void setDicttypeCode(String dicttypeCode) {
                this.dicttypeCode = dicttypeCode;
            }

            public String getDictdataDiplayname() {
                return dictdataDiplayname;
            }

            public void setDictdataDiplayname(String dictdataDiplayname) {
                this.dictdataDiplayname = dictdataDiplayname;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public String getDictdataValue() {
                return dictdataValue;
            }

            public void setDictdataValue(String dictdataValue) {
                this.dictdataValue = dictdataValue;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public long getCreattime() {
                return creattime;
            }

            public void setCreattime(long creattime) {
                this.creattime = creattime;
            }

            public int getCountry() {
                return country;
            }

            public void setCountry(int country) {
                this.country = country;
            }

            @Override
            public String toString() {
                return "GameType{" +
                        "dictdataId=" + dictdataId +
                        ", dicttypeCode='" + dicttypeCode + '\'' +
                        ", dictdataDiplayname='" + dictdataDiplayname + '\'' +
                        ", sort='" + sort + '\'' +
                        ", pid='" + pid + '\'' +
                        ", dictdataValue='" + dictdataValue + '\'' +
                        ", remark='" + remark + '\'' +
                        ", creattime=" + creattime +
                        ", country=" + country +
                        '}';
            }
        }
    }
}
