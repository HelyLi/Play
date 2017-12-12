package com.hhly.lyygame.presentation.view.mall;

import android.os.Bundle;
import android.view.View;

import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.view.BaseFragment;

/**
 * 礼包详情
 * Created by Simon on 2016/12/1.
 */

public class GiftDetailFragment extends BaseFragment {

    public static GiftDetailFragment newInstance() {
        return new GiftDetailFragment();
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void fetchData(boolean isLoadMore) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gift_detail_layout;
    }
}
