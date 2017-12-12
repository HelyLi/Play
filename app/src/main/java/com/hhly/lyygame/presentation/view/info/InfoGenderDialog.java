package com.hhly.lyygame.presentation.view.info;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.View;
import android.widget.RadioGroup;

import com.hhly.lyygame.R;
import com.hhly.lyygame.data.db.manager.AccountManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Simon on 2016/11/26.
 */

public class InfoGenderDialog extends Dialog {

    private OnClickListener mOnClickListener;

    @BindView(R.id.gender_group)
    RadioGroup mGroup;

    @BindView(R.id.gender_female_rb)
    AppCompatRadioButton mFemaleRB;

    private InfoGenderDialog(Context context) {
        super(context);
    }

    public InfoGenderDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public InfoGenderDialog(Context context, OnClickListener onClickListener) {
        super(context);
        mOnClickListener = onClickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_gender_layout);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        Integer sex = AccountManager.getInstance().getUserInfo().getSex();
        if (sex == null || sex == 2 || sex == 3) {
            mFemaleRB.setChecked(true);
        }
    }

    @OnClick(R.id.confirm_btn)
    void onConfirmClick(View view) {
        if (mOnClickListener != null) {
            mOnClickListener.onConfirmClick(R.id.gender_male_rb == mGroup.getCheckedRadioButtonId());
        }
        dismiss();
    }

    @OnClick(R.id.close_iv)
    void onCloseClick(View view) {
        if (mOnClickListener != null) {
            mOnClickListener.onCloseClick();
        }
        dismiss();
    }


    public interface OnClickListener {
        void onConfirmClick(Boolean isMale);

        void onCloseClick();
    }

}
