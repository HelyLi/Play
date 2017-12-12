package com.hhly.lyygame.presentation.view.info;

import com.hhly.lyygame.presentation.view.BaseLoadingView;
import com.hhly.lyygame.presentation.view.BasePresenter;
import com.hhly.lyygame.presentation.view.BaseView;

/**
 * Created by ${HELY} on 17/2/7.
 * 邮箱：heli.lixiong@gmail.com
 */

public class ResetPwdContract {

    interface Presenter extends BasePresenter{
        void resetPassword(String oldPwd, String newPwd);
    }

    interface View extends BaseView<Presenter>, BaseLoadingView{
        void resetPwdSuccess();

        void resetPwdFailure(String msg);
    }

}
