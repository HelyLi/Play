package com.hhly.lyygame.presentation.view.transfer;

import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.App;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.db.entry.UserInfo;
import com.hhly.lyygame.data.db.manager.AccountManager;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.TransferApi;
import com.hhly.lyygame.data.net.UserApi;
import com.hhly.lyygame.data.net.client.BaseSubscriber;
import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.BaseReq;
import com.hhly.lyygame.data.net.protocol.transfer.TransferGameBalanceReq;
import com.hhly.lyygame.data.net.protocol.transfer.TransferGameBalanceResp;
import com.hhly.lyygame.data.net.protocol.transfer.TransferRemitInfoReq;
import com.hhly.lyygame.data.net.protocol.transfer.TransferRemitInfoResp;
import com.hhly.lyygame.data.net.protocol.transfer.TransferRemitReq;
import com.hhly.lyygame.data.net.protocol.transfer.TransferRemitResp;
import com.hhly.lyygame.data.net.protocol.user.GetUserInfoResp;
import com.hhly.lyygame.presentation.utils.NetworkUtil;
import com.hhly.lyygame.presentation.utils.TelephonyUtil;
import com.hhly.lyygame.presentation.view.ToastUtil;
import com.orhanobut.logger.Logger;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ${HELY} on 17/4/24.
 * 邮箱：heli.lixiong@gmail.com
 */

public class GameTransferPresenter implements GameTransferContract.Presenter{

    private final TransferApi mTransferApi;
    private final UserApi mUserApi;
    private final GameTransferContract.View mView;

    public GameTransferPresenter(GameTransferContract.View view){
        mTransferApi = RetrofitManager.getInstance(ApiType.TRANSFER_API).getTransferApi();
        mUserApi = RetrofitManager.getInstance(ApiType.USER_API).getUserApi();
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void getTransGameBalance(String gameId) {
        TransferGameBalanceReq req = new TransferGameBalanceReq();
        req.setPlatformId(gameId);

        Logger.d("getTransGameBalance.req=" +req);

        mTransferApi.transGameBalance(req.params())
                .compose(RxUtil.<TransferGameBalanceResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<TransferGameBalanceResp>bindToLife())
                .subscribe(new BaseSubscriber<TransferGameBalanceResp>() {
                    @Override
                    protected void hideDialog() {

                    }

                    @Override
                    protected void showDialog() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        mView.showTransGameBalanceFailure();
                    }

                    @Override
                    public void onNext(TransferGameBalanceResp resp) {
                        if (resp != null && resp.isOk()){
                            mView.showTransGameBalance(resp.getFreeBalance());
                        }else {
                            mView.showTransGameBalanceFailure();
//                            mView.showMsg(resp != null ? resp.getMsg() : "");
                        }
                    }
                });

    }

    @Override
    public void getTransRemitGameInfo(String fGameId, String tGameId) {
        final TransferRemitInfoReq req = new TransferRemitInfoReq();
        req.setFplatformId(fGameId);
        req.setTplatformId(tGameId);

        mTransferApi.transRemitGameInfo(req.params())
                .compose(RxUtil.<TransferRemitInfoResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<TransferRemitInfoResp>bindToLife())
                .subscribe(new BaseSubscriber<TransferRemitInfoResp>() {
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
                    public void onNext(TransferRemitInfoResp resp) {
                        if (resp != null && resp.isOk()){
                            mView.showTransRemitGameInfo(resp);
                        }
                    }
                });
    }

    @Override
    public void transRemit(String fGameId, String tGameId, String fGameGolds) {

        if (!NetworkUtil.isAvailable(App.getContext())) {
            ToastUtil.showTip(App.getContext(), R.string.lyy_network_notwork);
            return;
        }

        final TransferRemitReq req = new TransferRemitReq();
        req.setTerminal(String.valueOf(TelephonyUtil.getOsTypeInt()));
        req.setfPlatformId(fGameId);
        req.settPlatformId(tGameId);
        req.setfGameGolds(fGameGolds);

        mTransferApi.transRemit(req.params())
                .compose(RxUtil.<TransferRemitResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(RetrofitManager.<TransferRemitResp>composeBackpressureOther())
                .compose(mView.<TransferRemitResp>bindToLife())
                .subscribe(new BaseSubscriber<TransferRemitResp>() {
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
                        mView.showMsg(e.message);
                        mView.transRemitFailure();
                    }

                    @Override
                    public void onNext(TransferRemitResp resp) {
                        mView.dismissLoading();
                        if (resp != null && resp.isOk()){
                            mView.transRemitSuccess();
                        }else {
                            mView.transRemitFailure();
                        }
                    }
                });

    }

    @Override
    public void getUserBalance() {

        BaseReq req = new BaseReq();
        mUserApi.getUserInfo(req.params())
                .compose(RxUtil.<GetUserInfoResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<GetUserInfoResp>bindToLife())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<GetUserInfoResp>() {
                    @Override
                    public boolean test(@NonNull GetUserInfoResp getUserInfoResp) throws Exception {
                        if (getUserInfoResp == null || !getUserInfoResp.isOk()) {
                            mView.showTransGameBalanceFailure();
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
                        extra.setToken(AccountManager.getInstance().getToken());
                        AccountManager.getInstance().saveUserInfo(extra);
                        Logger.d("login.saveUserInfo");
                        return extra;
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
                        mView.showTransGameBalanceFailure();
                    }

                    @Override
                    public void onNext(UserInfo userInfo) {
                        mView.showTransGameBalance(Double.parseDouble(userInfo.getLyb() == null ? "0": userInfo.getLyb()));
                    }
                });

    }

    //http://mgame.1332255.com/api/gameGoldsTransfer/getGameBalance?country=0&platformId=10110&userId=hhly91599
    //http://mgame.1332255.com/api/gameGoldsTransfer/getRemitGameInfo?country=0&fplatformId=10110&tplatformId=10110
    //http://mgame.1332255.com/api/gameGoldsTransfer/remit?country=0&fGameGolds=50&fPlatformId=1&tPlatformId=10560&terminal=4

}
