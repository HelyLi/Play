package com.hhly.lyygame.data.net.protocol.user;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.List;
import java.util.Map;

/**
 * Created by ${HELY} on 17/2/13.
 * 邮箱：heli.lixiong@gmail.com
 */

public class FeedbackReq extends BaseReq {

    private String contact;//联系方式

    private String content;//反馈内容

    private List<String> images;//图片

//    private Integer country;

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

//    public void setCountry(Integer country) {
//        this.country = country;
//    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();

        if (contact != null) {
            params.put("contact", contact);
        }
        if (content != null) {
            params.put("content", content);
        }
        if (images != null) {
            params.put("images", images());
        }

        return params;
    }

    private String images() {

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < images.size(); i++) {
            builder.append(images.get(i));
            if (i != (images.size() - 1))
                builder.append(";");
        }

        return builder.toString();
    }

}
