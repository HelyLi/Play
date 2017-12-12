package com.hhly.lyygame.data.repository.banner;

import com.hhly.lyygame.data.cache.BannerCacheImpl;
import com.hhly.lyygame.data.net.protocol.banner.GoodsBannerResp;
import com.hhly.lyygame.data.net.protocol.banner.HomeBannerResp;

import java.util.Map;

import io.reactivex.Flowable;


/**
 * Banner本地数据
 * Created by Simon on 2016/12/9.
 */

public class BannerLocalDataSource implements BannerDataSource {

    private BannerCacheImpl mBannerCache;

    public BannerLocalDataSource(BannerCacheImpl bannerCache) {
        this.mBannerCache = bannerCache;
    }

    @Override
    public Flowable<HomeBannerResp> homeBanner(Map<String, String> params) {
        return mBannerCache.homeBanner(params);
    }

    @Override
    public Flowable<GoodsBannerResp> goodsBanner(Map<String, String> params) {
        return mBannerCache.goodsBanner(params);
    }

    @Override
    public boolean isExpiration(Map<String, String> params) {
        return mBannerCache.isExpiration(params);
    }

}
