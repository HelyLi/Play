package com.hhly.lyygame.presentation.view.score;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.utils.ActivityUtil;
import com.hhly.lyygame.presentation.view.BaseActivity;
import com.hhly.lyygame.presentation.view.immersive.IImmersiveApply;

/**
 * 积分明细
 * Created by Simon on 2016/11/30.
 */

public class ScoreRecordActivity extends BaseActivity implements IImmersiveApply {

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, ScoreRecordActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityUtil.addFragment(getSupportFragmentManager(), ScoreRecordFragment.newInstance(), R.id.content_container);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_score_record_layout;
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
