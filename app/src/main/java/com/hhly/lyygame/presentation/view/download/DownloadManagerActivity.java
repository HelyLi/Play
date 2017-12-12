package com.hhly.lyygame.presentation.view.download;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.view.BaseActivity;
import com.hhly.lyygame.presentation.view.immersive.IImmersiveApply;

import butterknife.BindView;

/**
 * 下载管理
 * Created by Simon on 2016/12/5.
 */

public class DownloadManagerActivity extends BaseActivity implements IImmersiveApply {

    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    private final static int[] TITLES = {R.string.lyy_title_downloading, R.string.lyy_title_downloaded};
    private Fragment[] mFragments;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, DownloadManagerActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFragments = new Fragment[TITLES.length];
        mFragments[0] = DownloadingFragment.newInstance();
        mFragments[1] = DownloadedFragment.newInstance();

        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(new TabAdapter(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_download_manager_layout;
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
