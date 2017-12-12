package com.hhly.lyygame.presentation.view.address;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.db.manager.AccountManager;
import com.hhly.lyygame.data.net.protocol.user.UserAddressResp;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.immersive.IImmersiveApply;
import com.hhly.lyygame.presentation.view.widget.RecyclerViewListDivide;
import com.hhly.lyygame.presentation.view.widget.RecyclerViewListFirstDivide;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 收货地址管理
 * Created by Simon on 2016/11/28.
 */

public class AddressManagerFragment extends BaseFragment implements IImmersiveApply, AddressManagerContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
//    @BindView(R.id.refreshLayout)
//    SwipeRefreshLayout mRefreshLayout;

    public static AddressManagerFragment newInstance() {
        return new AddressManagerFragment();
    }

    private AddressManagerAdapter mManagerAdapter;
    private AddressManagerContract.Presenter mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_address_manager_layout;
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new RecyclerViewListDivide(getActivity(), R.color.transparent, RecyclerViewListDivide.VERTICAL_LIST, 15));
        mRecyclerView.addItemDecoration(new RecyclerViewListFirstDivide(getActivity(), RecyclerViewListDivide.VERTICAL_LIST, 15));

        mManagerAdapter = new AddressManagerAdapter();

        View emptyView = LayoutInflater.from(getActivity()).inflate(R.layout.widget_empty_view, null, false);
        TextView addressEmptyTv = (TextView) emptyView.findViewById(R.id.empty_tv);

        Drawable topDrawable = getResources().getDrawable(R.drawable.ic_address_empty);
        topDrawable.setBounds(0, 0, topDrawable.getMinimumWidth(), topDrawable.getMinimumHeight());
        addressEmptyTv.setCompoundDrawables(null, topDrawable, null, null);
        addressEmptyTv.setText(R.string.lyy_address_empty_tip);
        mManagerAdapter.setEmptyView(emptyView);

        mRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setAdapter(mManagerAdapter);
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                UserAddressResp.AddressBean addressBean = (UserAddressResp.AddressBean) baseQuickAdapter.getItem(i);

                switch (view.getId()) {
                    case R.id.address_default_cb:
                        AppCompatCheckBox checkBox = (AppCompatCheckBox) view;
                        Logger.d("checkbox=" + checkBox.isChecked());
                        modifyDefaultAddress(addressBean, checkBox.isChecked());
                        break;
                    case R.id.delete_btn:
                        delAddress(addressBean);
                        break;
                    case R.id.edit_btn:
                        editAddress(addressBean);
                        break;
                    default:
                        break;
                }
            }
        });

//        postDelay(new Runnable() {
//            @Override
//            public void run() {
                fetchData(true);
//            }
//        }, 500);
    }

    @OnClick(R.id.address_add_btn)
    void onAddClick(View view) {

        startActivityForResult(AddressEditActivity.getCallingIntent(getActivity(), null), AddressManagerActivity.RQ_ADDRESS_EDIT, null);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Logger.d("onActivityResult");
        if (resultCode == Activity.RESULT_OK) {
            fetchData(true);
        }
    }

    @Override
    public void showAddress(List<UserAddressResp.AddressBean> addressBeanList) {
        mRefreshLayout.setRefreshing(false);
        mManagerAdapter.setNewData(addressBeanList);
    }

    @Override
    public void showAddressFailure() {
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void modifyAddressSuccess() {
//        mPresenter.getUserAddress(AccountManager.getInstance().getUserId());
        mManagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void modifyAddressFailure() {

    }

    /**
     * 删除应用
     */
    @Override
    public void delAddressSuccess(int addressId) {

        List<UserAddressResp.AddressBean> list = mManagerAdapter.getData();
        for (int i = 0; i < list.size(); i++) {
            UserAddressResp.AddressBean itemModel = list.get(i);
            if (itemModel.getId() == addressId){
                list.remove(itemModel);
            }
        }
        mManagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void delAddressFailure() {

    }

    @Override
    public void setPresenter(AddressManagerContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    protected void fetchData(boolean loadMore) {
        mPresenter.getUserAddress(AccountManager.getInstance().getUserId());
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

    /**
     * 删除界面
     *
     * @param addressBean
     */
    public void delAddress(UserAddressResp.AddressBean addressBean) {
        mPresenter.delUserAddress(addressBean.getId());
    }

    /**
     * 跳转编辑界面
     */
    public void editAddress(UserAddressResp.AddressBean addressBean) {

        startActivityForResult(AddressEditActivity.getCallingIntent(getActivity(), addressBean), AddressManagerActivity.RQ_ADDRESS_EDIT, null);
    }

    /**
     * @param addressBean
     * @param choose      false 选中 true 取消选中
     */
    public void modifyDefaultAddress(UserAddressResp.AddressBean addressBean, boolean choose) {
        addressBean.setLast(choose ? 1 : 0);

        if (addressBean.getLast() == 0) {//last = 0 默认 1 取消默认

            List<UserAddressResp.AddressBean> list = mManagerAdapter.getData();
            for (int i = 0; i < list.size(); i++) {
                UserAddressResp.AddressBean itemModel = list.get(i);
                if (!itemModel.equals(addressBean)){
                    itemModel.setLast(1);
                }
            }
        }

        mPresenter.modifyDefaultAddress(addressBean.getUserId(), addressBean.getId(), addressBean.getLast());
    }

    @Override
    public void onRefresh() {
        fetchData(true);
    }
}
