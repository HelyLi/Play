package com.hhly.lyygame.data.repository.game;

import com.hhly.lyygame.data.cache.GameCacheImpl;
import com.hhly.lyygame.data.net.protocol.game.GameByIdInfoResp;
import com.hhly.lyygame.data.net.protocol.game.GameByModelIdResp;
import com.hhly.lyygame.data.net.protocol.game.GameByTypeResp;
import com.hhly.lyygame.data.net.protocol.game.RecentlyGameListResp;

import java.util.Map;

import io.reactivex.Flowable;


/**
 * 游戏本地数据
 * Created by Simon on 2016/12/9.
 */

public class GameLocalDataSource implements GameDataSource {

    private final GameCacheImpl mGameCache;

    public GameLocalDataSource(GameCacheImpl gameCache) {
        mGameCache = gameCache;
    }

    @Override
    public Flowable<GameByModelIdResp> getIndexGameByModelId(Map<String, String> params) {
        return mGameCache.getIndexGameByModelId(params);
    }

    @Override
    public Flowable<GameByTypeResp> getGameByType(Map<String, String> params) {
        return mGameCache.getGameByType(params);
    }

    @Override
    public Flowable<RecentlyGameListResp> getMyGameList(Map<String, String> params) {
        return mGameCache.getMyGameList(params);
    }

    @Override
    public Flowable<GameByIdInfoResp> getGameById(Map<String, String> params) {
        return mGameCache.getGameById(params);
    }

    @Override
    public boolean isExpiration(Map<String, String> params) {
        return mGameCache.isExpiration(params);
    }

}
