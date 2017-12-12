package com.hhly.lyygame.presentation.view.score;

import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.UserApi;
import com.hhly.lyygame.data.net.client.BaseSubscriber;
import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.user.AccountRechargeDetailReq;
import com.hhly.lyygame.data.net.protocol.user.AccountScorePager;
import com.hhly.lyygame.data.net.protocol.user.AccountScoreResp;
import com.hhly.lyygame.presentation.utils.CollectionUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by ${HELY} on 17/3/4.
 * 邮箱：heli.lixiong@gmail.com
 */

public class SRIncomePresenterImpl implements ScoreContract.SRIncomePresenter {

    private final ScoreContract.SRIncomeView mView;
    private final UserApi mUserApi;

    public SRIncomePresenterImpl(ScoreContract.SRIncomeView view) {
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

    private volatile int totalPage = 0;

    @Override
    public void getSRIncomeDetail(int pagerNo, int pagerSize) {

        AccountRechargeDetailReq req = new AccountRechargeDetailReq();
        req.setPageNo(pagerNo);
        req.setPageSize(pagerSize);
        req.setType(3);

        mUserApi.getAccountScoreDetail(req.params())
                .compose(RxUtil.<AccountScoreResp>applySchedulers(RxUtil.IO_TRANSFORMER_BACK_PRESSURE))
//                .compose(RetrofitManager.<AccountScoreResp>composeBackpressureOther())
                .compose(RetrofitManager.<AccountScoreResp>composeBackpressureError())
                .compose(mView.<AccountScoreResp>bindToLife())
                .map(new Function<AccountScoreResp, List<AccountScorePager.AccountScoreBean>>() {
                    @Override
                    public List<AccountScorePager.AccountScoreBean> apply(@NonNull AccountScoreResp accountRechargeDetailResp) throws Exception {
                        List<AccountScorePager.AccountScoreBean> list = new ArrayList<>();
                        if (accountRechargeDetailResp != null && accountRechargeDetailResp.isOk() && accountRechargeDetailResp.getPage() != null && CollectionUtil.isNotEmpty(accountRechargeDetailResp.getPage().getList())) {

                            totalPage = accountRechargeDetailResp.getPage().getTotalPages();
                            for (AccountScorePager.AccountScoreBean bean : accountRechargeDetailResp.getPage().getList()) {
                                if (bean.getTradeType() == 0) {
                                    list.add(bean);
                                }
                            }
                        }
                        return list;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<List<AccountScorePager.AccountScoreBean>>() {
                    @Override
                    protected void hideDialog() {

                    }

                    @Override
                    protected void showDialog() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        mView.onFailure();
                    }

                    @Override
                    public void onNext(List<AccountScorePager.AccountScoreBean> rechargeBeanList) {
                        mView.showSRIncomeDetail(rechargeBeanList, totalPage);
                    }
                });

    }
}
