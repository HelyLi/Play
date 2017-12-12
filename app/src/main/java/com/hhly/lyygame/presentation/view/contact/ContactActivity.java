package com.hhly.lyygame.presentation.view.contact;

import android.content.Context;
import android.content.Intent;

import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.view.BaseActivity;
import com.hhly.lyygame.presentation.view.immersive.IImmersiveApply;

/**
 * 联系我们
 * Created by Simon on 2016/12/2.
 */

public class ContactActivity extends BaseActivity implements IImmersiveApply {

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, ContactActivity.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_contact_layout;
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
