package com.hhly.lyygame.presentation.view.notification;

import com.chad.library.adapter.base.entity.SectionEntity;
import com.hhly.lyygame.data.net.protocol.user.MsgPager;

/**
 * Created by Simon on 2016/11/30.
 * 消息
 */

public class MsgItem extends SectionEntity<MsgPager.MsgBean> {

    private MsgPager.MsgBean data;

    public MsgItem(MsgPager.MsgBean data) {
        super(data);
        this.data = data;
    }
    public MsgPager.MsgBean getData(){
        return data;
    }
}
