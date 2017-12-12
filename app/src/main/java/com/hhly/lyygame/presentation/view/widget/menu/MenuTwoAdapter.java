package com.hhly.lyygame.presentation.view.widget.menu;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.utils.CollectionUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Simon on 2016/11/24.
 */

public class MenuTwoAdapter extends RecyclerView.Adapter<MenuTwoAdapter.TwoMenuViewHolder> {

    private List<MenuTwoGroup> mMenuGroups;
    private LayoutInflater mInflater = null;
    private int[] mPositionOfGroup;
    private int[] mPositionWithGroup;
    private boolean[] mFirstWithinGroup;

    private final Handler mHandler = new Handler(Looper.getMainLooper());

    public MenuTwoAdapter() {
        mMenuGroups = new ArrayList<>();
    }

    public MenuTwoAdapter(List<MenuTwoGroup> dataList) {
        mMenuGroups = new ArrayList<>(dataList);
    }

    private RecyclerView.AdapterDataObserver mDataObserver = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            dealWithMenuCount();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            super.onItemRangeChanged(positionStart, itemCount);
            dealWithMenuCount();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            super.onItemRangeChanged(positionStart, itemCount, payload);
            dealWithMenuCount();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            super.onItemRangeInserted(positionStart, itemCount);
            dealWithMenuCount();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            super.onItemRangeRemoved(positionStart, itemCount);
            dealWithMenuCount();
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            super.onItemRangeMoved(fromPosition, toPosition, itemCount);
            dealWithMenuCount();
        }
    };

    @Override
    public TwoMenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(parent.getContext());
        }
        return new TwoMenuViewHolder(mInflater.inflate(R.layout.widget_me_menu_two_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(TwoMenuViewHolder holder, int position) {
        final MenuTwoItem item = getItem(position);
        final MenuItem leftItem = item.getFisrtItem();
        final MenuItem rightItem = item.getSecondItem();

        if (leftItem != null){
            holder.mMenuLeftItemIv.setImageResource(leftItem.getIcon());
            holder.mMenuLeftItemNameTv.setText(leftItem.getTitle());
            holder.mMenuLeftItem.setTag(R.id.recyclerViewLeftDataKey, leftItem);
            holder.mMenuLeftItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MenuItem menuItem = (MenuItem) v.getTag(R.id.recyclerViewLeftDataKey);
                    mHandler.post(menuItem.getAction());
                }
            });
        }else {
            holder.mMenuLeftItem.setVisibility(View.INVISIBLE);
        }

        if (rightItem != null){
            holder.mMenuRightItemIv.setImageResource(rightItem.getIcon());
            holder.mMenuRightItemNameTv.setText(rightItem.getTitle());
            holder.mMenuRightItem.setTag(R.id.recyclerViewRightDataKey, rightItem);
            holder.mMenuRightItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MenuItem menuItem = (MenuItem) v.getTag(R.id.recyclerViewRightDataKey);
                    mHandler.post(menuItem.getAction());
                }
            });
        }else {
            holder.mMenuRightItem.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        if (mDataObserver != null) {
            registerAdapterDataObserver(mDataObserver);
        }
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        if (mDataObserver != null) {
            unregisterAdapterDataObserver(mDataObserver);
        }
    }

    private void dealWithMenuCount() {
        mPositionOfGroup = new int[getItemCount()];
        mFirstWithinGroup = new boolean[getItemCount()];
        mPositionWithGroup = new int[getItemCount()];
        int pos = 0;
        int groupIndex = 0;
        for (MenuTwoGroup group : mMenuGroups) {
            List<MenuTwoItem> menuItemList = group.getChildList();
            for (int i = 0; i < menuItemList.size(); i++) {
                if(i == 0) {
                    mFirstWithinGroup[pos] = true;
                } else {
                    mFirstWithinGroup[pos] = false;
                }
                mPositionWithGroup[pos] = i;
                mPositionOfGroup[pos] = groupIndex;
                pos++;
            }
            groupIndex++;
        }
    }

    @Override
    public int getItemCount() {
        if (CollectionUtil.isEmpty(mMenuGroups)) {
            return 0;
        }
        int size = 0;
        for (MenuTwoGroup group : mMenuGroups) {
            if (CollectionUtil.isEmpty(group.getChildList())) {
                continue;
            }
            size += group.getChildList().size();
        }
        return size;
    }

    public MenuTwoItem getItem(int position) {
        int groupIndex = mPositionOfGroup[position];
        int indexWithinGroup = mPositionWithGroup[position];
        return mMenuGroups.get(groupIndex).getChildList().get(indexWithinGroup);
    }

    public boolean isFirstItemOfGroup(int position) {
        return mFirstWithinGroup[position];
    }

    static class TwoMenuViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.menu_left_item)
        RelativeLayout mMenuLeftItem;
        @BindView(R.id.menu_left_item_iv)
        ImageView mMenuLeftItemIv;
        @BindView(R.id.menu_left_item_name_tv)
        TextView mMenuLeftItemNameTv;
        @BindView(R.id.menu_right_item)
        RelativeLayout mMenuRightItem;
        @BindView(R.id.menu_right_item_iv)
        ImageView mMenuRightItemIv;
        @BindView(R.id.menu_right_item_name_tv)
        TextView mMenuRightItemNameTv;

        TwoMenuViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
