package com.hhly.lyygame.presentation.view.gamehall;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhly.lyygame.R;

import java.util.ArrayList;
import java.util.Random;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**4个一行的宫格
 * Created by Simon on 2016/11/25.
 */

public class GameGridAdapter extends BaseQuickAdapter<Object, BaseViewHolder> {

    private Context mContext;

    private String[] mImages = {"http://imgb.mumayi.com/android/icon/001/01/32/62/96.png?is=255a9e848cd94514ee460f6d4da1332b",
    "http://imga.mumayi.com/android/icon/001/08/13/44/96.png?is=d54b5ff8e4a494c338aaeb3061849c86"};
    private Random mRandom = new Random();

    public GameGridAdapter(Context context) {
        super(R.layout.lyy_game_item_layout_04, new ArrayList<Object>());
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Object o) {

        Glide.with(mContext).load(mImages[mRandom.nextInt(mImages.length)])
                .centerCrop()
                .bitmapTransform(new RoundedCornersTransformation(mContext, mContext.getResources().getDimensionPixelSize(R.dimen.round_radius), 0))
                .placeholder(R.drawable.ic_game_pic_default_01)
                .error(R.drawable.ic_game_pic_default_01)
                .into((ImageView) baseViewHolder.getView(R.id.game_item_pic_iv));

    }
}
