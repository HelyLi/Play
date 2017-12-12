package com.github.freeman0211.library.address;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.ArrayWheelAdapter;

/**
 * Created by freeman on 16/3/2.
 */
public class AddrPickerFragment extends Fragment implements OnWheelChangedListener, LoaderManager.LoaderCallbacks<RootItem> {

    public static final int VISIBLE_COUNT = 3;
    private WheelView mProvinceView;
    private WheelView mCityView;
    private WheelView mDistrictView;
    private View mRootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.addr_picker_layout, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getLoaderManager().initLoader(0, null, this);
    }

    private void initView (View view) {
        mProvinceView = (WheelView) view.findViewById(R.id.addr_picker_provice_view);
        mProvinceView.setVisibleItems(VISIBLE_COUNT);
        mCityView = (WheelView) view.findViewById(R.id.addr_picker_city_view);
        mCityView.setVisibleItems(VISIBLE_COUNT);
        mDistrictView = (WheelView) view.findViewById(R.id.addr_picker_district_view);
        mDistrictView.setVisibleItems(VISIBLE_COUNT);

        mProvinceView.addChangingListener(this);
        mCityView.addChangingListener(this);
        mDistrictView.addChangingListener(this);

        mRootView = view.findViewById(R.id.addr_picker_root);

        mProvinceView.setViewAdapter(new ArrayWheelAdapter<String>(getActivity(), new String[]{""}, R.layout.addr_item_layout));

    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        if (wheel.equals(mProvinceView)) {
            updateCity();
        } else if (wheel.equals(mCityView)) {
            updateDistrict();
        } else if (wheel.equals(mDistrictView)) {

        }
    }

    private void updateCity () {
        int pIndex = mProvinceView.getCurrentItem();
        mCityView.setViewAdapter(new ArrayWheelAdapter<CityItem>(getActivity(), JsonParse.convertCityToArray(JsonParse.getCityList(pIndex))));
        mCityView.setCurrentItem(0);
        updateDistrict();
    }

    private void updateDistrict () {
        int cIndex = mCityView.getCurrentItem();
        int pIndex = mProvinceView.getCurrentItem();
        mDistrictView.setViewAdapter(new ArrayWheelAdapter<DistrictItem>(getActivity(), JsonParse.convertDistrictToArray(JsonParse.getDistrictList(pIndex, cIndex))));
        mDistrictView.setCurrentItem(0);
    }

    @Override
    public Loader<RootItem> onCreateLoader(int id, Bundle args) {
        return new ParseLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<RootItem> loader, RootItem data) {
        mProvinceView.setViewAdapter(new ArrayWheelAdapter<ProvinceItem>(getActivity(), JsonParse.convertProvinceToArray(data.getProvince()), R.layout.addr_item_layout));
        mProvinceView.setCurrentItem(0);
        updateCity();
        updateDistrict();
    }

    @Override
    public void onLoaderReset(Loader<RootItem> loader) {

    }

    public String getAddress () {
        int pIndex = mProvinceView.getCurrentItem();
        int cIndex = mCityView.getCurrentItem();
        int dIndex = mDistrictView.getCurrentItem();
        ProvinceItem provinceItem = JsonParse.getProvince(pIndex);
        CityItem cityItem = JsonParse.getCity(provinceItem, cIndex);
        DistrictItem districtItem = JsonParse.getDistrict(cityItem, dIndex);
        if (cityItem.getName().equals(provinceItem.getName())) {
            return provinceItem + (districtItem.getName().equals(getString(R.string.addr_other)) ? "" : districtItem.getName());
        }
        return provinceItem.getName() + cityItem.getName() + (districtItem.getName().equals(getString(R.string.addr_other)) ? "" : districtItem.getName());
    }

    public ProvinceItem getProvince () {
        int pIndex = mProvinceView.getCurrentItem();
        return JsonParse.getProvince(pIndex);
    }

    public CityItem getCity () {
        int cIndex = mCityView.getCurrentItem();
        return JsonParse.getCity(getProvince(), cIndex);
    }

    public DistrictItem getDistrict () {
        int dIndex = mDistrictView.getCurrentItem();
        return JsonParse.getDistrict(getCity(), dIndex);
    }

    public void setBackground(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mRootView.setBackground(drawable);
        } else {
            mRootView.setBackgroundDrawable(drawable);
        }
    }

}
