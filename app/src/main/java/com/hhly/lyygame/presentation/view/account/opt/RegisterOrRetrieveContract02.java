package com.hhly.lyygame.presentation.view.account.opt;


import com.hhly.lyygame.presentation.view.BaseLoadingView;
import com.hhly.lyygame.presentation.view.BasePresenter;
import com.hhly.lyygame.presentation.view.BaseView;

/**
 * Created by Simon on 16/9/8.
 */
public interface RegisterOrRetrieveContract02 {

    interface Presenter extends BasePresenter {

        //注册
        void requestRegOrRetrieve(String phone, String pwd, String inviteCode, String smsCode, Integer operateType);

        //忘记密码
        void requestResetPassword(String phone, String pwd);

        //登录
        void login(String account, String pwd);
    }

    interface View extends BaseView<Presenter>, BaseLoadingView {
        void onNext();

        void doLoginSuccess();
    }
}
