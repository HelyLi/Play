package com.hhly.lyygame.presentation.view.download;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhly.lyygame.App;
import com.hhly.lyygame.BuildConfig;
import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.downloadutils.DownloadHelper;
import com.hhly.lyygame.presentation.downloadutils.DownloadItem;
import com.hhly.lyygame.presentation.utils.AppUtil;
import com.hhly.lyygame.presentation.utils.NetworkUtil;
import com.hhly.lyygame.presentation.view.ToastUtil;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadBean;
import zlc.season.rxdownload2.entity.DownloadEvent;
import zlc.season.rxdownload2.entity.DownloadFlag;
import zlc.season.rxdownload2.entity.DownloadStatus;

import static zlc.season.rxdownload2.function.Utils.dispose;
import static zlc.season.rxdownload2.function.Utils.empty;

/**
 * 正在下载管理
 * Created by Simon on 2016/12/5.
 */

public class DownloadingAdapter extends BaseQuickAdapter<DownloadItem, DownloadingAdapter.DownloadingViewHolder> {

    private Activity mContext;

    public DownloadingAdapter(Activity activity) {
        super(R.layout.lyy_downloading_item_layout, new ArrayList<DownloadItem>());
        mContext = activity;
    }

    @Override
    protected DownloadingViewHolder createBaseViewHolder(View view) {
        DownloadingViewHolder downloadingViewHolder = new DownloadingViewHolder(view, mContext, this);
//        downloadingViewHolder.setIsRecyclable(false);
        return downloadingViewHolder;
    }

    @Override
    protected void convert(final DownloadingViewHolder baseViewHolder,final DownloadItem data) {

        Glide.with(mContext).load(data.record.getPicUrl())
                .error(R.drawable.ic_game_pic_default_01)
                .placeholder(R.drawable.ic_game_pic_default_01)
                .bitmapTransform(new RoundedCornersTransformation(mContext, mContext.getResources().getDimensionPixelSize(R.dimen.round_radius), 0))
                .into((ImageView) baseViewHolder.getView(R.id.game_item_pic_iv));

        String name = empty(data.record.getApkName()) ? data.record.getSaveName() : data.record.getApkName();
        baseViewHolder.setText(R.id.game_item_name_tv, name);
        baseViewHolder.setData(data);

        Logger.d("data.record=" + data.record);

        baseViewHolder.mGameItemActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (NetworkUtil.isAvailable(mContext)) {

                    baseViewHolder.mDownloadController.handleClick(new DownloadController.Callback() {
                        @Override
                        public void startDownload() {
                            baseViewHolder.start();
                        }

                        @Override
                        public void pauseDownload() {
                            baseViewHolder.pause();
                        }

                        @Override
                        public void install() {
                            baseViewHolder.installApk();
                        }

                        @Override
                        public void open() {
                            baseViewHolder.open();
                        }
                    });

                } else {
                    ToastUtil.showTip(App.getContext(), R.string.lyy_network_notwork);
                }
            }
        });

        baseViewHolder.mGameItemDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseViewHolder.delete();
            }
        });

    }

    static class DownloadingViewHolder extends BaseViewHolder {
        @Nullable
        @BindView(R.id.game_item_pic_iv)
        ImageView mGameItemPicIv;
        @Nullable
        @BindView(R.id.game_item_name_tv)
        TextView mGameItemNameTv;
        @Nullable
        @BindView(R.id.game_item_status_tv)
        TextView mGameItemStatusTv;
        @Nullable
        @BindView(R.id.game_item_size_tv)
        TextView mGameItemSizeTv;
        @Nullable
        @BindView(R.id.game_item_progress)
        ProgressBar mGameItemProgress;
        @Nullable
        @BindView(R.id.game_item_delete_btn)
        Button mGameItemDeleteBtn;
        @Nullable
        @BindView(R.id.game_item_action_btn)
        Button mGameItemActionBtn;

        private Activity mContext;

        private BaseQuickAdapter mAdapter;

        private DownloadController mDownloadController;
        private DownloadItem data;
        private RxDownload mRxDownload;

        private int flag;

        public DownloadingViewHolder(View itemView, Activity activity, BaseQuickAdapter adapter) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mAdapter = adapter;
            mContext = activity;

            if (mGameItemActionBtn == null) return;

            mRxDownload = DownloadHelper.get().rxDownload();
            mDownloadController = new DownloadController(mGameItemStatusTv, mGameItemActionBtn);
        }

        void setData(DownloadItem data) {
            this.data = data;

            if (mRxDownload == null) return;

            data.bean = new DownloadBean.Builder(data.record.getUrl())
                    .setApkName(data.record.getApkName())
                    .setPackage(data.record.getPackageName())
                    .build();

            data.disposable = mRxDownload.receiveDownloadStatus(data.bean)
                    .subscribe(new Consumer<DownloadEvent>() {
                        @Override
                        public void accept(DownloadEvent downloadEvent) throws Exception {
                            if (flag != downloadEvent.getFlag()) {
                                flag = downloadEvent.getFlag();
                                Logger.d(flag + "");
                            }
                            if (downloadEvent.getFlag() == DownloadFlag.FAILED) {
                                Throwable throwable = downloadEvent.getError();
                                Logger.d("TAG", throwable);
                            }
                            mDownloadController.setEvent(downloadEvent);
                            updateProgressStatus(downloadEvent.getDownloadStatus());
                        }
                    });
        }

        private void updateProgressStatus(DownloadStatus status) {
            mGameItemProgress.setIndeterminate(status.isChunked);
            mGameItemProgress.setMax((int) status.getTotalSize());
            mGameItemProgress.setProgress((int) status.getDownloadSize());
            mGameItemSizeTv.setText(status.getFormatStatusString());
        }

        private void installApk() {
            File[] files = mRxDownload.getRealFiles(data.record.getUrl());
            if (files != null) {
                Intent intent = new Intent(Intent.ACTION_VIEW);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    Uri contentUri = FileProvider.getUriForFile(mContext, BuildConfig.APPLICATION_ID + ".file_provider", files[0]);
                    intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
                } else {
                    Uri uri = Uri.fromFile(files[0]);
                    intent.setDataAndType(uri, "application/vnd.android.package-archive");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                }

                mContext.startActivity(intent);
            } else {
                Toast.makeText(mContext, "File not exists", Toast.LENGTH_SHORT).show();
            }
        }

        private void start() {

            if (NetworkUtil.isMobile(mContext)){
                DownloadDialog.showNotWifiDownloadDialog(mContext, data.record.getSize(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DownloadHelper.get().start(data.bean)
                                .subscribe(new Consumer<Object>() {
                                    @Override
                                    public void accept(Object o) throws Exception {

                                    }
                                });
                    }
                }, null);
            }else {
                DownloadHelper.get().start(data.bean)
                        .subscribe(new Consumer<Object>() {
                            @Override
                            public void accept(Object o) throws Exception {

                            }
                        });
            }
        }

        private void open() {
            AppUtil.startApp(App.getContext(), data.record.getPackageName());
        }

        private void pause() {
            mRxDownload.pauseServiceDownload(data.record.getUrl()).subscribe();
        }

        private void delete() {
            if (data != null && data.disposable != null){
                dispose(data.disposable);
            }
            mRxDownload.deleteServiceDownload(data.record.getUrl(), true)
                    .doFinally(new Action() {
                        @Override
                        public void run() throws Exception {
                            mAdapter.remove(getAdapterPosition());
                        }
                    })
                    .subscribe();
        }

    }

}
