package com.hhly.lyygame.data.net.protocol.user;

import com.hhly.lyygame.data.net.protocol.BaseResp;

/**
 * Created by ${HELY} on 17/1/3.
 * 邮箱：heli.lixiong@gmail.com
 */

public class AccountCoinsResp extends BaseResp {

    private AccountCoinsPager page;

    public AccountCoinsPager getPage() {
        return page;
    }

    public void setPage(AccountCoinsPager page) {
        this.page = page;
    }
}
