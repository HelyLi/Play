package com.hhly.lyygame.presentation.view.messagedetail;

import com.hhly.lyygame.data.net.protocol.game.NewsDetailsResp;
import com.hhly.lyygame.data.net.protocol.user.MsgInfoResp;
import com.hhly.lyygame.presentation.view.BaseLoadingView;
import com.hhly.lyygame.presentation.view.BasePresenter;
import com.hhly.lyygame.presentation.view.BaseView;

/**
 * Created by ${HELY} on 17/2/9.
 * 邮箱：heli.lixiong@gmail.com
 */

public interface WebDetailContract {

    interface Presenter extends BasePresenter {
        void getMessageDetail(int msgId);
        void updateMessage(int msgId);
        void getActivityDetail(int activityId);
        void getNoticeDetail(int noticeId);
    }

    interface View extends BaseView<WebDetailContract.Presenter>, BaseLoadingView {
        void showMessageDetail(MsgInfoResp.MessageDetailBean detailMsg);
        void updateMessageSuccess();
        void showActivityDetail(NewsDetailsResp.NewsNotice newsNotice);
        void showNoticeDetail(NewsDetailsResp.NewsNotice newsNotice);
    }
}
