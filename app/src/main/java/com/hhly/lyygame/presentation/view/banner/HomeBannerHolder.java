package com.hhly.lyygame.presentation.view.banner;

import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.protocol.banner.HomeBannerResp;
import com.hhly.lyygame.presentation.view.share.ShareContent;
import com.hhly.lyygame.presentation.view.shareactivity.ShareWebActivity;
import com.hhly.lyygame.presentation.view.shareactivity.ShareWebBuilder;

/**
 * Created by Simon on 2016/12/7.
 */

public class HomeBannerHolder implements Holder<HomeBannerResp.Pager.BannerInfo> {

    private ImageView imageView;
    
    @Override
    public View createView(Context context) {
        imageView = new ImageView(context);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(final Context context, final int position, final HomeBannerResp.Pager.BannerInfo data) {

        Glide.with(context)
                    .load(data.getPictureUrl())
                    .placeholder(R.drawable.ic_game_pic_default_02)
                    .error(R.drawable.ic_game_pic_default_02)
                    .centerCrop()
                    .into(imageView);

        imageView.setTag(R.id.item_data_key, data);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ShareContent shareContent = new ShareContent();
                if (ShareWebBuilder.tourUrl.equals(data.getJumpUrl())){

                    shareContent.setWebTitle("泰国游活动");
                    shareContent.setWebUrl(ShareWebBuilder.tourUrl);
                    shareContent.setAppName(context.getString(R.string.app_name));
                    shareContent.setImage("http://public.13322.com/23c192a0.png");
                    shareContent.setLink(ShareWebBuilder.shareUrl);
                    shareContent.setDescription("邀请朋友抽取玩乐大礼包，100%中奖！更有免费泰国游豪华六日游！");
                    shareContent.setContent("邀请朋友抽取玩乐大礼包，100%中奖！更有免费泰国游豪华六日游！");
                    shareContent.setTitle("免费体验泰国豪华六日游啦！");
                    shareContent.setShowShare(false);
                }else {
                    shareContent.setWebTitle(data.getRemarks());
                    shareContent.setWebUrl(data.getJumpUrl());
                    shareContent.setAppName(context.getString(R.string.app_name));
                    shareContent.setImage("http://public.13322.com/23c192a0.png");
                    shareContent.setLink(data.getJumpUrl());
                    shareContent.setContent(data.getRemarks());
                    shareContent.setTitle(context.getString(R.string.lyy_wyx));
                    shareContent.setShowShare(true);


                    if (data.getJumpUrl() == null || data.getJumpUrl().length() < 4){
                        return;
                    }
                }

                ActivityCompat.startActivity(context, ShareWebActivity.getCallingIntent(context, shareContent), null);
            }
        });
    }
}
