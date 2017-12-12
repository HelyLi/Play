package com.hhly.lyygame.presentation.view.game;

import com.hhly.lyygame.data.net.protocol.game.GameByIdInfoResp;
import com.hhly.lyygame.data.net.protocol.game.GameNoticeResp;
import com.hhly.lyygame.data.net.protocol.game.GamePictureInfoResp;
import com.hhly.lyygame.data.net.protocol.game.GiftBagResp;
import com.hhly.lyygame.presentation.view.BaseLoadingView;
import com.hhly.lyygame.presentation.view.BasePresenter;
import com.hhly.lyygame.presentation.view.BaseView;

import java.util.List;

/**
 * Created by ${HELY} on 17/2/16.
 * 邮箱：heli.lixiong@gmail.com
 */

public class GameContract {

    interface DetailPresenter extends BasePresenter {
        void getGamePic(int gameId, int imageType);
        void getGameByIdInfo(int gameId);
    }

    interface DetailView extends BaseView<DetailPresenter>, BaseLoadingView {
        void showGamePic(List<GamePictureInfoResp.GamePicPager.GamePic> gamePicList);
        void showGameInfo(GameByIdInfoResp.GameByIdInfo gameBean);
    }

    interface IntroPresenter extends BasePresenter {
        void getGameByIdInfo(int gameId);
//        void getGameType(int gameType);
    }

    interface IntrolView extends BaseView<IntroPresenter>, BaseLoadingView {
        void showGameInfo(GameByIdInfoResp.GameByIdInfo gameBean);
    }

    interface NoticePresenter extends BasePresenter {
        void getGameNotice(int gameId, int noticeType);
    }

    interface NoticeView extends BaseView<NoticePresenter>, BaseLoadingView {
        void showGameNotice(List<GameNoticeResp.GameNoticePage.GameNotice> gameNoticeList);
    }

    interface GiftPresenter extends BasePresenter {
        void getGameGift(int gameId);
    }

    interface GiftView extends BaseView<GiftPresenter>, BaseLoadingView {
        void showGameGift(List<GiftBagResp.GiftBagBean>  giftBagBeanList);
        void onFailureGift();
    }

}
