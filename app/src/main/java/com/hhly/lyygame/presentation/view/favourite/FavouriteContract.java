package com.hhly.lyygame.presentation.view.favourite;

import com.hhly.lyygame.data.net.protocol.goods.GoodsExchangeReq;
import com.hhly.lyygame.data.net.protocol.goods.StoreGoodsResp;
import com.hhly.lyygame.presentation.view.BaseLoadingView;
import com.hhly.lyygame.presentation.view.BasePresenter;
import com.hhly.lyygame.presentation.view.BaseView;

import java.util.List;

/**
 * Created by ${HELY} on 17/2/9.
 * 邮箱：heli.lixiong@gmail.com
 */

public interface FavouriteContract {

    interface Presenter extends BasePresenter {
        void getStoreGoods(String userId);
        void exchangeGoods(GoodsExchangeReq.ExGoods exGoods);
        void delGoods(int goodsId);
        void getUserInfo(List<StoreGoodsResp.StoreGoodsBean> goodsInfos);
    }

    interface View extends BaseView<FavouriteContract.Presenter>, BaseLoadingView {
        void showStoreGoods(List<FavouriteAdapter.FavouriteItemData> storeGoodsBeanList);
        void onStoreGoodsFailure();
        void exchangeGoodsSuccess(int goodId);
        void exchangeGoodsFailure(String msg);
        void delGoodsSuccess(int goodsId);
        void delGoodsFailure();
        void getUserInfoSuccess(List<StoreGoodsResp.StoreGoodsBean> goodsInfos);
        void getUserInfoFailure(String msg);
    }

}
