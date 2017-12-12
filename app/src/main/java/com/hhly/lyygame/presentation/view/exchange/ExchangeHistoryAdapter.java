package com.hhly.lyygame.presentation.view.exchange;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.protocol.goods.GoodsExchangeHistoryResp;
import com.hhly.lyygame.presentation.utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;

/**
 * 兑换记录
 * Created by Simon on 2016/11/29.
 */

public class ExchangeHistoryAdapter extends BaseQuickAdapter<GoodsExchangeHistoryResp.ExchangeBean, BaseViewHolder> {

    public ExchangeHistoryAdapter() {
        super(R.layout.fragment_exchange_history_item_layout, new ArrayList<GoodsExchangeHistoryResp.ExchangeBean>());
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, GoodsExchangeHistoryResp.ExchangeBean exchangeBean) {

        if (exchangeBean.getPicUrl() != null)
            Glide.with(baseViewHolder.itemView.getContext()).load(exchangeBean.getPicUrl()).asBitmap()
                    .centerCrop().into((ImageView) baseViewHolder.getView(R.id.goods_iv));

        baseViewHolder.setText(R.id.goods_name_tv, exchangeBean.getGoodsName());
        baseViewHolder.setText(R.id.goods_count_tv, baseViewHolder.itemView.getContext().getString(R.string.lyy_game_count, exchangeBean.getCount()));

//        String exchangePrice = NumberFormatUtils.doubleTrans2(exchangeBean.getExchangePrice());
        baseViewHolder.setText(R.id.goods_score_tv, exchangeBean.getExchangePrice());
        baseViewHolder.setText(R.id.goods_date_tv, DateUtils.formatDate(new Date(exchangeBean.getCreateTime())));

    }

}
