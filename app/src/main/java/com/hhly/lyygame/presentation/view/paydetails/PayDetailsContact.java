package com.hhly.lyygame.presentation.view.paydetails;

import com.hhly.lyygame.data.net.protocol.user.OrderQueryResp;
import com.hhly.lyygame.presentation.view.BaseLoadingView;
import com.hhly.lyygame.presentation.view.BasePresenter;
import com.hhly.lyygame.presentation.view.BaseView;

/**
 * Created by ${HELY} on 17/1/18.
 * 邮箱：heli.lixiong@gmail.com
 */

public interface PayDetailsContact {

    interface Persenter extends BasePresenter {
        void getOrderDetail(String pre);

        void getWeChatOrderDetail(String pre);
    }

    interface View extends BaseView<PayDetailsContact.Persenter>, BaseLoadingView {
        void aliQuerySuccess(OrderQueryResp resp);

        void weChatQuerySuccess(OrderQueryResp resp);
    }

}
