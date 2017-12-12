package com.hhly.lyygame.data.net;

import com.hhly.lyygame.data.net.protocol.BaseResp;
import com.hhly.lyygame.data.net.protocol.game.LoginLogResp;
import com.hhly.lyygame.data.net.protocol.user.AccountCoinsResp;
import com.hhly.lyygame.data.net.protocol.user.AccountScoreResp;
import com.hhly.lyygame.data.net.protocol.user.CheckAccountResp;
import com.hhly.lyygame.data.net.protocol.user.CheckBindResp;
import com.hhly.lyygame.data.net.protocol.user.EmailFilterResp;
import com.hhly.lyygame.data.net.protocol.user.FeedbackResp;
import com.hhly.lyygame.data.net.protocol.user.ForgetPwdResp;
import com.hhly.lyygame.data.net.protocol.user.GetUserInfoResp;
import com.hhly.lyygame.data.net.protocol.user.InviteCodeResp;
import com.hhly.lyygame.data.net.protocol.user.LoginResp;
import com.hhly.lyygame.data.net.protocol.user.MonthSignResp;
import com.hhly.lyygame.data.net.protocol.user.MsgInfoResp;
import com.hhly.lyygame.data.net.protocol.user.MsgListResp;
import com.hhly.lyygame.data.net.protocol.user.MsgStateResp;
import com.hhly.lyygame.data.net.protocol.user.NicknameKeyWordResp;
import com.hhly.lyygame.data.net.protocol.user.NotificationActivityResp;
import com.hhly.lyygame.data.net.protocol.user.PhoneBindResp;
import com.hhly.lyygame.data.net.protocol.user.PhoneFilterResp;
import com.hhly.lyygame.data.net.protocol.user.RealAuthResp;
import com.hhly.lyygame.data.net.protocol.user.RegisterResp;
import com.hhly.lyygame.data.net.protocol.user.ResetPwdResp;
import com.hhly.lyygame.data.net.protocol.user.UpdateUserInfoResp;
import com.hhly.lyygame.data.net.protocol.user.UserSignResp;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 用户相关
 * Created by Simon on 2016/12/6.
 */

public interface UserApi {

    /**
     * 注册
     * @param params 注册参数
     * @return
     */
    @FormUrlEncoded
    @POST("register/doRegister")
    Flowable<RegisterResp> register(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 发送注册验证码
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("register/sendPhoneCode")
    Flowable<BaseResp> sendPhoneCode(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 短信验证码验证
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("register/valiSmsCode")
    Flowable<BaseResp> validatePhoneCode(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 登录
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("login/doLogin/V2")
    Flowable<LoginResp> login(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 忘记密码
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("forgetPassword/updatePassword")
    Flowable<ForgetPwdResp> forgetPwd(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 退出登录
     * @param params 不需要参数
     * @return
     */
    @FormUrlEncoded
    @POST("login/exits")
    Flowable<BaseResp> logout(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 获取用户消息
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("user/getUserInfo")
    Flowable<GetUserInfoResp> getUserInfo(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 用户登录日志记录
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("login/loginLog")
    Flowable<LoginLogResp> loginLog(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 查询用户名是否存在
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("register/findAccountName")
    Flowable<CheckAccountResp> checkAccount(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 校验当前已登录用户的密码
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("user/validatePwd")
    Flowable<BaseResp> validatePwd(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 完善防沉迷信息(用户认证)
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("user/userWSFCMXX")
    Flowable<RealAuthResp> completeAuth(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 绑定手机
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("user/phoneBind")
    Flowable<PhoneBindResp> phoneBind(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 修改密码
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("user/resetPwd")//user.resetPwd.do
    Flowable<ResetPwdResp> resetPwd(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 修改个人资料
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("user/updateUserInfo")
    Flowable<UpdateUserInfoResp> updateUserInfo(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 获取个人积分明细
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("user/getAccountDetail")
    Flowable<AccountScoreResp> getAccountScoreDetail(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 获取个人乐盈币明细
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("user/getAccountDetail")
    Flowable<AccountCoinsResp> getAccountCoinsDetail(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 判断账号下是否绑定手机或者邮箱
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("userUtil/isBand")
    Flowable<CheckBindResp> checkBind(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 校验昵称关键字
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("userUtil/keyWordFilter")
    Flowable<NicknameKeyWordResp> keyWordFilter(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 邮箱校验
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("userUtil/emailFilter")
    Flowable<EmailFilterResp> emailFilter(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 手机校验
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("userUtil/phoneFilter")
    Flowable<PhoneFilterResp> phoneFilter(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 用户签到
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("userPoint/userSign")
    Flowable<UserSignResp> userSign(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 用户每月签到
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("userPoint/queryMonthSign")
    Flowable<MonthSignResp> queryMonthSign(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 我的消息
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("userMsg/getMsgPage")
    Flowable<MsgListResp> getMsgPage(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 更新消息状态
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("userMsg/updateUserMsg")
    Flowable<MsgStateResp> updateUserMsg(@FieldMap(encoded = true) Map<String, String> params);

    /**
     *  消息详情
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("userMsg/getMsgInfo")
    Flowable<MsgInfoResp> getMsgInfo(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 发送邮件
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("user/sendEmail")
    Flowable<BaseResp> sendEmail(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 添加用户反馈建议
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("user/feedback")
    Flowable<FeedbackResp> commitFeedback(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 获取活动
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("hdzx/getActivity")
    Flowable<NotificationActivityResp> getNotificationActivity(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 第三方登录
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("thirdLogin/login")
    Flowable<LoginResp> thirdLogin(@FieldMap(encoded = true) Map<String, String> params);


    /**
     * 获取邀请码
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("pointShopUser/getInviteCode")
    Flowable<InviteCodeResp> getInviteCode(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 获取邀请码
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("user/addRecentlyPlayed")
    Flowable<BaseResp> addRecentlyPlayed(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 获取邀请码
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("userMsg/batchDelMsgByIds")
    Flowable<BaseResp> batchDelMsgByIds(@FieldMap(encoded = true) Map<String, String> params);
}



