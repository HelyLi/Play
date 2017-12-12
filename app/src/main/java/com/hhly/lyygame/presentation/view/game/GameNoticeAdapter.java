package com.hhly.lyygame.presentation.view.game;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.protocol.game.GameNoticeResp;
import com.hhly.lyygame.presentation.utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;

/**
 * 游戏公告
 * Created by Simon on 2016/12/2.
 */

public class GameNoticeAdapter extends BaseQuickAdapter<GameNoticeResp.GameNoticePage.GameNotice, BaseViewHolder> {

    public GameNoticeAdapter() {
        super(R.layout.fragment_game_notice_item_layout, new ArrayList<GameNoticeResp.GameNoticePage.GameNotice>());
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, GameNoticeResp.GameNoticePage.GameNotice gameNotice) {

        baseViewHolder.setText(R.id.notice_date_tv, DateUtils.formatDate(new Date(gameNotice.getCreatorTime())));
        baseViewHolder.setText(R.id.notice_source_tv, gameNotice.getPlatformName());
        baseViewHolder.setText(R.id.notice_title_tv, gameNotice.getTitle());
//        baseViewHolder.setText(R.id.notice_subtitle_tv, gameNotice.getRemarks());

        Glide.with(baseViewHolder.itemView.getContext()).load(gameNotice.getImgUrl())
                .centerCrop()
                .placeholder(R.drawable.ic_game_pic_default_01)
                .error(R.drawable.ic_game_pic_default_01)
                .into((ImageView) baseViewHolder.getView(R.id.notice_pic_iv));

    }
}
