package com.hhly.lyygame.presentation.view.download;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.ListPopupWindow;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.classic.android.rx.RxUtil;
import com.hhly.lyygame.App;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.ApiType;
import com.hhly.lyygame.data.net.RetrofitManager;
import com.hhly.lyygame.data.net.UserApi;
import com.hhly.lyygame.data.net.protocol.BaseResp;
import com.hhly.lyygame.data.net.protocol.user.AddRecentlyPlayedReq;
import com.hhly.lyygame.presentation.downloadutils.DownloadItem;
import com.hhly.lyygame.presentation.utils.AppUtil;
import com.hhly.lyygame.presentation.utils.TelephonyUtil;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;


/**
 * 已安装
 * Created by Simon on 2016/12/5.
 */

public class DownloadedAdapter extends BaseQuickAdapter<DownloadItem, DownloadedAdapter.DownloadedViewHolder> {

    private Activity mContext;
    private UserApi mUserApi;

    public DownloadedAdapter(Activity activity) {
        super(R.layout.lyy_download_item_layout, new ArrayList<DownloadItem>());
        mContext = activity;
        mUserApi = RetrofitManager.getInstance(ApiType.USER_API).getUserApi();
    }

    @Override
    protected DownloadedViewHolder createBaseViewHolder(View view) {
        return new DownloadedViewHolder(view);
    }

    @Override
    protected void convert(DownloadedViewHolder baseViewHolder, final DownloadItem data) {

        Logger.d("downloaded.data=" + data.record.toString());

        Glide.with(mContext).load(data.record.getPicUrl())
                .error(R.drawable.ic_game_pic_default_01)
                .placeholder(R.drawable.ic_game_pic_default_01)
                .bitmapTransform(new RoundedCornersTransformation(mContext, mContext.getResources().getDimensionPixelSize(R.dimen.round_radius), 0))
                .into((ImageView) baseViewHolder.getView(R.id.game_item_pic_iv));

        baseViewHolder.setText(R.id.game_item_name_tv, data.record.getApkName());
        baseViewHolder.setText(R.id.game_item_version_tv, mContext.getString(R.string.lyy_game_version, AppUtil.getAppVersionName(mContext, data.record.getPackageName())));//lyy_game_version
//        baseViewHolder.setText(R.id.game_item_size_tv, TextUtils.isEmpty(data.record.getSize()) ? "" : data.record.getSize());

        //启动
        baseViewHolder.gameItemOpenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUpWindow(mContext, view, data);
            }
        });
        //卸载
        baseViewHolder.gameItemUninstallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUtil.startUninstall(mContext, data.record.getPackageName());
            }
        });
    }

    public void open(DownloadItem data) {

        AddRecentlyPlayedReq req = new AddRecentlyPlayedReq();
        req.setGameId(data.record.getGameId());
        req.setTerminal(TelephonyUtil.getOsTypeInt());

        mUserApi.addRecentlyPlayed(req.params())
                .compose(RxUtil.<BaseResp>applySchedulers(RxUtil.IO_TRANSFORMER_BACK_PRESSURE))
                .subscribe(new Consumer<BaseResp>() {
                    @Override
                    public void accept(@NonNull BaseResp baseResp) throws Exception {
                        Logger.d("add.result=" + baseResp);
                    }
                });

        AppUtil.startApp(App.getContext(), data.record.getPackageName());
    }

    private void showPopUpWindow(final Context context, View view, final DownloadItem data) {
        final ListPopupWindow listPopupWindow = new ListPopupWindow(context);
        listPopupWindow.setAdapter(new ArrayAdapter<>(App.getContext(), R.layout.widget_downloaded_open_layout,
                new String[]{"启动"}));
        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                if (pos == 0) {
                    open(data);
                    listPopupWindow.dismiss();
                }
            }
        });
        listPopupWindow.setWidth(200);
        listPopupWindow.setAnchorView(view);
        listPopupWindow.setModal(false);
        listPopupWindow.show();
    }

    static class DownloadedViewHolder extends BaseViewHolder {

        @BindView(R.id.game_item_open_btn)
        Button gameItemOpenBtn;
        @BindView(R.id.game_item_uninstall_btn)
        Button gameItemUninstallBtn;

        public DownloadedViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
