package com.hhly.lyygame.presentation.view.transfer;

import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.App;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.TransferApi;
import com.hhly.lyygame.data.net.client.BaseSubscriber;
import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.transfer.TransferExchangeGameInfoReq;
import com.hhly.lyygame.data.net.protocol.transfer.TransferExchangeGameInfoResp;
import com.hhly.lyygame.data.net.protocol.transfer.TransferExchangeReq;
import com.hhly.lyygame.data.net.protocol.transfer.TransferExchangeResp;
import com.hhly.lyygame.data.net.protocol.transfer.TransferGameBalanceReq;
import com.hhly.lyygame.data.net.protocol.transfer.TransferGameBalanceResp;
import com.hhly.lyygame.presentation.utils.NetworkUtil;
import com.hhly.lyygame.presentation.utils.TelephonyUtil;
import com.hhly.lyygame.presentation.view.ToastUtil;
import com.orhanobut.logger.Logger;

/**
 * Created by ${HELY} on 17/4/24.
 * 邮箱：heli.lixiong@gmail.com
 */

public class CouponTransferPresenter implements CouponTransferContract.Presenter{

    private final TransferApi mTransferApi;
    private final CouponTransferContract.View mView;

    public CouponTransferPresenter(CouponTransferContract.View view){
        mTransferApi = RetrofitManager.getInstance(ApiType.TRANSFER_API).getTransferApi();
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

                    }

                    @Override
                    public void onNext(TransferGameBalanceResp resp) {
                        if (resp != null && resp.isOk()){
                            mView.showTransGameBalance(resp.getFreeBalance());
                        }
//                        else {
//                            mView.showMsg(resp != null ? resp.getMsg() : "");
//                        }
                    }
                });
    }

    @Override
    public void getTransExchangeGameInfo(String gameId) {
        final TransferExchangeGameInfoReq req = new TransferExchangeGameInfoReq();
        req.setPlatformId(gameId);

        mTransferApi.transExchangeGameInfo(req.params())
                .compose(RxUtil.<TransferExchangeGameInfoResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<TransferExchangeGameInfoResp>bindToLife())
                .subscribe(new BaseSubscriber<TransferExchangeGameInfoResp>() {
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
                    public void onNext(TransferExchangeGameInfoResp resp) {
                        if (resp != null && resp.isOk()){
                            mView.showTransExchangeGameInfo(resp);
                        }
//                        else {
//                            mView.showMsg(resp != null ? resp.getMsg() : "");
//                        }
                    }
                });
    }

    @Override
    public void transExchange(String fGameId, String fGameGolds) {
        if (!NetworkUtil.isAvailable(App.getContext())) {
            ToastUtil.showTip(App.getContext(), R.string.lyy_network_notwork);
            return;
        }
        final TransferExchangeReq req = new TransferExchangeReq();
        req.setfGameGolds(fGameGolds);
        req.setfPlatformId(fGameId);
        req.setTerminal(String.valueOf(TelephonyUtil.getOsTypeInt()));

        mTransferApi.transExchange(req.params())
                .compose(RxUtil.<TransferExchangeResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(RetrofitManager.<TransferExchangeResp>composeBackpressureOther())
                .compose(mView.<TransferExchangeResp>bindToLife())
                .subscribe(new BaseSubscriber<TransferExchangeResp>() {
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
                        Logger.d(e.message);
                    }

                    @Override
                    public void onNext(TransferExchangeResp resp) {
                        mView.dismissLoading();
                        if (resp != null && resp.isOk()){
                            mView.exchangeSuccess();
                        }else {
                            Logger.d(resp.getMsg());
                            mView.exchangeFailure();
                        }
                    }
                });

    }

    //http://mgame.1332255.com/api/gameGoldsToLyqTransfer/getExchangeGameInfo?country=0&platformId=10110
    //http://mgame.1332255.com/api/gameGoldsToLyqTransfer/exchange?country=0&fGameGolds=2&fPlatformId=10600&terminal=4
}
