package com.hhly.lyygame.presentation.view.info;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.github.freeman0211.library.address.CityItem;
import com.github.freeman0211.library.address.DistrictItem;
import com.github.freeman0211.library.address.ProvinceItem;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.db.entry.UserInfo;
import com.hhly.lyygame.data.db.manager.AccountManager;
import com.hhly.lyygame.data.net.protocol.user.UpdateUserInfoReq;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.DialogFactory;
import com.hhly.lyygame.presentation.view.ToastUtil;
import com.hhly.lyygame.presentation.view.address.AddressPickupDialog;
import com.hhly.lyygame.presentation.view.immersive.IImmersiveApply;
import com.miguelbcr.ui.rx_paparazzo2.RxPaparazzo;
import com.miguelbcr.ui.rx_paparazzo2.entities.FileData;
import com.miguelbcr.ui.rx_paparazzo2.entities.Options;
import com.miguelbcr.ui.rx_paparazzo2.entities.Response;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

//import com.miguelbcr.ui.rx_paparazzo.entities.Options;
//import com.miguelbcr.ui.rx_paparazzo.entities.Response;

/**
 * 个人资料
 * Created by Simon on 2016/11/25.
 */

public class InfoFragment extends BaseFragment implements IImmersiveApply, InfoPictureSelectDialog.OnPictureSelectListener, InfoContract.View {

    private static final String TAG = "InfoFragment";

    public static final int INFO_TEXT_EDIT_REQUEST_CODE = 1;
    public static final int REAL_AUTH_REQUEST_CODE = 2;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private InfoAdapter mInfoAdapter;

    private List<InfoGroup> mInfoGroups;

    private Options mOptions;

    private UserInfo mUserInfo;

    InfoContract.Presenter mPresenter;

    public InfoFragment() {
        new InfoPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_info_layout;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mOptions = new Options();
        mOptions.setToolbarColor(ContextCompat.getColor(getActivity(), R.color.color_e7384a));
        mOptions.setStatusBarColor(ContextCompat.getColor(getActivity(), R.color.color_e7384a));
        mOptions.setToolbarTitle(getString(R.string.lyy_crop));
        mOptions.setCircleDimmedLayer(true);
        mOptions.setHideBottomControls(true);
        mOptions.setAspectRatio(70, 70);
        mUserInfo = AccountManager.getInstance().getUserInfo();
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new InfoDivide(getActivity()));

