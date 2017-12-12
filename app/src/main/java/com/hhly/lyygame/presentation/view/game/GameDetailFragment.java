package com.hhly.lyygame.presentation.view.game;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.protocol.game.GameByIdInfoResp;
import com.hhly.lyygame.data.net.protocol.game.GamePictureInfoResp;
import com.hhly.lyygame.presentation.utils.CollectionUtil;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.widget.RecyclerViewListDivide;
import com.hhly.lyygame.presentation.view.widget.RecyclerViewListFirstDivide;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Simon on 2016/12/2.
 */

public class GameDetailFragment extends BaseFragment implements GameContract.DetailView {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.game_intro_content_tv)
    TextView mGameIntroContentTv;

    private GamePictureAdapter mPictureAdapter;
    private GameContract.DetailPresenter mPresenter;

    public GameDetailFragment() {
        new DetailPresenterImpl(this);
    }

    public static GameDetailFragment newInstance(int gameId, int gameType) {
        GameDetailFragment fragment = new GameDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(GameIntroFragment.GAME_INTRO_ID, gameId);
        bundle.putInt(GameIntroFragment.GAME_IMAGE_TYPE, gameType);
        fragment.setArguments(bundle);
        return fragment;
    }

    private int mGameId;
    private int mImageType;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGameId = getArguments().getInt(GameIntroFragment.GAME_INTRO_ID);
        mImageType = getArguments().getInt(GameIntroFragment.GAME_IMAGE_TYPE);

        mPictureAdapter = new GamePictureAdapter();
        postDelay(new Runnable() {
            @Override
            public void run() {
              fetchData(false);
            }
        }, 300);
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.addItemDecoration(new RecyclerViewListDivide(getActivity(), R.color.transparent, RecyclerViewListDivide.HORIZONTAL_LIST, 15));
        mRecyclerView.addItemDecoration(new RecyclerViewListFirstDivide(getActivity(), RecyclerViewListDivide.HORIZONTAL_LIST, 15));
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setAdapter(mPictureAdapter);

    }

    @Override
    protected void fetchData(boolean loadMore) {
        mPresenter.getGamePic(mGameId, mImageType);
        mPresenter.getGameByIdInfo(mGameId);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_game_detail_layout;
    }

    @Override
    public void showGamePic(List<GamePictureInfoResp.GamePicPager.GamePic> gamePicList) {
        if (CollectionUtil.isEmpty(gamePicList)) return;
        mPictureAdapter.setNewData(gamePicList);
    }

    @Override
    public void showGameInfo(GameByIdInfoResp.GameByIdInfo gameBean) {
        if (gameBean == null) return;
        mGameIntroContentTv.setText(gameBean.getDesc() == null ? "" : gameBean.getDesc());
    }

    @Override
    public void setPresenter(GameContract.DetailPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return false;
    }

}
