package com.hhly.lyygame.presentation.view.pay.bankpay;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.*;
import butterknife.BindView;
import butterknife.OnClick;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.db.manager.AccountManager;
import com.hhly.lyygame.data.net.client.BaseSubscriber;
import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.pay.QuickPayConfirmReq;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.contact.ContactActivity;
import com.hhly.lyygame.presentation.view.immersive.IImmersiveApply;
import com.hhly.lyygame.presentation.view.main.MainTabActivity;
import com.hhly.lyygame.presentation.view.pay.PayActivity;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;

import java.util.concurrent.TimeUnit;

/**
 * description :
 * Created by Flynn
 * 2017/5/18
 */

public class PayResultFragment extends BaseFragment implements PayResultContract.View, IImmersiveApply {

    private static final long SECOND = 10;
    @BindView(R.id.process_img_bg)
    RelativeLayout mProcessImgBg;
    @BindView(R.id.process_img)
    ImageView mProcessImg;
    @BindView(R.id.time_tv)
    TextView mTimeTv;
    @BindView(R.id.process_bg)
    LinearLayout mProcessBg;
    @BindView(R.id.failure_cause_tv)
    TextView mFailureCauseTv;
    @BindView(R.id.failure_bg)
    LinearLayout mFailureBg;
    @BindView(R.id.btn_left)
    Button mBtnLeft;
    @BindView(R.id.btn_right)
    Button mBtnRight;
    @BindView(R.id.btn_ll)
    LinearLayout mBtnLl;
    @BindView(R.id.account_tv)
    TextView mAccountTv;
    @BindView(R.id.order_no_tv)
    TextView mOrderNoTv;
    @BindView(R.id.total_money_tv)
    TextView mTotalMoneyTv;
    @BindView(R.id.success_bg)
    LinearLayout mSuccessBg;
    private PayResultContract.Presenter mPresenter;

    private boolean isFinish;

    private boolean isFailure;
    private boolean isSuccess;

    public static final String KEY = "key";

    public static PayResultFragment newInstance(Bundle args) {
        PayResultFragment fragment = new PayResultFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        new PayResultPresenter(this);
        initView();
        QuickPayConfirmReq req = (QuickPayConfirmReq) getArguments().getSerializable(KEY);
        if (req != null) {
            mPresenter.quickPayConfirm(req);
        }
    }

    private void initView() {
        Animation circle_anim = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_round_rotate);
        LinearInterpolator interpolator = new LinearInterpolator();//设置匀速旋转，在xml文件中设置会出现卡顿
        if (circle_anim != null) {
            circle_anim.setInterpolator(interpolator);
            mProcessImg.startAnimation(circle_anim);  //开始动画
        }
        Flowable.interval(0, 1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .compose(this.<Long>bindToLife())
                .take(SECOND + 1)
                .subscribe(new BaseSubscriber<Long>() {
                    @Override
                    protected void hideDialog() {

                    }

                    @Override
                    protected void showDialog() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        mTimeTv.setText(String.valueOf(SECOND - aLong) + "秒");
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                        if (!isSuccess) {
                            showFailure(getString(R.string.lyy_game_pay_result_failure_cause_time));
                        }
                    }
                });
    }

    @Override
    protected void fetchData(boolean isLoadMore) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pay_result;
    }

    @Override
    public void setPresenter(PayResultContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void quickPayConfirmSuccess() {
        isSuccess = true;
        showSuccess();
    }

    @Override
    public void quickPayConfirmFailure(String msg, String respCode) {
        if ("2015".equalsIgnoreCase(respCode)) {
            isFinish = true;
            Activity activity = getActivity();
            if (activity instanceof PayResultActivity) {
                ((PayResultActivity) activity).setFinish();
            }
        }
        showFailure(!TextUtils.isEmpty(msg) ? msg : getString(R.string.lyy_network_exception));
    }


    private void showFailure(String msg) {
        if (!isFailure) {
            isFailure = true;
            hideProcess();
            mProcessImgBg.setBackgroundResource(R.drawable.icon_pay_result_failure);
            mFailureBg.setVisibility(View.VISIBLE);
            mBtnLl.setVisibility(View.VISIBLE);
            mBtnLeft.setText(R.string.lyy_game_pay_result_contact_customer);
            mBtnRight.setText(R.string.lyy_game_pay_result_continue_pay);
            mFailureCauseTv.setText(getString(R.string.lyy_game_pay_result_failure_cause, msg));
        }
    }

    private void showSuccess() {
        hideProcess();
        mProcessImgBg.setBackgroundResource(R.drawable.icon_pay_result_success);
        mSuccessBg.setVisibility(View.VISIBLE);
        mBtnLl.setVisibility(View.VISIBLE);
        mBtnLeft.setText(R.string.lyy_game_pay_result_back_main);
        mBtnRight.setText(R.string.lyy_game_pay_result_agent_pay);
        QuickPayConfirmReq req = (QuickPayConfirmReq) getArguments().getSerializable(KEY);
        if (req != null) {
            mAccountTv.setText(AccountManager.getInstance().getUserId());
            mOrderNoTv.setText(req.getBusinessNo());
            mTotalMoneyTv.setText(getString(R.string.lyy_game_pay_result_money_num, req.getTotalFee()));
        }
    }

    private void hideProcess() {
        mProcessBg.setVisibility(View.GONE);
        mTimeTv.setVisibility(View.GONE);
        mProcessImg.clearAnimation();
        mProcessImg.setVisibility(View.GONE);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mProcessImg.clearAnimation();
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


    @OnClick(R.id.btn_left)
    public void onMBtnLeftClicked() {

        if (isFailure) {
            ActivityCompat.startActivity(getActivity(), ContactActivity.getCallingIntent(getActivity()), null);
        } else {
            startActivity(MainTabActivity.getCallIntent(getActivity(), 2));
        }
    }

    @OnClick(R.id.btn_right)
    public void onMBtnRightClicked() {
        startActivity(PayActivity.getCallingIntent(getActivity()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (!isFinish) {
                startActivity(PayActivity.getCallingIntent(getActivity()));
            } else {
                onBackPressed();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
