package com.hhly.lyygame.data.net.protocol.game;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by ${HELY} on 17/4/7.
 * 邮箱：heli.lixiong@gmail.com
 */

public class GameByModelIdReq extends BaseReq {

    private Integer terminal;

    private Integer pageNo;

    private Integer pageSize;

    private String modelId;

    private String keyword;

    private String channelId;

    public void setTerminal(Integer terminal) {
        this.terminal = terminal;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @Override
    public Map<String, String> params() {

        Map<String, String> params = super.params();

        if (terminal != null){
            params.put("terminal", String.valueOf(terminal));
        }
        if (pageNo != null){
            params.put("pageNo", String.valueOf(pageNo));
        }
        if (pageSize != null){
            params.put("pageSize", String.valueOf(pageSize));
        }
        if (modelId != null){
            params.put("modelId", modelId);
        }
        if (keyword != null){
            params.put("keyword", keyword);
        }
        if (channelId != null){
            params.put("channelId", channelId);
        }
        return params;
    }

    @Override
    public String toString() {
        return "GameByModelIdReq{" +
                "terminal=" + terminal +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", modelId='" + modelId + '\'' +
                ", keyword='" + keyword + '\'' +
                ", channelId='" + channelId + '\'' +
                '}';
    }
}
