package com.hhly.lyygame.data.repository.goods;

import com.hhly.lyygame.data.cache.GoodsCacheImpl;
import com.hhly.lyygame.data.net.protocol.goods.GoodsListResp;

import java.util.Map;

import io.reactivex.Flowable;


/**
 * 游戏本地数据
 * Created by Simon on 2016/12/9.
 */

public class GoodsLocalDataSource implements GoodsDataSource {

    private final GoodsCacheImpl mGoodsCache;

    public GoodsLocalDataSource(GoodsCacheImpl goodsCache) {
        mGoodsCache = goodsCache;
    }

    @Override
    public Flowable<GoodsListResp> getGoodsList(Map<String, String> params) {
        return mGoodsCache.getGoodsList(params);
    }

    @Override
    public boolean isExpiration(Map<String, String> params) {
        return mGoodsCache.isExpiration(params);
    }

}
