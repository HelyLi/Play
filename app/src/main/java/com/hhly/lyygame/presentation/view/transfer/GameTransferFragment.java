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
import com.hhly.lyygame.data.db.manager.AccountManager;
import com.hhly.lyygame.data.net.protocol.transfer.TransferRemitInfoResp;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.DialogFactory;
import com.hhly.lyygame.presentation.view.ToastUtil;
import com.hhly.lyygame.presentation.view.immersive.IImmersiveApply;
import com.hhly.lyygame.presentation.view.main.MainTabActivity;
import com.orhanobut.logger.Logger;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

import static com.hhly.lyygame.presentation.view.DialogFactory.createTransfer;

/**
 * Created by ${HELY} on 17/4/24.
 * 邮箱：heli.lixiong@gmail.com
 */

public class GameTransferFragment extends BaseFragment implements IImmersiveApply, GameTransferContract.View {

    @BindView(R.id.trans_out_app_click_tv)
    TextView mTransOutAppClickTv;
    @BindView(R.id.trans_coins_balance_tv)
    TextView mTransCoinsBalanceTv;
    @BindView(R.id.trans_in_app_click_tv)
    TextView mTransInAppClickTv;
    @BindView(R.id.trans_out_coins_et)
    EditText mTransOutCoinsEt;
    @BindView(R.id.trans_in_coins_tv)
    TextView mTransInCoinsTv;
    @BindView(R.id.trans_sure_btn)
    Button mTransSureBtn;
    @BindView(R.id.trans_ratio_tv)
    TextView mTransRatioTv;
    @BindView(R.id.trans_min_amount)
    TextView mTransMinAmount;
    @BindView(R.id.trans_tip_tv)
    TextView mTransTipTv;

    private GameTransferContract.Presenter mPresenter;

