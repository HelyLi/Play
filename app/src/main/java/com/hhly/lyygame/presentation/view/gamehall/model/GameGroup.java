package com.hhly.lyygame.presentation.view.gamehall.model;

import android.support.annotation.IntDef;
import android.text.TextUtils;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simon on 2016/11/25.
 */

public class GameGroup<T> implements MultiItemEntity {

    private int mType;
    private int mLayoutResId;
    private int mSectionIcon;
    private String mSectionIconUrl;
    private int mSectionTitleResId;
    private String mSectionTitle;
    private boolean mEnableMore;
    private List<T> mDataList;

    public GameGroup() {
        mDataList = new ArrayList<>();
    }

    private GameGroup(Builder builder) {
        mType = builder.mType;
        mLayoutResId = builder.mLayoutResId;
        mSectionIcon = builder.mSectionIcon;
        mSectionIconUrl = builder.mSectionIconUrl;
        mSectionTitleResId = builder.mSectionTitleResId;
        mSectionTitle = builder.mSectionTitle;
        mEnableMore = builder.mEnableMore;
        mDataList = new ArrayList<>();
    }

    public List<T> getDataList() {
        return mDataList;
    }

    public GameGroup<T> setDataList(List<T> dataList) {
        mDataList = dataList;
        return this;
    }

    public int getLayoutResId() {
        return mLayoutResId;
    }

    public int getSectionIcon() {
        return mSectionIcon;
    }

    public String getSectionIconUrl() {
        return mSectionIconUrl;
    }

    public int getSectionTitleResId() {
        return mSectionTitleResId;
    }

    public String getSectionTitle() {
        return mSectionTitle;
    }

    public boolean isEnableMore() {
        return mEnableMore;
    }

    @Override
    public int getItemType() {
        return mType;
    }

    @IntDef({PLAYED, HOT, ONLINE, OFFLINE, NEW, REC_WEEKLY})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Type{}

    //我玩过的
    public static final int PLAYED = 10;
    //热门游戏
    public static final int HOT = 11;
    //网络游戏
    public static final int ONLINE = 12;
    //单机精品
    public static final int OFFLINE = 13;
    //最新游戏
    public static final int NEW = 14;
    //本周推荐
    public static final int REC_WEEKLY = 15;

    public static final class Builder {
        private int mType;
        private int mLayoutResId = 0;
        private int mSectionIcon = 0;
        private String mSectionIconUrl;
        private int mSectionTitleResId = 0;
        private String mSectionTitle;
        private boolean mEnableMore;

        public Builder() {
        }

        public Builder type(@Type int val) {
            mType = val;
            return this;
        }

        public Builder layoutResId(int val) {
            mLayoutResId = val;
            return this;
        }

        public Builder sectionIcon(int val) {
            if (TextUtils.isEmpty(mSectionIconUrl)) {
                mSectionIcon = val;
            }
            return this;
        }

        public Builder sectionIconUrl(String val) {
            if (mSectionIcon == 0) {
                mSectionIconUrl = val;
            }
            return this;
        }

        public Builder sectionTitleResId(int val) {
            if (TextUtils.isEmpty(mSectionTitle)) {
                mSectionTitleResId = val;
            }
            return this;
        }

        public Builder sectionTitle(String val) {
            if (mSectionTitleResId == 0) {
                mSectionTitle = val;
            }
            return this;
        }

        public Builder enableMore(boolean val) {
            mEnableMore = val;
            return this;
        }

        public GameGroup build() {
            return new GameGroup(this);
        }
    }
}
