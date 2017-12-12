package com.github.freeman0211.library.address;

import java.io.Serializable;
import java.util.List;

/**
 * Created by freeman on 16/3/2.
 */
public final class CityItem implements Serializable {

    private String name;
    private List<DistrictItem> district;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DistrictItem> getDistrict() {
        return district;
    }

    public void setDistrict(List<DistrictItem> district) {
        this.district = district;
    }

    @Override
    public String toString() {
        return name;
    }
}
