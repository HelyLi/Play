package com.hhly.lyygame.data.repository.goods;

import com.hhly.lyygame.data.cache.GoodsCacheImpl;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.GoodsApi;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.protocol.goods.GoodsListResp;

import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;


/**
 * 游戏远程数据
 * Created by Simon on 2016/12/9.
 */

public class GoodsRemoteDataSource implements GoodsDataSource {

    private GoodsApi mGoodsApi;
    private final GoodsCacheImpl mGameCache;

    public GoodsRemoteDataSource(GoodsCacheImpl goodsCache) {
        mGameCache = goodsCache;
        mGoodsApi = RetrofitManager.getInstance(ApiType.GOODS_API).getGoodsApi();
    }

    @Override
    public Flowable<GoodsListResp> getGoodsList(final Map<String, String> params) {
        return mGoodsApi.getGoodsList(params)
                .doOnNext(new Consumer<GoodsListResp>() {
                    @Override
                    public void accept(@NonNull GoodsListResp goodsListResp) throws Exception {
                        if (mGameCache != null && goodsListResp != null) {
                            mGameCache.put(params,goodsListResp);
                        }
                    }
                });
    }

    @Override
    public boolean isExpiration(Map<String, String> params) {
        return false;
    }

}
