package com.hhly.lyygame.presentation.view.mall.category;

import android.graphics.Paint;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.db.manager.AccountManager;
import com.hhly.lyygame.data.model.GoodsInfo;
import com.hhly.lyygame.data.net.protocol.user.State;
import com.hhly.lyygame.presentation.utils.NumberFormatUtils;

import java.util.ArrayList;


/**
 * Created by Simon on 2016/11/23.
 */

public class MallListAdapter extends BaseQuickAdapter<GoodsInfo, BaseViewHolder> {

    public MallListAdapter() {
        super(R.layout.fragment_mall_item_layout, new ArrayList<GoodsInfo>());
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, GoodsInfo mallItem) {
         TextView originalTv = baseViewHolder.getView(R.id.mall_item_original_tv);
         originalTv.setPaintFlags(originalTv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        baseViewHolder.setText(R.id.mall_item_name_tv, mallItem.getName());

//        1：乐盈币 2：乐盈券 3：积分
//        String coin_str = null;
//        if (mallItem.getNeedScoreId() == State.MallType.COINS){
//            coin_str = baseViewHolder.itemView.getContext().getString(R.string.lyy_me_coins);
//        }else if (mallItem.getNeedScoreId() == State.MallType.COUPON){
//            coin_str = baseViewHolder.itemView.getContext().getString(R.string.lyy_me_coupon);
//        }else if (mallItem.getNeedScoreId() == State.MallType.SCORE){
//            coin_str = baseViewHolder.itemView.getContext().getString(R.string.lyy_me_score);
//        }

        String needScore = NumberFormatUtils.doubleTrans2(mallItem.getNeedScore());
        String price = NumberFormatUtils.doubleTrans2(mallItem.getPrice());
        baseViewHolder.setText(R.id.mall_item_coin_tv, baseViewHolder.itemView.getContext().getString(R.string.lyy_game_order_rmb_str, needScore));
        baseViewHolder.setText(R.id.mall_item_original_tv, baseViewHolder.itemView.getContext().getString(R.string.lyy_game_order_original_price_str, price));//lyy_game_order_original_price

        baseViewHolder.addOnClickListener(R.id.mall_item_exchange_btn);

        if (TextUtils.isEmpty(mallItem.getUserId()) || !mallItem.getUserId().equals(AccountManager.getInstance().getUserId())){
//            baseViewHolder.setBackgroundRes(R.id.mall_item_favourite_btn, R.drawable.bg_collection);
            baseViewHolder.setText(R.id.mall_item_favourite_btn, R.string.lyy_favourite);
        }else {
//            baseViewHolder.setBackgroundRes(R.id.mall_item_favourite_btn, R.drawable.bg_collection);
            baseViewHolder.setText(R.id.mall_item_favourite_btn, R.string.lyy_cancel_favourite);
        }

        baseViewHolder.addOnClickListener(R.id.mall_item_favourite_btn);

        Glide.with(baseViewHolder.itemView.getContext())
                .load(mallItem.getPicUrl())
                .centerCrop()
                .placeholder(R.drawable.ic_mall_pic_default)
                .error(R.drawable.ic_mall_pic_default)
                .into((ImageView) baseViewHolder.getView(R.id.mall_item_pic_iv));
    }
}
