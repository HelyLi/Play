package com.hhly.lyygame.presentation.rxbus.event;

import android.support.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 推广（独家、推荐、单机、网游Event）
 * Created by dell on 2017/4/24.
 */

public class OtherEvent {

    public static final int OTHER_401_TYPE = 401;
    public static final int OTHER_402_TYPE = 402;

    @Retention(RetentionPolicy.SOURCE)
    @Target(ElementType.PARAMETER)
    @IntDef({OTHER_401_TYPE, OTHER_402_TYPE})
    public @interface ExtensionHeaderEventType {

    }

    private int mEventType;

    public OtherEvent(@ExtensionHeaderEventType int eventType) {
        this.mEventType = eventType;
    }

    public int getEventType() {
        return mEventType;
    }

    public void setEventType(int eventType) {
        mEventType = eventType;
    }

}
