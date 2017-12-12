package com.hhly.lyygame.presentation.view.pay.bankpay;

import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.PayApi;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.client.BaseSubscriber;
import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.pay.QuickPayConfirmReq;
import com.hhly.lyygame.data.net.protocol.pay.QuickPayConfirmResp;
import com.hhly.lyygame.data.net.protocol.pay.QuickPaySendMsgReq;
import com.hhly.lyygame.data.net.protocol.pay.QuickPaySendMsgResp;
import com.orhanobut.logger.Logger;

/**
 * description :
 * Created by Flynn
 * 2017/5/13
 */

public class SmsValidatePresenter implements SmsValidateContract.Presenter {

    private final PayApi mPayApi;
    private SmsValidateContract.View mView;

    public SmsValidatePresenter(SmsValidateContract.View view) {
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
    public void getQuickPaySendMsg(String tn) {
        QuickPaySendMsgReq req = new QuickPaySendMsgReq();
        req.setTn(tn);
        mPayApi.getQuickPaySendMsg(req.params())
                .compose(RxUtil.<QuickPaySendMsgResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<QuickPaySendMsgResp>bindToLife())
                .subscribe(new BaseSubscriber<QuickPaySendMsgResp>() {
                    @Override
                    protected void hideDialog() {

                    }

                    @Override
                    protected void showDialog() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {

                    }

                    @Override
                    public void onNext(QuickPaySendMsgResp quickPaySendMsgResp) {

                    }
                });
    }

    @Override
    public void quickPayConfirm(QuickPayConfirmReq req) {
        mPayApi.quickPayConfirm(req.params())
                .compose(RxUtil.<QuickPayConfirmResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<QuickPayConfirmResp>bindToLife())
                .subscribe(new BaseSubscriber<QuickPayConfirmResp>() {
                    @Override
                    protected void hideDialog() {

                    }

                    @Override
                    protected void showDialog() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        Logger.e(e.getMessage());
                        mView.quickPayConfirmFailure("");
                    }

                    @Override
                    public void onNext(QuickPayConfirmResp resp) {
                        if (resp != null && "0000".equalsIgnoreCase(resp.getRespCode())) {
                            mView.quickPayConfirmSuccess();
                        } else {
                            mView.quickPayConfirmFailure(resp.getRespMsg());
                        }
                    }
                });
    }
}
