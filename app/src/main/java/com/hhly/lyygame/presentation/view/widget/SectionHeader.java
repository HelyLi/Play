package com.hhly.lyygame.presentation.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hhly.lyygame.R;

/**
 * Created by Simon on 2016/11/24.
 */

public class SectionHeader extends LinearLayout implements View.OnClickListener {

    private ImageView mIconIv;
    private TextView mTitleTv;
    private TextView mMoreTv;

    private boolean isEnableMore = true;

    public SectionHeader(Context context) {
        super(context);
        initialize();
    }

    public SectionHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
        initAttrs(attrs);
    }

    public SectionHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
        initAttrs(attrs);
    }

    private void initialize() {
        inflate(getContext(), R.layout.widget_section_header_layout, this);
        setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, getResources().getDimensionPixelSize(R.dimen.section_height)));
        setBackgroundColor(getResources().getColor(R.color.color_fff));
        setOrientation(HORIZONTAL);

        mIconIv = (ImageView) findViewById(R.id.section_iv);
        mTitleTv = (TextView) findViewById(R.id.section_title_tv);
        mMoreTv = (TextView) findViewById(R.id.section_more_tv);

    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.SectionHeader);

        int iconResId = ta.getResourceId(R.styleable.SectionHeader_sectionIcon, 0);
        int titleResId = ta.getResourceId(R.styleable.SectionHeader_sectionTitle, 0);
        isEnableMore = ta.getBoolean(R.styleable.SectionHeader_sectionMore, true);
        ta.recycle();
        if (iconResId != 0) {
            setIcon(iconResId);
        }
        if (titleResId != 0) {
            setTitle(titleResId);
        }
        updateMore();
    }

    public void setIcon(@DrawableRes int resId) {
        mIconIv.setImageResource(resId);
    }

    public void setIcon(String url) {
        Glide.with(getContext()).load(url).asBitmap().centerCrop().into(mIconIv);
    }

    public void setTitle(@StringRes int resId) {
        mTitleTv.setText(resId);
    }

    public void setTitle(String title) {
        mTitleTv.setText(title);
    }

    public void enableMore(boolean enable) {
        isEnableMore = enable;
        updateMore();
    }

    private void updateMore() {
        if (isEnableMore) {
            mMoreTv.setVisibility(VISIBLE);
            mMoreTv.setOnClickListener(this);
        } else {
            mMoreTv.setVisibility(GONE);
        }
    }

    @Override
    public void onClick(View v) {
        if (mSectionMoreListener != null) {
            v.setTag(R.id.item_data_key, getTag(R.id.item_data_key));
            mSectionMoreListener.onClickMore(v);
        }
    }

    private OnSectionMoreListener mSectionMoreListener;

    public void setSectionMoreListener(OnSectionMoreListener sectionMoreListener) {
        mSectionMoreListener = sectionMoreListener;
    }

    public interface OnSectionMoreListener {
        void onClickMore(View view);
    }
}
