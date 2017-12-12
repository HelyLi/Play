package com.hhly.lyygame.presentation.view.banner;

import android.content.Context;
import android.util.AttributeSet;

import com.bigkoo.convenientbanner.holder.Holder;
import com.hhly.lyygame.data.net.protocol.banner.GoodsBannerResp;
import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * Created by Simon on 2016/12/7.
 */

public class MallBannerHeader extends BannerHeader<GoodsBannerResp.GoodsBanner> {

    public MallBannerHeader(Context context) {
        super(context);
    }

    public MallBannerHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public Holder createHolder() {
        return new MallBannerHolder();
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return null;
    }
}
