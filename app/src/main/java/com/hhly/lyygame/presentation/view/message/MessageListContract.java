package com.hhly.lyygame.presentation.view.message;

import com.hhly.lyygame.data.net.protocol.user.MsgPager;
import com.hhly.lyygame.presentation.view.BaseLoadingView;
import com.hhly.lyygame.presentation.view.BasePresenter;
import com.hhly.lyygame.presentation.view.BaseView;

import java.util.List;

/**
 * 消息列表
 * Created by Simon on 2016/12/10.
 */

interface MessageListContract {

    interface Presenter extends BasePresenter {
        void loadMessage(int msgType, int pagerIndex, int pagerSize);
        void deleteMessage(int msgId, int position);
    }

    interface View extends BaseView<Presenter>, BaseLoadingView {
        void showMessages(List<MsgPager.MsgBean> messages, int totalPage);
        void onFailure();
        void onEmptyView();
        void deleteSuccess(int position);
    }
}
