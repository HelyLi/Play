package com.hhly.lyygame.data.net;

import com.hhly.lyygame.data.net.protocol.pay.OrderInfoForShopResp;
import com.hhly.lyygame.data.net.protocol.pay.OrderInfoResp;
import com.hhly.lyygame.data.net.protocol.pay.PayBankInfoResp;
import com.hhly.lyygame.data.net.protocol.pay.QuickPayApplyResp;
import com.hhly.lyygame.data.net.protocol.pay.QuickPayConfirmResp;
import com.hhly.lyygame.data.net.protocol.pay.QuickPaySendMsgResp;
import com.hhly.lyygame.data.net.protocol.user.OrderQueryResp;
import com.hhly.lyygame.data.net.protocol.user.PayResp;
import com.hhly.lyygame.data.net.protocol.user.QueryPayResp;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by dell on 2017/3/21.
 */

public interface PayApi {

    /**
     * 支付接口
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("pay/pay")
    Flowable<PayResp> getPayOrder(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 支付查询接口
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("pay/queryPayRecord")
    Flowable<OrderQueryResp> getPayDetailOrder(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 快捷支付-支持银行卡列表
     *
     * @param params PayBankInfoReq
     * @return List<PayBankInfoResp>
     */
    @FormUrlEncoded
    @POST("payBankInfo/queryPayBankInfo")
    Flowable<List<PayBankInfoResp>> queryPayBankInfo(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 快捷支付(2)发送手机短信验证码
     *
     * @param params QuickPaySendMsgReq
     * @return QuickPaySendMsgResp
     */
    @FormUrlEncoded
    @POST("pay/getQuickPaySendMsg")
    Flowable<QuickPaySendMsgResp> getQuickPaySendMsg(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 快捷支付(3)支付确认
     *
     * @param params QuickPayConfirmReq
     * @return QuickPayConfirmResp
     */
    @FormUrlEncoded
    @POST("pay/quickPayConfirm")
    Flowable<QuickPayConfirmResp> quickPayConfirm(@FieldMap(encoded = true) Map<String, String> params);


    /**
     * 快捷支付(1)申请 --即，验证银行卡信息
     *
     * @param params QuickPayApplyReq
     * @return QuickPayApplyResp
     */
    @FormUrlEncoded
    @POST("pay/quickPayApply")
    Flowable<QuickPayApplyResp> quickPayApply(@FieldMap(encoded = true) Map<String, String> params);


    /**
     * 快捷支付(1)申请 --即，验证银行卡信息
     *
     * @param params OrderInfoReq
     * @return OrderInfoResp
     */
    @FormUrlEncoded
    @POST("pay/getOrderInfo")
    Flowable<OrderInfoResp> getOrderInfo(@FieldMap(encoded = true) Map<String, String> params);


    @FormUrlEncoded
    @POST("pay/getOrderInfoForShop")
    Flowable<OrderInfoForShopResp> getOrderInfoForShop(@FieldMap(encoded = true) Map<String, String> params);

    @FormUrlEncoded
    @POST("pay/queryPay")
    Flowable<QueryPayResp> queryPay(@FieldMap(encoded = true) Map<String, String> params);
}
