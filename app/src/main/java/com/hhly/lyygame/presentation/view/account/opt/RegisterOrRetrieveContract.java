package com.hhly.lyygame.presentation.view.account.opt;


import com.hhly.lyygame.presentation.view.BaseLoadingView;
import com.hhly.lyygame.presentation.view.BasePresenter;
import com.hhly.lyygame.presentation.view.BaseView;

/**
 * Created by Simon on 16/9/8.
 */
public interface RegisterOrRetrieveContract {

    interface Presenter extends BasePresenter {

        /**
         * 请求验证码
         *
         * @param phone
         * @param type
         */
        void requestVerificationCode(String phone, Integer type);

        /**
         * 验证
         *
         * @param phone
         * @param code
         */
        void requestVerification(String phone, String code, int operateType);

        /**
         * @param account
         */
        void checkAccount(String account);
    }

    interface View extends BaseView<Presenter>, BaseLoadingView {
        void onNext();

        void startCountDown();

        void onCheckSuccess(String msg);
        void onCheckFailure(String msg);
    }
}
