package com.hhly.lyygame.presentation.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hhly.lyygame.presentation.utils.DisplayUtil;


/**
 * Created by freeman on 15/12/14.
 */
public class RecyclerViewListFirstDivide extends RecyclerView.ItemDecoration {

    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;

    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

    private int mOrientation;

    private Drawable mDrawable;
    private int height;

    public RecyclerViewListFirstDivide (Context context) {
        super();
        height = DisplayUtil.dip2px(context, 8);
        mDrawable = new ColorDrawable(Color.TRANSPARENT);
    }
    public RecyclerViewListFirstDivide (Context context, int orientation, int height) {
        super();
        this.height = DisplayUtil.dip2px(context, height);
        mDrawable = new ColorDrawable(Color.TRANSPARENT);
        mOrientation = orientation;
    }

    public RecyclerViewListFirstDivide (Context context, @ColorInt int color, int orientation, int height) {
        super();
        this.height = DisplayUtil.dip2px(context, height);
        mOrientation = orientation;
        mDrawable = new ColorDrawable(color);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getChildAdapterPosition(view) == 0) {
            if (mOrientation == VERTICAL_LIST) {
                outRect.set(0, height, 0, 0);
            } else {
                outRect.set(height, 0, 0, 0);
            }
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (mOrientation == VERTICAL_LIST) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }


    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        View child = parent.getChildAt(0);
        if (child != null) {
            final int left = child.getPaddingLeft();
            final int right = child.getWidth() - child.getPaddingRight();
            final int top = child.getTop() - height;
            final int bottom = child.getTop();
            mDrawable.setBounds(left, top, right, bottom);
            mDrawable.draw(c);
        }
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        View child = parent.getChildAt(0);
        if (child != null) {
            final int left = child.getLeft() - height;
            final int right = child.getLeft();
            final int top = child.getTop();
            final int bottom = child.getBottom();
            mDrawable.setBounds(left, top, right, bottom);
            mDrawable.draw(c);
        }
    }
}
