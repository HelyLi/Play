package com.hhly.lyygame.presentation.view.order;

import android.text.TextUtils;

import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.App;
import com.hhly.lyygame.data.db.manager.AccountManager;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.PayApi;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.client.BaseSubscriber;
import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.pay.OrderInfoForShopReq;
import com.hhly.lyygame.data.net.protocol.pay.OrderInfoForShopResp;
import com.hhly.lyygame.data.net.protocol.user.PayReq;
import com.hhly.lyygame.data.net.protocol.user.PayResp;
import com.hhly.lyygame.data.net.protocol.user.State;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dell on 2017/6/1.
 */

public class PayOrderPresenterImpl implements OrderContract.PayOrderPresenter {
    private final OrderContract.PayOrderView mView;
    private final PayApi mPayApi;

    public PayOrderPresenterImpl(OrderContract.PayOrderView view){
        mView = view;
        mPayApi = RetrofitManager.getInstance(ApiType.PAY_API).getPayApi();
        mView.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void getOrderInfoForShop() {
        OrderInfoForShopReq req = new OrderInfoForShopReq();

        mPayApi.getOrderInfoForShop(req.params())
                .compose(RxUtil.<OrderInfoForShopResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<OrderInfoForShopResp>bindToLife())
                .subscribe(new BaseSubscriber<OrderInfoForShopResp>() {

                    @Override
                    public void onNext(OrderInfoForShopResp resp) {
                        if (resp != null && resp.isOk() && resp.getData() != null && !TextUtils.isEmpty(resp.getData().getOrderNo())) {
                            mView.getOrderInfoSuccess(resp.getData().getOrderNo());
                        } else {
                            mView.getOrderInfoFailure(resp.getMsg());
                        }
                    }

                    @Override
                    protected void hideDialog() {

                    }

                    @Override
                    protected void showDialog() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        Logger.e(e.message);
                        mView.getOrderInfoFailure(e.message);
                    }
                });
    }

    /**
     * @param money    金额
     * @param gameId     游戏id   测试环境传11
     * @param gameName 游戏名称  玩一下
     * @param type     支付方式   0银联,1支付宝,2 微信
     */
    @Override
    public void getPayOrder(double money, long gameId, String gameName, final int type, final int goodsId, int shopType, int addressId, String phone, String payToAccount) {
        final PayReq req = new PayReq();
//        req.setMoney("1");
        Logger.e("shopType: " + shopType);
        if (shopType == State.GoodsType.MATTER) {
            req.setAddressId(String.valueOf(addressId));
        } else if (shopType == State.GoodsType.CARD) {
            req.setPhone(phone);
        } else {
            req.setPayToAccount(payToAccount);
        }
        req.setMoney(String.valueOf(money));
        req.setType(String.valueOf(type));
        req.setGame(String.valueOf(gameId));
        req.setGameName(gameName);
        req.setTo("2");
        req.setAccount(AccountManager.getInstance().getUserId());
        req.setService("2");
        req.setProductId(String.valueOf(goodsId));

        mPayApi.getPayOrder(req.params())
                .compose(RxUtil.<PayResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                //.compose(RetrofitManager.<PayResp>composeBackpressureError())
                .compose(mView.<PayResp>bindToLife())
                .subscribe(new BaseSubscriber<PayResp>() {
                    @Override
                    protected void hideDialog() {

                    }

                    @Override
                    protected void showDialog() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        mView.getOrderFailure("");
                    }

                    @Override
                    public void onNext(PayResp payResp) {
                        App.type = 1;
                        String data = payResp.getData();
                        if (!TextUtils.isEmpty(data)) {
                            try {
                                JSONObject jsonObject = new JSONObject(data);
                                int result = jsonObject.getInt("result");
                                if (result != 0) {
                                    String msg = jsonObject.getString("msg");
                                    mView.getOrderFailure(msg);
                                } else {
                                    switch (type) {
                                        case 1://支付宝
                                            getAliPay(payResp);
                                            break;
                                        case 2://微信
                                            getWechatPay(payResp);
                                            break;
                                    }
                                }
                            } catch (JSONException e) {
                                Logger.e("JSONException");
                                // e.printStackTrace();
                                switch (type) {
                                    case 1://支付宝
                                        getAliPay(payResp);
                                        break;
                                    case 2://微信
                                        getWechatPay(payResp);
                                        break;
                                }
                            }
                        } else {
                            mView.getOrderFailure("");
                        }
                    }
                });
    }

    //"{sign=8C13440F6209E0FDB925C88D9A22C004, timestamp=1493795631, noncestr=a78a6f0e208b041033fe4f089270b564, partnerid=1450159802, prepayid=wx20170503151429e4f798c2bf0480486389, package="Sign=WXPay", appid=wxdd4a10d4e8c7cac0}"
    private void getWechatPay(PayResp payResp) {
        if (payResp != null && payResp.isOk()) {
            App.businessNo = payResp.getBusinessNo();
            Logger.d(payResp);
            String data = payResp.getData();
            Logger.d("data.1=" + data);
            if (!TextUtils.isEmpty(data)) {
                data = data.substring(1, data.length() - 1);
            }
            Logger.d("data.2=" + data);
            Logger.d("BusinessNo" + payResp.getBusinessNo());
            com.tencent.mm.sdk.modelpay.PayReq req = parseData(data);
            Logger.d("payreq=" + req);
            mView.getOrderSuccess(req);
            App.bundessNo = payResp.getOrder_no();
        } else {
            mView.getOrderFailure(payResp == null ? "" : payResp.getMsg());
        }
    }

    private com.tencent.mm.sdk.modelpay.PayReq parseData(String data) {
        try {
//{sign=A58D4A6AEBD8114D1EB7C60E379800E2,
// timestamp=1493796382,
// noncestr=467d9be649ba94287076da35595700bd,
// partnerid=1450159802,
// prepayid=wx20170503152701eb5061e7530582436478,
// package="Sign=WXPay",
// appid=wxdd4a10d4e8c7cac0}
            Logger.d("data=" + data);

            JSONObject obj = new JSONObject(data);
            com.tencent.mm.sdk.modelpay.PayReq req = new com.tencent.mm.sdk.modelpay.PayReq();
            req.appId = obj.optString("appid");
            req.packageValue = obj.optString("package");
            req.prepayId = obj.optString("prepayid");
            req.partnerId = obj.optString("partnerid");
            req.nonceStr = obj.optString("noncestr");
            req.timeStamp = obj.optString("timestamp");
            req.sign = obj.optString("sign");
            return req;
        } catch (Exception e) {
            return null;
        }
    }

    private void getAliPay(PayResp payResp) {
        Logger.d("alipay=" + payResp);
        if (payResp != null && payResp.isOk()) {
            App.businessNo = payResp.getBusinessNo();
            String orderInfo = payResp.getData();
            if (!TextUtils.isEmpty(orderInfo)) {
                mView.getAliOrderSuccess(orderInfo);
                App.bundessNo = payResp.getOrder_no();
            } else {
                mView.getOrderFailure(payResp.getMsg());
            }
        } else {
            mView.getOrderFailure(payResp == null ? "" : payResp.getMsg());
        }
    }
}
