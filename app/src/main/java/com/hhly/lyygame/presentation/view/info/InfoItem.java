package com.hhly.lyygame.presentation.view.info;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Simon on 2016/11/23.
 */

class InfoItem {

    private int mIndex;
    private int mType;
    private int mTitle;
    private String mContent;
    private Runnable mAction;

    @Retention(RetentionPolicy.SOURCE)
    @interface Type{}
    static final int COMM = 0;
    static final int AVATAR = 1;

    InfoItem() {
    }

    InfoItem(int index, @Type int type, int title, String content, Runnable action) {
        mIndex = index;
        mType = type;
        mTitle = title;
        mContent = content;
        mAction = action;
    }

    private InfoItem(Builder builder) {
        setIndex(builder.mIndex);
        setType(builder.mType);
        setTitle(builder.mTitle);
        setContent(builder.mContent);
        setAction(builder.mAction);
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

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
    }

    public int getIndex() {
        return mIndex;
    }

    public void setIndex(int index) {
        mIndex = index;
    }

    static final class Builder {
        private int mIndex;
        private int mType;
        private int mTitle;
        private String mContent;
        private Runnable mAction;

        public Builder() {
        }

        public Builder type(int val) {
            mType = val;
            return this;
        }

        public Builder title(int val) {
            mTitle = val;
            return this;
        }

        public Builder content(String val) {
            mContent = val;
            return this;
        }

        public Builder action(Runnable val) {
            mAction = val;
            return this;
        }

        public Builder index(int val) {
            mIndex = val;
            return this;
        }

        public InfoItem build() {
            return new InfoItem(this);
        }
    }
}
