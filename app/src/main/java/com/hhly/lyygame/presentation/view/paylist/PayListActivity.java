package com.hhly.lyygame.presentation.view.paylist;

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
 * Created by ${HELY} on 17/1/18.
 * 邮箱：heli.lixiong@gmail.com
 */

public class PayListActivity extends BaseActivity implements IImmersiveApply {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    private static final int[] TAB_TEXT = {R.string.lyy_pay_recharge_record, R.string.lyy_pay_expense_record};
    private PayListFragment[] mFragments;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, PayListActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTabLayout.addTab(mTabLayout.newTab().setText(TAB_TEXT[0]), true);
        mTabLayout.addTab(mTabLayout.newTab().setText(TAB_TEXT[1]));
        mFragments = new PayListFragment[TAB_TEXT.length];
        mFragments[0] = PayListFragment.newInstance(PayListFragment.RECHARGE_CATEGORY);
        mFragments[1] = PayListFragment.newInstance(PayListFragment.CONSUME_CATEGORY);

        mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mTabLayout.setScrollPosition(position, positionOffset, false);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setOffscreenPageLimit(2);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pay_list_layout;
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
            return getString(TAB_TEXT[position]);
        }
    }
}
