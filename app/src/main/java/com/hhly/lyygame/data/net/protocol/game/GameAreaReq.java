package com.hhly.lyygame.data.net.protocol.game;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by hely on 17/1/3.
 * 邮箱：heli.lixiong@gmail.com
 *
 * 获取开服列表请求
 *
 */

public class GameAreaReq extends BaseReq {

    //查询条数
    private Integer dataSize;//不传这默认查询所有
    //平台类型
    private Integer platformTerminal;//1.PC 2.安卓 3.ios 4.h5  5.其他
    //开服类型
    private Integer serverType;//1 最新开服 0 开服预告

    public Integer getServerType() {
        return serverType;
    }

    public void setServerType(Integer serverType) {
        this.serverType = serverType;
    }

    public Integer getDataSize() {
        return dataSize;
    }

    public void setDataSize(Integer dataSize) {
        this.dataSize = dataSize;
    }

    public Integer getPlatformTerminal() {
        return platformTerminal;
    }

    public void setPlatformTerminal(Integer platformTerminal) {
        this.platformTerminal = platformTerminal;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();

        if (dataSize != null){
            params.put("dataSize", String.valueOf(dataSize));
        }
        if (platformTerminal != null){
            params.put("terminal",String.valueOf(platformTerminal));
        }
        if (serverType != null){
            params.put("serverType", String.valueOf(serverType));
        }

        return params;
    }
}
