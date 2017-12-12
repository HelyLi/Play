package com.hhly.lyygame.data.cache;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;
import com.hhly.lyygame.data.net.protocol.game.GameByIdInfoResp;
import com.hhly.lyygame.data.net.protocol.game.GameByModelIdResp;
import com.hhly.lyygame.data.net.protocol.game.GameByTypeResp;
import com.hhly.lyygame.data.net.protocol.game.RecentlyGameListResp;
import com.hhly.lyygame.data.repository.game.GameDataSource;
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

public class GameCacheImpl extends Cache implements GameDataSource {

    private static final String SETTINGS_FILE_NAME = "com.github.xmxu.app.movie.SETTINGS";
    private static final String SETTINGS_KEY_LAST_CACHE_UPDATE = "last_game_cache_update";

    private static final long EXPIRATION_TIME = 60 * 10 * 1000;

    private final Context context;
    private final File cacheDir;
    private final FileManager fileManager;

    public GameCacheImpl(Context context, FileManager fileManager) {
        this.context = context;
        this.cacheDir = this.context.getCacheDir();
        this.fileManager = fileManager;
    }

    public void put(@NonNull Map<String, String> params, @NonNull GameByModelIdResp entity) {
        File entityFile = buildFile(new MD5(Utils.getMapValue(params)).getMd5_16());
        String jsonString = mGson.toJson(entity, new TypeToken<GameByModelIdResp>() {
        }.getType());
        if (!TextUtils.isEmpty(jsonString)) {
            writeCache(fileManager, entityFile, jsonString);
//            setLastCacheUpdateTimeMillis();
        }
    }

    //
    public void put(@NonNull Map<String, String> params, @NonNull GameByTypeResp entity) {
        File entityFile = buildFile(new MD5(Utils.getMapValue(params)).getMd5_16());
        String jsonString = mGson.toJson(entity, new TypeToken<GameByTypeResp>() {
        }.getType());
        if (!TextUtils.isEmpty(jsonString)) {
            writeCache(fileManager, entityFile, jsonString);
//            setLastCacheUpdateTimeMillis();
        }
    }

    /**
     * 我玩过的
     *
     * @param entity
     */
    public void put(@NonNull Map<String, String> params, @NonNull RecentlyGameListResp entity) {
        String name = new MD5(Utils.getMapValue(params)).getMd5_16();
        File entityFile = buildFile(name);
        String jsonString = mGson.toJson(entity, new TypeToken<RecentlyGameListResp>() {
        }.getType());
        if (!TextUtils.isEmpty(jsonString)) {
            writeCache(fileManager, entityFile, jsonString);
            setLastCacheUpdateTimeMillis(name);
        }
    }

    public void put(@NonNull Map<String, String> params, @NonNull GameByIdInfoResp entity) {
        String name = new MD5(Utils.getMapValue(params)).getMd5_16();
        File entityFile = buildFile(name);
        String jsonString = mGson.toJson(entity, new TypeToken<GameByIdInfoResp>() {
        }.getType());
        if (!TextUtils.isEmpty(jsonString)) {
            writeCache(fileManager, entityFile, jsonString);
            setLastCacheUpdateTimeMillis(name);
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
    public Flowable<GameByModelIdResp> getIndexGameByModelId(final Map<String, String> params) {
        return Flowable.create(new FlowableOnSubscribe<GameByModelIdResp>() {
            @Override
            public void subscribe(FlowableEmitter<GameByModelIdResp> e) throws Exception {
                File entityFile = buildFile(new MD5(Utils.getMapValue(params)).getMd5_16());
                String fileContent = fileManager.readFileContent(entityFile);
                GameByModelIdResp resp = mGson.fromJson(fileContent, new TypeToken<GameByModelIdResp>() {
                }.getType());
                if (resp != null) {
                    e.onNext(resp);
                    e.onComplete();
                } else {
                    e.onNext(new GameByModelIdResp());
                    e.onComplete();
                }
            }
        }, BackpressureStrategy.DROP);
    }

    @Override
    public Flowable<GameByTypeResp> getGameByType(final Map<String, String> params) {
        return Flowable.create(new FlowableOnSubscribe<GameByTypeResp>() {
            @Override
            public void subscribe(FlowableEmitter<GameByTypeResp> e) throws Exception {
                File entityFile = buildFile(new MD5(Utils.getMapValue(params)).getMd5_16());
                String fileContent = fileManager.readFileContent(entityFile);
                GameByTypeResp resp = mGson.fromJson(fileContent, new TypeToken<GameByTypeResp>() {
                }.getType());
                if (resp != null) {
                    e.onNext(resp);
                    e.onComplete();
                } else {
                    e.onNext(new GameByTypeResp());
                    e.onComplete();
                }
            }
        }, BackpressureStrategy.DROP);
    }

    @Override
    public Flowable<RecentlyGameListResp> getMyGameList(final Map<String, String> params) {
        return Flowable.create(new FlowableOnSubscribe<RecentlyGameListResp>() {
            @Override
            public void subscribe(FlowableEmitter<RecentlyGameListResp> e) throws Exception {
                File entityFile = buildFile(new MD5(Utils.getMapValue(params)).getMd5_16());
                String fileContent = fileManager.readFileContent(entityFile);
                RecentlyGameListResp resp = mGson.fromJson(fileContent, new TypeToken<RecentlyGameListResp>() {
                }.getType());
                if (resp != null) {
                    e.onNext(resp);
                    e.onComplete();
                } else {
                    e.onNext(new RecentlyGameListResp());
                    e.onComplete();
                }
            }
        }, BackpressureStrategy.DROP);
    }

    @Override
    public Flowable<GameByIdInfoResp> getGameById(final Map<String, String> params) {
        return Flowable.create(new FlowableOnSubscribe<GameByIdInfoResp>() {
            @Override
            public void subscribe(FlowableEmitter<GameByIdInfoResp> e) throws Exception {
                File entityFile = buildFile(new MD5(Utils.getMapValue(params)).getMd5_16());
                String fileContent = fileManager.readFileContent(entityFile);
                GameByIdInfoResp resp = mGson.fromJson(fileContent, new TypeToken<GameByIdInfoResp>() {
                }.getType());
                if (resp != null) {
                    e.onNext(resp);
                    e.onComplete();
                } else {
                    e.onNext(new GameByIdInfoResp());
                    e.onComplete();
                }
            }
        }, BackpressureStrategy.DROP);
    }

    /**
     * @return true 过期 false 为过期
     */
    @Override
    public boolean isExpiration(Map<String, String> params) {
        return getLastCacheUpdateTimeMillis(new MD5(Utils.getMapValue(params)).getMd5_16()) + EXPIRATION_TIME < System.currentTimeMillis();
    }

}
