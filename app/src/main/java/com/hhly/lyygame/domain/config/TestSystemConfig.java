package com.hhly.lyygame.domain.config;

import android.content.Context;

import com.hhly.lyygame.data.net.ApiType;

import java.io.File;

/**
 * 测试环境配置
 */

class TestSystemConfig extends AbsSystemConfig {

    TestSystemConfig(Context context) {
        super(context);
    }

    @Override
    public String getApiBaseUrl() {
        return "http://mgame.1332255.com/api/";
    }

    @Override
    public String getFileBaseUrl() {
        return "http://file.13322.com/upload/";
    }

    @Override
    public String getPathName() {
        return super.getPathName() + "_test";
    }

    @Override
    public String getImageCachePath() {
        return getPath() + File.separator + "image";
    }

    @Override
    public boolean isTest() {
        return true;
    }

    @Override
    public String getHost(@ApiType.ApiTypeChecker int apiType) {
        String host;
        switch (apiType) {
            case ApiType.BANNER_API:
            case ApiType.GAME_API:
            case ApiType.GOODS_API:
            case ApiType.UPDATE_API:
            case ApiType.USER_API:
                host = getApiBaseUrl();
                break;
            case ApiType.FILE_API:
                host = getFileBaseUrl();
                break;
            default:
                host = "";
                break;
        }
        return host;
    }

    @Override
    public String getPayUrl() {
        return "http://mgame.1332255.com/pay.html";
    }
}
