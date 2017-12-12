package com.hhly.lyygame.presentation.view.gamehall;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhly.lyygame.R;
import java.util.ArrayList;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * 网络游戏
 * Created by Simon on 2016/12/10.
 */

public class GameOnlineAdapter extends BaseQuickAdapter<Object, BaseViewHolder> {

    public GameOnlineAdapter(Context context) {
        super(R.layout.lyy_game_item_layout_02, new ArrayList<Object>());
        mContext = context;
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
