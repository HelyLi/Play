package com.hhly.lyygame.presentation.view.game;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.protocol.game.GameByIdInfoResp;
import com.hhly.lyygame.presentation.downloadutils.DownloadBtnController;
import com.hhly.lyygame.presentation.downloadutils.DownloadItem;
import com.hhly.lyygame.presentation.utils.CollectionUtil;
import com.hhly.lyygame.presentation.utils.DateUtils;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.activity.ActivitiesCenterFragment;
import com.hhly.lyygame.presentation.view.widget.DownloadProgressButton;
import com.hhly.lyygame.presentation.view.widget.ExViewPager;
import com.orhanobut.logger.Logger;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import info.hoang8f.android.segmented.SegmentedGroup;
import zlc.season.rxdownload2.entity.DownloadRecord;

import static com.hhly.lyygame.App.mContext;

/**
 * 游戏介绍
 * Created by Simon on 2016/12/1.
 */

public class GameIntroFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener, GameContract.IntrolView {

    @BindView(R.id.game_cover_iv)
    ImageView mGameCoverIv;
    @BindView(R.id.game_icon_iv)
    ImageView mGameIconIv;
    @BindView(R.id.game_name_tv)
    TextView mGameNameTv;
    @BindView(R.id.game_description_tv)
    TextView mGameDescriptionTv;
    @BindView(R.id.segment_group)
    SegmentedGroup mSegmentGroup;
    @BindView(R.id.viewPager)
    ExViewPager mViewPager;
    @BindView(R.id.game_opt_pb)
    DownloadProgressButton mOptPb;

    public static final String GAME_INTRO_ID = "extra_game_id";
    public static final String GAME_AD_JUMP = "extra_ad_jump";
    public static final String GAME_NOTICE_ID = "extra_notice_id";
    public static final String GAME_IMAGE_TYPE = "extra_image_type";

    private static final int POS_DETAIL = 0;
    private static final int POS_ACTIVITY = 1;
    private static final int POS_NOTIFICATION = 2;
    private static final int POS_GIFT = 3;

    private SegmentAdapter mSegmentAdapter;
    private DownloadBtnController mDownloadController;
    private GameContract.IntroPresenter mPresenter;
    private int mGameId;

