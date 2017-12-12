package com.hhly.lyygame.presentation.view.exchange;

import com.hhly.lyygame.data.net.protocol.goods.GoodsExchangeHistoryResp;
import com.hhly.lyygame.presentation.view.BaseLoadingView;
import com.hhly.lyygame.presentation.view.BasePresenter;
import com.hhly.lyygame.presentation.view.BaseView;

import java.util.List;

/**
 * 兑换记录
 * Created by Simon on 2016/12/10.
 */

interface ExchangeHistoryContract {

    interface Presenter extends BasePresenter {
        void getExchangeHistory(String userId, int pageNo, int pageSize);
    }
    
    interface View extends BaseView<Presenter>, BaseLoadingView {
        void showExchangeHistory(List<GoodsExchangeHistoryResp.ExchangeBean> exchangeBeanList, int totalSize);
        void onFailure();
        void onEmptyView();
    }

}
