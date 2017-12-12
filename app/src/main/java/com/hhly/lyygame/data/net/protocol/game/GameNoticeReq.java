package com.hhly.lyygame.data.net.protocol.game;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by ${HELY} on 17/2/15.
 * 邮箱：heli.lixiong@gmail.com
 */

public class GameNoticeReq extends BaseReq {

    private Integer noticeType;//1新闻公告 2活动推广 3综合 4游戏攻略 5游戏资料 100其他

    private Integer platformTerminal;//1.PC 2.安卓 3ios 4.h5  5.其他

    private Integer platformId;//游戏id

    private Integer dataSize;

    public void setPlatformTerminal(Integer platformTerminal) {
        this.platformTerminal = platformTerminal;
    }

    public void setDataSize(Integer dataSize) {
        this.dataSize = dataSize;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    private Integer pageNo;

    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    public void setNoticeType(Integer noticeType) {

        this.noticeType = noticeType;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();

        if (noticeType != null){
            params.put("noticeType", String.valueOf(noticeType));
        }
        if (platformId != null){
            params.put("platformId", String.valueOf(platformId));
        }
        if (platformTerminal != null){
            params.put("terminal", String.valueOf(platformTerminal));
        }
        if (dataSize != null){
            params.put("pageSize", String.valueOf(dataSize));
        }
        if (pageNo != null){
            params.put("pageNo", String.valueOf(pageNo));
        }

        return params;
    }

    @Override
    public String toString() {
        return "GameNoticeReq{" +
                "noticeType=" + noticeType +
                ", platformTerminal=" + platformTerminal +
                ", platformId=" + platformId +
                ", dataSize=" + dataSize +
                ", pageNo=" + pageNo +
                '}';
    }
}
