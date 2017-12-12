package com.hhly.lyygame.presentation.view.mall.category;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.utils.ActivityUtil;
import com.hhly.lyygame.presentation.view.BaseActivity;

import static com.hhly.lyygame.presentation.view.mall.category.MallCategoryListFragment.CATEGORY_ALL;

/**
 * Created by Simon on 2016/11/24.
 */



public class MallCategoryListActivity extends BaseActivity {

    public static Intent getCallingIntent(Context context, @MallCategoryListFragment.Category int category) {

        Intent intent = new Intent(context, MallCategoryListActivity.class);
        intent.putExtra(MallCategoryListFragment.EXTRA_CATEGORY, category);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MallCategoryListFragment fragment = (MallCategoryListFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (fragment == null) {
            fragment = MallCategoryListFragment.newInstance(getIntent().getIntExtra(MallCategoryListFragment.EXTRA_CATEGORY, CATEGORY_ALL));
            ActivityUtil.addFragment(getSupportFragmentManager(), fragment, R.id.content_container);
        }
        new MallListPresenter(fragment);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_base_layout;
    }


}
