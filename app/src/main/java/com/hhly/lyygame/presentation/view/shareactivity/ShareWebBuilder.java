package com.hhly.lyygame.presentation.view.shareactivity;

/**
 * Created by dell on 2017/5/10.
 */

public class ShareWebBuilder {

    private StringBuilder mStringBuilder;

    public static String tourUrl = "http://market.13322.com/active/act/thActive/playSummer.html";
    public static String shareUrl = "http://market.13322.com/active/act/thActive/taiguo.html";

//    public static String tourUrl = "https://shouyin.yeepay.com/nc-cashier-wap/wap/request/10014876039/LcHTXvSwZO*MpoZQdavy4Q%3D%3D";
//    public static String shareUrl = "http://192.168.74.163:9688/active/act/thActive/taiguo.html";

    public ShareWebBuilder() {
        mStringBuilder = new StringBuilder();
        mStringBuilder.append("&isApp=1");
    }

    /**
     * userId
     * @param userId
     * @return
     */
    ShareWebBuilder userId(String userId) {
        mStringBuilder.append("&userId=").append(userId);
        return this;
    }

    /**
     * share
     * @param share
     * @return
     */
    ShareWebBuilder share(String share) {
        mStringBuilder.append("&share=").append(share);
        return this;
    }

    public String buildParams() {
        if (mStringBuilder.toString().startsWith("&")) {
            mStringBuilder.delete(0, 1);
        }
        return mStringBuilder.toString();
    }


}
