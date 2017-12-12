package com.hhly.lyygame.presentation.view.address;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.protocol.user.UserAddressResp;
import com.hhly.lyygame.presentation.utils.ActivityUtil;
import com.hhly.lyygame.presentation.view.BaseActivity;

/**
 * 地址编辑
 * Created by Simon on 2016/11/28.
 */

public class AddressEditActivity extends BaseActivity {

    public static Intent getCallingIntent(Context context, UserAddressResp.AddressBean addressBean) {
        Intent intent = new Intent(context, AddressEditActivity.class);
        intent.putExtra(AddressEditFragment.ADDRESS_EDIT, addressBean);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UserAddressResp.AddressBean addressBean = getIntent().getParcelableExtra(AddressEditFragment.ADDRESS_EDIT);

        AddressEditFragment fragment = (AddressEditFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (fragment == null) {
            fragment = AddressEditFragment.newInstance(addressBean);
            ActivityUtil.addFragment(getSupportFragmentManager(), fragment, R.id.content_container);
        }
        new AddressEditPresenter(fragment);
    }
}
