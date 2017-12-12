package com.hhly.lyygame.presentation.payutils;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;

import com.alipay.sdk.app.PayTask;
import com.github.xmxu.cf.Config;
import com.hhly.lyygame.App;
import com.hhly.lyygame.presentation.view.ToastUtil;
import com.hhly.lyygame.presentation.view.order.PayOrderDetailsActivity;
import com.hhly.lyygame.presentation.view.paydetails.PayDetailsActivity;
import com.orhanobut.logger.Logger;
import com.tencent.mm.sdk.constants.Build;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.Map;

/**
 * 充值api
 * Created by Simon on 16/9/20.
 */
public final class OrderApi {

    private static final String TAG = "OrderApi";

    private IWXAPI api;

    private WeakReference<Activity> mActivity;

//    private Integer mPayType;

//    public static final int aliPay = 1;
//    public static final int WechatPay = 2;

    private OrderApi() {

    }

    public static OrderApi obtain() {
        return new OrderApi();
    }


    /**
     * 微信支付
     * /**
     * IWXAPI api;
     * PayReq request = new PayReq();
     * request.appId = "wxd930ea5d5a258f4f";
     * request.partnerId = "1900000109";
     * request.prepayId= "1101000000140415649af9fc314aa427",;
     * request.packageValue = "Sign=WXPay";
     * request.nonceStr= "1101000000140429eb40476f8896f4c9";
     * request.timeStamp= "1398746574";
     * request.sign= "7FFECB600D7157C5AA49810D2D8F28BC2811827B";
     * api.sendReq(req);
     *
     * @param activity
     * @param req         PayReq 微信请求参数
     * @param payCallback
     */
    public void startWechatPay(Activity activity, PayReq req, OnPayCallback payCallback) {
//        this.mPayType = OrderBuilder.PayType.WECHAT;
        this.mActivity = new WeakReference<>(activity);
        mPayCallback = payCallback;
        api = WXAPIFactory.createWXAPI(activity.getApplicationContext(), null);
        if (!api.isWXAppInstalled()) {
            if (mPayCallback != null) {
                mPayCallback.onPayError("未安装微信客户端");
                ToastUtil.showTip(activity, "未安装微信客户端");
            }
            resetCallback();
            return;
        }
        if (mPayCallback != null) {
            mPayCallback.onPayStart();
        }
        api.registerApp(Config.get().getWechatAppId());
        boolean isPaySupported = api.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
        boolean isSend = api.sendReq(req);
        Logger.d("isPaySupported = " + isPaySupported + "   isSend = " + isSend);
    }

    /**
     * 启动支付宝
     */
    public void startAlipay(Activity activity, final String orderInfo) {
//        this.mPayType = OrderBuilder.PayType.ALIPAY;
        this.mActivity = new WeakReference<>(activity);
        try {
            Runnable payRunnable = new Runnable() {

                @Override
                public void run() {

//                    EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
                    // 构造PayTask 对象
                    PayTask alipay = new PayTask(mActivity.get());
                    // 调用支付接口，获取支付结果
                    Map<String, String> result = alipay.payV2(orderInfo, true);
                    Message msg = new Message();
                    msg.what = MSG_ALIPAY_RESP;
                    msg.obj = result;
                    mHandler.sendMessage(msg);
                }
            };

            // 必须异步调用
            Thread payThread = new Thread(payRunnable);
            payThread.start();

        } catch (Exception ex) {
            ex.printStackTrace();
            if (mPayCallback != null) {
                mPayCallback.onPayError("error");
            }
            resetCallback();
        }
    }

    private final Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_ORDER_RESP:
                    break;
                case MSG_ALIPAY_RESP:
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    String result = payResult.getResult();
                    String resultStatus = payResult.getResultStatus();
                    Logger.d(result);
                    if (TextUtils.equals(resultStatus, "9000")) {
                        if (mActivity.get() != null) {
                            try {
                                int type = App.type;
                                Logger.e("type: " + type);
                                JSONObject object = new JSONObject(result);
                                JSONObject obj = object.optJSONObject("alipay_trade_app_pay_response");
                                //                            mActivity.get().startActivity(PayDetailsActivity.getPayDetailsIntent(mActivity.get()));

                                if (type == 1) {
                                    mActivity.get().startActivity(PayOrderDetailsActivity.getPayOrderDetailsIntent(mActivity.get(), "2", ""));
                                } else {
                                    mActivity.get().startActivity(PayDetailsActivity.getPayDetailsIntent(
                                            mActivity.get(),
//                                        obj.optString("total_amount"),
//                                        obj.optString("timestamp"),
                                            "1",
                                            obj.optString("out_trade_no")
                                    ));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        //支付成功
                        if (mPayCallback != null) {
                            mPayCallback.onPayCompletely();
                        }
                    } else {
                        //支付失败
                        if (mPayCallback != null) {
                            mPayCallback.onPayError(result);
                        }
                        int type = App.type;
                        if (type == 1) {
                            mActivity.get().startActivity(PayOrderDetailsActivity.getPayOrderDetailsIntent(mActivity.get(), "2", ""));
                            // mActivity.get().startActivity(PayOrderFailActivity.getPayOrderFailDetailsIntent(mActivity.get(), "2", ""));
                        }
                    }
                    resetCallback();
                    break;
            }
        }
    };

    public static final int MSG_ORDER_RESP = 100;
    public static final int MSG_ALIPAY_RESP = 101;

    private static OnPayCallback mPayCallback;

    public interface OnPayCallback {
        /**
         * 支付开始
         */
        void onPayStart();

        /**
         * 支付错误
         *
         * @param msg
         */
        void onPayError(String msg);

        /**
         * 支付完成
         */
        void onPayCompletely();
    }

    public static void onWxPayResult(int errorCode) {
        if (mPayCallback != null) {
            if (errorCode == 0) {
                //成功
                mPayCallback.onPayCompletely();
            } else {
                mPayCallback.onPayError("");
            }
            resetCallback();
        }
    }

    private static void resetCallback() {
        mPayCallback = null;
    }
}
