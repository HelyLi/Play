package com.hhly.lyygame.presentation.view.order;

import com.hhly.lyygame.data.model.GoodsInfo;
import com.hhly.lyygame.data.net.protocol.goods.GoodsExchangeReq;
import com.hhly.lyygame.data.net.protocol.user.OrderQueryResp;
import com.hhly.lyygame.data.net.protocol.user.PayResp;
import com.hhly.lyygame.data.net.protocol.user.QueryPayResp;
import com.hhly.lyygame.data.net.protocol.user.UserAddressResp;
import com.hhly.lyygame.presentation.view.BaseLoadingView;
import com.hhly.lyygame.presentation.view.BasePresenter;
import com.hhly.lyygame.presentation.view.BaseView;
import com.tencent.mm.sdk.modelpay.PayReq;

import java.util.List;

/**
 * Created by ${HELY} on 17/2/18.
 * 邮箱：heli.lixiong@gmail.com
 */

public interface OrderContract {

    interface OrderCardPresenter extends BasePresenter {
        void getGoodsInfo(int goodsIds);
        void exchangeGoods(GoodsExchangeReq.ExGoods exGoods);
    }

    interface OrderCardView extends BaseView<OrderContract.OrderCardPresenter>, BaseLoadingView {
        void showGoodsInfo(GoodsInfo goodsInfoBean);
        void showExchangeGoods();
        void onGoodsInfoFailure();
        void onExchangeFailure(String msg);
    }

    interface OrderPresenter extends BasePresenter {
        void getUserAddress(String userId);
        void getGoodsInfo(int goodsId , int num);
        void exchangeGoods(GoodsExchangeReq.ExGoods exGoods);
    }

    interface OrderView extends BaseView<OrderContract.OrderPresenter>, BaseLoadingView {
        void showAddress(List<UserAddressResp.AddressBean> addressBeanList);
        void onAddressFailure();
        void showGoodsInfo(GoodsInfo goodsInfoBean);
        void onGoodsInfoFailure();
        void showExchangeGoods();
        void onExchangeFailure(String msg);
    }

    interface OrderGiftPresenter extends BasePresenter {
        void exchangeGiftBag(int giftId);

        void getGoodsInfo(int giftId);
    }

    interface OrderGiftView extends BaseView<OrderContract.OrderGiftPresenter>, BaseLoadingView {
        void showExchangeGiftBag();
        void showExchangeGiftBagFailure(String msg);

        void onGoodsInfoFailure();
        void showGoodsInfo(GoodsInfo goodsInfoBean);
    }

    interface PayOrderPresenter extends BasePresenter {
        void getOrderInfoForShop();
        void getPayOrder(double money, long game,String gameName,int type, int goodsId, int shopType, int addressId, String phone, String payToAccount);
    }

    interface PayOrderView extends BaseView<OrderContract.PayOrderPresenter>, BaseLoadingView {
        void showPayInfo(PayResp payResp);

        void getOrderSuccess(PayReq req);
        void getOrderFailure(String msg);
        void getAliOrderSuccess(String orderInfo);

        void getOrderInfoFailure(String msg);
        void getOrderInfoSuccess(String orderNo);
    }

    interface PayOrderDetailsPresenter extends BasePresenter {
        void getOrderDetail(String pre);
    }

    interface PayOrderDetailsView extends BaseView<OrderContract.PayOrderDetailsPresenter>, BaseLoadingView {
        void payQuerySuccess(QueryPayResp resp);

        void payQueryFailure(String msg);
    }
}
