package com.hhly.lyygame.presentation.view.account.opt;


import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.UserApi;
import com.hhly.lyygame.data.net.client.BaseSubscriber;
import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.BaseResp;
import com.hhly.lyygame.data.net.protocol.user.CheckAccountReq;
import com.hhly.lyygame.data.net.protocol.user.CheckAccountResp;
import com.hhly.lyygame.data.net.protocol.user.SendPhoneCodeReq;
import com.hhly.lyygame.data.net.protocol.user.ValidatePhoneCodeReq;
import com.hhly.lyygame.presentation.utils.RegexUtils;
import com.hhly.lyygame.presentation.utils.TelephonyUtil;
import com.orhanobut.logger.Logger;


/**
 * Created by Simon on 16/9/8.
 */
public class RegisterOrRetrievePresenter implements RegisterOrRetrieveContract.Presenter {

    private static final String TAG = "RegisterOrRetrievePresenter";

    private final RegisterOrRetrieveContract.View mView;

    private UserApi mUserApi;

    public RegisterOrRetrievePresenter(RegisterOrRetrieveContract.View view) {
        mView = view;
        mUserApi = RetrofitManager.getInstance(ApiType.USER_API).getUserApi();
        mView.setPresenter(this);
    }

    @Override
    public void requestVerificationCode(String phone, Integer type) {
        if (!RegexUtils.checkMobile(phone)) {
            mView.showMsg(R.string.lyy_game_address_edit_hint_phone);
            return;
        }


        SendPhoneCodeReq req = new SendPhoneCodeReq();
        req.setPhone(phone);
        req.setPlatformId(TelephonyUtil.getPlatformId());
        req.setOperateType(1 == type ? 1 : 4);
        mUserApi.sendPhoneCode(req.params())
                .compose(RxUtil.<BaseResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<BaseResp>bindToLife())
                //.compose(RetrofitManager.<BaseResp>composeBackpressureError())
                .subscribe(new BaseSubscriber<BaseResp>() {
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
//                        Toast.makeText(App.getTopActivity(), e.message + e.code, Toast.LENGTH_LONG).show();
                        Logger.d("e=" + e.message + ",e.code=" + e.code);
                    }

                    @Override
                    public void onNext(BaseResp baseResp) {
                        mView.dismissLoading();
                        if (!baseResp.isOk()) {
                            mView.showMsg(baseResp.getMsg());
                        } else {
                            mView.startCountDown();
                        }
                    }
                });
    }

    @Override
    public void requestVerification(String phone, String code, int operateType) {

        ValidatePhoneCodeReq req = new ValidatePhoneCodeReq();
        req.setPhone(phone);
        req.setSmsCode(code);
        req.setPlatformId(TelephonyUtil.getPlatformId());
        req.setType(5);
        req.setOperateType(1 == operateType ? 1 : 4);
        mUserApi.validatePhoneCode(req.params())
                .compose(RxUtil.<BaseResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<BaseResp>bindToLife())
                //.compose(RetrofitManager.<BaseResp>composeBackpressureError())
                .subscribe(new BaseSubscriber<BaseResp>() {
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
//                        Toast.makeText(App.getTopActivity(), e.message + e.code, Toast.LENGTH_LONG).show();
                        Logger.d("e=" + e.message + ",e.code=" + e.code);
                    }

                    @Override
                    public void onNext(BaseResp baseResp) {
                        mView.dismissLoading();
                        mView.showMsg(baseResp.getMsg());
                        if (baseResp.isOk()) {
                            mView.onNext();
                        }
                    }
                });

    }

    @Override
    public void checkAccount(String account) {

        CheckAccountReq req = new CheckAccountReq();
        req.setAccount(Long.parseLong(account));

        mUserApi.checkAccount(req.params())
                .compose(RxUtil.<CheckAccountResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<CheckAccountResp>bindToLife())
                //.compose(RetrofitManager.<CheckAccountResp>composeBackpressureError())
                .subscribe(new BaseSubscriber<CheckAccountResp>() {
                    @Override
                    protected void hideDialog() {

                    }

                    @Override
                    protected void showDialog() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
//                        Toast.makeText(App.getTopActivity(), e.message + e.code, Toast.LENGTH_LONG).show();
                        Logger.d("e=" + e.message + ",e.code=" + e.code);
                    }

                    @Override
                    public void onNext(CheckAccountResp checkAccountResp) {
                        if (checkAccountResp.isOk()) {
                            mView.onCheckSuccess(checkAccountResp.getMsg());
                        } else {
                            mView.onCheckFailure(checkAccountResp.getMsg());
                        }
                    }
                });
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
}
