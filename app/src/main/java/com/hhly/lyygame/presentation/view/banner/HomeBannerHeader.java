package com.hhly.lyygame.presentation.view.banner;

import android.content.Context;
import android.util.AttributeSet;

import com.bigkoo.convenientbanner.holder.Holder;
import com.hhly.lyygame.data.net.protocol.banner.HomeBannerResp;
import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * 首页轮播
 * Created by Simon on 2016/12/7.
 */

public class HomeBannerHeader extends BannerHeader<HomeBannerResp.Pager.BannerInfo> {

    public HomeBannerHeader(Context context) {
        super(context);
    }

    public HomeBannerHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public Holder createHolder() {
        return new HomeBannerHolder();
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return null;
    }
}
