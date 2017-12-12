package com.hhly.lyygame.presentation.view.paylist;

import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.UserApi;
import com.hhly.lyygame.data.net.client.BaseSubscriber;
import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.user.AccountCoinsPager;
import com.hhly.lyygame.data.net.protocol.user.AccountCoinsResp;
import com.hhly.lyygame.data.net.protocol.user.AccountRechargeDetailReq;
import com.hhly.lyygame.presentation.utils.CollectionUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by ${HELY} on 17/1/18.
 * 邮箱：heli.lixiong@gmail.com
 */

public class PayListPresenter implements PayListContact.Persenter {

    PayListContact.View mView;
    UserApi mUserApi;
    private volatile int totalPage = 0;

    public PayListPresenter(PayListContact.View view) {
        this.mView = view;
        mUserApi = RetrofitManager.getInstance(ApiType.USER_API).getUserApi();
        this.mView.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
    }

    @Override
    public void getAccountRechargeDetail(int pagerNo, int pagerSize, final int type) {
        AccountRechargeDetailReq req = new AccountRechargeDetailReq();
        req.setPageNo(pagerNo);
        req.setPageSize(pagerSize);
        req.setType(type);

        mUserApi.getAccountCoinsDetail(req.params())
                .compose(RxUtil.<AccountCoinsResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(RetrofitManager.<AccountCoinsResp>composeBackpressureOther())
                .compose(RetrofitManager.<AccountCoinsResp>composeBackpressureError())
                .compose(mView.<AccountCoinsResp>bindToLife())
                .map(new Function<AccountCoinsResp, List<AccountCoinsPager.AccountCoinsBean>>() {
                    @Override
                    public List<AccountCoinsPager.AccountCoinsBean> apply(@NonNull AccountCoinsResp accountRechargeDetailResp) throws Exception {
                        List<AccountCoinsPager.AccountCoinsBean> beanList = new ArrayList<>();
                        if (accountRechargeDetailResp != null && accountRechargeDetailResp.isOk() && accountRechargeDetailResp.getPage() != null && CollectionUtil.isNotEmpty(accountRechargeDetailResp.getPage().getList())) {
                            totalPage = accountRechargeDetailResp.getPage().getTotalPages();
                            List<AccountCoinsPager.AccountCoinsBean> respList = accountRechargeDetailResp.getPage().getList();
                            for (AccountCoinsPager.AccountCoinsBean bean : respList) {
                                if (type == PayListFragment.RECHARGE_CATEGORY) {//充值
//                                    if (bean.getTRADETYPE() == 0) {
                                        beanList.add(bean);
//                                    }
                                } else
                                    if (type == PayListFragment.CONSUME_CATEGORY) {//消费
                                    if (bean.getCHANGETYPE() == 2) {
                                        beanList.add(bean);
                                    }
                                }
                            }
                        }
                        return beanList;
                    }
                })
                .subscribe(new BaseSubscriber<List<AccountCoinsPager.AccountCoinsBean>>() {
                    @Override
                    protected void hideDialog() {

                    }

                    @Override
                    protected void showDialog() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        mView.getAccountRechargeDetailFailure();
                    }

                    @Override
                    public void onNext(List<AccountCoinsPager.AccountCoinsBean> rechargeBeanList) {
                        mView.showAccountRechargeDetail(rechargeBeanList, totalPage);
                    }
                });
    }
}