    public static GameIntroFragment newInstance(int gameId) {
        GameIntroFragment fragment = new GameIntroFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(GAME_INTRO_ID, gameId);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGameId = getArguments().getInt(GAME_INTRO_ID, 0);
        if (mGameId == 0) {
            getActivity().finish();
            return;
        }

        mSegmentAdapter = new SegmentAdapter(getChildFragmentManager());
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {

        mSegmentGroup.setOnCheckedChangeListener(this);
        mViewPager.setOffscreenPageLimit(mSegmentGroup.getChildCount());
        mViewPager.setAdapter(mSegmentAdapter);
        postDelay(new Runnable() {
            @Override
            public void run() {
                mPresenter.getGameByIdInfo(mGameId);
            }
        }, 200);
        mDownloadController = new DownloadBtnController(mOptPb, getActivity());
    }


    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.subscribe();
        }
        if (mGameIntro != null && mDownloadController != null){
            
            DownloadItem data = new DownloadItem();
            data.record = new DownloadRecord();
            data.record.setUrl(mGameIntro.getPackeChannelUrl());
            data.record.setGameId(mGameIntro.getId());
            data.record.setPicUrl(mGameIntro.getIconUrl());
            data.record.setGameId(mGameIntro.getId());
            data.record.setDate(mGameIntro.getRegistTime());
            data.record.setApkName(mGameIntro.getName());
            data.record.setPackageName(mGameIntro.getPackageName());
            data.record.setSize(mGameIntro.getPackageSize());
            data.versionCode = mGameIntro.getVersionCode();

            mDownloadController.setData(data);
        }
    }

    @Override
    protected void fetchData(boolean isLoadMore) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDownloadController != null) {
            mDownloadController.release();
        }
        if (mPresenter != null) {
            mPresenter.unsubscribe();
        }
    }

    @OnClick(R.id.game_opt_pb)
    void onOptClick(View view) {
        if (mDownloadController != null)
            mDownloadController.handleClick(false);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_game_intro_layout;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.segment_detail:
                mViewPager.setCurrentItem(POS_DETAIL, false);
                break;
            case R.id.segment_activity:
                mViewPager.setCurrentItem(POS_ACTIVITY, false);
                break;
            case R.id.segment_notification:
                mViewPager.setCurrentItem(POS_NOTIFICATION, false);
                break;
//            case R.id.segment_gift:
//                mViewPager.setCurrentItem(POS_GIFT, false);
//                break;
        }
    }

    private GameByIdInfoResp.GameByIdInfo mGameIntro;

    //游戏详情信息
    @Override
    public void showGameInfo(GameByIdInfoResp.GameByIdInfo gameBean) {
        if (gameBean == null) return;

        if (mDownloadController != null) {

            DownloadItem data = new DownloadItem();
            data.record = new DownloadRecord();
            data.record.setUrl(gameBean.getPackeChannelUrl());
            data.record.setGameId(gameBean.getId());
            data.record.setPicUrl(gameBean.getIconUrl());
            data.record.setGameId(gameBean.getId());
            data.record.setDate(gameBean.getRegistTime());
            data.record.setApkName(gameBean.getName());
            data.record.setPackageName(gameBean.getPackageName());
            data.record.setSize(gameBean.getPackageSize());
//            data.packageSize = gameBean.getPackageSize();
            data.versionCode = gameBean.getVersionCode();

            Logger.d("apkBean.first=" + data.record.toString());

            mDownloadController.setData(data);
        }

        mGameIntro = gameBean;
        //获取游戏类型

        processGameType(gameBean.getGameType());

        //图片
        Glide.with(mContext).load(gameBean.getTittleimgUrl())
                .centerCrop()
                .placeholder(R.drawable.ic_game_pic_default_03)
                .error(R.drawable.ic_game_pic_default_03)
                .into(mGameCoverIv);
        Glide.with(mContext).load(gameBean.getTittleimgUrl())
                .centerCrop()
                .placeholder(R.drawable.ic_game_pic_default_01)
                .error(R.drawable.ic_game_pic_default_01)
                .into(mGameIconIv);
        //名称
        mGameNameTv.setText(gameBean.getName());

        Logger.d("apkurl=" + gameBean.getSourUrl());
    }

    private void processGameType(List<GameByIdInfoResp.GameByIdInfo.GameTypeBean> gameTypeList) {
        if (CollectionUtil.isEmpty(gameTypeList)) return;

        StringBuilder content = new StringBuilder();

        for (GameByIdInfoResp.GameByIdInfo.GameTypeBean gameTYpe : gameTypeList) {

            content.append(gameTYpe.getGameTypeName() + "・");
        }

        content.append(mGameIntro.getPopularitVal() + "人在玩・" + DateUtils.formatDate(new Date(mGameIntro.getRegistTime())) + "发布");

        mGameDescriptionTv.setText(content.toString());
    }

    @Override
    public void setPresenter(GameContract.IntroPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    private GameDetailFragment mGameDetailFragment;
    private ActivitiesCenterFragment mActivitiesFragment;
    private GameNoticeFragment mGameNoticeFragment;
    private GameGiftFragment mGameGiftFragment;

    private class SegmentAdapter extends FragmentStatePagerAdapter {

        public SegmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case POS_DETAIL://详情
                    if (mGameDetailFragment == null) {
                        mGameDetailFragment = GameDetailFragment.newInstance(mGameId, 2);
                    }
                    return mGameDetailFragment;
                case POS_ACTIVITY://活动
                    if (mActivitiesFragment == null) {
                        mActivitiesFragment = ActivitiesCenterFragment.newInstance(new int[]{mGameId, 2});
                    }
                    return mActivitiesFragment;
                case POS_NOTIFICATION://公告
                    if (mGameNoticeFragment == null) {
                        mGameNoticeFragment = GameNoticeFragment.newInstance(new int[]{mGameId, 1});
                    }
                    return mGameNoticeFragment;
//                case POS_GIFT://礼包
//                    if (mGameGiftFragment == null) {
//                        mGameGiftFragment = GameGiftFragment.newInstance(mGameId);
//                    }
//                    return mGameGiftFragment;
            }
            return new Fragment();
        }

        @Override
        public int getCount() {
            return mSegmentGroup.getChildCount();
        }
    }
}


