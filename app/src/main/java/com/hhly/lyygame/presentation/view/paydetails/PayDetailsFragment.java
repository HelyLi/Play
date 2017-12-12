package com.hhly.lyygame.presentation.view.paydetails;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hhly.lyygame.App;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.protocol.user.OrderQueryResp;
import com.hhly.lyygame.presentation.utils.DateUtils;
import com.hhly.lyygame.presentation.view.BaseFragment;

import java.text.ParseException;
import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by ${HELY} on 17/1/18.
 * 邮箱：heli.lixiong@gmail.com
 */

public class PayDetailsFragment extends BaseFragment implements PayDetailsContact.View {

    @BindView(R.id.detail_state_iv)
    ImageView mDetailStateIv;
    @BindView(R.id.detail_tv)
    TextView mDetailTv;
    @BindView(R.id.detail_coins_tv)
    TextView mDetailCoinsTv;
    @BindView(R.id.detail_state_tv)
    TextView mDetailStateTv;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private final static String RECHARGE_TYPE = "recharge_type";
    private final static String RECHARGE_COUNT = "recharge_count";
    private final static String TIMESTAMP = "timestamp";
    private final static String TRANSACTION_ID = "transaction_id";
    String transactionId;

    private ArrayList<DetailItem> mItems = new ArrayList<>();
    private PayDetailsAdapter mAdapter;

    private PayDetailsContact.Persenter mPersenter;

    public PayDetailsFragment() {
        new PayDetailsPresenter(this);
    }

    public static PayDetailsFragment newInstance() {
        return new PayDetailsFragment();
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //        initDetail();

        Bundle bundle = getArguments();
        if (bundle != null) {
            int type = Integer.parseInt(bundle.getString("type", "1"));
            String transId = bundle.getString(TRANSACTION_ID);
            if (type == 1) {
                mPersenter.getOrderDetail(App.bundessNo);
            } else if (type == 2) {
                mPersenter.getWeChatOrderDetail(App.bundessNo);
            }
        }

        mAdapter = new PayDetailsAdapter(mItems);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void fetchData(boolean isLoadMore) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pay_details_layout;
    }

    @Override
    public void setPresenter(PayDetailsContact.Persenter presenter) {
        this.mPersenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }


    private void initDetail() {
        mItems = new ArrayList<>();
        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }
        int count = (int) Double.parseDouble(bundle.getString(RECHARGE_COUNT));
        mDetailCoinsTv.setText("+" + (count * 10));
        mItems.add(new DetailItem(getString(R.string.lyy_game_pay_detail_type), getString(R.string.lyy_game_pay_detail_type_content)));
        mItems.add(new DetailItem(getString(R.string.lyy_game_pay_detail_count), getString(R.string.lyy_game_pay_detail_gold, count * 10)));
        mItems.add(new DetailItem(getString(R.string.lyy_game_pay_detail_time), bundle.getString(TIMESTAMP)));
        mItems.add(new DetailItem(getString(R.string.lyy_game_pay_detail_order), bundle.getString(TRANSACTION_ID)));
        //        transactionId =
    }

    @Override
    public void aliQuerySuccess(OrderQueryResp resp) {
        if (resp != null && resp.getPayBussinessInfo() != null) {
            mItems.clear();
            mItems.add(new DetailItem(getString(R.string.lyy_game_pay_detail_type), getString(R.string.lyy_game_pay_detail_type_content)));
            mItems.add(new DetailItem(getString(R.string.lyy_game_pay_detail_count), getString(R.string.lyy_game_pay_detail_gold, resp.getPayBussinessInfo().getLyb())));
            mDetailCoinsTv.setText("+" + (resp.getPayBussinessInfo().getApplyAmount()));
            try {
                String date = DateUtils.longToString(resp.getPayBussinessInfo().getApplyTime(), "yyyy-MM-dd HH:mm:ss");
                mItems.add(new DetailItem(getString(R.string.lyy_game_pay_detail_time), date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            mItems.add(new DetailItem(getString(R.string.lyy_game_pay_detail_order), resp.getPayBussinessInfo().getOrderno()));
        }
        mAdapter.setNewData(mItems);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void weChatQuerySuccess(OrderQueryResp resp) {
        aliQuerySuccess(resp);
    }
}
