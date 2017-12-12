package com.hhly.lyygame.data.net.protocol.game;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by ${HELY} on 17/2/17.
 * 邮箱：heli.lixiong@gmail.com
 */

public class NoticeDetailReq extends BaseReq {

    private Integer noticeId;

    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();

        if (noticeId != null){
            params.put("noticeId", String.valueOf(noticeId));
        }

        return params;
    }
}
