package com.hhly.lyygame.data.net.protocol.user;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Date;
import java.util.Map;

/**
 * Created by ${HELY} on 17/1/3.
 * 邮箱：heli.lixiong@gmail.com
 */

public class UpdateUserInfoReq extends BaseReq {

    UpdateUserInfo mUpdateUserInfo;

    public UpdateUserInfoReq(UpdateUserInfo updateUserInfo) {
        mUpdateUserInfo = updateUserInfo;
    }

    public UpdateUserInfo getUpdateUserInfo() {
        return mUpdateUserInfo;
    }

    public static class UpdateUserInfo{

        String userId; //用户名
        String email;  //邮箱
        String phone; //电话号码
        String headIcon; // 头像路径
        String nickname; //昵称
        Integer sex; //性别  1男 2女 3保密
        Integer age; // 年龄
        Integer status; // 状态 0 禁用 1 未激活 2 已激活
        String location; // 地区
        String realName;// 真实姓名
        String idcarNo;// 身份证号码
        String address;// 详细地址
        String qq;// QQ号
        Integer emailStatus;//邮箱状态 0:未绑定 1：已绑定
        String key;//预留字段
        String password; //密码
        Date forbiddenStartTime; // 禁用开始时间
        Date forbiddenEndTime; //  禁用结束时间
        String forbiddenRemark;//禁用备注

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getHeadIcon() {
            return headIcon;
        }

        public void setHeadUrl(String headIcon) {
            this.headIcon = headIcon;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public Integer getSex() {
            return sex;
        }

        public void setSex(Integer sex) {
            this.sex = sex;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getIdcarNo() {
            return idcarNo;
        }

        public void setIdcarNo(String idcarNo) {
            this.idcarNo = idcarNo;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public Integer getEmailStatus() {
            return emailStatus;
        }

        public void setEmailStatus(Integer emailStatus) {
            this.emailStatus = emailStatus;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Date getForbiddenStartTime() {
            return forbiddenStartTime;
        }

        public void setForbiddenStartTime(Date forbiddenStartTime) {
            this.forbiddenStartTime = forbiddenStartTime;
        }

        public Date getForbiddenEndTime() {
            return forbiddenEndTime;
        }

        public void setForbiddenEndTime(Date forbiddenEndTime) {
            this.forbiddenEndTime = forbiddenEndTime;
        }

        public String getForbiddenRemark() {
            return forbiddenRemark;
        }

        public void setForbiddenRemark(String forbiddenRemark) {
            this.forbiddenRemark = forbiddenRemark;
        }

        @Override
        public String toString() {
            return "UpdateUserInfo{" +
                    "userId='" + userId + '\'' +
                    ", email='" + email + '\'' +
                    ", phone='" + phone + '\'' +
                    ", headIcon='" + headIcon + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", sex=" + sex +
                    ", age=" + age +
                    ", status=" + status +
                    ", location='" + location + '\'' +
                    ", realName='" + realName + '\'' +
                    ", idcarNo='" + idcarNo + '\'' +
                    ", address='" + address + '\'' +
                    ", qq='" + qq + '\'' +
                    ", emailStatus=" + emailStatus +
                    ", key='" + key + '\'' +
                    ", password='" + password + '\'' +
                    ", forbiddenStartTime=" + forbiddenStartTime +
                    ", forbiddenEndTime=" + forbiddenEndTime +
                    ", forbiddenRemark='" + forbiddenRemark + '\'' +
                    '}';
        }

    }


    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();

        if (mUpdateUserInfo.email != null){
            params.put("email", mUpdateUserInfo.email);
        }
        if (mUpdateUserInfo.phone != null){
            params.put("phone", mUpdateUserInfo.phone);
        }
        if (mUpdateUserInfo.headIcon != null){
            params.put("headIcon", mUpdateUserInfo.headIcon);
        }
        if (mUpdateUserInfo.nickname != null){
            params.put("nickname", mUpdateUserInfo.nickname);
        }
        if (mUpdateUserInfo.sex != null){
            params.put("sex", String.valueOf(mUpdateUserInfo.sex));
        }
        if (mUpdateUserInfo.age != null){
            params.put("age", String.valueOf(mUpdateUserInfo.age));
        }
        if (mUpdateUserInfo.status != null){
            params.put("status", String.valueOf(mUpdateUserInfo.status));
        }
        if (mUpdateUserInfo.location != null){
            params.put("location", mUpdateUserInfo.location);
        }
        if (mUpdateUserInfo.realName != null){
            params.put("realName", mUpdateUserInfo.realName);
        }
        if (mUpdateUserInfo.idcarNo != null){
            params.put("idcarNo", mUpdateUserInfo.idcarNo);
        }
        if (mUpdateUserInfo.address != null){
            params.put("address", mUpdateUserInfo.address);
        }
        if (mUpdateUserInfo.qq != null){
            params.put("qq", mUpdateUserInfo.qq);
        }
        if (mUpdateUserInfo.password != null){
            params.put("password", mUpdateUserInfo.password);
        }

        return params;
    }

    @Override
    public String toString() {
        return "UpdateUserInfoReq{" +
                "mUpdateUserInfo=" + mUpdateUserInfo +
                '}';
    }
}
