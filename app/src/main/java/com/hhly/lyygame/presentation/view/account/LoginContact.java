package com.hhly.lyygame.presentation.view.account;

import com.hhly.lyygame.presentation.view.BaseLoadingView;
import com.hhly.lyygame.presentation.view.BasePresenter;
import com.hhly.lyygame.presentation.view.BaseView;

/**
 * 登录
 * Created by Simon on 2016/12/7.
 */

interface LoginContact {

    interface Presenter extends BasePresenter {
        void login(String account, String pwd);
        void thirdLogin(String accessToken, int type, String appId);
    }

    interface View extends BaseView<Presenter>, BaseLoadingView {
        void doLoginSuccess();
    }

}
