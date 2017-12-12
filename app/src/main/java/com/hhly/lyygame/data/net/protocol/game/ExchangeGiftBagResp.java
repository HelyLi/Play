package com.hhly.lyygame.data.net.protocol.game;

import com.google.gson.Gson;
import com.hhly.lyygame.data.net.protocol.BaseResp;

/**
 * Created by ${HELY} on 17/2/18.
 * 邮箱：heli.lixiong@gmail.com
 */

public class ExchangeGiftBagResp extends BaseResp {
    /**
     * msg : null
     * record : {"id":5071,"giftBag":280,"code":"ASD10","time":1489824920518,"userId":"hhly92698","come":null,"rotationImgUrl":null,"imgUrl":null,"title":null,"redirectUrl":null,"needScoreId":null,"needScore":null}
     */

    private RecordBean record;

    public RecordBean getRecord() {
        return record;
    }

    public void setRecord(RecordBean record) {
        this.record = record;
    }

    public static class RecordBean {
        /**
         * id : 5071
         * giftBag : 280
         * code : ASD10
         * time : 1489824920518
         * userId : hhly92698
         * come : null
         * rotationImgUrl : null
         * imgUrl : null
         * title : null
         * redirectUrl : null
         * needScoreId : null
         * needScore : null
         */

        private int id;
        private int giftBag;
        private String code;
        private long time;
        private String userId;
        private Object come;
        private Object rotationImgUrl;
        private Object imgUrl;
        private Object title;
        private Object redirectUrl;
        private Object needScoreId;
        private Object needScore;

        public static RecordBean objectFromData(String str) {

            return new Gson().fromJson(str, RecordBean.class);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getGiftBag() {
            return giftBag;
        }

        public void setGiftBag(int giftBag) {
            this.giftBag = giftBag;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public Object getCome() {
            return come;
        }

        public void setCome(Object come) {
            this.come = come;
        }

        public Object getRotationImgUrl() {
            return rotationImgUrl;
        }

        public void setRotationImgUrl(Object rotationImgUrl) {
            this.rotationImgUrl = rotationImgUrl;
        }

        public Object getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(Object imgUrl) {
            this.imgUrl = imgUrl;
        }

        public Object getTitle() {
            return title;
        }

        public void setTitle(Object title) {
            this.title = title;
        }

        public Object getRedirectUrl() {
            return redirectUrl;
        }

        public void setRedirectUrl(Object redirectUrl) {
            this.redirectUrl = redirectUrl;
        }

        public Object getNeedScoreId() {
            return needScoreId;
        }

        public void setNeedScoreId(Object needScoreId) {
            this.needScoreId = needScoreId;
        }

        public Object getNeedScore() {
            return needScore;
        }

        public void setNeedScore(Object needScore) {
            this.needScore = needScore;
        }
    }
}
