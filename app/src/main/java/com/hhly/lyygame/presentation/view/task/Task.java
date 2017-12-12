package com.hhly.lyygame.presentation.view.task;

/**
 * Created by Simon on 2016/11/29.
 */

public class Task {

    public static final int TYPE_SIGN_IN = 1;
    public static final int TYPE_TASK = 2;
    public static final int TYPE_SCORE = 3;

    private int mType;
    private boolean isFinished;

    private String mName;
    private String mDescription;
    private int mScore;

    public int getIndex() {
        return mIndex;
    }

    public void setIndex(int index) {
        this.mIndex = index;
    }

    private int mIndex;

    public Task(int type, boolean isFinished, String name, String description, int score, int index) {
        mType = type;
        this.isFinished = isFinished;
        mName = name;
        mDescription = description;
        mScore = score;
        mIndex = index;
    }

    public Task() {
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public int getScore() {
        return mScore;
    }

    public void setScore(int score) {
        mScore = score;
    }
}
