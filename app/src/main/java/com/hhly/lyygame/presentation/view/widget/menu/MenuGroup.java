package com.hhly.lyygame.presentation.view.widget.menu;

import java.util.ArrayList;

/**
 * Created by Simon on 2016/11/23.
 */

public class MenuGroup {

    private int mIndex;

    private String mGroupName;

    private ArrayList<MenuItem> mChildList;

    public MenuGroup(int index, String groupName, ArrayList<MenuItem> childList) {
        mIndex = index;
        mGroupName = groupName;
        mChildList = childList;
    }

    public MenuGroup() {
    }

    public int getIndex() {
        return mIndex;
    }

    public void setIndex(int index) {
        mIndex = index;
    }

    public String getGroupName() {
        return mGroupName;
    }

    public void setGroupName(String groupName) {
        mGroupName = groupName;
    }

    public ArrayList<MenuItem> getChildList() {
        return mChildList;
    }

    public void setChildList(ArrayList<MenuItem> childList) {
        mChildList = childList;
    }
}
