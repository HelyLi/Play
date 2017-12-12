package com.hhly.lyygame.data.net.protocol.user;

import com.hhly.lyygame.data.net.protocol.BaseResp;

/**
 * Created by ${HELY} on 17/4/1.
 * 邮箱：heli.lixiong@gmail.com
 */

public class TokenLoginResp extends BaseResp {

    private TokenUserInfo user;

    public TokenUserInfo getUser() {
        return user;
    }

    public void setUser(TokenUserInfo user) {
        this.user = user;
    }

    public static class TokenUserInfo {

        private String idcarNo;
        private int regType;
        private Object createTime;
        private String phone;
        private int sex;
        private String location;
        private int versionCode;
        private int phoneStatus;
        private String password;
        private String loginType;
        private String loginAccount;
        private String registerIp;
        private String lastLoginIp;
        private Object forbiddenStartTime;
        private String platformName;
        private String userId;
        private int age;
        private String qq;
        private Object lastLoginTime;
        private int status;
        private String nickname;
        private String channelNetwork;
        private String headIconSmall;
        private int osType;
        private String versionName;
        private int platformId;
        private String ip;
        private int country;
        private int emailStatus;
        private String headIcon;
        private String forbiddenRemark;
        private String address;
        private String email;
        private String paypassword;
        private String realName;
        private Object forbiddenEndTime;
        private String channel;

        public String getIdcarNo() {
            return idcarNo;
        }

        public void setIdcarNo(String idcarNo) {
            this.idcarNo = idcarNo;
        }

        public int getRegType() {
            return regType;
        }

        public void setRegType(int regType) {
            this.regType = regType;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public int getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(int versionCode) {
            this.versionCode = versionCode;
        }

        public int getPhoneStatus() {
            return phoneStatus;
        }

        public void setPhoneStatus(int phoneStatus) {
            this.phoneStatus = phoneStatus;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getLoginType() {
            return loginType;
        }

        public void setLoginType(String loginType) {
            this.loginType = loginType;
        }

        public String getLoginAccount() {
            return loginAccount;
        }

        public void setLoginAccount(String loginAccount) {
            this.loginAccount = loginAccount;
        }

        public String getRegisterIp() {
            return registerIp;
        }

        public void setRegisterIp(String registerIp) {
            this.registerIp = registerIp;
        }

        public String getLastLoginIp() {
            return lastLoginIp;
        }

        public void setLastLoginIp(String lastLoginIp) {
            this.lastLoginIp = lastLoginIp;
        }

        public Object getForbiddenStartTime() {
            return forbiddenStartTime;
        }

        public void setForbiddenStartTime(Object forbiddenStartTime) {
            this.forbiddenStartTime = forbiddenStartTime;
        }

        public String getPlatformName() {
            return platformName;
        }

        public void setPlatformName(String platformName) {
            this.platformName = platformName;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public Object getLastLoginTime() {
            return lastLoginTime;
        }

        public void setLastLoginTime(Object lastLoginTime) {
            this.lastLoginTime = lastLoginTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getChannelNetwork() {
            return channelNetwork;
        }

        public void setChannelNetwork(String channelNetwork) {
            this.channelNetwork = channelNetwork;
        }

        public String getHeadIconSmall() {
            return headIconSmall;
        }

        public void setHeadIconSmall(String headIconSmall) {
            this.headIconSmall = headIconSmall;
        }

        public int getOsType() {
            return osType;
        }

        public void setOsType(int osType) {
            this.osType = osType;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }

        public int getPlatformId() {
            return platformId;
        }

        public void setPlatformId(int platformId) {
            this.platformId = platformId;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public int getCountry() {
            return country;
        }

        public void setCountry(int country) {
            this.country = country;
        }

        public int getEmailStatus() {
            return emailStatus;
        }

        public void setEmailStatus(int emailStatus) {
            this.emailStatus = emailStatus;
        }

        public String getHeadIcon() {
            return headIcon;
        }

        public void setHeadIcon(String headIcon) {
            this.headIcon = headIcon;
        }

        public String getForbiddenRemark() {
            return forbiddenRemark;
        }

        public void setForbiddenRemark(String forbiddenRemark) {
            this.forbiddenRemark = forbiddenRemark;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPaypassword() {
            return paypassword;
        }

        public void setPaypassword(String paypassword) {
            this.paypassword = paypassword;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public Object getForbiddenEndTime() {
            return forbiddenEndTime;
        }

        public void setForbiddenEndTime(Object forbiddenEndTime) {
            this.forbiddenEndTime = forbiddenEndTime;
        }

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

    }
}
