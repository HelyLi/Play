package com.hhly.lyygame.presentation.view.me;

import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.data.db.entry.UserInfo;
import com.hhly.lyygame.data.db.manager.AccountManager;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.UserApi;
import com.hhly.lyygame.data.net.client.BaseSubscriber;
import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.BaseReq;
import com.hhly.lyygame.data.net.protocol.BaseResp;
import com.hhly.lyygame.data.net.protocol.user.GetUserInfoResp;
import com.hhly.lyygame.data.net.protocol.user.State;
import com.orhanobut.logger.Logger;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ${HELY} on 16/12/26.
 * 邮箱：heli.lixiong@gmail.com
 */

public class MePresenter implements MeContract.Presenter{

    private final MeContract.View mView;
    private final UserApi mUserApi;

    public MePresenter(MeContract.View view) {
        mView = view;
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
    public void logout() {
        mView.showLoading();
        BaseReq req = new BaseReq();

        mUserApi.logout(req.params())
                .compose(RxUtil.<BaseResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                //.compose(RetrofitManager.<BaseResp>composeBackpressureError())
                .compose(mView.<BaseResp>bindToLife())
                .subscribe(new BaseSubscriber<BaseResp>() {

                    @Override
                    protected void hideDialog() {
                        mView.dismissLoading();
                    }

                    @Override
                    protected void showDialog() {

                    }

                    @Override
                    public void onNext(BaseResp baseResp) {
                        mView.dismissLoading();
                    }


                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        Logger.d(e.message + e.code);
                    }
                });

    }

    @Override
    public void getUserInfo() {
//        mView.showLoading();
        BaseReq req = new BaseReq();

        mUserApi.getUserInfo(req.params())
                .compose(RxUtil.<GetUserInfoResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(RetrofitManager.<GetUserInfoResp>composeBackpressureOther())
                .compose(mView.<GetUserInfoResp>bindToLife())
                .filter(new Predicate<GetUserInfoResp>() {
                    @Override
                    public boolean test(@NonNull GetUserInfoResp getUserInfoResp) throws Exception {
                        if (getUserInfoResp == null || getUserInfoResp.getResult() != State.OK) {
//                            mView.dismissLoading();
                        }
                        return getUserInfoResp != null && getUserInfoResp.isOk();
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Function<GetUserInfoResp, UserInfo>() {
                    @Override
                    public UserInfo apply(@NonNull GetUserInfoResp getUserInfoResp) throws Exception {
                        UserInfo extra = getUserInfoResp.getUser();

                        extra.setId(getUserInfoResp.getUser().getId());
                        extra.setJf(getUserInfoResp.getJf());
                        extra.setBindFlag(getUserInfoResp.getBindFlag());
                        extra.setLyb(getUserInfoResp.getLyb());
                        extra.setLyq(getUserInfoResp.getLyq());
                        extra.setPaypwdFlag(getUserInfoResp.getPaypwdFlag());
                        extra.setSafeLevel(getUserInfoResp.getSafeLevel());
                        String token = AccountManager.getInstance().getToken();
                        extra.setToken(token);

                        AccountManager.getInstance().saveUserInfo(extra);
                        Logger.d("login.saveUserInfo");
                        return extra;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<UserInfo>() {
                    @Override
                    protected void hideDialog() {
//                        mView.dismissLoading();
                    }

                    @Override
                    protected void showDialog() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        Logger.d(e.message + e.code);
                    }

                    @Override
                    public void onNext(UserInfo userInfo) {
                        mView.getUserInfoSuccess();
                    }
                });

    }
}
