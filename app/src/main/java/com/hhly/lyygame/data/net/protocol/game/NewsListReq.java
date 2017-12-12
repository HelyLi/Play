package com.hhly.lyygame.data.net.protocol.game;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by ${HELY} on 17/1/3.
 * 邮箱：heli.lixiong@gmail.com
 */

public class NewsListReq extends BaseReq {

    private String platformTerminal;

    private Integer pageNo;
    private Integer pageSize;
    private String terminal;//1：pc端 2：ios端 3：安卓 4：h5
    private String type;//广告类型：1.新闻，2.活动推广

    public String getPlatformTerminal() {
        return platformTerminal;
    }

    public void setPlatformTerminal(String platformTerminal) {
        this.platformTerminal = platformTerminal;
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

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();

        if (platformTerminal != null){
            params.put("platformTerminal", platformTerminal);
        }
        if (pageNo != null){
            params.put("pageNo", String.valueOf(pageNo));
        }
        if (pageSize != null){
            params.put("pageSize", String.valueOf(pageSize));
        }
        if (terminal != null){
            params.put("terminal", terminal);
        }
        if (type != null){
            params.put("type", type);
        }
        return params;
    }

}
