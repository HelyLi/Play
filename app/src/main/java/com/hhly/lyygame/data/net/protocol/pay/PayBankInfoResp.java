package com.hhly.lyygame.data.net.protocol.pay;

import com.hhly.lyygame.data.net.protocol.BaseResp;

/**
 * description :
 * Created by Flynn
 * 2017/5/13
 */

public class PayBankInfoResp extends BaseResp {


    /**
     * id : 18
     * platformTerminal : 4
     * bankName : 北京银行
     * position : 1
     * isDepositCard : 1
     * isCreditCard : 1
     * pcBankImgUrl : http://public.13322.com/511c101b.jpg
     * mobileBankImgUrl : http://public.13322.com/21b44184.jpg
     * status : 1
     * type : 1
     * limitation : 北京银行
     * country : 0
     * createTime : 1494518400000
     * createUser : mrdeepen
     * updateTime : 1494518400000
     * updateUser : mrdeepen
     */

    private int id;
    private int platformTerminal;
    private String bankName;
    private int position;
    private int isDepositCard;
    private int isCreditCard;
    private String pcBankImgUrl;
    private String mobileBankImgUrl;
    private int status;
    private int type;
    private String limitation;
    private int country;
    private long createTime;
    private String createUser;
    private long updateTime;
    private String updateUser;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlatformTerminal() {
        return platformTerminal;
    }

    public void setPlatformTerminal(int platformTerminal) {
        this.platformTerminal = platformTerminal;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getIsDepositCard() {
        return isDepositCard;
    }

    public void setIsDepositCard(int isDepositCard) {
        this.isDepositCard = isDepositCard;
    }

    public int getIsCreditCard() {
        return isCreditCard;
    }

    public void setIsCreditCard(int isCreditCard) {
        this.isCreditCard = isCreditCard;
    }

    public String getPcBankImgUrl() {
        return pcBankImgUrl;
    }

    public void setPcBankImgUrl(String pcBankImgUrl) {
        this.pcBankImgUrl = pcBankImgUrl;
    }

    public String getMobileBankImgUrl() {
        return mobileBankImgUrl;
    }

    public void setMobileBankImgUrl(String mobileBankImgUrl) {
        this.mobileBankImgUrl = mobileBankImgUrl;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getLimitation() {
        return limitation;
    }

    public void setLimitation(String limitation) {
        this.limitation = limitation;
    }

    public int getCountry() {
        return country;
    }

    public void setCountry(int country) {
        this.country = country;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
}
