package com.hhly.lyygame.presentation.view.order;

import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.App;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.db.entry.UserInfo;
import com.hhly.lyygame.data.db.manager.AccountManager;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.GameApi;
import com.hhly.lyygame.data.net.GoodsApi;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.UserApi;
import com.hhly.lyygame.data.net.client.BaseSubscriber;
import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.BaseReq;
import com.hhly.lyygame.data.net.protocol.game.ExchangeGiftBagReq;
import com.hhly.lyygame.data.net.protocol.game.ExchangeGiftBagResp;
import com.hhly.lyygame.data.net.protocol.game.SignGiftBagReq;
import com.hhly.lyygame.data.net.protocol.game.SignGiftBagResp;
import com.hhly.lyygame.data.net.protocol.goods.GoodsInfoReq;
import com.hhly.lyygame.data.net.protocol.goods.GoodsInfoResp;
import com.hhly.lyygame.data.net.protocol.user.GetUserInfoResp;
import com.hhly.lyygame.presentation.utils.NetworkUtil;
import com.hhly.lyygame.presentation.view.ToastUtil;
import com.orhanobut.logger.Logger;

import org.reactivestreams.Publisher;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ${HELY} on 17/2/18.
 * 邮箱：heli.lixiong@gmail.com
 */

public class OrderGiftPresenterImpl implements OrderContract.OrderGiftPresenter {

    private final OrderContract.OrderGiftView mView;
    private final GameApi mGameApi;
    private final UserApi mUserApi;
    private final GoodsApi mGoodsApi;

    public OrderGiftPresenterImpl(OrderContract.OrderGiftView view) {
        mView = view;
        mGoodsApi = RetrofitManager.getInstance(ApiType.GOODS_API).getGoodsApi();
        mGameApi = RetrofitManager.getInstance(ApiType.GAME_API).getGameApi();
        mUserApi = RetrofitManager.getInstance(ApiType.USER_API).getUserApi();
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
                        mView.showMsg("");
                        mView.onGoodsInfoFailure();
                    }

                    @Override
                    public void onNext(GoodsInfoResp goodsInfoResp) {
                        if (goodsInfoResp != null && goodsInfoResp.isOk()){
                            mView.showGoodsInfo(goodsInfoResp.getGoods());
                        }else {
                            mView.showMsg(goodsInfoResp != null ? goodsInfoResp.getMsg(): "");
                            mView.onGoodsInfoFailure();
                        }
                    }
                });

    }

    @Override
    public void exchangeGiftBag(int giftId) {
        if (!NetworkUtil.isAvailable(App.getContext())) {
            ToastUtil.showTip(App.getContext(), R.string.lyy_network_notwork);
            return;
        }
        mView.showLoading();
        ExchangeGiftBagReq req = new ExchangeGiftBagReq();
        req.setGiftBag(giftId);
        req.setUserId(AccountManager.getInstance().getUserId());

        mGameApi.exchangeGiftBag(req.params())
                .compose(RxUtil.<ExchangeGiftBagResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(RetrofitManager.<ExchangeGiftBagResp>composeBackpressureOther())
                .compose(RetrofitManager.<ExchangeGiftBagResp>composeBackpressureError())
                .compose(mView.<ExchangeGiftBagResp>bindToLife())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<ExchangeGiftBagResp>() {
                    @Override
                    public boolean test(@NonNull ExchangeGiftBagResp getUserInfoResp) throws Exception {
                        if (getUserInfoResp == null || !getUserInfoResp.isOk()) {
                            mView.showExchangeGiftBagFailure(getUserInfoResp == null ? "系统错误" : getUserInfoResp.getMsg());
                        }
                        return getUserInfoResp != null && getUserInfoResp.isOk();
                    }
                })
                .map(new Function<ExchangeGiftBagResp, BaseReq>() {
                    @Override
                    public BaseReq apply(@NonNull ExchangeGiftBagResp exchangeGiftBagResp) throws Exception {
                        BaseReq req = new BaseReq();
                        return req;
                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Function<BaseReq, Publisher<GetUserInfoResp>>() {
                    @Override
                    public Publisher<GetUserInfoResp> apply(@NonNull BaseReq baseReq) throws Exception {
                        return mUserApi.getUserInfo(baseReq.params());
                    }
                })
                .filter(new Predicate<GetUserInfoResp>() {
                    @Override
                    public boolean test(@NonNull GetUserInfoResp getUserInfoResp) throws Exception {
                        return getUserInfoResp != null && getUserInfoResp.isOk();
                    }
                })
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
                        mView.dismissLoading();
                    }

                    @Override
                    protected void showDialog() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        Logger.d(e.message + e.code);
                    }

                    @Override
                    public void onNext(UserInfo userInfo) {
                        mView.dismissLoading();
                        mView.showExchangeGiftBag();
                    }
                });

    }
}
