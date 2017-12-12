package com.hhly.lyygame.presentation.view.home;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IntDef;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.protocol.game.IndexActivityByModelIdResp;
import com.hhly.lyygame.presentation.downloadutils.DownloadItem;
import com.hhly.lyygame.presentation.utils.CollectionUtil;
import com.hhly.lyygame.presentation.utils.DisplayUtil;
import com.hhly.lyygame.presentation.view.widget.RecyclerViewGridDivide;
import com.hhly.lyygame.presentation.view.widget.SectionHeader;
import com.orhanobut.logger.Logger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

import zlc.season.rxdownload2.function.Utils;

/**
 * Created by dell on 2017/5/15.
 */

public class GameHomeAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    //
    public static final int CATEGORY_SELECT = 0;
    //独家游戏
    public static final int CATEGORY_ONLY = 1;
    //活动专题
    public static final int CATEGORY_ACTIVITY = 2;

    @IntDef({CATEGORY_SELECT, CATEGORY_ONLY, CATEGORY_ACTIVITY})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Category {
    }

    private final RecyclerView.RecycledViewPool mRecycledViewPool;
    private SparseArrayCompat<BaseQuickAdapter> mAdapterArray;
    private SparseArrayCompat<RecyclerView.LayoutManager> mLayoutManagerSparseArray;

    private Context mContext;
    RecyclerViewGridDivide mRecyclerViewGridDivide15;
