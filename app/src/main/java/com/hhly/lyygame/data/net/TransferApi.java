package com.hhly.lyygame.data.net;

import com.hhly.lyygame.data.net.protocol.transfer.TransferExchangeGameInfoResp;
import com.hhly.lyygame.data.net.protocol.transfer.TransferExchangeResp;
import com.hhly.lyygame.data.net.protocol.transfer.TransferFindSonPlatformBalanceResp;
import com.hhly.lyygame.data.net.protocol.transfer.TransferGameBalanceResp;
import com.hhly.lyygame.data.net.protocol.transfer.TransferGameListResp;
import com.hhly.lyygame.data.net.protocol.transfer.TransferLYBInfoResp;
import com.hhly.lyygame.data.net.protocol.transfer.TransferRemitInfoResp;
import com.hhly.lyygame.data.net.protocol.transfer.TransferRemitResp;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ${HELY} on 17/4/24.
 * 邮箱：heli.lixiong@gmail.com
 */

public interface TransferApi {

    /**
     * 游戏币兑换乐盈券
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("gameGoldsToLyqTransfer/exchange")//gameGoldsToLyqTransfer/exchange
    Flowable<TransferExchangeResp> transExchange(@FieldMap(encoded = true)Map<String, String> params);


    /**
     * 游戏币划账
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("gameGoldsTransfer/remit")
    Flowable<TransferRemitResp> transRemit(@FieldMap(encoded = true)Map<String, String> params);


    /**
     * 获取划入划出,兑换的游戏列表
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("gameGoldsTransfer/getGameList")
    Flowable<TransferGameListResp> transGameList(@FieldMap(encoded = true)Map<String, String> params);


    /**
     * 查询用户在某游戏平台的游戏币余额
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("gameGoldsTransfer/getGameBalance")
    Flowable<TransferGameBalanceResp> transGameBalance(@FieldMap(encoded = true)Map<String, String> params);

    /**
     * 查询用户在某游戏平台的游戏币余额
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("gameGoldsTransfer/findSonPlatformBalance")
    Flowable<TransferFindSonPlatformBalanceResp> transFindSonPlatformBalance(@FieldMap(encoded = true)Map<String, String> params);

    /**
     * 获取用户乐盈币信息
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("gameGoldsTransfer/getLYBInfo")
    Flowable<TransferLYBInfoResp> transLYBInfo(@FieldMap(encoded = true)Map<String, String> params);


    /**
     * 获取用户乐盈币信息
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("gameGoldsTransfer/getRemitGameInfo")
    Flowable<TransferRemitInfoResp> transRemitGameInfo(@FieldMap(encoded = true)Map<String, String> params);


    /**
     * 根据id获取游戏乐盈券兑换参数表
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("gameGoldsToLyqTransfer/getExchangeGameInfo")
    Flowable<TransferExchangeGameInfoResp> transExchangeGameInfo(@FieldMap(encoded = true)Map<String, String> params);


}
