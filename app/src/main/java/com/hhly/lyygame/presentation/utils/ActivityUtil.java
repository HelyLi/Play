package com.hhly.lyygame.presentation.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.hhly.lyygame.data.db.manager.AccountManager;
import com.hhly.lyygame.presentation.view.account.LoginActivity;


/**
 * Created by Simon on 2016/11/24.
 */

public class ActivityUtil {

    public static void addFragment(FragmentManager fragmentManager, Fragment fragment, int containerId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(containerId, fragment);
        transaction.commit();
    }

//    private static String mToken;

//    public static void login() {
//
//        Flowable.just(AccountManager.getInstance().getToken())
//                .subscribeOn(Schedulers.io())
//                .filter(new Predicate<String>() {
//                    @Override
//                    public boolean test(@NonNull String token) throws Exception {
//                        return !TextUtils.isEmpty(token);
//                    }
//                })
//                .map(new Function<String, TokenLoginReq>() {
//                    @Override
//                    public TokenLoginReq apply(@NonNull String token) throws Exception {
//
//                        mToken = token;
//
//                        TokenLoginReq req = new TokenLoginReq();
//                        req.setToken(token);
//                        req.setDeviceId(Installation.id(App.getContext()));
//                        return req;
//                    }
//                })
//                .flatMap(new Function<TokenLoginReq, Publisher<TokenLoginResp>>() {
//                    @Override
//                    public Publisher<TokenLoginResp> apply(@NonNull TokenLoginReq req) throws Exception {
//                        return RetrofitManager.getInstance(ApiType.USER_API).getUserApi().tokenLogin(req.params());
//                    }
//                })
//                .filter(new Predicate<TokenLoginResp>() {
//                    @Override
//                    public boolean test(@NonNull TokenLoginResp loginResp) throws Exception {
//                        return loginResp.isOk();
//                    }
//                })
//                .map(new Function<TokenLoginResp, BaseReq>() {
//                    @Override
//                    public BaseReq apply(@NonNull TokenLoginResp loginResp) throws Exception {
//                        BaseReq req = new BaseReq();
//                        return req;
//                    }
//                })
//                .flatMap(new Function<BaseReq, Publisher<GetUserInfoResp>>() {
//                    @Override
//                    public Publisher<GetUserInfoResp> apply(@NonNull BaseReq req) throws Exception {
//                        return RetrofitManager.getInstance(ApiType.USER_API).getUserApi().getUserInfo(req.params());
//                    }
//                })
//                .filter(new Predicate<GetUserInfoResp>() {
//                    @Override
//                    public boolean test(@NonNull GetUserInfoResp getUserInfoResp) throws Exception {
//                        return getUserInfoResp != null && getUserInfoResp.isOk();
//                    }
//                })
//                .map(new Function<GetUserInfoResp, UserInfo>() {
//                    @Override
//                    public UserInfo apply(@NonNull GetUserInfoResp getUserInfoResp) throws Exception {
//                        UserInfo extra = getUserInfoResp.getUser();
//
//                        extra.setId(getUserInfoResp.getUser().getId());
//                        extra.setJf(getUserInfoResp.getJf());
//                        extra.setBindFlag(getUserInfoResp.getBindFlag());
//                        extra.setLyb(getUserInfoResp.getLyb());
//                        extra.setLyq(getUserInfoResp.getLyq());
//                        extra.setPaypwdFlag(getUserInfoResp.getPaypwdFlag());
//                        extra.setSafeLevel(getUserInfoResp.getSafeLevel());
//                        extra.setToken(mToken);
//                        return extra;
//                    }
//                })
//                .subscribe(new Consumer<UserInfo>() {
//                    @Override
//                    public void accept(@NonNull UserInfo info) throws Exception {
//                        AccountManager.getInstance().saveUserInfo(info);//
//                        StatisticalUtil.onProfileSignIn(info.getLoginType(), info.getUserId());
//                    }
//                });
//    }

    /**
     * @param context
     */
    public static void startLoginForRequest(Context context) {
        startLoginActivityForResult(context, LoginActivity.TYPE_REQUEST);
    }

    public static void startLoginForNormal(Context context) {
        startLoginActivity(context, LoginActivity.TYPE_NORMAL);
    }

    /**
     * 跳转登录页
     *
     * @param context
     * @param type    {@link LoginActivity}
     */
    public static void startLoginActivity(Context context, Integer type) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra(LoginActivity.EXTRA_TYPE, type);
        context.startActivity(intent);
    }

    /**
     * 跳转登录页
     *
     * @param context
     * @param type    {@link LoginActivity}
     */
    public static void startLoginActivityForResult(Context context, Integer type) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra(LoginActivity.EXTRA_TYPE, type);
        ((Activity) context).startActivityForResult(intent, 0);
    }

    /**
     * 检查是否已登录, 未登录就跳转登录界面，登录完成后不会跳转首页
     *
     * @param context
     * @return 已登录返回 true, 未登录返回false
     */
    public static boolean checkLoginAndRequestLogin(Context context) {
        if (!AccountManager.getInstance().isLogin()) {
            startLoginForRequest(context);
            return false;
        }
        return true;
    }

    /**
     * 检查是否已登录, 未登录就跳转登录界面,登录完成后会跳转首页
     *
     * @param context
     * @return 已登录返回 true, 未登录返回false
     */
    public static boolean checkLoginAndRequestLoginNormal(Context context) {
        if (!AccountManager.getInstance().isLogin()) {
            startLoginForNormal(context);
            return false;
        }
        return true;
    }

}
