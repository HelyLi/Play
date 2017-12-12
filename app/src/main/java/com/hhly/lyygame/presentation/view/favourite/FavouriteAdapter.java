package com.hhly.lyygame.presentation.view.favourite;

import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhly.lyygame.App;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.protocol.goods.StoreGoodsResp;
import com.hhly.lyygame.data.net.protocol.user.State;
import com.hhly.lyygame.presentation.utils.DateUtils;
import com.hhly.lyygame.presentation.utils.NetworkUtil;
import com.hhly.lyygame.presentation.utils.NumberFormatUtils;
import com.hhly.lyygame.presentation.view.ToastUtil;
import com.hhly.lyygame.presentation.view.order.OrderActivity;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Simon on 2016/11/29.
 */

public class FavouriteAdapter extends BaseQuickAdapter<FavouriteAdapter.FavouriteItemData, BaseViewHolder> {

    private Context mContext;

    public FavouriteAdapter(Context context) {
        super(R.layout.fragment_favourite_item_layout, new ArrayList<FavouriteAdapter.FavouriteItemData>());
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, FavouriteAdapter.FavouriteItemData itemData) {

        final StoreGoodsResp.StoreGoodsBean goodsBean = itemData.getGoodsBean();

        if (goodsBean.getPicUrl() != null)
            Glide.with(baseViewHolder.itemView.getContext()).load(goodsBean.getPicUrl()).asBitmap()
                    .centerCrop().into((ImageView) baseViewHolder.getView(R.id.goods_iv));

        baseViewHolder.setText(R.id.goods_name_tv, goodsBean.getName());
//        String scoreTv = "";
//
//        if (goodsBean.getNeedScoreId() == com.hhly.lyygame.data.net.protocol.user.State.MallType.COINS) {
//            scoreTv = mContext.getString(R.string.lyy_me_coins);
//        } else if (goodsBean.getNeedScoreId() == com.hhly.lyygame.data.net.protocol.user.State.MallType.COUPON) {
//            scoreTv = mContext.getString(R.string.lyy_me_coupon);
//        } else if (goodsBean.getNeedScoreId() == com.hhly.lyygame.data.net.protocol.user.State.MallType.SCORE) {
//            scoreTv = mContext.getString(R.string.lyy_me_score);
//        }

        baseViewHolder.setText(R.id.goods_count_tv, mContext.getString(R.string.lyy_game_count, 1));
//        baseViewHolder.setText(R.id.goods_score_tv, scoreTv + goodsBean.getPrice());

        String needScore = NumberFormatUtils.doubleTrans2(goodsBean.getNeedScore());
        baseViewHolder.setText(R.id.goods_score_tv, mContext.getString(R.string.lyy_game_order_rmb_str, needScore));
        baseViewHolder.setText(R.id.goods_date_tv, DateUtils.formatDate(new Date(goodsBean.getCreateTime())));

//        baseViewHolder.setChecked(R.id.favourite_cb, itemData.isSelect());
//        final TextView tvCheckBox = (TextView) baseViewHolder.itemView.findViewById(R.id.favourite_cb);
//        tvCheckBox.setSelected(itemData.isSelect());
//        baseViewHolder.addOnClickListener(R.id.favourite_cb);
        baseViewHolder.itemView.findViewById(R.id.layout_history_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!NetworkUtil.isAvailable(App.getContext())) {
                    ToastUtil.showTip(App.getContext(), R.string.lyy_network_notwork);
                    return;
                }
                if (goodsBean.getType() == State.GoodsType.MATTER) {//实物
                    ActivityCompat.startActivity(mContext, OrderActivity.getOrderCallingIntent(mContext, new int[]{goodsBean.getGoodsId()}, new int[]{1}), null);
                } else if (goodsBean.getType() == State.GoodsType.CARD) {//电话卡
                    ActivityCompat.startActivity(mContext, OrderActivity.getOrderCardCallingIntent(mContext, goodsBean.getGoodsId()), null);
                } else {//礼品
                    ActivityCompat.startActivity(mContext, OrderActivity.getOrderGiftCallingIntent(mContext, goodsBean.getGoodsId()), null);
                }
            }
        });
        baseViewHolder.addOnClickListener(R.id.delete_favourite_iv);
    }

    public static class FavouriteItemData {

        private boolean select = false;
        private StoreGoodsResp.StoreGoodsBean goodsBean;

        public FavouriteItemData(StoreGoodsResp.StoreGoodsBean goodsBean) {
            this.goodsBean = goodsBean;
        }

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }

        public StoreGoodsResp.StoreGoodsBean getGoodsBean() {
            return goodsBean;
        }
    }
}
