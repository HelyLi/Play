package com.hhly.lyygame.data.net;

import com.hhly.lyygame.data.net.protocol.game.ExchangeGiftBagResp;
import com.hhly.lyygame.data.net.protocol.game.GameAreaResp;
import com.hhly.lyygame.data.net.protocol.game.GameByIdInfoResp;
import com.hhly.lyygame.data.net.protocol.game.GameByModelIdResp;
import com.hhly.lyygame.data.net.protocol.game.GameByTypeResp;
import com.hhly.lyygame.data.net.protocol.game.GameNoticeResp;
import com.hhly.lyygame.data.net.protocol.game.GamePictureInfoResp;
import com.hhly.lyygame.data.net.protocol.game.GameTypeResp;
import com.hhly.lyygame.data.net.protocol.game.GiftBagResp;
import com.hhly.lyygame.data.net.protocol.game.IndexActivityByModelIdResp;
import com.hhly.lyygame.data.net.protocol.game.NewsDetailsResp;
import com.hhly.lyygame.data.net.protocol.game.NewsListResp;
import com.hhly.lyygame.data.net.protocol.game.RecentlyGameListResp;
import com.hhly.lyygame.data.net.protocol.game.SearchGameListResp;
import com.hhly.lyygame.data.net.protocol.game.SignGiftBagResp;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 游戏api
 * Created by Simon on 2016/12/9.
 */

public interface GameApi {

    /**
     * 获取精品游戏列表
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("index/getIndexGameByModelId")
    Flowable<GameByModelIdResp> getIndexGameByModelId(@FieldMap(encoded = true)Map<String, String> params);

    /**
     * 搜索游戏
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("index/getIndeqbyx")
    Flowable<SearchGameListResp> getIndexAllGame(@FieldMap(encoded = true)Map<String, String> params);

    /**
     * 获取新闻公告分页列表(平台)
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("activity/getActivityInfo")
    Flowable<NewsListResp> getNewsList(@FieldMap(encoded = true)Map<String, String> params);

    /**
     * 获取新闻公告单个详情
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("activity/getActivityId")
    Flowable<NewsDetailsResp> getNewsDetails(@FieldMap(encoded = true)Map<String, String> params);

    /**
     * 所有的游戏类型
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("index/getGameType")
    Flowable<GameTypeResp> getGameType(@FieldMap(encoded = true)Map<String, String> params);

    /**
     * 获取用户最近玩过的游戏
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("user/getRecentlyPlayedList")
    Flowable<RecentlyGameListResp> getRecentlyGameList(@FieldMap(encoded = true)Map<String, String> params);

    /**
     * 获取开服列表
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("index/getGameArea")
    Flowable<GameAreaResp> getGameArea(@FieldMap(encoded = true)Map<String, String> params);

    /**
     * 根据类型获取游戏
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("index/getGameByType")//index/getGameByType
    Flowable<GameByTypeResp> getGameByType(@FieldMap(encoded = true)Map<String, String> params);

    /**
     * 获取游戏信息
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("index/getGameById")
    Flowable<GameByIdInfoResp> getGameById(@FieldMap(encoded = true)Map<String, String> params);

    /**
     * 获取游戏图片信息
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("index/gamePictureInfo")
    Flowable<GamePictureInfoResp> getGamePictureInfo(@FieldMap(encoded = true)Map<String, String> params);

    /**
     * 获取游戏图片信息
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("index/gameNotice")
    Flowable<GameNoticeResp> getGameNotice(@FieldMap(encoded = true)Map<String, String> params);

    /**
     * 查询礼包列表
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("gift/getGiftBag")
    Flowable<GiftBagResp> getGiftBag(@FieldMap(encoded = true)Map<String, String> params);

    /**
     * 获取游戏图片信息
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("index/gameNoticeById")
    Flowable<NewsDetailsResp> getGameNotieById(@FieldMap(encoded = true)Map<String, String> params);

    /**
     * 获取礼包详情
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("gift/getSignGiftBag")
    Flowable<SignGiftBagResp> getSignGiftBag(@FieldMap(encoded = true)Map<String, String> params);

    /**
     * 兑换礼包
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("gift/lqGiftBag")
    Flowable<ExchangeGiftBagResp> exchangeGiftBag(@FieldMap(encoded = true)Map<String, String> params);

    /**
     * 活动专题
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("pageActivity/getIndexActivityByModelId")
    Flowable<IndexActivityByModelIdResp> getIndexActivityByModelId(@FieldMap(encoded = true)Map<String, String> params);

}
