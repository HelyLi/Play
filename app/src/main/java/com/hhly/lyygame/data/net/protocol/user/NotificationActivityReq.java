package com.hhly.lyygame.data.net.protocol.user;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by ${HELY} on 17/2/23.
 * 邮箱：heli.lixiong@gmail.com
 */

public class NotificationActivityReq extends BaseReq {

    private Integer isAll;

    private Integer platform;

    private Integer type;

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    private Integer pageSize;

    private Integer pageNo;

    public void setIsAll(Integer isAll) {
        this.isAll = isAll;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();

        if (isAll != null){
            params.put("isAll", String.valueOf(isAll));
        }
        if (platform != null){
            params.put("platform", String.valueOf(platform));
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

        return params;
    }
}
