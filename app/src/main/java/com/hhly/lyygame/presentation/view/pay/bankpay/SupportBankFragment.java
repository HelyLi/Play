package com.hhly.lyygame.presentation.view.pay.bankpay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.protocol.pay.PayBankInfoResp;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.widget.RecyclerViewListDivide;

import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/5/13
 */

public class SupportBankFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, SupportBankContract.View {

    public static final String EXTRA_CATEGORY = "extra_category";
    public static final String KEY_BANKNAME = "key_bankName";
    public static final String KEY_BANKID = "key_bankId";
    /**
     * 储蓄卡
     */
    public static final int DEPOSIT = 0;
    /**
     * 信用卡
     */
    public static final int CREDIT = 1;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout mRefreshLayout;
    private SupportBankContract.Presenter mPresenter;
    private SupportBankAdapter mAdapter;

    public static SupportBankFragment newInstance(int category) {
        SupportBankFragment fragment = new SupportBankFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_CATEGORY, category);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        mRefreshLayout.setOnRefreshListener(this);
        new SupportBankPresenter(this);
        initRecycleView();
        fetchData(false);
    }

    private void initRecycleView() {
        mAdapter = new SupportBankAdapter(null);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new RecyclerViewListDivide(getActivity(),
                R.color.color_e9e9e9,
                LinearLayoutManager.VERTICAL,
                1));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                PayBankInfoResp item = (PayBankInfoResp) adapter.getData().get(position);
                bundle.putString(KEY_BANKNAME, item.getBankName());
                bundle.putInt(KEY_BANKID, item.getId());
                bundle.putInt(EXTRA_CATEGORY, getArguments().getInt(EXTRA_CATEGORY, DEPOSIT));
                intent.putExtras(bundle);
                getActivity().setResult(Activity.RESULT_OK, intent);
                getActivity().finish();
            }
        });
    }

    @Override
    protected void fetchData(boolean isLoadMore) {
        mRefreshLayout.setRefreshing(!isLoadMore);
        mPresenter.queryPayBankInfo();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_support_bank;
    }

    @Override
    public void onRefresh() {
        fetchData(false);
    }

    @Override
    public void setPresenter(SupportBankContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void queryPayBankInfoSuccess(List<PayBankInfoResp> list) {
        mRefreshLayout.setRefreshing(false);
        int category = getArguments().getInt(EXTRA_CATEGORY, DEPOSIT);
        if (list != null && list.size() > 0) {

            if (category == DEPOSIT) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getIsDepositCard() == 0) {
                        list.remove(i);
                        i--;
                    }
                }
            } else {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getIsCreditCard() == 0) {
                        list.remove(i);
                        i--;
                    }
                }
            }
        }
        mAdapter.setNewData(list);
    }

    @Override
    public void queryPayBankInfoFailure(String msg) {
        mRefreshLayout.setRefreshing(false);
    }
}
