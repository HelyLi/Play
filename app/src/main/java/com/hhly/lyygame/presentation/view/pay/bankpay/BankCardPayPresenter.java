package com.hhly.lyygame.presentation.view.pay.bankpay;

import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.PayApi;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.client.BaseSubscriber;
import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.pay.OrderInfoReq;
import com.hhly.lyygame.data.net.protocol.pay.OrderInfoResp;
import com.hhly.lyygame.data.net.protocol.pay.QuickPayApplyReq;
import com.hhly.lyygame.presentation.view.pay.bankpay.BankCardPayContract;
import com.orhanobut.logger.Logger;

/**
 * description :
 * Created by Flynn
 * 2017/5/13
 */

public class BankCardPayPresenter implements BankCardPayContract.Presenter {

    private final PayApi mPayApi;
    private BankCardPayContract.View mView;

    public BankCardPayPresenter(BankCardPayContract.View view) {
        mView = view;
        mView.setPresenter(this);
        mPayApi = RetrofitManager.getInstance(ApiType.PAY_API).getPayApi();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void getOrderInfo(final QuickPayApplyReq model) {
        OrderInfoReq req = new OrderInfoReq();
        String bankName = model.getBankName().substring(0, model.getBankName().length() - 3);
        //        if (!"中国银行".equalsIgnoreCase(bankName)) {
        //            bankName = bankName.replace("中国", "");
        //        }
        req.setBankName(bankName);
        req.setBankCardNo(model.getAccNo());
        mPayApi.getOrderInfo(req.params())
                .compose(RxUtil.<OrderInfoResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<OrderInfoResp>bindToLife())
                .subscribe(new BaseSubscriber<OrderInfoResp>() {
                    @Override
                    protected void hideDialog() {

                    }

                    @Override
                    protected void showDialog() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        Logger.e(e.getMessage());
                        mView.getOrderInfoFailure("");
                    }

                    @Override
                    public void onNext(OrderInfoResp resp) {
                        if (resp.isOk() && resp.getData() != null) {
                            model.setTransactionTime(resp.getData().getApplyTime());
                            model.setOutTradeNo(resp.getData().getOutTradeNo());
                            mView.getOrderInfoSuccess(model);
                        } else {
                            mView.getOrderInfoFailure(resp.getMsg());
                        }
                    }
                });
    }
}
