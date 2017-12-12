package com.hhly.lyygame.data.net.protocol.update;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Simon on 2016/9/30.
 */

public class CheckUpdateReq extends BaseReq{

    private Integer country = 0;
    private String appId;
    private String versionCode;
    private String osType;//android  / ios

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    @Override
    public void setCountry(Integer country) {
        this.country = country;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = new HashMap<>();

        if (appId != null){
            params.put("appId", appId);
        }
        if (versionCode != null){
            params.put("versionCode", versionCode);
        }
        if (osType != null){
            params.put("osType", osType);
        }
        if (country != null){
            params.put("country", String.valueOf(country));
        }

        return params;
    }
}
