package com.hhly.lyygame.presentation.view.mall;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hhly.lyygame.App;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.model.GoodsInfo;
import com.hhly.lyygame.data.net.protocol.user.State;
import com.hhly.lyygame.presentation.utils.NetworkUtil;
import com.hhly.lyygame.presentation.utils.NumberFormatUtils;
import com.hhly.lyygame.presentation.view.BaseActivity;
import com.hhly.lyygame.presentation.view.ToastUtil;
import com.hhly.lyygame.presentation.view.order.OrderActivity;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * Created by Simon on 2016/11/30.
 */

public class MallOptDialog extends Dialog implements MallContract.MallOptView {

    @BindView(R.id.name_tv)
    TextView mNameTv;
    @BindView(R.id.price_tv)
    TextView mPriceTv;
    @BindView(R.id.original_tv)
    TextView mOriginalTv;
    @BindView(R.id.count_description_tv)
    TextView mCountDescriptionTv;
    @BindView(R.id.minus_btn)
    ImageButton mMinusBtn;
    @BindView(R.id.count_et)
    EditText mCountEt;
    @BindView(R.id.add_btn)
    ImageButton mAddBtn;
    @BindView(R.id.opt_btn)
    Button mOptBtn;
    @BindView(R.id.mall_item_pic_iv)
    ImageView mMallItemPicIv;
    @BindView(R.id.count_select_title_tv)
    TextView mCountSelectTitleTv;
    @BindView(R.id.count_root)
    LinearLayout mCountRoot;

    private int mCount = 1;

    @OnClick({R.id.minus_btn, R.id.add_btn, R.id.opt_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            //// FIXME: 2017/5/19 产品屏蔽数量修改
//            case R.id.minus_btn:
//                mCount = Math.max(0, --mCount);
//                updateCount();
//                break;
//            case R.id.add_btn:
//                mCount++;
//                updateCount();
//                break;
            case R.id.opt_btn:
//                if (!NetworkUtil.isAvailable(getContext())) {
//                    ToastUtil.showTip(getContext(), getContext().getString(R.string.lyy_network_notwork));
//                    dismiss();
//                    return;
//                }
                if (mType == TYPE_EXCHANGE) {//兑换
                    exchangeGoods(mGoodsInfo);
                } else if (mType == TYPE_FAVOURITE) {//收藏
                    mPresenter.addStoreGoods(mGoodsInfo.getId());
                }
                mOptBtn.setClickable(false);
                break;
        }
    }

    private void exchangeGoods(GoodsInfo goodsInfo) {
        if (!NetworkUtil.isAvailable(getContext())){
            ToastUtil.showTip(getContext(),R.string.lyy_game_network_state);
            return;
        }
        if (goodsInfo.getType() == State.GoodsType.MATTER) {//实物
            ActivityCompat.startActivity(getContext(), OrderActivity.getOrderCallingIntent(getContext(), new int[]{mGoodsInfo.getId()}, new int[]{mCount}), null);
            dismiss();
        } else if (goodsInfo.getType() == State.GoodsType.CARD) {//电话卡
            ActivityCompat.startActivity(getContext(), OrderActivity.getOrderCardCallingIntent(getContext(), mGoodsInfo.getId()), null);
            dismiss();
        } else {//QQ卡
//            GoodsExchangeReq.ExGoods exGoods = new GoodsExchangeReq.ExGoods();
//            exGoods.setGoodsId(mGoodsInfo.getId());
//            exGoods.setUserId(AccountManager.getInstance().getUserId());
//            exGoods.setCount(Integer.parseInt(mCountEt.getText().toString().trim()));
//            exGoods.setCountry(0);
//
//            mPresenter.exchangeGoods(exGoods);

            ActivityCompat.startActivity(getContext(), OrderActivity.getOrderGiftCallingIntent(getContext(), mGoodsInfo.getId()), null);
            dismiss();
        }
    }

    private void updateCount() {
        mCountEt.setText(String.valueOf(mCount));
        mCountEt.setSelection(mCountEt.getText().length());
    }

    @Override
    public void addStoreSuccess() {
        if (mCallback != null) {
            mCallback.addStoreSuccess();
        }
        dismiss();
    }

    @Override
    public void addStoreFailure(String msg) {
        //        DialogFactory.createExchangeResult(App.getContext(), ExchangeResultDialog.TYPE_FAILURE, mGoodsInfo.getName());
        ToastUtil.showTip(App.getContext(), TextUtils.isEmpty(msg) ? App.getContext().getString(R.string.lyy_game_network_state) : msg);
        dismiss();
    }

