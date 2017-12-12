package com.hhly.lyygame.presentation.view.paydetails;

/**
 * Created by dell on 2017/4/7.
 */

public class DetailItem {
    private String mLable;
    private String mContent;

    public DetailItem() {
    }

    public DetailItem(String lable, String content) {
        mLable = lable;
        mContent = content;
    }

    public String getLable() {
        return mLable;
    }

    public void setLable(String lable) {
        mLable = lable;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }
}
