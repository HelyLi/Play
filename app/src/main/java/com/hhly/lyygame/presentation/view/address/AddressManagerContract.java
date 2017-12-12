package com.hhly.lyygame.presentation.view.address;

import com.hhly.lyygame.data.net.protocol.user.UserAddressResp;
import com.hhly.lyygame.presentation.view.BaseLoadingView;
import com.hhly.lyygame.presentation.view.BasePresenter;
import com.hhly.lyygame.presentation.view.BaseView;

import java.util.List;

/**
 * Created by ${HELY} on 17/2/10.
 * 邮箱：heli.lixiong@gmail.com
 */

public interface AddressManagerContract {

    interface Presenter extends BasePresenter {
        void getUserAddress(String userId);
        void modifyDefaultAddress(String userId, int addressId, int useDefault);//0 是:1 否
        void delUserAddress(int addressId);
    }

    interface View extends BaseView<Presenter>, BaseLoadingView {
        void showAddress(List<UserAddressResp.AddressBean> addressBeanList);
        void showAddressFailure();
        void modifyAddressSuccess();
        void modifyAddressFailure();
        void delAddressSuccess(int addressId);
        void delAddressFailure();
    }

}
