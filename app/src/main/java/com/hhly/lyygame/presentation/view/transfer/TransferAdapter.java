package com.hhly.lyygame.presentation.view.transfer;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.db.manager.AccountManager;
import com.hhly.lyygame.data.net.protocol.transfer.TransferGameListResp;

import java.util.ArrayList;

/**
 * Created by ${HELY} on 17/4/24.
 * 邮箱：heli.lixiong@gmail.com
 */

public class TransferAdapter extends BaseQuickAdapter<TransferGameListResp.TransferGameInfo, BaseViewHolder> {

    public TransferAdapter() {
        super(R.layout.lyy_game_transfer_item, new ArrayList<TransferGameListResp.TransferGameInfo>());
    }

    @Override
    protected void convert(BaseViewHolder helper, TransferGameListResp.TransferGameInfo item) {

        if (item.getId() == 1){
            helper.setVisible(R.id.item_balance_ll, true);
            helper.setText(R.id.item_balance_tv, AccountManager.getInstance().getUserInfo().getLyb());
        }

        helper.setText(R.id.item_tv, item.getName());
    }
}
