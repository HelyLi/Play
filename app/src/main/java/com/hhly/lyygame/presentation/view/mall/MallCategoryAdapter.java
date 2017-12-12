package com.hhly.lyygame.presentation.view.mall;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.v4.app.ActivityCompat;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.model.GoodsInfo;
import com.hhly.lyygame.presentation.utils.CollectionUtil;
import com.hhly.lyygame.presentation.view.mall.category.MallCategoryListActivity;
import com.hhly.lyygame.presentation.view.mall.category.MallCategoryListFragment;
import com.hhly.lyygame.presentation.view.mall.category.MallListAdapter;
import com.hhly.lyygame.presentation.view.widget.RecyclerViewGridDivide;
import com.hhly.lyygame.presentation.view.widget.SectionHeader;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2017/5/8.
 */

public class MallCategoryAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    //虚拟
    public static final int CATEGORY_VIR = 1;
    //实物
    public static final int CATEGORY_MAT = 2;

    @IntDef({CATEGORY_VIR, CATEGORY_MAT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Category{}

    private final RecyclerView.RecycledViewPool mRecycledViewPool;
    private SparseArrayCompat<BaseQuickAdapter> mAdapterArray;
    private SparseArrayCompat<RecyclerView.LayoutManager> mLayoutManagerSparseArray;

    private RecyclerViewGridDivide mRecyclerViewGridDivide;

    private Context mContext;
    private int mCategory;

    public MallCategoryAdapter(Context context, int category) {
        super(new ArrayList<MultiItemEntity>());
        mContext = context;
        mCategory = category;
        mRecycledViewPool = new RecyclerView.RecycledViewPool();
        mAdapterArray = new SparseArrayCompat<>();
        mLayoutManagerSparseArray = new SparseArrayCompat<>();
        mRecyclerViewGridDivide = new RecyclerViewGridDivide(mContext, R.color.transparent, 10);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        int viewType = helper.getItemViewType();
        RecyclerView rv = helper.getView(R.id.recyclerView);

        SectionHeader sectionHeader = helper.getView(R.id.sectionHeader);
        rv.setRecycledViewPool(mRecycledViewPool);
        initLayoutManagerByType(viewType, rv);
        switch (viewType){
            case CATEGORY_VIR:
                sectionHeader.setTitle(R.string.lyy_category_vir);
                sectionHeader.setIcon(R.drawable.ic_section_vir);
                sectionHeader.setSectionMoreListener(new SectionHeader.OnSectionMoreListener() {
                    @Override
                    public void onClickMore(View view) {//更多热门游戏
                        switch (mCategory) {
                            case MallCategoryFragment.CATEGORY_COUPON://乐盈卷－>虚拟物品
                                ActivityCompat.startActivity(mContext, MallCategoryListActivity.getCallingIntent(mContext, MallCategoryListFragment.CATEGORY_COUPON_VIR), null);
                                break;
                            case MallCategoryFragment.CATEGORY_SCORE://乐盈币->虚拟物品
                                ActivityCompat.startActivity(mContext, MallCategoryListActivity.getCallingIntent(mContext, MallCategoryListFragment.CATEGORY_SCORE_VIR), null);
                                break;
                            default:
                                break;
                        }
                    }
                });
                break;
            case CATEGORY_MAT:
                sectionHeader.setTitle(R.string.lyy_category_mat);
                sectionHeader.setIcon(R.drawable.ic_section_mat);
                sectionHeader.setSectionMoreListener(new SectionHeader.OnSectionMoreListener() {
                    @Override
                    public void onClickMore(View view) {//更多热门游戏
                        switch (mCategory) {
                            case MallCategoryFragment.CATEGORY_COUPON://乐盈卷－>手办周边
                                ActivityCompat.startActivity(mContext, MallCategoryListActivity.getCallingIntent(mContext, MallCategoryListFragment.CATEGORY_COUPON_MAT), null);
                                break;
                            case MallCategoryFragment.CATEGORY_SCORE://乐盈币->手办周边
                                ActivityCompat.startActivity(mContext, MallCategoryListActivity.getCallingIntent(mContext, MallCategoryListFragment.CATEGORY_SCORE_MAT), null);
                                break;
                            default:
                                break;
                        }
                    }
                });
                break;
        }
    }

    private void initLayoutManagerByType(int viewType, RecyclerView recyclerView){
        RecyclerView.LayoutManager layoutManager = mLayoutManagerSparseArray.get(viewType);
        if (layoutManager == null){
            switch (viewType){
                case CATEGORY_VIR:
                case CATEGORY_MAT:
                    layoutManager = new GridLayoutManager(mContext, 2);
                    recyclerView.addItemDecoration(mRecyclerViewGridDivide);
                    break;
            }
            recyclerView.setLayoutManager(layoutManager);
            mLayoutManagerSparseArray.put(viewType, layoutManager);
        }
        BaseQuickAdapter adapter = initAdapterByType(viewType);
        recyclerView.setAdapter(adapter);
//        if (mRListener != null){
//            recyclerView.addOnItemTouchListener(mRListener);
//        }
    }

    private BaseQuickAdapter initAdapterByType(int viewType){
        BaseQuickAdapter adapter = mAdapterArray.get(viewType);
        if (adapter == null){
            adapter = new MallListAdapter();
            switch (viewType){
                case CATEGORY_VIR:
                    if (mVirListener != null){
                        adapter.setOnItemChildClickListener(mVirListener);
                    }
                    break;
                case CATEGORY_MAT:
                    if (mMatListener != null){
                        adapter.setOnItemChildClickListener(mMatListener);
                    }
                    break;
            }
            mAdapterArray.put(viewType, adapter);
        }

        return adapter;
    }

//    private OnItemChildClickListener mListener;

    private OnItemChildClickListener mMatListener;
    private OnItemChildClickListener mVirListener;

    public void setOnMatItemChildClickListener(OnItemChildClickListener listener){
        mMatListener = listener;
    }

    public void setOnVirItemChildClickListener(OnItemChildClickListener listener){
        mVirListener = listener;
    }

//    @Override
//    public void setOnItemChildClickListener(OnItemChildClickListener listener) {
//        mListener = listener;
//    }


//    private com.chad.library.adapter.base.listener.OnItemChildClickListener mRListener;
//
//    public void setListener(com.chad.library.adapter.base.listener.OnItemChildClickListener listener){
//        mRListener = listener;
//    }

    private void updateItemsByType(List<?> list, int type){
        BaseQuickAdapter adapter = initAdapterByType(type);
        adapter.setNewData(list);
    }

    static class VirItem implements MultiItemEntity{
        private List<GoodsInfo> mList;

        public VirItem() {
            mList = new ArrayList<>();
        }

        @Override
        public int getItemType() {
            return CATEGORY_VIR;
        }

        public void addItem(GoodsInfo item) {
            mList.add(item);
        }

        public void updateItems(List<GoodsInfo> list) {
            mList.clear();
            mList.addAll(list);
        }
    }

    static class MatItem implements MultiItemEntity{
        private List<GoodsInfo> mList;

        public MatItem() {
            mList = new ArrayList<>();
        }

        @Override
        public int getItemType() {
            return CATEGORY_MAT;
        }

        public void addItem(GoodsInfo item) {
            mList.add(item);
        }

        public void updateItems(List<GoodsInfo> list) {
            mList.clear();
            mList.addAll(list);
        }
    }

    List<GoodsInfo> mVirGoods = null;
    List<GoodsInfo> mMatGoods = null;

    public List<GoodsInfo> getVirGoodsList(){
        return mVirGoods;
    }

    public List<GoodsInfo> getMatGoodsList(){
        return mMatGoods;
    }

    /**
     *
     * @param list
     */
    public void update(List<GoodsInfo> list){
        if (CollectionUtil.isEmpty(list))return;
        setNewData(new ArrayList<MultiItemEntity>());

        if (mVirGoods == null){
            mVirGoods = new ArrayList<>();
        }
        mVirGoods.clear();
        if (mMatGoods == null){
            mMatGoods = new ArrayList<>();
        }
        mMatGoods.clear();

        for (GoodsInfo goods : list){
            if (goods.getType() == 1){
                mMatGoods.add(goods);
            }else {
                mVirGoods.add(goods);
            }
        }

        if (mMatGoods.size() > 4){
            mMatGoods.subList(4, mMatGoods.size()).clear();
        }

        if (mVirGoods.size() > 4){
            mVirGoods.subList(4, mVirGoods.size()).clear();
        }

        if (CollectionUtil.isNotEmpty(mVirGoods)){
            addItemType(CATEGORY_VIR, R.layout.lyy_rv_with_header);
            addData(new VirItem());
            updateItemsByType(mVirGoods, CATEGORY_VIR);
        }
        if (CollectionUtil.isNotEmpty(mMatGoods)){
            addItemType(CATEGORY_MAT, R.layout.lyy_rv_with_header);
            addData(new MatItem());
            updateItemsByType(mMatGoods, CATEGORY_MAT);
        }

    }


}
