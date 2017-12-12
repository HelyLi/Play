package com.hhly.lyygame.presentation.view.score;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.view.BaseFragment;

import butterknife.BindView;

/**
 * 积分明细
 * Created by Simon on 2016/11/30.
 */

public class ScoreRecordFragment extends BaseFragment {

    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    private final static int[] TITLES = {R.string.lyy_score_record_income, R.string.lyy_score_record_outgo};
    private Fragment[] mFragments;

    public static ScoreRecordFragment newInstance() {
        return new ScoreRecordFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragments = new Fragment[TITLES.length];
        mFragments[0] = SRIncomeFragment.newInstance();
        mFragments[1] = SROutgoFragment.newInstance();

    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {

        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(new TabAdapter(getChildFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    protected void fetchData(boolean isLoadMore) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_score_record_layout;
    }

    private class TabAdapter extends FragmentStatePagerAdapter {

        TabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments[position];
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getString(TITLES[position]);
        }
    }

}
