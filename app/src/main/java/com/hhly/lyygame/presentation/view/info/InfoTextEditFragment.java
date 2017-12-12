package com.hhly.lyygame.presentation.view.info;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.ToastUtil;
import com.hhly.lyygame.presentation.view.immersive.IImmersiveApply;
import com.orhanobut.logger.Logger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * Created by Simon on 2016/11/26.
 */

public class InfoTextEditFragment extends BaseFragment implements IImmersiveApply {

    public static final String EXTRA_TYPE = "extra_type";
    public static final String EXTRA_CONTENT = "extra_content";
    @BindView(R.id.input_et)
    EditText mInputEt;

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

    @IntDef({TYPE_NICKNAME, TYPE_QQ, TYPE_ADDRESS})
    @Retention(RetentionPolicy.SOURCE)
    @interface Type {
    }

    public static final int TYPE_NICKNAME = 0;
    public static final int TYPE_QQ = 1;
    public static final int TYPE_ADDRESS = 2;

    public static final String KEY_NICKNAME = "info_key_nickname";
    public static final String KEY_QQ = "info_key_qq";
    public static final String KEY_ADDRESS = "info_key_address";

    private int mType = TYPE_NICKNAME;
    private String mContent;

    public static InfoTextEditFragment newInstance(@Type int type, String content) {
        InfoTextEditFragment fragment = new InfoTextEditFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_TYPE, type);
        bundle.putString(EXTRA_CONTENT, content);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            mType = bundle.getInt(EXTRA_TYPE, TYPE_NICKNAME);
            mContent = bundle.getString(EXTRA_CONTENT);
        }
        switch (mType) {
            case TYPE_NICKNAME:
                mToolbarHelper.setTitle(getString(R.string.lyy_edit_nickname));
                mInputEt.setHint(R.string.lyy_hint_edit_nickname);
                mInputEt.setInputType(InputType.TYPE_CLASS_TEXT);
                break;
            case TYPE_QQ:
                mToolbarHelper.setTitle(getString(R.string.lyy_edit_qq));
                mInputEt.setHint(R.string.lyy_hint_edit_qq);
                mInputEt.setInputType(InputType.TYPE_CLASS_NUMBER);
                break;
            case TYPE_ADDRESS:
                mToolbarHelper.setTitle(getString(R.string.lyy_info_address));
                mInputEt.setHint(R.string.lyy_info_hint_address);
                mInputEt.setInputType(InputType.TYPE_CLASS_TEXT);
                mInputEt.setSingleLine(false);
                break;
        }

        Logger.d("mContent=" + mContent);
        if (!TextUtils.isEmpty(mContent)) {
            mInputEt.setText(mContent);
            mInputEt.selectAll();
            mInputEt.setFocusable(true);
        }

        Toolbar toolbar = mToolbarHelper.getToolbar();
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.menu_edit_save) {
                    if (mType == TYPE_ADDRESS && mInputEt.getText().toString().trim().length() > 80) {
                        ToastUtil.showTip(getActivity(), R.string.lyy_info_address_edit);
                        return true;
                    }
                    resultValue();
                    onBackPressed();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void fetchData(boolean isLoadMore) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_text_edit_layout;
    }

    //昵称
    @OnTextChanged(value = R.id.input_et, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void onInputChanged(Editable editable) {
        if (editable.toString().trim().length() > 0) {
            mInputEt.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.text_size_16));
        } else {
            mInputEt.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.text_size_14));
        }

    }

    @OnClick(R.id.clear_iv)
    void onClearClick(View view) {
        mInputEt.getText().clear();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.edit_save_menu, menu);
    }

    private void resultValue() {
        if (TextUtils.isEmpty(mInputEt.getText().toString().trim()) || mInputEt.getText().toString().trim().equals(mContent)) {
            return;
        }

        Intent intent = new Intent();
        Bundle bundle = new Bundle();

        switch (mType) {
            case TYPE_NICKNAME:
                Logger.d("TYPE_NICKNAME");
                bundle.putString(KEY_NICKNAME, mInputEt.getText().toString().trim());
                intent.putExtras(bundle);
                getActivity().setResult(Activity.RESULT_OK, intent);

                break;
            case TYPE_QQ:
                bundle.putString(KEY_QQ, mInputEt.getText().toString().trim());
                intent.putExtras(bundle);
                getActivity().setResult(Activity.RESULT_OK, intent);

                break;
            case TYPE_ADDRESS:
                bundle.putString(KEY_ADDRESS, mInputEt.getText().toString().trim());
                intent.putExtras(bundle);
                getActivity().setResult(Activity.RESULT_OK, intent);
                break;
            default:
                break;
        }
    }

}
