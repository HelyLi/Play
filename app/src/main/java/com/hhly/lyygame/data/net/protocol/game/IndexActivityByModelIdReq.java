package com.hhly.lyygame.data.net.protocol.game;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by dell on 2017/5/15.
 */

public class IndexActivityByModelIdReq extends BaseReq {

    private String terminal;

    private String modelId;

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    @Override
    public Map<String, String> params() {
        Map<String ,String> params = super.params();
        if (terminal != null){
            params.put("terminal", terminal);
        }
        if (modelId != null){
            params.put("modelId", modelId);
        }
        return params;
    }
}
