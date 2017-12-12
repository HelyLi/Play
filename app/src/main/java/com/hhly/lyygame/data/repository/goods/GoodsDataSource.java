package com.hhly.lyygame.data.repository.goods;

import com.hhly.lyygame.data.net.protocol.goods.GoodsListResp;

import java.util.Map;

import io.reactivex.Flowable;

/**
 * 游戏仓库数据源接口
 * Created by Simon on 2016/12/9.
 */

public interface GoodsDataSource {

    Flowable<GoodsListResp> getGoodsList(Map<String, String> params);

    boolean isExpiration(Map<String, String> params);

}
