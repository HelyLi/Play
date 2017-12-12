package com.hhly.lyygame.presentation.view.shareactivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;

import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.utils.ActivityUtil;
import com.hhly.lyygame.presentation.view.BaseActivity;
import com.hhly.lyygame.presentation.view.share.ShareContent;
import com.sina.weibo.sdk.api.share.BaseResponse;
import com.sina.weibo.sdk.api.share.IWeiboHandler;

/**
 * Created by ${HELY} on 17/4/13.
 * 邮箱：heli.lixiong@gmail.com
 */

public class ShareWebActivity extends BaseActivity implements IWeiboHandler.Response {

    public static final String EXTRA_SHARE = "extra_share";

    public static Intent getCallingIntent(Context context, ShareContent shareContent) {
        Intent intent = new Intent(context, ShareWebActivity.class);
        intent.putExtra(EXTRA_SHARE, shareContent);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        ShareContent shareContent = null;

        if (intent != null) {
            shareContent = intent.getParcelableExtra(EXTRA_SHARE);
        }

        if (shareContent == null) {
            finish();
            return;
        }

        ShareWebFragment fragment = (ShareWebFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (fragment == null) {
            fragment = ShareWebFragment.newInstance(shareContent);
            ActivityUtil.addFragment(getSupportFragmentManager(), fragment, R.id.content_container);
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_base_layout;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ShareWebFragment fragment = (ShareWebFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ShareWebFragment fragment = (ShareWebFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (fragment != null) {
            fragment.onNewIntent(intent);
        }
    }

    @Override
    public void onResponse(BaseResponse baseResponse) {
        ShareWebFragment fragment = (ShareWebFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (fragment != null) {
            fragment.onResponse(baseResponse);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        ShareWebFragment fragment = (ShareWebFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (fragment != null && KeyEvent.KEYCODE_BACK == keyCode) {
            if(fragment.onKeyDown(keyCode, event)){
                return true;
            }else {
                return super.onKeyDown(keyCode, event);
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
