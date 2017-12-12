package com.hhly.lyygame.presentation.view.transfer;

import com.hhly.lyygame.data.net.protocol.transfer.TransferGameListResp;
import com.hhly.lyygame.presentation.view.BaseLoadingView;
import com.hhly.lyygame.presentation.view.BasePresenter;
import com.hhly.lyygame.presentation.view.BaseView;

import java.util.List;

/**
 * Created by ${HELY} on 17/4/24.
 * 邮箱：heli.lixiong@gmail.com
 */

public class TransferContract {

    interface Presenter extends BasePresenter {
        void transGameList(String drawType);
    }

    interface View extends BaseView<TransferContract.Presenter>, BaseLoadingView {
        void showTransGameList(List<TransferGameListResp.TransferGameInfo> transferGameInfoList);
        void showFailure();
        void onEmptyView();
    }

}
