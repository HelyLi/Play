package com.hhly.lyygame.presentation.view.transfer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.utils.ActivityUtil;
import com.hhly.lyygame.presentation.view.BaseActivity;

/**
 * Created by ${HELY} on 17/4/24.
 * 邮箱：heli.lixiong@gmail.com
 */

public class TransferActivity extends BaseActivity {

    public static Intent getCallingIntent(Context context, String drawType) {
        Intent intent = new Intent(context, TransferActivity.class);
        intent.putExtra(TransferFragment.TRANSFER_DRAW_TYPE, drawType);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TransferFragment fragment = (TransferFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (fragment == null) {
            fragment = TransferFragment.newInstance(getIntent().getStringExtra(TransferFragment.TRANSFER_DRAW_TYPE));
            ActivityUtil.addFragment(getSupportFragmentManager(), fragment, R.id.content_container);
        }
        new TransferPresenter(fragment);

    }

}
