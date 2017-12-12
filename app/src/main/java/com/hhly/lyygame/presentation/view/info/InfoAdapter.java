package com.hhly.lyygame.presentation.view.info;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.utils.CollectionUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Simon on 2016/11/24.
 */

public class InfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<InfoGroup> mMenuGroups;
    private LayoutInflater mInflater = null;
    private int[] mPositionOfGroup;
    private int[] mPositionWithGroup;
    private boolean[] mFirstWithinGroup;

    private final Handler mHandler = new Handler(Looper.getMainLooper());

    public InfoAdapter() {
        mMenuGroups = new ArrayList<>();
    }

    public InfoAdapter(List<InfoGroup> dataList) {
        mMenuGroups = dataList;
    }

    public void setInfo(List<InfoGroup> dataList) {
        mMenuGroups = dataList;
        dealWithMenuCount();
        notifyDataSetChanged();
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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(parent.getContext());
        }
        switch (viewType) {
            case InfoItem.COMM:
                return new InfoViewHolder(mInflater.inflate(R.layout.widget_info_item_layout, parent, false));
            case InfoItem.AVATAR:
                return new AvatarViewHolder(mInflater.inflate(R.layout.widget_info_item_avatar_layout, parent, false));
        }

        throw new IllegalStateException("viewType must be COMM or AVATAR");
    }

    @Override
    public int getItemViewType(int position) {
        if (!CollectionUtil.isEmpty(mMenuGroups)) {
            return mMenuGroups.get(mPositionOfGroup[position]).getChildList().get(mPositionWithGroup[position]).getType();
        }
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder iHolder, int position) {
        final InfoItem item = getItem(position);
        if (iHolder instanceof InfoViewHolder) {
            InfoViewHolder holder = (InfoViewHolder) iHolder;
            if (item.getAction() == null) {
                holder.mArrowIv.setVisibility(View.GONE);
            } else {
                holder.mArrowIv.setVisibility(View.VISIBLE);
            }
            holder.mNameTv.setText(item.getTitle());
            holder.mContentTv.setText(item.getContent());
        } else if (iHolder instanceof AvatarViewHolder) {
            AvatarViewHolder holder = (AvatarViewHolder) iHolder;
            if (item.getAction() == null) {
                holder.mArrowIv.setVisibility(View.GONE);
            } else {
                holder.mArrowIv.setVisibility(View.VISIBLE);
            }
            holder.mNameTv.setText(item.getTitle());
            Glide.with(holder.itemView.getContext()).load(item.getContent()).asBitmap()
                    .error(R.drawable.ic_game_pic_default_01)
                    .placeholder(R.drawable.ic_game_pic_default_01)
                    .centerCrop().into(holder.mAvatarIv);
        }
        iHolder.itemView.setTag(R.id.recyclerViewDataKey, item);
        iHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InfoItem menuItem = (InfoItem) v.getTag(R.id.recyclerViewDataKey);
                if (menuItem.getAction() != null) {
                    mHandler.post(menuItem.getAction());
                }
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
        for (InfoGroup group : mMenuGroups) {
            List<InfoItem> menuItemList = group.getChildList();
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
        for (InfoGroup group : mMenuGroups) {
            if (CollectionUtil.isEmpty(group.getChildList())) {
                continue;
            }
            size += group.getChildList().size();
        }
        return size;
    }

    public InfoItem getItem(int position) {
        int groupIndex = mPositionOfGroup[position];
        int indexWithinGroup = mPositionWithGroup[position];
        return mMenuGroups.get(groupIndex).getChildList().get(indexWithinGroup);
    }

    public boolean isFirstItemOfGroup(int position) {
        return mFirstWithinGroup[position];
    }

    static class InfoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_arrow_iv)
        ImageView mArrowIv;
        @BindView(R.id.item_name_tv)
        TextView mNameTv;
        @BindView(R.id.item_content_tv)
        TextView mContentTv;

        InfoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class AvatarViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_arrow_iv)
        ImageView mArrowIv;
        @BindView(R.id.item_avatar_iv)
        ImageView mAvatarIv;
        @BindView(R.id.item_name_tv)
        TextView mNameTv;

        AvatarViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
