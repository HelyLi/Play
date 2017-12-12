package com.hhly.lyygame.data.db.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.hhly.lyygame.App;
import com.hhly.lyygame.data.db.entry.UserInfo;


/**
 * Created by ${HELY} on 16/12/23.
 * 邮箱：heli.lixiong@gmail.com
 */

public class AccountManager {

    private volatile UserInfo mCurrentUserInfo = null;
    private static final String NAME = "lyy_account";
    private static final String KEY_TOKEN_ID = "key_token_id";

    private static AccountManager sInstance = null;
    private static SharedPreferences mPreferences = null;

    private AccountManager() {
        mPreferences = App.getContext().getSharedPreferences(NAME, Context.MODE_APPEND);
    }

    public static AccountManager getInstance() {
        if (sInstance == null) {
            synchronized (AccountManager.class) {
                if (sInstance == null) {
                    sInstance = new AccountManager();
                }
            }
        }
        return sInstance;
    }

    public String getUserId() {
        return mCurrentUserInfo == null ? "" : mCurrentUserInfo.getUserId();
    }

    public String getToken() {
        return mCurrentUserInfo == null ? mPreferences.getString(KEY_TOKEN_ID, "") : mCurrentUserInfo.getToken();
    }

    public UserInfo getUserInfo() {
        return mCurrentUserInfo;
    }

    public boolean isLogin() {
        return mCurrentUserInfo != null;
    }

    public void clearCurrentUserInfo() {
        mCurrentUserInfo = null;
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(KEY_TOKEN_ID, "");
        editor.apply();
    }

    public void saveUserInfo(UserInfo info) {
        if (info != null) {
            synchronized (this) {
                UserInfo tmpInfo = UserInfoOpe.queryUnique(info.getToken());
                if (tmpInfo != null) {
                    info.setId(tmpInfo.getId());
                    if (TextUtils.isEmpty(info.getPhone())) {
                        info.setPhone(tmpInfo.getPhone());
                    }
                }
                mCurrentUserInfo = info;
                SharedPreferences.Editor editor = mPreferences.edit();
                editor.putString(KEY_TOKEN_ID, mCurrentUserInfo.getToken());
                editor.apply();
                //
                UserInfoOpe.saveData(mCurrentUserInfo);
            }
        }
    }

//    public void saveExtraUserInfo(ExtraUserInfo info) {
//        if (info != null) {
//            synchronized (this) {
//                mCurrentExtraUserInfo = info;
//                ExtraUserInfoOpe.saveData(info);
//            }
//        }
//    }

}
