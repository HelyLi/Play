package com.hhly.lyygame.presentation.view.mall;

import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.data.db.manager.AccountManager;
import com.hhly.lyygame.data.model.GoodsInfo;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.GoodsApi;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.client.BaseSubscriber;
import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.BaseResp;
import com.hhly.lyygame.data.net.protocol.goods.CancelStoreGoodsReq;
import com.hhly.lyygame.data.net.protocol.goods.GoodsListReq;
import com.hhly.lyygame.data.net.protocol.goods.GoodsListResp;
import com.hhly.lyygame.data.repository.GoodsRepository;
import com.hhly.lyygame.presentation.utils.CollectionUtil;
import com.hhly.lyygame.presentation.utils.TelephonyUtil;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by ${HELY} on 17/2/5.
 * 邮箱：heli.lixiong@gmail.com
 */

public class MallPresenter implements MallContract.Presenter {

    private MallContract.View mView;
    private final GoodsRepository mGoodsRepository;
    private final GoodsApi mGoodsApi;

    public MallPresenter(MallContract.View view) {
        this.mView = view;
        mGoodsRepository = new GoodsRepository();
        mGoodsApi = RetrofitManager.getInstance(ApiType.GOODS_API).getGoodsApi();
        this.mView.setPresenter(this);
    }

    @Override
    public void cancelGoods(final int goodsId) {

        CancelStoreGoodsReq req = new CancelStoreGoodsReq();
        req.setUserId(AccountManager.getInstance().getUserId());
        req.setGoodsId(goodsId);

        mGoodsApi.cancelStoreGoods(req.params())
                .compose(RxUtil.<BaseResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<BaseResp>bindToLife())
                .subscribe(new BaseSubscriber<BaseResp>() {
                    @Override
                    protected void hideDialog() {

                    }

                    @Override
                    protected void showDialog() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {

                    }

                    @Override
                    public void onNext(BaseResp baseResp) {
                        if (baseResp != null && baseResp.isOk()){
                            mView.cancelGoodsSuccess(goodsId);
                        }
                    }
                });

    }

    @Override
    public void getGoodsList(boolean refresh,final int needScoreId) {
        GoodsListReq req = new GoodsListReq();
        req.setGoodsListCountry(0);
        req.setGoodsListEnable(0);
        req.setPageNo(1);
        req.setDataSize(50);
        req.setGoodsListPlatform(TelephonyUtil.getOsTypeInt());
        req.setUserId(AccountManager.getInstance().getUserId());

        mGoodsRepository.getGoodsList(req.params())
                .compose(RxUtil.<GoodsListResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<GoodsListResp>bindToLife())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<GoodsListResp>() {
                    @Override
                    public boolean test(@NonNull GoodsListResp goodsListResp) throws Exception {
                        boolean predicate = false;
                        if (goodsListResp != null && goodsListResp.isOk() && CollectionUtil.isNotEmpty(goodsListResp.getGoods())){
                            predicate = true;
                        }else {
                            mView.onFailure();
                        }
                        return predicate;
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Function<GoodsListResp, List<GoodsInfo>>() {
                    @Override
                    public List<GoodsInfo> apply(@NonNull GoodsListResp resp) throws Exception {

                        List<GoodsInfo> malllist = new ArrayList<>();

                        for (GoodsInfo goods : resp.getGoods()){
                            if (goods.getNeedScoreId() == needScoreId){
                                malllist.add(goods);
                            }
//                            if (malllist.size() >= 4){
//                                break;
//                            }
                        }

                        return malllist;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<List<GoodsInfo>>() {
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
                    public void onNext(List<GoodsInfo> goodsInfos) {
                        Logger.d("goodsInfos.size=" + goodsInfos.size());
                        mView.showGoodsList(goodsInfos);
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
