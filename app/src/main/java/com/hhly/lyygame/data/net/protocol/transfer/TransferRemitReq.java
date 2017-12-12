package com.hhly.lyygame.data.net.protocol.transfer;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by ${HELY} on 17/4/24.
 * 邮箱：heli.lixiong@gmail.com
 */

public class TransferRemitReq extends BaseReq {

    private String fPlatformId;//转出平台id 必输
    private String tPlatformId;//转入平台id 必输
    private String fGameGolds;//转出多少游戏币 必输
    private String terminal;//操作终端(1:pc,2:iOS 3:安卓 4:H5, 5其他)
    private String sourceId;//从那个哪个平台id 跳转过来的PlatformId

    public void setfPlatformId(String fPlatformId) {
        this.fPlatformId = fPlatformId;
    }

    public void settPlatformId(String tPlatformId) {
        this.tPlatformId = tPlatformId;
    }

    public void setfGameGolds(String fGameGolds) {
        this.fGameGolds = fGameGolds;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    @Override
    public Map<String, String> params() {
        Map<String , String> params = super.params();

        if (fPlatformId != null){
            params.put("fPlatformId", fPlatformId);
        }
        if (tPlatformId != null){
            params.put("tPlatformId", tPlatformId);
        }
        if (fGameGolds != null){
            params.put("fGameGolds", fGameGolds);
        }
        if (terminal != null){
            params.put("terminal", terminal);
        }
        if (sourceId != null){
            params.put("sourceId", sourceId);
        }

        return params;
    }
}
