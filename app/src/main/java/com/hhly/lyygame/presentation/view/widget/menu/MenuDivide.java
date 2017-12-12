package com.hhly.lyygame.presentation.view.widget.menu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hhly.lyygame.R;

/**
 * Created by Simon on 2016/11/23.
 */

public class MenuDivide extends RecyclerView.ItemDecoration {

    private int mGroupDivideHeight;
    private Drawable mGroupDivide;
    private int mItemDivideHeight;
    private Drawable mItemDivide;

    private static final int[] ATTRS = new int[]{R.attr.listMenuGroupDivide, R.attr.listMenuGroupDivideHeight,
            R.attr.listMenuItemDivide, R.attr.listMenuItemDivideHeight};

    public MenuDivide(Context context) {
        TypedArray ta = context.obtainStyledAttributes(ATTRS);

        mGroupDivide = ta.getDrawable(0);
        mItemDivide = ta.getDrawable(2);

        mGroupDivideHeight = ta.getDimensionPixelSize(1, 0);
        mItemDivideHeight = ta.getDimensionPixelSize(3, 0);

        ta.recycle();

    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        MenuAdapter adapter = (MenuAdapter) parent.getAdapter();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getTop() + params.topMargin;
            if (adapter.isFirstItemOfGroup(parent.getChildAdapterPosition(child))) {
                final int bottom = top + mGroupDivideHeight;
                mGroupDivide.setBounds(left, top, right, bottom);
                mGroupDivide.draw(c);
            } else {
                final int bottom = top + mItemDivideHeight;
                mItemDivide.setBounds(left, top, right, bottom);
                mItemDivide.draw(c);
            }
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        if (position == 0) {
            return;
        }
        RecyclerView.Adapter adapter = parent.getAdapter();
        if (adapter instanceof MenuAdapter) {
            MenuAdapter menuAdapter = (MenuAdapter) adapter;
            boolean isFirstItem = menuAdapter.isFirstItemOfGroup(position);
            if (isFirstItem) {
                outRect.set(0, mGroupDivideHeight, 0, 0);
            } else {
//                outRect.set(0, mItemDivideHeight, 0, 0);
                outRect.set(0, 0, 0, 0);
            }
        }
    }
}
