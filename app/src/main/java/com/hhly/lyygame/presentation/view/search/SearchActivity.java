package com.hhly.lyygame.presentation.view.search;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;

import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.view.BaseActivity;
import com.hhly.lyygame.presentation.view.immersive.IImmersiveApply;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.OnTextChanged;

/**
 * 搜索
 * Created by Simon on 2016/12/3.
 */

public class SearchActivity extends BaseActivity implements IImmersiveApply {

    @BindView(R.id.toolbar_title_tv)
    EditText mToolbarTitleTv;
    @BindView(R.id.clear_iv)
    ImageView mClearIv;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, SearchActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mToolbarHelper.setTitle("");
        switchToInitializeFrame();
    }

    private void switchToInitializeFrame() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_container, SearchInitializeFragment.newInstance());
        transaction.commitAllowingStateLoss();
    }

    public void switchToResultFrame(@NonNull String keyword) {

        keyword = keyword.replace("%", "").replace("/","");

        mToolbarTitleTv.setText(keyword);
        mToolbarTitleTv.setSelection(keyword.length());

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_container, SearchResultFragment.newInstance(keyword));
        transaction.commitAllowingStateLoss();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_layout;
    }

    private int[] mLocationArr;
    @Override
    protected boolean enableHideInputSoft(int x, int y) {
        if (mLocationArr == null) {
            mLocationArr = new int[2];
        }
        mClearIv.getLocationInWindow(mLocationArr);
        return !new Rect(mLocationArr[0], mLocationArr[1], mLocationArr[0] + mClearIv.getWidth(), mLocationArr[1] + mClearIv.getHeight()).contains(x, y);
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
    protected boolean enableHomeAsUp() {
        return false;
    }

    @OnTextChanged(value = R.id.toolbar_title_tv, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void onSearchTextChanged(Editable editable) {
        if (editable.toString().trim().length() > 0) {
            mClearIv.setVisibility(View.VISIBLE);
        } else {
            mClearIv.setVisibility(View.GONE);
            switchToInitializeFrame();
        }
    }
    
    @OnEditorAction(R.id.toolbar_title_tv)
    boolean onSearchEditorAction(int actionId) {
        switch (actionId) {
            case EditorInfo.IME_ACTION_SEARCH:
            case EditorInfo.IME_ACTION_DONE:
            case EditorInfo.IME_ACTION_GO:
                //start to search
                if (mToolbarTitleTv.getText().toString().length() > 0) {
                    switchToResultFrame(mToolbarTitleTv.getText().toString().trim());
                }
                return true;
        }
        return false;
    }

    @OnClick({R.id.clear_iv, R.id.cancel_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.clear_iv:
                mToolbarTitleTv.getText().clear();
                break;
            case R.id.cancel_btn:
                onBackPressed();
                break;
        }
    }
}
