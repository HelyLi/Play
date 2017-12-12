package com.hhly.lyygame.data.net;

import com.hhly.lyygame.data.net.protocol.BaseResp;
import com.hhly.lyygame.data.net.protocol.goods.AddStoreGoodsResp;
import com.hhly.lyygame.data.net.protocol.goods.DelStoreGoodsResp;
import com.hhly.lyygame.data.net.protocol.goods.GoodsExchangeHistoryResp;
import com.hhly.lyygame.data.net.protocol.goods.GoodsExchangeResp;
import com.hhly.lyygame.data.net.protocol.goods.GoodsInfoResp;
import com.hhly.lyygame.data.net.protocol.goods.GoodsListResp;
import com.hhly.lyygame.data.net.protocol.goods.StoreGoodsResp;
import com.hhly.lyygame.data.net.protocol.user.AddAddressResp;
import com.hhly.lyygame.data.net.protocol.user.DelAddressResp;
import com.hhly.lyygame.data.net.protocol.user.UserAddressResp;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 用户相关
 * Created by Simon on 2016/12/6.
 */

public interface GoodsApi {

    /**
     * 获取积分商城中商品信息
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("pointShopGoods/getAllGoods")
    Flowable<GoodsListResp> getGoodsList(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 获取收藏品
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("pointShopGoods/queryStoreGoods")
    Flowable<StoreGoodsResp> getStoreGoods(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 兑换收藏品
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("pointShopGoods/exchangeGoods")
    Flowable<GoodsExchangeResp> exchangeGoods(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 兑换纪录
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("pointShopGoods/queryExchangedGoods")
    Flowable<GoodsExchangeHistoryResp> getExchangeGoodsHistory(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 获取商品信息
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("pointShopGoods/getGoodsInfo")
    Flowable<GoodsInfoResp> getGoodsInfo(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 用户地址
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("pointShopUser/queryAddress")
    Flowable<UserAddressResp> getAddress(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 添加地址&修改默认地址
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("pointShopUser/addAddress")
    Flowable<AddAddressResp> addAddress(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 删除地址
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("pointShopUser/delAddress")
    Flowable<DelAddressResp> delAddress(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 添加收藏
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("pointShopGoods/addStoreGoods")
    Flowable<AddStoreGoodsResp> addStoreGoods(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 删除收藏的商品
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("pointShopGoods/delStoreGoods")
    Flowable<DelStoreGoodsResp> delStoreGoods(@FieldMap(encoded = true) Map<String, String> params);


    /**
    * 删除收藏的商品
    * @param params
    * @return
    */
    @FormUrlEncoded
    @POST("pointShopGoods/cancelStoreGoods")
    Flowable<BaseResp> cancelStoreGoods(@FieldMap(encoded = true) Map<String, String> params);

}



