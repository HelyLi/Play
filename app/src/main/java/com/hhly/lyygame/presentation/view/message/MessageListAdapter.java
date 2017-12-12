package com.hhly.lyygame.presentation.view.message;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.protocol.user.MsgPager;
import com.hhly.lyygame.presentation.utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;

/**
 * 我的消息
 * Created by Simon on 2016/12/6.
 */

public class MessageListAdapter extends BaseQuickAdapter<MsgPager.MsgBean, BaseViewHolder> {

    public MessageListAdapter() {
        super(R.layout.fragment_message_list_item_layout, new ArrayList<MsgPager.MsgBean>());
    }

    @Override
    protected void convert(BaseViewHolder holder, MsgPager.MsgBean msgBean) {
        if (msgBean.getImage() != null)
            Glide.with(holder.itemView.getContext()).load(msgBean.getImage()).asBitmap()
                    .placeholder(R.mipmap.ic_launcher)
                    .centerCrop().into((ImageView) holder.getView(R.id.item_avatar_iv));

        boolean unread = msgBean.getReadStatus() != 1;//未读

        holder.setText(R.id.item_title_tv, msgBean.getTitle());
        holder.setTextColor(R.id.item_title_tv, unread ? holder.itemView.getContext().getResources().getColor(R.color.color_bbbbbb): holder.itemView.getContext().getResources().getColor(R.color.color_333333));

        holder.setText(R.id.item_content_tv, msgBean.getSynopsis());
        holder.setTextColor(R.id.item_content_tv, unread ? holder.itemView.getContext().getResources().getColor(R.color.color_bbbbbb): holder.itemView.getContext().getResources().getColor(R.color.color_666666));

        holder.setText(R.id.item_date_tv, DateUtils.getTimeInterval(new Date(msgBean.getTime())));
        holder.setTextColor(R.id.item_content_tv, unread ? holder.itemView.getContext().getResources().getColor(R.color.color_bbbbbb): holder.itemView.getContext().getResources().getColor(R.color.color_666666));

        holder.setVisible(R.id.item_state_rl, msgBean.getReadStatus() == 1);

        holder.setBackgroundColor(R.id.rowFG, unread ? holder.itemView.getContext().getResources().getColor(R.color.color_fff) : holder.itemView.getContext().getResources().getColor(R.color.color_f9f9f9));

    }

}
