package com.hhly.lyygame.presentation.view.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.utils.CollectionUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${HELY} on 17/3/3.
 * 邮箱：heli.lixiong@gmail.com
 */

public class SearchRecordTagAdapter extends TagAdapter<String> {

    private static List<String> mList = new ArrayList<String>();

    private final LayoutInflater mInflater;

    public SearchRecordTagAdapter(Context context) {
        super(mList);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(FlowLayout parent, int position, String record) {

        View view = mInflater.inflate(R.layout.widget_search_record_item_layout, parent, false);
        TextView textView = (TextView) view.findViewById(R.id.search_recent_label_tv);
        textView.setText(record);
        if (mRecordListener != null) {
            textView.setOnClickListener(mRecordListener);
        }
        return view;
    }

    public void setNewData(List data) {
        if (CollectionUtil.isEmpty(data)) return;
        mList.clear();
        mList.addAll(data);
        notifyDataChanged();
    }

    private View.OnClickListener mRecordListener;

    public void setRecordListener(View.OnClickListener listener) {
        mRecordListener = listener;
    }

}
