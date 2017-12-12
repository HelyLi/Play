package com.hhly.lyygame.data.net.protocol;

import com.hhly.lyygame.App;
import com.hhly.lyygame.data.db.manager.AccountManager;
import com.hhly.lyygame.presentation.utils.Installation;
import com.hhly.lyygame.presentation.utils.TelephonyUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Simon on 2016/12/6.
 */

public class BaseReq {

    private Integer country = 0;

    private String deviceId = Installation.id(App.getContext());

    private String platformTerminal = String.valueOf(TelephonyUtil.getOsTypeInt());

    private String token = AccountManager.getInstance().getToken();

    public void setCountry(Integer country) {
        this.country = country;
    }

    public Integer getCountry() {
        return country;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public Map<String, String> params() {

        Map<String, String> params = new HashMap<>();

        if (country != null) {
            params.put("country", String.valueOf(country));
        }
        if (deviceId != null) {
            params.put("deviceId", deviceId);
        }
        if (platformTerminal != null){
            params.put("platformTerminal", platformTerminal);
        }
        if (token != null){
            params.put("token", token);
        }
        return params;
    }

    @Override
    public String toString() {
        return "BaseReq{" +
                "country=" + country +
                '}';
    }
}
