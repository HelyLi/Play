package com.hhly.lyygame.presentation.view.mall;

import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.App;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.db.entry.UserInfo;
import com.hhly.lyygame.data.db.manager.AccountManager;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.GoodsApi;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.UserApi;
import com.hhly.lyygame.data.net.client.BaseSubscriber;
import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.BaseReq;
import com.hhly.lyygame.data.net.protocol.goods.AddStoreGoodsReq;
import com.hhly.lyygame.data.net.protocol.goods.AddStoreGoodsResp;
import com.hhly.lyygame.data.net.protocol.goods.GoodsExchangeReq;
import com.hhly.lyygame.data.net.protocol.goods.GoodsExchangeResp;
import com.hhly.lyygame.data.net.protocol.user.GetUserInfoResp;
import com.hhly.lyygame.presentation.view.ToastUtil;
import com.orhanobut.logger.Logger;

import org.reactivestreams.Publisher;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ${HELY} on 17/2/28.
 * 邮箱：heli.lixiong@gmail.com
 */

public class MallOptPresenterImpl implements MallContract.MallOptPresenter {

    private final UserApi mUserApi;
    private MallContract.MallOptView mView;
    private final GoodsApi mGoodsApi;

    public MallOptPresenterImpl(MallContract.MallOptView view) {
        this.mView = view;
        mGoodsApi = RetrofitManager.getInstance(ApiType.GOODS_API).getGoodsApi();
        mUserApi = RetrofitManager.getInstance(ApiType.USER_API).getUserApi();
        this.mView.setPresenter(this);
    }

    @Override
    public void addStoreGoods(int goodsId) {
        AddStoreGoodsReq req = new AddStoreGoodsReq();
        req.setGoodsId(goodsId);
        req.setUserId(AccountManager.getInstance().getUserId());

        mGoodsApi.addStoreGoods(req.params())
                .compose(RxUtil.<AddStoreGoodsResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<AddStoreGoodsResp>bindToLife())
                .compose(RetrofitManager.<AddStoreGoodsResp>composeBackpressureError())
                .subscribe(new BaseSubscriber<AddStoreGoodsResp>() {
                    @Override
                    protected void hideDialog() {

                    }

                    @Override
                    protected void showDialog() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        mView.addStoreFailure(e.message);
                    }

                    @Override
                    public void onNext(AddStoreGoodsResp addStoreGoodsResp) {
                        if (addStoreGoodsResp != null && addStoreGoodsResp.isOk()) {
                            ToastUtil.showTip(App.getContext(), R.string.lyy_game_add_store_success);
                            mView.addStoreSuccess();
                        } else {
                            mView.addStoreFailure(addStoreGoodsResp != null ? addStoreGoodsResp.getMsg() : "");
                        }
                    }
                });
    }

    @Override
    public void exchangeGoods(GoodsExchangeReq.ExGoods exGoods) {
        GoodsExchangeReq req = new GoodsExchangeReq(exGoods);

        mGoodsApi.exchangeGoods(req.params())
                .compose(RxUtil.<GoodsExchangeResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<GoodsExchangeResp>bindToLife())
                .compose(RetrofitManager.<GoodsExchangeResp>composeBackpressureError())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<GoodsExchangeResp>() {
                    @Override
                    public boolean test(@NonNull GoodsExchangeResp goodsExchangeResp) throws Exception {
                        if (goodsExchangeResp == null || !goodsExchangeResp.isOk()) {
                            mView.exchangeGoodsFailure(goodsExchangeResp == null ? "" : goodsExchangeResp.getMsg());
                        }
                        return goodsExchangeResp != null && goodsExchangeResp.isOk();
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Function<GoodsExchangeResp, BaseReq>() {
                    @Override
                    public BaseReq apply(@NonNull GoodsExchangeResp goodsExchangeResp) throws Exception {
                        BaseReq req = new BaseReq();
                        req.setToken(AccountManager.getInstance().getToken());
                        return req;
                    }
                })
                .flatMap(new Function<BaseReq, Publisher<GetUserInfoResp>>() {
                    @Override
                    public Publisher<GetUserInfoResp> apply(@NonNull BaseReq req) throws Exception {
                        return mUserApi.getUserInfo(req.params());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<GetUserInfoResp>() {
                    @Override
                    public boolean test(@NonNull GetUserInfoResp getUserInfoResp) throws Exception {
                        if (getUserInfoResp == null || !getUserInfoResp.isOk()) {
                            mView.exchangeGoodsSuccess();
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
                        mView.exchangeGoodsFailure(e.message);
                    }

                    @Override
                    public void onNext(UserInfo info) {
                        mView.exchangeGoodsSuccess();
                    }
                });
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
}
