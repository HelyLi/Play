package com.hhly.lyygame.presentation.view.activity;

import com.hhly.lyygame.presentation.view.BaseLoadingView;
import com.hhly.lyygame.presentation.view.BasePresenter;
import com.hhly.lyygame.presentation.view.BaseView;

import java.util.List;

/**
 * 活动
 * Created by Simon on 2016/12/10.
 */

interface ActivitiesContract {

    interface Presenter extends BasePresenter {
        void getActivities(boolean refresh);
        void loadGameActivity(int gameId, int noticeId);
    }

    interface View extends BaseView<Presenter>, BaseLoadingView {
        void showActivityList(List<?> list);
        void showGameActive(List<?> gameNoticeList);
        void onFailure();
        void onEmptyView();
    }

}