        mInfoAdapter = new InfoAdapter();
        initInfo();
        mInfoAdapter.setInfo(mInfoGroups);
        mRecyclerView.setAdapter(mInfoAdapter);

    }

    private void initInfo() {
        if (mInfoGroups == null) {
            mInfoGroups = new ArrayList<>();

            InfoGroup group1 = new InfoGroup();
            group1.setIndex(0);
            ArrayList<InfoItem> infoList1 = new ArrayList<>();
            infoList1.add(new InfoItem.Builder()
                    .type(InfoItem.AVATAR).title(R.string.lyy_info_avatar).content(mUserInfo.getHeadIcon() == null ? "" : mUserInfo.getHeadIcon()).action(mAvatarAction)//0
                    .build());
            infoList1.add(new InfoItem.Builder()
                    .type(InfoItem.COMM).title(R.string.lyy_info_account).content(mUserInfo.getUserId())//1
                    .build());
            infoList1.add(new InfoItem.Builder()
                    .type(InfoItem.COMM).title(R.string.lyy_info_nickname).content(mUserInfo.getNickname() == null ? "" : mUserInfo.getNickname()).action(mNicknameAction)//2
                    .build());
            infoList1.add(new InfoItem.Builder()//3
                    .type(InfoItem.COMM).title(R.string.lyy_info_gender).content(mUserInfo.getSex() == null ? getString(R.string.lyy_gender_secret) : (mUserInfo.getSex() == 1 ? getString(R.string.lyy_gender_male) : (mUserInfo.getSex() == 2 ? getString(R.string.lyy_gender_female) : getString(R.string.lyy_gender_secret)))).action(mGenderAction)//1男、 2女 、3保密
                    .build());
            group1.setChildList(infoList1);
            mInfoGroups.add(group1);

            group1 = new InfoGroup();
            group1.setIndex(1);
            ArrayList<InfoItem> infoList2 = new ArrayList<>();
            infoList2.add(new InfoItem.Builder()//4
                    .type(InfoItem.COMM).title(R.string.lyy_info_real_auth).content(TextUtils.isEmpty(mUserInfo.getRealName()) || TextUtils.isEmpty(mUserInfo.getIdcarNo()) ? getString(R.string.lyy_info_unauthorized) : getString(R.string.lyy_info_authorized)).action(mRealAuthAction)
                    .build());
            infoList2.add(new InfoItem.Builder()//5
                    .type(InfoItem.COMM).title(R.string.lyy_info_qq).content(mUserInfo.getQq() == null ? "" : mUserInfo.getQq()).action(mQQAction)
                    .build());
            infoList2.add(new InfoItem.Builder()//6
                    .type(InfoItem.COMM).title(R.string.lyy_info_region).content(mUserInfo.getLocation() == null ? "" : mUserInfo.getLocation()).action(mRegionAction)
                    .build());
            //第三方登录不需要修改密码
            if (AccountManager.getInstance().getUserInfo().getRegType() != 4) {
                infoList2.add(new InfoItem.Builder()//7
                        .type(InfoItem.COMM).title(R.string.lyy_info_modify_password).content("").action(mModifyPwdAction)
                        .build());
            }
            infoList2.add(new InfoItem.Builder()//7
                    .type(InfoItem.COMM).title(R.string.lyy_info_address).content(mUserInfo.getAddress() == null ? "" : mUserInfo.getAddress()).action(mModifyAddressAction)
                    .build());

            group1.setChildList(infoList2);

            mInfoGroups.add(group1);
        }
    }

    /**
     * 头像
     */
    private final Runnable mAvatarAction = new Runnable() {
        @Override
        public void run() {
            DialogFactory.createSelectPhoto(getActivity(), InfoFragment.this).show();
        }
    };

    /**
     * 昵称
     */
    private final Runnable mNicknameAction = new Runnable() {
        @Override
        public void run() {
            ActivityCompat.startActivityForResult(getActivity(),
                    InfoTextEditActivity.getCallingIntent(getActivity(),
                            InfoTextEditFragment.TYPE_NICKNAME, mUserInfo.getNickname()), INFO_TEXT_EDIT_REQUEST_CODE, null);
        }
    };

    /**
     * 性别
     */
    private final Runnable mGenderAction = new Runnable() {
        @Override
        public void run() {
            DialogFactory.createGenderSelectDialog(getActivity(), mOnClickListener).show();
        }
    };

    /**
     * 用户认证
     */
    private final Runnable mRealAuthAction = new Runnable() {
        @Override
        public void run() {
            ActivityCompat.startActivityForResult(getActivity(),
                    RealAuthActivity.getCallingIntent(getActivity()), REAL_AUTH_REQUEST_CODE, null);
        }
    };

    /**
     * 修改QQ
     */
    private final Runnable mQQAction = new Runnable() {
        @Override
        public void run() {
            ActivityCompat.startActivityForResult(getActivity(),
                    InfoTextEditActivity.getCallingIntent(getActivity(),
                            InfoTextEditFragment.TYPE_QQ, mUserInfo.getQq()), INFO_TEXT_EDIT_REQUEST_CODE, null);
        }
    };

    /**
     * 详细地址
     */
    private final Runnable mModifyAddressAction = new Runnable() {
        @Override
        public void run() {
            Logger.d("getAddress=" + mUserInfo.getAddress());
            ActivityCompat.startActivityForResult(getActivity(),
                    InfoTextEditActivity.getCallingIntent(getActivity(),
                            InfoTextEditFragment.TYPE_ADDRESS, mUserInfo.getAddress()), INFO_TEXT_EDIT_REQUEST_CODE, null);
        }
    };

    private AddressPickupDialog.OnSelectedListener mOnSelectedListener = new AddressPickupDialog.OnSelectedListener() {
        @Override
        public void onSelected(ProvinceItem pItem, CityItem cItem, DistrictItem dItem, String address) {
            Logger.d("address=" + address);
            mUserInfo.setLocation(address);

            UpdateUserInfoReq.UpdateUserInfo updateUserInfo = new UpdateUserInfoReq.UpdateUserInfo();
            updateUserInfo.setLocation(address);
            mPresenter.updateUserInfo(6, updateUserInfo);
        }
    };

    private InfoGenderSelectDialog.OnGenderSelectListener mOnClickListener = new InfoGenderSelectDialog.OnGenderSelectListener() {
        @Override
        public void onClick(int gender) {//1男、 2女 、3保密
            UpdateUserInfoReq.UpdateUserInfo updateUserInfo = new UpdateUserInfoReq.UpdateUserInfo();
            updateUserInfo.setSex(gender);
            mPresenter.updateUserInfo(3, updateUserInfo);
        }
    };

    /**
     * 地区选择
     */
    private final Runnable mRegionAction = new Runnable() {
        @Override
        public void run() {
            DialogFactory.createAddressPickup(mOnSelectedListener).show(getChildFragmentManager(), "AddressPickup");
        }
    };

    /**
     * 修改密码
     */
    private final Runnable mModifyPwdAction = new Runnable() {
        @Override
        public void run() {

            ActivityCompat.startActivity(getActivity(),
                    ResetPwdActivity.getCallingIntent(getActivity()), null);
        }
    };

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

    /**
     * 拍照
     */
    @Override
    public void onClickCamera() {

        RxPaparazzo.single(InfoFragment.this)
                .usingCamera()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<InfoFragment, FileData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<InfoFragment, FileData> feedbackFragmentFileDataResponse) {
                        handlePictureCallback(feedbackFragmentFileDataResponse);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 相册
     */
    @Override
    public void onClickAlbum() {

        RxPaparazzo.single(InfoFragment.this)
                .usingGallery()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<InfoFragment, FileData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<InfoFragment, FileData> feedbackFragmentFileDataResponse) {
                        handlePictureCallback(feedbackFragmentFileDataResponse);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void handlePictureCallback(Response<InfoFragment, FileData> response) {
        if (response.resultCode() == Activity.RESULT_OK) {
            Logger.d("Response: " + response.data().getFile().getAbsolutePath());

            mPresenter.uploadImage(response.data().getFile().getAbsolutePath());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Logger.d("requestCode=" + requestCode + ",resultCode" + resultCode);

        mUserInfo = AccountManager.getInstance().getUserInfo();

        if (requestCode == REAL_AUTH_REQUEST_CODE) {
            updateInfo(4, TextUtils.isEmpty(mUserInfo.getRealName()) || TextUtils.isEmpty(mUserInfo.getIdcarNo()) ? getString(R.string.lyy_info_unauthorized) : getString(R.string.lyy_info_authorized));
        }

        if (resultCode == Activity.RESULT_CANCELED) {
            return;
        }
        if (requestCode == INFO_TEXT_EDIT_REQUEST_CODE) {
            Bundle bundle = data.getExtras();
            String content = null;
            if (!TextUtils.isEmpty(content = bundle.getString(InfoTextEditFragment.KEY_NICKNAME))) {

                Logger.d("nickname=" + content);

                UpdateUserInfoReq.UpdateUserInfo updateUserInfo = new UpdateUserInfoReq.UpdateUserInfo();
                updateUserInfo.setNickname(content);
                mPresenter.keyWordFilter(2, updateUserInfo);

            } else if (!TextUtils.isEmpty(content = bundle.getString(InfoTextEditFragment.KEY_QQ))) {

                Logger.d("qq=" + content);

                UpdateUserInfoReq.UpdateUserInfo updateUserInfo = new UpdateUserInfoReq.UpdateUserInfo();
                updateUserInfo.setQq(content);

                mPresenter.updateUserInfo(5, updateUserInfo);

            } else if (!TextUtils.isEmpty(content = bundle.getString(InfoTextEditFragment.KEY_ADDRESS))) {
                UpdateUserInfoReq.UpdateUserInfo updateUserInfo = new UpdateUserInfoReq.UpdateUserInfo();
                updateUserInfo.setAddress(content);

                mPresenter.updateUserInfo(8, updateUserInfo);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.d("onResume");
        mPresenter.subscribe();
        if (!AccountManager.getInstance().isLogin()){
            onBackPressed();
            return;
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

    private void updateInfo(int index, String content) {

        InfoGroup infoGroup = null;
        switch (index) {
            case 0:
            case 1:
            case 2:
            case 3: {
                infoGroup = mInfoGroups.get(0);
                ArrayList<InfoItem> list = infoGroup.getChildList();
                InfoItem infoItem = list.get(index);
                infoItem.setContent(content);
            }
            break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8: {
                infoGroup = mInfoGroups.get(1);
                ArrayList<InfoItem> list = infoGroup.getChildList();
                InfoItem infoItem = list.get(index - 4);
                infoItem.setContent(content);
            }
            break;
            default:
                break;
        }
        mInfoAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateSuccess(int index, UserInfo userInfo) {
        mUserInfo = userInfo;
        //
        String content = null;
        switch (index) {
            case 0:
                break;
            case 1:
                break;
            case 2://nickname
                content = userInfo.getNickname();
                break;
            case 3:
                content = userInfo.getSex() == null ? getString(R.string.lyy_gender_secret) : (userInfo.getSex() == 1 ? getString(R.string.lyy_gender_male) : (userInfo.getSex() == 2 ? getString(R.string.lyy_gender_female) : getString(R.string.lyy_gender_secret)));
                break;
            case 4:
                break;
            case 5:
                content = userInfo.getQq();
                break;
            case 6:
                content = userInfo.getLocation();
                break;
            case 7:
                break;
            case 8:
                if (AccountManager.getInstance().getUserInfo().getRegType() == 4) {
                    index--;
                }
                content = userInfo.getAddress();
                break;
            default:
                break;
        }

        if (TextUtils.isEmpty(content))
            return;
        updateInfo(index, content);
    }

    @Override
    public void updateFailure(String msg) {
        ToastUtil.showTip(getActivity(), msg);
    }

    @Override
    public void imageUrlFailure(String filePath, String msg) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }

    @Override
    public void keyWordFilterSuccess(int index, UpdateUserInfoReq.UpdateUserInfo updateUserInfo) {
        mPresenter.updateUserInfo(index, updateUserInfo);
    }

    @Override
    public void keyWordFilterFailure(String msg) {
        ToastUtil.showTip(getActivity(), msg);
    }

    @Override
    public void imageUrl(String imageUrl, String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
        updateInfo(0, imageUrl);
        UpdateUserInfoReq.UpdateUserInfo updateUserInfo = new UpdateUserInfoReq.UpdateUserInfo();
        updateUserInfo.setHeadUrl(imageUrl);

        mPresenter.updateUserInfo(0, updateUserInfo);
    }

    @Override
    public void setPresenter(InfoContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return false;
    }
}
