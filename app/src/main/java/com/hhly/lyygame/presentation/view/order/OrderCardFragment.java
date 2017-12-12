package com.hhly.lyygame.presentation.view.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hhly.lyygame.App;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.db.entry.UserInfo;
import com.hhly.lyygame.data.db.manager.AccountManager;
import com.hhly.lyygame.data.model.GoodsInfo;
import com.hhly.lyygame.presentation.utils.NetworkUtil;
import com.hhly.lyygame.presentation.utils.NumberFormatUtils;
import com.hhly.lyygame.presentation.utils.RegexUtils;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.ToastUtil;
import com.hhly.lyygame.presentation.view.web.WebLocationActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 充值卡确认订单
 * Created by Simon on 2016/12/14.
 */

public class OrderCardFragment extends BaseFragment implements OrderContract.OrderCardView {

    @BindView(R.id.mall_item_pic_iv)
    ImageView mallItemPicIv;
    @BindView(R.id.mall_item_name_tv)
    TextView mallItemNameTv;
    @BindView(R.id.mall_item_coin_tv)
    TextView mallItemCoinTv;
    @BindView(R.id.mall_item_count_tv)
    TextView mallItemCountTv;
    @BindView(R.id.order_phone_et)
    EditText orderPhoneEt;
    @BindView(R.id.order_phone_again_et)
    EditText orderPhoneAgainEt;
    @BindView(R.id.order_price_tv)
    TextView orderPriceTv;
    @BindView(R.id.order_agreement_cb)
    AppCompatCheckBox orderAgreementCb;
    @BindView(R.id.price_tv)
    TextView priceTv;
    @BindView(R.id.confirm_btn)
    Button confirmBtn;

    OrderContract.OrderCardPresenter mPresenter;

    public OrderCardFragment() {
        new OrderCardPresenterImpl(this);
    }

