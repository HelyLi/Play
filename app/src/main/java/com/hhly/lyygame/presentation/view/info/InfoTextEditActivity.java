package com.hhly.lyygame.presentation.view.info;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;

import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.view.BaseActivity;

/**
 * Created by Simon on 2016/11/26.
 */

public class InfoTextEditActivity extends BaseActivity {

    public static Intent getCallingIntent(Context context, @InfoTextEditFragment.Type int type, String content) {
        Intent intent = new Intent(context, InfoTextEditActivity.class);
        intent.putExtra(InfoTextEditFragment.EXTRA_TYPE, type);
        intent.putExtra(InfoTextEditFragment.EXTRA_CONTENT, content);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int type = getIntent().getIntExtra(InfoTextEditFragment.EXTRA_TYPE, InfoTextEditFragment.TYPE_NICKNAME);//
        startTextEdit(type , getIntent().getStringExtra(InfoTextEditFragment.EXTRA_CONTENT));
    }

    void startTextEdit(@InfoTextEditFragment.Type int type, String content) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_container, InfoTextEditFragment.newInstance(type, content));
        transaction.commit();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_base_layout;
    }


}
