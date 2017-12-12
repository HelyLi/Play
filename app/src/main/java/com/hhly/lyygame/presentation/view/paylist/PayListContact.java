package com.hhly.lyygame.presentation.view.paylist;

import com.hhly.lyygame.data.net.protocol.user.AccountCoinsPager;
import com.hhly.lyygame.presentation.view.BaseLoadingView;
import com.hhly.lyygame.presentation.view.BasePresenter;
import com.hhly.lyygame.presentation.view.BaseView;

import java.util.List;

/**
 * Created by ${HELY} on 17/1/18.
 * 邮箱：heli.lixiong@gmail.com
 */

public interface PayListContact {

    interface Persenter extends BasePresenter{
        void getAccountRechargeDetail(int pagerNo, int pagerSize, int type);
    }

    interface View extends BaseView<PayListContact.Persenter>, BaseLoadingView {
        void showAccountRechargeDetail(List<AccountCoinsPager.AccountCoinsBean> rechargeBeanList, int totalPages);
        void getAccountRechargeDetailFailure();
    }

}
