package com.hhly.lyygame.presentation.view.pay.bankpay;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.protocol.pay.QuickPayConfirmReq;
import com.hhly.lyygame.presentation.utils.InputUtil;
import com.hhly.lyygame.presentation.utils.NetworkUtil;
import com.hhly.lyygame.presentation.utils.RegexUtils;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.ToastUtil;
import com.hhly.lyygame.presentation.view.immersive.IImmersiveApply;
import com.hhly.lyygame.presentation.view.main.MainTabActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * description :
 * Created by Flynn
 * 2017/5/13
 */

public class SmsValidateFragment extends BaseFragment implements IImmersiveApply, SmsValidateContract.View {

    public static final String KEY_PHONE = "key_phone";
    public static final String KEY_TN = "key_tn";
    public static final String KEY_REQ = "key_quickPayConfirmReq";

    @BindView(R.id.phone_tv)
    TextView mPhoneTv;
    @BindView(R.id.validate_code_et)
    EditText mValidateCodeEt;
    @BindView(R.id.validate_code_btn)
    Button mValidateCodeBtn;
    @BindView(R.id.confirm_btn)
    Button mConfirmBtn;
    private boolean isCountDown;
    private boolean isGetValidateCode;

    private SmsValidateContract.Presenter mPresenter;

    private String mPhone;
    private String mTn;

    public static SmsValidateFragment getInstance(Bundle bundle) {
        SmsValidateFragment fragment = new SmsValidateFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        new SmsValidatePresenter(this);
        mPhone = getArguments().getString(KEY_PHONE);
        mTn = getArguments().getString(KEY_TN);
        mPhoneTv.setText(getString(R.string.lyy_game_phone_sms_validate_phone, mPhone.substring(0, 3) + "****" + mPhone.substring(mPhone.length() - 4)));
        mValidateCodeEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s.toString()) && isGetValidateCode) {
                    mConfirmBtn.setEnabled(true);
                } else {
                    mConfirmBtn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void fetchData(boolean isLoadMore) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sms_validate_layout;
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

    @OnClick(R.id.validate_code_btn)
    public void onMValidateCodeBtnClicked() {
        isGetValidateCode = true;
        if (isCountDown) {
            return;
        }
        if (RegexUtils.checkMobile(mPhone)) {
            startCountDown();
            mPresenter.getQuickPaySendMsg(mTn);
        } else {
            //            ToastUtil.showTip(getActivity(), R.string.lyy_game_account_phone_input_error);
        }
    }

    @OnClick(R.id.confirm_btn)
    public void onMConfirmBtnClicked() {
        if (TextUtils.isEmpty(mValidateCodeEt.getText().toString().trim())) {
            ToastUtil.showTip(getActivity(), R.string.lyy_account_hint_auth_code);
            return;
        }
        if (!NetworkUtil.isAvailable(getActivity())) {
            ToastUtil.showTip(getActivity(), getString(R.string.lyy_game_network_state));
            return;
        }
        showLoading();
        QuickPayConfirmReq req = (QuickPayConfirmReq) getArguments().getSerializable(KEY_REQ);
        if (req != null) {
            req.setTn(mTn);
            req.setSmsCode(mValidateCodeEt.getText().toString().trim());
            //            mPresenter.quickPayConfirm(req);
            startActivity(PayResultActivity.getCallIntent(getActivity(), req));
        }
    }


    public void startCountDown() {
        isCountDown = true;
        mValidateCodeBtn.setEnabled(false);
        mVerificationTimer.start();
    }

    private CountDownTimer mVerificationTimer = new CountDownTimer(60 * 1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            if (mValidateCodeBtn != null) {
                mValidateCodeBtn.setEnabled(false);
                mValidateCodeBtn.setText(getString(R.string.lyy_account_auth_cd_format, millisUntilFinished / 1000));
            }
        }

        @Override
        public void onFinish() {
            if (mValidateCodeBtn != null) {
                mValidateCodeBtn.setEnabled(true);
                mValidateCodeBtn.setText(getString(R.string.lyy_account_auth_re_get));
                isCountDown = false;
            }
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mVerificationTimer.cancel();
        InputUtil.hideInputSoftFromWindowMethod(getActivity(), mValidateCodeEt);
    }

    @Override
    public void setPresenter(SmsValidateContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void getQuickPaySendMsgSuccess() {
        mValidateCodeEt.setText("");
        ToastUtil.showTip(getContext(), getString(R.string.lyy_pay_sms_send_success));
    }

    @Override
    public void getQuickPaySendMsgFailure(String msg) {
        dismissLoading();
        ToastUtil.showTip(getActivity(), !TextUtils.isEmpty(msg) ? msg : getString(R.string.lyy_network_notwork));
    }

    @Override
    public void quickPayConfirmSuccess() {
        dismissLoading();
        ToastUtil.showTip(getContext(), getString(R.string.lyy_pay_success));
        startActivity(MainTabActivity.getCallIntent(getActivity(), 2));
    }

    @Override
    public void quickPayConfirmFailure(String msg) {
        dismissLoading();
        startActivity(MainTabActivity.getCallIntent(getActivity(), 2));
        ToastUtil.showTip(getActivity(), !TextUtils.isEmpty(msg) ? msg : getString(R.string.lyy_network_notwork));
    }
}
