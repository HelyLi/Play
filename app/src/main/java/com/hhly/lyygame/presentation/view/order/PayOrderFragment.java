package com.hhly.lyygame.presentation.view.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hhly.lyygame.App;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.db.entry.UserInfo;
import com.hhly.lyygame.data.db.manager.AccountManager;
import com.hhly.lyygame.data.model.GoodsInfo;
import com.hhly.lyygame.data.net.protocol.user.PayResp;
import com.hhly.lyygame.presentation.payutils.OrderApi;
import com.hhly.lyygame.presentation.utils.NetworkUtil;
import com.hhly.lyygame.presentation.utils.NumberFormatUtils;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.ToastUtil;
import com.hhly.lyygame.presentation.view.pay.PlatformItemModel;
import com.hhly.lyygame.presentation.view.paydetails.PayDetailsActivity;
import com.orhanobut.logger.Logger;
import com.tencent.mm.sdk.modelpay.PayReq;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by dell on 2017/6/1.
 */

public class PayOrderFragment extends BaseFragment implements OrderContract.PayOrderView {

    @BindView(R.id.pay_recyclerView)
    RecyclerView mPayRecyclerView;
    @BindView(R.id.confirm_btn)
    Button mConfirmBtn;

    private PayAdapter mPayAdapter;

    @BindView(R.id.mall_item_name_tv)
    TextView mallItemNameTv;
    @BindView(R.id.order_account_tv)
    TextView mOrderAccountTv;
    @BindView(R.id.tv_mall_order_id)
    TextView mTvMallOrderId;
    @BindView(R.id.order_price_tv)
    TextView mOrderPriceTv;

    private OrderContract.PayOrderPresenter mPresenter;

    private GoodsInfo mGoodsInfo;

    private int mAddressId;
    private String mPhone;
    private String mPayToAccount;

    public PayOrderFragment() {
        new PayOrderPresenterImpl(this);
    }

    public static PayOrderFragment newInstance(GoodsInfo goodsInfo, int addressId, String phone, String payToAccount) {
        PayOrderFragment fragment = new PayOrderFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(PayOrderActivity.GOODS_INFO, goodsInfo);
        bundle.putInt(PayOrderActivity.ADDRESS_ID, addressId);
        bundle.putString(PayOrderActivity.PHONE, phone);
        bundle.putString(PayOrderActivity.PAY_TO_ACCOUNT, payToAccount);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGoodsInfo = getArguments().getParcelable(PayOrderActivity.GOODS_INFO);
        mAddressId = getArguments().getInt(PayOrderActivity.ADDRESS_ID);
        mPhone = getArguments().getString(PayOrderActivity.PHONE);
        mPayToAccount = getArguments().getString(PayOrderActivity.PAY_TO_ACCOUNT);
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        mPayRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mPayRecyclerView.setHasFixedSize(true);

        mPayAdapter = new PayAdapter();
        mPayAdapter.setPayItemClickListener(mOnPayItemClickListener);

        mPayRecyclerView.setAdapter(mPayAdapter);

        // mPresenter.getOrderInfoForShop();

        UserInfo userInfo = AccountManager.getInstance().getUserInfo();
        if (null != userInfo) {
            mOrderAccountTv.setText(userInfo.getUserId());
        }

        if (null != mGoodsInfo) {
            String name = mGoodsInfo.getName();
            if (!TextUtils.isEmpty(name))
                mallItemNameTv.setText(name);

            String price = NumberFormatUtils.doubleTrans2(mGoodsInfo.getNeedScore());
            mOrderPriceTv.setText(getString(R.string.lyy_game_order_rmb_str, price));

            App.shopName = name;
            App.shopPrice = price;
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

    @Override
    protected void fetchData(boolean isLoadMore) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pay_order_layout;
    }

    @Override
    public void setPresenter(OrderContract.PayOrderPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void showPayInfo(PayResp payResp) {
        // mTvMallOrderId.setText(payResp.getBusinessNo());
    }

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
//                onBackPressed();
            }
        });
    }

    @Override
    public void getOrderFailure(String msg) {
        if (!TextUtils.isEmpty(msg))
            ToastUtil.showTip(getActivity(), msg);
        dismissLoading();
    }

    @Override
    public void getAliOrderSuccess(String orderInfo) {
        OrderApi.obtain().startAlipay(getActivity(), orderInfo);
    }

    @Override
    public void getOrderInfoFailure(String msg) {
        // ToastUtil.showTip(getActivity(), !TextUtils.isEmpty(msg) ? msg : getString(R.string.lyy_game_request_error));
    }

    @Override
    public void getOrderInfoSuccess(String orderNo) {
        mConfirmBtn.setEnabled(true);
        // mTvMallOrderId.setText(orderNo);
        App.orderNo = orderNo;
    }

    @OnClick(R.id.confirm_btn)
    public void onViewClicked() {
        if (!NetworkUtil.isAvailable(getActivity())) {
            ToastUtil.showTip(getActivity(), R.string.lyy_network_notwork);
            return;
        }
        switch (payIndex) {
            case 0://支付宝
                if (null != mGoodsInfo) {
                    String name = mGoodsInfo.getName();
                    double price = mGoodsInfo.getNeedScore();
                    int id = mGoodsInfo.getGoodsId() != 0 ? mGoodsInfo.getGoodsId() : mGoodsInfo.getId();
                    int shopType = mGoodsInfo.getType();
                    mPresenter.getPayOrder(price, 11L, name, 1, id, shopType, mAddressId, mPhone, mPayToAccount);
                }
                break;
            case 1://微信
                if (null != mGoodsInfo) {
                    String name = mGoodsInfo.getName();
                    double price = mGoodsInfo.getNeedScore();
                    int id = mGoodsInfo.getGoodsId() != 0 ? mGoodsInfo.getGoodsId() : mGoodsInfo.getId();
                    int shopType = mGoodsInfo.getType();
                    mPresenter.getPayOrder(price, 11L, name, 2, id, shopType, mAddressId, mPhone, mPayToAccount);
                }
                break;
            default:
                break;
        }
    }

}
