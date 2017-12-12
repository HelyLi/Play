package com.hhly.lyygame.presentation.view.account.opt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.view.BaseActivity;
import com.hhly.lyygame.presentation.view.immersive.IImmersiveApply;


/**
 * Created by Simon on 16/9/8.
 */
public class RegisterOrRetrieveActivity extends BaseActivity implements IImmersiveApply {

    private static final String TAG = "RegisterOrRetrieveActivity";

    public static final String EXTRA_TYPE = "extra_type";
    public static final String EXTRA_PHONE = "extra_phone";
    public static final String EXTRA_SMSCODE = "extra_smsCode";
    public static final int TYPE_REGISTER = 0;//注册
    public static final int TYPE_RETRIEVE = 1;//找回密码
    private int mType = TYPE_REGISTER;

    private RegisterOrRetrievePresenter mRegisterOrRetrievePresenter;

    public static Intent getCallingIntent(Context context, int type) {
        Intent intent = new Intent(context, RegisterOrRetrieveActivity.class);
        intent.putExtra(EXTRA_TYPE, type);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent() != null) {
            Intent intent = getIntent();
            mType = intent.getIntExtra(EXTRA_TYPE, TYPE_REGISTER);
        }

        if (savedInstanceState == null) {
            RegisterOrRetrieveFragment fragment = RegisterOrRetrieveFragment.newInstance(mType);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content_container, fragment);
            transaction.commitAllowingStateLoss();
            mRegisterOrRetrievePresenter = new RegisterOrRetrievePresenter(fragment);
        }

        if (mToolbarHelper != null) {
            if (mType == TYPE_REGISTER) {
                mToolbarHelper.setTitle(getString(R.string.lyy_account_title_register));
            } else if (mType == TYPE_RETRIEVE) {
                mToolbarHelper.setTitle(getString(R.string.lyy_account_title_pwd_retrieve));
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_account_reg_or_retri_layout;
    }

    public void doNextPage (String phone, String smsCode) {
        RegisterOrRetrieveFragment02 fragment02 = RegisterOrRetrieveFragment02.newInstance(mType, phone, smsCode);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_container, fragment02);
        transaction.addToBackStack("Register02");
        transaction.commitAllowingStateLoss();
        new RegisterOrRetrievePresenter02(fragment02);
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
}
