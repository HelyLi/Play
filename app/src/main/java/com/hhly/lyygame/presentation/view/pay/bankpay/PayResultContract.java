package com.hhly.lyygame.presentation.view.pay.bankpay;

import com.hhly.lyygame.data.net.protocol.pay.QuickPayConfirmReq;
import com.hhly.lyygame.presentation.view.BasePresenter;
import com.hhly.lyygame.presentation.view.BaseView;

/**
 * description :
 * Created by Flynn
 * 2017/5/18
 */

public interface PayResultContract {

    interface Presenter extends BasePresenter {
        void quickPayConfirm(QuickPayConfirmReq req);
    }

    interface View extends BaseView<Presenter> {
        void quickPayConfirmSuccess();

        void quickPayConfirmFailure(String msg, String respCode);
    }

}
