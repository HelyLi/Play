package com.github.freeman0211.library.address;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

/**
 * Created by freeman on 16/3/2.
 */
public class AddrPickerDialog extends DialogFragment {

    public interface OnSelectedListener {
        void onSelected (ProvinceItem pItem, CityItem cItem, DistrictItem dItem, String address);
    }

    private AddrPickerFragment mFragment;

    private OnSelectedListener mSelectedListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_addr_picker_layout, container, false);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        mFragment = new AddrPickerFragment();
        transaction.replace(R.id.addr_picker_root, mFragment);
        transaction.commit();

        Button cancelBtn = (Button) view.findViewById(R.id.addr_picker_btn_1);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        Button confirmBtn = (Button) view.findViewById(R.id.addr_picker_btn_2);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSelectedListener != null) {
                    mSelectedListener.onSelected(mFragment.getProvince(), mFragment.getCity(), mFragment.getDistrict(), mFragment.getAddress());
                }
                dismiss();
            }
        });

        return view;
    }

    public void setSelectedListener(OnSelectedListener selectedListener) {
        mSelectedListener = selectedListener;
    }
}
