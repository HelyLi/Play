package com.hhly.lyygame.presentation.view.pay.bankpay;

import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.PayApi;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.client.BaseSubscriber;
import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.pay.PayBankInfoReq;
import com.hhly.lyygame.data.net.protocol.pay.PayBankInfoResp;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/5/13
 */

public class SupportBankPresenter implements SupportBankContract.Presenter {

    private final PayApi mPayApi;
    private SupportBankContract.View mView;

    public SupportBankPresenter(SupportBankContract.View view) {
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
    public void queryPayBankInfo() {

        PayBankInfoReq req = new PayBankInfoReq();
        mPayApi.queryPayBankInfo(req.params())
                .compose(RxUtil.<List<PayBankInfoResp>>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<List<PayBankInfoResp>>bindToLife())
                .subscribe(new BaseSubscriber<List<PayBankInfoResp>>() {
                    @Override
                    protected void hideDialog() {

                    }

                    @Override
                    protected void showDialog() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        Logger.e(e.getMessage());
                        mView.queryPayBankInfoFailure("");
                    }

                    @Override
                    public void onNext(List<PayBankInfoResp> respList) {
                        mView.queryPayBankInfoSuccess(respList);
                    }
                });
    }
}
