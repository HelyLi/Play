package com.hhly.lyygame.presentation.view.mall.category;

import com.hhly.lyygame.data.model.GoodsInfo;
import com.hhly.lyygame.presentation.view.BaseLoadingView;
import com.hhly.lyygame.presentation.view.BasePresenter;
import com.hhly.lyygame.presentation.view.BaseView;

import java.util.List;

/**
 * Created by ${HELY} on 17/2/26.
 * 邮箱：heli.lixiong@gmail.com
 */

public class MallListContract {

    interface Presenter extends BasePresenter {
        void getGoodsList(boolean refresh, int pageNo, int pageSize, int type);
        void cancelGoods(int goodsId);
    }

    interface View extends BaseView<Presenter>, BaseLoadingView {
        void showGoodsList(List<GoodsInfo> list);
        void onFailure();
        void cancelGoodsSuccess(int goodsId);
        void onEmptyView();
    }

}
