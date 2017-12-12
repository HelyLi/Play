package com.hhly.lyygame.presentation.view.search;

import com.hhly.lyygame.data.net.protocol.game.GameByModelIdResp;
import com.hhly.lyygame.presentation.downloadutils.DownloadItem;
import com.hhly.lyygame.presentation.view.BaseLoadingView;
import com.hhly.lyygame.presentation.view.BasePresenter;
import com.hhly.lyygame.presentation.view.BaseView;

import java.util.List;

/**
 * Created by ${HELY} on 17/2/23.
 * 邮箱：heli.lixiong@gmail.com
 */

public class SearchContract {

    interface SearchInitializePresenter extends BasePresenter{
        void getHotGameList();//<=4
        void getSearchRecord();
        void clearSearchRecord();
    }
    
    interface SearchInitializeView extends BaseView<SearchInitializePresenter>, BaseLoadingView{
        void showHotGameList(List<GameByModelIdResp.GameByModeIdPage.GameByModeIdInfo> gameInfoList);
        void showSearchRecord(List<String> records);
        void onClearRecord();
    }

    interface SearchResultPresenter extends BasePresenter{
        void searchGameList(String keyword, int pageNo, int pageSize);
        void saveSearchRecord(String record);
    }

    interface SearchResultView extends BaseView<SearchResultPresenter>, BaseLoadingView{
        void showSearchGameList(List<DownloadItem> downloadItems);
        void showTotalPage(int totalPage);
        void onFailure();
    }

}
