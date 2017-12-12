package com.hhly.lyygame.presentation.view.gamehall.category;

import com.hhly.lyygame.presentation.downloadutils.DownloadItem;
import com.hhly.lyygame.presentation.view.BaseLoadingView;
import com.hhly.lyygame.presentation.view.BasePresenter;
import com.hhly.lyygame.presentation.view.BaseView;

import java.util.List;

/**
 * Created by ${HELY} on 17/2/25.
 * 邮箱：heli.lixiong@gmail.com
 */

public class GategoryListContract {

    interface Presenter extends BasePresenter {
        void getGameList(boolean refresh, GameCategory category, int pageNo, int pageSize);
    }

    interface View extends BaseView<Presenter>, BaseLoadingView {
        void showGameList(List<DownloadItem> list,boolean refresh);
        void showTotalPage(int totalPage);
        void onFailure();
    }

}
