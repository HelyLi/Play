package com.hhly.lyygame.presentation.view.pay.bankpay;

import com.hhly.lyygame.data.net.protocol.pay.QuickPayApplyReq;
import com.hhly.lyygame.presentation.view.BasePresenter;
import com.hhly.lyygame.presentation.view.BaseView;

/**
 * description :
 * Created by Flynn
 * 2017/5/13
 */

public interface BankCardPayContract {

    interface Presenter extends BasePresenter {
        void getOrderInfo(QuickPayApplyReq model);
    }

    interface View extends BaseView<Presenter> {
        void getOrderInfoSuccess(QuickPayApplyReq model);

        void getOrderInfoFailure(String msg);
    }
}
