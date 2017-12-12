package com.hhly.lyygame.presentation.view.pay;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hhly.lyygame.App;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.db.entry.UserInfo;
import com.hhly.lyygame.data.db.manager.AccountManager;
import com.hhly.lyygame.presentation.payutils.OrderApi;
import com.hhly.lyygame.presentation.utils.NetworkUtil;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.ToastUtil;
import com.hhly.lyygame.presentation.view.order.PayOrderDetailsActivity;
import com.hhly.lyygame.presentation.view.pay.bankpay.BankCardPayActivity;
import com.hhly.lyygame.presentation.view.paydetails.PayDetailsActivity;
import com.orhanobut.logger.Logger;
import com.tencent.mm.sdk.modelpay.PayReq;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ${HELY} on 17/1/18.
 * 邮箱：heli.lixiong@gmail.com
 */

public class PayFragment extends BaseFragment implements PayContact.View {

    TextView mPayAccountTv;
    TextView mPayBalanceTv;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private PayAdapter mPayAdapter;
    private PayContact.Presenter mPresenter;

    public PayFragment() {
        new PayPresenter(this);
    }

    public static PayFragment getInstance() {
        return new PayFragment();
    }

    private UserInfo mUserInfo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserInfo = AccountManager.getInstance().getUserInfo();
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mPayAdapter = new PayAdapter(getActivity());
        mPayAdapter.setPayItemClickListener(mOnPayItemClickListener);
        mPayAdapter.setCoinsItemClickListener(mOnCoinsItemClickListener);
        //
        View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_pay_header_layout, null, false);
        mPayAccountTv = (TextView) headerView.findViewById(R.id.pay_account_tv);
        mPayBalanceTv = (TextView) headerView.findViewById(R.id.pay_balance_tv);
        mPayAdapter.addHeaderView(headerView);

        if (mUserInfo != null) {
            mPayAccountTv.setText(mUserInfo.getUserId());
            mPayBalanceTv.setText(mUserInfo.getLyb());
        }

        View footerView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_pay_footer_layout, null, false);
        footerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showTip(getContext(), getString(R.string.lyy_pay_no_more_way));
            }
        });
//        mPayAdapter.addFooterView(footerView);
        mRecyclerView.setAdapter(mPayAdapter);
        mPayAdapter.notifyDataSetChanged();
    }

    @Override
    protected void fetchData(boolean isLoadMore) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pay_layout_recyclerview;
    }

    @Override
    public void setPresenter(PayContact.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @OnClick(R.id.pay_sure_btn)
    public void onViewClicked() {
        confirmPay();
        //        ActivityUtil.startPayWebActivity(this.getActivity(), "http://mgame.1332255.com/pay.html?", "支付");
        //        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://qr.alipay.com/_d?_b=TRADE_DY&mergeOrder=false&tradeNo=2017041221002001550284362193"));
        //        this.getActivity().startActivity(intent);
        //
        //        startAlipayActivity("https://qr.alipay.com/_d?_b=TRADE_DY&mergeOrder=false&tradeNo=2017041221002001550284362193");
        //
        //        ActivityUtil.startPayWebActivity(this.getActivity(), "http://mgame.1332255.com/pay.html?gameId=xxx&gameName=xxx&to=xxx&token=xxx&deviceId=xxx", "支付");
    }


    /**
     * 确认支付
     */
    private void confirmPay() {
        int money = mPayAdapter.getCoinsItem().getDataList().get(coinsIndex).getRmb();
        if (money == 0) {
            showMsg(R.string.lyy_game_pay_detail_select_gold);
            return;
        }
        if (!NetworkUtil.isAvailable(getActivity())) {
            showMsg(R.string.lyy_network_notwork);
            return;
        }
        int to = 1; //0游戏 , 1乐盈币
        switch (payIndex) {
            case 0://银联
                ActivityCompat.startActivity(getContext(), BankCardPayActivity.getCallIntent(getContext(), money), null);
                break;
            case 1://支付宝
                mPresenter.getPayOrder(money, 11L, getString(R.string.app_name), to, 1);
                break;
            case 2://微信
                mPresenter.getPayOrder(money, 11L, getString(R.string.app_name), to, 2);
                break;
            default:
                break;
        }


    }

    private int payIndex = 0;//支付方式

    private BaseQuickAdapter.OnItemClickListener mOnPayItemClickListener = new BaseQuickAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            payIndex = position;
            List<PlatformItemModel> list = adapter.getData();
            for (int i = 0; i < list.size(); i++) {
                PlatformItemModel itemModel = list.get(i);
                itemModel.setChecked(i == position);
            }

            adapter.notifyDataSetChanged();
        }
    };


    //    private BaseQuickAdapter.OnItemClickListener mOnPayItemClickListener = new BaseQuickAdapter.OnItemClickListener() {
    //        @Override
    //        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
    //
    //        }
    //    };

    //    private OnItemClickListener mOnPayItemClickListener = new OnItemClickListener() {
    //        @Override
    //        public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
    //            payIndex = position;
    //            List<PlatformItemModel> list = baseQuickAdapter.getData();
    //            for (int i = 0; i < list.size(); i++) {
    //                PlatformItemModel itemModel = list.get(i);
    //                itemModel.setChecked(i == position);
    //            }
    //
    //            baseQuickAdapter.notifyDataSetChanged();
    //        }
    //    };

    private int coinsIndex = 0;//支付金额
    @SuppressWarnings("unchecked")
    private BaseQuickAdapter.OnItemClickListener mOnCoinsItemClickListener = new BaseQuickAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
            coinsIndex = position;

//            if (position == 5) {
//                LinearLayoutManager layoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
//                layoutManager.setStackFromEnd(true);
//                mRecyclerView.scrollToPosition(3);
//            }

            List<CoinsItemAdapter.BasePayAmountItem> list = baseQuickAdapter.getData();
            for (int i = 0; i < list.size(); i++) {
                CoinsItemAdapter.BasePayAmountItem itemModel = list.get(i);
                itemModel.setChecked(i == position);
            }
            baseQuickAdapter.notifyDataSetChanged();
        }
    };

    @Override
    public void getOrderSuccess(PayReq req) {
        Logger.d("payReq=" + req);
        if (req == null) {
            return;
        }

        OrderApi.obtain().startWechatPay(getActivity(), req, new OrderApi.OnPayCallback() {
            @Override
            public void onPayStart() {
                Logger.d("onPayStart");
            }

            @Override
            public void onPayError(String msg) {
                Logger.d("onPayError");
                int type = App.type;
                if (type == 1) {
                    getActivity().startActivity(PayOrderDetailsActivity.getPayOrderDetailsIntent(getActivity(), "2", ""));
                }
            }

            @Override
            public void onPayCompletely() {
                Logger.d("onPayCompletely");

                final int type = App.type;
                if (type == 1) {
                    getActivity().startActivity(PayOrderDetailsActivity.getPayOrderDetailsIntent(getActivity(), "2", ""));
                } else {
                    getActivity().startActivity(PayDetailsActivity.getPayDetailsIntent(getActivity(), "2", ""));
                }
                onBackPressed();
            }
        });

    }

    @Override
    public void getOrderFailure(String msg) {

    }

    @Override
    public void getAliOrderSuccess(String orderInfo) {
        OrderApi.obtain().startAlipay(getActivity(), orderInfo);
    }
}
