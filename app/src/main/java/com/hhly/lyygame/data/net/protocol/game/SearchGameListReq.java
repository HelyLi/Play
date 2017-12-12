package com.hhly.lyygame.data.net.protocol.game;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by ${HELY} on 17/1/3.
 * 邮箱：heli.lixiong@gmail.com
 */

public class SearchGameListReq extends BaseReq {

    private String keyword;
    private Integer platformTerminal;
    private Integer dataSize;
    private Integer pageNo;

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public void setPlatformTerminal(Integer platformTerminal) {
        this.platformTerminal = platformTerminal;
    }

    public void setDataSize(Integer dataSize) {
        this.dataSize = dataSize;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();

        if (keyword != null){
            params.put("keyword", keyword);
        }
        if (platformTerminal != null){
            params.put("terminal" ,String.valueOf(platformTerminal));
        }
        if (dataSize != null){
            params.put("pageSize", String.valueOf(dataSize));
        }
        if (pageNo != null){
            params.put("pageNo", String.valueOf(pageNo));
        }

        return params;
    }
}
