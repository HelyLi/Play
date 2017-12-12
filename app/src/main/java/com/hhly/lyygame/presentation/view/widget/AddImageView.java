package com.hhly.lyygame.presentation.view.widget;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.utils.DisplayUtil;

/**
 * 添加图片＋删除
 */

public class AddImageView implements View.OnClickListener {

    private Context mContext;
    private LayoutInflater mInflater;
    private View mContentView;
    private ImageView mPicIv;
    private ImageView mDeleteIv;
    private String mCurrentPath;
    private OnAddImageOptListener mAddImageOptListener;

    public AddImageView(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        initialize();
    }

    private void initialize() {
        mContentView = mInflater.inflate(R.layout.widget_feeddback_add_layout, null, false);
        mPicIv = (ImageView) mContentView.findViewById(R.id.item_iv);
        mPicIv.setOnClickListener(this);
        mDeleteIv = (ImageView) mContentView.findViewById(R.id.item_delete_iv);
        mDeleteIv.setOnClickListener(this);
        reset();
    }

    public View getContentView() {
        return mContentView;
    }

    public void addInto(ViewGroup group) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(DisplayUtil.dip2px(mContext, 80),
                DisplayUtil.dip2px(mContext, 80));
        lp.leftMargin = DisplayUtil.dip2px(mContext, 10);
        group.addView(mContentView, Math.max(group.getChildCount() - 1, 0), lp);
    }

    public void addInto(ViewGroup group, int index) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(DisplayUtil.dip2px(mContext, 80),
                DisplayUtil.dip2px(mContext, 80));
        lp.leftMargin = DisplayUtil.dip2px(mContext, 10);
        group.addView(mContentView, index, lp);
    }

    public void setAddImageOptListener(OnAddImageOptListener addImageOptListener) {
        mAddImageOptListener = addImageOptListener;
    }

    public void setImage(String path) {
        if (TextUtils.equals(path, mCurrentPath)) {
            return;
        }
        if (!TextUtils.isEmpty(path)) {
            Glide.with(mContext).load(path)
                    .centerCrop()
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            if (TextUtils.isEmpty(mCurrentPath)) {
                                //reset ImageView
                                reset();
                            }
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            mCurrentPath = model;
                            switchDeleteStatus();
                            return false;
                        }
                    })
                    .into(mPicIv);
        }
    }

    private void reset() {
        mCurrentPath = null;
        mDeleteIv.setVisibility(View.INVISIBLE);
        mPicIv.setBackgroundResource(R.drawable.ic_feedback_add);
        mPicIv.setImageResource(0);
    }

    private void switchDeleteStatus() {
        mPicIv.setBackgroundResource(0);
        mDeleteIv.setVisibility(View.VISIBLE);
    }

    public String getCurrentPath() {
        return mCurrentPath;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_iv:
                if (TextUtils.isEmpty(mCurrentPath)) {
                    //can select pic
                    if (mAddImageOptListener != null) {
                        mAddImageOptListener.onClickSelect();
                    }
                }
                break;
            case R.id.item_delete_iv:
                //delete pic
                if (mAddImageOptListener != null) {
                    mAddImageOptListener.onClickDelete(mCurrentPath);
                }
                reset();
                removeViewFromParent();
                break;
        }
    }

    private void removeViewFromParent() {
        ViewParent parent = mContentView.getParent();
        if (parent != null && parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(mContentView);
        }
    }

    public interface OnAddImageOptListener {
        void onClickSelect();
        void onClickDelete(String path);
    }
}
