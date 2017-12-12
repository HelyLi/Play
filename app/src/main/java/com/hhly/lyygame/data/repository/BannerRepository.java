package com.hhly.lyygame.data.repository;

import com.hhly.lyygame.App;
import com.hhly.lyygame.data.cache.BannerCacheImpl;
import com.hhly.lyygame.data.cache.FileManager;
import com.hhly.lyygame.data.net.protocol.banner.GoodsBannerResp;
import com.hhly.lyygame.data.net.protocol.banner.HomeBannerResp;
import com.hhly.lyygame.data.repository.banner.BannerDataSource;
import com.hhly.lyygame.data.repository.banner.BannerLocalDataSource;
import com.hhly.lyygame.data.repository.banner.BannerRemoteDataSource;
import com.hhly.lyygame.presentation.utils.NetworkUtil;

import java.util.Map;

import io.reactivex.Flowable;


/**
 * Banner数据仓库
 * Created by Simon on 2016/12/9.
 */

public class BannerRepository implements BannerDataSource {

    private final BannerDataSource remote;
    private final BannerDataSource cache;
    private BannerCacheImpl mBannerCache;

    public BannerRepository() {
        mBannerCache = new BannerCacheImpl(App.getContext(), new FileManager());
        cache = new BannerLocalDataSource(mBannerCache);
        remote = new BannerRemoteDataSource(mBannerCache);
    }

    @Override
    public Flowable<HomeBannerResp> homeBanner(Map<String, String> params) {
        if (NetworkUtil.isAvailable(App.getContext())) {
            return remote.homeBanner(params);
        }
        return cache.homeBanner(params);
    }

    @Override
    public Flowable<GoodsBannerResp> goodsBanner(Map<String, String> params) {
        if (NetworkUtil.isAvailable(App.getContext())) {
            return remote.goodsBanner(params);
        }
        return cache.goodsBanner(params);
    }

    @Override
    public boolean isExpiration(Map<String, String> params) {
        return false;
    }

}
