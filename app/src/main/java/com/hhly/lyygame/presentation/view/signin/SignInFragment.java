package com.hhly.lyygame.presentation.view.signin;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hhly.lyygame.R;
import com.hhly.lyygame.data.db.entry.UserInfo;
import com.hhly.lyygame.data.db.manager.AccountManager;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 每日签到
 * Created by Simon on 2016/12/3.
 */

public class SignInFragment extends BaseFragment implements SignInContract.View {

    @BindView(R.id.current_date_tv)
    TextView mCurrentDateTv;
    @BindView(R.id.score_tv)
    TextView mScoreTv;
    @BindView(R.id.calendarView)
    MaterialCalendarView mCalendarView;
    @BindView(R.id.sign_in_btn)
    Button mSignInBtn;
    @BindView(R.id.sign_in_days_description_tv)
    TextView mSignInDaysDescriptionTv;
    @BindView(R.id.sign_in_description_tv)
    TextView mSignInDescriptionTv;

    SignInContract.Presenter mPresenter;

    public static SignInFragment newInstance() {
        return new SignInFragment();
    }

    public SignInFragment() {
        new SignInPresenter(this);
    }

    private Calendar current;
    private UserInfo mUserInfo;

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {

        mCalendarView.setShowOtherDates(MaterialCalendarView.SHOW_DEFAULTS);
        mCalendarView.setTopbarVisible(false);
        mCalendarView.setDynamicHeightEnabled(true);
        mCalendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_NONE);

        current = Calendar.getInstance();
        mCalendarView.setSelectedDate(current);

        SimpleDateFormat sdf = (SimpleDateFormat) SimpleDateFormat.getDateInstance();
        sdf.applyPattern("yyyy-MM-dd");
        mCurrentDateTv.setText(sdf.format(current.getTime()));

        Calendar min = Calendar.getInstance();
        min.set(Calendar.DAY_OF_MONTH, 1);

        Calendar max = Calendar.getInstance();
        max.set(Calendar.DAY_OF_MONTH, max.getActualMaximum(Calendar.DAY_OF_MONTH));

        mCalendarView.state().edit()
                .setMinimumDate(min)
                .setMaximumDate(max)
                .commit();

        //        mCalendarView.addDecorator(new DaysDecorator());
        mCalendarView.addDecorator(new SelectionDecorator(getActivity()));
        mPresenter.getSignInInfo();

        mUserInfo = AccountManager.getInstance().getUserInfo();

        double score = Double.parseDouble(mUserInfo.getJf() == null ? "0" : mUserInfo.getJf());
        mScoreTv.setText(getString(R.string.lyy_sign_in_get_score, (int) score));
        mSignInDaysDescriptionTv.setText(getString(R.string.lyy_sign_in_continuous_sign, 0));
    }

    @Override
    protected void fetchData(boolean isLoadMore) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sign_in_layout;
    }

    @OnClick(R.id.sign_in_btn)
    public void onClick() {
        //request sign in
        mPresenter.signIn(current.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void setPresenter(SignInContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    private String[] days = new String[]{};
    private int mContinueDay = 0;

    @Override
    public void showSignInfo(int continueDay, String day) {
        if (!TextUtils.isEmpty(day)) {
            days = day.split(";");
        }

        mSignInDaysDescriptionTv.setText(getString(R.string.lyy_sign_in_continuous_sign, continueDay < 0 ? 0 : continueDay));
        mCalendarView.addDecorator(new PastDecorator(days));
        int currentDay = current.get(Calendar.DAY_OF_MONTH);
        mCalendarView.addDecorator(new DisableDecorator(currentDay, days));

        boolean sign = true;
        for (String s : days) {
            if (Integer.parseInt(s) == currentDay) {
                sign = false;
                break;
            }
        }
        mSignInBtn.setEnabled(sign);
        mSignInBtn.setText(sign ? R.string.lyy_sign_in_status_01 : R.string.lyy_sign_in_status_02);
    }

    @Override
    public void signInSuccess(double points) {
        mContinueDay++;
        mSignInDaysDescriptionTv.setText(getString(R.string.lyy_sign_in_continuous_sign, mContinueDay < 0 ? 0 : mContinueDay));
        if (mUserInfo != null) {
            double score = points + Double.parseDouble(mUserInfo.getJf() == null ? "0" : mUserInfo.getJf());
            mScoreTv.setText(getString(R.string.lyy_sign_in_get_score, (int) score));

            mUserInfo.setJf(String.valueOf(score));
            AccountManager.getInstance().saveUserInfo(mUserInfo);
        }
        mPresenter.getSignInInfo();
    }

    /**
     * 过去中没签到的装饰
     */
    private static class DisableDecorator implements DayViewDecorator {

        private int mCurrentDay;
        private String[] days;

        DisableDecorator(int currentDay, String[] days) {
            this.days = days;
            mCurrentDay = currentDay;
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {

            boolean sign = true;
            for (String s : days) {
                if (Integer.parseInt(s) == day.getDay()) {
                    sign = false;
                    break;
                }
            }

            return mCurrentDay > day.getDay() && sign;
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.setDaysDisabled(true);
        }
    }

    /**
     * 过去已签到
     */
    private static class PastDecorator implements DayViewDecorator {

        private String[] days;

        public PastDecorator(String[] days) {
            this.days = days;
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {

            boolean sign = false;
            for (String s : days) {
                if (Integer.parseInt(s) == day.getDay()) {
                    sign = true;
                    break;
                }
            }
            return sign;
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.setDaysPast(true);
        }
    }

    /**
     * 选中的（当天）
     */
    private static class SelectionDecorator implements DayViewDecorator {

        private Drawable mDrawable;

        SelectionDecorator(Context context) {
            mDrawable = ContextCompat.getDrawable(context, R.drawable.bg_calendar_day_selector);
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return true;
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.setSelectionDrawable(mDrawable);
        }
    }

}
