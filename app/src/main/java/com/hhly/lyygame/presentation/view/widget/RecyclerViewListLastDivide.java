package com.hhly.lyygame.presentation.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hhly.lyygame.presentation.utils.DisplayUtil;


/**
 * Created by yuhui.zeng on 2015/12/15.
 */
public class RecyclerViewListLastDivide extends RecyclerView.ItemDecoration{ private Drawable mDrawable;
    private int height;

    public RecyclerViewListLastDivide (Context context) {
        super();
        height = DisplayUtil.dip2px(context, 8);
        mDrawable = new ColorDrawable(Color.TRANSPARENT);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getChildAdapterPosition(view) == parent.getChildCount()-1) {
            outRect.set(0, 0, 0, height);
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        View child = parent.getChildAt(parent.getChildCount()-1);
        if (child != null) {
            final int left = child.getPaddingLeft();
            final int right = child.getWidth() - child.getPaddingRight();
            final int bottom = child.getBottom()+height;
            final int top = child.getBottom();
            mDrawable.setBounds(left, top, right, bottom);
            mDrawable.draw(c);
        }

    }
}