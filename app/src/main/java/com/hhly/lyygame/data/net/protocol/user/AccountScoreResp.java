package com.hhly.lyygame.data.net.protocol.user;

import com.hhly.lyygame.data.net.protocol.BaseResp;

/**
 * Created by ${HELY} on 17/1/3.
 * 邮箱：heli.lixiong@gmail.com
 */

public class AccountScoreResp extends BaseResp {

    private AccountScorePager page;

    public AccountScorePager getPage() {
        return page;
    }

    public void setPage(AccountScorePager page) {
        this.page = page;
    }
}
