package com.hhly.lyygame.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ${HELY} on 17/2/5.
 * 邮箱：heli.lixiong@gmail.com
 */

public class GoodsInfo implements Parcelable {
    /**
     * id : 4323
     * userId : hhly91599
     * goodsId : 1593
     * createTime : 1496473608000
     * name : 顺丰输运顺丰输运顺丰输运顺丰输运
     * type : 3
     * picUrl : http://public.13322.com/68558135.png
     * needScoreId : 2
     * needScore : 1.9999999999E8
     * total : 95
     * enable : 0
     * position : 9
     * price : 2.5
     * country : 0
     */

    private int id;
    private String userId;
    private int goodsId;
    private long createTime;
    private String name;
    private int type;
    private String picUrl;
    private int needScoreId;
    private double needScore;
    private int total;
    private int enable;
    private int position;
    private double price;
    private int country;

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    private int orderNum;

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

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public int getNeedScoreId() {
        return needScoreId;
    }

    public void setNeedScoreId(int needScoreId) {
        this.needScoreId = needScoreId;
    }

    public double getNeedScore() {
        return needScore;
    }

    public void setNeedScore(double needScore) {
        this.needScore = needScore;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCountry() {
        return country;
    }

    public void setCountry(int country) {
        this.country = country;
    }


//    /**
//     * id : 1101
//     * name : 天猫1000积分兑换券
//     * type : 1//
//     * picUrl : http://public.13322.com/5445f82f.jpg
//     * needScoreId : 2
//     * needScore : 1.0
//     * total : 100
//     * enable : 0
//     * price : 1000.0
//     * country : 0
//     * position : 2
//     * createTime : 1483948543000
//     * updateTime : null
//     * platform : 4
//     * placeType:
//     * userId:
//     */
//
//    private int id;
//    private String name;
//    private int type;
//    private String picUrl;
//    private int orderNum;
//
//    /**
//     * 1：乐盈币 2：乐盈券 3：积分
//     */
//    private int needScoreId;
//    private double needScore;
//    private int total;
//    private int enable;
//    private double price;
//    private int country;
//    private int position;
//    private long createTime;
//    private Long updateTime;
//    private int platform;
//    private int placeType;
//    private String userId;//用户ID返回null，表示未收藏，返回用户ID值，表示已收藏
//
//    public int getOrderNum() {
//        return orderNum;
//    }
//
//    public void setOrderNum(int orderNum) {
//        this.orderNum = orderNum;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getType() {
//        return type;
//    }
//
//    public void setType(int type) {
//        this.type = type;
//    }
//
//    public String getPicUrl() {
//        return picUrl;
//    }
//
//    public void setPicUrl(String picUrl) {
//        this.picUrl = picUrl;
//    }
//
//    public int getNeedScoreId() {
//        return needScoreId;
//    }
//
//    public void setNeedScoreId(int needScoreId) {
//        this.needScoreId = needScoreId;
//    }
//
//    public double getNeedScore() {
//        return needScore;
//    }
//
//    public void setNeedScore(double needScore) {
//        this.needScore = needScore;
//    }
//
//    public int getTotal() {
//        return total;
//    }
//
//    public void setTotal(int total) {
//        this.total = total;
//    }
//
//    public int getEnable() {
//        return enable;
//    }
//
//    public void setEnable(int enable) {
//        this.enable = enable;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }
//
//    public int getCountry() {
//        return country;
//    }
//
//    public void setCountry(int country) {
//        this.country = country;
//    }
//
//    public int getPosition() {
//        return position;
//    }
//
//    public void setPosition(int position) {
//        this.position = position;
//    }
//
//    public long getCreateTime() {
//        return createTime;
//    }
//
//    public void setCreateTime(long createTime) {
//        this.createTime = createTime;
//    }
//
//    public Long getUpdateTime() {
//        return updateTime;
//    }
//
//    public void setUpdateTime(Long updateTime) {
//        this.updateTime = updateTime;
//    }
//
//    public int getPlatform() {
//        return platform;
//    }
//
//    public void setPlatform(int platform) {
//        this.platform = platform;
//    }
//
//    public int getPlaceType() {
//        return placeType;
//    }
//
//    public void setPlaceType(int placeType) {
//        this.placeType = placeType;
//    }
//
//    public String getUserId() {
//        return userId;
//    }
//
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }
//
//    @Override
//    public String toString() {
//        return "GoodsInfo{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", type=" + type +
//                ", picUrl='" + picUrl + '\'' +
//                ", needScoreId=" + needScoreId +
//                ", needScore=" + needScore +
//                ", total=" + total +
//                ", enable=" + enable +
//                ", price=" + price +
//                ", country=" + country +
//                ", position=" + position +
//                ", createTime=" + createTime +
//                ", updateTime=" + updateTime +
//                ", platform=" + platform +
//                '}';
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeInt(this.id);
//        dest.writeString(this.name);
//        dest.writeInt(this.type);
//        dest.writeString(this.picUrl);
//        dest.writeInt(this.orderNum);
//        dest.writeInt(this.needScoreId);
//        dest.writeDouble(this.needScore);
//        dest.writeInt(this.total);
//        dest.writeInt(this.enable);
//        dest.writeDouble(this.price);
//        dest.writeInt(this.country);
//        dest.writeInt(this.position);
//        dest.writeLong(this.createTime);
//        dest.writeValue(this.updateTime);
//        dest.writeInt(this.platform);
//        dest.writeInt(this.placeType);
//        dest.writeString(this.userId);
//    }
//
//    public GoodsInfo() {
//    }
//
//    protected GoodsInfo(Parcel in) {
//        this.id = in.readInt();
//        this.name = in.readString();
//        this.type = in.readInt();
//        this.picUrl = in.readString();
//        this.orderNum = in.readInt();
//        this.needScoreId = in.readInt();
//        this.needScore = in.readDouble();
//        this.total = in.readInt();
//        this.enable = in.readInt();
//        this.price = in.readDouble();
//        this.country = in.readInt();
//        this.position = in.readInt();
//        this.createTime = in.readLong();
//        this.updateTime = (Long) in.readValue(Long.class.getClassLoader());
//        this.platform = in.readInt();
//        this.placeType = in.readInt();
//        this.userId = in.readString();
//    }
//
//    public static final Parcelable.Creator<GoodsInfo> CREATOR = new Parcelable.Creator<GoodsInfo>() {
//        @Override
//        public GoodsInfo createFromParcel(Parcel source) {
//            return new GoodsInfo(source);
//        }
//
//        @Override
//        public GoodsInfo[] newArray(int size) {
//            return new GoodsInfo[size];
//        }
//    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.userId);
        dest.writeInt(this.goodsId);
        dest.writeLong(this.createTime);
        dest.writeString(this.name);
        dest.writeInt(this.type);
        dest.writeString(this.picUrl);
        dest.writeInt(this.needScoreId);
        dest.writeDouble(this.needScore);
        dest.writeInt(this.total);
        dest.writeInt(this.enable);
        dest.writeInt(this.position);
        dest.writeDouble(this.price);
        dest.writeInt(this.country);
    }

    public GoodsInfo() {
    }

    protected GoodsInfo(Parcel in) {
        this.id = in.readInt();
        this.userId = in.readString();
        this.goodsId = in.readInt();
        this.createTime = in.readLong();
        this.name = in.readString();
        this.type = in.readInt();
        this.picUrl = in.readString();
        this.needScoreId = in.readInt();
        this.needScore = in.readDouble();
        this.total = in.readInt();
        this.enable = in.readInt();
        this.position = in.readInt();
        this.price = in.readDouble();
        this.country = in.readInt();
    }

    public static final Creator<GoodsInfo> CREATOR = new Creator<GoodsInfo>() {
        @Override
        public GoodsInfo createFromParcel(Parcel source) {
            return new GoodsInfo(source);
        }

        @Override
        public GoodsInfo[] newArray(int size) {
            return new GoodsInfo[size];
        }
    };
}
