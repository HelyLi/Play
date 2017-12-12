package com.hhly.lyygame.presentation.view.home;

import com.hhly.lyygame.data.net.protocol.game.IndexActivityByModelIdResp;
import com.hhly.lyygame.data.net.protocol.game.NewsListResp;
import com.hhly.lyygame.presentation.downloadutils.DownloadItem;
import com.hhly.lyygame.presentation.view.BaseLoadingView;
import com.hhly.lyygame.presentation.view.BasePresenter;
import com.hhly.lyygame.presentation.view.BaseView;

import java.util.List;

/**
 * Created by dell on 2017/5/15.
 */

public interface GameHomeContract {

    interface Presenter extends BasePresenter {

        void getMsgPage();
        void getOnlyGames(boolean refresh);
        void getActivitis(boolean refresh);
        void getNews(boolean refresh);
    }

    interface View extends BaseView<Presenter>, BaseLoadingView {

        void showOnlyGameList(List<DownloadItem> gameInfoList);
        void showActivityList(List<IndexActivityByModelIdResp.ActivityPage.ActivityInfo> list);
        void onMsgSize(int size);
        void hideRefresh();
        void showNews(List<NewsListResp.NewsPage.NewsInfo> list);
    }

}
