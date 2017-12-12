package com.hhly.lyygame.presentation.view.gameservice;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.protocol.game.GameAreaResp;
import com.hhly.lyygame.presentation.utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ${HELY} on 17/1/18.
 * 邮箱：heli.lixiong@gmail.com
 */

public class GameServiceAdapter extends BaseQuickAdapter<GameAreaResp.ListGameServerAreaBean ,BaseViewHolder>{

    public GameServiceAdapter() {
        super(R.layout.game_service_item_layout, new ArrayList<GameAreaResp.ListGameServerAreaBean>());
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, GameAreaResp.ListGameServerAreaBean info) {

        baseViewHolder.setText(R.id.game_service_name_tv, info.getPlatformName());
        baseViewHolder.setText(R.id.game_service_state_tv, info.getServerNum() + "-" + info.getAreaName());
        baseViewHolder.setText(R.id.game_service_time_tv, DateUtils.formatDate(new Date(info.getUpdateTime())));

    }


}
