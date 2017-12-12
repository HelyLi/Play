package com.hhly.lyygame.data.net.protocol.banner;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by ${HELY} on 17/2/5.
 * 邮箱：heli.lixiong@gmail.com
 */

public class GoodsBannerReq extends BaseReq {

    private Integer country;

    private Integer platform;

    public void setIsAll(Integer isAll) {
        this.isAll = isAll;
    }

    private Integer isAll;

    public Integer getCountry() {
        return country;
    }

    public void setCountry(Integer country) {
        this.country = country;
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    @Override
    public Map<String, String> params() {

        Map<String, String> params = super.params();

        if (country != null){
            params.put("country", String.valueOf(country));
        }
        if (platform != null){
            params.put("terminal", String.valueOf(platform));
        }
        if (isAll != null){
            params.put("isAll", String.valueOf(isAll));
        }

        return params;
    }

    @Override
    public String toString() {
        return "GoodsBannerReq{" +
                "Country=" + country +
                ", terminal=" + platform +
                '}';
    }
}
