package com.hhly.lyygame.presentation.view.info;

import java.util.ArrayList;

/**
 * Created by Simon on 2016/11/23.
 */

class InfoGroup {

    private int mIndex;

    private ArrayList<InfoItem> mChildList;

    InfoGroup(int index, ArrayList<InfoItem> childList) {
        mIndex = index;
        mChildList = childList;
    }

    InfoGroup() {
    }

    public int getIndex() {
        return mIndex;
    }

    public void setIndex(int index) {
        mIndex = index;
    }

    public ArrayList<InfoItem> getChildList() {
        return mChildList;
    }

    public void setChildList(ArrayList<InfoItem> childList) {
        mChildList = childList;
    }
}
