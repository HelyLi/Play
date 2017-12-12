package com.hhly.lyygame.presentation.view.task;

import android.text.TextUtils;

import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.data.db.entry.UserInfo;
import com.hhly.lyygame.data.db.manager.AccountManager;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.UserApi;
import com.hhly.lyygame.data.net.client.BaseSubscriber;
import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.BaseReq;
import com.hhly.lyygame.data.net.protocol.user.GetUserInfoResp;
import com.hhly.lyygame.data.net.protocol.user.InviteCodeReq;
import com.hhly.lyygame.data.net.protocol.user.InviteCodeResp;
import com.hhly.lyygame.data.net.protocol.user.MonthSignReq;
import com.hhly.lyygame.data.net.protocol.user.MonthSignResp;
import com.hhly.lyygame.data.net.protocol.user.State;
import com.hhly.lyygame.presentation.utils.DateUtils;
import com.orhanobut.logger.Logger;

import java.util.Date;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * Created by ${HELY} on 17/3/4.
 * 邮箱：heli.lixiong@gmail.com
 */

public class TaskPresenter implements TaskContract.Presenter {

    private final TaskContract.View mView;
    private final UserApi mUserApi;

    public TaskPresenter(TaskContract.View view) {
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
    public void getUserInfo() {
        BaseReq req = new BaseReq();

        mUserApi.getUserInfo(req.params())
                .compose(RxUtil.<GetUserInfoResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(RetrofitManager.<GetUserInfoResp>composeBackpressureOther())
                .compose(RetrofitManager.<GetUserInfoResp>composeBackpressureError())
                .compose(mView.<GetUserInfoResp>bindToLife())
                .filter(new Predicate<GetUserInfoResp>() {
                    @Override
                    public boolean test(@NonNull GetUserInfoResp getUserInfoResp) throws Exception {
                        if (getUserInfoResp == null || getUserInfoResp.getResult() != State.OK) {
                            mView.dismissLoading();
                        }
                        return getUserInfoResp != null && getUserInfoResp.isOk();
                    }
                })
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

                        return extra;
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
                        mView.showUserInfo(userInfo);
                    }
                });

    }

    @Override
    public void queryMonthSign() {
        MonthSignReq req = new MonthSignReq();

        mUserApi.queryMonthSign(req.params())
                .compose(RxUtil.<MonthSignResp>applySchedulers(RxUtil.IO_TRANSFORMER_BACK_PRESSURE))
                .compose(RetrofitManager.<MonthSignResp>composeBackpressureOther())
                .compose(RetrofitManager.<MonthSignResp>composeBackpressureError())
                .compose(mView.<MonthSignResp>bindToLife())
                .map(new Function<MonthSignResp, Boolean>() {
                    @Override
                    public Boolean apply(@NonNull MonthSignResp monthSignResp) throws Exception {
                        int continueDay = monthSignResp.getContinueDay();
                        String day = monthSignResp.getDay();
                        boolean has = false;

                        if (continueDay > 0 && !TextUtils.isEmpty(day)) {
                            String[] days = day.split(";");
                            String currentDay = String.valueOf(DateUtils.getDay(new Date(System.currentTimeMillis())));
                            for (String str : days) {
                                if (str.equals(currentDay)) {
                                    has = true;
                                    break;
                                }
                            }
                        }
                        return has;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<Boolean>() {
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
                    public void onNext(Boolean aBoolean) {
                        mView.showMonthSign(aBoolean);
                    }
                });
    }

    @Override
    public void getInviteCode() {
        InviteCodeReq req = new InviteCodeReq();
        req.setUserId(AccountManager.getInstance().getUserId());
        
        mUserApi.getInviteCode(req.params())
                .compose(RxUtil.<InviteCodeResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(RetrofitManager.<InviteCodeResp>composeBackpressureOther())
                .compose(RetrofitManager.<InviteCodeResp>composeBackpressureError())
                .compose(mView.<InviteCodeResp>bindToLife())
                .subscribe(new BaseSubscriber<InviteCodeResp>() {
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
                    public void onNext(InviteCodeResp inviteCodeResp) {
                        if (inviteCodeResp != null && inviteCodeResp.isOk() && !TextUtils.isEmpty(inviteCodeResp.getInviteCode())) {
                            mView.showInviteCode(inviteCodeResp.getInviteCode());
                        } else {
                            getInviteCode();
                        }
                    }
                });

    }
}
