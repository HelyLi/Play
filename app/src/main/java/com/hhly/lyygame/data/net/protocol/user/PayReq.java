package com.hhly.lyygame.data.net.protocol.user;

import com.hhly.lyygame.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by dell on 2017/3/21.
 */

public class PayReq extends BaseReq {

    /**
     * 金额(优贝和cc点卡不用填)
     */
    private String money;
    /**
     * 0 银联,1 支付宝,2 微信,3 PayPal,4乐盈币,7 CC点卡,8 优贝支付,9 mol电子钱包,10 mol点卡
     */
    private String type;

    private String appId;

    public String getPayPlatformId() {
        return payPlatformId;
    }

    public void setPayPlatformId(String payPlatformId) {
        this.payPlatformId = payPlatformId;
    }

    private String payPlatformId;

    /**
     * 商品ID
     */
    private String goodsId;
    /**
     * 游戏id
     */
    private String game;
    /**
     * 游戏名称
     */
    private String gameName;
    /**
     * 充值到（0游戏1乐盈币2商城购买）
     */
    private String to;
    /**
     * 账户
     */
    private String account;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 支付接口 0：PC 1：WAP 2：APP
     */
    private String service;
    /**
     * 支付返回url
     */
    private String returnUrl;
    /**
     * 支付系统流水号
     */
    private String businessNo;
    /**
     * 扩展信息
     */
    private String extra;
    /**
     * 充值来源
     */
    private String source;
    /**
     * 游戏区id
     */
    private Long sonPlatformId;
    /**
     * 游戏服id
     */
    private Long gameServiceId;
    /**
     * 充值卡卡号(优贝和cc点卡需要填)
     */
    private String rechargeCard;
    /**
     * 序列号(cc点卡需要填)
     */
    private String rechargePassword;
    /**
     * 海外支付（目前仅优贝支付要传）
     */
    private String overseasType;

    private String addressId;

    private String phone;

    private String payToAccount;

    private String productId;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getBusinessNo() {
        return businessNo;
    }

    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Long getSonPlatformId() {
        return sonPlatformId;
    }

    public void setSonPlatformId(Long sonPlatformId) {
        this.sonPlatformId = sonPlatformId;
    }

    public Long getGameServiceId() {
        return gameServiceId;
    }

    public void setGameServiceId(Long gameServiceId) {
        this.gameServiceId = gameServiceId;
    }

    public String getRechargeCard() {
        return rechargeCard;
    }

    public void setRechargeCard(String rechargeCard) {
        this.rechargeCard = rechargeCard;
    }

    public String getRechargePassword() {
        return rechargePassword;
    }

    public void setRechargePassword(String rechargePassword) {
        this.rechargePassword = rechargePassword;
    }

    public String getOverseasType() {
        return overseasType;
    }

    public void setOverseasType(String overseasType) {
        this.overseasType = overseasType;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPayToAccount() {
        return payToAccount;
    }

    public void setPayToAccount(String payToAccount) {
        this.payToAccount = payToAccount;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public Map<String, String> params() {
        final Map<String, String> maps = super.params();
        if (money != null) {
            maps.put("money", money);
        }
        if (type != null) {
            maps.put("type", type);
        }
        if (appId != null){
            maps.put("appId", appId);
        }
        if (payPlatformId != null){
            maps.put("payPlatformId", payPlatformId);
        }
        if (goodsId != null) {
            maps.put("goodsId", goodsId);
        }
        if (game != null) {
            maps.put("game", game);
        }
        if (gameName != null) {
            maps.put("gameName", gameName);
        }
        if (to != null) {
            maps.put("to", to);
        }
        if (account != null) {
            maps.put("account", account);
        }
        if (orderNo != null) {
            maps.put("orderNo", orderNo);
        }
        if (service != null) {
            maps.put("service", service);
        }
        if (returnUrl != null) {
            maps.put("returnUrl", returnUrl);
        }
        if (businessNo != null) {
            maps.put("businessNo", businessNo);
        }
        if (extra != null) {
            maps.put("extra", extra);
        }
        if (source != null) {
            maps.put("source", source);
        }
        if (sonPlatformId != null) {
            maps.put("sonPlatformId", String.valueOf(sonPlatformId));
        }
        if (gameServiceId != null) {
            maps.put("gameServiceId", String.valueOf(gameServiceId));
        }
        if (rechargeCard != null) {
            maps.put("rechargeCard", rechargeCard);
        }
        if (rechargePassword != null) {
            maps.put("rechargePassword", rechargePassword);
        }
        if (overseasType != null) {
            maps.put("overseasType", overseasType);
        }
        if (addressId != null) {
            maps.put("addressId", addressId);
        }
        if (phone != null) {
            maps.put("phone", phone);
        }
        if (payToAccount != null) {
            maps.put("payToAccount", payToAccount);
        }
        if (productId != null) {
            maps.put("productId", productId);
        }
        return maps;
    }

    @Override
    public String toString() {
        return "PayReq{" +
                "money='" + money + '\'' +
                ", type='" + type + '\'' +
                ", appId='" + appId + '\'' +
                ", payPlatformId='" + payPlatformId + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", game='" + game + '\'' +
                ", gameName='" + gameName + '\'' +
                ", to='" + to + '\'' +
                ", account='" + account + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", service='" + service + '\'' +
                ", returnUrl='" + returnUrl + '\'' +
                ", businessNo='" + businessNo + '\'' +
                ", extra='" + extra + '\'' +
                ", source='" + source + '\'' +
                ", sonPlatformId=" + sonPlatformId +
                ", gameServiceId=" + gameServiceId +
                ", rechargeCard='" + rechargeCard + '\'' +
                ", rechargePassword='" + rechargePassword + '\'' +
                ", overseasType='" + overseasType + '\'' +
                ", addressId='" + addressId + '\'' +
                ", phone='" + phone + '\'' +
                ", payToAccount='" + payToAccount + '\'' +
                ", productId='" + productId + '\'' +
                '}';
    }

}
