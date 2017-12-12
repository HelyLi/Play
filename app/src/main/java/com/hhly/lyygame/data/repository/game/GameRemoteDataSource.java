package com.hhly.lyygame.data.repository.game;

import com.hhly.lyygame.data.cache.GameCacheImpl;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.GameApi;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.protocol.game.GameByIdInfoResp;
import com.hhly.lyygame.data.net.protocol.game.GameByModelIdResp;
import com.hhly.lyygame.data.net.protocol.game.GameByTypeResp;
import com.hhly.lyygame.data.net.protocol.game.RecentlyGameListResp;

import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * 游戏远程数据
 * Created by Simon on 2016/12/9.
 */

public class GameRemoteDataSource implements GameDataSource {

    private GameApi mGameApi;
    private final GameCacheImpl mGameCache;

    public GameRemoteDataSource(GameCacheImpl gameCache) {
        mGameCache = gameCache;
        mGameApi = RetrofitManager.getInstance(ApiType.GAME_API).getGameApi();
    }

    @Override
    public Flowable<GameByModelIdResp> getIndexGameByModelId(final Map<String, String> params) {
        return mGameApi.getIndexGameByModelId(params)
                .doOnNext(new Consumer<GameByModelIdResp>() {
                    @Override
                    public void accept(@NonNull GameByModelIdResp boutiqueGameListResp) throws Exception {
                        if (mGameCache != null && boutiqueGameListResp != null) {
                            mGameCache.put(params, boutiqueGameListResp);
                        }
                    }
                });
    }

    @Override
    public Flowable<GameByTypeResp> getGameByType(final Map<String, String> params) {

        return mGameApi.getGameByType(params)
                .doOnNext(new Consumer<GameByTypeResp>() {
                    @Override
                    public void accept(@NonNull GameByTypeResp gameByTypeResp) throws Exception {
                        if (mGameCache != null && gameByTypeResp != null){
                            mGameCache.put(params, gameByTypeResp);
                        }
                    }
                });
    }

    @Override
    public Flowable<RecentlyGameListResp> getMyGameList(final Map<String, String> params) {
        return mGameApi.getRecentlyGameList(params)
                .doOnNext(new Consumer<RecentlyGameListResp>() {
                    @Override
                    public void accept(@NonNull RecentlyGameListResp recentlyGameListResp) throws Exception {
                        if (mGameCache != null && recentlyGameListResp != null){
                            mGameCache.put(params, recentlyGameListResp);
                        }
                    }
                });
    }

    @Override
    public Flowable<GameByIdInfoResp> getGameById(final Map<String, String> params) {
        return mGameApi.getGameById(params)
                .doOnNext(new Consumer<GameByIdInfoResp>() {
                    @Override
                    public void accept(@NonNull GameByIdInfoResp gameByIdInfoResp) throws Exception {
                        if (mGameCache != null && gameByIdInfoResp != null){
                            mGameCache.put(params, gameByIdInfoResp);
                        }
                    }
                });
    }

    @Override
    public boolean isExpiration(Map<String, String> params) {
        return false;
    }

}
