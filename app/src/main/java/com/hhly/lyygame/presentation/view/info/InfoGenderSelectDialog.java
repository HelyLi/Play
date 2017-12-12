package com.hhly.lyygame.presentation.view.info;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.hhly.lyygame.R;

/**
 * Created by dell on 2017/3/13.
 */

public class InfoGenderSelectDialog extends BottomSheetDialog {
    private OnGenderSelectListener mOnGenderSelectListener;

    public InfoGenderSelectDialog(@NonNull Context context) {
        super(context);
    }

    public InfoGenderSelectDialog(@NonNull Context context, OnGenderSelectListener onGenderSelectListener) {
        super(context);
        mOnGenderSelectListener = onGenderSelectListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_gender_select_layout);
        ButterKnife.bind(this);
        getWindow().setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @OnClick(R.id.male_tv)
    void onMaleClick(View view) {
        if (mOnGenderSelectListener != null) {
            mOnGenderSelectListener.onClick(1);
        }
        dismiss();
    }

    @OnClick(R.id.female_tv)
    void onFemaleClick(View view) {
        if (mOnGenderSelectListener != null) {
            mOnGenderSelectListener.onClick(2);
        }
        dismiss();
    }
    @OnClick(R.id.secret_tv)
    void onSecretClick(View view){
        if (mOnGenderSelectListener != null) {
            mOnGenderSelectListener.onClick(3);
        }
        dismiss();
    }

    @OnClick(R.id.cancel_tv)
    void onCancelClick(View view) {
        dismiss();
    }

    public interface OnGenderSelectListener {
        void onClick(int gender);
    }
}
