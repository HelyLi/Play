package com.hhly.lyygame.presentation.view.task;

import com.hhly.lyygame.data.db.entry.UserInfo;
import com.hhly.lyygame.presentation.view.BaseLoadingView;
import com.hhly.lyygame.presentation.view.BasePresenter;
import com.hhly.lyygame.presentation.view.BaseView;

/**
 * Created by ${HELY} on 17/3/4.
 * 邮箱：heli.lixiong@gmail.com
 */

public class TaskContract {

    //积分明细
    interface Presenter extends BasePresenter {
        void getUserInfo();
        void queryMonthSign();
        void getInviteCode();
    }

    interface View extends BaseView<Presenter>, BaseLoadingView {
        void showUserInfo(UserInfo userInfo);
        void showMonthSign(boolean sign);
        void showInviteCode(String inviteCode);
    }

}
