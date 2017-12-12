package com.hhly.lyygame.presentation.view.mall;

import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.db.manager.AccountManager;
import com.hhly.lyygame.data.model.GoodsInfo;
import com.hhly.lyygame.data.net.protocol.user.State;
import com.hhly.lyygame.presentation.utils.ActivityUtil;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.DialogFactory;
import com.hhly.lyygame.presentation.view.ToastUtil;
import com.orhanobut.logger.Logger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import butterknife.BindView;

import static com.hhly.lyygame.R.id.recyclerView;

/**
 * 商品分类
 * Created by Simon on 2016/11/23.
 */

public class MallCategoryFragment extends BaseFragment implements MallContract.View, MallOptDialog.MallStateCallback {

    public static final String EXTRA_CATEGORY = "extra_category";

    @BindView(recyclerView)
    RecyclerView mRecyclerView;

    private MallCategoryAdapter mAdapter;
    private MallContract.Presenter mPresenter;

    protected int mCategory;

    public static MallCategoryFragment newInstance(@Category int category) {
        MallCategoryFragment fragment = new MallCategoryFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_CATEGORY, category);
        fragment.setArguments(bundle);
        return fragment;
    }

    public MallCategoryFragment() {
        new MallPresenter(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCategory = getArguments().getInt(EXTRA_CATEGORY, CATEGORY_ALL);//
        }
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        initAdapter();
    }


    private void initAdapter() {
        mAdapter = new MallCategoryAdapter(getContext(), mCategory);
        mAdapter.setOnItemChildClickListener(mListener);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void fetchData(boolean isLoadMore) {
        showRefresh();
        switch (mCategory) {
            case MallCategoryFragment.CATEGORY_COUPON://乐盈卷
                mPresenter.getGoodsList(isLoadMore, State.MallType.COUPON);
                break;
            case MallCategoryFragment.CATEGORY_SCORE://积分
                mPresenter.getGoodsList(isLoadMore, State.MallType.SCORE);
                break;
            default:
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.widget_recycler_view;
    }

    public void forceFetchData(boolean refresh) {
        fetchData(refresh);
    }

    void showRefresh() {
        if (getParentFragment() != null && getParentFragment() instanceof MallFragment) {
            ((MallFragment) getParentFragment()).showRefresh();
        }
    }

    void hideRefresh() {
        if (getParentFragment() != null && getParentFragment() instanceof MallFragment) {
            ((MallFragment) getParentFragment()).hideRefresh();
        }
    }

    public static final int CATEGORY_ALL = 0;
    //乐盈卷兑换
    public static final int CATEGORY_COUPON = 1;
    //积分兑换
    public static final int CATEGORY_SCORE = 2;

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
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailure() {
        hideRefresh();
    }

    @Override
    public void cancelGoodsSuccess(int goodsId) {
        ToastUtil.showTip(getActivity(), R.string.lyy_cancel_favourite);
        fetchData(false);
    }

    BaseQuickAdapter.OnItemChildClickListener mListener = new BaseQuickAdapter.OnItemChildClickListener() {
        @Override
        public void onItemChildClick(BaseQuickAdapter adapter, View view, int i) {
            if (!ActivityUtil.checkLoginAndRequestLogin(getActivity()))
                return;//登录用户
            GoodsInfo goodsInfo = (GoodsInfo) adapter.getItem(i);
            Logger.d("goods.type=" + goodsInfo.getType());

            switch (view.getId()) {
                case R.id.mall_item_exchange_btn:
                    DialogFactory.createMallOpt(getActivity(), goodsInfo, MallOptDialog.TYPE_EXCHANGE, MallCategoryFragment.this).show();
                    break;
                case R.id.mall_item_favourite_btn:
                    if (TextUtils.isEmpty(goodsInfo.getUserId()) || !goodsInfo.getUserId().equals(AccountManager.getInstance().getUserId())) {
                        DialogFactory.createMallOpt(getActivity(), goodsInfo, MallOptDialog.TYPE_FAVOURITE, MallCategoryFragment.this).show();
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
        fetchData(false);
    }

    @IntDef({CATEGORY_ALL, CATEGORY_COUPON, CATEGORY_SCORE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Category {
    }
}
