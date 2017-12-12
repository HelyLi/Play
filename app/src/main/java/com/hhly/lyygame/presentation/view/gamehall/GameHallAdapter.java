package com.hhly.lyygame.presentation.view.gamehall;

import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.view.game.GameIntroActivity;
import com.hhly.lyygame.presentation.view.gamehall.model.GameGroup;
import com.hhly.lyygame.presentation.view.widget.RecyclerViewGridDivide;
import com.hhly.lyygame.presentation.view.widget.RecyclerViewListDivide;
import com.hhly.lyygame.presentation.view.widget.RecyclerViewListFirstDivide;
import com.hhly.lyygame.presentation.view.widget.SectionHeader;

import java.util.ArrayList;
import java.util.List;

/**
 *游戏大厅
 * Created by Simon on 2016/11/25.
 */

public class GameHallAdapter extends BaseMultiItemQuickAdapter<GameGroup, BaseViewHolder> {

    private Context mContext;

    private final RecyclerView.RecycledViewPool mRecycledViewPool;
    private RecyclerView.ItemDecoration mHorizontal15;
    private RecyclerView.ItemDecoration mFirstHorizontal15;
    private RecyclerView.ItemDecoration mGrid15;
    private RecyclerView.ItemDecoration mVertical15;

    private SparseArray<BaseQuickAdapter> mAdapterArray;


    public GameHallAdapter(Context context) {
        super(new ArrayList<GameGroup>());
        this.mContext = context;
        mRecycledViewPool = new RecyclerView.RecycledViewPool();
        addItemType(GameGroup.PLAYED, R.layout.lyy_rv_with_header);
        addItemType(GameGroup.HOT, R.layout.lyy_rv_with_header);
        addItemType(GameGroup.ONLINE, R.layout.lyy_rv_with_header);
        addItemType(GameGroup.OFFLINE, R.layout.lyy_rv_with_header);
        addItemType(GameGroup.NEW, R.layout.lyy_rv_with_header);
        addItemType(GameGroup.REC_WEEKLY, R.layout.lyy_rv_with_header);

        mAdapterArray = new SparseArray<>();

        mHorizontal15 = new RecyclerViewListDivide(context, R.color.transparent, RecyclerViewListDivide.HORIZONTAL_LIST, 15);
        mFirstHorizontal15 = new RecyclerViewListFirstDivide(context, RecyclerViewListFirstDivide.HORIZONTAL_LIST, 15);
        mGrid15 = new RecyclerViewGridDivide(context, R.color.transparent, 15f);
        mVertical15 = new RecyclerViewListDivide(context, R.color.color_e9e9e9, RecyclerViewListDivide.VERTICAL_LIST, 1);

    }

