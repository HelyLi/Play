package com.hhly.lyygame.presentation.view.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hhly.lyygame.App;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.model.GoodsInfo;
import com.hhly.lyygame.presentation.utils.NetworkUtil;
import com.hhly.lyygame.presentation.utils.NumberFormatUtils;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 礼包确认订单
 * Created by Simon on 2016/12/14.
 */

public class OrderGiftFragment extends BaseFragment implements OrderContract.OrderGiftView {

    @BindView(R.id.mall_item_pic_iv)
    ImageView mallItemPicIv;
    @BindView(R.id.mall_item_name_tv)
    TextView mallItemNameTv;
    @BindView(R.id.mall_item_coin_tv)
    TextView mallItemCoinTv;
    @BindView(R.id.mall_item_count_tv)
    TextView mallItemCountTv;
    @BindView(R.id.order_price_tv)
    TextView orderPriceTv;

    @BindView(R.id.price_tv)
    TextView priceTv;
    @BindView(R.id.confirm_btn)
    Button confirmBtn;
    @BindView(R.id.order_qq_et)
    EditText mOrderQqEt;
    @BindView(R.id.order_qq_again_et)
    EditText mOrderQqAgainEt;

    public OrderGiftFragment() {
        new OrderGiftPresenterImpl(this);
    }

    public static OrderGiftFragment newInstance(int giftId) {
        OrderGiftFragment fragment = new OrderGiftFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(OrderActivity.ORDER_GIFT_ID, giftId);
        fragment.setArguments(bundle);
        return fragment;
    }

    private OrderContract.OrderGiftPresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter.getGoodsInfo(getArguments().getInt(OrderActivity.ORDER_GIFT_ID));
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
    }

    @Override
    protected void fetchData(boolean isLoadMore) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_confirm_order_gift_layout;
    }

    @Override
    public void setPresenter(OrderContract.OrderGiftPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    /**
     * 根据商品类型显示文字
     * needScoreId 1：乐盈币 2：乐盈券 3：积分
     *
     * @param
     */
    /*private void setPriceTvText(SignGiftBagResp.GiftBagBean giftBagBean) {
        switch (giftBagBean.getNeedScoreId()) {
            case 1:
                priceTv.setText(getString(R.string.lyy_game_order_price_all_coins, 1, giftBagBean.getNeedScore()));
                orderPriceTv.setText(getString(R.string.lyy_game_order_price_coins, 1, giftBagBean.getNeedScore()));
                break;
            case 2:
                priceTv.setText(getString(R.string.lyy_game_order_price_all_coupon, 1, giftBagBean.getNeedScore()));
                orderPriceTv.setText(getString(R.string.lyy_game_order_price_coupon, 1, giftBagBean.getNeedScore()));
                break;
            case 3:
                priceTv.setText(getString(R.string.lyy_game_order_price_all, 1, giftBagBean.getNeedScore()));
                orderPriceTv.setText(getString(R.string.lyy_game_order_price, 1, giftBagBean.getNeedScore()));
                break;
            default:
                break;
        }
    }*/

    @Override
    public void showExchangeGiftBag() {
        dismissLoading();
        ToastUtil.showTip(getActivity(), R.string.lyy_exchange_succeed);
        postDelay(new Runnable() {
            @Override
            public void run() {
                onBackPressed();
            }
        }, 500);
    }

    @Override
    public void showExchangeGiftBagFailure(String msg) {
        dismissLoading();
        showMsg(TextUtils.isEmpty(msg) ? "" : msg);
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

        String price = NumberFormatUtils.doubleTrans2(goodsInfoBean.getNeedScore());
        mallItemCoinTv.setText(getContext().getString(R.string.lyy_game_order_rmb_str, price));
        mallItemCountTv.setText("x 1");

        mallItemNameTv.setText(goodsInfoBean.getName());

        priceTv.setText(getString(R.string.lyy_game_order_price_all_rmb_str, 1, price));
        orderPriceTv.setText(getString(R.string.lyy_game_order_price_rmb_str, 1, price));
    }

    @Override
    public void onGoodsInfoFailure() {

    }

    private int giftBagId = 0;

    @OnClick(R.id.confirm_btn)
    void confirmClick(View view) {
        // mPresenter.exchangeGiftBag(giftBagId);
        if (!NetworkUtil.isAvailable(App.getContext())) {
            ToastUtil.showTip(App.getContext(), R.string.lyy_network_notwork);
            return;
        }

        if (mGoodsInfoBean == null) {
            ToastUtil.showTip(getActivity(), R.string.lyy_connect_server_error);
            return;
        }

        String orderQq = mOrderQqEt.getText().toString().trim();
        String orderQqAgain = mOrderQqAgainEt.getText().toString().trim();

        if (TextUtils.isEmpty(orderQq) || TextUtils.isEmpty(orderQqAgain)) {
            ToastUtil.showTip(getActivity(), R.string.lyy_game_order_qq_null);
            return;
        }
        if (orderQq.length() < 5){
            ToastUtil.showTip(getActivity(), R.string.lyy_game_order_qq_less);
            return;
        }
        if (!orderQq.equalsIgnoreCase(orderQqAgain)) {
            ToastUtil.showTip(getActivity(), R.string.lyy_game_order_card_qq_check);
            return;
        }

        if (null != mGoodsInfoBean) {
            if (mGoodsInfoBean.getNeedScore() > 10000000) {
                ToastUtil.showTip(getContext(), "支付金额超额");
                return;
            }
            Intent intent = new Intent(getActivity(), PayOrderActivity.class);
            intent.putExtra(PayOrderActivity.GOODS_INFO, mGoodsInfoBean);
            intent.putExtra(PayOrderActivity.PAY_TO_ACCOUNT, orderQq);
            getActivity().startActivity(intent);
            onBackPressed();
        }
    }

}
