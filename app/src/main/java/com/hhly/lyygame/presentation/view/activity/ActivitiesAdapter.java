package com.hhly.lyygame.presentation.view.activity;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.protocol.banner.GoodsBannerResp;
import com.hhly.lyygame.data.net.protocol.game.GameNoticeResp;
import com.hhly.lyygame.data.net.protocol.game.IndexActivityByModelIdResp;
import com.hhly.lyygame.presentation.utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;

/**
 * 活动中心适配器
 * Created by Simon on 2016/11/29.
 */

public class ActivitiesAdapter extends BaseQuickAdapter<Object, BaseViewHolder> {

    public ActivitiesAdapter() {
        super(R.layout.fragment_activities_item_layout, new ArrayList<Object>());
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Object activityInfo) {

        if (activityInfo instanceof IndexActivityByModelIdResp.ActivityPage.ActivityInfo) {
            IndexActivityByModelIdResp.ActivityPage.ActivityInfo info = (IndexActivityByModelIdResp.ActivityPage.ActivityInfo) activityInfo;

            baseViewHolder.setImageResource(R.id.activity_status_iv, R.drawable.ic_activity_status_active);

            baseViewHolder.setText(R.id.activity_name_tv, info.getREMARK());
            baseViewHolder.setText(R.id.activity_date_tv, info.getCreateTime());

            Glide.with(baseViewHolder.itemView.getContext()).load(info.getImageUrl())
                    .placeholder(R.drawable.ic_game_pic_default_02)
                    .error(R.drawable.ic_game_pic_default_02)
                    .into((ImageView) baseViewHolder.getView(R.id.activity_iv));

        } else if (activityInfo instanceof GoodsBannerResp.GoodsBanner) {

            GoodsBannerResp.GoodsBanner info = (GoodsBannerResp.GoodsBanner) activityInfo;

            baseViewHolder.setImageResource(R.id.activity_status_iv, R.drawable.ic_activity_status_active);
//            }
            baseViewHolder.setText(R.id.activity_name_tv, info.getDetail());
            baseViewHolder.setText(R.id.activity_date_tv, DateUtils.format(new Date(info.getUpdateTime())));

            Glide.with(baseViewHolder.itemView.getContext()).load(info.getImageUrl())
                    .placeholder(R.drawable.ic_game_pic_default_02)
                    .error(R.drawable.ic_game_pic_default_02)
                    .into((ImageView) baseViewHolder.getView(R.id.activity_iv));

        }else if (activityInfo instanceof GameNoticeResp.GameNoticePage.GameNotice){

            GameNoticeResp.GameNoticePage.GameNotice info = (GameNoticeResp.GameNoticePage.GameNotice) activityInfo;

            baseViewHolder.setImageResource(R.id.activity_status_iv, R.drawable.ic_activity_status_active);
            baseViewHolder.setText(R.id.activity_name_tv, info.getTitle());
            baseViewHolder.setText(R.id.activity_date_tv, DateUtils.format(new Date(info.getCreatorTime())));

            Glide.with(baseViewHolder.itemView.getContext()).load(info.getImgUrl())
                    .placeholder(R.drawable.ic_game_pic_default_02)
                    .error(R.drawable.ic_game_pic_default_02)
                    .into((ImageView) baseViewHolder.getView(R.id.activity_iv));

        }
    }




}
