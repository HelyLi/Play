package com.hhly.lyygame.presentation.view.pay.bankpay;

import com.hhly.lyygame.data.net.protocol.pay.QuickPayApplyReq;
import com.hhly.lyygame.data.net.protocol.pay.QuickPayApplyResp;
import com.hhly.lyygame.presentation.view.BasePresenter;
import com.hhly.lyygame.presentation.view.BaseView;

/**
 * description :
 * Created by Flynn
 * 2017/5/13
 */

public interface VerifyBankCardInfoContract {

    interface Presenter extends BasePresenter {
        void quickPayApply(QuickPayApplyReq req);
    }

    interface View extends BaseView<Presenter> {
        void quickPayApplySuccess(String phone,QuickPayApplyResp.DataBean resp);

        void quickPayApplyFailure(String msg);
    }
}
