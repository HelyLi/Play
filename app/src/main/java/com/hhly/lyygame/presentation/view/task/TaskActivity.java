package com.hhly.lyygame.presentation.view.task;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.utils.ActivityUtil;
import com.hhly.lyygame.presentation.view.BaseActivity;

/**
 * Created by Simon on 2016/11/29.
 */

public class TaskActivity extends BaseActivity {

    public static final int TASK_REQUESTCODE = 1;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, TaskActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TaskFragment fragment = (TaskFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (fragment == null) {
            fragment = TaskFragment.newInstance();
            ActivityUtil.addFragment(getSupportFragmentManager(), fragment, R.id.content_container);
        }
        new TaskPresenter(fragment);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        TaskFragment fragment = (TaskFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

}
