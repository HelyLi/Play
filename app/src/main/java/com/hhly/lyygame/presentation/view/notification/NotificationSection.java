package com.hhly.lyygame.presentation.view.notification;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by Simon on 2016/11/30.
 */

public class NotificationSection extends SectionEntity {

    private int iconResId;
    private Runnable action;
    private int unreadCount;

    public NotificationSection(int iconResId, String header, Runnable action) {
        super(true, header);
        this.iconResId = iconResId;
        this.action = action;
    }

    public int getIconResId() {
        return iconResId;
    }

    public Runnable getAction() {
        return action;
    }

    public int getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(int unreadCount) {
        this.unreadCount = unreadCount;
    }
}
