package com.hhly.lyygame.data.repository;

import com.hhly.lyygame.App;
import com.hhly.lyygame.data.cache.FileManager;
import com.hhly.lyygame.data.cache.GameCacheImpl;
import com.hhly.lyygame.data.net.protocol.game.GameByIdInfoResp;
import com.hhly.lyygame.data.net.protocol.game.GameByModelIdResp;
import com.hhly.lyygame.data.net.protocol.game.GameByTypeResp;
import com.hhly.lyygame.data.net.protocol.game.RecentlyGameListResp;
import com.hhly.lyygame.data.repository.game.GameDataSource;
import com.hhly.lyygame.data.repository.game.GameLocalDataSource;
import com.hhly.lyygame.data.repository.game.GameRemoteDataSource;
import com.hhly.lyygame.presentation.utils.NetworkUtil;

import java.util.Map;

import io.reactivex.Flowable;


/**
 * 游戏数据仓库
 * Created by Simon on 2016/12/9.
 */

public class GameRepository implements GameDataSource {

    private final GameDataSource cache;
    private final GameDataSource remote;
    private GameCacheImpl mGameCache;
    
    public GameRepository() {
        mGameCache = new GameCacheImpl(App.getContext(), new FileManager());
        cache = new GameLocalDataSource(mGameCache);
        remote = new GameRemoteDataSource(mGameCache);
    }

    @Override
    public Flowable<GameByModelIdResp> getIndexGameByModelId(final Map<String, String> params) {
        if (NetworkUtil.isAvailable(App.getContext())) {
            return remote.getIndexGameByModelId(params);
        }
        return cache.getIndexGameByModelId(params);
    }

    @Override
    public Flowable<GameByTypeResp> getGameByType(Map<String, String> params) {

        if (NetworkUtil.isAvailable(App.getContext())) {
            return remote.getGameByType(params);
        }
        return cache.getGameByType(params);
    }

    /**
     * 获取玩过的游戏需要及时性
     * @param params
     * @return
     */
    @Override
    public Flowable<RecentlyGameListResp> getMyGameList(Map<String, String> params) {
        if (NetworkUtil.isAvailable(App.getContext())) {
            return remote.getMyGameList(params);
        }
        return cache.getMyGameList(params);
    }

    @Override
    public Flowable<GameByIdInfoResp> getGameById(Map<String, String> params) {
        if (NetworkUtil.isAvailable(App.getContext())) {
            return remote.getGameById(params);
        }
        return cache.getGameById(params);
    }

    @Override
    public boolean isExpiration(Map<String, String> params) {
        return false;
    }

}
