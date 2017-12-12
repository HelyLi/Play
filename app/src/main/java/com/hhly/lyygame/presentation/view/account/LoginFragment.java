package com.hhly.lyygame.presentation.view.account;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.xmxu.cf.Callback;
import com.github.xmxu.cf.Caller;
import com.github.xmxu.cf.Cuttlefish;
import com.github.xmxu.cf.LoginResult;
import com.github.xmxu.cf.Result;
import com.github.xmxu.cf.qq.QQLoginHandler;
import com.github.xmxu.cf.sina.WeiboLoginHandler;
import com.github.xmxu.cf.wechat.WechatLoginHandler;
import com.hhly.lyygame.App;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.db.manager.AccountManager;
import com.hhly.lyygame.data.net.protocol.user.State;
import com.hhly.lyygame.presentation.utils.NetworkUtil;
import com.hhly.lyygame.presentation.utils.StatisticalUtil;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.ToastUtil;
import com.hhly.lyygame.presentation.view.account.opt.RegisterOrRetrieveActivity;
import com.hhly.lyygame.presentation.view.immersive.IImmersiveApply;
import com.hhly.lyygame.presentation.view.main.MainTabActivity;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by hely on 2016/11/24.
 */

public class LoginFragment extends BaseFragment implements IImmersiveApply, LoginContact.View {

    @BindView(R.id.account_et)
    EditText mAccountEt;
    @BindView(R.id.account_select_iv)
    ImageView mAccountSelectIv;
    @BindView(R.id.password_et)
    EditText mPasswordEt;
    @BindView(R.id.password_visibility_iv)
    ImageView mPasswordVisibilityIv;
    @BindView(R.id.login_btn)
    Button mLoginBtn;
    @BindView(R.id.switch_to_register_tv)
    TextView mSwitchToRegisterTv;
    @BindView(R.id.switch_to_fp_tv)
    TextView mSwitchToFpTv;
    @BindView(R.id.login_wechat_tv)
    TextView mLoginWechatTv;
    @BindView(R.id.login_weibo_tv)
    TextView mLoginWeiboTv;
    @BindView(R.id.login_qq_tv)
    TextView mLoginQqTv;

    private LoginContact.Presenter mPresenter;

    private Caller mCurrentCaller;

    private boolean isPwdVisible = false;
    private boolean isNeedStartMain = false;

    public static final String EXTRA_NEED_START_MAIN = "extra_need_start_main";

