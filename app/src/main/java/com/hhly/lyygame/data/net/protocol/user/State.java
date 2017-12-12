package com.hhly.lyygame.data.net.protocol.user;

/**
 * 状态
 * Created by Simon on 2016/12/7.
 */

public final class State {
    private State() {}

    public static final int OK = 0;

    /**
     * 注册类型
     */
    public static final class RegisterType {
        private RegisterType() {}

        /**
         * 用户名
         */
        public static final int NAME = 1;
        /**
         * 手机号
         */
        public static final int PHONE = 2;
        /**
         * 邮箱
         */
        public static final int EMAIL = 3;
    }

    /**
     * 登录类型
     */
    public static final class LoginType{
        private LoginType(){}

        /**
         * 用户名
         */
        public static final int NAME = 1;

        /**
         * 邮箱
         */
        public static final int EMAIL = 2;

        /**
         * 手机号
         */
        public static final int PHONE = 3;

        /**
         * 第三方登录
         */
        public static final int THIRD = 4;
    }

    public static final class ResetTyee{
        private ResetTyee(){};

        /**
         * 忘记密码
         */
        public static final int FORGET = 1;

        /**
         * 修改密码
         */
        public static final int CHANGE = 2;
    }

    /**
     * 操作类型
     */
    public static final class OperateType {
        private OperateType() {}

        /**
         * 手机注册
         */
        public static final int REGISTER = 1;

        /**
         * 绑定手机
         */
        public static final int BIND_PHONE = 2;

        /**
         * 修改登录密码
         */
        public static final int CHANGE_PWD = 3;

        /**
         * 忘记密码
         */
        public static final int FORGOT_PWD = 4;

        /**
         * 验证手机号
         */
        public static final int VERI_PHONE = 9;
    }

    /**
     * 开服类型
     */
    public static final class ServiceType{

        private ServiceType(){}

        //最新开服
        public static final int NEW = 1;
        //开服预告
        public static final int FUTURE = 0;
    }

    /**
     * 平台类型//1.PC 2.安卓 3.ios 4.h5  5.其他
     */
    public static final class PlatformTerminalType{

        private PlatformTerminalType(){}

        public static final int PC = 1;
        public static final int ANDROID = 2;
        public static final int IOS = 3;
        public static final int H5 = 4;
        public static final int OTHER = 5;
    }

    /**
     * 1：充值明细、2：乐盈币明细、3：积分明细
     */
    public static final class DetailsType{
        private DetailsType(){}

        public static final int RECHARGE_TYPE = 1;
        public static final int COINS_TYPE = 2;
        public static final int SCORE_TYPE = 3;
    }

    /**
     * 1：乐盈币 2：乐盈券 3：积分
     */
    public static final class MallType{

        private MallType(){}

        public static final int COINS = 1;
        public static final int COUPON = 2;
        public static final int SCORE = 3;
    }

    /**
     * 1. 实物，2电话卡，3，礼包
     */
    public static final class GoodsType{

        private GoodsType(){}

        public static final int MATTER = 1;
        public static final int CARD = 2;
        public static final int GIFT = 3;

    }

    public static final class ThirdType{

        private ThirdType(){}

        public static final int QQ = 0;
        public static final int WECHAT = 1;
        public static final int WEIBO = 2;

    }

}
