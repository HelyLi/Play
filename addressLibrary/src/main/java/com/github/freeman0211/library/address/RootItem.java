package com.github.freeman0211.library.address;

import java.io.Serializable;
import java.util.List;

/**
 * Created by freeman on 16/3/2.
 */
public final class RootItem implements Serializable {

    private List<ProvinceItem> province;

    public List<ProvinceItem> getProvince() {
        return province;
    }

    public void setProvince(List<ProvinceItem> province) {
        this.province = province;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RootItem{");
        sb.append("province=").append(province);
        sb.append('}');
        return sb.toString();
    }
}
