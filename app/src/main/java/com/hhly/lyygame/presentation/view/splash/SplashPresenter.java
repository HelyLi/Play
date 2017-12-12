package com.hhly.lyygame.presentation.view.splash;

import android.text.TextUtils;

import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.data.db.entry.UserInfo;
import com.hhly.lyygame.data.db.manager.AccountManager;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.UpdateApi;
import com.hhly.lyygame.data.net.client.BaseSubscriber;
import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.BaseReq;
import com.hhly.lyygame.data.net.protocol.update.ADInfoReq;
import com.hhly.lyygame.data.net.protocol.update.ADInfoResp;
import com.hhly.lyygame.data.repository.UserInfoRepository;
import com.hhly.lyygame.data.repository.userinfo.UserInfoDataSource;
import com.hhly.lyygame.presentation.utils.StatisticalUtil;
import com.orhanobut.logger.Logger;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;


/**
 * Created by ${HELY} on 16/12/29.
 * 邮箱：heli.lixiong@gmail.com
 */

public class SplashPresenter implements SplashContract.Presenter {

    private final UserInfoDataSource mUserInfoRepository;
    private final SplashContract.View mView;
    private final UpdateApi mUpdateApi;

    public SplashPresenter(SplashContract.View view) {
        mView = view;
        mUserInfoRepository = new UserInfoRepository();
        mUpdateApi = RetrofitManager.getInstance(ApiType.UPDATE_API).getUpdateApi();
        mView.setPresenter(this);
    }

    @Override
    public void getUserInfos() {
        if (TextUtils.isEmpty(AccountManager.getInstance().getToken())) return;
        /**
         * 逻辑处理
         */
        Flowable.just(AccountManager.getInstance().getToken())
                .compose(RxUtil.<String>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(RetrofitManager.<String>composeBackpressureError())
                .compose(mView.<String>bindToLife())
                .flatMap(new Function<String, Publisher<UserInfo>>() {
                    @Override
                    public Publisher<UserInfo> apply(@NonNull String token) throws Exception {
                        BaseReq req = new BaseReq();
                        req.setToken(token);

                        return mUserInfoRepository.getUserInfo(req);
                    }
                })
                .subscribe(new BaseSubscriber<UserInfo>() {
                    @Override
                    protected void hideDialog() {

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
                        AccountManager.getInstance().saveUserInfo(userInfo);
                        StatisticalUtil.onProfileSignIn(userInfo.getLoginType(), userInfo.getUserId());
                    }
                });

    }

    @Override
    public void getAdvert() {
        final ADInfoReq req = new ADInfoReq();

        mUpdateApi.getADInfo(req.params())
                .compose(RxUtil.<ADInfoResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                //.compose(RetrofitManager.<ADInfoResp>composeBackpressureError())
                .compose(mView.<ADInfoResp>bindToLife())
                .subscribe(new BaseSubscriber<ADInfoResp>() {
                    @Override
                    protected void hideDialog() {

                    }

                    @Override
                    protected void showDialog() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
//                        mView.onFailure();
                    }

                    @Override
                    public void onNext(ADInfoResp adInfoResp) {
                        if (adInfoResp != null && adInfoResp.isOk() && adInfoResp.getData() != null) {
                            mView.showAdvert(adInfoResp.getData());
                        }
                    }
                });

    }

    @Override
    public void subscribe() {
        getUserInfos();
        getAdvert();
    }

    @Override
    public void unsubscribe() {

    }
}
