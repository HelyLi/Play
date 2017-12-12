package com.github.freeman0211.library.address;

import java.io.Serializable;

/**
 * Created by freeman on 16/3/2.
 */
public final class DistrictItem implements Serializable {

    private String name;
    private String zipcode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public String toString() {
        return name;
    }
}
