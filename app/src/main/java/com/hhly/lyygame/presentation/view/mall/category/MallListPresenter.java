package com.hhly.lyygame.presentation.view.mall.category;

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
import com.hhly.lyygame.data.net.protocol.user.State;
import com.hhly.lyygame.data.repository.GoodsRepository;
import com.hhly.lyygame.presentation.utils.CollectionUtil;
import com.hhly.lyygame.presentation.utils.TelephonyUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

import static com.hhly.lyygame.presentation.view.mall.category.MallCategoryListFragment.CATEGORY_COUPON_MAT;
import static com.hhly.lyygame.presentation.view.mall.category.MallCategoryListFragment.CATEGORY_COUPON_VIR;
import static com.hhly.lyygame.presentation.view.mall.category.MallCategoryListFragment.CATEGORY_SCORE_MAT;
import static com.hhly.lyygame.presentation.view.mall.category.MallCategoryListFragment.CATEGORY_SCORE_VIR;

/**
 * Created by ${HELY} on 17/2/26.
 * 邮箱：heli.lixiong@gmail.com
 */

public class MallListPresenter implements MallListContract.Presenter {

    private final MallListContract.View mView;
    private final GoodsRepository mGoodsRepository;
    private final GoodsApi mGoodsApi;

    public MallListPresenter(MallListContract.View view) {
        mView = view;
        mGoodsRepository = new GoodsRepository();
        mGoodsApi = RetrofitManager.getInstance(ApiType.GOODS_API).getGoodsApi();
        mView.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void getGoodsList(boolean refresh ,final int pageNo, int pageSize,final int type) {

        GoodsListReq req = new GoodsListReq();
        req.setGoodsListCountry(0);
        req.setGoodsListEnable(0);
        req.setPageNo(pageNo);
        req.setDataSize(pageSize);
        req.setUserId(AccountManager.getInstance().getUserId());
        req.setGoodsListPlatform(TelephonyUtil.getOsTypeInt());

        mGoodsRepository.getGoodsList(req.params())
                .compose(RxUtil.<GoodsListResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<GoodsListResp>bindToLife())
                .compose(RetrofitManager.<GoodsListResp>composeBackpressureError())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<GoodsListResp>() {
                    @Override
                    public boolean test(@NonNull GoodsListResp resp) throws Exception {

                        if (resp == null || !resp.isOk()){
                            mView.onFailure();
                        }else if (pageNo == 1 && CollectionUtil.isEmpty(resp.getGoods())){
                            mView.onEmptyView();
                        }
                        return resp != null && resp.isOk() && CollectionUtil.isNotEmpty(resp.getGoods());
                    }
                })
                .observeOn(Schedulers.newThread())
                .map(new Function<GoodsListResp, List<GoodsInfo>>() {
                    @Override
                    public List<GoodsInfo> apply(@NonNull GoodsListResp goodsListResp) throws Exception {

                        List<GoodsInfo> malllist = new ArrayList<>();

                        for (GoodsInfo goods : goodsListResp.getGoods()) {

                            switch (type) {
                                case CATEGORY_COUPON_VIR:
                                    if (goods.getNeedScoreId() == State.MallType.COUPON && goods.getType() != 1) {
                                        malllist.add(goods);
                                    }
                                    break;
                                case CATEGORY_COUPON_MAT:
                                    if (goods.getNeedScoreId() == State.MallType.COUPON && goods.getType() == 1) {
                                        malllist.add(goods);
                                    }
                                    break;
                                case CATEGORY_SCORE_VIR:
                                    if (goods.getNeedScoreId() == State.MallType.SCORE && goods.getType() != 1) {
                                        malllist.add(goods);
                                    }
                                    break;
                                case CATEGORY_SCORE_MAT:
                                    if (goods.getNeedScoreId() == State.MallType.SCORE && goods.getType() == 1) {
                                        malllist.add(goods);
                                    }
                                    break;
                                default:
                                    break;
                            }
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
                    public void onNext(List<GoodsInfo> list) {
                            mView.showGoodsList(list);
                    }
                });
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

}
