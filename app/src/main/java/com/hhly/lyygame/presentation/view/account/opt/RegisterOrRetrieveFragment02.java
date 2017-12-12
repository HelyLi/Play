package com.hhly.lyygame.presentation.view.account.opt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.protocol.user.State;
import com.hhly.lyygame.presentation.utils.ActivityUtil;
import com.hhly.lyygame.presentation.utils.InputUtil;
import com.hhly.lyygame.presentation.utils.RegexUtils;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.ToastUtil;
import com.hhly.lyygame.presentation.view.account.AccountCons;
import com.hhly.lyygame.presentation.view.main.MainTabActivity;
import com.hhly.lyygame.presentation.view.web.WebLocationActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.hhly.lyygame.R.id.pwd_again_et;

/**
 * 注册/找回密码 第二步
 * Created by Simon on 16/9/8.
 */
public class RegisterOrRetrieveFragment02 extends BaseFragment implements RegisterOrRetrieveContract02.View {

    @BindView(pwd_again_et)
    EditText mPwdAgainEt;
    @BindView(R.id.password_again_visibility_iv)
    ImageView mPwdAgainVisibilityIv;
    @BindView(R.id.invite_code_et)
    EditText mInviteCodeEt;
    @BindView(R.id.invite_layout)
    LinearLayout mInviteLayout;
    @BindView(R.id.invite_divide)
    View mInviteDivide;

    private RegisterOrRetrieveContract02.Presenter mPresenter;

    @BindView(R.id.account_agreement_root)
    View agreement_root;
    @BindView(R.id.pwd_et)
    EditText pwd_et;
    @BindView(R.id.password_visibility_iv)
    ImageView mPwdVisibilityIv;
    @BindView(R.id.next_btn)
    Button next_btn;

    private String mPhone;
    private String mSmsCode;
    private int type = RegisterOrRetrieveActivity.TYPE_REGISTER;

    private List<EditText> mEditTextNeedWatched = new ArrayList<>();

    public static RegisterOrRetrieveFragment02 newInstance(int type, String phone, String smsCode) {
        RegisterOrRetrieveFragment02 fragment = new RegisterOrRetrieveFragment02();
        Bundle bundle = new Bundle();
        bundle.putInt(RegisterOrRetrieveActivity.EXTRA_TYPE, type);
        bundle.putString(RegisterOrRetrieveActivity.EXTRA_PHONE, phone);
        bundle.putString(RegisterOrRetrieveActivity.EXTRA_SMSCODE, smsCode);
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
            mPhone = getArguments().getString(RegisterOrRetrieveActivity.EXTRA_PHONE, null);
            mSmsCode = getArguments().getString(RegisterOrRetrieveActivity.EXTRA_SMSCODE, null);
        }
        if (type == RegisterOrRetrieveActivity.TYPE_REGISTER) {
            agreement_root.setVisibility(View.VISIBLE);
            next_btn.setText(R.string.lyy_account_title_register);
        } else if (type == RegisterOrRetrieveActivity.TYPE_RETRIEVE) {
            agreement_root.setVisibility(View.GONE);
            next_btn.setText(R.string.lyy_finish);
        } else {
            agreement_root.setVisibility(View.GONE);
            next_btn.setText(R.string.lyy_next);
        }

        handleInviteLayout();

