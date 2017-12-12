package com.hhly.lyygame.presentation.view.notification;

import android.util.Log;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.SectionEntity;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.protocol.user.MsgPager;
import com.hhly.lyygame.data.net.protocol.user.NotificationActivityResp;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

/**
 * 提示中心
 * Created by Simon on 2016/11/30.
 */

public class NotificationAdapter extends BaseSectionQuickAdapter<SectionEntity, BaseViewHolder> {
    public NotificationAdapter() {
        super(R.layout.notification_item_layout, R.layout.fragment_notification_section_layout, new ArrayList<SectionEntity>());
    }

    @Override
    protected void convertHead(BaseViewHolder baseViewHolder, SectionEntity sectionEntity) {
        NotificationSection section = (NotificationSection) sectionEntity;
        baseViewHolder.setImageResource(R.id.notification_section_iv, section.getIconResId());
        baseViewHolder.setText(R.id.notification_title_tv, section.header);
        int position = baseViewHolder.getAdapterPosition();
        if (position < getItemCount() - 1) {
            if (getItem(position + 1).isHeader) {
                baseViewHolder.setBackgroundRes(R.id.notification_section_root, R.drawable.bg_notification_with_all);
            } else {
                baseViewHolder.setBackgroundRes(R.id.notification_section_root, R.drawable.bg_notification_with_top);
            }
        } else {
            baseViewHolder.setBackgroundRes(R.id.notification_section_root, R.drawable.bg_notification_with_all);
        }
        if (section.getUnreadCount()>0) {
            baseViewHolder.setVisible(R.id.notification_count_tv, true);
            if (section.getUnreadCount()>99){
                baseViewHolder.setText(R.id.notification_count_tv,"99+");
            }else{
                baseViewHolder.setText(R.id.notification_count_tv,String.valueOf(section.getUnreadCount()));
            }
        }else{
            baseViewHolder.setVisible(R.id.notification_count_tv, false);
        }
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, SectionEntity sectionEntity) {
        int position = baseViewHolder.getAdapterPosition();
        if (position < getItemCount() - 1) {
            if (getItem(position + 1).isHeader) {
                baseViewHolder.setBackgroundRes(R.id.notification_item_root, R.drawable.bg_notification_with_bottom);
            }
        } else {
            baseViewHolder.setBackgroundRes(R.id.notification_item_root, R.drawable.bg_notification_with_bottom);
        }

        if (sectionEntity instanceof ActivityItem) {
            NotificationActivityResp.ActivityBean bean = (NotificationActivityResp.ActivityBean) sectionEntity.t;
            baseViewHolder.setText(R.id.notification_item_name_tv, bean.getTitle());
            baseViewHolder.setText(R.id.notification_item_description_tv, bean.getDescription());

        } else if (sectionEntity instanceof MsgItem) {
            MsgPager.MsgBean bean = (MsgPager.MsgBean) sectionEntity.t;
            baseViewHolder.setText(R.id.notification_item_name_tv, bean.getTitle());
            baseViewHolder.setText(R.id.notification_item_description_tv, bean.getSynopsis());
        }

    }
}
