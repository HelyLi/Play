package com.hhly.lyygame.presentation.view.pay;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.utils.DisplayUtil;
import com.hhly.lyygame.presentation.view.widget.RecyclerViewListDivide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${HELY} on 17/1/18.
 * 邮箱：heli.lixiong@gmail.com
 */

public class PayAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    private final RecyclerView.RecycledViewPool mRecycledViewPool;

    private static final int VIEW_TYPE_COINS = 100;
    private static final int VIEW_TYPE_PAY = 101;

    private RecyclerView.ItemDecoration mListDecoration;

    private SparseArray<BaseQuickAdapter> mAdapterArray;
    CoinsItem mCoinsItem;

    public PayAdapter(Context context) {
        super(new ArrayList<MultiItemEntity>());

        mRecycledViewPool = new RecyclerView.RecycledViewPool();
        addItemType(VIEW_TYPE_COINS, R.layout.lyy_pay_rv_with_header);
        addItemType(VIEW_TYPE_PAY, R.layout.lyy_pay_rv_with_header);

        mListDecoration = new RecyclerViewListDivide(context, R.color.color_e9e9e9, RecyclerViewListDivide.VERTICAL_LIST, 0.8f);
        mAdapterArray = new SparseArray<>();

        mCoinsItem = new CoinsItem();
        addData(mCoinsItem);
        addData(new PayItem());
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void convert(BaseViewHolder baseViewHolder, MultiItemEntity item) {
        final int viewType = baseViewHolder.getItemViewType();
        RecyclerView rv = baseViewHolder.getView(R.id.recyclerView);
        rv.setRecycledViewPool(mRecycledViewPool);
        TextView sectionTitleTv = baseViewHolder.getView(R.id.title_tv);
        BaseQuickAdapter adapter = initAdapterByType(viewType);
        switch (viewType) {
            case VIEW_TYPE_COINS:
                sectionTitleTv.setText(R.string.pay_choice_recharge_amount);
                rv.setLayoutManager(new GridLayoutManager(baseViewHolder.itemView.getContext(), 3, GridLayoutManager.VERTICAL, false));
                adapter.setNewData(((CoinsItem) item).getDataList());
                adapter.setOnItemClickListener(mOnCoinsItemClickListener);
                rv.setPadding(DisplayUtil.dip2px(mContext, 7.5f), DisplayUtil.dip2px(mContext, 7.5f),
                        DisplayUtil.dip2px(mContext, 7.5f), DisplayUtil.dip2px(mContext, 7.5f));
                rv.setAdapter(adapter);
                break;
            case VIEW_TYPE_PAY:
                sectionTitleTv.setText(R.string.pay_choice_pay_way);
                rv.setLayoutManager(new LinearLayoutManager(baseViewHolder.itemView.getContext()));
                adapter.setNewData(((PayItem) item).getDataList());
                rv.addItemDecoration(mListDecoration);
                rv.setAdapter(adapter);
                adapter.setOnItemClickListener(mOnPayItemClickListener);
                break;
            default:
                break;
        }
    }

    private BaseQuickAdapter initAdapterByType(int viewType) {
        BaseQuickAdapter adapter = mAdapterArray.get(viewType);
        if (adapter == null) {
            switch (viewType) {
                case VIEW_TYPE_COINS:
                    adapter = new CoinsItemAdapter();
                    break;
                case VIEW_TYPE_PAY:
                    adapter = new PlatformItemAdapter();
                    break;
            }
            mAdapterArray.put(VIEW_TYPE_COINS, adapter);
        }
        return adapter;
    }

    public CoinsItem getCoinsItem() {
        return mCoinsItem;
    }

    static class CoinsItem implements MultiItemEntity {

        private List<CoinsItemAdapter.BasePayAmountItem> mList;

        public List<CoinsItemAdapter.BasePayAmountItem> getDataList() {
            return mList;
        }

        public CoinsItem() {
            mList = new ArrayList<>();
            addItem(new CoinsItemModel(R.drawable.ic_pay_coins_20, 20, 200, 0, true));
            addItem(new CoinsItemModel(R.drawable.ic_pay_coins_50, 50, 500, 1, false));
            addItem(new CoinsItemModel(R.drawable.ic_pay_coins_100, 100, 1000, 2, false));
            addItem(new CoinsItemModel(R.drawable.ic_pay_coins_200, 200, 2000, 3, false));
            addItem(new CoinsItemModel(R.drawable.ic_pay_coins_300, 300, 3000, 4, false));
            addItem(new CoinsItemAdapter.CustomItem());
        }

        @Override
        public int getItemType() {
            return VIEW_TYPE_COINS;
        }

        private void addItem(CoinsItemAdapter.BasePayAmountItem item) {
            mList.add(item);
        }

        public void updateItems(List<CoinsItemModel> list) {
            mList.clear();
            mList.addAll(list);
        }
    }

    static class PayItem implements MultiItemEntity {

        private List<PlatformItemModel> mList;

        public List<PlatformItemModel> getDataList() {
            return mList;
        }

        public PayItem() {
            mList = new ArrayList<>();
            mList.add(new PlatformItemModel(R.drawable.ic_pay_unionpay, R.string.lyy_pay_unionpay, R.drawable.ic_cb_check_selector, true));
            mList.add(new PlatformItemModel(R.drawable.ic_pay_alipay, R.string.lyy_pay_alipay, R.drawable.ic_cb_check_selector, false));
            mList.add(new PlatformItemModel(R.drawable.ic_share_wechat, R.string.lyy_pay_wechat, R.drawable.ic_cb_check_selector, false));
        }

        @Override
        public int getItemType() {
            return VIEW_TYPE_PAY;
        }

        private void addItem(PlatformItemModel item) {
            mList.add(item);
        }

        public void updateItems(List<PlatformItemModel> list) {
            mList.clear();
            mList.addAll(list);
        }

    }

    private OnItemClickListener mOnPayItemClickListener;

    public void setPayItemClickListener(OnItemClickListener listener) {
        mOnPayItemClickListener = listener;
    }

    private OnItemClickListener mOnCoinsItemClickListener;

    public void setCoinsItemClickListener(OnItemClickListener listener) {
        mOnCoinsItemClickListener = listener;
    }


}
