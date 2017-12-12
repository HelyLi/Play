package com.hhly.lyygame.presentation.view.address;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.github.freeman0211.library.address.AddrPickerFragment;
import com.github.freeman0211.library.address.CityItem;
import com.github.freeman0211.library.address.DistrictItem;
import com.github.freeman0211.library.address.ProvinceItem;
import com.hhly.lyygame.R;

/**
 * Created by Simon on 2016/11/28.
 */

public class AddressPickupDialog extends BottomSheetDialogFragment {

    private AddrPickerFragment mFragment;

    public interface OnSelectedListener {
        void onSelected (ProvinceItem pItem, CityItem cItem, DistrictItem dItem, String address);
    }

    private OnSelectedListener mSelectedListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        View view = inflater.inflate(R.layout.fragment_address_pickup_layout, container, false);
        view.findViewById(R.id.finish_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSelectedListener != null) {
                    mSelectedListener.onSelected(mFragment.getProvince(), mFragment.getCity(), mFragment.getDistrict(), mFragment.getAddress());
                }
                dismissAllowingStateLoss();
            }
        });
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        mFragment = new AddrPickerFragment();
        transaction.replace(R.id.content_container, mFragment);
        transaction.commit();
        return view;
    }

    public void setSelectedListener(OnSelectedListener selectedListener) {
        mSelectedListener = selectedListener;
    }
}
