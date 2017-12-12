package com.hhly.lyygame.presentation.view.info;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;

import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.utils.ActivityUtil;
import com.hhly.lyygame.presentation.view.BaseActivity;

/**
 * Created by Simon on 2016/11/25.
 */

public class InfoActivity extends BaseActivity {

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, InfoActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityUtil.addFragment(getSupportFragmentManager(), new InfoFragment(), R.id.content_container);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_base_layout;
    }

    void startTextEdit(@InfoTextEditFragment.Type int type, String content) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_container, InfoTextEditFragment.newInstance(type, content));
        transaction.addToBackStack("TextEdit");
        transaction.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        InfoFragment fragment = (InfoFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

}