    public static LoginFragment newInstance(boolean isNeedStartMain) {
        LoginFragment fragment = new LoginFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(EXTRA_NEED_START_MAIN, isNeedStartMain);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            isNeedStartMain = getArguments().getBoolean(EXTRA_NEED_START_MAIN, true);
        }
        if (savedInstanceState != null) {
            if (mCurrentCaller != null) {
                mCurrentCaller.handler().onNewIntent(getActivity().getIntent());
            }
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login_layout;
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        mToolbarHelper.setTitle("");
        mToolbarHelper.applyScroll(0);
        Toolbar toolbar = mToolbarHelper.getToolbar();
        toolbar.inflateMenu(R.menu.login_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.menu_login_close) {
                    onBackPressed();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void fetchData(boolean isLoadMore) {

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
    protected boolean enableHomeAsUp() {
        return false;
    }

    @Override
    protected boolean enableSetActionBar() {
        return false;
    }

    @Override
    public float initAlpha() {
        return 0f;
    }

    @OnClick({R.id.switch_to_register_tv, R.id.switch_to_fp_tv,
            R.id.password_visibility_iv, R.id.account_select_iv, R.id.login_btn,
            R.id.login_wechat_tv, R.id.login_weibo_tv, R.id.login_qq_tv})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.switch_to_register_tv:
                ActivityCompat.startActivity(getActivity(),
                        RegisterOrRetrieveActivity.getCallingIntent(getActivity(),
                                RegisterOrRetrieveActivity.TYPE_REGISTER), null);
                break;
            case R.id.switch_to_fp_tv:
                ActivityCompat.startActivity(getActivity(),
                        RegisterOrRetrieveActivity.getCallingIntent(getActivity(),
                                RegisterOrRetrieveActivity.TYPE_RETRIEVE), null);
                break;
            case R.id.password_visibility_iv:
                if (isPwdVisible) {
                    isPwdVisible = false;
                    mPasswordVisibilityIv.setSelected(false);
                    mPasswordEt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    mPasswordEt.setSelection(mPasswordEt.getText().length());
                } else {
                    isPwdVisible = true;
                    mPasswordVisibilityIv.setSelected(true);
                    mPasswordEt.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    mPasswordEt.setSelection(mPasswordEt.getText().length());
                }
                break;
            case R.id.account_select_iv:
                break;
            case R.id.login_btn://
                if (checkAccountAndPwd(mAccountEt.getText().toString().trim(), mPasswordEt.getText().toString().trim())) {
                    mPresenter.login(mAccountEt.getText().toString().trim(), mPasswordEt.getText().toString().trim());
                }
                break;
            case R.id.login_wechat_tv:
                if (!NetworkUtil.isAvailable(App.getContext())) {
                    ToastUtil.showTip(App.getContext(), R.string.lyy_network_notwork);
                    return;
                }
                mCurrentCaller = Cuttlefish.with(getActivity()).login().callback(mLoginResultCallback).tag(State.ThirdType.WECHAT).to(WechatLoginHandler.get());
                break;
            case R.id.login_weibo_tv:
                if (!NetworkUtil.isAvailable(App.getContext())) {
                    ToastUtil.showTip(App.getContext(), R.string.lyy_network_notwork);
                    return;
                }
                mCurrentCaller = Cuttlefish.with(getActivity()).login().callback(mLoginResultCallback).tag(State.ThirdType.WEIBO).to(WeiboLoginHandler.get());
                break;
            case R.id.login_qq_tv:
                if (!NetworkUtil.isAvailable(App.getContext())) {
                    ToastUtil.showTip(App.getContext(), R.string.lyy_network_notwork);
                    return;
                }
                mCurrentCaller = Cuttlefish.with(getActivity()).login().callback(mLoginResultCallback).tag(State.ThirdType.QQ).to(QQLoginHandler.get());
                break;
            default:
                break;
        }
    }

    private boolean checkAccountAndPwd(String account, String pwd) {

        if (TextUtils.isEmpty(account)) {//
            ToastUtil.showTip(getActivity(), R.string.lyy_account_hint_account);
            return false;
        } else if (TextUtils.isEmpty(pwd)) {
            ToastUtil.showTip(getActivity(), R.string.lyy_account_hint_password);
            return false;
        } else if (pwd.length() < 6) {
            ToastUtil.showTip(getActivity(), R.string.lyy_account_reset_password_error);
            return false;
        }
        return true;
    }

    @Override
    public void doLoginSuccess() {
        if (isNeedStartMain) {
            Logger.d("Login.MainTab");
            ActivityCompat.startActivity(getActivity(), MainTabActivity.getCallIntent(getActivity(),true), null);
        }
        if (AccountManager.getInstance().getUserInfo() != null) {
            StatisticalUtil.onProfileSignIn(AccountManager.getInstance().getUserInfo().getLoginType(), AccountManager.getInstance().getUserInfo().getUserId());
            Logger.d("Login.MainTab  StatisticalUtil type = " + AccountManager.getInstance().getUserInfo().getLoginType());
        }
        getActivity().setResult(Activity.RESULT_OK);
        onBackPressed();
    }

    public void setPresenter(LoginContact.Presenter gamePresenter) {
        mPresenter = gamePresenter;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    private Callback<LoginResult> mLoginResultCallback = new Callback<LoginResult>() {
        @Override
        public void onFailure(Result result) {
            if (Result.Code.NO_CLIENT == result.getErrorCode()){
                ToastUtil.showTip(getActivity(), result.getErrorMsg());
            } else {
                ToastUtil.showTip(getActivity(), "用户取消登录");
            }
        }

        @Override
        public void onComplete(LoginResult result) {
            //            ToastUtil.showTip(getActivity(), String.format("登录成功：uid=%s", result.getUid()));
            mPresenter.thirdLogin(result.getAccessToken(), (int) result.getTag(), (int) result.getTag() == 2 ? result.getUid() : result.getAppId());
        }
    };

    public void onNewIntent(Intent intent) {
        if (mCurrentCaller != null) {
            mCurrentCaller.handler().onNewIntent(intent);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Logger.d("onActivityResult");
        super.onActivityResult(requestCode, resultCode, data);
        if (mCurrentCaller != null)
            mCurrentCaller.handler().onActivityResult(requestCode, resultCode, data);
    }

}
