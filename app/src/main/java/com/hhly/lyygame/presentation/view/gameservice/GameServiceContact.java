package com.hhly.lyygame.presentation.view.gameservice;

import com.hhly.lyygame.data.net.protocol.game.GameAreaResp;
import com.hhly.lyygame.presentation.view.BaseLoadingView;
import com.hhly.lyygame.presentation.view.BasePresenter;
import com.hhly.lyygame.presentation.view.BaseView;

import java.util.List;

/**
 * Created by ${HELY} on 17/1/18.
 * 邮箱：heli.lixiong@gmail.com
 */

public interface GameServiceContact {

    interface Persenter extends BasePresenter{

        void getGameArea(int serviceType);
    }

    interface View extends BaseView<GameServiceContact.Persenter>, BaseLoadingView {

        void showNewGameArea(List<GameAreaResp.ListGameServerAreaBean> list);

        void showFutureGameArea(List<GameAreaResp.ListGameServerAreaBean> list);

        void onFailure();
    }

}
