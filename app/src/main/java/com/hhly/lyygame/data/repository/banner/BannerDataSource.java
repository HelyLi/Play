package com.hhly.lyygame.data.repository.banner;

import com.hhly.lyygame.data.net.protocol.banner.GoodsBannerResp;
import com.hhly.lyygame.data.net.protocol.banner.HomeBannerResp;

import java.util.Map;

import io.reactivex.Flowable;


/**
 * Created by Simon on 2016/12/9.
 */

public interface BannerDataSource {

    Flowable<HomeBannerResp> homeBanner(Map<String, String> params);

    Flowable<GoodsBannerResp> goodsBanner(Map<String, String> params);

    boolean isExpiration(Map<String, String> params);
}