        //        pwd_et.addTextChangedListener(mPwdWatcher);
        pwd_et.setFilters(new InputFilter[]{new InputFilter.LengthFilter(AccountCons.PWD_MAX_LEN)});
        pwd_et.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        mEditTextNeedWatched.add(pwd_et);
        mPwdAgainEt.setFilters(new InputFilter[]{new InputFilter.LengthFilter(AccountCons.PWD_MAX_LEN)});
        mPwdAgainEt.setImeOptions(EditorInfo.IME_ACTION_DONE);
        mEditTextNeedWatched.add(mPwdAgainEt);
        mPwdAgainEt.addTextChangedListener(mPwdWatcher);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_account_reg_or_retri_02;
    }

    public void setPresenter(RegisterOrRetrieveContract02.Presenter gamePresenter) {
        this.mPresenter = gamePresenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    protected void onBackPressed() {
        super.onBackPressed();
        InputUtil.hideInputSoftFromWindowMethod(getActivity(), pwd_et);
    }

    @Override
    public void onNext() {
        //注册/找回密码成功, 跳到主页
        dismissLoading();
        if (type == RegisterOrRetrieveActivity.TYPE_REGISTER) {//自动登录
            mPresenter.login(mPhone, pwd_et.getText().toString().trim());
        } else if (type == RegisterOrRetrieveActivity.TYPE_RETRIEVE) {//手动登录

            ActivityUtil.startLoginForNormal(getActivity());

//            LoginActivity

//            onBackPressed();
        }
    }

    @Override
    public void doLoginSuccess() {
        ActivityCompat.startActivity(getActivity(), MainTabActivity.getCallIntent(getContext(),true), null);
        onBackPressed();
    }

    @OnClick(R.id.next_btn)
    void onNextClick(View view) {
        //注册/找回密码
        InputUtil.hideInputSoftFromWindowMethod(getContext(), pwd_et);

        if (!pwd_et.getText().toString().trim().equals(mPwdAgainEt.getText().toString().trim())) {
            ToastUtil.showTip(getActivity(), R.string.lyy_account_hint_password_error);
            return;
        }
        doRequest();
    }

    private boolean isPwdVisible = false;
    @OnClick(R.id.password_visibility_iv)
    void onPwdVisiblityClick(View view){
        if (isPwdVisible) {
            isPwdVisible = false;
            mPwdVisibilityIv.setSelected(false);
            pwd_et.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            pwd_et.setSelection(pwd_et.getText().length());
        } else {
            isPwdVisible = true;
            mPwdVisibilityIv.setSelected(true);
            pwd_et.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
            pwd_et.setSelection(pwd_et.getText().length());
        }
    }

    private boolean isPwdAgainVisible = false;
    @OnClick(R.id.password_again_visibility_iv)
    void onPwdAgainVisiblityClick(View view){
        if (isPwdAgainVisible) {
            isPwdAgainVisible = false;
            mPwdAgainVisibilityIv.setSelected(false);
            mPwdAgainEt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            mPwdAgainEt.setSelection(mPwdAgainEt.getText().length());
        } else {
            isPwdAgainVisible = true;
            mPwdAgainVisibilityIv.setSelected(true);
            mPwdAgainEt.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
            mPwdAgainEt.setSelection(mPwdAgainEt.getText().length());
        }
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

    private void doRequest() {

        if (!RegexUtils.checkPassword(pwd_et.getText().toString().trim())
                || ((!TextUtils.isEmpty(mInviteCodeEt.getText().toString().trim()))
                && !RegexUtils.checkPassword(mInviteCodeEt.getText().toString().trim())
        )) {
            ToastUtil.showTip(getActivity(), R.string.lyy_game_password_rules);
            return;
        }
        if (!RegexUtils.checkPassword(pwd_et.getText().toString().trim())
                && pwd_et.getText().toString().length() < 8
                && pwd_et.getText().toString().length() > 14) {
            ToastUtil.showTip(getActivity(), R.string.lyy_game_password_num);
            return;
        }
        if ((!TextUtils.isEmpty(mInviteCodeEt.getText().toString().trim())
                && mInviteCodeEt.getText().toString().trim().length() > 8)) {
            ToastUtil.showTip(getActivity(), R.string.lyy_game_password_code_rules);
            return;
        }
        if (type == RegisterOrRetrieveActivity.TYPE_REGISTER) {
            mPresenter.requestRegOrRetrieve(mPhone, pwd_et.getText().toString().trim(),
                    mInviteCodeEt.getText().toString().trim(), mSmsCode, State.OperateType.REGISTER);
        } else if (type == RegisterOrRetrieveActivity.TYPE_RETRIEVE) {
            mPresenter.requestResetPassword(mPhone, pwd_et.getText().toString().trim());
        }
    }

    @OnClick(R.id.account_agreement)
    void onAgreementClick(View view) {
        ActivityCompat.startActivity(getActivity(), WebLocationActivity.getCallingIntent(getActivity(),
                WebLocationActivity.USER_AGREEMENT, getActivity().getString(R.string.lyy_user_agreement_title)), null);
    }

    private TextWatcher mPwdWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            watchEditText();
        }
    };

    public void watchEditText() {
        boolean enabled = true;
        for (EditText editText : mEditTextNeedWatched) {
            enabled = enabled && !TextUtils.isEmpty(editText.getText()) && editText.getText().toString().trim().length() >= AccountCons.PWD_MIN_LEN && editText.getText().toString().trim().length() <= AccountCons.PWD_MAX_LEN;
        }

        next_btn.setEnabled(enabled);
    }

    private void handleInviteLayout() {
        if (type == RegisterOrRetrieveActivity.TYPE_REGISTER) {
            mInviteLayout.setVisibility(View.VISIBLE);
            mInviteDivide.setVisibility(View.VISIBLE);
        } else {
            mInviteLayout.setVisibility(View.GONE);
            mInviteDivide.setVisibility(View.GONE);
        }
    }

}
