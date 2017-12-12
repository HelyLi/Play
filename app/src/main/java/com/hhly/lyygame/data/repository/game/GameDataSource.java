package com.hhly.lyygame.data.repository.game;

import com.hhly.lyygame.data.net.protocol.game.GameByIdInfoResp;
import com.hhly.lyygame.data.net.protocol.game.GameByModelIdResp;
import com.hhly.lyygame.data.net.protocol.game.GameByTypeResp;
import com.hhly.lyygame.data.net.protocol.game.RecentlyGameListResp;

import java.util.Map;

import io.reactivex.Flowable;


/**
 * 游戏仓库数据源接口
 * Created by Simon on 2016/12/9.
 */

public interface GameDataSource {

    Flowable<GameByModelIdResp> getIndexGameByModelId(Map<String, String> params);

    Flowable<GameByTypeResp> getGameByType(Map<String, String> params);

    Flowable<RecentlyGameListResp> getMyGameList(Map<String, String> params);

    Flowable<GameByIdInfoResp> getGameById(Map<String, String> params);

    boolean isExpiration(Map<String, String> params);
}