    @Override
    protected synchronized void convert(BaseViewHolder baseViewHolder, GameGroup gameGroup) {
        final int viewType = baseViewHolder.getItemViewType();
        RecyclerView rv = baseViewHolder.getView(R.id.recyclerView);
        rv.setRecycledViewPool(mRecycledViewPool);

        SectionHeader sectionHeader = baseViewHolder.getView(R.id.sectionHeader);
        if (sectionHeader != null) {
            sectionHeader.enableMore(gameGroup.isEnableMore());
            sectionHeader.setTag(R.id.item_data_key, gameGroup);
            sectionHeader.setSectionMoreListener(mOnSectionMoreListener);
            if (gameGroup.getSectionTitleResId() != 0) {
                sectionHeader.setTitle(gameGroup.getSectionTitleResId());
            } else {
                sectionHeader.setTitle(gameGroup.getSectionTitle());
            }
            if (gameGroup.getSectionIcon() != 0) {
                sectionHeader.setIcon(gameGroup.getSectionIcon());
            } else {
                sectionHeader.setIcon(gameGroup.getSectionIconUrl());
            }
        }

        BaseQuickAdapter adapter = null;

        switch (viewType) {
            case GameGroup.PLAYED:
                rv.setLayoutManager(new LinearLayoutManager(baseViewHolder.itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
                if ((adapter = mAdapterArray.get(GameGroup.PLAYED)) == null) {
//                    adapter = new PlayedAdapter(mContext);
                    mAdapterArray.put(GameGroup.PLAYED, adapter);
                }
                rv.removeItemDecoration(mHorizontal15);
                rv.removeItemDecoration(mFirstHorizontal15);
                rv.addItemDecoration(mHorizontal15);
                rv.addItemDecoration(mFirstHorizontal15);
                break;
            case GameGroup.HOT:
                rv.setLayoutManager(new GridLayoutManager(baseViewHolder.itemView.getContext(), 4));
                if ((adapter = mAdapterArray.get(GameGroup.HOT)) == null) {
                    adapter = new GameGridAdapter(mContext);
                    mAdapterArray.put(GameGroup.HOT, adapter);
                }
                rv.removeItemDecoration(mGrid15);
                rv.addItemDecoration(mGrid15);
                break;
            case GameGroup.ONLINE:
                rv.setLayoutManager(new GridLayoutManager(baseViewHolder.itemView.getContext(), 2));
                if ((adapter = mAdapterArray.get(GameGroup.ONLINE)) == null) {
                    adapter = new GameOnlineAdapter(mContext);
                    mAdapterArray.put(GameGroup.ONLINE, adapter);
                }
                rv.removeItemDecoration(mGrid15);
                rv.addItemDecoration(mGrid15);
                break;
            case GameGroup.OFFLINE:
                rv.setLayoutManager(new GridLayoutManager(baseViewHolder.itemView.getContext(), 4));
                if ((adapter = mAdapterArray.get(GameGroup.OFFLINE)) == null) {
                    adapter = new GameGridAdapter(mContext);
                    mAdapterArray.put(GameGroup.OFFLINE, adapter);
                }
                rv.removeItemDecoration(mGrid15);
                rv.addItemDecoration(mGrid15);
                break;
            case GameGroup.NEW:
                rv.setLayoutManager(new LinearLayoutManager(baseViewHolder.itemView.getContext(), LinearLayoutManager.VERTICAL, false));
                if ((adapter = mAdapterArray.get(GameGroup.NEW)) == null) {
                    adapter = new GameNewAdapter();
                    mAdapterArray.put(GameGroup.NEW, adapter);
                }
                rv.removeItemDecoration(mVertical15);
                rv.addItemDecoration(mVertical15);
                break;
            case GameGroup.REC_WEEKLY:
                rv.setLayoutManager(new GridLayoutManager(baseViewHolder.itemView.getContext(), 4));
                if ((adapter = mAdapterArray.get(GameGroup.REC_WEEKLY)) == null) {
                    adapter = new GameGridAdapter(mContext);
                    mAdapterArray.put(GameGroup.REC_WEEKLY, adapter);
                }
                rv.removeItemDecoration(mGrid15);
                rv.addItemDecoration(mGrid15);
                break;
        }
        if (adapter != null) {
            adapter.setNewData(gameGroup.getDataList());
            rv.setAdapter(adapter);
            adapter.setOnItemClickListener(mOnItemClickListener);
//            rv.addOnItemTouchListener(mOnItemClickListener);
        }
    }

    public void update(int type, List<Object> dataList) {
        BaseQuickAdapter adapter = null;
        if ((adapter = mAdapterArray.get(type)) != null) {
            adapter.setNewData(dataList);
        }
    }

    private SectionHeader.OnSectionMoreListener mOnSectionMoreListener = new SectionHeader.OnSectionMoreListener() {
        @Override
        public void onClickMore(View view) {
//            ActivityCompat.startActivity(view.getContext(), GameCategoryListActivity.getCallingIntent(view.getContext()), null);
        }
    };

    private OnItemClickListener mOnItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            ActivityCompat.startActivity(mContext, GameIntroActivity.getCallingIntent(mContext, 0, false), null);
        }
    };
//            new OnItemClickListener() {
//        @Override
//        public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
//            //baseQuickAdapter.getItem(i);
//            ActivityCompat.startActivity(mContext, GameIntroActivity.getCallingIntent(mContext, 0, false), null);
//        }
//
//    };


}
