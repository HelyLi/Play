package com.hhly.lyygame.presentation.view.address;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.github.freeman0211.library.address.CityItem;
import com.github.freeman0211.library.address.DistrictItem;
import com.github.freeman0211.library.address.ProvinceItem;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.db.manager.AccountManager;
import com.hhly.lyygame.data.net.protocol.user.AddAddressReq;
import com.hhly.lyygame.data.net.protocol.user.UserAddressResp;
import com.hhly.lyygame.presentation.utils.RegexUtils;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.DialogFactory;
import com.hhly.lyygame.presentation.view.ToastUtil;
import com.hhly.lyygame.presentation.view.immersive.IImmersiveApply;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 地址编辑
 * Created by Simon on 2016/11/28.
 */

public class AddressEditFragment extends BaseFragment implements IImmersiveApply, AddressPickupDialog.OnSelectedListener, AddressEditContract.View {

    public static final String ADDRESS_EDIT = "address_edit";

    private static final String TAG = "AddressEditFragment";
    @BindView(R.id.name_et)
    EditText mNameEt;
    @BindView(R.id.name_clear_iv)
    ImageView mNameClearIv;
    @BindView(R.id.phone_et)
    EditText mPhoneEt;
    @BindView(R.id.phone_clear_iv)
    ImageView mPhoneClearIv;
    @BindView(R.id.street_tv)
    Button mStreetTv;
    @BindView(R.id.detail_et)
    EditText mDetailEt;
    @BindView(R.id.detail_clear_iv)
    ImageView mDetailClearIv;
    @BindView(R.id.address_default_cb)
    AppCompatCheckBox mAddressDefaultCb;
    @BindView(R.id.save_btn)
    Button mSaveBtn;

    public static AddressEditFragment newInstance(UserAddressResp.AddressBean addressBean) {
        AddressEditFragment fragment = new AddressEditFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(ADDRESS_EDIT, addressBean);
        fragment.setArguments(bundle);

        return fragment;
    }

    private AddressEditContract.Presenter mPresenter;
    private UserAddressResp.AddressBean mAddressBean;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_address_edit_layout;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAddressBean = getArguments().getParcelable(ADDRESS_EDIT);

    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        if (mAddressBean != null) {

            if (mToolbarHelper != null) {
                mToolbarHelper.setTitle(getString(R.string.lyy_address_modify));
            }

            mNameEt.setText(mAddressBean.getName());
            mPhoneEt.setText(mAddressBean.getTel());

            province = mAddressBean.getProvince();
            city = mAddressBean.getCity();
            district = mAddressBean.getStreet();

            mStreetTv.setText(mAddressBean.getProvince()
                    + mAddressBean.getCity()
                    + (("null".equalsIgnoreCase(mAddressBean.getStreet()) || mAddressBean.getStreet() == null || "其他".equalsIgnoreCase(mAddressBean.getStreet())) ? "" : mAddressBean.getStreet()));
            mDetailEt.setText(mAddressBean.getDetail());
            mAddressDefaultCb.setChecked(mAddressBean.getLast() == 0);
        }

        mDetailEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mDetailEt.getText().toString().trim().length() == 100) {
                    ToastUtil.showTip(getActivity(), R.string.lyy_game_address_length);
                }
            }
        });
    }

    //此处应该处理
    @OnClick(R.id.street_tv)
    void onStreetClick(View view) {
        Logger.d("onStreetClick");
        DialogFactory.createAddressPickup(this).show(getChildFragmentManager(), "AddressPickup");

    }

    @OnClick(R.id.save_btn)
    void onSaveClick() {

        String nameEt = mNameEt.getText().toString().trim();
        String phoneEt = mPhoneEt.getText().toString().trim();
        String detailEt = mDetailEt.getText().toString().trim();

        //        if (TextUtils.isEmpty(nameEt) ||
        //                TextUtils.isEmpty(phoneEt) ||
        //                TextUtils.isEmpty(mStreetTv.getText().toString().trim()) ||
        //                TextUtils.isEmpty(detailEt)) {
        //            ToastUtil.showTip(getActivity(), R.string.lyy_game_address_edit_hint_address);
        //            return;
        //        }
        //检查收货人信息  是否为空
        if (TextUtils.isEmpty(nameEt)) {
            ToastUtil.showTip(getActivity(), R.string.lyy_game_address_edit_hint_info);
            return;
        }
        //检查手机号是否正确
        if (TextUtils.isEmpty(phoneEt) || !RegexUtils.checkMobile(phoneEt)) {
            ToastUtil.showTip(getActivity(), R.string.lyy_game_address_edit_hint_phone);
            return;
        }
        //检查收货地址是否有填写
        if (TextUtils.isEmpty(mStreetTv.getText().toString().trim())
                || mStreetTv.getText().toString().equalsIgnoreCase(getResources().getString(R.string.lyy_address_hint_street))
                || TextUtils.isEmpty(detailEt)) {
            ToastUtil.showTip(getActivity(), R.string.lyy_game_address_edit_hint_address);
            return;
        }

        Logger.d("onSaveClick");
        AddAddressReq req = new AddAddressReq();
        req.setCountry(0);
        req.setProvince(province);
        req.setCity(city);
        req.setStreet(district);
        req.setUserId(AccountManager.getInstance().getUserId());
        req.setName(nameEt);
        req.setUseDefault(mAddressDefaultCb.isChecked() ? 0 : 1);
        req.setTel(phoneEt);
        req.setDetail(detailEt);
        if (mAddressBean != null) {
            req.setAddressId(mAddressBean.getId());
        }

        mPresenter.addUserAddress(req);
        mSaveBtn.setClickable(false);

    }

    @Override
    public boolean applyImmersive() {
        return true;
    }

    @Override
    public boolean applyScroll() {
        return false;
    }

    @Override
    public float initAlpha() {
        return 1.0f;
    }

    private String province;
    private String city;
    private String district;

    @Override
    public void onSelected(ProvinceItem pItem, CityItem cItem, DistrictItem dItem, String address) {
        Logger.d(String.format("ProvinceItem: %s\nCityItem: %s,\nDistrictItem: %s\nAddress: %s", pItem, cItem, dItem, address));
        mStreetTv.setText(address);
        province = pItem.getName();
        city = cItem.getName();
        district = dItem.getName();
    }

    @Override
    public void addAddressSuccess(int addressId) {
        getActivity().setResult(Activity.RESULT_OK);
        onBackPressed();
    }

    @Override
    public void addAddressFailure() {
        mSaveBtn.post(new Runnable() {
            @Override
            public void run() {
                mSaveBtn.setClickable(true);
            }
        });
    }

    @Override
    public void setPresenter(AddressEditContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    protected void fetchData(boolean isLoadMore) {

    }
}
