package com.hhly.lyygame.presentation.view.gamehall.category;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.downloadutils.DownloadItem;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.game.GameIntroActivity;
import com.hhly.lyygame.presentation.view.immersive.IImmersiveApply;
import com.hhly.lyygame.presentation.view.widget.RecyclerViewListDivide;

import java.util.List;

import butterknife.BindView;
import zlc.season.rxdownload2.function.Utils;

/**
 * Created by Simon on 2016/11/24.
 */

public class GameCategoryListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener, IImmersiveApply, GategoryListContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
//    @BindView(R.id.refreshLayout)
//    SwipeRefreshLayout mRefreshLayout;

    private GameCategoryListAdapter mAdapter;

    private GategoryListContract.Presenter mPresenter;

    public static GameCategoryListFragment newInstance(@NonNull GameCategory category) {

        GameCategoryListFragment fragment = new GameCategoryListFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(GameCategoryListActivity.GAME_CATEGORY, category);
        fragment.setArguments(bundle);

        return fragment;
    }

    private GameCategory mGameCategory = null;

    private int pageNo = 1;
    private int totalPage = 1;
    private int PAGE_SIZE = 10;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGameCategory = (GameCategory) getArguments().getSerializable(GameCategoryListActivity.GAME_CATEGORY);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_game_cateogry_layout;
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {

        setToolbalTitle(mGameCategory);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.addItemDecoration(new RecyclerViewListDivide(getActivity(), R.color.color_e9e9e9, RecyclerViewListDivide.VERTICAL_LIST, 1));

        initAdapter();

        //获取缓存的策略 先本地－>服务端
        mRefreshLayout.setRefreshing(true);
        mPresenter.getGameList(false, mGameCategory, pageNo, PAGE_SIZE);
    }

    private void initAdapter() {
        mAdapter = new GameCategoryListAdapter(getActivity());
        mAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(mOnItemChildClickListener);
    }

    OnItemChildClickListener mOnItemChildClickListener = new OnItemChildClickListener() {
        @Override
        public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            switch (view.getId()) {
                case R.id.game_item_root:
                    DownloadItem item = (DownloadItem) adapter.getItem(position);
                    ActivityCompat.startActivity(view.getContext(), GameIntroActivity.getCallingIntent(view.getContext(), item.record.getGameId(), false), null);
                    break;
            }
        }
    };

    private void setToolbalTitle(GameCategory category) {

        String title = null;

        switch (category) {
            case HOT:
                title = getActivity().getString(R.string.lyy_section_hot);
                break;
            case QUA:
                title = getActivity().getString(R.string.lyy_section_rec);
                break;
            case ONLY://独家
                title = getString(R.string.lyy_home_only) + getString(R.string.lyy_game);
                break;
            case REC://推荐

                break;
            case OFFLINE://单机
                title = getString(R.string.lyy_section_offline);
                break;
            case ONLINE://网游
                title = getString(R.string.lyy_home_online) + getString(R.string.lyy_game);
                break;
            case ROLE://角色扮演
                title = getString(R.string.lyy_game_room_common_00);
                break;
            case SHOOT://射击游戏
                title = getString(R.string.lyy_game_room_common_01);
                break;
            case ARPG://即时战斗
                title = getString(R.string.lyy_game_room_common_02);
                break;
            case ADVE://冒险游戏
                title = getString(R.string.lyy_game_room_common_03);
                break;
            case SLG://策略游戏
                title = getString(R.string.lyy_game_room_common_04);
                break;
            case CHESS://旗类游戏
                title = getString(R.string.lyy_game_room_common_05);
                break;
            case RAC://竞速游戏
                title = getString(R.string.lyy_game_room_common_06);
                break;
            case ACTION://动作游戏
                title = getString(R.string.lyy_game_room_common_07);
                break;
            case SIM://模拟经营
                title = getString(R.string.lyy_game_room_common_08);
                break;
            case LVG://养成游戏
                title = getString(R.string.lyy_game_room_common_09);
                break;
            case FLY://飞行游戏
                title = getString(R.string.lyy_game_room_common_10);
                break;
            case SPORT://体育游戏
                title = getString(R.string.lyy_game_room_common_11);
                break;
            case FIGHT://格斗游戏
                title = getString(R.string.lyy_game_room_common_12);
                break;
            case PUZ://益智游戏
                title = getString(R.string.lyy_game_room_common_13);
                break;
            case FILLER://对战游戏
                title = getString(R.string.lyy_game_room_common_14);
                break;
            case OTHER://其他
                title = getString(R.string.lyy_game_room_common_15);
                break;
        }

        mToolbarHelper.setTitle(title);
    }

    @Override
    public void onRefresh() {
        pageNo = 1;
        mPresenter.getGameList(true, mGameCategory, pageNo, PAGE_SIZE);
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.getGameList(true, mGameCategory, pageNo, PAGE_SIZE);
    }

    @Override
    public boolean applyImmersive() {
        return true;
    }

    @Override
    public boolean applyScroll() {
        return false;
    }

    @Override
    public float initAlpha() {
        return 1.0f;
    }

    @Override
    public void showGameList(List<DownloadItem> list, boolean refresh) {
        if (pageNo == 1) {
            mAdapter.setNewData(list);
        } else {
            mAdapter.addData(list);
        }
        pageNo++;
        if (pageNo >= totalPage) {
            mAdapter.setEnableLoadMore(false);
        } else {
            mAdapter.setEnableLoadMore(false);
        }
        mRefreshLayout.setRefreshing(false);
        mAdapter.loadMoreComplete();
    }

    @Override
    public void showTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    @Override
    public void onFailure() {
        if (mRefreshLayout != null)
            mRefreshLayout.setRefreshing(false);
        mAdapter.loadMoreComplete();
    }

    @Override
    public void setPresenter(GategoryListContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
        if (mAdapter != null){
            mAdapter.notifyDataSetChanged();
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
    public void onDestroy() {
        super.onDestroy();
        List<DownloadItem> list = mAdapter.getData();
        if (list != null)
            for (DownloadItem each : list) {
                if (each.disposable != null)
                    Utils.dispose(each.disposable);
            }
    }
}
