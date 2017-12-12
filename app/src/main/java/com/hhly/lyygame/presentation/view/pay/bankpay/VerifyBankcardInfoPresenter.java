package com.hhly.lyygame.presentation.view.pay.bankpay;

import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.PayApi;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.client.BaseSubscriber;
import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.pay.QuickPayApplyReq;
import com.hhly.lyygame.data.net.protocol.pay.QuickPayApplyResp;
import com.orhanobut.logger.Logger;

/**
 * description :
 * Created by Flynn
 * 2017/5/13
 */

public class VerifyBankcardInfoPresenter implements VerifyBankCardInfoContract.Presenter {

    private final PayApi mPayApi;
    private VerifyBankCardInfoContract.View mView;

    public VerifyBankcardInfoPresenter(VerifyBankCardInfoContract.View view) {
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
    public void quickPayApply(final QuickPayApplyReq req) {
        mPayApi.quickPayApply(req.params())
                .compose(RxUtil.<QuickPayApplyResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<QuickPayApplyResp>bindToLife())
                .subscribe(new BaseSubscriber<QuickPayApplyResp>() {
                    @Override
                    protected void hideDialog() {

                    }

                    @Override
                    protected void showDialog() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        Logger.e(e.getMessage());
                        mView.quickPayApplyFailure("");
                    }

                    @Override
                    public void onNext(QuickPayApplyResp resp) {
                        if (resp.isOk() && resp.getData() != null && "0000".equals(resp.getData().getRespCode())) {
                            mView.quickPayApplySuccess(req.getPhoneNo(), resp.getData());
                        } else if (resp.isOk() && resp.getData() != null && "0001".equals(resp.getData().getRespCode())) {
                            mView.quickPayApplyFailure("0001");
                        } else {
                            mView.quickPayApplyFailure(resp.getData() != null ? resp.getData().getRespMsg() : "");
                        }
                    }
                });
    }
}
