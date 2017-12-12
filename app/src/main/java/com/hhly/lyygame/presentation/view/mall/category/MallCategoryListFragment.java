package com.hhly.lyygame.presentation.view.mall.category;

import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.db.manager.AccountManager;
import com.hhly.lyygame.data.model.GoodsInfo;
import com.hhly.lyygame.presentation.utils.ActivityUtil;
import com.hhly.lyygame.presentation.utils.NetworkUtil;
import com.hhly.lyygame.presentation.utils.SharedPrefsFavouriteUtil;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.DialogFactory;
import com.hhly.lyygame.presentation.view.ToastUtil;
import com.hhly.lyygame.presentation.view.immersive.IImmersiveApply;
import com.hhly.lyygame.presentation.view.mall.MallOptDialog;
import com.hhly.lyygame.presentation.view.widget.RecyclerViewGridDivide;
import com.orhanobut.logger.Logger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import butterknife.BindView;

/**
 * 商品分类
 * Created by Simon on 2016/11/23.
 * 同一类型的列表
 */

public class MallCategoryListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener, IImmersiveApply, MallListContract.View, MallOptDialog.MallStateCallback {

    public static final String EXTRA_CATEGORY = "extra_category";

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    protected MallListAdapter mMallListAdapter;
    private MallListContract.Presenter mPresenter;
    protected int mCategory;

    private GoodsInfo mGoodsInfo;

    private int pageNo = 1;
    private int pageSize = 40;

    public static MallCategoryListFragment newInstance(int category) {
        MallCategoryListFragment fragment = new MallCategoryListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_CATEGORY, category);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCategory = getArguments().getInt(EXTRA_CATEGORY, CATEGORY_ALL);
        }
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {

        setToolbalTitle(mCategory);

        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setRefreshing(true);
        mRecyclerView.addItemDecoration(new RecyclerViewGridDivide(getActivity(), R.color.transparent, 10));
        mMallListAdapter = new MallListAdapter();
        mMallListAdapter.setEnableLoadMore(true);
        mMallListAdapter.setOnLoadMoreListener(this, mRecyclerView);

        mRecyclerView.setAdapter(mMallListAdapter);
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                if (!ActivityUtil.checkLoginAndRequestLogin(getActivity()))
                    return;//登录用户
                GoodsInfo goodsInfo = (GoodsInfo) baseQuickAdapter.getItem(i);
                Logger.d("goods.type=" + goodsInfo.getType());
                if (!NetworkUtil.isAvailable(getActivity())){
                    ToastUtil.showTip(getActivity(),R.string.lyy_game_network_state);
                    return;
                }
                switch (view.getId()) {
                    case R.id.mall_item_exchange_btn:
                        DialogFactory.createMallOpt(getActivity(), goodsInfo, MallOptDialog.TYPE_EXCHANGE, MallCategoryListFragment.this).show();
                        break;
                    case R.id.mall_item_favourite_btn:
                        SharedPrefsFavouriteUtil.putBooleanValue(getActivity(), "isDeleleFavourite", true);
                        mGoodsInfo = goodsInfo;
                        if (TextUtils.isEmpty(goodsInfo.getUserId()) || !goodsInfo.getUserId().equals(AccountManager.getInstance().getUserId())) {
                            //收藏
                            DialogFactory.createMallOpt(getActivity(), goodsInfo, MallOptDialog.TYPE_FAVOURITE, MallCategoryListFragment.this).show();
                        } else {
                            //取消收藏
                            mPresenter.cancelGoods(goodsInfo.getId());
                        }
                        break;
                    default:
                        break;
                }
            }
        });

        mPresenter.getGoodsList(true, pageNo, pageSize, mCategory);
    }

    @Override
    protected void fetchData(boolean isLoadMore) {

    }

    private void setToolbalTitle(int category) {

        String title = null;

        switch (category) {
            case CATEGORY_ALL:
                title = getString(R.string.lyy_category_all);
                break;
            case CATEGORY_COUPON_VIR:
            case CATEGORY_SCORE_VIR:
                title = getString(R.string.lyy_category_vir);
                break;
            case CATEGORY_COUPON_MAT:
            case CATEGORY_SCORE_MAT:
                title = getString(R.string.lyy_category_mat);
                break;
        }

        mToolbarHelper.setTitle(title);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mall_category_layout;
    }

    @Override
    public void onRefresh() {
        pageNo = 1;
        mMallListAdapter.setEnableLoadMore(true);
        mPresenter.getGoodsList(true, pageNo, pageSize, mCategory);
    }

    @Override
    public void onLoadMoreRequested() {
        if (pageNo == 1)
            return;
        mPresenter.getGoodsList(true, pageNo, pageSize, mCategory);
    }

    //全部
    public static final int CATEGORY_ALL = 0;
    //乐盈卷兑换 - 虚拟物品
    public static final int CATEGORY_COUPON_VIR = 1;
    //乐盈卷兑换 - 实物
    public static final int CATEGORY_COUPON_MAT = 2;
    //积分兑换 - 虚拟物品
    public static final int CATEGORY_SCORE_VIR = 3;
    //积分兑换 - 实物
    public static final int CATEGORY_SCORE_MAT = 4;

    @Override
    public void showGoodsList(List<GoodsInfo> list) {
        mRefreshLayout.setRefreshing(false);
        mMallListAdapter.loadMoreComplete();

        if (pageNo == 1) {
            mMallListAdapter.setNewData(list);
        } else {
            mMallListAdapter.addData(list);
        }
        pageNo++;
        if (list.size() < pageSize) {
            mMallListAdapter.setEnableLoadMore(false);
        } else if (list.size() == 0) {
            mPresenter.getGoodsList(true, pageNo, pageSize, mCategory);
        }

    }

    @Override
    public void onFailure() {
        mRefreshLayout.setRefreshing(false);
        mMallListAdapter.loadMoreComplete();
    }

    @Override
    public void cancelGoodsSuccess(int goodsId) {
        ToastUtil.showTip(getActivity(), R.string.lyy_cancel_favourite);
        mGoodsInfo.setUserId(null);
        mMallListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onEmptyView() {
        if (mRefreshLayout != null)
            mRefreshLayout.setRefreshing(false);
        View emptyView = LayoutInflater.from(getActivity()).inflate(R.layout.widget_empty_imageview, null, false);
        mMallListAdapter.setEmptyView(emptyView);
    }

    @Override
    public void setPresenter(MallListContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
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
    public void addStoreSuccess() {
        mGoodsInfo.setUserId(AccountManager.getInstance().getUserId());
        mMallListAdapter.notifyDataSetChanged();
    }

    @IntDef({CATEGORY_ALL, CATEGORY_COUPON_VIR, CATEGORY_COUPON_MAT, CATEGORY_SCORE_VIR, CATEGORY_SCORE_MAT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Category {
    }

}
