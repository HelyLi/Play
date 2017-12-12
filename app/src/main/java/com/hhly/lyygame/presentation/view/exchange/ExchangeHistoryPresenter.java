package com.hhly.lyygame.presentation.view.exchange;

import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.GoodsApi;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.client.BaseSubscriber;
import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.goods.GoodsExchangeHistoryReq;
import com.hhly.lyygame.data.net.protocol.goods.GoodsExchangeHistoryResp;
import com.hhly.lyygame.presentation.utils.CollectionUtil;
import com.orhanobut.logger.Logger;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Predicate;


/**
 * 兑换记录
 * Created by Simon on 2016/12/10.
 */

public class ExchangeHistoryPresenter implements ExchangeHistoryContract.Presenter {

    private final ExchangeHistoryContract.View mView;
    private final GoodsApi mGoodsApi;

    public ExchangeHistoryPresenter(ExchangeHistoryContract.View view) {
        mView = view;
        mGoodsApi = RetrofitManager.getInstance(ApiType.GOODS_API).getGoodsApi();
        this.mView.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void getExchangeHistory(String userId,final int pageNo, int pageSize) {
        final GoodsExchangeHistoryReq req = new GoodsExchangeHistoryReq();
        req.setUserId(userId);
        req.setCountry("0");
        req.setDataSize(pageSize);
        req.setPageNo(pageNo);

        mGoodsApi.getExchangeGoodsHistory(req.params())
                .compose(RxUtil.<GoodsExchangeHistoryResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<GoodsExchangeHistoryResp>bindToLife())
                .compose(RetrofitManager.<GoodsExchangeHistoryResp>composeBackpressureOther())
                .compose(RetrofitManager.<GoodsExchangeHistoryResp>composeBackpressureError())
                .filter(new Predicate<GoodsExchangeHistoryResp>() {
                    @Override
                    public boolean test(@NonNull GoodsExchangeHistoryResp resp) throws Exception {

                        Logger.d("GoodsExchangeHistoryResp="+ resp);

                        if (resp == null || !resp.isOk()){
                            mView.onFailure();
                        }else if (pageNo == 1 && CollectionUtil.isEmpty(resp.getList())){
                            mView.onEmptyView();
                        }
                        return resp != null && resp.isOk() && CollectionUtil.isNotEmpty(resp.getList());
                    }
                })
                .subscribe(new BaseSubscriber<GoodsExchangeHistoryResp>() {
                    @Override
                    protected void hideDialog() {

                    }

                    @Override
                    protected void showDialog() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        mView.onFailure();
                    }

                    @Override
                    public void onNext(GoodsExchangeHistoryResp resp) {

                            mView.showExchangeHistory(resp.getList(), resp.getTotal());

                    }
                });
    }
}
