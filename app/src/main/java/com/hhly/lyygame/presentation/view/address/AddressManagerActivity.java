package com.hhly.lyygame.presentation.view.address;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.utils.ActivityUtil;
import com.hhly.lyygame.presentation.view.BaseActivity;

/**
 * 收货地址管理
 * Created by Simon on 2016/11/28.
 */

public class AddressManagerActivity extends BaseActivity {

    public static final int RQ_ADDRESS_EDIT = 1009;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, AddressManagerActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AddressManagerFragment fragment = (AddressManagerFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (fragment == null) {
            fragment = AddressManagerFragment.newInstance();
            ActivityUtil.addFragment(getSupportFragmentManager(), fragment, R.id.content_container);
        }
        new AddressManagerPresenter(fragment);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_base_layout;
    }


}
