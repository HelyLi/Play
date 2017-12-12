package com.hhly.lyygame.presentation.view.pay.bankpay;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhly.lyygame.R;

import java.util.ArrayList;

/**
 * 证件类型选择adapter
 * Created by dell on 2017/5/15.
 */

public class VoucherTypeAdapter extends BaseQuickAdapter<VoucherTypeAdapter.VoucherTypeItem, BaseViewHolder> {

    public VoucherTypeAdapter() {
        super(R.layout.voucher_type_item_layout, new ArrayList<VoucherTypeItem>());
    }

    @Override
    protected void convert(BaseViewHolder helper, VoucherTypeItem item) {
        helper.setText(R.id.voucher_type_tv, item.getVoucherTypeName());
    }

    public static class VoucherTypeItem {
        private String typeId;
        private String mVoucherTypeName;

        public VoucherTypeItem(String voucherTypeName,String typeId) {
            this.mVoucherTypeName = voucherTypeName;
            this.typeId=typeId;
        }

        public String getVoucherTypeName() {
            return mVoucherTypeName;
        }

        public String getTypeId() {
            return typeId;
        }
    }

    public void updateItems(Context context) {
        addData(new VoucherTypeItem(context.getString(R.string.lyy_pay_identity_card),"01"));
        addData(new VoucherTypeItem(context.getString(R.string.lyy_pay_certificate_of_officers),"02"));
        addData(new VoucherTypeItem(context.getString(R.string.lyy_pay_protection),"03"));
        addData(new VoucherTypeItem(context.getString(R.string.lyy_pay_reentry_permit),"04"));
        addData(new VoucherTypeItem(context.getString(R.string.lyy_pay_tai_wan_card),"05"));
        addData(new VoucherTypeItem(context.getString(R.string.lyy_pay_police_id_card),"06"));
        addData(new VoucherTypeItem(context.getString(R.string.lyy_pay_soldiers_card),"07"));
        addData(new VoucherTypeItem(context.getString(R.string.lyy_pay_others_card),"99"));
    }
}
