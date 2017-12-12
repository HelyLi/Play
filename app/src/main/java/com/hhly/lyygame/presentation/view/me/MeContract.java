package com.hhly.lyygame.presentation.view.me;

import com.hhly.lyygame.presentation.view.BaseLoadingView;
import com.hhly.lyygame.presentation.view.BasePresenter;
import com.hhly.lyygame.presentation.view.BaseView;

/**
 * Created by ${HELY} on 16/12/26.
 * 邮箱：heli.lixiong@gmail.com
 */

public class MeContract {

    interface Presenter extends BasePresenter{
        void logout();
        void getUserInfo();
    }

    interface View extends BaseView<Presenter>, BaseLoadingView{
        void getUserInfoSuccess();
        void getUserInfoFailure(String msg);
    }

}
