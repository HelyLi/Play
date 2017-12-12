package com.hhly.lyygame.presentation.view.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hhly.lyygame.R;
import com.hhly.lyygame.data.db.manager.AccountManager;
import com.hhly.lyygame.data.model.GoodsInfo;
import com.hhly.lyygame.data.net.protocol.user.UserAddressResp;
import com.hhly.lyygame.presentation.utils.CollectionUtil;
import com.hhly.lyygame.presentation.utils.NetworkUtil;
import com.hhly.lyygame.presentation.utils.NumberFormatUtils;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.ToastUtil;
import com.hhly.lyygame.presentation.view.address.AddressManagerActivity;
import com.hhly.lyygame.presentation.view.web.WebLocationActivity;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 确认订单
 * Created by Simon on 2016/12/1.
 */

public class OrderFragment extends BaseFragment implements OrderContract.OrderView {

    public static final int RQ_ADDRESS = 100;
    @BindView(R.id.address_none_tv)
    TextView mAddressNoneTv;
    @BindView(R.id.name_tv)
    TextView mNameTv;
    @BindView(R.id.phone_tv)
    TextView mPhoneTv;
    @BindView(R.id.detail_tv)
    TextView mDetailTv;
    @BindView(R.id.address_detail_root)
    RelativeLayout mAddressDetailRoot;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.price_tv)
    TextView mPriceTv;
    @BindView(R.id.confirm_btn)
    Button mConfirmBtn;

    TextView mDeliveryTv;
    TextView mOrderPriceTv;
    AppCompatCheckBox mOrderAgreementCb;
    TextView mOrderAgreementTv;

    private OrderAdapter mOrderAdapter;
    private OrderContract.OrderPresenter mPresenter;

    public OrderFragment() {
        new OrderPresenterImpl(this);
    }

    public static OrderFragment newInstance(int[] goodIds, int[] num) {
        OrderFragment fragment = new OrderFragment();
        Bundle bundle = new Bundle();
        bundle.putIntArray(OrderActivity.ORDER_ID, goodIds);
        bundle.putIntArray(OrderActivity.ORDER_ID_NUM, num);
        fragment.setArguments(bundle);
        return fragment;
    }

    private int[] mOrderIDs;
    private int[] mOrderNUm;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mOrderIDs = getArguments().getIntArray(OrderActivity.ORDER_ID);
        mOrderNUm = getArguments().getIntArray(OrderActivity.ORDER_ID_NUM);
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mOrderAdapter = new OrderAdapter();
        View footView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_confirm_order_footer_layout, null, false);
        initFooterView(footView);
        mOrderAdapter.addFooterView(footView);

        for (int i = 0; i < mOrderIDs.length; i++) {
            int num = 1;
            if (mOrderNUm.length - 1 >= i) {
                num = mOrderNUm[i];
            }
            mPresenter.getGoodsInfo(mOrderIDs[i], num);
        }

        mPresenter.getUserAddress(AccountManager.getInstance().getUserId());

        mRecyclerView.setAdapter(mOrderAdapter);
    }

    @Override
    protected void fetchData(boolean isLoadMore) {

    }

    private void initFooterView(View view) {
        mDeliveryTv = (TextView) view.findViewById(R.id.delivery_tv);
        mOrderPriceTv = (TextView) view.findViewById(R.id.order_price_tv);
        mOrderAgreementCb = (AppCompatCheckBox) view.findViewById(R.id.order_agreement_cb);
        mOrderAgreementTv = (TextView) view.findViewById(R.id.order_agreement_tv);
        mOrderAgreementTv.setOnClickListener(agreement);
        mOrderAgreementCb.setOnCheckedChangeListener(mAgreementChangedListener);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_confirm_order_layout;
    }

    @OnClick(R.id.address_root)
    public void onClick() {
        if (!NetworkUtil.isAvailable(getActivity())){
            ToastUtil.showTip(getActivity(),R.string.lyy_game_network_state);
            return;
        }
        Intent intent = AddressManagerActivity.getCallingIntent(getActivity());
        startActivityForResult(intent, RQ_ADDRESS, null);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //选择地址回调
        //        if (requestCode == RQ_ADDRESS) {
        //
        //        }
        mPresenter.getUserAddress(AccountManager.getInstance().getUserId());
    }

    private CompoundButton.OnCheckedChangeListener mAgreementChangedListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            mConfirmBtn.setEnabled(isChecked);
        }
    };

    private View.OnClickListener agreement = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ActivityCompat.startActivity(getActivity(), WebLocationActivity.getCallingIntent(getActivity(),
                    WebLocationActivity.EX_AGREEMENT, getActivity().getString(R.string.lyy_exchange_agreement_title)), null);
        }
    };

    @Override
    public void setPresenter(OrderContract.OrderPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    UserAddressResp.AddressBean mAddress = null;

    @Override
    public void showAddress(List<UserAddressResp.AddressBean> addressBeanList) {
        if (CollectionUtil.isEmpty(addressBeanList)) {
            mAddress = null;
            mAddressNoneTv.setVisibility(View.VISIBLE);
            mAddressDetailRoot.setVisibility(View.GONE);
            return;
        }

        for (UserAddressResp.AddressBean addressBean : addressBeanList) {
            if (addressBean.getLast() == 0) {//默认地址
                mAddress = addressBean;
                break;
            }
        }

        if (mAddress == null) {//时间最近
            Collections.sort(addressBeanList);
            mAddress = addressBeanList.get(0);
        }

        if (mAddress == null) {
            mAddressNoneTv.setVisibility(View.VISIBLE);
            mAddressDetailRoot.setVisibility(View.GONE);
        } else {
            mAddressNoneTv.setVisibility(View.GONE);
            mAddressDetailRoot.setVisibility(View.VISIBLE);
            mNameTv.setText(getString(R.string.lyy_game_order_receiver) + mAddress.getName());
            mPhoneTv.setText(mAddress.getTel());
            mDetailTv.setText(getString(R.string.lyy_game_order_receive_address) +
                    mAddress.getProvince() + mAddress.getCity() + mAddress.getStreet() + "-" +
                    mAddress.getDetail());
        }
    }

    @Override
    public void onAddressFailure() {
        mAddress = null;
        mAddressNoneTv.setVisibility(View.VISIBLE);
        mAddressDetailRoot.setVisibility(View.GONE);
    }

//    private List<GoodsInfo> mGoodsInfoBeans = new ArrayList<>();

    private GoodsInfo mGoodsInfo;

    /**
     * 商品信息
     *
     * @param goodsInfoBean
     */
    @Override
    public void showGoodsInfo(GoodsInfo goodsInfoBean) {
        if (goodsInfoBean == null)
            return;
        //        mGoodsInfoBean = goodsInfoBean;
        mGoodsInfo = goodsInfoBean;
        // mGoodsInfoBeans.add(goodsInfoBean);
        mOrderAdapter.addData(goodsInfoBean);
        //        mOrderPriceTv.setText(getString(R.string.lyy_game_order_price, 1, (int)goodsInfoBean.getNeedScore()));
        //        mPriceTv.setText(getString(R.string.lyy_game_order_price_all, 1, (int)goodsInfoBean.getNeedScore()));
        setPriceTvText(goodsInfoBean);
    }

    /**
     * 根据商品类型显示文字
     * needScoreId 1：乐盈币 2：乐盈券 3：积分
     *
     * @param
     */
    private void setPriceTvText(GoodsInfo goodsInfos) {

//        double needScore = 0;
//        int orderNum = 0;
//
//        for (GoodsInfo goodsInfo : goodsInfos) {
//            needScore += (Double.valueOf(goodsInfo.getNeedScore()) * goodsInfo.getOrderNum());
//            orderNum += goodsInfo.getOrderNum();
//        }

        String price = NumberFormatUtils.doubleTrans2(goodsInfos.getNeedScore());
        mPriceTv.setText(getString(R.string.lyy_game_order_price_all_rmb_str, 1, price));
        mOrderPriceTv.setText(getString(R.string.lyy_game_order_price_rmb_str, 1, price));

//        switch (goodsInfos.get(0).getNeedScoreId()) {
//            case 1:
//                mPriceTv.setText(getString(R.string.lyy_game_order_price_all_coins, orderNum, needScore));
//                mOrderPriceTv.setText(getString(R.string.lyy_game_order_price_coins, orderNum, needScore));
//                break;
//            case 2:
//                mPriceTv.setText(getString(R.string.lyy_game_order_price_all_coupon, orderNum, needScore));
//                mOrderPriceTv.setText(getString(R.string.lyy_game_order_price_coupon, orderNum, needScore));
//                break;
//            case 3:
//                mPriceTv.setText(getString(R.string.lyy_game_order_price_all, orderNum, needScore));
//                mOrderPriceTv.setText(getString(R.string.lyy_game_order_price, orderNum, needScore));
//                break;
//            default:
//                break;
//        }
    }

    @Override
    public void onGoodsInfoFailure() {
        //        ToastUtil.showTip(getActivity(), "购买失败");
        // mConfirmBtn.setClickable(true);
        dismissLoading();
    }

    private int size = 0;

    @Override
    public void showExchangeGoods() {
        ToastUtil.showTip(getActivity(), R.string.lyy_game_order_success);
        postDelay(new Runnable() {
            @Override
            public void run() {
                onBackPressed();
            }
        }, 500);
//        size++;
//        if (size == mGoodsInfoBeans.size()) {
//
//        }
    }

    @Override
    public void onExchangeFailure(String msg) {
        ToastUtil.showTip(getActivity(), "".equals(msg) ? getString(R.string.lyy_game_network_state) : msg);
        // mConfirmBtn.setClickable(true);
        dismissLoading();
    }

    @OnClick(R.id.confirm_btn)
    void confirmClick(View view) {
        if (mAddress == null) {
            ToastUtil.showTip(getActivity(), R.string.lyy_game_order_address);
            return;
        }
        size = 0;
//        for (GoodsInfo goodsInfo : mGoodsInfoBeans) {
//            GoodsExchangeReq.ExGoods exGoods = new GoodsExchangeReq.ExGoods();
//            exGoods.setGoodsId(goodsInfo.getId());
//            exGoods.setPhone(mAddress.getTel());
//            exGoods.setUserId(AccountManager.getInstance().getUserId());
//            exGoods.setAddressId(mAddress.getId());
//            exGoods.setCount(goodsInfo.getOrderNum());
//            exGoods.setId(goodsInfo.getId());
//
//            mPresenter.exchangeGoods(exGoods);
//        }

        if (!NetworkUtil.isAvailable(getActivity())) {
            ToastUtil.showTip(getActivity(), R.string.lyy_network_notwork);
            return;
        }

        if (null != mGoodsInfo) {
            if (mGoodsInfo.getNeedScore() > 10000000) {
                ToastUtil.showTip(getContext(), "支付金额超额");
                return;
            }
            Intent intent = new Intent(getActivity(), PayOrderActivity.class);
            intent.putExtra("goodsInfo", mGoodsInfo);
            intent.putExtra("addressId", mAddress.getId());
            getActivity().startActivity(intent);
            onBackPressed();
        }
    }

}
