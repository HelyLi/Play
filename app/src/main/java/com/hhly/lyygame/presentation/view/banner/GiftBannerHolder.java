package com.hhly.lyygame.presentation.view.banner;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.protocol.banner.HomeBannerResp;

/**
 * Created by Simon on 2016/12/7.
 */

public class GiftBannerHolder implements Holder<HomeBannerResp.Pager.BannerInfo> {

    private ImageView imageView;

    @Override
    public View createView(Context context) {
        imageView = new ImageView(context);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, HomeBannerResp.Pager.BannerInfo data) {
        Glide.with(context)
                .load(data.getPictureUrl())
                .placeholder(R.drawable.ic_game_pic_default_02)
                .error(R.drawable.ic_game_pic_default_02)
                .fitCenter()
                .into(imageView);
        imageView.setTag(R.id.item_data_key, data);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    AdvertisingInfo info = ((AdvertisingInfo)v.getTag(R.id.item_data_key));
//                    JumpManager.getInstance().handle(info);

            }
        });
    }
}
