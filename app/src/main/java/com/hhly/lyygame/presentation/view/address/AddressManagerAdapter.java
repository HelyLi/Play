package com.hhly.lyygame.presentation.view.address;

import android.support.v7.widget.AppCompatCheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.protocol.user.UserAddressResp;

import java.util.ArrayList;

/**
 * 地址管理
 * Created by Simon on 2016/11/29.
 */

public class AddressManagerAdapter extends BaseQuickAdapter<UserAddressResp.AddressBean, BaseViewHolder> {

    public AddressManagerAdapter() {
        super(R.layout.fragment_address_item_layout, new ArrayList<UserAddressResp.AddressBean>());
    }

    //    private boolean onBind;

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, final UserAddressResp.AddressBean addressBean) {

        //        name_tv
        baseViewHolder.setText(R.id.name_tv, addressBean.getName());
        baseViewHolder.setText(R.id.phone_tv, addressBean.getTel());
        baseViewHolder.setText(R.id.detail_tv, addressBean.getProvince() + addressBean.getCity()
                + (("null".equalsIgnoreCase(addressBean.getStreet()) || addressBean.getStreet() == null || "其他".equalsIgnoreCase(addressBean.getStreet())) ? "" : addressBean.getStreet())
                + " " + addressBean.getDetail());

        //默认地址
        final AppCompatCheckBox checkBox = (AppCompatCheckBox) baseViewHolder.itemView.findViewById(R.id.address_default_cb);
        //        onBind = true;
        checkBox.setChecked(addressBean.getLast() == 0 ? true : false);
        //        onBind = true;

        baseViewHolder.addOnClickListener(R.id.address_default_cb);
        baseViewHolder.addOnClickListener(R.id.delete_btn);
        baseViewHolder.addOnClickListener(R.id.edit_btn);


        //        baseViewHolder.setOnCheckedChangeListener(R.id.address_default_cb, new CompoundButton.OnCheckedChangeListener() {
        //            @Override
        //            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        //
        //                Logger.d("isChecked=" + isChecked);
        //
        //                //不需要处理
        //                if (isChecked && addressBean.getLast() == 0){
        //                    return;
        //                }
        //                if (!isChecked && addressBean.getLast() == 1){
        //                    return;
        //                }
        //
        //                addressBean.setLast(isChecked ? 0 : 1);
        //                buttonView.setChecked(isChecked);
        //
        //                if (mAddressItemSelectListener != null) {
        //                    mAddressItemSelectListener.modifyDefaultAddress(addressBean);
        //                }
        //
        //            }
        //        });

        //        Button delete = (Button) baseViewHolder.itemView.findViewById(R.id.delete_btn);
        //        delete.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //                if (mAddressItemSelectListener != null) {
        //                    mAddressItemSelectListener.delAddress(addressBean);
        //                }
        //            }
        //        });

        //        Button edit = (Button) baseViewHolder.itemView.findViewById(R.id.edit_btn);
        //        edit.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //                if (mAddressItemSelectListener != null) {
        //                    mAddressItemSelectListener.editAddress(addressBean);
        //                }
        //            }
        //        });

    }

    //    private AddressItemSelectListener mAddressItemSelectListener;

    //    public void setAddressItemSelectListener(AddressItemSelectListener listener) {
    //        mAddressItemSelectListener = listener;
    //    }

    //    interface AddressItemSelectListener {
    //
    //        void delAddress(UserAddressResp.AddressBean addressBean);
    //
    //        void editAddress(UserAddressResp.AddressBean addressBean);
    //
    //        void modifyDefaultAddress(UserAddressResp.AddressBean addressBean);
    //    }

}
