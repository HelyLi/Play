package com.hhly.lyygame.presentation.view.signin;

import com.hhly.lyygame.presentation.view.BaseLoadingView;
import com.hhly.lyygame.presentation.view.BasePresenter;
import com.hhly.lyygame.presentation.view.BaseView;

/**
 * 签到
 * Created by Simon on 2016/12/10.
 */

interface SignInContract {

    interface Presenter extends BasePresenter {
        void getSignInInfo();
        void signIn(int day);
    }

    interface View extends BaseView<Presenter>, BaseLoadingView {
        void showSignInfo(int continueDay, String day);
        void signInSuccess(double points);
    }

}
