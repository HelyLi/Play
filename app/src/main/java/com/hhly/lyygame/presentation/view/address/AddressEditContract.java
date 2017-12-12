package com.hhly.lyygame.presentation.view.address;

import com.hhly.lyygame.data.net.protocol.user.AddAddressReq;
import com.hhly.lyygame.presentation.view.BaseLoadingView;
import com.hhly.lyygame.presentation.view.BasePresenter;
import com.hhly.lyygame.presentation.view.BaseView;

/**
 * Created by ${HELY} on 17/2/10.
 * 邮箱：heli.lixiong@gmail.com
 */

public interface AddressEditContract {

    interface Presenter extends BasePresenter {
        void addUserAddress(AddAddressReq req);
    }

    interface View extends BaseView<Presenter>, BaseLoadingView {
        void addAddressSuccess(int addressId);
        void addAddressFailure();
    }
}
