package com.hhly.lyygame.presentation.view.notification;

import com.chad.library.adapter.base.entity.SectionEntity;
import com.hhly.lyygame.data.net.protocol.user.NotificationActivityResp;

/**
 * Created by Simon on 2016/11/30.
 */

public class ActivityItem extends SectionEntity<NotificationActivityResp.ActivityBean> {

    private NotificationActivityResp.ActivityBean data;

    public ActivityItem(NotificationActivityResp.ActivityBean data) {
        super(data);
        this.data = data;
    }
    public NotificationActivityResp.ActivityBean getData(){
        return data;
    }
}
