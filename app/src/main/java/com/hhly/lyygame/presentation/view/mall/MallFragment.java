package com.hhly.lyygame.presentation.view.mall;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.utils.ActivityUtil;
import com.hhly.lyygame.presentation.utils.SharedPrefsFavouriteUtil;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.banner.BannerHeader;
import com.hhly.lyygame.presentation.view.exchange.ExchangeHistoryActivity;
import com.hhly.lyygame.presentation.view.immersive.IImmersiveApply;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 积分商城
 */

public class MallFragment extends BaseFragment implements IImmersiveApply,
        AppBarLayout.OnOffsetChangedListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.banner_header)
    BannerHeader mBannerHeader;
    @BindView(R.id.appBarLayout)
    AppBarLayout mAppBarLayout;

    private static final int[] TAB_TEXT = {R.string.lyy_ex_score, R.string.lyy_ex_coupon};

    private static final int[] TAB_IMG = {R.drawable.ic_mall_category_mat, R.drawable.ic_mall_category_vir};

    private MallCategoryFragment[] mFragments;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFragments = new MallCategoryFragment[TAB_TEXT.length];
        mFragments[0] = MallCategoryFragment.newInstance(MallCategoryFragment.CATEGORY_SCORE);
        mFragments[1] = MallCategoryFragment.newInstance(MallCategoryFragment.CATEGORY_COUPON);
    }

    @Override
    protected void fetchData(boolean refresh) {
        postDelay(new Runnable() {
            @Override
            public void run() {
                mBannerHeader.fetchMallData();
                fetchChildData(false);
            }
        }, 300);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mall_layout;
    }

    @Override
    public boolean applyImmersive() {
        return true;
    }

    @Override
    public boolean applyScroll() {
        return false;
    }

    @Override
    public float initAlpha() {
        return 1.0f;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mBannerHeader != null) {
            mBannerHeader.onStart();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mBannerHeader != null) {
            mBannerHeader.onStop();
        }
    }

    @OnClick(R.id.exchange_history_btn)
    void onExchangeHistoryClick() {
        if (ActivityUtil.checkLoginAndRequestLogin(getActivity())) {
            ActivityCompat.startActivity(getActivity(), ExchangeHistoryActivity.getCallingIntent(getActivity()), null);
        }
    }

    @Override
    protected boolean enableHomeAsUp() {
        return false;
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {

        if (mToolbarHelper != null) {
            mToolbarHelper.setTitle(getString(R.string.tab_mall));
        }
        mRefreshLayout.setOnRefreshListener(this);

        mViewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            tab.setCustomView(getTabView(i));
        }
        mAppBarLayout.addOnOffsetChangedListener(this);
        mBannerHeader.fetchMallData();
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (!mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setEnabled(verticalOffset == 0);
        }
    }

    @Override
    public void onRefresh() {
        fetchData(true);
    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments[position];
        }

        @Override
        public int getCount() {
            return TAB_TEXT.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            //            Drawable image = getResources().getDrawable(TAB_IMG[position]);
            //            image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
            //            // Replace blank spaces with image icon
            //            SpannableString sp = new SpannableString("   " + getResources().getString(TAB_TEXT[position]));
            //
            //            ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BASELINE);
            //            sp.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            //            return sp;
            return null;
        }
    }

    void showRefresh() {
        if (mRefreshLayout != null && !mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (SharedPrefsFavouriteUtil.getBooleanValue(getActivity(), "isDeleleFavourite", false)) {
            SharedPrefsFavouriteUtil.putBooleanValue(getActivity(), "isDeleleFavourite", false);
            for (int i = 0; i < mFragments.length; i++) {
                mFragments[i].forceFetchData(false);
            }
        }
    }

    void hideRefresh() {
        postDelay(new Runnable() {
            @Override
            public void run() {
                if (mRefreshLayout != null && mRefreshLayout.isRefreshing())
                    mRefreshLayout.setRefreshing(false);
            }
        }, 200);
    }

    void fetchChildData(boolean refresh) {
        int position = mViewPager.getCurrentItem();
        mFragments[position].forceFetchData(refresh);
    }

    public View getTabView(int position) {
        View view = LayoutInflater.from(MallFragment.this.getContext()).inflate(R.layout.widget_tab_item_layout, null);
        TextView tv = (TextView) view.findViewById(R.id.textView);
        tv.setText(TAB_TEXT[position]);
        ImageView img = (ImageView) view.findViewById(R.id.imageView);
        img.setImageResource(TAB_IMG[position]);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mRefreshLayout != null && mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(false);
        }
    }

}
