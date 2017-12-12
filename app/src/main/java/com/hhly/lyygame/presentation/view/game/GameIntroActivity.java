package com.hhly.lyygame.presentation.view.game;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.utils.ActivityUtil;
import com.hhly.lyygame.presentation.view.BaseActivity;
import com.hhly.lyygame.presentation.view.immersive.IImmersiveApply;
import com.hhly.lyygame.presentation.view.main.MainTabActivity;

/**
 * 游戏详情
 * Created by Simon on 2016/12/1.
 */

public class GameIntroActivity extends BaseActivity  implements IImmersiveApply {

    /**
     * @param context
     * @param gameId
     * @param jump    默认 false 不跳转； true 跳转主页
     * @return
     */
    public static Intent getCallingIntent(Context context, int gameId, boolean jump) {

        Intent intent = new Intent(context, GameIntroActivity.class);
        intent.putExtra(GameIntroFragment.GAME_INTRO_ID, gameId);
        intent.putExtra(GameIntroFragment.GAME_AD_JUMP, jump);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent().getBooleanExtra(GameIntroFragment.GAME_AD_JUMP, false)) {
            Toolbar toolbar = mToolbarHelper.getToolbar();
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ActivityCompat.startActivity(GameIntroActivity.this, MainTabActivity.getCallIntent(getContext()), null);
                    GameIntroActivity.this.finish();
                }
            });
        }

        GameIntroFragment fragment = (GameIntroFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (fragment == null) {
            fragment = GameIntroFragment.newInstance(getIntent().getIntExtra(GameIntroFragment.GAME_INTRO_ID, 0));
            ActivityUtil.addFragment(getSupportFragmentManager(), fragment, R.id.content_container);
        }
        new IntroPresenterImpl(fragment);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mToolbarHelper!=null)
            mToolbarHelper.updateAlpha(initAlpha());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_game_intro_layout;
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

    @Override
    public void onBackPressed() {
        if (getIntent().getBooleanExtra(GameIntroFragment.GAME_AD_JUMP, false)) {
            ActivityCompat.startActivity(GameIntroActivity.this, MainTabActivity.getCallIntent(getContext()), null);
            GameIntroActivity.this.finish();
        }else {
            super.onBackPressed();
        }
    }

}
