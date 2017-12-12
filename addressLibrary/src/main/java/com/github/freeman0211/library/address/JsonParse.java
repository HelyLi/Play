package com.github.freeman0211.library.address;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by freeman on 16/3/2.
 */
public final class JsonParse {

    private static RootItem sRootItem = null;

    public static RootItem parse (Context context, OnParseListener parseListener) {
        if (sRootItem != null) {
            return sRootItem;
        }
        InputStream is = null;
        InputStreamReader reader = null;
        try {
            is = context.getAssets().open("province_city_data.json", AssetManager.ACCESS_STREAMING);
            reader = new InputStreamReader(is);
            char[] buff = new char[1024];
            StringBuilder sb = new StringBuilder();
            int result = 0;
            while ((result = reader.read(buff)) != -1) {
                sb.append(buff, 0, result);
            }
            String content = sb.toString();
            Gson gson = new Gson();
            sRootItem = gson.fromJson(content, RootItem.class);
        } catch (IOException e) {
            if (parseListener != null) {
                parseListener.onFailure(e.toString());
            }
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                    reader = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                    is = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (parseListener != null) {
            parseListener.onComplete();
        }

        return sRootItem;
    }

    public static List<ProvinceItem> getProvinceList () {
        if (sRootItem == null) {
            throw new IllegalStateException("Please parse first");
        }
        return sRootItem.getProvince();
    }

    public static List<CityItem> getCityList (int position) {
        return getProvinceList().get(position).getCity();
    }

    public static List<DistrictItem> getDistrictList (int provincePos, int cityPos) {
        return getCityList(provincePos).get(cityPos).getDistrict();
    }

    public static ProvinceItem getProvince (int position) {
        return getProvinceList().get(position);
    }

    public static CityItem getCity (ProvinceItem provinceItem, int position) {
        return provinceItem.getCity().get(position);
    }

    public static DistrictItem getDistrict (CityItem cityItem, int position) {
        return cityItem.getDistrict().get(position);
    }

    public static ProvinceItem[] convertProvinceToArray (List<ProvinceItem> itemList) {
        return itemList.toArray(new ProvinceItem[itemList.size()]);
    }

    public static CityItem[] convertCityToArray (List<CityItem> cityItemList) {
        return cityItemList.toArray(new CityItem[cityItemList.size()]);
    }

    public static DistrictItem[] convertDistrictToArray (List<DistrictItem> districtItemList) {
        return districtItemList.toArray(new DistrictItem[districtItemList.size()]);
    }

}
