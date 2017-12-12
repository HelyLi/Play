package com.hhly.lyygame.presentation.view.order;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.model.GoodsInfo;
import com.hhly.lyygame.presentation.utils.NumberFormatUtils;

import java.util.ArrayList;

/**
 * Created by Simon on 2016/12/1.
 */

public class OrderAdapter extends BaseQuickAdapter<GoodsInfo, BaseViewHolder> {

    public OrderAdapter() {
        super(R.layout.fragment_order_item_layout, new ArrayList<GoodsInfo>());
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, GoodsInfo goodsInfo) {

        Glide.with(baseViewHolder.itemView.getContext()).load(goodsInfo.getPicUrl())
                .centerCrop()
                .placeholder(R.drawable.ic_game_pic_default_01)
                .error(R.drawable.ic_game_pic_default_01)
                .into((ImageView) baseViewHolder.itemView.findViewById(R.id.mall_item_pic_iv));

        baseViewHolder.setText(R.id.mall_item_name_tv, goodsInfo.getName());

        String price = NumberFormatUtils.doubleTrans2(goodsInfo.getNeedScore());
        baseViewHolder.setText(R.id.mall_item_coin_tv, baseViewHolder.itemView.getContext().getString(R.string.lyy_game_order_rmb_str, price));
//        if (goodsInfo.getNeedScoreId() == 1) {
//            baseViewHolder.setText(R.id.mall_item_coin_tv,
//                    baseViewHolder.itemView.getContext().getString(R.string.lyy_game_order_coins, (int) goodsInfo.getNeedScore()));
//        } else if (goodsInfo.getNeedScoreId() == 2) {
//            baseViewHolder.setText(R.id.mall_item_coin_tv,
//                    baseViewHolder.itemView.getContext().getString(R.string.lyy_game_order_coupon, (int) goodsInfo.getNeedScore()));
//        } else if (goodsInfo.getNeedScoreId() == 3) {
//            baseViewHolder.setText(R.id.mall_item_coin_tv,
//                    baseViewHolder.itemView.getContext().getString(R.string.lyy_game_order_score, (int) goodsInfo.getNeedScore()));
//        }
        
        baseViewHolder.setText(R.id.mall_item_count_tv, baseViewHolder.itemView.getContext().getString(R.string.lyy_game_order_x, goodsInfo.getOrderNum()));
    }

}
