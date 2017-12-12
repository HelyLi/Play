package com.hhly.lyygame.presentation.view.pay;

import android.text.TextUtils;

import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.App;
import com.hhly.lyygame.data.db.manager.AccountManager;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.PayApi;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.client.BaseSubscriber;
import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.user.PayReq;
import com.hhly.lyygame.data.net.protocol.user.PayResp;
import com.orhanobut.logger.Logger;

import org.json.JSONObject;

/**
 * Created by ${HELY} on 17/1/18.
 * 邮箱：heli.lixiong@gmail.com
 */

public class PayPresenter implements PayContact.Presenter {

    private final PayApi mPayApi;
    PayContact.View mView;

    public PayPresenter(PayContact.View view) {
        this.mView = view;
        this.mView.setPresenter(this);
        mPayApi = RetrofitManager.getInstance(ApiType.PAY_API).getPayApi();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    /**
     *
     * @param payInfo type=1&money=1&gameId=11111&gameName=%E7%8E%A9%E4%B8%80%E4%B8%8B&account=hhly91599&to=1&payPlatformId=xxx
     */
    public void getPayOrder(String payInfo){
        final PayReq req = new PayReq();

        String[] infos = payInfo.split("&");
        for (String info : infos) {
            if (info.startsWith("type")) {
                req.setType(info.split("=").length == 2 ? info.split("=")[1] : "");
            } else if (info.startsWith("money")) {
                req.setMoney(info.split("=").length == 2 ? info.split("=")[1] : "");
            } else if (info.startsWith("gameId")) {
                req.setGame(info.split("=").length == 2 ? info.split("=")[1] : "");
            } else if (info.startsWith("gameName")) {
                req.setGameName(info.split("=").length == 2 ? info.split("=")[1] : "");
            } else if (info.startsWith("account")) {
                req.setAccount(info.split("=").length == 2 ? info.split("=")[1] : "");
            } else if (info.startsWith("to")) {
                req.setTo(info.split("=").length == 2 ? info.split("=")[1] : "");
            } else if (info.startsWith("appId")) {
                req.setAppId(info.split("=").length == 2 ? info.split("=")[1] : "");
            } else if (info.startsWith("payPlatformId")){
                req.setPayPlatformId(info.split("=").length == 2 ? info.split("=")[1] : "");
            }
        }

        req.setService("2");

        Logger.d("pay.req=" + req);

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
                        App.type = 0;
                        switch (req.getType()) {
                            case "1"://支付宝
                                getAliPay(payResp);
                                break;
                            case "2"://微信
                                getWechatPay(payResp);
                                break;
                        }
                    }
                });
    }

    /**
     * @param money    金额
     * @param gameId     游戏id   测试环境传11
     * @param gameName 游戏名称  玩一下
     * @param to       充值到（0游戏1乐盈币）
     * @param type     支付方式   0银联,1支付宝,2 微信
     */
    @Override
    public void getPayOrder(double money, long gameId, String gameName, int to, final int type) {
        final PayReq req = new PayReq();
//        req.setMoney("0.01");
        req.setMoney(String.valueOf(money));
        req.setType(String.valueOf(type));
        req.setGame(String.valueOf(gameId));
        req.setGameName(gameName);
        req.setTo(String.valueOf(to));
        req.setAccount(AccountManager.getInstance().getUserId());
        req.setService("2");

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
                        App.type = 0;
                        switch (type) {
                            case 1://支付宝
                                getAliPay(payResp);
                                break;
                            case 2://微信
                                getWechatPay(payResp);
                                break;
                        }
                    }
                });
    }

    //"{sign=8C13440F6209E0FDB925C88D9A22C004, timestamp=1493795631, noncestr=a78a6f0e208b041033fe4f089270b564, partnerid=1450159802, prepayid=wx20170503151429e4f798c2bf0480486389, package="Sign=WXPay", appid=wxdd4a10d4e8c7cac0}"
    private void getWechatPay(PayResp payResp) {
        if (payResp != null && payResp.isOk()) {
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
