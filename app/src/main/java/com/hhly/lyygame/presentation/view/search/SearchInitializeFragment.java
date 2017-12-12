package com.hhly.lyygame.presentation.view.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.protocol.game.GameByModelIdResp;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.game.GameIntroActivity;
import com.hhly.lyygame.presentation.view.widget.RecyclerViewListDivide;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 搜索初始界面
 * Created by Simon on 2016/12/3.
 */

public class SearchInitializeFragment extends BaseFragment implements SearchContract.SearchInitializeView, View.OnClickListener {

    @BindView(R.id.search_recent_clear_tv)
    TextView mSearchRecentClearTv;
    @BindView(R.id.recyclerView_hot)
    RecyclerView mRecyclerViewHot;
    @BindView(R.id.record_layout)
    TagFlowLayout mRecordLayout;

    private SearchHotAdapter mHotAdapter;
    private SearchContract.SearchInitializePresenter mPresenter;

    private SearchRecordTagAdapter mSearchRecordAdapter;

    public SearchInitializeFragment() {
        new SearchInitializePresenterImpl(this);
    }

    public static SearchInitializeFragment newInstance() {
        return new SearchInitializeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHotAdapter = new SearchHotAdapter();
        mSearchRecordAdapter = new SearchRecordTagAdapter(getActivity());
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {

        mRecyclerViewHot.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerViewHot.addItemDecoration(new RecyclerViewListDivide(getActivity(), R.color.color_e9e9e9, RecyclerViewListDivide.VERTICAL_LIST, 0.5f));

        mRecyclerViewHot.setAdapter(mHotAdapter);
        mRecyclerViewHot.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

                GameByModelIdResp.GameByModeIdPage.GameByModeIdInfo gameInfo = (GameByModelIdResp.GameByModeIdPage.GameByModeIdInfo) baseQuickAdapter.getItem(i);
                ActivityCompat.startActivity(view.getContext(), GameIntroActivity.getCallingIntent(view.getContext(), gameInfo.getGameId(), false), null);
            }
        });

        mSearchRecordAdapter.setRecordListener(this);
        mRecordLayout.setAdapter(mSearchRecordAdapter);

        mPresenter.getSearchRecord();
        mPresenter.getHotGameList();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search_initialize_layout;
    }

    @OnClick(R.id.search_recent_clear_tv)
    public void onClick() {
        mPresenter.clearSearchRecord();
    }

    @Override
    public void showHotGameList(List<GameByModelIdResp.GameByModeIdPage.GameByModeIdInfo> gameInfoList) {
        mHotAdapter.setNewData(gameInfoList);
    }

    @Override
    public void showSearchRecord(List<String> records) {
        if (mRecordLayout != null) {
            mRecordLayout.removeAllViews();
        }
        mSearchRecordAdapter.setNewData(records);
    }

    @Override
    public void onClearRecord() {
        if (mRecordLayout != null) {
            mRecordLayout.removeAllViews();
        }
    }

    @Override
    public void setPresenter(SearchContract.SearchInitializePresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    protected void fetchData(boolean isLoadMore) {

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onClick(View v) {
        if (v instanceof TextView) {
            ((SearchActivity) getActivity()).switchToResultFrame(((TextView) v).getText().toString());
        }
    }

}
