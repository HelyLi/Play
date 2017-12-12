package com.hhly.lyygame.data.net.protocol.user;

import com.hhly.lyygame.data.net.protocol.BaseResp;

/**
 * Created by ${HELY} on 17/2/13.
 * 邮箱：heli.lixiong@gmail.com
 */

public class FeedbackResp extends BaseResp {

    private Object feedback;
    private Object pager;

    public Object getFeedback() {
        return feedback;
    }

    public void setFeedback(Object feedback) {
        this.feedback = feedback;
    }

    public Object getPager() {
        return pager;
    }

    public void setPager(Object pager) {
        this.pager = pager;
    }
}
