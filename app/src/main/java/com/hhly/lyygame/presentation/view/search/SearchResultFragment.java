package com.hhly.lyygame.presentation.view.search;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.downloadutils.DownloadItem;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.game.GameIntroActivity;
import com.hhly.lyygame.presentation.view.gamehall.category.GameCategoryListAdapter;
import com.hhly.lyygame.presentation.view.widget.RecyclerViewListDivide;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;
import zlc.season.rxdownload2.function.Utils;

/**
 * 搜索结果
 * Created by Simon on 2016/12/3.
 */

public class SearchResultFragment extends BaseFragment implements BaseQuickAdapter.RequestLoadMoreListener, SearchContract.SearchResultView {

    final static String SEARCH_KEYWORD = "extra_keyword";

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private GameCategoryListAdapter mResultAdapter;

    private SearchContract.SearchResultPresenter mPresenter;

    private String keyword;
    private int pageNo = 1;
    private int totalPage = 1;
    private int PAGE_SIZE = 15;

    public SearchResultFragment() {
        new SearchResultPresenterImpl(this);
    }

    public static SearchResultFragment newInstance(String keyword) {
        SearchResultFragment fragment = new SearchResultFragment();
        Bundle bundle = new Bundle();
        bundle.putString(SEARCH_KEYWORD, keyword);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        keyword = getArguments().getString(SEARCH_KEYWORD);
    }

    private void initAdapter() {

        mResultAdapter = new GameCategoryListAdapter(getActivity());
        mResultAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mResultAdapter.setEnableLoadMore(true);

        View emptyView = LayoutInflater.from(getActivity()).inflate(R.layout.widget_empty_view, null, false);
        TextView addressEmptyTv = (TextView) emptyView.findViewById(R.id.empty_tv);
        Drawable topDrawable = getResources().getDrawable(R.drawable.ic_search_result_empty);
        topDrawable.setBounds(0, 0, topDrawable.getMinimumWidth(), topDrawable.getMinimumHeight());
        addressEmptyTv.setCompoundDrawables(null, topDrawable, null, null);
        addressEmptyTv.setText(R.string.lyy_search_result_empty);
        mResultAdapter.setEmptyView(emptyView);
        mRecyclerView.setAdapter(mResultAdapter);
        mRecyclerView.addOnItemTouchListener(mOnItemClickListener);
    }

    private OnItemChildClickListener mOnItemClickListener = new OnItemChildClickListener() {
        @Override
        public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            switch (view.getId()) {
                case R.id.game_item_root:
                    DownloadItem gameInfo = (DownloadItem) baseQuickAdapter.getItem(position);
                    ActivityCompat.startActivity(view.getContext(), GameIntroActivity.getCallingIntent(view.getContext(), gameInfo.record.getGameId(), false), null);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new RecyclerViewListDivide(getActivity(), R.color.color_e9e9e9, RecyclerViewListDivide.VERTICAL_LIST, 0.8f));

        initAdapter();

        if (TextUtils.isEmpty(keyword)) return;
        mPresenter.saveSearchRecord(keyword);
        mPresenter.searchGameList(keyword, pageNo, PAGE_SIZE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search_result_layout;
    }

    @Override
    public void showSearchGameList(List<DownloadItem> downloadItems) {
        if (pageNo == 1) {//onRefresh
            mResultAdapter.setNewData(downloadItems);
            mResultAdapter.setEnableLoadMore(true);
        } else {//
            mResultAdapter.addData(downloadItems);
            mResultAdapter.loadMoreComplete();
        }
        if (pageNo >= totalPage) {
            mResultAdapter.loadMoreEnd(true);
        }
        pageNo++;
    }

    @Override
    public void showTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    @Override
    public void onFailure() {
        mResultAdapter.loadMoreFail();
    }

    @Override
    public void setPresenter(SearchContract.SearchResultPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.d("onResume");
        if (mResultAdapter != null){
            mResultAdapter.notifyDataSetChanged();
        }
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
    public void onLoadMoreRequested() {
        mPresenter.searchGameList(keyword, pageNo, PAGE_SIZE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        List<DownloadItem> list = mResultAdapter.getData();
        for (DownloadItem each : list) {
            if (each.disposable != null)
                Utils.dispose(each.disposable);
        }
    }

}
