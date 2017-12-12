package com.hhly.lyygame.presentation.view.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Simon on 2016/12/1.
 */

public class ExViewPager extends ViewPager {

    private boolean canScroll = false;

    private int mLastX;

    public ExViewPager(Context context) {
        super(context);
    }

    public ExViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setCanScroll(boolean canScroll) {
        this.canScroll = canScroll;

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!canScroll) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mLastX = (int) ev.getX();
                    break;
                case MotionEvent.ACTION_MOVE:
                    int current = (int) ev.getX();
                    int diff = current - mLastX;
                    mLastX = current;
                    if (Math.abs(diff) > 0) {
                        return false;
                    }
                    break;
            }
            return false;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!canScroll) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mLastX = (int) ev.getX();
                    break;
                case MotionEvent.ACTION_MOVE:
                    int current = (int) ev.getX();
                    int diff = current - mLastX;
                    mLastX = current;
                    if (Math.abs(diff) > 0) {
                        return true;
                    }
                    break;
            }
            return false;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean canScrollHorizontally(int direction) {
        if (!canScroll) {
            return false;
        }
        return super.canScrollHorizontally(direction);
    }
}
