package com.hhly.lyygame.data.net.protocol.update;

import com.hhly.lyygame.data.net.protocol.BaseResp;

/**
 * Created by ${HELY} on 17/3/13.
 * 邮箱：heli.lixiong@gmail.com
 */

public class ADInfoResp extends BaseResp {

    private ADInfo data;

    public ADInfo getData() {
        return data;
    }

    public void setData(ADInfo data) {
        this.data = data;
    }

    public static class ADInfo {

        /**
         * platformId : 10163
         * description : 新游戏来了
         * jumpType : 1
         * imgUrl : http://public.13322.com/620ae301.jpg
         */

        private String platformId;
        private String description;
        private int jumpType;
        private String imgUrl;

        public String getGameId() {
            return platformId;
        }

        public void setGameId(String gameId) {
            this.platformId = gameId;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getJumpType() {
            return jumpType;
        }

        public void setJumpType(int jumpType) {
            this.jumpType = jumpType;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        @Override
        public String toString() {
            return "ADInfo{" +
                    "platformId='" + platformId + '\'' +
                    ", description='" + description + '\'' +
                    ", jumpType=" + jumpType +
                    ", imgUrl='" + imgUrl + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ADInfoResp{" +
                "data=" + data +
                '}';
    }
}
