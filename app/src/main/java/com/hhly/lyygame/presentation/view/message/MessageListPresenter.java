package com.hhly.lyygame.presentation.view.message;

import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.UserApi;
import com.hhly.lyygame.data.net.client.BaseSubscriber;
import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.BaseResp;
import com.hhly.lyygame.data.net.protocol.user.BatchDelMsgReq;
import com.hhly.lyygame.data.net.protocol.user.MsgListReq;
import com.hhly.lyygame.data.net.protocol.user.MsgListResp;
import com.hhly.lyygame.presentation.utils.CollectionUtil;
import com.hhly.lyygame.presentation.utils.TelephonyUtil;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Predicate;

/**
 * 消息列表
 * Created by Simon on 2016/12/10.
 */

class MessageListPresenter implements MessageListContract.Presenter {

    private final MessageListContract.View mView;
    private final UserApi mUserApi;

    MessageListPresenter(MessageListContract.View view) {
        mView = view;
        mView.setPresenter(this);
        mUserApi = RetrofitManager.getInstance(ApiType.USER_API).getUserApi();
    }

    @Override
    public void loadMessage(int msgType,final int pageNo, int pageSize) {
        MsgListReq req = new MsgListReq();
        req.setPageNo(pageNo);
        req.setPageSize(pageSize);
        req.setMsgType(msgType);
        req.setPlateFormId(TelephonyUtil.getOsTypeInt());

        mUserApi.getMsgPage(req.params())
                .compose(RxUtil.<MsgListResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(RetrofitManager.<MsgListResp>composeBackpressureError())
                .compose(mView.<MsgListResp>bindToLife())
                .filter(new Predicate<MsgListResp>() {
                    @Override
                    public boolean test(@NonNull MsgListResp resp) throws Exception {

                        if (resp == null || !resp.isOk()){
                            mView.onFailure();
                        }else if (pageNo == 1 && (resp.getPager() == null || CollectionUtil.isEmpty(resp.getPager().getList()) )){
                            mView.onEmptyView();
                        }
                        return resp != null && resp.isOk() && resp.getPager() != null && CollectionUtil.isNotEmpty(resp.getPager().getList());
                    }
                })
                .subscribe(new BaseSubscriber<MsgListResp>() {
                    @Override
                    protected void hideDialog() {

                    }

                    @Override
                    protected void showDialog() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        mView.showMsg(e.message);
                        mView.onFailure();
                    }

                    @Override
                    public void onNext(MsgListResp resp) {
                            mView.showMessages(resp.getPager().getList(), resp.getPager().getTotalPages());
                        }
                });

    }

    @Override
    public void deleteMessage(int msgId,final int position) {
        BatchDelMsgReq req = new BatchDelMsgReq();
        req.setPlatformTerminal(String.valueOf(TelephonyUtil.getOsTypeInt()));
        req.setMsgIds(String.valueOf(msgId));

        mUserApi.batchDelMsgByIds(req.params())
                .compose(RxUtil.<BaseResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<BaseResp>bindToLife())
                .subscribe(new BaseSubscriber<BaseResp>() {
                    @Override
                    protected void hideDialog() {

                    }

                    @Override
                    protected void showDialog() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {

                    }

                    @Override
                    public void onNext(BaseResp baseResp) {
                        if (baseResp != null && baseResp.isOk()){
                            mView.deleteSuccess(position);
                        }
                    }
                });
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
}
