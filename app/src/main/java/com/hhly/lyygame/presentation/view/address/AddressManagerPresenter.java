package com.hhly.lyygame.presentation.view.address;

import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.GoodsApi;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.client.BaseSubscriber;
import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.user.AddAddressReq;
import com.hhly.lyygame.data.net.protocol.user.AddAddressResp;
import com.hhly.lyygame.data.net.protocol.user.DelAddressReq;
import com.hhly.lyygame.data.net.protocol.user.DelAddressResp;
import com.hhly.lyygame.data.net.protocol.user.UserAddressReq;
import com.hhly.lyygame.data.net.protocol.user.UserAddressResp;

/**
 * Created by ${HELY} on 17/2/10.
 * 邮箱：heli.lixiong@gmail.com
 */

public class AddressManagerPresenter implements AddressManagerContract.Presenter {

    private final AddressManagerContract.View mView;
    private final GoodsApi mGoodsApi;

    public AddressManagerPresenter(AddressManagerContract.View view) {
        mView = view;
        mGoodsApi = RetrofitManager.getInstance(ApiType.GOODS_API).getGoodsApi();
        mView.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    /**
     * 获取用户列表
     *
     * @param userId
     */
    @Override
    public void getUserAddress(String userId) {
        UserAddressReq req = new UserAddressReq();
        req.setCountry(0);
        req.setUserId(userId);

        mGoodsApi.getAddress(req.params())
                .compose(RxUtil.<UserAddressResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<UserAddressResp>bindToLife())
                .compose(RetrofitManager.<UserAddressResp>composeBackpressureOther())
                .compose(RetrofitManager.<UserAddressResp>composeBackpressureError())
                .subscribe(new BaseSubscriber<UserAddressResp>() {
                    @Override
                    protected void hideDialog() {

                    }

                    @Override
                    protected void showDialog() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        mView.showAddressFailure();
                    }

                    @Override
                    public void onNext(UserAddressResp userAddressResp) {
                        if (userAddressResp != null && userAddressResp.isOk()) {
                            mView.showAddress(userAddressResp.getList());
                        } else {
                            mView.showAddressFailure();
                        }
                    }
                });
    }

    /**
     * 修改地址
     *
     * @param userId
     * @param addressId
     * @param useDefault
     */
    @Override
    public void modifyDefaultAddress(String userId, int addressId, int useDefault) {
        AddAddressReq req = new AddAddressReq();
        req.setUserId(userId);
        req.setAddressId(addressId);
        req.setUseDefault(useDefault);
        req.setCountry(0);

        mGoodsApi.addAddress(req.params())
                .compose(RxUtil.<AddAddressResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<AddAddressResp>bindToLife())
                .compose(RetrofitManager.<AddAddressResp>composeBackpressureOther())
                .compose(RetrofitManager.<AddAddressResp>composeBackpressureError())
                .subscribe(new BaseSubscriber<AddAddressResp>() {
                    @Override
                    protected void hideDialog() {
                        mView.dismissLoading();
                    }

                    @Override
                    protected void showDialog() {
                        mView.showLoading();
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        mView.modifyAddressFailure();
                    }

                    @Override
                    public void onNext(AddAddressResp addAddressResp) {
                        mView.dismissLoading();
                        if (addAddressResp != null && addAddressResp.isOk()) {
                            mView.modifyAddressSuccess();
                        } else {
                            mView.modifyAddressFailure();
                        }
                    }
                });
    }

    /**
     * 删除地址
     *
     * @param addressId
     */
    @Override
    public void delUserAddress(final int addressId) {
        DelAddressReq req = new DelAddressReq();
        req.setAddressId(addressId);

        mGoodsApi.delAddress(req.params())
                .compose(RxUtil.<DelAddressResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<DelAddressResp>bindToLife())
                .compose(RetrofitManager.<DelAddressResp>composeBackpressureOther())
                .compose(RetrofitManager.<DelAddressResp>composeBackpressureError())
                .subscribe(new BaseSubscriber<DelAddressResp>() {
                    @Override
                    protected void hideDialog() {

                    }

                    @Override
                    protected void showDialog() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        mView.delAddressFailure();
                    }

                    @Override
                    public void onNext(DelAddressResp delAddressResp) {
                        if (delAddressResp != null && delAddressResp.isOk()) {
                            mView.delAddressSuccess(addressId);
                        } else {
                            mView.delAddressFailure();
                        }
                    }
                });
    }

}
