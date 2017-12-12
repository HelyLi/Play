package com.hhly.lyygame.presentation.view.pay.bankpay;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.hhly.lyygame.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.ArrayWheelAdapter;

/**
 * 有效期选择dialog
 * Created by dell on 2017/5/15.
 */

public class ValidityTimeSelectDialog extends BottomSheetDialogFragment {
    @BindView(R.id.year_wheel_view)
    WheelView mYearWheelView;
    @BindView(R.id.month_wheel_view)
    WheelView mMonthWheelView;
    private OnSelectedCallBack mCallBack;
    private String[] mYears;
    private String[] mMonths;
    Unbinder unbinder;

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.validity_time_select_dialog_layout, null);
        ButterKnife.bind(this,contentView);
        mYears = new String[]{"2017",
                "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027"};
        mMonths = new String[]{"01", "02", "03",
                "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        mYearWheelView.setViewAdapter(new ArrayWheelAdapter<>(getContext(), mYears));
        mMonthWheelView.setViewAdapter(new ArrayWheelAdapter<>(getContext(), mMonths));
        unbinder = ButterKnife.bind(this, contentView);
        return contentView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.cancel_tv, R.id.confirm_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cancel_tv:
                dismiss();
                break;
            case R.id.confirm_tv:
                dismiss();
                if (mCallBack != null) {
                    mCallBack.onCallBack(mYears[mYearWheelView.getCurrentItem()], mMonths[mMonthWheelView.getCurrentItem()]);
                }
                break;
        }
    }

    public void setCallBack(OnSelectedCallBack callBack) {
        mCallBack = callBack;
    }

    public interface OnSelectedCallBack {
        void onCallBack(String year, String month);
    }
}
