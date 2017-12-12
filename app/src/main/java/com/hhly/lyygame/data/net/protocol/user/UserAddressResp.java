package com.hhly.lyygame.data.net.protocol.user;

import android.os.Parcel;
import android.os.Parcelable;

import com.hhly.lyygame.data.net.protocol.BaseResp;

import java.util.List;

/**
 * Created by ${HELY} on 17/2/10.
 * 邮箱：heli.lixiong@gmail.com
 */

public class UserAddressResp extends BaseResp {

    private List<AddressBean> list;

    public List<AddressBean> getList() {
        return list;
    }

    public void setList(List<AddressBean> list) {
        this.list = list;
    }

    public static class AddressBean implements Parcelable ,Comparable<AddressBean>{
        /**
         * id : 633
         * userId : hhly91480
         * name : 邓君明
         * tel : 15795456346
         * province : 云南省
         * city : 保山市
         * street : 龙陵县
         * detail : 惠州惠城区
         * createTime : 1481783706000
         * last : 0
         * country : 0
         */

        private int id;
        private String userId;
        private String name;
        private String tel;
        private String province;
        private String city;
        private String street;
        private String detail;
        private long createTime;
        private int last;//默认地址 0 是; 1 否
        private int country;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getLast() {
            return last;
        }

        public void setLast(int last) {
            this.last = last;
        }

        public int getCountry() {
            return country;
        }

        public void setCountry(int country) {
            this.country = country;
        }

        @Override
        public String toString() {
            return "AddressBean{" +
                    "id=" + id +
                    ", userId='" + userId + '\'' +
                    ", name='" + name + '\'' +
                    ", tel='" + tel + '\'' +
                    ", province='" + province + '\'' +
                    ", city='" + city + '\'' +
                    ", street='" + street + '\'' +
                    ", detail='" + detail + '\'' +
                    ", createTime=" + createTime +
                    ", last=" + last +
                    ", country=" + country +
                    '}';
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.userId);
            dest.writeString(this.name);
            dest.writeString(this.tel);
            dest.writeString(this.province);
            dest.writeString(this.city);
            dest.writeString(this.street);
            dest.writeString(this.detail);
            dest.writeLong(this.createTime);
            dest.writeInt(this.last);
            dest.writeInt(this.country);
        }

        public AddressBean() {
        }

        protected AddressBean(Parcel in) {
            this.id = in.readInt();
            this.userId = in.readString();
            this.name = in.readString();
            this.tel = in.readString();
            this.province = in.readString();
            this.city = in.readString();
            this.street = in.readString();
            this.detail = in.readString();
            this.createTime = in.readLong();
            this.last = in.readInt();
            this.country = in.readInt();
        }

        public static final Parcelable.Creator<AddressBean> CREATOR = new Parcelable.Creator<AddressBean>() {
            @Override
            public AddressBean createFromParcel(Parcel source) {
                return new AddressBean(source);
            }

            @Override
            public AddressBean[] newArray(int size) {
                return new AddressBean[size];
            }
        };

        @Override
        public int compareTo(AddressBean address) {

            return address.getCreateTime() > this.getCreateTime()? 1 : (address.getCreateTime() == this.getCreateTime() ? 0 : -1);
        }
    }
}
