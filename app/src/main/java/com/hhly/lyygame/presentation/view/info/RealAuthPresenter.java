package com.hhly.lyygame.presentation.view.info;

import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.data.db.entry.UserInfo;
import com.hhly.lyygame.data.db.manager.AccountManager;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.UserApi;
import com.hhly.lyygame.data.net.client.BaseSubscriber;
import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.user.RealAuthReq;
import com.hhly.lyygame.data.net.protocol.user.RealAuthResp;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * Created by ${HELY} on 17/2/7.
 * 邮箱：heli.lixiong@gmail.com
 */

public class RealAuthPresenter implements RealAuthContract.Presenter {

    private final RealAuthContract.View mView;
    private final UserApi mUserApi;

    public RealAuthPresenter(RealAuthContract.View view) {
        this.mView = view;
        mUserApi = RetrofitManager.getInstance(ApiType.USER_API).getUserApi();
        mView.setPresenter(this);
    }

    @Override
    public void completeAuth(String name, String idCard) {

        RealAuthReq req = new RealAuthReq();
        req.setRealname(name);
        req.setIdcardNo(idCard);

        mUserApi.completeAuth(req.params())
                .compose(RxUtil.<RealAuthResp>applySchedulers(RxUtil.IO_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<RealAuthResp>bindToLife())
                .compose(RetrofitManager.<RealAuthResp>composeBackpressureError())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<RealAuthResp, UserInfo>() {
                    @Override
                    public UserInfo apply(@NonNull RealAuthResp realAuthResp) throws Exception {

                        if (realAuthResp != null && realAuthResp.isOk()) {
                            realAuthResp.getUser().setToken(AccountManager.getInstance().getToken());
                            AccountManager.getInstance().saveUserInfo(realAuthResp.getUser());
                        } else {
                            mView.authFailure(realAuthResp.getMsg());
                        }
                        return realAuthResp.getUser();
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
                        mView.authSuccess();
                    }
                });
//                .subscribe(new Consumer<UserInfo>() {
//                    @Override
//                    public void accept(@NonNull UserInfo userInfo) throws Exception {
//
//                    }
//                });

    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
    }
}
