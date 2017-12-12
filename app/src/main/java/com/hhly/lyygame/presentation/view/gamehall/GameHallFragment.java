package com.hhly.lyygame.presentation.view.gamehall;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.gamehall.model.CategoryItem;
import com.hhly.lyygame.presentation.view.gamehall.model.GameGroup;
import com.hhly.lyygame.presentation.view.immersive.IImmersiveApply;
import com.hhly.lyygame.presentation.view.widget.RecyclerViewListDivide;
import com.hhly.lyygame.presentation.view.widget.RecyclerViewListFirstDivide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Simon on 2016/11/25.
 */

public class GameHallFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, IImmersiveApply {

    @BindView(R.id.scroll_top_fab)
    FloatingActionButton mScrollTopFab;

    public static GameHallFragment newInstance() {
        return new GameHallFragment();
    }


    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
//    @BindView(R.id.refreshLayout)
//    NestedSwipeRefreshLayout mRefreshLayout;

    private RecyclerView mCategoryRecyclerView;

    private GameHallAdapter mHallAdapter;

    private int mScrolledY;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_game_hall_layout;
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        mRefreshLayout.setOnRefreshListener(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mHallAdapter = new GameHallAdapter(getActivity());
        View categoryView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_game_hall_category_layout, null, false);
        mCategoryRecyclerView = (RecyclerView) categoryView.findViewById(R.id.recyclerView);
        mHallAdapter.addHeaderView(categoryView);
        GameHallCategoryAdapter gameHallCategoryAdapter = new GameHallCategoryAdapter();
        gameHallCategoryAdapter.setNewData(generateCategory());
        mCategoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mCategoryRecyclerView.addItemDecoration(new RecyclerViewListDivide(getActivity(), R.color.transparent, RecyclerViewListDivide.HORIZONTAL_LIST, 20));
        mCategoryRecyclerView.addItemDecoration(new RecyclerViewListFirstDivide(getActivity(), getResources().getColor(R.color.transparent), RecyclerViewListDivide.HORIZONTAL_LIST, 24));
        mCategoryRecyclerView.setAdapter(gameHallCategoryAdapter);
        mCategoryRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
//                ActivityCompat.startActivity(getActivity(), GameCategoryListActivity.getCallingIntent(getActivity()), null);
            }

        });

        detectScrollTop();

        mRecyclerView.addItemDecoration(new RecyclerViewListDivide(getActivity(), R.color.color_e9e9e9, RecyclerViewListDivide.VERTICAL_LIST, 10));

        mRecyclerView.addOnScrollListener(mFabScrollListener);

        mRecyclerView.setAdapter(mHallAdapter);

        mRefreshLayout.setRefreshing(true);
        onRefresh();

    }

    @Override
    protected void fetchData(boolean isLoadMore) {

    }

    private void detectScrollTop() {
        if (mScrolledY > 0) {
            mScrollTopFab.show();
        } else {
            mScrollTopFab.hide();
        }
    }

    private List<CategoryItem> generateCategory() {
        List<CategoryItem> categoryItems = new ArrayList<>();
        categoryItems.add(new CategoryItem(R.drawable.ic_category_rec, R.string.lyy_category_rec, 0));
        categoryItems.add(new CategoryItem(R.drawable.ic_category_3d, R.string.lyy_category_3d, 1));
        categoryItems.add(new CategoryItem(R.drawable.ic_category_slg, R.string.lyy_category_slg, 2));
        categoryItems.add(new CategoryItem(R.drawable.ic_category_casual, R.string.lyy_category_casual, 3));
        categoryItems.add(new CategoryItem(R.drawable.ic_category_rpg, R.string.lyy_category_rpg, 4));
        categoryItems.add(new CategoryItem(R.drawable.ic_category_chess, R.string.lyy_category_chess, 5));
        categoryItems.add(new CategoryItem(R.drawable.ic_category_shoot, R.string.lyy_category_shoot, 6));
        return categoryItems;
    }

    @Override
    public void onRefresh() {

        postDelay(new Runnable() {
            @Override
            public void run() {
                mHallAdapter.setNewData(generate());
                mRefreshLayout.setRefreshing(false);
            }
        }, 1000);

    }

    private List<GameGroup> generate() {
        List<GameGroup> gameGroupList = new ArrayList<>();

        gameGroupList.add(new GameGroup.Builder().enableMore(false)
                .sectionIcon(R.drawable.ic_section_played)
                .sectionTitleResId(R.string.lyy_section_played)
                .type(GameGroup.PLAYED).build().setDataList(Arrays.asList(
                        new Object(), new Object(), new Object(), new Object(), new Object(), new Object(), new Object(), new Object()
                )));

        gameGroupList.add(new GameGroup.Builder().enableMore(true)
                .sectionIcon(R.drawable.ic_section_hot)
                .sectionTitleResId(R.string.lyy_section_hot)
                .type(GameGroup.HOT).build().setDataList(Arrays.asList(
                        new Object(), new Object(), new Object(), new Object(), new Object(), new Object(), new Object(), new Object()
                )));

        gameGroupList.add(new GameGroup.Builder().enableMore(false)
                .sectionIcon(R.drawable.ic_section_online)
                .sectionTitleResId(R.string.lyy_section_online)
                .type(GameGroup.ONLINE).build().setDataList(Arrays.asList(
                        new Object(), new Object(), new Object(), new Object()
                )));

        gameGroupList.add(new GameGroup.Builder().enableMore(true)
                .sectionIcon(R.drawable.ic_section_offline)
                .sectionTitleResId(R.string.lyy_section_offline)
                .type(GameGroup.OFFLINE).build().setDataList(Arrays.asList(
                        new Object(), new Object(), new Object(), new Object(), new Object(), new Object(), new Object(), new Object()
                )));

        gameGroupList.add(new GameGroup.Builder().enableMore(false)
                .sectionIcon(R.drawable.ic_section_new)
                .sectionTitleResId(R.string.lyy_section_new)
                .type(GameGroup.NEW).build().setDataList(Arrays.asList(
                        new Object(), new Object(), new Object()
                )));

        gameGroupList.add(new GameGroup.Builder().enableMore(true)
                .sectionIcon(R.drawable.ic_section_rec_weekly)
                .sectionTitleResId(R.string.lyy_section_rec_weekly)
                .type(GameGroup.REC_WEEKLY).build().setDataList(Arrays.asList(
                        new Object(), new Object(), new Object(), new Object(), new Object(), new Object(), new Object(), new Object()
                )));

        return gameGroupList;
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

    @OnClick(R.id.scroll_top_fab)
    void onScrollTopFabClick() {
        mRecyclerView.smoothScrollToPosition(0);
    }

    private RecyclerView.OnScrollListener mFabScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            if (newState == RecyclerView.SCROLL_STATE_IDLE && mScrolledY > 0) {
                mScrollTopFab.show();
            }
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            mScrolledY += dy;
            mScrolledY = Math.max(0, mScrolledY);
            if (Math.abs(dy) > 0 && mScrollTopFab.isShown()) {
                mScrollTopFab.hide();
            }
            super.onScrolled(recyclerView, dx, dy);
        }
    };

}
