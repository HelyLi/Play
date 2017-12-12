package com.hhly.lyygame.presentation.view.pay.bankpay;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import butterknife.BindView;
import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.view.BaseActivity;
import com.hhly.lyygame.presentation.view.immersive.IImmersiveApply;

/**
 * description :
 * Created by Flynn
 * 2017/5/13
 */

public class SupportBankActivity extends BaseActivity implements IImmersiveApply, TabLayout.OnTabSelectedListener {

    private static final int[] TAB_TEXT = {R.string.lyy_game_bank_deposit_card, R.string.lyy_game_bank_credit_card};
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    private SupportBankFragment[] mFragments;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, SupportBankActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragments = new SupportBankFragment[TAB_TEXT.length];
        mFragments[0] = SupportBankFragment.newInstance(SupportBankFragment.DEPOSIT);
        mFragments[1] = SupportBankFragment.newInstance(SupportBankFragment.CREDIT);
        mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        mViewPager.setOffscreenPageLimit(2);
        mTabLayout.setupWithViewPager(mViewPager);
        for (int i = 0; i < TAB_TEXT.length; i++) {
            mTabLayout.getTabAt(i).setText(TAB_TEXT[i]);
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_support_bank_layout;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mViewPager.setCurrentItem(tab.getPosition(), false);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

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
            return null;
        }
    }

}
