package com.hhly.lyygame.presentation.view.pay;

import com.hhly.lyygame.presentation.view.BaseLoadingView;
import com.hhly.lyygame.presentation.view.BasePresenter;
import com.hhly.lyygame.presentation.view.BaseView;
import com.tencent.mm.sdk.modelpay.PayReq;

/**
 * Created by ${HELY} on 17/1/18.
 * 邮箱：heli.lixiong@gmail.com
 */

public interface PayContact {

    interface Presenter extends BasePresenter{
        void getPayOrder(double money, long game,String gameName,int to,int type);
        void getPayOrder(String payInfo);
    }

    interface View extends BaseView<Presenter>, BaseLoadingView {
        void getOrderSuccess(PayReq req);
        void getOrderFailure(String msg);
        void getAliOrderSuccess(String orderInfo);
    }

}
