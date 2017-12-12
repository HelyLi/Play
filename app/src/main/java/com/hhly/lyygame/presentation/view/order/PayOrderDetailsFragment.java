package com.hhly.lyygame.presentation.view.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hhly.lyygame.App;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.client.BaseSubscriber;
import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.user.QueryPayResp;
import com.hhly.lyygame.presentation.utils.NumberFormatUtils;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.contact.ContactActivity;
import com.hhly.lyygame.presentation.view.main.MainTabActivity;
import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by dell on 2017/6/3.
 */

public class PayOrderDetailsFragment extends BaseFragment implements OrderContract.PayOrderDetailsView {

    @BindView(R.id.detail_tv)
    TextView mDetailTv;
    @BindView(R.id.layout_order_info)
    LinearLayout mLayoutOrderInfo;
    @BindView(R.id.btn_back)
    Button mBtnBack;
    private OrderContract.PayOrderDetailsPresenter mPresenter;

    @BindView(R.id.order_account_tv)
    TextView mOrderAccountTv;
    @BindView(R.id.tv_mall_order_id)
    TextView mallOrderId;
    @BindView(R.id.mall_item_name_tv)
    TextView mallItemNameTv;
    @BindView(R.id.order_price_tv)
    TextView mOrderPriceTv;

    @BindView(R.id.detail_state_iv)
    ImageView mDetailStateIv;

    @BindView(R.id.process_img)
    ImageView mProcessImg;

    @BindView(R.id.time_tv)
    TextView mTimeTv;
    @BindView(R.id.process_bg)
    LinearLayout mProcessBg;

    @BindView(R.id.success_bg)
    RelativeLayout mSuccessBg;

    @BindView(R.id.process_img_bg)
    RelativeLayout mProcessImgBg;

    private static final long SECOND = 10;

    private boolean isFailure;
    private boolean isSuccess;

    public PayOrderDetailsFragment() {
        new PayOrderDetailsPresenter(this);
    }

    public static PayOrderDetailsFragment newInstance() {
        return new PayOrderDetailsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        new PayOrderDetailsPresenter(this);
        initView();
        String businessNo = App.businessNo;
        Logger.d("PayOrderDetailsFragment businessNo: " + businessNo);
        if (!TextUtils.isEmpty(businessNo))
            mPresenter.getOrderDetail(businessNo);
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
        return R.layout.fragment_pay_order_details_layout;
    }

    @Override
    public void setPresenter(OrderContract.PayOrderDetailsPresenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void payQuerySuccess(final QueryPayResp resp) {
        isSuccess = true;
        showSuccess(resp);
    }

    @Override
    public void payQueryFailure(String msg) {
        showFailure(msg);
    }

    @OnClick({R.id.btn_back, R.id.btn_agent})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                if (isFailure) {
                    startActivity(ContactActivity.getCallingIntent(getActivity()));
                    // getActivity().finish();
                }
                else {
                    startActivity(MainTabActivity.getCallIntent(getActivity(), 0));
                }
                break;
            case R.id.btn_agent:
                startActivity(MainTabActivity.getCallIntent(getActivity(), 1));
                break;
        }
    }

    private void showSuccess(final QueryPayResp resp) {
        hideProcess();
        mSuccessBg.setVisibility(View.VISIBLE);

        mBtnBack.setText(R.string.lyy_game_pay_result_back_main);
        mDetailStateIv.setImageResource(R.drawable.icon_pay_result_success);

        mDetailTv.setText(getString(R.string.lyy_game_order_success));
        mLayoutOrderInfo.setVisibility(View.VISIBLE);
        String orderNo = resp.getPayBussinessInfo().getBusinessNo();

        Logger.d("orderNo: " + orderNo);
        if (!TextUtils.isEmpty(orderNo))
            mallOrderId.setText(orderNo);

        String shopName = resp.getPayBussinessInfo().getTradeName();
        Logger.d("shopName: " + shopName);
        if (!TextUtils.isEmpty(shopName))
            mallItemNameTv.setText(shopName);

        double amount = resp.getPayBussinessInfo().getOrderAmount();
        Logger.d("amount: " + amount);
        String shopPrice = NumberFormatUtils.doubleTrans2(amount);
        mOrderPriceTv.setText(getString(R.string.lyy_game_order_rmb_str, shopPrice));

        String userInfo = resp.getPayBussinessInfo().getBuyerId();
        Logger.d("userInfo: " + userInfo);
        if (!TextUtils.isEmpty(userInfo)) {
            mOrderAccountTv.setText(userInfo);
        }
    }

    private void showFailure(String msg) {
        if (!isFailure) {
            isFailure = true;
            hideProcess();

            mSuccessBg.setVisibility(View.VISIBLE);
            mDetailStateIv.setImageResource(R.drawable.transfer_failure);
            mBtnBack.setText(R.string.lyy_game_pay_result_contact_customer);
            mDetailTv.setText(getString(R.string.lyy_game_order_fail));
            mLayoutOrderInfo.setVisibility(View.GONE);
            // mFailureCauseTv.setText(getString(R.string.lyy_game_pay_result_failure_cause, msg));
        }
    }

    private void hideProcess() {
        mProcessImgBg.setVisibility(View.GONE);
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
}
