package com.hhly.lyygame.presentation.view.splash;

import com.hhly.lyygame.data.net.protocol.update.ADInfoResp;
import com.hhly.lyygame.presentation.view.BasePresenter;
import com.hhly.lyygame.presentation.view.BaseView;

/**
 * Created by ${HELY} on 16/12/29.
 * 邮箱：heli.lixiong@gmail.com
 */

public interface SplashContract {

    interface Presenter extends BasePresenter {
        void getUserInfos();
        void getAdvert();
    }

    interface View extends BaseView<SplashContract.Presenter> {
        void showAdvert(ADInfoResp.ADInfo adInfo);
//        void onFailure();
    }

}