    public static GameTransferFragment newInstance() {
        return new GameTransferFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_game_transfer_layout;
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {

        mTransRatioTv.setText(getString(R.string.lyy_game_transfer_ratio, 0, 0));
        mTransMinAmount.setText(getString(R.string.lyy_game_transfer_min_coins, 0));
    }

    @Override
    protected void fetchData(boolean isLoadMore) {

    }

    @Override
    public void setPresenter(GameTransferContract.Presenter presenter) {
        mPresenter = presenter;
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

    @OnClick({R.id.trans_out_app_click_tv, R.id.trans_in_app_click_tv, R.id.trans_sure_btn})
    void onTransOutAppClick(View view) {
        switch (view.getId()) {
            case R.id.trans_out_app_click_tv:
                ActivityCompat.startActivityForResult(getActivity(), TransferActivity.getCallingIntent(getActivity(), "2"), GameTransferActivity.TRANSFER_GAME_OUT_REQUESTCODE, null);
                break;
            case R.id.trans_in_app_click_tv:
                ActivityCompat.startActivityForResult(getActivity(), TransferActivity.getCallingIntent(getActivity(), "1"), GameTransferActivity.TRANSFER_GAME_IN_REQUESTCODE, null);
                break;
            case R.id.trans_sure_btn:
                commitRemit();
                break;
        }
    }

    private double mBalance;

    @Override
    public void showTransGameBalance(double balance) {
        mBalance = balance;
        mTransCoinsBalanceTv.setText(String.valueOf(BigDecimal.valueOf(balance)));
    }

    @Override
    public void showTransGameBalanceFailure() {

    }

    private TransferRemitInfoResp mRemitInfo;

    @Override
    public void showTransRemitGameInfo(TransferRemitInfoResp remitInfo) {
        if (remitInfo == null)return;
        mRemitInfo = remitInfo;
        mTransRatioTv.setText(getString(R.string.lyy_game_transfer_ratio, (int) remitInfo.getfRate(), (int) remitInfo.gettRate()));
        mTransMinAmount.setText(getString(R.string.lyy_game_transfer_min_coins, (int) remitInfo.getMinAmount()));
    }

    @OnTextChanged(value = R.id.trans_out_coins_et, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void onTextCountChanged(Editable s) {
        mTransSureBtn.setEnabled(false);
        if (mRemitInfo == null)return;
        if (!TextUtils.isEmpty(s.toString())) {

            int coins = Integer.parseInt(s.toString());

            if (coins > mBalance) {
                mTransTipTv.setText(getString(R.string.lyy_game_transfer_lack_balance));
            } else if (coins < mRemitInfo.getMinAmount()) {
                mTransTipTv.setText(getString(R.string.lyy_game_transfer_min_balance));
            } else {
                mTransTipTv.setText("");
                mTransInCoinsTv.setText((int) (coins * mRemitInfo.gettRate() / mRemitInfo.getfRate()) + "");
                mTransSureBtn.setEnabled(true);
            }
        } else {
            mTransTipTv.setText("");
            mTransInCoinsTv.setText("0");
        }
    }

    @Override
    public void transRemitSuccess() {

//        if (mTransferOutGameId == 1){
//            mPresenter.getUserBalance();
//        }else if (mTransferOutGameId != 0) {
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

//                postDelay(new );

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
    public void transRemitFailure() {
        createTransfer(getActivity(), TransferDialog.TYPE_FAILURE).show();
    }

    private void commitRemit() {

        mPresenter.transRemit(String.valueOf(mTransferOutGameId), String.valueOf(mTransferInGameId), mTransOutCoinsEt.getText().toString().trim());
    }

    private int mTransferOutGameId = 0;
    private int mTransferInGameId = 0;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == GameTransferActivity.TRANSFER_GAME_OUT_REQUESTCODE && resultCode == Activity.RESULT_OK) {
            String fGameName = data.getStringExtra(GameTransferActivity.TRANSFER_GAMENAME);
            mTransOutAppClickTv.setText(fGameName);
            mTransferOutGameId = data.getIntExtra(GameTransferActivity.TRANSFER_GAMEID, 0);

        } else if (requestCode == GameTransferActivity.TRANSFER_GAME_IN_REQUESTCODE && resultCode == Activity.RESULT_OK) {
            String fGameName = data.getStringExtra(GameTransferActivity.TRANSFER_GAMENAME);
            mTransInAppClickTv.setText(fGameName);
            mTransferInGameId = data.getIntExtra(GameTransferActivity.TRANSFER_GAMEID, 0);
        }

        Logger.d("mTransferOutGameId=" + mTransferOutGameId);

        if (mTransferOutGameId != 1){
            mTransCoinsBalanceTv.setText("0");
        }
        mTransRatioTv.setText(getString(R.string.lyy_game_transfer_ratio, 0, 0));
        mTransMinAmount.setText(getString(R.string.lyy_game_transfer_min_coins, 0));

        if (mTransferInGameId != 0 && mTransferOutGameId == 1){
            String lyb = AccountManager.getInstance().getUserInfo().getLyb();
            mBalance = Double.parseDouble(lyb);
            mTransCoinsBalanceTv.setText(lyb);
//
//            mPresenter.getTransGameBalance(String.valueOf(mTransferOutGameId));
            mPresenter.getTransRemitGameInfo(String.valueOf(mTransferOutGameId), String.valueOf(mTransferInGameId));
        }else if (mTransferInGameId != 0 && mTransferOutGameId != 0) {
            if (mTransferOutGameId == mTransferInGameId) {
                ToastUtil.showTip(getActivity(), "划出游戏与划入游戏相同");
            } else {
                Logger.d("mTransferOutGameId=" + mTransferOutGameId);
                mPresenter.getTransGameBalance(String.valueOf(mTransferOutGameId));
                mPresenter.getTransRemitGameInfo(String.valueOf(mTransferOutGameId), String.valueOf(mTransferInGameId));
            }
        } else if (mTransferOutGameId == 1) {
            String lyb = AccountManager.getInstance().getUserInfo().getLyb();
            mBalance = Double.parseDouble(lyb);
            mTransCoinsBalanceTv.setText(lyb);

        } else if (mTransferOutGameId != 0) {
            mPresenter.getTransGameBalance(String.valueOf(mTransferOutGameId));
        }

        mTransOutCoinsEt.setText("");
        mTransInCoinsTv.setText("0");
        mTransSureBtn.setEnabled(false);
    }

}
