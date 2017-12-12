package com.hhly.lyygame.presentation.view.search;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.protocol.game.GameByModelIdResp;

import java.util.ArrayList;

/**
 * 搜索热门
 * Created by Simon on 2016/12/3.
 */

public class SearchHotAdapter extends BaseQuickAdapter<GameByModelIdResp.GameByModeIdPage.GameByModeIdInfo, BaseViewHolder> {

    public SearchHotAdapter() {
        super(R.layout.widget_search_hot_item_layout, new ArrayList<GameByModelIdResp.GameByModeIdPage.GameByModeIdInfo>());
    }
    
    @Override
    protected void convert(BaseViewHolder baseViewHolder, GameByModelIdResp.GameByModeIdPage.GameByModeIdInfo gameInfo) {
        int position = baseViewHolder.getAdapterPosition();
        if (position == 0) {
            baseViewHolder.setBackgroundRes(R.id.item_rank_tv, R.drawable.bg_search_rank_01);
        } else if (position == 1) {
            baseViewHolder.setBackgroundRes(R.id.item_rank_tv, R.drawable.bg_search_rank_02);
        } else if (position == 2) {
            baseViewHolder.setBackgroundRes(R.id.item_rank_tv, R.drawable.bg_search_rank_03);
        } else {
            baseViewHolder.setBackgroundRes(R.id.item_rank_tv, R.drawable.bg_search_rank_04);
        }
        baseViewHolder.setText(R.id.item_rank_tv, String.valueOf(position + 1));
        baseViewHolder.setText(R.id.item_content_tv, gameInfo.getName());
    }

}