    public static OrderCardFragment newInstance(int goodsId) {
        OrderCardFragment fragment = new OrderCardFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(OrderActivity.ORDER_GOODS_ID, goodsId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter.getGoodsInfo(getArguments().getInt(OrderActivity.ORDER_GOODS_ID));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_confirm_order_phone_card_layout;
    }

    @Override
    public void setPresenter(OrderContract.OrderCardPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {

        orderAgreementCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                orderAgreementCb.setChecked(isChecked);
                confirmBtn.setEnabled(isChecked);
            }
        });
        UserInfo userInfo = AccountManager.getInstance().getUserInfo();
        if (null != userInfo) {
            String phone = userInfo.getPhone();
            orderPhoneEt.setText(TextUtils.isEmpty(phone) ? "" : phone);
            orderPhoneAgainEt.setText(TextUtils.isEmpty(phone) ? "" : phone);
            orderPhoneEt.setSelection((TextUtils.isEmpty(phone) ? "" : phone).length());
        }
    }

    @Override
    protected void fetchData(boolean isLoadMore) {

    }

    @Override
    public boolean isActive() {
        return false;
    }


    private GoodsInfo mGoodsInfoBean = null;

    @Override
    public void showGoodsInfo(GoodsInfo goodsInfoBean) {
        if (goodsInfoBean == null)
            return;
        mGoodsInfoBean = goodsInfoBean;

        Glide.with(getActivity()).load(goodsInfoBean.getPicUrl())
                .centerCrop()
                .placeholder(R.drawable.ic_game_pic_default_01)
                .error(R.drawable.ic_game_pic_default_01)
                .into(mallItemPicIv);

        mallItemNameTv.setText(goodsInfoBean.getName());

//        String coin_str = "";
//        if (goodsInfoBean.getNeedScoreId() == State.MallType.COINS){
//            coin_str = getActivity().getString(R.string.lyy_me_coins);
//        }else if (goodsInfoBean.getNeedScoreId() == com.hhly.lyygame.data.net.protocol.user.State.MallType.COUPON){
//            coin_str = getActivity().getString(R.string.lyy_me_coupon);
//        }else if (goodsInfoBean.getNeedScoreId() == com.hhly.lyygame.data.net.protocol.user.State.MallType.SCORE){
//            coin_str = getActivity().getString(R.string.lyy_me_score);
//        }
//        mallItemCoinTv.setText(coin_str + (int)goodsInfoBean.getNeedScore());

        String price = NumberFormatUtils.doubleTrans2(goodsInfoBean.getNeedScore());
        mallItemCoinTv.setText(getContext().getString(R.string.lyy_game_order_rmb_str, price));
        mallItemCountTv.setText("x 1");

//        setPriceTvText(goodsInfoBean);
        priceTv.setText(getString(R.string.lyy_game_order_price_all_rmb_str, 1, price));
        orderPriceTv.setText(getString(R.string.lyy_game_order_price_rmb_str, 1, price));
    }

    /**
     * 根据商品类型显示文字
     * needScoreId 1：乐盈币 2：乐盈券 3：积分
     *
     * @param
     */
    /*private void setPriceTvText(GoodsInfo goodsInfoBean) {
        switch (goodsInfoBean.getNeedScoreId()) {
            case 1:
                priceTv.setText(getString(R.string.lyy_game_order_price_all_coins, 1, (int) goodsInfoBean.getNeedScore()));
                orderPriceTv.setText(getString(R.string.lyy_game_order_price_coins, 1, (int) goodsInfoBean.getNeedScore()));
                break;
            case 2:
                priceTv.setText(getString(R.string.lyy_game_order_price_all_coupon, 1, (int) goodsInfoBean.getNeedScore()));
                orderPriceTv.setText(getString(R.string.lyy_game_order_price_coupon, 1, (int) goodsInfoBean.getNeedScore()));
                break;
            case 3:
                priceTv.setText(getString(R.string.lyy_game_order_price_all, 1, (int) goodsInfoBean.getNeedScore()));
                orderPriceTv.setText(getString(R.string.lyy_game_order_price, 1, (int) goodsInfoBean.getNeedScore()));
                break;
            default:
                break;
        }
    }*/

    @Override
    public void showExchangeGoods() {
        ToastUtil.showTip(getActivity(), R.string.lyy_exchange_succeed);
        postDelay(new Runnable() {
            @Override
            public void run() {
                onBackPressed();
            }
        }, 500);
    }

    @Override
    public void onGoodsInfoFailure() {

    }


    @Override
    public void onExchangeFailure(String msg) {
        ToastUtil.showTip(getActivity(), "".equals(msg) ? getString(R.string.lyy_game_network_state) : msg);
        confirmBtn.setClickable(true);
    }

    @OnClick(R.id.confirm_btn)
    void confirmClick(View view) {
        if (!NetworkUtil.isAvailable(App.getContext())) {
            ToastUtil.showTip(App.getContext(), R.string.lyy_network_notwork);
            return;
        }

        if (mGoodsInfoBean == null) {
            ToastUtil.showTip(getActivity(), R.string.lyy_connect_server_error);
            return;
        }

        String orderPhone = orderPhoneEt.getText().toString().trim();
        String orderPhoneAgain = orderPhoneAgainEt.getText().toString().trim();

        if (TextUtils.isEmpty(orderPhone) || TextUtils.isEmpty(orderPhoneAgain)) {
            ToastUtil.showTip(getActivity(), R.string.lyy_game_address_edit_hint_phone);
            return;
        }
        if (!orderPhone.equalsIgnoreCase(orderPhoneAgain)) {
            ToastUtil.showTip(getActivity(), R.string.lyy_game_order_card_phone_check);
            return;
        }
        if (!RegexUtils.checkMobile(orderPhone)) {
            ToastUtil.showTip(getActivity(), R.string.lyy_game_order_card_phone);
            return;
        }
        // confirmBtn.setClickable(false);

//        GoodsExchangeReq.ExGoods exGoods = new GoodsExchangeReq.ExGoods();
//        exGoods.setCount(1);
//        exGoods.setGoodsId(mGoodsInfoBean.getId());
//        exGoods.setPhone(orderPhone);
//        exGoods.setUserId(AccountManager.getInstance().getUserId());
//
//        mPresenter.exchangeGoods(exGoods);

        if (null != mGoodsInfoBean) {
            if (mGoodsInfoBean.getNeedScore() > 10000000) {
                ToastUtil.showTip(getContext(), "支付金额超额");
                return;
            }

            Intent intent = new Intent(getActivity(), PayOrderActivity.class);
            intent.putExtra("goodsInfo", mGoodsInfoBean);
            intent.putExtra("phone", orderPhone);
            getActivity().startActivity(intent);
            onBackPressed();
        }
    }

    /**
     * 兑换协议
     *
     * @param view
     */
    @OnClick(R.id.order_agreement_tv)
    void agreementClick(View view) {
        ActivityCompat.startActivity(getActivity(), WebLocationActivity.getCallingIntent(getActivity(),
                "http://game.13322.com/xuzhi.htm", getActivity().getString(R.string.lyy_exchange_agreement_title)), null);
    }

}
