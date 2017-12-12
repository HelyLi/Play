package com.hhly.lyygame.presentation.view.pay.bankpay;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.protocol.pay.QuickPayApplyReq;
import com.hhly.lyygame.data.net.protocol.pay.QuickPayApplyResp;
import com.hhly.lyygame.data.net.protocol.pay.QuickPayConfirmReq;
import com.hhly.lyygame.presentation.utils.NetworkUtil;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.DialogFactory;
import com.hhly.lyygame.presentation.view.ToastUtil;
import com.hhly.lyygame.presentation.view.immersive.IImmersiveApply;
import com.hhly.lyygame.presentation.view.web.WebLocationActivity;
import com.hhly.lyygame.presentation.view.widget.RecyclerViewListDivide;

import butterknife.BindView;
import butterknife.OnClick;

import static com.hhly.lyygame.presentation.view.pay.bankpay.VerifyBankCardInfoActivity.INFO_EXTRA_KEY;

/**
 * 验证银行卡信息
 * Created by dell on 2017/5/13.
 */

public class VerifyBankCardInfoFragment extends BaseFragment implements IImmersiveApply,
        VerifyBankCardInfoContract.View {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.verify_info_btn)
    Button mVerifyInfoBtn;
    private VerifyBankCardInfoAdapter mAdapter;
    private VerifyBankCardInfoContract.Presenter mPresenter;


    public static VerifyBankCardInfoFragment newInstance(QuickPayApplyReq req) {
        Bundle args = new Bundle();
        args.putSerializable(INFO_EXTRA_KEY, req);
        VerifyBankCardInfoFragment fragment = new VerifyBankCardInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        new VerifyBankcardInfoPresenter(this);
        mAdapter = new VerifyBankCardInfoAdapter();
        mRecyclerView.addItemDecoration(new RecyclerViewListDivide(getContext(),
                R.color.color_e9e9e9, RecyclerViewListDivide.VERTICAL_LIST, 1));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
        View footerView = LayoutInflater.from(getContext()).inflate(R.layout.verify_bank_card_info_footer_layout, null);
        CheckBox checkBox = (CheckBox) footerView.findViewById(R.id.protocol_check_box);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mVerifyInfoBtn.setSelected(isChecked);
                mVerifyInfoBtn.setEnabled(isChecked);
            }
        });
        TextView protocolTv = (TextView) footerView.findViewById(R.id.protocol_tv);
        protocolTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.startActivity(getActivity(), WebLocationActivity.getCallingIntent(getActivity(),
                        WebLocationActivity.PAYMENT_AGREEMENT, getActivity().getString(R.string.lyy_game_bank_payment_agreement)), null);
            }
        });
        mAdapter.addFooterView(footerView);
        mAdapter.updateItems(getContext(),
                (QuickPayApplyReq) getArguments().getSerializable(INFO_EXTRA_KEY),
                getFragmentManager());
    }

    @Override
    protected void fetchData(boolean isLoadMore) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_verify_bank_card_info;
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

    @Override
    public void setPresenter(VerifyBankCardInfoContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @OnClick(R.id.verify_info_btn)
    public void onViewClicked() {
        if (!NetworkUtil.isAvailable(getActivity())) {
            ToastUtil.showTip(getActivity(), getString(R.string.lyy_game_network_state));
            return;
        }
        if (mAdapter.verifyInput(getContext())) {
            QuickPayApplyReq req = mAdapter.getQuickPayApplyReq(getContext());
            showLoading();
            mPresenter.quickPayApply(req);
        }
    }

    @Override
    public void quickPayApplySuccess(String phone, QuickPayApplyResp.DataBean resp) {
        dismissLoading();
        QuickPayApplyReq quick = (QuickPayApplyReq) getArguments().getSerializable(INFO_EXTRA_KEY);
        QuickPayConfirmReq req = new QuickPayConfirmReq();
        req.setOutTradeNo(quick.getOutTradeNo());
        req.setBusinessNo(resp.getBusinessNo());
        req.setExtendParams(quick.getExtendParams());
        req.setTotalFee(quick.getTotalFee());
        startActivity(SmsValidateActivity.getCallingIntent(getActivity(), phone, resp.getTn(), req));
    }

    @Override
    public void quickPayApplyFailure(String msg) {
        dismissLoading();
        if ("0001".equals(msg)) {
            VerifyBankCardDialog dialog = DialogFactory.createVerifyBankCardDialog();
            dialog.show(getFragmentManager(), "dialog");

        } else {
            ToastUtil.showTip(getContext(), TextUtils.isEmpty(msg) ? getString(R.string.lyy_game_request_error) : msg);
        }
    }

}
