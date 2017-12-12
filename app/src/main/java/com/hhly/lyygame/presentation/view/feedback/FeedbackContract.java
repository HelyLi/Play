package com.hhly.lyygame.presentation.view.feedback;

import com.hhly.lyygame.presentation.view.BaseLoadingView;
import com.hhly.lyygame.presentation.view.BasePresenter;
import com.hhly.lyygame.presentation.view.BaseView;

import java.util.List;

/**
 * 建议反馈
 * Created by Simon on 2016/12/10.
 */

interface FeedbackContract {

    interface Presenter extends BasePresenter {

        void uploadPicture(String picturePath);
        void commitInfo(String feedbackContent, List<String> pictureUrl, String contactInfo);
    }

    interface View extends BaseView<Presenter>, BaseLoadingView {
        void onUploadPictureFailure(String msg);
        void onUploadPictureSuccess(String pictureUrl);
        void onFeedbackSuccess();
        void onFeedbackFailure();
    }
}
