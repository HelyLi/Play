package com.hhly.lyygame.presentation.view.paylist;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.protocol.user.AccountCoinsPager;

import java.util.ArrayList;


/**
 * Created by ${HELY} on 17/1/18.
 * 邮箱：heli.lixiong@gmail.com
 */

public class PayListAdapter extends BaseQuickAdapter<AccountCoinsPager.AccountCoinsBean, BaseViewHolder> {
    private int  category=1;
    public PayListAdapter(int category) {
        super(R.layout.pay_list_item_layout, new ArrayList<AccountCoinsPager.AccountCoinsBean>());
        this.category=category;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, AccountCoinsPager.AccountCoinsBean bean) {
        baseViewHolder.setText(R.id.item_record_tv, bean.getNAME());
        baseViewHolder.setText(R.id.item_time_tv, bean.getRECORDTIME());
        baseViewHolder.setText(R.id.item_coins_tv, getPrefix() + getRechange(bean));
    }

    private String getPrefix(){
        return category== PayListFragment.RECHARGE_CATEGORY?"+":"-";
    }

    private String getRechange(AccountCoinsPager.AccountCoinsBean bean){
        return category== PayListFragment.RECHARGE_CATEGORY?String.valueOf(bean.getAPPLYAMOUNT()):String.valueOf(bean.getLYB());
    }

}
