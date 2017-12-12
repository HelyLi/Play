package com.hhly.lyygame.presentation.view.pay.bankpay;

import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.protocol.pay.PayBankInfoResp;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import java.util.ArrayList;
import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/5/13
 */

public class SupportBankAdapter extends BaseQuickAdapter<PayBankInfoResp, BaseViewHolder> {

    public SupportBankAdapter(List<PayBankInfoResp> data) {
        super(R.layout.support_bank_item, data != null ? data : new ArrayList<PayBankInfoResp>());
    }

    @Override
    protected void convert(BaseViewHolder helper, PayBankInfoResp item) {
        helper.setText(R.id.bank_name, item.getBankName())
                .setText(R.id.bank_desc, item.getLimitation());
        Glide.with(mContext).load(item.getMobileBankImgUrl())
                .centerCrop()
                //                .bitmapTransform(new RoundedCornersTransformation(mContext, mContext.getResources().getDimensionPixelSize(R.dimen.round_radius), 0, RoundedCornersTransformation.CornerType.ALL))
                //Ui觉得不需要默认图
//                .placeholder(R.drawable.ic_contact_service)
//                .error(R.drawable.ic_contact_service)
                .into((ImageView) helper.getView(R.id.bank_img));
    }
}
