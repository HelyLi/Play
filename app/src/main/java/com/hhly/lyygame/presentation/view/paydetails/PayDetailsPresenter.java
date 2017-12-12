package com.hhly.lyygame.presentation.view.paydetails;

import android.util.Log;

import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.PayApi;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.client.BaseSubscriber;
import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.user.OrderQueryReq;
import com.hhly.lyygame.data.net.protocol.user.OrderQueryResp;

/**
 * Created by ${HELY} on 17/1/18.
 * 邮箱：heli.lixiong@gmail.com
 */

public class PayDetailsPresenter implements PayDetailsContact.Persenter {

    private final PayApi mPayApi;
    PayDetailsContact.View mView;


    public PayDetailsPresenter(PayDetailsContact.View view) {
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

    @Override
    public void getOrderDetail(String pre) {
        OrderQueryReq req = new OrderQueryReq();
        req.setBussinessNo(pre);
        mPayApi.getPayDetailOrder(req.params())
                .compose(RxUtil.<OrderQueryResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<OrderQueryResp>bindToLife())
                .compose(RetrofitManager.<OrderQueryResp>composeBackpressureOther())
                .compose(RetrofitManager.<OrderQueryResp>composeBackpressureError())
                .subscribe(new BaseSubscriber<OrderQueryResp>() {

                    @Override
                    protected void hideDialog() {
                        mView.dismissLoading();
                    }

                    @Override
                    protected void showDialog() {
                        mView.showLoading();
                    }

                    @Override
                    public void onNext(OrderQueryResp resp) {
                        mView.dismissLoading();
                        mView.aliQuerySuccess(resp);
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        Log.d("TAG", "getPayDetail onError");
                    }
                });
    }

    @Override
    public void getWeChatOrderDetail(String pre) {
        OrderQueryReq req = new OrderQueryReq();
        req.setBussinessNo(pre);
        mPayApi.getPayDetailOrder(req.params())
                .compose(RxUtil.<OrderQueryResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<OrderQueryResp>bindToLife())
                //.compose(RetrofitManager.<OrderQueryResp>composeBackpressureError())
                .subscribe(new BaseSubscriber<OrderQueryResp>() {
                    @Override
                    protected void hideDialog() {
                        mView.dismissLoading();
                    }

                    @Override
                    protected void showDialog() {
                        mView.showLoading();
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {

                    }

                    @Override
                    public void onNext(OrderQueryResp resp) {
                        mView.dismissLoading();
                        mView.weChatQuerySuccess(resp);
                    }
                });

    }
}
