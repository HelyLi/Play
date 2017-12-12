package com.hhly.lyygame.presentation.view.info;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.view.ViewGroup;

import com.hhly.lyygame.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 图片选择对话框
 * Created by Simon on 2016/11/26.
 */

public class InfoPictureSelectDialog extends BottomSheetDialog {

    private OnPictureSelectListener mPictureSelectListener;

    public InfoPictureSelectDialog(@NonNull Context context) {
        super(context);
    }

    public InfoPictureSelectDialog(@NonNull Context context, OnPictureSelectListener selectListener) {
        super(context);
        mPictureSelectListener = selectListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_photo_layout);
        ButterKnife.bind(this);
        getWindow().setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    }

    @OnClick(R.id.camera_tv)
    void onCameraClick(View view) {
        if (mPictureSelectListener != null) {
            mPictureSelectListener.onClickCamera();
        }
        dismiss();
    }

    @OnClick(R.id.album_tv)
    void onAlbumClick(View view) {
        if (mPictureSelectListener != null) {
            mPictureSelectListener.onClickAlbum();
        }
        dismiss();
    }

    @OnClick(R.id.cancel_tv)
    void onCancelClick(View view) {
        dismiss();
    }

    public interface OnPictureSelectListener {
        void onClickCamera();
        void onClickAlbum();
    }
}
