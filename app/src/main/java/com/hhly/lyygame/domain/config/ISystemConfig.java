package com.hhly.lyygame.domain.config;

/**
 * Created by Simon on 2016/11/18.
 */

interface ISystemConfig {

    String getApiBaseUrl();

    String getFileBaseUrl();

    String getPath();

    String getPathName();

    String getImageCachePath();

    boolean isTest();

    String getHost(int hostType);

    String getPayUrl();

}
