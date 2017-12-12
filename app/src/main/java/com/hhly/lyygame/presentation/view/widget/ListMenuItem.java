package com.hhly.lyygame.presentation.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hhly.lyygame.R;

/**
 * Created by Simon on 2016/11/23.
 */

public class ListMenuItem extends FrameLayout {

    public ListMenuItem(Context context) {
        super(context);
    }

    public ListMenuItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(attrs);
    }

    public ListMenuItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(attrs);
    }

    private void initialize(AttributeSet attrs) {
        inflate(getContext(), R.layout.widget_me_menu_item_layout, this);

        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.ListMenuItem);
        int menuIconResId = ta.getResourceId(R.styleable.ListMenuItem_menuIcon, 0);
        int menuTitleResId = ta.getResourceId(R.styleable.ListMenuItem_menuTitle, 0);
        ta.recycle();

        TextView titleTv = (TextView) findViewById(R.id.menu_item_name_tv);
        titleTv.setText(menuTitleResId);

        ImageView iconIv = (ImageView) findViewById(R.id.menu_item_iv);
        iconIv.setImageResource(menuIconResId);


    }

}
