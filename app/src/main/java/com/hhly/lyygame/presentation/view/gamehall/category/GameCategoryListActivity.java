package com.hhly.lyygame.presentation.view.gamehall.category;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.utils.ActivityUtil;
import com.hhly.lyygame.presentation.view.BaseActivity;

/**
 * Created by Simon on 2016/11/24.
 */



public class GameCategoryListActivity extends BaseActivity {

    public static String GAME_CATEGORY = "extra_game_category";

    public static Intent getCallingIntent(Context context,@NonNull GameCategory category) {

        Intent intent = new Intent(context, GameCategoryListActivity.class);
        intent.putExtra(GAME_CATEGORY, category);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GameCategoryListFragment fragment = (GameCategoryListFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (fragment == null) {
            fragment = GameCategoryListFragment.newInstance((GameCategory)getIntent().getSerializableExtra(GAME_CATEGORY));
            ActivityUtil.addFragment(getSupportFragmentManager(), fragment, R.id.content_container);
        }
        new GategoryListPresenter(fragment);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_base_layout;
    }


}
