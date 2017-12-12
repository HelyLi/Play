package com.hhly.lyygame.presentation.view.pay.bankpay;

import com.hhly.lyygame.data.net.protocol.pay.PayBankInfoResp;
import com.hhly.lyygame.presentation.view.BasePresenter;
import com.hhly.lyygame.presentation.view.BaseView;

import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/5/13
 */

public interface SupportBankContract {

    interface Presenter extends BasePresenter {
        void queryPayBankInfo();
    }

    interface View extends BaseView<Presenter> {
        void queryPayBankInfoSuccess(List<PayBankInfoResp> list);

        void queryPayBankInfoFailure(String msg);
    }

}
