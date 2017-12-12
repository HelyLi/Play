package com.hhly.lyygame.presentation.view.gamehall;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhly.lyygame.R;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * 最新游戏
 * Created by Simon on 2016/11/25.
 */

public class GameNewAdapter extends BaseQuickAdapter<Object, BaseViewHolder> {

    public GameNewAdapter() {
        super(R.layout.lyy_game_item_layout_03, new ArrayList<Object>());
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Object o) {
        Glide.with(mContext).load("http://imgsrc.baidu.com/anxun/pic/item/5ab5c9ea15ce36d3d1a8f88d3df33a87e850b1da.jpg")
                .centerCrop()
                .bitmapTransform(new RoundedCornersTransformation(mContext, mContext.getResources().getDimensionPixelSize(R.dimen.round_radius), 0))
                .placeholder(R.drawable.ic_game_pic_default_03)
                .error(R.drawable.ic_game_pic_default_03)
                .into((ImageView) baseViewHolder.getView(R.id.game_item_pic_iv));
    }
}
