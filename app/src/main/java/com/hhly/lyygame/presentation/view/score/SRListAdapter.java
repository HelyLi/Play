package com.hhly.lyygame.presentation.view.score;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.protocol.user.AccountScorePager;
import com.hhly.lyygame.presentation.utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Simon on 2016/11/30.
 */

public class SRListAdapter extends BaseQuickAdapter<AccountScorePager.AccountScoreBean, BaseViewHolder> {

    public SRListAdapter() {
        super(R.layout.fragment_score_record_item_layout, new ArrayList<AccountScorePager.AccountScoreBean>());
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, AccountScorePager.AccountScoreBean bean) {

        baseViewHolder.setText(R.id.name_tv, bean.getRemark());
        baseViewHolder.setText(R.id.date_tv, DateUtils.formatDate(new Date(bean.getRecordTime())));
        baseViewHolder.setText(R.id.score_tv, String.valueOf(bean.getPoint()));
        baseViewHolder.setText(R.id.score_symbol_tv, bean.getTradeType() == 0 ? "+" : "-");

    }

}
