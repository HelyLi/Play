package com.hhly.lyygame.presentation.view.info;

import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.data.db.entry.UserInfo;
import com.hhly.lyygame.data.db.manager.AccountManager;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.FileApi;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.UserApi;
import com.hhly.lyygame.data.net.client.BaseSubscriber;
import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.file.ImageUploadResp;
import com.hhly.lyygame.data.net.protocol.user.NicknameKeyWordReq;
import com.hhly.lyygame.data.net.protocol.user.NicknameKeyWordResp;
import com.hhly.lyygame.data.net.protocol.user.UpdateUserInfoReq;
import com.hhly.lyygame.data.net.protocol.user.UpdateUserInfoResp;
import com.hhly.lyygame.presentation.utils.Compressor;
import com.hhly.lyygame.presentation.utils.FileUtil;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by ${HELY} on 17/2/8.
 * 邮箱：heli.lixiong@gmail.com
 */

public class InfoPresenter implements InfoContract.Presenter {

    private final InfoContract.View mView;
    private final UserApi mUserApi;
    private final FileApi mFileApi;

    public InfoPresenter(InfoContract.View view) {
        this.mView = view;
        mUserApi = RetrofitManager.getInstance(ApiType.USER_API).getUserApi();
        mFileApi = RetrofitManager.getInstance(ApiType.FILE_API).getFileApi();
        mView.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
    }

    @Override
    public void updateUserInfo(final int index, final UpdateUserInfoReq.UpdateUserInfo updateUserInfo) {
        final UpdateUserInfoReq req = new UpdateUserInfoReq(updateUserInfo);

        Logger.d("req=" + req.toString());
        mUserApi.updateUserInfo(req.params())
                .compose(RxUtil.<UpdateUserInfoResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<UpdateUserInfoResp>bindToLife())
                .compose(RetrofitManager.<UpdateUserInfoResp>composeBackpressureError())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<UpdateUserInfoResp>() {
                    @Override
                    public boolean test(@NonNull UpdateUserInfoResp updateUserInfoResp) throws Exception {
                        mView.dismissLoading();
                        return updateUserInfoResp != null && updateUserInfoResp.isOk();
                    }
                })
                .subscribeOn(Schedulers.io())
                .map(new Function<UpdateUserInfoResp, UserInfo>() {
                    @Override
                    public UserInfo apply(@NonNull UpdateUserInfoResp updateUserInfoResp) throws Exception {
                        UserInfo info = updateUserInfoResp.getUser();

                        info.setToken(AccountManager.getInstance().getToken());

                        Logger.d("token=" + AccountManager.getInstance().getToken());
                        AccountManager.getInstance().saveUserInfo(info);
                        return info;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<UserInfo>() {
                    @Override
                    protected void hideDialog() {
                        mView.dismissLoading();
                    }

                    @Override
                    protected void showDialog() {
                        mView.showLoading();
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {

                    }

                    @Override
                    public void onNext(UserInfo info) {
                        mView.dismissLoading();
                        mView.updateSuccess(index, info);
                    }
                });
    }

    @Override
    public void uploadImage(final String imagePath) {

        File file = new File(imagePath);

        if (file != null && file.exists()) {

            Logger.d("Before Compress : " + file.length() / 1024 + " k");
            if (FileUtil.isImageFormat(file) && needCompress(file)) {
                try {
                    file = Compressor.compress(file);
                    Logger.d("After Compress : " + file.length() / 1024 + " k");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("imageFile", file.getName(), requestBody);

            mFileApi.uploadImage(part)
                    .compose(RxUtil.<ImageUploadResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                    .compose(mView.<ImageUploadResp>bindToLife())
                    .compose(RetrofitManager.<ImageUploadResp>composeBackpressureError())
                    .subscribe(new BaseSubscriber<ImageUploadResp>() {
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
                        public void onNext(ImageUploadResp imageUploadResp) {
                            if (imageUploadResp != null && imageUploadResp.isOk()) {
                                mView.imageUrl(imageUploadResp.getImageUrl(), imagePath);
                            }
                        }
                    });
        }

    }

    @Override
    public void keyWordFilter(final int index, final UpdateUserInfoReq.UpdateUserInfo updateUserInfo) {
        final NicknameKeyWordReq nicknameKeyWordReq = new NicknameKeyWordReq();
        nicknameKeyWordReq.setNickname(updateUserInfo.getNickname());
        mUserApi.keyWordFilter(nicknameKeyWordReq.params())
                .compose(mView.<NicknameKeyWordResp>bindToLife())
                .compose(RetrofitManager.<NicknameKeyWordResp>composeBackpressureError())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<NicknameKeyWordResp>() {
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
                    public void onNext(NicknameKeyWordResp nicknameKeyWordResp) {
                        if (nicknameKeyWordResp.isOk()) {
                            mView.keyWordFilterSuccess(index, updateUserInfo);
                        } else {
                            mView.keyWordFilterFailure(nicknameKeyWordResp.getMsg());
                        }
                    }
                });
    }

    /**
     * @param file
     * @return
     */
    private boolean needCompress(File file) {
        return file != null && file.exists() && file.length() >= 1024 * 1000;
    }

}
