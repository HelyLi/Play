package com.hhly.lyygame.presentation.view.address;

import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.GoodsApi;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.client.BaseSubscriber;
import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.user.AddAddressReq;
import com.hhly.lyygame.data.net.protocol.user.AddAddressResp;

/**
 * Created by ${HELY} on 17/2/10.
 * 邮箱：heli.lixiong@gmail.com
 */

public class AddressEditPresenter implements AddressEditContract.Presenter {

    private final AddressEditContract.View mView;
    private final GoodsApi mGoodsApi;

    public AddressEditPresenter(AddressEditContract.View view) {
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

    @Override
    public void addUserAddress(AddAddressReq req) {
        mGoodsApi.addAddress(req.params())
                .compose(RxUtil.<AddAddressResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<AddAddressResp>bindToLife())
                .compose(RetrofitManager.<AddAddressResp>composeBackpressureError())
                .compose(RetrofitManager.<AddAddressResp>composeBackpressureOther())
                .subscribe(new BaseSubscriber<AddAddressResp>() {
                    @Override
                    protected void hideDialog() {

                    }

                    @Override
                    protected void showDialog() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        mView.addAddressFailure();
                    }

                    @Override
                    public void onNext(AddAddressResp addAddressResp) {
                        if (addAddressResp != null && addAddressResp.isOk()) {
                            mView.addAddressSuccess(addAddressResp.getAddressId());
                        } else {
                            mView.showMsg(addAddressResp != null ? addAddressResp.getMsg() : "");
                            mView.addAddressFailure();
                        }
                    }
                });
    }
}