//    RecyclerViewListFirstDivide mFirstHorizontal35;
//    RecyclerViewListDivide mHorizontal36;

    public GameHomeAdapter(Context context) {
        super(new ArrayList<MultiItemEntity>());
        mContext = context;
        mRecycledViewPool = new RecyclerView.RecycledViewPool();
        mAdapterArray = new SparseArrayCompat<>();
        mLayoutManagerSparseArray = new SparseArrayCompat<>();
        mRecyclerViewGridDivide15 = new RecyclerViewGridDivide(context, R.color.transparent, 15);
//        mHorizontal36 = new RecyclerViewListDivide(mContext, R.color.transparent, RecyclerViewListDivide.HORIZONTAL_LIST, 36);
//        mFirstHorizontal35 = new RecyclerViewListFirstDivide(mContext, RecyclerViewListFirstDivide.HORIZONTAL_LIST, 35);

//        updateSelect();
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        int viewType = helper.getItemViewType();
        RecyclerView rv = helper.getView(R.id.recyclerView);

        rv.setRecycledViewPool(mRecycledViewPool);
        initLayoutManagerByType(viewType, rv);
        switch (viewType) {
            case CATEGORY_SELECT:

                break;
            case CATEGORY_ONLY:
                SectionHeader onlyHeader = helper.getView(R.id.sectionHeader);
                onlyHeader.setTitle(R.string.lyy_section_only);
                onlyHeader.setIcon(R.drawable.ic_section_only);
                onlyHeader.enableMore(false);
                break;
            case CATEGORY_ACTIVITY:
                SectionHeader activityHeader = helper.getView(R.id.sectionHeader);
                activityHeader.setTitle(R.string.lyy_section_activity);
                activityHeader.setIcon(R.drawable.ic_section_activity);
                activityHeader.enableMore(false);
                break;
        }
    }

    private void initLayoutManagerByType(int viewType, RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = mLayoutManagerSparseArray.get(viewType);
        if (layoutManager == null) {
            switch (viewType) {
                case CATEGORY_SELECT:
                    layoutManager = new GridLayoutManager(mContext, 4);
                    recyclerView.setPadding(DisplayUtil.dip2px(mContext, 15f), 0, DisplayUtil.dip2px(mContext, 15f), 0);
                    break;
                case CATEGORY_ONLY:
                    layoutManager = new GridLayoutManager(mContext, 2);
                    recyclerView.addItemDecoration(mRecyclerViewGridDivide15);
                    break;
                case CATEGORY_ACTIVITY:
                    layoutManager = new GridLayoutManager(mContext, 2);
                    recyclerView.setPadding(DisplayUtil.dip2px(mContext, 15f), DisplayUtil.dip2px(mContext, 15), DisplayUtil.dip2px(mContext, 15), DisplayUtil.dip2px(mContext, 15));
                    break;
            }
            recyclerView.setLayoutManager(layoutManager);
            mLayoutManagerSparseArray.put(viewType, layoutManager);
        }
        BaseQuickAdapter adapter = initAdapterByType(viewType);
        recyclerView.setAdapter(adapter);
    }

    private void updateItemsByType(List<?> list, int type) {
        BaseQuickAdapter adapter = initAdapterByType(type);
        adapter.setNewData(list);
    }

    private BaseQuickAdapter initAdapterByType(int viewType) {
        BaseQuickAdapter adapter = mAdapterArray.get(viewType);
        if (adapter == null) {
            switch (viewType) {
                case CATEGORY_SELECT:
                    adapter = new SelectAdapter();
                    break;
                case CATEGORY_ONLY:
                    adapter = new HomeOnlyAdapter((Activity) mContext);
                    break;
                case CATEGORY_ACTIVITY:
                    adapter = new ActivityAdapter();
                    break;
            }
            if (mListener != null) {
                adapter.setOnItemChildClickListener(mListener);
            }
            mAdapterArray.put(viewType, adapter);
        }
        return adapter;
    }

    private OnItemChildClickListener mListener;

    @Override
    public void setOnItemChildClickListener(OnItemChildClickListener listener) {
        mListener = listener;
    }

    static class SelectItem implements MultiItemEntity {

        private List<HomeSelectItem> mList;

        public List<HomeSelectItem> getDataList() {
            return mList;
        }

        SelectItem() {
            mList = new ArrayList<>();
            addItem(new HomeSelectItem(R.drawable.bg_home_score_task, R.string.lyy_home_score_task));
            addItem(new HomeSelectItem(R.drawable.bg_home_activity_center, R.string.lyy_home_activity_center));
            addItem(new HomeSelectItem(R.drawable.bg_home_game_exchange, R.string.lyy_home_game_exchange));
            addItem(new HomeSelectItem(R.drawable.bg_home_coupon_exchange, R.string.lyy_home_coupon_exchange));
        }

        public void addItem(HomeSelectItem string) {
            mList.add(string);
        }

        public void updateItems(List<HomeSelectItem> selectList) {
            mList.clear();
            mList.addAll(selectList);
        }

        @Override
        public int getItemType() {
            return CATEGORY_SELECT;
        }
    }

    /**
     * 独家游戏
     */
    static class OnlyItem implements MultiItemEntity {
        private List<DownloadItem> mList;

        OnlyItem() {
            mList = new ArrayList<>();
        }

        public List<DownloadItem> getDataList() {
            return mList;
        }

        public void addItem(DownloadItem string) {
            mList.add(string);
        }

        public void updateItems(List<DownloadItem> gameInfoList) {
            mList.clear();
            mList.addAll(gameInfoList);
        }

        @Override
        public int getItemType() {
            return CATEGORY_ONLY;
        }
    }

    /**
     * 活动专题
     */
    static class ActivityItem implements MultiItemEntity {
        private List<String> mList;

        ActivityItem() {
            mList = new ArrayList<>();
        }

        public List<String> getDataList() {
            return mList;
        }

        public void addItem(String string) {
            mList.add(string);
        }

        public void updateItems(List<String> gameInfoList) {
            mList.clear();
            mList.addAll(gameInfoList);
        }

        @Override
        public int getItemType() {
            return CATEGORY_ACTIVITY;
        }
    }

    /**
     *
     */
    public void updateSelect() {
        Logger.d("HY:updateSelect");
        addItemType(CATEGORY_SELECT, R.layout.widget_recycler_view);
        addData(0, new SelectItem());
        updateItemsByType(new SelectItem().getDataList(), CATEGORY_SELECT);
    }

    //独家游戏item是否已初始化过
    private boolean isInitOnlyGame;

    /**
     * 更新独家游戏
     */
    public void updateOnlyGame(List<DownloadItem> list) {
        Logger.d("HY:updateOnlyGame" + (list == null));
        if (list != null) {
            if (!isInitOnlyGame) {
                addItemType(CATEGORY_ONLY, R.layout.lyy_rv_with_header);
                addData(1, new OnlyItem());
                isInitOnlyGame = true;
            }
            updateItemsByType(list, CATEGORY_ONLY);
        }
    }

    //活动专题item是否已初始化过
    private boolean isInitActivity;

    /**
     * 更新活动专题
     */
    public void updateActivity(List<IndexActivityByModelIdResp.ActivityPage.ActivityInfo> list) {
        Logger.d("HY:activityList" + (list == null));
        if (list != null) {
            if (!isInitActivity) {
                addItemType(CATEGORY_ACTIVITY, R.layout.lyy_rv_with_header);
                addData(getData().size(), new ActivityItem());
                isInitActivity = true;
            }
            updateItemsByType(list, CATEGORY_ACTIVITY);
        }
    }

    public void disposeGame(){
        BaseQuickAdapter adapter = initAdapterByType(CATEGORY_ONLY);
        if (adapter != null && CollectionUtil.isNotEmpty(adapter.getData())){
            for (DownloadItem each : (List<DownloadItem>)adapter.getData()) {
                if (each.disposable != null)
                    Utils.dispose(each.disposable);
            }
        }
    }

}
