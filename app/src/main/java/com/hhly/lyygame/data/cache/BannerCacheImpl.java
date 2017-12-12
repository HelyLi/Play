package com.hhly.lyygame.data.cache;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;
import com.hhly.lyygame.data.net.protocol.banner.GoodsBannerResp;
import com.hhly.lyygame.data.net.protocol.banner.HomeBannerResp;
import com.hhly.lyygame.data.repository.banner.BannerDataSource;
import com.hhly.lyygame.presentation.utils.MD5;
import com.hhly.lyygame.presentation.utils.Utils;

import java.io.File;
import java.util.Map;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;

/**
 * 简单实现Banner数据本地缓存，使用文件保存
 * Created by Simon on 2016/11/3.
 */

public class BannerCacheImpl extends Cache implements BannerDataSource {

    private static final String SETTINGS_FILE_NAME = "com.github.xmxu.app.movie.SETTINGS";
    private static final String SETTINGS_KEY_LAST_HOME_CACHE_UPDATE = "last_home_banner_cache_update";
    private static final String SETTINGS_KEY_LAST_GOODS_CACHE_UPDATE = "last_goods_banner_cache_update";

    private static final long EXPIRATION_TIME = 60 * 10 * 1000;

    private final Context context;
    private final File cacheDir;
    private final FileManager fileManager;

    public BannerCacheImpl(Context context, FileManager fileManager) {
        this.context = context;
        this.cacheDir = this.context.getCacheDir();
        this.fileManager = fileManager;
    }

    public void put(@NonNull Map<String, String> params, @NonNull HomeBannerResp entity) {
        if (entity != null) {
            String name = new MD5(Utils.getMapValue(params)).getMd5_16();
            File entityFile = buildFile(name);
            String jsonString = mGson.toJson(entity, new TypeToken<HomeBannerResp>() {
            }.getType());
            if (!TextUtils.isEmpty(jsonString)) {
                writeCache(fileManager, entityFile, jsonString);
                setLastCacheUpdateTimeMillis(name);
            }
        }
    }

    public void put(@NonNull Map<String, String> params, GoodsBannerResp entity) {
        if (entity != null) {
            String name = new MD5(Utils.getMapValue(params)).getMd5_16();
            File entityFile = buildFile(name);
            String jsonString = mGson.toJson(entity, new TypeToken<GoodsBannerResp>() {
            }.getType());
            if (!TextUtils.isEmpty(jsonString)) {
                writeCache(fileManager, entityFile, jsonString);
                setLastCacheUpdateTimeMillis(name);
            }
        }
    }

    private File buildFile(String filename) {
        StringBuilder fileNameBuilder = new StringBuilder();
        fileNameBuilder.append(this.cacheDir.getPath());
        fileNameBuilder.append(File.separator);
        fileNameBuilder.append(filename);

        return new File(fileNameBuilder.toString());
    }

    /**
     * Set in millis, the last time the cache was accessed.
     */
    private void setLastCacheUpdateTimeMillis(String keyName) {
        long currentMillis = System.currentTimeMillis();
        this.fileManager.writeToPreferences(this.context, SETTINGS_FILE_NAME,
                keyName, currentMillis);
    }

    /**
     * Get in millis, the last time the cache was accessed.
     */
    private long getLastCacheUpdateTimeMillis(String keyName) {
        return this.fileManager.getFromPreferences(this.context, SETTINGS_FILE_NAME,
                keyName);
    }

    @Override
    public Flowable<HomeBannerResp> homeBanner(final Map<String, String> params) {
        return Flowable.create(new FlowableOnSubscribe<HomeBannerResp>() {
            @Override
            public void subscribe(FlowableEmitter<HomeBannerResp> e) throws Exception {
                File entityFile = buildFile(new MD5(Utils.getMapValue(params)).getMd5_16());
                String fileContent = fileManager.readFileContent(entityFile);
                HomeBannerResp subjectList = mGson.fromJson(fileContent, new TypeToken<HomeBannerResp>() {
                }.getType());
                if (subjectList != null) {
                    e.onNext(subjectList);
                    e.onComplete();
                } else {
                    e.onNext(new HomeBannerResp());
                    e.onComplete();
                }
            }
        }, BackpressureStrategy.DROP);
    }

    @Override
    public Flowable<GoodsBannerResp> goodsBanner(final Map<String, String> params) {

        return Flowable.create(new FlowableOnSubscribe<GoodsBannerResp>() {
            @Override
            public void subscribe(FlowableEmitter<GoodsBannerResp> e) throws Exception {
                File entityFile = buildFile(new MD5(Utils.getMapValue(params)).getMd5_16());
                String fileContent = fileManager.readFileContent(entityFile);
                GoodsBannerResp subjectList = mGson.fromJson(fileContent, new TypeToken<GoodsBannerResp>() {
                }.getType());
                if (subjectList != null) {
                    e.onNext(subjectList);
                    e.onComplete();
                } else {
                    e.onNext(new GoodsBannerResp());
                    e.onComplete();
                }
            }
        }, BackpressureStrategy.DROP);
    }

    @Override
    public boolean isExpiration(Map<String, String> params) {
        String keyName = new MD5(Utils.getMapValue(params)).getMd5_16();
        return getLastCacheUpdateTimeMillis(keyName) + EXPIRATION_TIME < System.currentTimeMillis();
    }

}


