package com.hhly.lyygame.presentation.view.mall.coupon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.db.manager.AccountManager;
import com.hhly.lyygame.data.model.GoodsInfo;
import com.hhly.lyygame.data.net.protocol.user.State;
import com.hhly.lyygame.presentation.utils.ActivityUtil;
import com.hhly.lyygame.presentation.utils.NetworkUtil;
import com.hhly.lyygame.presentation.utils.SharedPrefsFavouriteUtil;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.DialogFactory;
import com.hhly.lyygame.presentation.view.ToastUtil;
import com.hhly.lyygame.presentation.view.banner.BannerHeader;
import com.hhly.lyygame.presentation.view.banner.MallBannerHeader;
import com.hhly.lyygame.presentation.view.immersive.IImmersiveApply;
import com.hhly.lyygame.presentation.view.mall.MallCategoryAdapter;
import com.hhly.lyygame.presentation.view.mall.MallCategoryFragment;
import com.hhly.lyygame.presentation.view.mall.MallContract;
import com.hhly.lyygame.presentation.view.mall.MallOptDialog;
import com.hhly.lyygame.presentation.view.mall.MallPresenter;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;

/**
 * Created by dell on 2017/5/18.
 */

public class CouponFragment extends BaseFragment implements MallContract.View,IImmersiveApply, SwipeRefreshLayout.OnRefreshListener, MallOptDialog.MallStateCallback{

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private BannerHeader mBannerHeader;
    private MallCategoryAdapter mAdapter;
    private MallContract.Presenter mPresenter;

    private int mCategory = State.MallType.COUPON;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new MallPresenter(this);
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRefreshLayout.setOnRefreshListener(this);
        initAdapter(view);
        mRefreshLayout.setRefreshing(true);
        fetchData(false);
    }

    private void initAdapter(View view) {
        mAdapter = new MallCategoryAdapter(getContext(), MallCategoryFragment.CATEGORY_COUPON);
        ViewGroup viewGroup = null;
        if (view instanceof ViewGroup) {
            viewGroup = (ViewGroup) view;
        }
        if (viewGroup == null) {
            Logger.d("viewGroup is null");
        }

        View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_game_coupon_header_layout, viewGroup, false);
        mBannerHeader = (MallBannerHeader) headerView.findViewById(R.id.banner_header);

        mAdapter.addHeaderView(headerView);

        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnMatItemChildClickListener(mMatListener);
        mAdapter.setOnVirItemChildClickListener(mVirListener);
    }

    @Override
    protected boolean enableHomeAsUp() {
        return false;
    }

    @Override
    protected void fetchData(boolean isLoadMore) {
        postDelay(new Runnable() {
            @Override
            public void run() {
                mBannerHeader.fetchMallData();
                mPresenter.getGoodsList(false, mCategory);
            }
        }, 500);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mBannerHeader != null) {
            mBannerHeader.onStart();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mRefreshLayout != null && mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mBannerHeader != null) {
            mBannerHeader.onStop();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_game_cateogry_layout;
    }

    @Override
    public void onRefresh() {
        fetchData(false);
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
    public void setPresenter(MallContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showGoodsList(List<GoodsInfo> list) {
        hideRefresh();
        mAdapter.update(list);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mToolbarHelper != null) {
            mToolbarHelper.setTitle(getString(R.string.tab_mall));
        }
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
        if (SharedPrefsFavouriteUtil.getBooleanValue(getActivity(), "isDeleleFavourite", false)) {
            SharedPrefsFavouriteUtil.putBooleanValue(getActivity(), "isDeleleFavourite", false);
            mPresenter.getGoodsList(false, mCategory);
        }
    }

    @Override
    public void onFailure() {
        hideRefresh();
    }

    private void hideRefresh(){
        if (mRefreshLayout != null && mRefreshLayout.isRefreshing()){
            mRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void cancelGoodsSuccess(int goodsId) {
        ToastUtil.showTip(getActivity(), R.string.lyy_cancel_favourite);
        fetchData(false);
    }

    BaseQuickAdapter.OnItemChildClickListener mMatListener = new BaseQuickAdapter.OnItemChildClickListener() {
        @Override
        public void onItemChildClick(BaseQuickAdapter adapter, View view, int i) {
            if (!ActivityUtil.checkLoginAndRequestLogin(getActivity()))
                return;//登录用户

            if (null == mAdapter) return;
            if (null == mAdapter.getMatGoodsList()) return;
            if (!(i < mAdapter.getMatGoodsList().size()))return;

            GoodsInfo goodsInfo = mAdapter.getMatGoodsList().get(i);
            Logger.d("goods.type=" + goodsInfo);
            if (!NetworkUtil.isAvailable(getActivity())){
                ToastUtil.showTip(getActivity(),R.string.lyy_game_network_state);
                return;
            }
            switch (view.getId()) {
                case R.id.mall_item_exchange_btn:
                    DialogFactory.createMallOpt(getActivity(), goodsInfo, MallOptDialog.TYPE_EXCHANGE, CouponFragment.this).show();
                    break;
                case R.id.mall_item_favourite_btn:
                    if (TextUtils.isEmpty(goodsInfo.getUserId()) || !goodsInfo.getUserId().equals(AccountManager.getInstance().getUserId())) {
                        DialogFactory.createMallOpt(getActivity(), goodsInfo, MallOptDialog.TYPE_FAVOURITE, CouponFragment.this).show();
                    } else {
                        mPresenter.cancelGoods(goodsInfo.getId());
                    }
                    break;
                default:
                    break;
            }
        }
    };

    BaseQuickAdapter.OnItemChildClickListener mVirListener = new BaseQuickAdapter.OnItemChildClickListener() {
        @Override
        public void onItemChildClick(BaseQuickAdapter adapter, View view, int i) {
            if (!ActivityUtil.checkLoginAndRequestLogin(getActivity()))
                return;//登录用户

            if (!(i < mAdapter.getVirGoodsList().size()))return;

            GoodsInfo goodsInfo = mAdapter.getVirGoodsList().get(i);
            Logger.d("goods.type=" + goodsInfo);
            if (!NetworkUtil.isAvailable(getActivity())){
                ToastUtil.showTip(getActivity(),R.string.lyy_game_network_state);
                return;
            }
            switch (view.getId()) {
                case R.id.mall_item_exchange_btn:
                    DialogFactory.createMallOpt(getActivity(), goodsInfo, MallOptDialog.TYPE_EXCHANGE, CouponFragment.this).show();
                    break;
                case R.id.mall_item_favourite_btn:
                    if (TextUtils.isEmpty(goodsInfo.getUserId()) || !goodsInfo.getUserId().equals(AccountManager.getInstance().getUserId())) {
                        DialogFactory.createMallOpt(getActivity(), goodsInfo, MallOptDialog.TYPE_FAVOURITE, CouponFragment.this).show();
                    } else {
                        mPresenter.cancelGoods(goodsInfo.getId());
                    }
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void addStoreSuccess() {
        mPresenter.getGoodsList(false, mCategory);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK){
            mPresenter.getGoodsList(false, mCategory);
        }
    }

}
