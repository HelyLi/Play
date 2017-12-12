package com.hhly.lyygame.presentation.view.order;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.view.pay.PlatformItemAdapter;
import com.hhly.lyygame.presentation.view.pay.PlatformItemModel;
import com.hhly.lyygame.presentation.view.widget.RecyclerViewListDivide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangbo on 2017/5/31.
 */

public class PayAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    private static final int VIEW_TYPE_PAY = 101;
    public PayAdapter() {
        super(new ArrayList<MultiItemEntity>());

        addItemType(VIEW_TYPE_PAY, R.layout.lyy_pay_rv_with_header);

        addData(new PayItem());
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MultiItemEntity item) {
        RecyclerView rv = baseViewHolder.getView(R.id.recyclerView);

        TextView titleTv = baseViewHolder.getView(R.id.title_tv);
        titleTv.setText(R.string.pay_choice_pay_way);

        BaseQuickAdapter adapter = new PlatformItemAdapter();
        rv.setLayoutManager(new LinearLayoutManager(baseViewHolder.itemView.getContext()));
        adapter.setNewData(((PayItem) item).getDataList());
        rv.addItemDecoration(new RecyclerViewListDivide(mContext, R.color.color_e9e9e9, RecyclerViewListDivide.VERTICAL_LIST, 0.8f));
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(mOnPayItemClickListener);
    }

    static class PayItem implements MultiItemEntity {

        private List<PlatformItemModel> mList;

        public List<PlatformItemModel> getDataList() {
            return mList;
        }

        public PayItem() {
            mList = new ArrayList<>();
            mList.add(new PlatformItemModel(R.drawable.ic_pay_alipay, R.string.lyy_pay_alipay, R.drawable.ic_cb_check_selector, true));
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
}
