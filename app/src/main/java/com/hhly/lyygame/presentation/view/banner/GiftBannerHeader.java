package com.hhly.lyygame.presentation.view.banner;

import android.content.Context;
import android.util.AttributeSet;

import com.bigkoo.convenientbanner.holder.Holder;
import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * Created by Simon on 2016/12/7.
 */

public class GiftBannerHeader extends BannerHeader<String> {

    public GiftBannerHeader(Context context) {
        super(context);
    }

    public GiftBannerHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public Holder createHolder() {
        return new GiftBannerHolder();
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return null;
    }


}
