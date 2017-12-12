package com.hhly.lyygame.presentation.view.score;

import com.hhly.lyygame.data.net.protocol.user.AccountScorePager;
import com.hhly.lyygame.presentation.view.BaseLoadingView;
import com.hhly.lyygame.presentation.view.BasePresenter;
import com.hhly.lyygame.presentation.view.BaseView;

import java.util.List;

/**
 * Created by ${HELY} on 17/3/4.
 * 邮箱：heli.lixiong@gmail.com
 */

public class ScoreContract {

    interface SRIncomePresenter extends BasePresenter{
        void getSRIncomeDetail(int pagerNo, int pagerSize);
    }

    interface SRIncomeView extends BaseView<SRIncomePresenter>, BaseLoadingView{
        void showSRIncomeDetail(List<AccountScorePager.AccountScoreBean> list, int totalPage);
        void onFailure();
    }

    interface SROutgoPresenter extends BasePresenter{
        void getSROutgoDetail(int pagerNo, int pagerSize);
    }

    interface SROutgoView extends BaseView<SROutgoPresenter>, BaseLoadingView{
        void showSROutgoDetail(List<AccountScorePager.AccountScoreBean> list, int totalPage);
        void onFailure();
    }

}
