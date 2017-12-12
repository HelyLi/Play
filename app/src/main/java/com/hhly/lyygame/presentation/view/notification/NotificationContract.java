package com.hhly.lyygame.presentation.view.notification;

import com.hhly.lyygame.data.net.protocol.user.MsgPager;
import com.hhly.lyygame.data.net.protocol.user.NotificationActivityResp;
import com.hhly.lyygame.presentation.view.BaseLoadingView;
import com.hhly.lyygame.presentation.view.BasePresenter;
import com.hhly.lyygame.presentation.view.BaseView;

import java.util.List;

/**
 * Created by ${HELY} on 17/2/23.
 * 邮箱：heli.lixiong@gmail.com
 */

public interface NotificationContract {

    interface Presenter extends BasePresenter {

        void getNotificationActivity();

        void getMsgPage();
    }

    interface View extends BaseView<NotificationContract.Presenter>, BaseLoadingView {

        void showNotifications(List<NotificationActivityResp.ActivityBean> activitys);

        void getNotificationsFail();

        void showMsgs(List<MsgPager.MsgBean> msgBeans, int unreads);

        void getMsgFail();
    }


}
