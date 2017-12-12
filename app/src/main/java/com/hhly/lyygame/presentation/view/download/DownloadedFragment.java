package com.hhly.lyygame.presentation.view.download;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.downloadutils.DownloadHelper;
import com.hhly.lyygame.presentation.downloadutils.DownloadItem;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.widget.RecyclerViewListDivide;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import zlc.season.rxdownload2.function.Utils;


/**
 * 已下载
 * Created by Simon on 2016/12/5.
 */

public class DownloadedFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
//    @BindView(R.id.refreshLayout)
//    SwipeRefreshLayout mRefreshLayout;

    protected DownloadedAdapter mDownloadedAdapter;

    public static DownloadedFragment newInstance() {
        return new DownloadedFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDownloadedAdapter = new DownloadedAdapter(getActivity());
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {

        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setEnabled(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new RecyclerViewListDivide(getActivity(), R.color.color_e9e9e9, RecyclerViewListDivide.VERTICAL_LIST, 0.8f));
        
        mRecyclerView.setAdapter(mDownloadedAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_recyclerview_with_refresh;
    }

    @Override
    public void onRefresh() {
        fetchData(false);
    }

    protected void fetchData(boolean loadMore) {
        mRefreshLayout.setRefreshing(true);
        DownloadHelper.get().getDownloadedRecord().subscribe(new Observer<List<DownloadItem>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<DownloadItem> downloadItems) {
                mRefreshLayout.setRefreshing(false);
                mDownloadedAdapter.setNewData(downloadItems);
            }

            @Override
            public void onError(Throwable e) {
                mRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        List<DownloadItem> list = mDownloadedAdapter.getData();
        for (DownloadItem each : list) {
            if (each.disposable != null)
                Utils.dispose(each.disposable);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        postDelay(new Runnable() {
            @Override
            public void run() {
                fetchData(false);
            }
        }, 500);
    }

}
