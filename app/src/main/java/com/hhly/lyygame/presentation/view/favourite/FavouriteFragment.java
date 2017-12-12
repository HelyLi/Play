package com.hhly.lyygame.presentation.view.favourite;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.hhly.lyygame.App;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.db.entry.UserInfo;
import com.hhly.lyygame.data.db.manager.AccountManager;
import com.hhly.lyygame.data.net.protocol.goods.GoodsExchangeReq;
import com.hhly.lyygame.data.net.protocol.goods.StoreGoodsResp;
import com.hhly.lyygame.data.net.protocol.user.State;
import com.hhly.lyygame.presentation.utils.CollectionUtil;
import com.hhly.lyygame.presentation.utils.DoubleClickUtil;
import com.hhly.lyygame.presentation.utils.NetworkUtil;
import com.hhly.lyygame.presentation.utils.SharedPrefsFavouriteUtil;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.ToastUtil;
import com.hhly.lyygame.presentation.view.immersive.IImmersiveApply;
import com.hhly.lyygame.presentation.view.order.OrderActivity;
import com.hhly.lyygame.presentation.view.widget.RecyclerViewDividerItem;
import com.orhanobut.logger.Logger;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的收藏
 * Created by Simon on 2016/11/29.
 */

public class FavouriteFragment extends BaseFragment implements IImmersiveApply,
        SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener, FavouriteContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    //    @BindView(R.id.refreshLayout)
    //    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.select_all_cb)
    AppCompatCheckBox mSelectAllCb;
    @BindView(R.id.price_tv)
    TextView mPriceTv;
    @BindView(R.id.balance_btn)
    Button mBalanceBtn;

    private boolean isSelectedAll = false;

    private FavouriteAdapter mFavouriteAdapter;
    private FavouriteContract.Presenter mPresenter;

    public static FavouriteFragment newInstance() {
        return new FavouriteFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFavouriteAdapter = new FavouriteAdapter(getActivity());
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setRefreshing(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new RecyclerViewDividerItem(getActivity(), R.color.color_e9e9e9, RecyclerViewDividerItem.VERTICAL_LIST, 1));

        View emptyView = LayoutInflater.from(getActivity()).inflate(R.layout.widget_empty_view, null, false);
        TextView favouriteEmptyTv = (TextView) emptyView.findViewById(R.id.empty_tv);
        favouriteEmptyTv.setText(R.string.lyy_favourite_empty_tip);
        mFavouriteAdapter.setEmptyView(emptyView);
        mRecyclerView.setAdapter(mFavouriteAdapter);

        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                //                if (DoubleClickUtil.isFastClick()) {
                //                    return;
                //                }
                FavouriteAdapter.FavouriteItemData goods = (FavouriteAdapter.FavouriteItemData) baseQuickAdapter.getItem(i);
                switch (view.getId()) {
//                    case R.id.favourite_cb:
//                        TextView checkBox = (TextView) view;
//                        processSelectGoods(goods.getGoodsBean(), !checkBox.isSelected(), checkBox);
//                        //                        checkBox.setSelected(!checkBox.isSelected());
//                        break;
                    case R.id.delete_favourite_iv:
                        if (!NetworkUtil.isAvailable(App.getContext())) {
                            ToastUtil.showTip(App.getContext(), R.string.lyy_network_notwork);
                            return;
                        }
                        SharedPrefsFavouriteUtil.putBooleanValue(getActivity(), "isDeleleFavourite", true);
                        delStoreGoods(goods.getGoodsBean().getId());
                        break;
                    default:
                        break;
                }
            }
        });

        postDelay(new Runnable() {
            @Override
            public void run() {
                fetchData(false);
            }
        }, 500);
    }

    private void delStoreGoods(int goodsId) {

        //        for (StoreGoodsResp.StoreGoodsBean storeGoodsBean : mAllGoodsList) {
        //            if (storeGoodsBean.getId() == goodsId) {
        //                mAllGoodsList.remove(storeGoodsBean);
        //                break;
        //            }
        //        }
        mPresenter.delGoods(goodsId);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_favourite_layout;
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
    public void onRefresh() {
        if (!NetworkUtil.isAvailable(App.getContext())) {
            mRefreshLayout.setRefreshing(false);
            return;
        }
        fetchData(false);
    }

    @Override
    protected void fetchData(boolean loadMore) {
        //        mRefreshLayout.setRefreshing(true);
        mPresenter.getStoreGoods(AccountManager.getInstance().getUserId());
    }

    @OnClick(R.id.select_all_cb)
    void onSelectAllClick() {
        List<FavouriteAdapter.FavouriteItemData> list = mFavouriteAdapter.getData();
        if (CollectionUtil.isEmpty(list)) {
            mSelectAllCb.setChecked(true);
            return;
        }
        if (sameNeedScoreId()) {
            isSelectedAll = !isSelectedAll;
            processAllGoods(isSelectedAll);
        } else {
            ToastUtil.showTip(getActivity(), R.string.lyy_game_all_store_type);
        }
    }

    /**
     * 需要的金额与用户拥有的金额比较
     * 1：乐盈币 2：乐盈券 3：积分
     */
    private boolean processMoney() {

        switch (mNeedScore[0]) {
            case State.MallType.COINS:

                if (mNeedScore[1] > Long.parseLong(AccountManager.getInstance().getUserInfo().getLyb())) {
                    ToastUtil.showTip(getActivity(), R.string.lyy_game_coins_lack);
                    return false;
                }
                break;
            case State.MallType.COUPON:

                if (mNeedScore[1] > Long.parseLong(AccountManager.getInstance().getUserInfo().getLyq())) {
                    ToastUtil.showTip(getActivity(), R.string.lyy_game_coupon_lack);
                    return false;
                }
                break;
            case State.MallType.SCORE:

                if (mNeedScore[1] > Long.parseLong(AccountManager.getInstance().getUserInfo().getJf())) {
                    ToastUtil.showTip(getActivity(), R.string.lyy_game_score_lack);
                    return false;
                }
                break;
            default:
                break;
        }
        return true;
    }

    /**
     * 结算按钮
     *
     * @param view
     */
    @OnClick(R.id.balance_btn)
    void onBalanceClick(View view) {
        if (DoubleClickUtil.isFastClick()) {
            return;
        }
        if (mRefreshLayout.isRefreshing()) {
            return;
        }
        if (sameNeedType()) {
            if (processMoney() && mAllGoodsList.size() > 0) {
                exchangeGoods(mAllGoodsList);
            }
        } else {
            ToastUtil.showTip(getActivity(), R.string.lyy_game_exchange_mat_and_vir);
        }
    }

    private void exchangeGoods(List<StoreGoodsResp.StoreGoodsBean> goodsInfos) {

        if (goodsInfos.get(0).getType() == State.GoodsType.MATTER) {//实物

            int[] goodsIds = new int[goodsInfos.size()];
            int[] goodsIdsNum = new int[goodsInfos.size()];
            for (int i = 0; i < goodsInfos.size(); i++) {
                goodsIds[i] = goodsInfos.get(i).getGoodsId();
                goodsIdsNum[i] = 1;
            }
            ActivityCompat.startActivityForResult(getActivity(), OrderActivity.getOrderCallingIntent(getContext(), goodsIds, goodsIdsNum), 0, null);
        } else if (goodsInfos.get(0).getType() == State.GoodsType.CARD) {//电话卡

            if (goodsInfos.size() == 1) {
                ActivityCompat.startActivityForResult(getActivity(), OrderActivity.getOrderCardCallingIntent(getContext(), goodsInfos.get(0).getGoodsId()), 0, null);
            } else {
                ToastUtil.showTip(getActivity(), "电话卡只能选择一种");
            }

        } else {//礼品

            //查积分
            mPresenter.getUserInfo(goodsInfos);


        }
    }

    /**
     * @param isSelectedAll
     */
    private void processAllGoods(boolean isSelectedAll) {
        int needScoreId = 0;

        List<FavouriteAdapter.FavouriteItemData> list = mFavouriteAdapter.getData();
        mAllGoodsList.clear();
        for (FavouriteAdapter.FavouriteItemData itemData : list) {
            itemData.setSelect(isSelectedAll);
            if (isSelectedAll) {
                mAllGoodsList.add(itemData.getGoodsBean());
                needScoreId = itemData.getGoodsBean().getNeedScoreId();
            } else {
                mAllGoodsList.clear();
            }
        }
        mFavouriteAdapter.notifyDataSetChanged();

        needScore(needScoreId);
    }

    /**
     * 处理类型(1乐盈币 2乐盈券 3积分)是否相同
     *
     * @return
     */
    private boolean sameNeedScoreId() {
        boolean same = true;
        List<FavouriteAdapter.FavouriteItemData> list = mFavouriteAdapter.getData();

        int newType = 0;
        int oldType = 0;

        for (FavouriteAdapter.FavouriteItemData goodsBean : list) {
            Logger.d(goodsBean.getGoodsBean());
            newType = goodsBean.getGoodsBean().getNeedScoreId();
            Logger.d("newType=" + newType);
            if (oldType != 0 && newType != oldType) {
                same = false;
                break;
            }
            oldType = newType;
            Logger.d("oldType=" + oldType);
        }
        Logger.d("same=" + same);
        return same;
    }

    /**
     * 处理奖品类型 1:实物 2:虚拟物品
     *
     * @return
     */
    private boolean sameNeedType() {
        boolean same = true;
        if (mAllGoodsList.size() == 1) {
            return same;
        }

        int newType = 0;
        int oldType = 0;

        for (StoreGoodsResp.StoreGoodsBean goodsBean : mAllGoodsList) {
            newType = goodsBean.getType();
            if (oldType != 0 && newType != oldType) {
                same = false;
                break;
            }
            oldType = newType;
        }
        return same;
    }

    private List<StoreGoodsResp.StoreGoodsBean> mAllGoodsList = new CopyOnWriteArrayList<>();

    /**
     * @param goodsBean
     * @param add       添加true ／ 删除false
     * @return
     */
    private void processSelectGoods(StoreGoodsResp.StoreGoodsBean goodsBean, boolean add, TextView view) {
        int needScoreId = 0;

        Logger.d("add" + add);

        if (add) {
            boolean enable = true;
            for (StoreGoodsResp.StoreGoodsBean goods : mAllGoodsList) {
                if (goods.getNeedScoreId() != goodsBean.getNeedScoreId()) {
                    enable = false;
                    break;
                }
            }
            if (enable) {
                needScoreId = goodsBean.getNeedScoreId();
                mAllGoodsList.add(goodsBean);
                view.setSelected(add);
            } else {
                //                view.setChecked(true);
                ToastUtil.showTip(getActivity(), R.string.lyy_game_add_store_not_same);
            }
        } else {
            for (StoreGoodsResp.StoreGoodsBean storeGoodsBean : mAllGoodsList) {
                if (storeGoodsBean.getId() == goodsBean.getId()) {
                    mAllGoodsList.remove(storeGoodsBean);
                    view.setSelected(add);
                } else {
                    needScoreId = goodsBean.getNeedScoreId();
                }
            }
        }
        mBalanceBtn.setEnabled(CollectionUtil.isNotEmpty(mAllGoodsList));
        needScore(needScoreId);
    }

    private int[] mNeedScore = new int[2];

    private void needScore(int needScoreId) {
        if (mAllGoodsList.isEmpty()) {
            mPriceTv.setText(R.string.lyy_favourite_total);
            mBalanceBtn.setEnabled(false);
            mSelectAllCb.setChecked(false);
        } else {
            int price = 0;
            for (StoreGoodsResp.StoreGoodsBean goods : mAllGoodsList) {
                price += goods.getPrice();
            }
            mNeedScore[0] = needScoreId;
            mNeedScore[1] = price;
//            if (needScoreId == State.MallType.COINS) {//
//                mPriceTv.setText(getString(R.string.lyy_favourite_total_coins, needScore));
//            } else if (needScoreId == State.MallType.COUPON) {//
//                mPriceTv.setText(getString(R.string.lyy_favourite_total_coupon, needScore));
//            } else if (needScoreId == State.MallType.SCORE) {//
//                mPriceTv.setText(getString(R.string.lyy_favourite_total_score, needScore));
//            }
            mPriceTv.setText(getString(R.string.lyy_favourite_total_rmb, price));
            mBalanceBtn.setEnabled(true);
        }
        if (mFavouriteAdapter.getData().size() == mAllGoodsList.size()) {
            mSelectAllCb.setChecked(true);
        } else {
            mSelectAllCb.setChecked(false);
        }
    }

    @Override
    public void onLoadMoreRequested() {
        //        fetchData(true);
    }

    @Override
    public void showStoreGoods(List<FavouriteAdapter.FavouriteItemData> storeGoodsBeanList) {
        mRefreshLayout.setRefreshing(false);
        mFavouriteAdapter.setNewData(storeGoodsBeanList);
        mSelectAllCb.setChecked(false);
        mAllGoodsList.clear();
        mPriceTv.setText(R.string.lyy_favourite_total);
        mBalanceBtn.setEnabled(false);
    }

    @Override
    public void onStoreGoodsFailure() {
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void exchangeGoodsSuccess(int goodsId) {
        Logger.d("goodsId=" + goodsId);
        mRefreshLayout.setRefreshing(false);
        //        mPresenter.delGoods(goodsId);//删除兑换的
    }

    @Override
    public void exchangeGoodsFailure(String msg) {
        ToastUtil.showTip(getActivity(), "".equals(msg) ? getString(R.string.lyy_game_network_state) : msg);
    }

    @Override
    public void delGoodsSuccess(int id) {
        List<FavouriteAdapter.FavouriteItemData> list = mFavouriteAdapter.getData();
        for (int i = 0; i < list.size(); i++) {
            StoreGoodsResp.StoreGoodsBean goodsBean = list.get(i).getGoodsBean();
            if (goodsBean.getId() == id) {
                mFavouriteAdapter.remove(i);
                break;
            }
        }

        for (int i = 0; i < mAllGoodsList.size(); i++) {
            StoreGoodsResp.StoreGoodsBean goodsBean = mAllGoodsList.get(i);
            if (goodsBean.getId() == id) {
                mAllGoodsList.remove(i);
                needScore(goodsBean.getNeedScoreId());
                break;
            }
        }
        if (mAllGoodsList.size() == 0 && mBalanceBtn.isEnabled()) {
            mBalanceBtn.setEnabled(false);
        }
    }

    @Override
    public void delGoodsFailure() {
        ToastUtil.showTip(getActivity(), R.string.lyy_game_del_store_failure);
    }

    @Override
    public void getUserInfoSuccess(List<StoreGoodsResp.StoreGoodsBean> goodsInfos) {
        int needScore = 0;
        //needScoreId  1：乐盈币 2：乐盈券 3：积分
        int needScoreId = 0;
        for (StoreGoodsResp.StoreGoodsBean goodsInfo : goodsInfos) {
            needScore += goodsInfo.getNeedScore();
            needScoreId = goodsInfo.getNeedScoreId();
        }
        UserInfo userInfo = AccountManager.getInstance().getUserInfo();
        if (needScoreId == 1) {
            if (needScore > Long.parseLong(userInfo.getLyb())) {
                ToastUtil.showTip(getActivity(), getString(R.string.lyy_game_favourite_pay_lyb));
                return;
            }
        } else if (needScoreId == 2) {
            if (needScore > Long.parseLong(userInfo.getLyq())) {
                ToastUtil.showTip(getActivity(), getString(R.string.lyy_game_favourite_pay_lyq));
                return;
            }
        } else if (needScoreId == 3) {
            if (needScore > Long.parseLong(userInfo.getJf())) {
                ToastUtil.showTip(getActivity(), getString(R.string.lyy_game_favourite_pay_jf));
                return;
            }
        }
        for (StoreGoodsResp.StoreGoodsBean goodsInfo : goodsInfos) {
            GoodsExchangeReq.ExGoods exGoods = new GoodsExchangeReq.ExGoods();
            exGoods.setGoodsId(goodsInfo.getGoodsId());
            exGoods.setUserId(AccountManager.getInstance().getUserId());
            exGoods.setCount(1);
            exGoods.setCountry(0);
            exGoods.setId(goodsInfo.getId());

            mPresenter.exchangeGoods(exGoods);
        }
    }

    @Override
    public void getUserInfoFailure(String msg) {
        ToastUtil.showTip(getActivity(), getString(R.string.lyy_game_favourite_pay_jf));
    }

    @Override
    public void setPresenter(FavouriteContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        onRefresh();
    }
}
