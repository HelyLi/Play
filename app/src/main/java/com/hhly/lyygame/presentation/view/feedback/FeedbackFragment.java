package com.hhly.lyygame.presentation.view.feedback;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.DialogFactory;
import com.hhly.lyygame.presentation.view.ToastUtil;
import com.hhly.lyygame.presentation.view.info.InfoPictureSelectDialog;
import com.hhly.lyygame.presentation.view.widget.AddImageView;
import com.miguelbcr.ui.rx_paparazzo2.RxPaparazzo;
import com.miguelbcr.ui.rx_paparazzo2.entities.FileData;
import com.miguelbcr.ui.rx_paparazzo2.entities.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;

/**
 * 建议反馈
 * Created by Simon on 2016/12/2.
 */

public class FeedbackFragment extends BaseFragment implements AddImageView.OnAddImageOptListener,
        InfoPictureSelectDialog.OnPictureSelectListener, FeedbackContract.View {

    @BindView(R.id.feedback_et)
    EditText mFeedbackEt;
    @BindView(R.id.feedback_count_hint_tv)
    TextView mFeedbackCountHintTv;
    @BindView(R.id.feedback_pic_root)
    LinearLayout mFeedbackPicRoot;
    @BindView(R.id.feedback_contact_et)
    EditText mFeedbackContactEt;
    @BindView(R.id.feedback_save_btn)
    Button mFeedbackSaveBtn;

    private FeedbackContract.Presenter mPresenter;

    private List<String> mCurrentPathList;
    private List<String> mImagesUrlList;

    public static FeedbackFragment newInstance() {
        return new FeedbackFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCurrentPathList = new ArrayList<>(3);
        mImagesUrlList = new ArrayList<>();
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {

        AddImageView addImageView = new AddImageView(getActivity());
        addImageView.addInto(mFeedbackPicRoot);
        addImageView.setAddImageOptListener(this);
        mFeedbackEt.addTextChangedListener(mTextWatcher);
        mFeedbackContactEt.addTextChangedListener(mTextWatcher);
    }

    @Override
    protected void fetchData(boolean isLoadMore) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_feedback_layout;
    }

    @OnTextChanged(value = R.id.feedback_et, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void onFeedbackChanged(Editable editable) {
        int length = editable.toString().trim().length();
        mFeedbackCountHintTv.setText(getString(R.string.lyy_feedback_count_hint_format, length, 150));
    }

    @OnClick(R.id.feedback_save_btn)
    public void onClick() {
        mPresenter.commitInfo(mFeedbackEt.getText().toString().trim(), mImagesUrlList, mFeedbackContactEt.getText().toString().trim());
    }

    @Override
    public void onClickSelect() {
        DialogFactory.createSelectPhoto(getActivity(), this).show();
    }

    @Override
    public void onClickDelete(String path) {
        mCurrentPathList.remove(path);
        if (mCurrentPathList.size() == 2) {
            addEmptySelectPictureView();
        }
    }

    private void addEmptySelectPictureView() {
        AddImageView addImageView = new AddImageView(getActivity());
        addImageView.setAddImageOptListener(FeedbackFragment.this);
        addImageView.addInto(mFeedbackPicRoot, mFeedbackPicRoot.getChildCount());
    }

    @Override
    public void onClickCamera() {

        RxPaparazzo.single(FeedbackFragment.this)
                .usingCamera()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<FeedbackFragment, FileData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<FeedbackFragment, FileData> feedbackFragmentFileDataResponse) {
                        handlePictureCallback(feedbackFragmentFileDataResponse);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



    /**
     * 相册
     */
    @Override
    public void onClickAlbum() {

        RxPaparazzo.single(FeedbackFragment.this)
                .usingGallery()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<FeedbackFragment, FileData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<FeedbackFragment, FileData> feedbackFragmentFileDataResponse) {
                        handlePictureCallback(feedbackFragmentFileDataResponse);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void handlePictureCallback(Response<FeedbackFragment, FileData> feedbackFragmentStringResponse) {
        if (feedbackFragmentStringResponse.resultCode() == RESULT_OK) {
            if (!mCurrentPathList.contains(feedbackFragmentStringResponse.data())) {
                mCurrentPathList.add(feedbackFragmentStringResponse.data().getFile().getAbsolutePath());
                //此处处理上传图片
                mPresenter.uploadPicture(feedbackFragmentStringResponse.data().getFile().getAbsolutePath());
                AddImageView addImageView = new AddImageView(getActivity());
                addImageView.setAddImageOptListener(FeedbackFragment.this);
                addImageView.addInto(mFeedbackPicRoot);
                addImageView.setImage(feedbackFragmentStringResponse.data().getFile().getAbsolutePath());
                if (mCurrentPathList.size() >= 3) {
                    mFeedbackPicRoot.removeViewAt(3);
                }
            }
        }
    }

    @Override
    public void setPresenter(FeedbackContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.subscribe();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void onUploadPictureFailure(String msg) {

    }

    @Override
    public void onUploadPictureSuccess(String pictureUrl) {
        if (TextUtils.isEmpty(pictureUrl))return;
        mImagesUrlList.add(pictureUrl);
    }

    @Override
    public void onFeedbackSuccess() {
        ToastUtil.showTip(getActivity(), "感谢你的反馈");
        clearFeedback();
        onBackPressed();
    }

    private void clearFeedback(){
        mFeedbackEt.getText().clear();
        mFeedbackContactEt.getText().clear();
        mCurrentPathList.clear();
        mImagesUrlList.clear();
        mFeedbackPicRoot.removeAllViews();
        addEmptySelectPictureView();
    }

    @Override
    public void onFeedbackFailure() {

    }

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            checkInputState();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void checkInputState () {
        String content = mFeedbackEt.getText().toString().trim();
        String contact = mFeedbackContactEt.getText().toString().trim();

        if (TextUtils.isEmpty(content) || TextUtils.isEmpty(contact)) {
            mFeedbackSaveBtn.setEnabled(false);
            return;
        }
        mFeedbackSaveBtn.setEnabled(true);
    }

}
