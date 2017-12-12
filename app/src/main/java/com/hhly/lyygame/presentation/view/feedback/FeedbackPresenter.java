package com.hhly.lyygame.presentation.view.feedback;

import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.FileApi;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.UserApi;
import com.hhly.lyygame.data.net.client.BaseSubscriber;
import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.BaseResp;
import com.hhly.lyygame.data.net.protocol.file.ImageUploadResp;
import com.hhly.lyygame.data.net.protocol.user.FeedbackReq;
import com.hhly.lyygame.presentation.utils.Compressor;
import com.hhly.lyygame.presentation.utils.FileUtil;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 建议反馈
 * Created by Simon on 2016/12/10.
 */

class FeedbackPresenter implements FeedbackContract.Presenter {

    private final FeedbackContract.View mView;
    private final UserApi mUserApi;
    private final FileApi mFileApi;

    FeedbackPresenter(FeedbackContract.View view) {
        mView = view;
        mView.setPresenter(this);
        mUserApi = RetrofitManager.getInstance(ApiType.USER_API).getUserApi();
        mFileApi = RetrofitManager.getInstance(ApiType.FILE_API).getFileApi();
    }

    @Override
    public void uploadPicture(String imagePath) {
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
                    //.compose(RetrofitManager.<ImageUploadResp>composeBackpressureError())
                    .subscribe(new BaseSubscriber<ImageUploadResp>() {
                        @Override
                        protected void hideDialog() {

                        }

                        @Override
                        protected void showDialog() {

                        }

                        @Override
                        public void onError(ExceptionHandle.ResponeThrowable e) {
                            mView.onUploadPictureFailure(e.message);
                        }

                        @Override
                        public void onNext(ImageUploadResp imageUploadResp) {
                            if (imageUploadResp != null && imageUploadResp.isOk()) {
                                mView.onUploadPictureSuccess(imageUploadResp.getImageUrl());
                            }else {
                                mView.onUploadPictureFailure(imageUploadResp.getMsg());
                            }
                        }
                    });
        }

    }

    @Override
    public void commitInfo(String feedbackContent, List<String> pictureUrl, String contactInfo) {
        FeedbackReq req = new FeedbackReq();
        req.setCountry(0);
        req.setContent(feedbackContent);
        req.setContact(contactInfo);
        req.setImages(pictureUrl);

        mUserApi.commitFeedback(req.params())
                .compose(RxUtil.<BaseResp>applySchedulers(RxUtil.IO_ON_UI_TRANSFORMER_BACK_PRESSURE))
                .compose(mView.<BaseResp>bindToLife())
                //.compose(RetrofitManager.<BaseResp>composeBackpressureError())
                .subscribe(new BaseSubscriber<BaseResp>() {
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
                        mView.onFeedbackFailure();
                    }

                    @Override
                    public void onNext(BaseResp baseResp) {
                        mView.dismissLoading();
                        if (baseResp != null && baseResp.isOk()){
                            mView.onFeedbackSuccess();
                        }else {
                            mView.onFeedbackFailure();
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

    /**
     * @param file
     * @return
     */
    private boolean needCompress(File file) {
        return file != null && file.exists() && file.length() >= 1024 * 1000;
    }
}