    @Override
    public void exchangeGoodsSuccess() {
//        DialogFactory.createExchangeResult(App.getTopActivity(), ExchangeResultDialog.TYPE_SUCCEED, mGoodsInfo.getName());
        ToastUtil.showTip(App.getContext(), R.string.lyy_game_exchange_success);
        dismiss();
    }

    @Override
    public void exchangeGoodsFailure(String msg) {
//        DialogFactory.createExchangeResult(App.getTopActivity(), ExchangeResultDialog.TYPE_FAILURE, mGoodsInfo.getName());
        ToastUtil.showTip(App.getContext(), TextUtils.isEmpty(msg) ? App.getTopActivity().getString(R.string.lyy_game_exchange_failure) : msg);
        dismiss();
    }

    @Override
    public void setPresenter(MallContract.MallOptPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return mActivity.bindToLife();
    }

    @IntDef({TYPE_EXCHANGE, TYPE_FAVOURITE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface OptType {
    }

    public static final int TYPE_EXCHANGE = 0;
    public static final int TYPE_FAVOURITE = 1;
    private int mType = TYPE_EXCHANGE;
    private GoodsInfo mGoodsInfo = null;

    private MallContract.MallOptPresenter mPresenter;

    public MallOptDialog(@NonNull Context context, @NonNull GoodsInfo goodsInfo, @OptType int type, MallStateCallback callback) {
        super(context, R.style.Theme_LyyGame_Dialog_Fullscreen);
        mType = type;
        new MallOptPresenterImpl(this);
        mGoodsInfo = goodsInfo;
        if (context instanceof BaseActivity) {
            mActivity = (BaseActivity) context;
        }
        mCallback = callback;
    }

    private BaseActivity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mall_attribute_opt_layout);
        ButterKnife.bind(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.gravity = Gravity.BOTTOM;
        getWindow().setAttributes(lp);

        setCanceledOnTouchOutside(true);

        if (mType == TYPE_EXCHANGE) {
            mOptBtn.setText(R.string.lyy_buy);
        } else if (mType == TYPE_FAVOURITE) {
            mCountSelectTitleTv.setVisibility(View.INVISIBLE);
            mCountRoot.setVisibility(View.GONE);
            mOptBtn.setText(R.string.lyy_favourite);
        }
        mCountEt.setSelection(mCountEt.getText().length());
        mCountEt.setEnabled(false);
        if (mGoodsInfo.getType() == State.GoodsType.CARD) {
            mMinusBtn.setEnabled(false);
            mAddBtn.setEnabled(false);
        }

        Glide.with(getContext()).load(mGoodsInfo.getPicUrl())
                .centerCrop()
                .placeholder(R.drawable.ic_mall_pic_default)
                .error(R.drawable.ic_mall_pic_default)
                .into(mMallItemPicIv);

        mNameTv.setText(mGoodsInfo.getName());

//        String coin_str = null;
//        if (mGoodsInfo.getNeedScoreId() == State.MallType.COINS) {
//            coin_str = getContext().getString(R.string.lyy_me_coins);
//        } else if (mGoodsInfo.getNeedScoreId() == State.MallType.COUPON) {
//            coin_str = getContext().getString(R.string.lyy_me_coupon);
//        } else if (mGoodsInfo.getNeedScoreId() == State.MallType.SCORE) {
//            coin_str = getContext().getString(R.string.lyy_me_score);
//        }

        String needScore = NumberFormatUtils.doubleTrans2(mGoodsInfo.getNeedScore());
        mPriceTv.setText(needScore + "元");

        String price = NumberFormatUtils.doubleTrans2(mGoodsInfo.getPrice());
        mOriginalTv.setText(getContext().getString(R.string.lyy_game_order_original_price_str, price));
    }

    @OnTextChanged(value = R.id.count_et, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void onCountChanged(Editable editable) {
        if (editable.toString().length() > 0 && Integer.valueOf(editable.toString()) > 0) {
            mOptBtn.setEnabled(true);
        } else {
            mOptBtn.setEnabled(false);
        }
    }

    @OnTextChanged(value = R.id.count_et, callback = OnTextChanged.Callback.TEXT_CHANGED)
    void onTextCountChanged(CharSequence s, int i, int k, int j) {
        if (s.toString().length() == 0) {
            mCount = 0;
        } else {
            mCount = Integer.parseInt(s.toString());
        }
    }

    public interface MallStateCallback {
        void addStoreSuccess();
    }

    private MallStateCallback mCallback;

}
