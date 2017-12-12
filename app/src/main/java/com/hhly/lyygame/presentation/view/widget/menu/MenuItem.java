package com.hhly.lyygame.presentation.view.widget.menu;

/**
 * Created by Simon on 2016/11/23.
 */

public class MenuItem {

    private int mId;
    private int mIcon;
    private int mTitle;
    private Runnable mAction;

    public MenuItem() {
    }

    public MenuItem(int id, int icon, int title, Runnable action) {
        mId = id;
        mIcon = icon;
        mTitle = title;
        mAction = action;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getIcon() {
        return mIcon;
    }

    public void setIcon(int icon) {
        mIcon = icon;
    }

    public int getTitle() {
        return mTitle;
    }

    public void setTitle(int title) {
        mTitle = title;
    }

    public Runnable getAction() {
        return mAction;
    }

    public void setAction(Runnable action) {
        mAction = action;
    }
}
