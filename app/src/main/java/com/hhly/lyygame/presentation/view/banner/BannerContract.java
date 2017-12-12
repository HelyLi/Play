package com.hhly.lyygame.presentation.view.banner;


import com.hhly.lyygame.presentation.view.BasePresenter;
import com.hhly.lyygame.presentation.view.BaseView;

import java.util.List;

/**
 * Created by Simon on 16/9/6.
 */
public interface BannerContract {

    interface Presenter extends BasePresenter {
        void loadHomeBanner(boolean refresh);
        void loadGoodsBanner(boolean refresh);
    }
    
    interface View<T> extends BaseView<Presenter> {
        void updateFailure();
        void updateBanner(List<T> bannerData);
    }
}
