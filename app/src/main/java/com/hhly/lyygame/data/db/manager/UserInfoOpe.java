package com.hhly.lyygame.data.db.manager;

import android.text.TextUtils;

import com.hhly.lyygame.data.db.entry.UserInfo;
import com.hhly.lyygame.data.db.entry.UserInfoDao;
import com.hhly.lyygame.presentation.utils.CollectionUtil;

import java.util.List;

/**
 * Created by ${HELY} on 16/12/27.
 * 邮箱：heli.lixiong@gmail.com
 */

public class UserInfoOpe {

    /**
     *
     * @param user
     */
    public static void insertData(UserInfo user){
        DaoManager.getDaoSession().getUserInfoDao().insert(user);
    }

    /**
     * 将数据实体通过事务添加至数据库
     * @param list
     */
    public static void insertData(List<UserInfo> list){
        if (CollectionUtil.isEmpty(list)){
            return;
        }
        DaoManager.getDaoSession().getUserInfoDao().insertInTx(list);
    }

    /**
     * 添加数据至数据库，如果存在，将原来的数据覆盖
     * @param info
     */
    public static void saveData(UserInfo info){
        DaoManager.getDaoSession().getUserInfoDao().save(info);
    }

    /**
     * 查询所有数据
     * @return
     */
    public static List<UserInfo> queryAll(){
        return DaoManager.getDaoSession().getUserInfoDao().queryBuilder().build().list();
    }

    /**
     *
     * @param token
     * @return
     */
    public static UserInfo queryUnique(String token){
        return DaoManager.getDaoSession().getUserInfoDao().queryBuilder().where(UserInfoDao.Properties.Token.eq(token)).build().unique();
    }

    /**
     *
     * @return
     */
    public static List<UserInfo> queryOrderLastLoginTime(){
        return DaoManager.getDaoSession().getUserInfoDao().queryBuilder().orderDesc(UserInfoDao.Properties.LastLoginTime).build().list();
    }

    public static UserInfo getUserInfoSync(String token) {
        UserInfo info = null;
        if (!TextUtils.isEmpty(token)){
            info= UserInfoOpe.queryUnique(token);
        }
        if (info == null) {
            List<UserInfo> userInfos = UserInfoOpe.queryOrderLastLoginTime();
            if (CollectionUtil.isNotEmpty(userInfos)) {
                //取第一个作为当前帐号
                info = userInfos.get(0);
            }
        }
        return info;
    }

}
