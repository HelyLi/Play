package com.hhly.lyygame.presentation.view.activity;

/**
 * Created by Simon on 2016/11/29.
 */

public class ActivityInfo {

    private boolean isEnd;
    private String name;
    private String date;
    private String url;
    private String picUrl;

    public ActivityInfo(boolean isEnd, String name, String date, String url, String picUrl) {
        this.isEnd = isEnd;
        this.name = name;
        this.date = date;
        this.url = url;
        this.picUrl = picUrl;
    }

    public ActivityInfo(boolean isEnd, String name, String date, String url) {
        this.isEnd = isEnd;
        this.name = name;
        this.date = date;
        this.url = url;
        this.picUrl = "http://imgsrc.baidu.com/anxun/pic/item/32fa828ba61ea8d3a24f0db9900a304e251f5819.jpg";
    }

    public boolean isEnd() {
        return isEnd;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }

    public String getPicUrl() {
        return picUrl;
    }
}
