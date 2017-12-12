package com.hhly.lyygame.presentation.view.transfer;

import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.TransferApi;
import com.hhly.lyygame.data.net.client.BaseSubscriber;
import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.transfer.TransferGameListReq;
import com.hhly.lyygame.data.net.protocol.transfer.TransferGameListResp;
import com.hhly.lyygame.data.net.protocol.user.State;
import com.hhly.lyygame.presentation.utils.CollectionUtil;
import com.hhly.lyygame.presentation.utils.TelephonyUtil;

import java.util.Iterator;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ${HELY} on 17/4/24.
 * 邮箱：heli.lixiong@gmail.com
 */

public class TransferPresenter implements TransferContract.Presenter{

    private final TransferApi mTransferApi;
    private final TransferContract.View mView;

    public TransferPresenter(TransferContract.View view){
        mTransferApi = RetrofitManager.getInstance(ApiType.TRANSFER_API).getTransferApi();
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void transGameList(final String drawType) {
        final TransferGameListReq req = new TransferGameListReq();
        req.setTerminal(String.valueOf(TelephonyUtil.getOsTypeInt()));
        req.setDrawType(drawType);
        
        mTransferApi.transGameList(req.params())
                .compose(RxUtil.<TransferGameListResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<TransferGameListResp>bindToLife())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<TransferGameListResp>() {
                    @Override
                    public boolean test(@NonNull TransferGameListResp resp) throws Exception {
                        if (resp == null || !resp.isOk()){
                            mView.showFailure();
                        }
                        return resp != null && resp.isOk();
                    }
                })
                .observeOn(Schedulers.newThread())
                .map(new Function<TransferGameListResp, List<TransferGameListResp.TransferGameInfo>>() {
                    @Override
                    public List<TransferGameListResp.TransferGameInfo> apply(@NonNull TransferGameListResp transferGameListResp) throws Exception {

                        List<TransferGameListResp.TransferGameInfo> list = transferGameListResp.getPlatformGameList();

                        if (drawType.equals("2")){
                            TransferGameListResp.TransferGameInfo info = new TransferGameListResp.TransferGameInfo();
                            info.setName("乐盈币(中心钱包)");
                            info.setId(1);
                            info.setPlatformTerminal(State.PlatformTerminalType.ANDROID);
                            list.add(0, info);
                        }

                        Iterator<TransferGameListResp.TransferGameInfo> iterator = list.iterator();
                        while (iterator.hasNext()){
                            TransferGameListResp.TransferGameInfo item = iterator.next();

                            if (item.getPlatformTerminal() != State.PlatformTerminalType.ANDROID
                                    && item.getPlatformTerminal() != State.PlatformTerminalType.H5){
                                iterator.remove();
                            }
                        }

                        return list;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<List<TransferGameListResp.TransferGameInfo>>() {
            @Override
            protected void hideDialog() {

            }

            @Override
            protected void showDialog() {

            }

            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                mView.showFailure();
            }

            @Override
            public void onNext(List<TransferGameListResp.TransferGameInfo> transferGameInfoList) {
                if (CollectionUtil.isEmpty(transferGameInfoList)){
                    mView.onEmptyView();
                }else {
                    mView.showTransGameList(transferGameInfoList);
                }
            }
        });

    }

}
