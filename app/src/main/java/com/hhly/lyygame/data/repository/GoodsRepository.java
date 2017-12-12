package com.hhly.lyygame.data.repository;

import com.hhly.lyygame.App;
import com.hhly.lyygame.data.cache.FileManager;
import com.hhly.lyygame.data.cache.GoodsCacheImpl;
import com.hhly.lyygame.data.net.protocol.goods.GoodsListResp;
import com.hhly.lyygame.data.repository.goods.GoodsDataSource;
import com.hhly.lyygame.data.repository.goods.GoodsLocalDataSource;
import com.hhly.lyygame.data.repository.goods.GoodsRemoteDataSource;
import com.hhly.lyygame.presentation.utils.NetworkUtil;

import java.util.Map;

import io.reactivex.Flowable;


/**
 * 游戏数据仓库
 * Created by Simon on 2016/12/9.
 */

public class GoodsRepository implements GoodsDataSource {

    private final GoodsDataSource cache;
    private final GoodsDataSource remote;
    private GoodsCacheImpl mGoodCache;

    public GoodsRepository() {
        mGoodCache = new GoodsCacheImpl(App.getContext(), new FileManager());
        cache = new GoodsLocalDataSource(mGoodCache);
        remote = new GoodsRemoteDataSource(mGoodCache);
    }

    @Override
    public Flowable<GoodsListResp> getGoodsList(final Map<String, String> params) {
        if (NetworkUtil.isAvailable(App.getContext())) {
            return remote.getGoodsList(params);
        }
        return cache.getGoodsList(params);
    }

    @Override
    public boolean isExpiration(Map<String, String> params) {
        return false;
    }

}
