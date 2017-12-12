package com.hhly.lyygame.presentation.view.pay.bankpay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hhly.lyygame.R;
import com.hhly.lyygame.data.db.entry.UserInfo;
import com.hhly.lyygame.data.db.manager.AccountManager;
import com.hhly.lyygame.data.net.protocol.pay.QuickPayApplyReq;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.ToastUtil;
import com.hhly.lyygame.presentation.view.immersive.IImmersiveApply;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;

import static com.hhly.lyygame.presentation.view.pay.bankpay.BankCardPayActivity.AMOUNT_EXTRA_KEY;

/**
 * 银行卡支付
 * Created by dell on 2017/5/13.
 */

public class BankCardPayFragment extends BaseFragment implements IImmersiveApply, BankCardPayContract.View {

    @BindView(R.id.order_amount_tv)
    TextView mOrderAmountTv;
    @BindView(R.id.account_tv)
    TextView mAccountTv;
    @BindView(R.id.pay_bank_tv)
    TextView mPayBankTv;
    @BindView(R.id.card_number_tv)
    EditText mCardNumberTv;
    @BindView(R.id.select_bank_rl)
    RelativeLayout mSelectBankRl;

    private Integer mBankCategoryId;


    private BankCardPayContract.Presenter mPresenter;

    public static BankCardPayFragment newInstance(int amount) {
        Bundle args = new Bundle();
        args.putInt(AMOUNT_EXTRA_KEY, amount);
        BankCardPayFragment fragment = new BankCardPayFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        mOrderAmountTv.setText(getString(R.string.lyy_game_pay_rmb, getArguments().getInt(AMOUNT_EXTRA_KEY)));
        new BankCardPayPresenter(this);
        UserInfo userInfo = AccountManager.getInstance().getUserInfo();
        mAccountTv.setText(userInfo.getUserId());
    }

    @Override
    protected void fetchData(boolean isLoadMore) {

    }

    @OnClick(R.id.next_step_btn)
    public void onViewClicked() {
        if (mBankCategoryId == null) {
            ToastUtil.showTip(getActivity(), getString(R.string.lyy_game_bank_support_card_type));
            return;
        }
        if (TextUtils.isEmpty(mCardNumberTv.getText().toString().trim())
                || mCardNumberTv.getText().toString().trim().length() < 12
                || mCardNumberTv.getText().toString().trim().length() > 19) {
            ToastUtil.showTip(getActivity(), getString(R.string.lyy_game_bank_support_card_num));
            return;
        }
        showLoading();
        QuickPayApplyReq model = new QuickPayApplyReq();
        model.setTotalFee(mOrderAmountTv.getText().toString().trim().substring(0, mOrderAmountTv.getText().toString().trim().length() - 1));
        model.setBuyerId(mAccountTv.getText().toString().trim());
        model.setDcType(String.valueOf(mBankCategoryId));
        model.setBankName(mPayBankTv.getText().toString().trim());
        model.setAccNo(mCardNumberTv.getText().toString().trim());
        mPresenter.getOrderInfo(model);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_bank_card_pay;
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
        return 1;
    }


    @OnClick(R.id.select_bank_rl)
    public void onSelectBank() {
        startActivityForResult(SupportBankActivity.getCallingIntent(getActivity()), 100);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && data != null) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                mBankCategoryId = bundle.getInt(SupportBankFragment.EXTRA_CATEGORY, SupportBankFragment.DEPOSIT);
                mPayBankTv.setText(bundle.getString(SupportBankFragment.KEY_BANKNAME)
                        + (mBankCategoryId == SupportBankFragment.DEPOSIT ? getString(R.string.lyy_game_bank_deposit_card) : getString(R.string.lyy_game_bank_credit_card)));
            }
        }
    }

    @Override
    public void setPresenter(BankCardPayContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void getOrderInfoSuccess(QuickPayApplyReq model) {
        dismissLoading();
        Logger.d(model);
        ActivityCompat.startActivity(getContext(), VerifyBankCardInfoActivity.getCallIntent(getContext(), model), null);
    }

    @Override
    public void getOrderInfoFailure(String msg) {
        dismissLoading();
        ToastUtil.showTip(getActivity(), !TextUtils.isEmpty(msg) ? msg : getString(R.string.lyy_game_request_error));
    }
}
