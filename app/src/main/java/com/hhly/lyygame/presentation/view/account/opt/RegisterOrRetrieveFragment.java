package com.hhly.lyygame.presentation.view.account.opt;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.utils.InputUtil;
import com.hhly.lyygame.presentation.utils.NetworkUtil;
import com.hhly.lyygame.presentation.utils.RegexUtils;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.ToastUtil;
import com.hhly.lyygame.presentation.view.web.WebLocationActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 注册/找回密码 第一步
 * Created by Simon on 16/9/8.
 */
public class RegisterOrRetrieveFragment extends BaseFragment
        implements RegisterOrRetrieveContract.View {

    private RegisterOrRetrieveContract.Presenter mPresenter;

    @BindView(R.id.account_agreement_root)
    View agreement_root;
    @BindView(R.id.phone_num_et)
    EditText phone_num_et;
    @BindView(R.id.verification_code_et)
    EditText verification_code_et;
    @BindView(R.id.verification_btn)
    Button verification_btn;
    @BindView(R.id.next_btn)
    Button next_btn;
    @BindView(R.id.account_clear_iv)
    ImageView account_clear_iv;

    private boolean isCountDown = false;
    private int type = RegisterOrRetrieveActivity.TYPE_REGISTER;

    public static RegisterOrRetrieveFragment newInstance(int type) {
        RegisterOrRetrieveFragment fragment = new RegisterOrRetrieveFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(RegisterOrRetrieveActivity.EXTRA_TYPE, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getArguments() != null) {
            type = getArguments().getInt(RegisterOrRetrieveActivity.EXTRA_TYPE, RegisterOrRetrieveActivity.TYPE_REGISTER);
        }
        if (type == RegisterOrRetrieveActivity.TYPE_REGISTER) {
            agreement_root.setVisibility(View.VISIBLE);
            //            next_btn.setText(R.string.lyy_next);
        } else if (type == RegisterOrRetrieveActivity.TYPE_RETRIEVE) {
            agreement_root.setVisibility(View.GONE);
            //            next_btn.setText(R.string.lyy_edit_password);
        } else {
            agreement_root.setVisibility(View.GONE);
        }

        phone_num_et.addTextChangedListener(mPhoneWatcher);
        verification_code_et.addTextChangedListener(mVerificationWatcher);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_account_reg_or_retri_01;
    }

    public void setPresenter(RegisterOrRetrieveContract.Presenter gamePresenter) {
        this.mPresenter = gamePresenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    protected void fetchData(boolean isLoadMore) {

    }

    @Override
    protected void onBackPressed() {
        super.onBackPressed();
        mVerificationTimer.cancel();
        InputUtil.hideInputSoftFromWindowMethod(getActivity(), phone_num_et);
    }

    //跳转到设置密码界面
    @Override
    public void onNext() {
        mVerificationTimer.cancel();
        mVerificationTimer.onFinish();
        if (getActivity() != null && getActivity() instanceof RegisterOrRetrieveActivity) {
            ((RegisterOrRetrieveActivity) getActivity()).doNextPage(phone_num_et.getText().toString().trim(),
                    verification_code_et.getText().toString().trim());
        }
    }

    @Override
    public void startCountDown() {
        isCountDown = true;
        verification_btn.setEnabled(false);
        mVerificationTimer.start();
    }

    @Override
    public void onCheckSuccess(String msg) {
        if (type == RegisterOrRetrieveActivity.TYPE_REGISTER) {
            mPresenter.requestVerificationCode(phone_num_et.getText().toString().trim(),
                    type == RegisterOrRetrieveActivity.TYPE_REGISTER ? 1 : 2);
        } else if (type == RegisterOrRetrieveActivity.TYPE_RETRIEVE) {
            ToastUtil.showTip(getActivity(), R.string.lyy_account_phone_not_register);
        }
    }

    @Override
    public void onCheckFailure(String msg) {
        if (type == RegisterOrRetrieveActivity.TYPE_RETRIEVE) {
            mPresenter.requestVerificationCode(phone_num_et.getText().toString().trim(),
                    type == RegisterOrRetrieveActivity.TYPE_REGISTER ? 1 : 2);
        } else if (type == RegisterOrRetrieveActivity.TYPE_REGISTER) {
            if (!TextUtils.isEmpty(msg))
                ToastUtil.showTip(getActivity(), msg);
        }
    }

    @OnClick(R.id.next_btn)
    void onNextClick(View view) {
        InputUtil.hideInputSoftFromWindowMethod(getContext(), verification_code_et);
        if (!NetworkUtil.isAvailable(getActivity())) {
            ToastUtil.showTip(getActivity(), R.string.lyy_game_network_state);
            return;
        }

        String phone = phone_num_et.getText().toString().trim();

        if (RegexUtils.checkMobile(phone)) {
            mPresenter.requestVerification(phone_num_et.getText().toString().trim(), verification_code_et.getText().toString().trim(), type == RegisterOrRetrieveActivity.TYPE_REGISTER ? 1 : 2);
        } else {
            ToastUtil.showTip(getActivity(), R.string.lyy_game_account_phone_input_error);
        }
    }

    //用户协议
    @OnClick(R.id.account_agreement)
    void onAgreementClick(View view) {
        ActivityCompat.startActivity(getActivity(), WebLocationActivity.getCallingIntent(getActivity(),
                WebLocationActivity.USER_AGREEMENT, getActivity().getString(R.string.lyy_user_agreement_title)), null);
    }

    @OnClick(R.id.verification_btn)
    void onVerificationClick(View view) {
        if (isCountDown) {
            return;
        }
        String phone = phone_num_et.getText().toString().trim();

        if (RegexUtils.checkMobile(phone)) {
            mPresenter.checkAccount(phone);
        } else {
            ToastUtil.showTip(getActivity(), R.string.lyy_game_account_phone_input_error);
        }
    }

    @OnClick(R.id.account_clear_iv)
    void onAccountClearClick() {
        phone_num_et.getText().clear();
    }

    private CountDownTimer mVerificationTimer = new CountDownTimer(60 * 1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            if (verification_btn != null) {
                verification_btn.setEnabled(false);
                verification_btn.setText(getString(R.string.lyy_account_auth_cd_format, millisUntilFinished / 1000));
            }
        }

        @Override
        public void onFinish() {
            if (verification_btn != null) {
                verification_btn.setEnabled(true);
                verification_btn.setText(getString(R.string.lyy_account_auth_re_get));
                isCountDown = false;
            }
        }
    };

    private TextWatcher mPhoneWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            checkPhone();
        }

        @Override
        public void afterTextChanged(Editable s) {
            account_clear_iv.setVisibility(phone_num_et.getText().length() > 0 ? View.VISIBLE : View.GONE);
        }
    };

    private void checkPhone() {
        String phone = phone_num_et.getText().toString().trim();
        if (RegexUtils.checkMobile(phone)) {
            verification_btn.setEnabled(true);
            //            next_btn.setEnabled(true);
        } else {
            verification_btn.setEnabled(false);
            //            next_btn.setEnabled(false);
        }
    }

    private void checkVerification() {
        String verification = verification_code_et.getText().toString().trim();
        if (verification.length() == 6 && RegexUtils.checkMobile(phone_num_et.getText().toString().trim())) {
            next_btn.setEnabled(true);
        } else {
            next_btn.setEnabled(false);
        }
    }

    private TextWatcher mVerificationWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            checkVerification();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}
