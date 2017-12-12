package com.hhly.lyygame.presentation.view.transfer;

import com.hhly.lyygame.data.net.protocol.transfer.TransferRemitInfoResp;
import com.hhly.lyygame.presentation.view.BaseLoadingView;
import com.hhly.lyygame.presentation.view.BasePresenter;
import com.hhly.lyygame.presentation.view.BaseView;

/**
 * Created by ${HELY} on 17/4/24.
 * 邮箱：heli.lixiong@gmail.com
 */

public class GameTransferContract {

    interface Presenter extends BasePresenter {
        void getTransGameBalance(String gameId);
        void getTransRemitGameInfo(String fGameId, String tGameId);
        void transRemit(String fGameId, String tGameId, String fGameGolds);
        void getUserBalance();
    }

    interface View extends BaseView<GameTransferContract.Presenter>, BaseLoadingView {
        void showTransGameBalance(double balance);
        void showTransGameBalanceFailure();
        void showTransRemitGameInfo(TransferRemitInfoResp remitInfo);
        void transRemitSuccess();
        void transRemitFailure();
//        void userInfoSuccess(String lyb);
//        void userInfoFailure();
    }

}
