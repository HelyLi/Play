package com.hhly.lyygame.data.net.protocol.banner;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by Simon on 2016/12/7.
 */

public class HomeBannerReq extends BaseReq {

    private Integer platformTerminal;

    public void setIsAll(Integer isAll) {
        this.isAll = isAll;
    }

    private Integer isAll;

    public Integer getPlatformTerminal() {
        return platformTerminal;
    }

    public void setPlatformTerminal(Integer platformTerminal) {
        this.platformTerminal = platformTerminal;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HomeBannerReq{");
        sb.append("terminal=").append(platformTerminal);
        sb.append('}');
        return sb.toString();
    }

    public Map<String, String> params() {
        final Map<String, String> maps = super.params();
        if (platformTerminal != null) {
            maps.put("terminal", String.valueOf(platformTerminal));
        }

        if (isAll != null){
            maps.put("isAll", String.valueOf(isAll));
        }
        return maps;
    }
}
