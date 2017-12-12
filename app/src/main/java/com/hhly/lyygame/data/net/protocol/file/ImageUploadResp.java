package com.hhly.lyygame.data.net.protocol.file;

import com.hhly.lyygame.data.net.protocol.BaseResp;

/**
 * Created by ${HELY} on 17/1/3.
 * 邮箱：heli.lixiong@gmail.com
 */

public class ImageUploadResp extends BaseResp {

    String url;

    public String getImageUrl() {
        return url;
    }

    public void setImageUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ImageUploadResp{" +
                "url='" + url + '\'' +
                '}';
    }

}
