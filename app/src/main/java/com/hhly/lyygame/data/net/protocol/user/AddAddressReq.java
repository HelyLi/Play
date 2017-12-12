package com.hhly.lyygame.data.net.protocol.user;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by ${HELY} on 17/2/10.
 * 邮箱：heli.lixiong@gmail.com
 */

public class AddAddressReq extends BaseReq {

//    addReq.city:深圳市
//    addReq.detail:等等等等等等
//    addReq.name:李雄
//    addReq.province:广东省
//    addReq.street:南山区
//    addReq.tel:18688978615
//    addReq.useDefault:1
//    addReq.userId:hhly91480
//    country:0

    private String city;
    private String detail;
    private String name;
    private String province;
    private String street;
    private String tel;
    private Integer useDefault;
    private String userId;
//    private Integer country;

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    private Integer addressId;

    public void setCity(String city) {
        this.city = city;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setUseDefault(Integer useDefault) {
        this.useDefault = useDefault;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();

        if (city != null){
            params.put("city", city);
        }
        if (detail != null){
            params.put("detail", detail);
        }
        if (name != null){
            params.put("name", name);
        }
        if (province != null){
            params.put("province", province);
        }
        if (street != null){
            params.put("street", street);
        }
        if (tel != null){
            params.put("tel", tel);
        }
        if (useDefault != null){
            params.put("useDefault", String.valueOf(useDefault));
        }
        if (userId != null){
            params.put("userId", userId);
        }
        if (addressId != null){
            params.put("addressId", String.valueOf(addressId));
        }

        return params;
    }

    @Override
    public String toString() {
        return "AddAddressReq{" +
                "city='" + city + '\'' +
                ", detail='" + detail + '\'' +
                ", name='" + name + '\'' +
                ", province='" + province + '\'' +
                ", street='" + street + '\'' +
                ", tel='" + tel + '\'' +
                ", useDefault=" + useDefault +
                ", userId='" + userId + '\'' +
                ", addressId=" + addressId +
                '}';
    }
}

