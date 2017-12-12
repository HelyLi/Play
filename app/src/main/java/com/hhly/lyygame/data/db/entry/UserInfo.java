package com.hhly.lyygame.data.db.entry;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

/**
 * Created by ${HELY} on 16/12/22.
 * 邮箱：heli.lixiong@gmail.com
 */
@Entity(active = true, nameInDb = "USER_INFO")
public class UserInfo {
    
    @Id
    private Long id;
    @Unique
    String token;
    String userId;//账号
    String password;//密码
    String email;//邮箱
    String phone;//手机号
    String headIcon;// 大图
    String headIconSmall;// 小图
    String nickname;//昵称
    Integer sex;//性别 ：1男、 2女 、3保密
    Integer age;//年龄
    String location;//区域地址  存放格式为：湖南省 湘西土家族苗族自治州 凤凰县
    Long lastLoginTime;// 最后登录时间
    String lastLoginIp;// 最后登录ip
    String ip;// 注册ip
    String registerIp;// 注册ip
    Integer status; // 0 禁用 1 未激活 2 已激活
    String loginAccount;// 用户登录输入值
    String realName;// 真实姓名
    String idcarNo;// 身份证号码
    String address;// 详细地址
    String qq;// qq
    Integer emailStatus; // 0:未绑定 ，1：绑定
    Integer phoneStatus; // 0:未绑定 ，1：绑定
    Integer regType; // 1 用户名注册 2 手机注册 3 邮箱注册 4 第三方登录
    Integer platformId;// 平台id
    String platformName;// 平台Name
    Long createTime; // 注册时间，即创建时间
    String channel;// 注册渠道 （web 和移动端）
    String paypassword;// 支付密码
    String loginType;// 登录类型： 0：平台注册登录；1：QQ注册登录；2：新浪微博注册登录；3：微信注册登录；4：支付宝注册登录；5：twitter注册登录；6：Facebook注册登录；
    String versionName;// 版本名称
    Integer osType;// 来源（1pc 2iOS 3安卓 不是 1||2||3就是其他）
    Integer versionCode;// 版本编号
    Long forbiddenStartTime; // 禁用开始时间
    Long forbiddenEndTime; // 禁用结束时间
    Integer country;// 用户来自哪里(0中国 1美国 2泰国 3越南)
    String forbiddenRemark;//禁用备注
    String lyb;//个人乐盈币
    String jf;//个人积分
    String lyq;//个人乐盈券
    Integer integrity; //资料完整度
    Integer safeLevel; // 安全等级
    Integer bindFlag;//绑定标志
    Integer paypwdFlag; //是否设置支付密码
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 437952339)
    private transient UserInfoDao myDao;
    @Generated(hash = 1163021296)
    public UserInfo(Long id, String token, String userId, String password,
            String email, String phone, String headIcon, String headIconSmall,
            String nickname, Integer sex, Integer age, String location,
            Long lastLoginTime, String lastLoginIp, String ip, String registerIp,
            Integer status, String loginAccount, String realName, String idcarNo,
            String address, String qq, Integer emailStatus, Integer phoneStatus,
            Integer regType, Integer platformId, String platformName,
            Long createTime, String channel, String paypassword, String loginType,
            String versionName, Integer osType, Integer versionCode,
            Long forbiddenStartTime, Long forbiddenEndTime, Integer country,
            String forbiddenRemark, String lyb, String jf, String lyq,
            Integer integrity, Integer safeLevel, Integer bindFlag,
            Integer paypwdFlag) {
        this.id = id;
        this.token = token;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.headIcon = headIcon;
        this.headIconSmall = headIconSmall;
        this.nickname = nickname;
        this.sex = sex;
        this.age = age;
        this.location = location;
        this.lastLoginTime = lastLoginTime;
        this.lastLoginIp = lastLoginIp;
        this.ip = ip;
        this.registerIp = registerIp;
        this.status = status;
        this.loginAccount = loginAccount;
        this.realName = realName;
        this.idcarNo = idcarNo;
        this.address = address;
        this.qq = qq;
        this.emailStatus = emailStatus;
        this.phoneStatus = phoneStatus;
        this.regType = regType;
        this.platformId = platformId;
        this.platformName = platformName;
        this.createTime = createTime;
        this.channel = channel;
        this.paypassword = paypassword;
        this.loginType = loginType;
        this.versionName = versionName;
        this.osType = osType;
        this.versionCode = versionCode;
        this.forbiddenStartTime = forbiddenStartTime;
        this.forbiddenEndTime = forbiddenEndTime;
        this.country = country;
        this.forbiddenRemark = forbiddenRemark;
        this.lyb = lyb;
        this.jf = jf;
        this.lyq = lyq;
        this.integrity = integrity;
        this.safeLevel = safeLevel;
        this.bindFlag = bindFlag;
        this.paypwdFlag = paypwdFlag;
    }
    @Generated(hash = 1279772520)
    public UserInfo() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getToken() {
        return this.token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getUserId() {
        return this.userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getHeadIcon() {
        return this.headIcon;
    }
    public void setHeadIcon(String headIcon) {
        this.headIcon = headIcon;
    }
    public String getHeadIconSmall() {
        return this.headIconSmall;
    }
    public void setHeadIconSmall(String headIconSmall) {
        this.headIconSmall = headIconSmall;
    }
    public String getNickname() {
        return this.nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public Integer getSex() {
        return this.sex;
    }
    public void setSex(Integer sex) {
        this.sex = sex;
    }
    public Integer getAge() {
        return this.age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public String getLocation() {
        return this.location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public Long getLastLoginTime() {
        return this.lastLoginTime;
    }
    public void setLastLoginTime(Long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
    public String getLastLoginIp() {
        return this.lastLoginIp;
    }
    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }
    public String getIp() {
        return this.ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getRegisterIp() {
        return this.registerIp;
    }
    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp;
    }
    public Integer getStatus() {
        return this.status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getLoginAccount() {
        return this.loginAccount;
    }
    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }
    public String getRealName() {
        return this.realName;
    }
    public void setRealName(String realName) {
        this.realName = realName;
    }
    public String getIdcarNo() {
        return this.idcarNo;
    }
    public void setIdcarNo(String idcarNo) {
        this.idcarNo = idcarNo;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getQq() {
        return this.qq;
    }
    public void setQq(String qq) {
        this.qq = qq;
    }
    public Integer getEmailStatus() {
        return this.emailStatus;
    }
    public void setEmailStatus(Integer emailStatus) {
        this.emailStatus = emailStatus;
    }
    public Integer getPhoneStatus() {
        return this.phoneStatus;
    }
    public void setPhoneStatus(Integer phoneStatus) {
        this.phoneStatus = phoneStatus;
    }
    public Integer getRegType() {
        return this.regType;
    }
    public void setRegType(Integer regType) {
        this.regType = regType;
    }
    public Integer getPlatformId() {
        return this.platformId;
    }
    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }
    public String getPlatformName() {
        return this.platformName;
    }
    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }
    public Long getCreateTime() {
        return this.createTime;
    }
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
    public String getChannel() {
        return this.channel;
    }
    public void setChannel(String channel) {
        this.channel = channel;
    }
    public String getPaypassword() {
        return this.paypassword;
    }
    public void setPaypassword(String paypassword) {
        this.paypassword = paypassword;
    }
    public String getLoginType() {
        return this.loginType;
    }
    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }
    public String getVersionName() {
        return this.versionName;
    }
    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }
    public Integer getOsType() {
        return this.osType;
    }
    public void setOsType(Integer osType) {
        this.osType = osType;
    }
    public Integer getVersionCode() {
        return this.versionCode;
    }
    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }
    public Long getForbiddenStartTime() {
        return this.forbiddenStartTime;
    }
    public void setForbiddenStartTime(Long forbiddenStartTime) {
        this.forbiddenStartTime = forbiddenStartTime;
    }
    public Long getForbiddenEndTime() {
        return this.forbiddenEndTime;
    }
    public void setForbiddenEndTime(Long forbiddenEndTime) {
        this.forbiddenEndTime = forbiddenEndTime;
    }
    public Integer getCountry() {
        return this.country;
    }
    public void setCountry(Integer country) {
        this.country = country;
    }
    public String getForbiddenRemark() {
        return this.forbiddenRemark;
    }
    public void setForbiddenRemark(String forbiddenRemark) {
        this.forbiddenRemark = forbiddenRemark;
    }
    public String getLyb() {
        return this.lyb;
    }
    public void setLyb(String lyb) {
        this.lyb = lyb;
    }
    public String getJf() {
        return this.jf;
    }
    public void setJf(String jf) {
        this.jf = jf;
    }
    public String getLyq() {
        return this.lyq;
    }
    public void setLyq(String lyq) {
        this.lyq = lyq;
    }
    public Integer getIntegrity() {
        return this.integrity;
    }
    public void setIntegrity(Integer integrity) {
        this.integrity = integrity;
    }
    public Integer getSafeLevel() {
        return this.safeLevel;
    }
    public void setSafeLevel(Integer safeLevel) {
        this.safeLevel = safeLevel;
    }
    public Integer getBindFlag() {
        return this.bindFlag;
    }
    public void setBindFlag(Integer bindFlag) {
        this.bindFlag = bindFlag;
    }
    public Integer getPaypwdFlag() {
        return this.paypwdFlag;
    }
    public void setPaypwdFlag(Integer paypwdFlag) {
        this.paypwdFlag = paypwdFlag;
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 821180768)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getUserInfoDao() : null;
    }


}
