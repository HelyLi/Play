package com.hhly.lyygame.presentation.view.order;

import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.PayApi;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.client.BaseSubscriber;
import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.user.OrderQueryReq;
import com.hhly.lyygame.data.net.protocol.user.QueryPayResp;
import com.orhanobut.logger.Logger;

/**
 * Created by ${HELY} on 17/1/18.
 * 邮箱：heli.lixiong@gmail.com
 */

public class PayOrderDetailsPresenter implements OrderContract.PayOrderDetailsPresenter {

    private final PayApi mPayApi;
    OrderContract.PayOrderDetailsView mView;

    public PayOrderDetailsPresenter(OrderContract.PayOrderDetailsView view) {
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
        mPayApi.queryPay(req.params())
                .compose(RxUtil.<QueryPayResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<QueryPayResp>bindToLife())
                .compose(RetrofitManager.<QueryPayResp>composeBackpressureOther())
                .compose(RetrofitManager.<QueryPayResp>composeBackpressureError())
                .subscribe(new BaseSubscriber<QueryPayResp>() {
                    @Override
                    protected void hideDialog() {

                    }

                    @Override
                    protected void showDialog() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        Logger.e("PayOrderDetailsPresenter onError" + e.code);
                        Logger.e("PayOrderDetailsPresenter onError" + e.message);
                        Logger.e("PayOrderDetailsPresenter onError" + e.getMessage());
                        mView.payQueryFailure("");
                    }

                    @Override
                    public void onNext(QueryPayResp resp) {
                        mView.dismissLoading();
                        if (resp.getResult() == 0) {
                            if (null == resp.getPayBussinessInfo()){
                                mView.payQueryFailure(resp.getMsg());
                            } else {
                                if (resp.getPayBussinessInfo().getTradeStatus().equals("1")) {
                                    mView.payQuerySuccess(resp);
                                } else {
                                    mView.payQueryFailure(resp.getMsg());
                                }
                            }
                        } else {
                            mView.payQueryFailure(resp.getMsg());
                        }
                    }
                });
    }
}
