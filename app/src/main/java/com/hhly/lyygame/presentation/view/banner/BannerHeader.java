package com.hhly.lyygame.presentation.view.banner;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.utils.DisplayUtil;
import com.hhly.lyygame.presentation.view.LifecycleListener;
import com.hhly.lyygame.presentation.view.widget.RatioFrameLayout;

import java.util.List;

/**
 * Created by Simon on 16/9/6.
 */
public abstract class BannerHeader<T> extends RatioFrameLayout implements BannerContract.View<T>, LifecycleListener{

    private static final long BANNER_TURNING_TIME = 5000;

    private BannerContract.Presenter mPresenter;
    private ConvenientBanner<T> mConvenientBanner;
    private int pageCount = 0;

    public BannerHeader(Context context) {
        super(context);
        initView();
    }

    public BannerHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public void startTurning() {
        if (pageCount > 1) {
            mConvenientBanner.startTurning(BANNER_TURNING_TIME);
        }
    }

    public void stopTurning() {
        if (pageCount > 1) {
            mConvenientBanner.stopTurning();
        }
    }

    private void initView() {
        inflate(getContext(), R.layout.fragment_banner_layout, this);
        widthWeight = 0.5f;
        requestLayout();
        mConvenientBanner = (ConvenientBanner<T>) findViewById(R.id.banner);
        new BannerPresenter(this);
    }

    public void setPresenter(BannerContract.Presenter gamePresenter) {
        this.mPresenter = gamePresenter;
    }

    private boolean refreshOver = true;

    public void fetchHomeData(){
        if (refreshOver){
            refreshOver = false;
            mPresenter.loadHomeBanner(false);
        }
    }

    public void fetchMallData(){
        if (refreshOver){
            refreshOver = false;
            mPresenter.loadGoodsBanner(false);
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public void updateFailure() {
        refreshOver = true;
    }

    @Override
    public void updateBanner(List<T> bannerData) {
        pageCount = bannerData != null ? bannerData.size() : 0;
        if (bannerData != null) {
            mConvenientBanner.setPages(new CBViewHolderCreator() {
                @Override
                public Object createHolder() {
                    return BannerHeader.this.createHolder();
                }
            }, bannerData)
                    .setPageIndicator(new int[]{
                            R.drawable.indicator_banner_normal,
                            R.drawable.indicator_banner_focus})
                    .setPageIndicatorAlign(0, 0, 0, DisplayUtil.dip2px(getContext(), 6), RelativeLayout.ALIGN_PARENT_BOTTOM)
                    .setCanLoop(pageCount > 1);
            startTurning();
        }
        refreshOver = true;
    }

    @Override
    public void onStart() {
        mPresenter.subscribe();
    }

    @Override
    public void onStop() {
        stopTurning();
    }

    @Override
    public void onDestroy() {
        mPresenter.unsubscribe();
    }

    public abstract Holder createHolder();

}
