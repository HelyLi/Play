package com.hhly.lyygame.presentation.view.order;

import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.data.db.entry.UserInfo;
import com.hhly.lyygame.data.db.manager.AccountManager;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.GoodsApi;
import com.hhly.lyygame.data.net.PayApi;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.UserApi;
import com.hhly.lyygame.data.net.client.BaseSubscriber;
import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.BaseReq;
import com.hhly.lyygame.data.net.protocol.goods.GoodsExchangeReq;
import com.hhly.lyygame.data.net.protocol.goods.GoodsExchangeResp;
import com.hhly.lyygame.data.net.protocol.goods.GoodsInfoReq;
import com.hhly.lyygame.data.net.protocol.goods.GoodsInfoResp;
import com.hhly.lyygame.data.net.protocol.user.GetUserInfoResp;
import com.orhanobut.logger.Logger;

import org.reactivestreams.Publisher;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
//import rx.subscriptions.CompositeSubscription;

/**
 * Created by ${HELY} on 17/2/18.
 * 邮箱：heli.lixiong@gmail.com
 */

public class OrderCardPresenterImpl implements OrderContract.OrderCardPresenter {

    private final OrderContract.OrderCardView mView;
    private final GoodsApi mGoodsApi;
    private final UserApi mUserApi;
    private final PayApi mPayApi;

    public OrderCardPresenterImpl(OrderContract.OrderCardView view) {
        mView = view;
        mGoodsApi = RetrofitManager.getInstance(ApiType.GOODS_API).getGoodsApi();
        mUserApi = RetrofitManager.getInstance(ApiType.USER_API).getUserApi();
        mPayApi = RetrofitManager.getInstance(ApiType.PAY_API).getPayApi();
        mView.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void getGoodsInfo(int goodsId) {
        GoodsInfoReq req = new GoodsInfoReq();
        req.setGoodsId(goodsId);

        mGoodsApi.getGoodsInfo(req.params())
                .compose(RxUtil.<GoodsInfoResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                //.compose(RetrofitManager.<GoodsInfoResp>composeBackpressureError())
                .compose(mView.<GoodsInfoResp>bindToLife())
                .subscribe(new BaseSubscriber<GoodsInfoResp>() {
                    @Override
                    protected void hideDialog() {

                    }

                    @Override
                    protected void showDialog() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        mView.onGoodsInfoFailure();
                    }

                    @Override
                    public void onNext(GoodsInfoResp goodsInfoResp) {
                        if (goodsInfoResp != null && goodsInfoResp.isOk()) {
                            mView.showGoodsInfo(goodsInfoResp.getGoods());
                        } else {
                            mView.showMsg(goodsInfoResp != null ? goodsInfoResp.getMsg() : "");
                            mView.onGoodsInfoFailure();
                        }
                    }
                });

    }

    @Override
    public void exchangeGoods(GoodsExchangeReq.ExGoods exGoods) {
        GoodsExchangeReq req = new GoodsExchangeReq(exGoods);

        mGoodsApi.exchangeGoods(req.params())
                .compose(RxUtil.<GoodsExchangeResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                //.compose(RetrofitManager.<GoodsExchangeResp>composeBackpressureError())
                .compose(mView.<GoodsExchangeResp>bindToLife())
                .filter(new Predicate<GoodsExchangeResp>() {
                    @Override
                    public boolean test(@NonNull GoodsExchangeResp goodsExchangeResp) throws Exception {
                        if (goodsExchangeResp == null) {
                            mView.onExchangeFailure("");
                        } else if (!goodsExchangeResp.isOk()) {
                            mView.onExchangeFailure(goodsExchangeResp.getMsg());
                        }
                        return goodsExchangeResp != null && goodsExchangeResp.isOk();
                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Function<GoodsExchangeResp, Publisher<GetUserInfoResp>>() {
                    @Override
                    public Publisher<GetUserInfoResp> apply(@NonNull GoodsExchangeResp goodsExchangeResp) throws Exception {
                        BaseReq req = new BaseReq();
                        return mUserApi.getUserInfo(req.params());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<GetUserInfoResp>() {
                    @Override
                    public boolean test(@NonNull GetUserInfoResp getUserInfoResp) throws Exception {
                        if (getUserInfoResp == null || !getUserInfoResp.isOk()) {
                            mView.showExchangeGoods();
                        }
                        return getUserInfoResp != null && getUserInfoResp.isOk();
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Function<GetUserInfoResp, UserInfo>() {
                    @Override
                    public UserInfo apply(@NonNull GetUserInfoResp getUserInfoResp) throws Exception {

                        UserInfo extra = getUserInfoResp.getUser();

                        extra.setId(getUserInfoResp.getUser().getId());
                        extra.setJf(getUserInfoResp.getJf());
                        extra.setBindFlag(getUserInfoResp.getBindFlag());
                        extra.setLyb(getUserInfoResp.getLyb());
                        extra.setLyq(getUserInfoResp.getLyq());
                        extra.setPaypwdFlag(getUserInfoResp.getPaypwdFlag());
                        extra.setSafeLevel(getUserInfoResp.getSafeLevel());
                        extra.setToken(AccountManager.getInstance().getToken());
                        AccountManager.getInstance().saveUserInfo(extra);
                        Logger.d("login.saveUserInfo");
                        return extra;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<UserInfo>() {
                    @Override
                    protected void hideDialog() {

                    }

                    @Override
                    protected void showDialog() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        mView.onExchangeFailure("");
                    }

                    @Override
                    public void onNext(UserInfo userInfo) {
                        mView.showExchangeGoods();
                    }
                });

    }
}
