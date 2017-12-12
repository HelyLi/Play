package com.hhly.lyygame.data.net.protocol.user;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by ${HELY} on 17/1/3.
 * 邮箱：heli.lixiong@gmail.com
 */

public class NicknameKeyWordReq extends BaseReq {

    private String nickname;

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();
        if (nickname != null) {
            params.put("nickname", nickname);
        }
        return params;
    }
}
