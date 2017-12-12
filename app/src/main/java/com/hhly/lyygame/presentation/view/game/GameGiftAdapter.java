package com.hhly.lyygame.presentation.view.game;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.protocol.game.GiftBagResp;

import java.util.ArrayList;

/**
 * Created by Simon on 2016/12/2.
 */

public class GameGiftAdapter extends BaseQuickAdapter<GiftBagResp.GiftBagBean, BaseViewHolder> {

    public GameGiftAdapter() {
        super(R.layout.fragment_game_gift_item_layout, new ArrayList<GiftBagResp.GiftBagBean>());
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, GiftBagResp.GiftBagBean giftBagBean) {

        ImageView iv = (ImageView) baseViewHolder.itemView.findViewById(R.id.gift_pic_iv);
        iv.setSelected(giftBagBean.isCheck());

        int canUse = giftBagBean.getCanUse();
        String appliedCount = giftBagBean.getAppliedCount();

        if ("1".equalsIgnoreCase(appliedCount)) {//已领取
            baseViewHolder.setImageResource(R.id.gift_type_iv, R.drawable.ic_game_gift_receive);
        } else {
            if (canUse > 0) {//进行中
                baseViewHolder.setImageResource(R.id.gift_type_iv, R.drawable.ic_activity_status_active);
            } else {//已领完
                baseViewHolder.setImageResource(R.id.gift_type_iv, R.drawable.ic_game_gift_over);
            }
        }

        baseViewHolder.setText(R.id.gift_name_tv, giftBagBean.getTitle());
        Glide.with(baseViewHolder.itemView.getContext()).load(giftBagBean.getRotationImgUrl())
                .centerCrop()
                .placeholder(R.drawable.ic_gift_default)
                .error(R.drawable.ic_gift_default)
                .into((ImageView) baseViewHolder.getView(R.id.gift_pic_iv));
    }

}
