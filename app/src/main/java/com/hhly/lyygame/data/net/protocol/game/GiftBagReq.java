package com.hhly.lyygame.data.net.protocol.game;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by ${HELY} on 17/2/15.
 * 邮箱：heli.lixiong@gmail.com
 */

public class GiftBagReq extends BaseReq {

//country=0&enable=0&gameId=10145

    private Integer type;//// 1:滚动图 2：普通礼包 3：全部

    private String userId;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    private Integer enable;

    private Integer gameId;
    private Integer pageNo;
    private Integer pageSize;
    private String keyword;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    @Override
    public Map<String, String> params() {

        Map<String , String> params = super.params();

        if (enable != null){
            params.put("enable", String.valueOf(enable));
        }
        if (gameId != null){
            params.put("gameId", String.valueOf(gameId));
        }
        if (type != null){
            params.put("type", String.valueOf(type));
        }
        if (pageNo != null){
            params.put("pageNo", String.valueOf(pageNo));
        }
        if (pageSize != null){
            params.put("pageSize", String.valueOf(pageSize));
        }
        if (keyword != null){
            params.put("keyword", keyword);
        }
        if (userId != null){
            params.put("userId", userId);
        }

        return params;
    }
}
