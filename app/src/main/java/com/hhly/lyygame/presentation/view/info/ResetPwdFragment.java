package com.hhly.lyygame.presentation.view.info;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.hhly.lyygame.R;
import com.hhly.lyygame.data.db.manager.AccountManager;
import com.hhly.lyygame.presentation.utils.ActivityUtil;
import com.hhly.lyygame.presentation.utils.RegexUtils;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.ToastUtil;
import com.hhly.lyygame.presentation.view.immersive.IImmersiveApply;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ${HELY} on 17/2/8.
 * 邮箱：heli.lixiong@gmail.com
 */

public class ResetPwdFragment extends BaseFragment implements IImmersiveApply, ResetPwdContract.View {

    @BindView(R.id.old_et)
    EditText mOldEt;
    @BindView(R.id.old_show_pwd_iv)
    ImageView mOldShowPwdIv;
    @BindView(R.id.new_et)
    EditText mNewEt;
    @BindView(R.id.new_show_pwd_iv)
    ImageView mNewShowPwdIv;
    @BindView(R.id.reset_pw_confirm)
    Button mResetConfirm;

    private ResetPwdContract.Presenter mPresenter;

    public ResetPwdFragment() {
        new ResetPwdPresenter(this);
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {

        mOldShowPwdIv.setSelected(false);
        mNewShowPwdIv.setSelected(false);
        Toolbar toolbar = mToolbarHelper.getToolbar();
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.menu_edit_save) {
                    String oldPwd = mOldEt.getText().toString().trim();
                    String newPwd = mNewEt.getText().toString().trim();
                    if (!checkPwd(oldPwd, newPwd)) {
                        ToastUtil.showTip(getActivity(), R.string.lyy_account_reset_password_error);
                        return true;
                    }
                    mPresenter.resetPassword(oldPwd, newPwd);

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
        return R.layout.fragment_reset_password_layout;
    }

    @Override
    public void resetPwdSuccess() {
        AccountManager.getInstance().clearCurrentUserInfo();
        ActivityUtil.startLoginForNormal(getActivity());
        onBackPressed();
    }

    @Override
    public void resetPwdFailure(final String msg) {
        //注意是否为主线程
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                ToastUtil.showTip(getActivity(), msg);
            }
        });
    }

    @Override
    public void setPresenter(ResetPwdContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return false;
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

    /**
     * @param oldPwd
     * @param newPwd
     * @return
     */
    private boolean checkPwd(String oldPwd, String newPwd) {
        return TextUtils.isEmpty(oldPwd) || TextUtils.isEmpty(newPwd) || oldPwd.length() < 6 || newPwd.length() < 6 ? false : true;
    }

    //    @OnTextChanged(value = R.id.old_et, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    //    void onOldTextChanged(Editable editable) {
    //        if (editable.toString().trim().length() > 0) {
    //            mOldClearIv.setVisibility(View.VISIBLE);
    //        } else {
    //            mOldClearIv.setVisibility(View.GONE);
    //        }
    //    }
    //
    //    @OnTextChanged(value = R.id.new_et, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    //    void onNewTextChanged(Editable editable) {
    //        if (editable.toString().trim().length() > 0) {
    //            mNewClearIv.setVisibility(View.VISIBLE);
    //        } else {
    //            mNewClearIv.setVisibility(View.GONE);
    //        }
    //    }

    @OnClick(R.id.old_show_pwd_iv)
    void onOldClearClick() {
        //        mOldEt.setInputType();
        if (mOldShowPwdIv.isSelected()) {
            mOldShowPwdIv.setSelected(false);
            mOldEt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        } else {
            mOldShowPwdIv.setSelected(true);
            mOldEt.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        mOldEt.setSelection(mOldEt.getText().length());
    }

    @OnClick(R.id.new_show_pwd_iv)
    void onNewClearClick() {
        //        mNewEt.getText().clear();
        if (mNewShowPwdIv.isSelected()) {
            mNewShowPwdIv.setSelected(false);
            mNewEt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        } else {
            mNewShowPwdIv.setSelected(true);
            mNewEt.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        mNewEt.setSelection(mNewEt.getText().length());
    }

    @OnClick(R.id.reset_pw_confirm)
    void onResetPWConfirm(View view) {

        String oldPwd = mOldEt.getText().toString().trim();
        String newPwd = mNewEt.getText().toString().trim();
        //        if (!checkPwd(oldPwd, newPwd)){
        //            ToastUtil.showTip(getActivity(), R.string.lyy_account_reset_password_error);
        //            return;
        //        }

        if (TextUtils.isEmpty(oldPwd)) {
            ToastUtil.showTip(getActivity(), R.string.lyy_account_reset_password_old_pw_error);
            return;
        }
        if (TextUtils.isEmpty(newPwd)) {
            ToastUtil.showTip(getActivity(), R.string.lyy_account_reset_password_new_pw_error);
            return;
        }
        if (!RegexUtils.checkPassword(newPwd)) {
            ToastUtil.showTip(getActivity(), R.string.lyy_game_password_rules);
            return;
        }
        if (newPwd.length() < 8) {
            ToastUtil.showTip(getActivity(), R.string.lyy_account_reset_password_error);
            return;
        }
        mPresenter.resetPassword(oldPwd, newPwd);
    }

}
