package com.hhly.lyygame.presentation.view.banner;

import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.data.net.client.BaseSubscriber;
import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.banner.GoodsBannerReq;
import com.hhly.lyygame.data.net.protocol.banner.GoodsBannerResp;
import com.hhly.lyygame.data.net.protocol.banner.HomeBannerReq;
import com.hhly.lyygame.data.net.protocol.banner.HomeBannerResp;
import com.hhly.lyygame.data.repository.BannerRepository;
import com.hhly.lyygame.presentation.utils.CollectionUtil;
import com.hhly.lyygame.presentation.utils.TelephonyUtil;
import com.orhanobut.logger.Logger;


/**
 * Created by Simon on 16/9/6.
 */
public class BannerPresenter implements BannerContract.Presenter {

    private final BannerContract.View mView;

    private BannerRepository mBannerRepository;

    public BannerPresenter(BannerContract.View view) {
        mView = view;
        mView.setPresenter(this);
        mBannerRepository = new BannerRepository();
    }

    @Override
    public void loadHomeBanner(boolean refresh) {

        HomeBannerReq req = new HomeBannerReq();
        req.setPlatformTerminal(TelephonyUtil.getOsTypeInt());

        mBannerRepository.homeBanner(req.params())
                .compose(RxUtil.<HomeBannerResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                //.compose(RetrofitManager.<HomeBannerResp>composeBackpressureError())
                .subscribe(new BaseSubscriber<HomeBannerResp>() {
                    @Override
                    protected void hideDialog() {

                    }

                    @Override
                    protected void showDialog() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        mView.updateFailure();
                    }

                    @Override
                    public void onNext(HomeBannerResp homeBannerResp) {
                        Logger.d("homeBannerResp" + homeBannerResp);
                        if (homeBannerResp != null && homeBannerResp.isOk() && homeBannerResp.getPager() != null && CollectionUtil.isNotEmpty(homeBannerResp.getPager().getList())) {
                            //                            for (HomeBannerResp.Pager.BannerInfo info:
                            //                                    homeBannerResp.getPager().getList()) {
                            //                            }
                            mView.updateBanner(homeBannerResp.getPager().getList());
                        }else {
                            mView.updateFailure();
                        }
                    }
                });

    }

    @Override
    public void loadGoodsBanner(boolean refresh) {
        GoodsBannerReq req = new GoodsBannerReq();
        req.setCountry(0);
        req.setPlatform(TelephonyUtil.getOsTypeInt());

        Logger.d("GoodsBannerReq=" + req);

        mBannerRepository.goodsBanner(req.params())
                .compose(RxUtil.<GoodsBannerResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                //.compose(RetrofitManager.<GoodsBannerResp>composeBackpressureError())
                .subscribe(new BaseSubscriber<GoodsBannerResp>() {
                    @Override
                    protected void hideDialog() {

                    }

                    @Override
                    protected void showDialog() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        mView.updateFailure();
                    }

                    @Override
                    public void onNext(GoodsBannerResp goodsBannerResp) {
                        Logger.d("goodsBannerResp=" + goodsBannerResp);
                        if (goodsBannerResp != null && goodsBannerResp.isOk() && CollectionUtil.isNotEmpty(goodsBannerResp.getList())) {
                            for (int i = 0; i < goodsBannerResp.getList().size(); i++) {
                                if (goodsBannerResp.getList().get(i).getEnable() != 0) {
                                    goodsBannerResp.getList().remove(i);
                                    i--;
                                }
                            }
                            mView.updateBanner(goodsBannerResp.getList());
                        }else {
                            mView.updateFailure();
                        }
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
