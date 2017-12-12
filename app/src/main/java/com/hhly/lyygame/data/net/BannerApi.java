package com.hhly.lyygame.data.net;

import com.hhly.lyygame.data.net.protocol.banner.GoodsBannerResp;
import com.hhly.lyygame.data.net.protocol.banner.HomeBannerResp;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Banner相关
 * Created by Simon on 2016/12/7.
 */

public interface BannerApi {

    /**
     * 首页轮播
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("index/getIndexlbt")
    Flowable<HomeBannerResp> homeBanner(@FieldMap(encoded = true)Map<String, String> params);

    /**
     * 商城轮播
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("pointShopGoods/getRotationInfo")
    Flowable<GoodsBannerResp> goodsBanner(@FieldMap(encoded = true) Map<String, String> params);

}
