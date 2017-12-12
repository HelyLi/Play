package com.hhly.lyygame.presentation.view.widget.menu;

import java.util.ArrayList;

/**
 * Created by ${HELY} on 17/1/12.
 * 邮箱：heli.lixiong@gmail.com
 */

public class MenuTwoGroup {

    private int mIndex;

    private String mGroupName;

    private ArrayList<MenuTwoItem> mChildList;

    public MenuTwoGroup(int index, String groupName, ArrayList<MenuTwoItem> childList) {
        mIndex = index;
        mGroupName = groupName;
        mChildList = childList;
    }
    
    public MenuTwoGroup() {
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

    public ArrayList<MenuTwoItem> getChildList() {
        return mChildList;
    }

    public void setChildList(ArrayList<MenuTwoItem> childList) {
        mChildList = childList;
    }
}
