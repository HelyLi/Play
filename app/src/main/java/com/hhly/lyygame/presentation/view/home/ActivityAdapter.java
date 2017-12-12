package com.hhly.lyygame.presentation.view.home;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.protocol.game.IndexActivityByModelIdResp;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by dell on 2017/5/15.
 */

public class ActivityAdapter extends BaseQuickAdapter<IndexActivityByModelIdResp.ActivityPage.ActivityInfo, BaseViewHolder> {

    public ActivityAdapter() {
        super(R.layout.lyy_home_activity_item_layout, new ArrayList<IndexActivityByModelIdResp.ActivityPage.ActivityInfo>());
    }

    @Override
    protected void convert(BaseViewHolder helper, IndexActivityByModelIdResp.ActivityPage.ActivityInfo item) {
        helper.addOnClickListener(R.id.select_item_icon_iv);

        Glide.with(mContext).load(item.getImageUrl())
                .fitCenter()
                .bitmapTransform(new RoundedCornersTransformation(mContext, mContext.getResources().getDimensionPixelSize(R.dimen.round_radius), 0))
                .placeholder(R.drawable.ic_game_pic_default_03)
                .error(R.drawable.ic_game_pic_default_03)
                .into((ImageView) helper.getView(R.id.select_item_icon_iv));
    }

}
