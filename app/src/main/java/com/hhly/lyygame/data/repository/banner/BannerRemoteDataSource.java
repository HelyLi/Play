package com.hhly.lyygame.data.repository.banner;

import com.hhly.lyygame.data.cache.BannerCacheImpl;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.BannerApi;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.protocol.banner.GoodsBannerResp;
import com.hhly.lyygame.data.net.protocol.banner.HomeBannerResp;

import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Banner远程数据
 * Created by Simon on 2016/12/9.
 */

public class BannerRemoteDataSource implements BannerDataSource {

    private BannerApi mBannerApi;
    private BannerCacheImpl mBannerCache;

    public BannerRemoteDataSource(BannerCacheImpl bannerCache) {
        mBannerApi = RetrofitManager.getInstance(ApiType.BANNER_API).getBannerApi();
        mBannerCache = bannerCache;
    }

    @Override
    public Flowable<HomeBannerResp> homeBanner(final Map<String, String> params) {
        return mBannerApi.homeBanner(params)
                .doOnNext(new Consumer<HomeBannerResp>() {
                    @Override
                    public void accept(@NonNull HomeBannerResp homeBannerResp) throws Exception {
                        if (mBannerCache != null && homeBannerResp != null) {
                            mBannerCache.put(params, homeBannerResp);
                        }
                    }
                });
    }

    @Override
    public Flowable<GoodsBannerResp> goodsBanner(final Map<String, String> params) {
        return mBannerApi.goodsBanner(params)
                .doOnNext(new Consumer<GoodsBannerResp>() {
                    @Override
                    public void accept(@NonNull GoodsBannerResp goodsBannerResp) throws Exception {
                        if (mBannerCache != null && goodsBannerResp != null) {
                            mBannerCache.put(params, goodsBannerResp);
                        }
                    }
                });
    }

    @Override
    public boolean isExpiration(Map<String, String> params) {
        return false;
    }

}
