package com.hhly.lyygame.presentation.view.info;

import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.data.db.entry.UserInfo;
import com.hhly.lyygame.data.db.manager.AccountManager;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.UserApi;
import com.hhly.lyygame.data.net.client.BaseSubscriber;
import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.user.ResetPwdReq;
import com.hhly.lyygame.data.net.protocol.user.ResetPwdResp;
import com.hhly.lyygame.presentation.utils.MD5;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * Created by ${HELY} on 17/2/8.
 * 邮箱：heli.lixiong@gmail.com
 */

public class ResetPwdPresenter implements ResetPwdContract.Presenter {

    private final ResetPwdContract.View mView;
    private final UserApi mUserApi;

    public ResetPwdPresenter(ResetPwdContract.View view) {
        this.mView = view;
        mUserApi = RetrofitManager.getInstance(ApiType.USER_API).getUserApi();
        mView.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void resetPassword(String oldPwd, String newPwd) {
        ResetPwdReq req = new ResetPwdReq();
        req.setCountry(0);
        req.setPassword(new MD5(newPwd).getMd5_32());
        req.setOldPassword(new MD5(oldPwd).getMd5_32());

        mUserApi.resetPwd(req.params())
                .compose(RxUtil.<ResetPwdResp>applySchedulers(RxUtil.IO_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<ResetPwdResp>bindToLife())
                .compose(RetrofitManager.<ResetPwdResp>composeBackpressureOther())
                .map(new Function<ResetPwdResp, UserInfo>() {
                    @Override
                    public UserInfo apply(@NonNull ResetPwdResp resetPwdResp) throws Exception {
                        if (resetPwdResp != null && resetPwdResp.isOk()) {

                            UserInfo userInfo = resetPwdResp.getUser();
                            userInfo.setToken(AccountManager.getInstance().getToken());
                            AccountManager.getInstance().saveUserInfo(userInfo);
                        } else {
                            mView.resetPwdFailure(resetPwdResp.getMsg());
                        }
                        return resetPwdResp.getUser();
                    }
                })
                .filter(new Predicate<UserInfo>() {
                    @Override
                    public boolean test(@NonNull UserInfo userInfo) throws Exception {
                        return userInfo != null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<UserInfo>() {
                    @Override
                    protected void hideDialog() {

                    }

                    @Override
                    protected void showDialog() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {

                    }

                    @Override
                    public void onNext(UserInfo info) {
                        mView.resetPwdSuccess();
                    }
                });
    }
}
