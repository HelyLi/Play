package com.hhly.lyygame.presentation.view.info;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hhly.lyygame.R;
import com.hhly.lyygame.data.db.entry.UserInfo;
import com.hhly.lyygame.data.db.manager.AccountManager;
import com.hhly.lyygame.presentation.utils.IDCardValidate;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.ToastUtil;
import com.hhly.lyygame.presentation.view.immersive.IImmersiveApply;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Simon on 2016/11/26.
 */

public class RealAuthFragment extends BaseFragment implements IImmersiveApply, RealAuthContract.View {

    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.name_et)
    EditText nameEt;
    @BindView(R.id.clear_iv)
    ImageView nameClearIv;
    @BindView(R.id.id_num_et)
    EditText idNumEt;
    @BindView(R.id.id_num_clear_iv)
    ImageView idNumClearIv;
    @BindView(R.id.error_tip_tv)
    TextView errorTipTv;
    @BindView(R.id.next_btn)
    Button nextBtn;

    RealAuthContract.Presenter mPresenter;
    UserInfo mUserInfo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new RealAuthPresenter(this);
        mUserInfo = AccountManager.getInstance().getUserInfo();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    protected void fetchData(boolean isLoadMore) {

    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {

        //未认证
        if (TextUtils.isEmpty(mUserInfo.getRealName()) || TextUtils.isEmpty(mUserInfo.getIdcarNo())) {
            switchNoAuth();
        } else {
            switchAuth();
        }
    }

    @Override
    public float initAlpha() {
        return 1.0f;
    }

    @Override
    public void authSuccess() {
        //        switchAuth();
        ToastUtil.showTip(getActivity(), R.string.lyy_success);
        if (getActivity() != null) {
            getActivity().setResult(Activity.RESULT_OK);
            onBackPressed();
        }
    }

    @Override
    public void authFailure(final String msg) {
        //此处注意是否为主线程
       post(new Runnable() {
            @Override
            public void run() {
                ToastUtil.showTip(getActivity(), msg);
            }
        });
    }

    @Override
    public void setPresenter(RealAuthContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_real_auth_layout;
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
    public boolean isActive() {
        return false;
    }

    //已经认证
    private void switchAuth() {
        titleTv.setText(R.string.lyy_game_auth_title_tips);
        nameEt.setText(mUserInfo.getRealName());
        nameEt.setFocusable(false);
        nameEt.setFocusableInTouchMode(false);
        nameEt.setTextColor(getResources().getColor(R.color.color_b4b4b4));
        String idCarNo = mUserInfo.getIdcarNo();
        idCarNo = idCarNo.substring(0, idCarNo.length() - 6) + "******";
        idNumEt.setText(idCarNo);
        idNumEt.setFocusable(false);
        idNumEt.setFocusableInTouchMode(false);
        idNumEt.setTextColor(getResources().getColor(R.color.color_b4b4b4));
        nextBtn.setText(R.string.lyy_info_authorized);
    }

    //未认证
    private void switchNoAuth() {
        titleTv.setText(R.string.lyy_game_no_auth_title_tips);
        nameEt.addTextChangedListener(mTextWatcher);
        idNumEt.addTextChangedListener(mTextWatcher);
    }

    @OnClick(R.id.next_btn)
    void onAuthClick(View view) {

        String name = nameEt.getText().toString().trim();
        String id = idNumEt.getText().toString().trim();
        try {
            if (TextUtils.isEmpty(IDCardValidate.IDCardValidate(id))) {
                mPresenter.completeAuth(name, id);
            } else {
                ToastUtil.showTip(getActivity(), IDCardValidate.IDCardValidate(id));
            }
        } catch (Exception e) {
            Logger.e(e.getMessage());
        }
    }

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            checkInputState();
        }

        @Override
        public void afterTextChanged(Editable s) {
            nameClearIv.setVisibility(nameEt.getText().length() > 0 ? View.VISIBLE : View.GONE);
            idNumClearIv.setVisibility(idNumEt.getText().length() > 0 ? View.VISIBLE : View.GONE);
        }
    };

    private void checkInputState() {
        String name = nameEt.getText().toString().trim();
        String idNum = idNumEt.getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(idNum) || idNum.length() != 18) {
            nextBtn.setEnabled(false);
            return;
        }
        nextBtn.setEnabled(true);
    }

    @OnClick(R.id.clear_iv)
    void onNameClearClick(View view) {
        nameEt.getText().clear();
        idNumEt.getText().clear();
    }

    @OnClick(R.id.id_num_clear_iv)
    void onNumClearClick(View view) {
        idNumEt.getText().clear();
    }

}
