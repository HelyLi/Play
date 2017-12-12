package com.hhly.lyygame.data.net;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 使用注解使代码更简洁优雅
 */
public class ApiType {

    /**
     * 多少种Host类型
     */
    public static final int TYPE_COUNT = 8;

    /**
     * BannerApi
     */
    public static final int BANNER_API = 1;

    /**
     * FileApi
     */
    public static final int FILE_API = 2;

    /**
     * GameApi
     */
    public static final int GAME_API = 3;

    /**
     * GoodsApi
     */
    public static final int GOODS_API = 4;

    /**
     * UpdateApi
     */
    public static final int UPDATE_API = 5;

    /**
     * UserApi
     */
    public static final int USER_API = 6;
    /**
     * OrderApi
     */
    public static final int PAY_API = 7;

    /**
     * TransferApi
     */
    public static final int TRANSFER_API = 8;
    
    /**
     * 替代枚举的方案，使用IntDef保证类型安全
     */
    @IntDef({BANNER_API, FILE_API, GAME_API, GOODS_API, UPDATE_API, USER_API , PAY_API, TRANSFER_API})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ApiTypeChecker {

    }

}
