package com.hhly.lyygame.presentation.view.gameservice;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.protocol.game.GameAreaResp;
import com.hhly.lyygame.data.net.protocol.user.State;
import com.hhly.lyygame.presentation.utils.CollectionUtil;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.widget.RecyclerViewListDivide;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import info.hoang8f.android.segmented.SegmentedGroup;


/**
 * Created by ${HELY} on 17/1/18.
 * 邮箱：heli.lixiong@gmail.com
 */

public class GameServiceFragment extends BaseFragment implements GameServiceContact.View,
        SwipeRefreshLayout.OnRefreshListener, RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.service_new)
    RadioButton mServiceNew;
    @BindView(R.id.service_future)
    RadioButton mServiceFuture;
    @BindView(R.id.segment_group)
    SegmentedGroup mSegmentGroup;

    private GameServiceContact.Persenter mPersenter;

    private GameServiceAdapter mResultAdapter;

    private List<GameAreaResp.ListGameServerAreaBean> newGameArea = new ArrayList<>();
    private List<GameAreaResp.ListGameServerAreaBean> futureGameArea = new ArrayList<>();

    private String[] tabData = new String[2];

    public static GameServiceFragment newInstance() {
        return new GameServiceFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_game_service_layout;
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {

        mSegmentGroup.setOnCheckedChangeListener(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new RecyclerViewListDivide(getActivity(), R.color.color_e9e9e9, RecyclerViewListDivide.VERTICAL_LIST, 0.8f));
        mResultAdapter = new GameServiceAdapter();
        mRecyclerView.setAdapter(mResultAdapter);

        View emptyView = LayoutInflater.from(getActivity()).inflate(R.layout.widget_empty_imageview, null, false);
        mResultAdapter.setEmptyView(emptyView);
        mRefreshLayout.setOnRefreshListener(this);

        postDelay(new Runnable() {
            @Override
            public void run() {
                fetchData(true);
            }
        }, 500);
    }

    @Override
    public void setPresenter(GameServiceContact.Persenter presenter) {
        this.mPersenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void onRefresh() {
        Logger.d("onRefresh");
        fetchData(false);
    }

    @Override
    protected void fetchData(boolean loadMore) {
        mPersenter.getGameArea(State.ServiceType.NEW);
        mPersenter.getGameArea(State.ServiceType.FUTURE);
    }

    @Override
    public void showNewGameArea(List<GameAreaResp.ListGameServerAreaBean> list) {
        onFailure();
        newGameArea.clear();
        newGameArea.addAll(list);
        if (CollectionUtil.isEmpty(list)) {
            tabData[0] = getString(R.string.lyy_game_service_new, 0);
        } else {
            tabData[0] = getString(R.string.lyy_game_service_new, list.size());
        }
        mServiceNew.setText(tabData[0]);

        if (mServiceNew.isChecked()) {
            mResultAdapter.setNewData(newGameArea);
        }
    }

    @Override
    public void showFutureGameArea(List<GameAreaResp.ListGameServerAreaBean> list) {
        onFailure();
        futureGameArea.clear();
        futureGameArea.addAll(list);
        if (CollectionUtil.isEmpty(list)) {
            tabData[1] = getString(R.string.lyy_game_service_future, 0);
        } else {
            tabData[1] = getString(R.string.lyy_game_service_future, list.size());
        }
        mServiceFuture.setText(tabData[1]);
        if (mServiceFuture.isChecked()) {
            mResultAdapter.setNewData(futureGameArea);
        }
    }

    @Override
    public void onFailure() {
        if (mRefreshLayout != null)
            mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId) {
            case R.id.service_new:
                mResultAdapter.setNewData(newGameArea);
                break;
            case R.id.service_future:
                mResultAdapter.setNewData(futureGameArea);
                break;
            default:
                break;
        }

    }
}
