package com.github.freeman0211.library.address;

import java.io.Serializable;
import java.util.List;

/**
 * Created by freeman on 16/3/2.
 */
public final class ProvinceItem implements Serializable {

    private String name;
    private List<CityItem> city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CityItem> getCity() {
        return city;
    }

    public void setCity(List<CityItem> city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return name;
    }
}
