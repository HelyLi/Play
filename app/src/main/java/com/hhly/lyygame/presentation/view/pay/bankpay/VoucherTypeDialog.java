package com.hhly.lyygame.presentation.view.pay.bankpay;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.view.widget.RecyclerViewListDivide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 证件类型选择dialog
 * Created by dell on 2017/5/15.
 */

public class VoucherTypeDialog extends BottomSheetDialogFragment {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private VoucherTypeAdapter mAdapter;
    private SelectedCallBack mCallBack;
    Unbinder unbinder;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.voucher_type_dialog_layout, null);
        unbinder = ButterKnife.bind(this, contentView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new RecyclerViewListDivide(getContext(), R.color.color_e9e9e9,
                RecyclerViewListDivide.VERTICAL_LIST, 1));
        mAdapter = new VoucherTypeAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                dismiss();
                VoucherTypeAdapter.VoucherTypeItem voucherTypeItem =
                        (VoucherTypeAdapter.VoucherTypeItem) adapter.getItem(position);
                if (mCallBack != null) {
                    mCallBack.onSelected(voucherTypeItem);
                }
            }
        });
        mAdapter.updateItems(getContext());
        return contentView;
    }

    public void setCallBack(SelectedCallBack callBack) {
        mCallBack = callBack;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public interface SelectedCallBack {
        void onSelected(VoucherTypeAdapter.VoucherTypeItem voucherTypeItem);
    }
}
