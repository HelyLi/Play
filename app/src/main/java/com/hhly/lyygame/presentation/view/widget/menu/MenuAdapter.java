package com.hhly.lyygame.presentation.view.widget.menu;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    private List<MenuGroup> mMenuGroups;
    private LayoutInflater mInflater = null;
    private int[] mPositionOfGroup;
    private int[] mPositionWithGroup;
    private boolean[] mFirstWithinGroup;

    private final Handler mHandler = new Handler(Looper.getMainLooper());

    public MenuAdapter() {
        mMenuGroups = new ArrayList<>();
    }

    public MenuAdapter(List<MenuGroup> dataList) {
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
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(parent.getContext());
        }
        return new MenuViewHolder(mInflater.inflate(R.layout.widget_me_menu_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder, int position) {
        final MenuItem item = getItem(position);
        holder.mMenuItemIv.setImageResource(item.getIcon());
        holder.mMenuItemNameTv.setText(item.getTitle());
        holder.itemView.setTag(R.id.recyclerViewDataKey, item);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuItem menuItem = (MenuItem) v.getTag(R.id.recyclerViewDataKey);
                mHandler.post(menuItem.getAction());
            }
        });
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
        for (MenuGroup group : mMenuGroups) {
            List<MenuItem> menuItemList = group.getChildList();
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
        for (MenuGroup group : mMenuGroups) {
            if (CollectionUtil.isEmpty(group.getChildList())) {
                continue;
            }
            size += group.getChildList().size();
        }
        return size;
    }

    public MenuItem getItem(int position) {
        int groupIndex = mPositionOfGroup[position];
        int indexWithinGroup = mPositionWithGroup[position];
        return mMenuGroups.get(groupIndex).getChildList().get(indexWithinGroup);
    }

    public boolean isFirstItemOfGroup(int position) {
        return mFirstWithinGroup[position];
    }

    static class MenuViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.menu_item_iv)
        ImageView mMenuItemIv;
        @BindView(R.id.menu_item_name_tv)
        TextView mMenuItemNameTv;

        MenuViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
