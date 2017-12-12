package com.hhly.lyygame.presentation.view.favourite;

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
import com.hhly.lyygame.data.net.protocol.goods.DelStoreGoodsReq;
import com.hhly.lyygame.data.net.protocol.goods.DelStoreGoodsResp;
import com.hhly.lyygame.data.net.protocol.goods.GoodsExchangeReq;
import com.hhly.lyygame.data.net.protocol.goods.GoodsExchangeResp;
import com.hhly.lyygame.data.net.protocol.goods.StoreGoodsReq;
import com.hhly.lyygame.data.net.protocol.goods.StoreGoodsResp;
import com.hhly.lyygame.data.net.protocol.user.GetUserInfoResp;
import com.hhly.lyygame.presentation.utils.CollectionUtil;
import com.hhly.lyygame.presentation.view.ToastUtil;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by HELY on 17/2/9.
 * 邮箱：heli.lixiong@gmail.com
 */

public class FavouritePresenter implements FavouriteContract.Presenter {

    private final FavouriteContract.View mView;
    private final GoodsApi mGoodsApi;
    private final UserApi mUserApi;



    FavouritePresenter(FavouriteContract.View view) {
        mView = view;
        mView.setPresenter(this);
        mGoodsApi = RetrofitManager.getInstance(ApiType.GOODS_API).getGoodsApi();
        mUserApi = RetrofitManager.getInstance(ApiType.USER_API).getUserApi();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void getStoreGoods(String userId) {
        StoreGoodsReq req = new StoreGoodsReq();
        req.setCountry(0);
        req.setEnable(0);
        req.setUserId(userId);

        mGoodsApi.getStoreGoods(req.params())
                .compose(RxUtil.<StoreGoodsResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<StoreGoodsResp>bindToLife())
                .compose(RetrofitManager.<StoreGoodsResp>composeBackpressureOther())
                .compose(RetrofitManager.<StoreGoodsResp>composeBackpressureError())
                .filter(new Predicate<StoreGoodsResp>() {
                    @Override
                    public boolean test(@NonNull StoreGoodsResp storeGoodsResp) throws Exception {
                        if (storeGoodsResp == null || !storeGoodsResp.isOk() || CollectionUtil.isEmpty(storeGoodsResp.getStoreGoods())) {
                            mView.onStoreGoodsFailure();
                        }
                        return storeGoodsResp != null && storeGoodsResp.isOk() && CollectionUtil.isNotEmpty(storeGoodsResp.getStoreGoods());
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Function<StoreGoodsResp, List<FavouriteAdapter.FavouriteItemData>>() {
                    @Override
                    public List<FavouriteAdapter.FavouriteItemData> apply(@NonNull StoreGoodsResp storeGoodsResp) throws Exception {
                        List<FavouriteAdapter.FavouriteItemData> favouriteList = new ArrayList<FavouriteAdapter.FavouriteItemData>();

                        for (StoreGoodsResp.StoreGoodsBean goodsBean : storeGoodsResp.getStoreGoods()) {
                            if (goodsBean.getEnable() == 0){
                                FavouriteAdapter.FavouriteItemData itemData = new FavouriteAdapter.FavouriteItemData(goodsBean);
                                favouriteList.add(itemData);
                            }
                        }

                        return favouriteList;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<List<FavouriteAdapter.FavouriteItemData>>() {
                    @Override
                    protected void hideDialog() {
                        mView.onStoreGoodsFailure();
                    }

                    @Override
                    protected void showDialog() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        mView.showMsg(e.message);
                    }

                    @Override
                    public void onNext(List<FavouriteAdapter.FavouriteItemData> favouriteItemDatas) {
                        mView.showStoreGoods(favouriteItemDatas);
                    }
                });
    }

    @Override
    public void delGoods(final int id) {
        DelStoreGoodsReq req = new DelStoreGoodsReq();
        req.setStoreGoodsId(id);

        mGoodsApi.delStoreGoods(req.params())
                .compose(RxUtil.<DelStoreGoodsResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<DelStoreGoodsResp>bindToLife())
                .compose(RetrofitManager.<DelStoreGoodsResp>composeBackpressureOther())
                .compose(RetrofitManager.<DelStoreGoodsResp>composeBackpressureError())
                .subscribe(new BaseSubscriber<DelStoreGoodsResp>() {
                    @Override
                    protected void hideDialog() {
                        mView.dismissLoading();
                    }

                    @Override
                    protected void showDialog() {
                        mView.showLoading();
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        mView.delGoodsFailure();
                    }

                    @Override
                    public void onNext(DelStoreGoodsResp delStoreGoodsResp) {
                        mView.dismissLoading();
                        if (delStoreGoodsResp != null && delStoreGoodsResp.isOk()) {
                            mView.delGoodsSuccess(id);
                        } else {
                            mView.delGoodsFailure();
                        }
                    }
                });
    }

    @Override
    public void getUserInfo(final List<StoreGoodsResp.StoreGoodsBean> goodsInfos) {

        BaseReq req = new BaseReq();
        mUserApi.getUserInfo(req.params())
                .compose(RxUtil.<GetUserInfoResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<GetUserInfoResp>bindToLife())
                .compose(RetrofitManager.<GetUserInfoResp>composeBackpressureOther())
                .compose(RetrofitManager.<GetUserInfoResp>composeBackpressureError())
                .filter(new Predicate<GetUserInfoResp>() {
                    @Override
                    public boolean test(@NonNull GetUserInfoResp getUserInfoResp) throws Exception {
                        if (getUserInfoResp == null || !getUserInfoResp.isOk()) {
                            mView.getUserInfoFailure(getUserInfoResp == null ? "" : getUserInfoResp.getMsg());
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
                        mView.getUserInfoFailure("");
                    }

                    @Override
                    public void onNext(UserInfo userInfo) {
                        mView.getUserInfoSuccess(goodsInfos);
                    }
                });

    }

    @Override
    public void exchangeGoods(final GoodsExchangeReq.ExGoods exGoods) {
        GoodsExchangeReq req = new GoodsExchangeReq(exGoods);

        mGoodsApi.exchangeGoods(req.params())
                .compose(RxUtil.<GoodsExchangeResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<GoodsExchangeResp>bindToLife())
                .compose(RetrofitManager.<GoodsExchangeResp>composeBackpressureOther())
                .compose(RetrofitManager.<GoodsExchangeResp>composeBackpressureError())
                .subscribe(new BaseSubscriber<GoodsExchangeResp>() {
                    @Override
                    protected void hideDialog() {

                    }

                    @Override
                    protected void showDialog() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        mView.exchangeGoodsFailure("");
                    }

                    @Override
                    public void onNext(GoodsExchangeResp goodsExchangeResp) {
                        if (goodsExchangeResp != null && goodsExchangeResp.isOk()) {
                            ToastUtil.showTip(App.getContext(), R.string.lyy_game_exchange_success);
                            mView.exchangeGoodsSuccess(exGoods.getId());
                        } else {
                            mView.exchangeGoodsFailure(goodsExchangeResp.getMsg());
                        }
                    }
                });

    }

}
