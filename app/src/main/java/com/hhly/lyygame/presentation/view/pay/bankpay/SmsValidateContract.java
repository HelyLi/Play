package com.hhly.lyygame.presentation.view.pay.bankpay;

import com.hhly.lyygame.data.net.protocol.pay.QuickPayConfirmReq;
import com.hhly.lyygame.presentation.view.BasePresenter;
import com.hhly.lyygame.presentation.view.BaseView;

/**
 * description :
 * Created by Flynn
 * 2017/5/13
 */

public interface SmsValidateContract {

    interface Presenter extends BasePresenter {

        void getQuickPaySendMsg(String tn);

        void quickPayConfirm(QuickPayConfirmReq req);
    }

    interface View extends BaseView<Presenter> {
        void getQuickPaySendMsgSuccess();

        void getQuickPaySendMsgFailure(String msg);

        void quickPayConfirmSuccess();

        void quickPayConfirmFailure(String msg);
    }
}
