package com.hhly.lyygame.presentation.view.transfer;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.protocol.transfer.TransferExchangeGameInfoResp;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.DialogFactory;
import com.hhly.lyygame.presentation.view.immersive.IImmersiveApply;
import com.hhly.lyygame.presentation.view.main.MainTabActivity;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

import static com.hhly.lyygame.presentation.view.DialogFactory.createTransfer;

/**
 * Created by ${HELY} on 17/4/24.
 * 邮箱：heli.lixiong@gmail.com
 */

public class CouponTransferFragment extends BaseFragment implements IImmersiveApply, CouponTransferContract.View {

    @BindView(R.id.trans_out_app_click_tv)
    TextView mTransOutAppClickTv;
    @BindView(R.id.trans_coins_balance_tv)
    TextView mTransCoinsBalanceTv;
    @BindView(R.id.trans_ratio_tv)
    TextView mTransRatioTv;
    @BindView(R.id.trans_out_coins_et)
    EditText mTransOutCoinsEt;
    @BindView(R.id.trans_in_coins_tv)
    TextView mTransInCoinsTv;
    @BindView(R.id.trans_min_amount)
    TextView mTransMinAmount;
    @BindView(R.id.trans_tip_tv)
    TextView mTransTipTv;
    @BindView(R.id.trans_sure_btn)
    Button mTransSureBtn;

    private CouponTransferContract.Presenter mPresenter;

    public static CouponTransferFragment newInstance() {
        return new CouponTransferFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_coupon_transfer_layout;
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {

        mTransRatioTv.setText(getString(R.string.lyy_game_transfer_coupon_ratio, 0, 0));
        mTransMinAmount.setText(getString(R.string.lyy_game_transfer_coupon_min_coins, 0));
    }

    @Override
    protected void fetchData(boolean isLoadMore) {

    }

    @Override
    public void setPresenter(CouponTransferContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @OnClick({R.id.trans_out_app_click_tv, R.id.trans_sure_btn})
    void onTransOutAppClick(View view) {
        switch (view.getId()) {
            case R.id.trans_out_app_click_tv:
                ActivityCompat.startActivityForResult(getActivity(), TransferActivity.getCallingIntent(getActivity(), "3"), GameTransferActivity.TRANSFER_GAME_COUPON_REQUESTCODE, null);
                break;
            case R.id.trans_sure_btn:
                commitRemit();
                break;
        }
    }

    private void commitRemit(){
        mPresenter.transExchange(String.valueOf(mTransferOutGameId), mTransOutCoinsEt.getText().toString().trim());
    }

    @Override
    public boolean isActive() {
        return isAdded();
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

    private double mBalance;

    @Override
    public void showTransGameBalance(double balance) {
        mBalance = balance;
        mTransCoinsBalanceTv.setText(String.valueOf(BigDecimal.valueOf(balance)));
    }

    private TransferExchangeGameInfoResp mExchangeInfo;

    @Override
    public void showTransExchangeGameInfo(TransferExchangeGameInfoResp exchangeGameInfo) {
        mExchangeInfo = exchangeGameInfo;
        mTransRatioTv.setText(getString(R.string.lyy_game_transfer_coupon_ratio, (int)exchangeGameInfo.getExchangeRate(), 1));
        mTransMinAmount.setText(getString(R.string.lyy_game_transfer_coupon_min_coins, (int)exchangeGameInfo.getMinAmount()));
    }

    @OnTextChanged(value = R.id.trans_out_coins_et, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void onTextCountChanged(Editable s) {
        mTransSureBtn.setEnabled(false);
        if (!TextUtils.isEmpty(s.toString().trim())) {

            int coins = Integer.parseInt(s.toString().trim());

            if (coins > mBalance) {
                mTransTipTv.setText(getString(R.string.lyy_game_transfer_lack_balance));
            } else if (coins < mExchangeInfo.getMinAmount()) {
                mTransTipTv.setText(getString(R.string.lyy_game_transfer_min_balance));
            } else {
                mTransTipTv.setText("");
                mTransInCoinsTv.setText((int) (coins / mExchangeInfo.getExchangeRate()) + "");
                mTransSureBtn.setEnabled(true);
            }
        } else {
            mTransTipTv.setText("");
            mTransInCoinsTv.setText("0");
        }
    }

    @Override
    public void exchangeSuccess() {

//        if (mTransferOutGameId != 0) {
//            mTransCoinsBalanceTv.setText("0");
//            mPresenter.getTransGameBalance(String.valueOf(mTransferOutGameId));
//        }

        Dialog dialog = DialogFactory.createTransfer(getActivity(), TransferDialog.TYPE_SUCCEED);
//        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
//                mTransOutCoinsEt.setText("");
//                mTransInCoinsTv.setText("0");
//                mTransSureBtn.setEnabled(false);
                ActivityCompat.startActivity(getActivity(), MainTabActivity.getCallIntent(getActivity(),false), null);
            }
        });
        dialog.show();
//        postDelay(new Runnable() {
//            @Override
//            public void run() {
//                ActivityCompat.startActivity(getActivity(), MainTabActivity.getCallIntent(getActivity(),false), null);
//            }
//        }, 1000);
    }

    @Override
    public void exchangeFailure() {
        createTransfer(getActivity(), TransferDialog.TYPE_FAILURE).show();
    }

    private int mTransferOutGameId;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == GameTransferActivity.TRANSFER_GAME_COUPON_REQUESTCODE && resultCode == Activity.RESULT_OK) {
            String fGameName = data.getStringExtra(GameTransferActivity.TRANSFER_GAMENAME);
            mTransOutAppClickTv.setText(fGameName);
            mTransferOutGameId = data.getIntExtra(GameTransferActivity.TRANSFER_GAMEID, 0);
        }

        mTransCoinsBalanceTv.setText("0");
        mTransRatioTv.setText(getString(R.string.lyy_game_transfer_coupon_ratio, 0, 0));
        mTransMinAmount.setText(getString(R.string.lyy_game_transfer_coupon_min_coins, 0));

        if (mTransferOutGameId != 0) {
            mPresenter.getTransGameBalance(String.valueOf(mTransferOutGameId));
            mPresenter.getTransExchangeGameInfo(String.valueOf(mTransferOutGameId));
        }

        mTransOutCoinsEt.setText("");
        mTransInCoinsTv.setText("0");
        mTransSureBtn.setEnabled(false);
    }

}
