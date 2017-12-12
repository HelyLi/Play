package com.hhly.lyygame.presentation.view.mall;

import com.hhly.lyygame.data.model.GoodsInfo;
import com.hhly.lyygame.data.net.protocol.goods.GoodsExchangeReq;
import com.hhly.lyygame.presentation.view.BaseLoadingView;
import com.hhly.lyygame.presentation.view.BasePresenter;
import com.hhly.lyygame.presentation.view.BaseView;

import java.util.List;

/**
 * Created by ${HELY} on 17/2/5.
 * 邮箱：heli.lixiong@gmail.com
 */

public interface MallContract {

    interface Presenter extends BasePresenter{
        void getGoodsList(boolean refresh, int type);
        void cancelGoods(int goodsId);
    }

    interface View extends BaseView<Presenter>, BaseLoadingView {
        void showGoodsList(List<GoodsInfo> list);
        void onFailure();
        void cancelGoodsSuccess(int goodsId);
    }

    interface MallOptPresenter extends BasePresenter{
        void addStoreGoods(int goodsId);
        void exchangeGoods(GoodsExchangeReq.ExGoods exGoods);
    }

    interface MallOptView extends BaseView<MallOptPresenter>{
        void addStoreSuccess();
        void addStoreFailure(String msg);

        void exchangeGoodsSuccess();
        void exchangeGoodsFailure(String msg);
    }

}
