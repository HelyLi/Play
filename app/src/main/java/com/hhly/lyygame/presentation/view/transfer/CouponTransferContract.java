package com.hhly.lyygame.presentation.view.transfer;

import com.hhly.lyygame.data.net.protocol.transfer.TransferExchangeGameInfoResp;
import com.hhly.lyygame.presentation.view.BaseLoadingView;
import com.hhly.lyygame.presentation.view.BasePresenter;
import com.hhly.lyygame.presentation.view.BaseView;

/**
 * Created by ${HELY} on 17/4/24.
 * 邮箱：heli.lixiong@gmail.com
 */

public class CouponTransferContract {

    interface Presenter extends BasePresenter {
        void getTransGameBalance(String gameId);
        void getTransExchangeGameInfo(String gameId);
        void transExchange(String fGameId, String fGameGolds);
    }

    interface View extends BaseView<CouponTransferContract.Presenter>, BaseLoadingView {
        void showTransGameBalance(double balance);
        void showTransExchangeGameInfo(TransferExchangeGameInfoResp exchangeGameInfo);
        void exchangeSuccess();
        void exchangeFailure();
    }

}
