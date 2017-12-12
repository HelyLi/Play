package com.hhly.lyygame.presentation.view.home;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.downloadutils.DownloadBtnController;
import com.hhly.lyygame.presentation.downloadutils.DownloadItem;
import com.hhly.lyygame.presentation.view.widget.DownloadProgressButton;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * 推荐
 * Created by Simon on 2016/12/8.
 */

public class HomeOnlyAdapter extends BaseQuickAdapter<DownloadItem, HomeOnlyAdapter.HomeOnlyViewHolder> {

    private Activity mContext;

    public HomeOnlyAdapter(Activity activity) {
        super(R.layout.lyy_game_item_layout_02, new ArrayList<DownloadItem>());
        mContext = activity;
    }

    @Override
    protected void convert(final HomeOnlyViewHolder baseViewHolder, DownloadItem data) {
        baseViewHolder.addOnClickListener(R.id.game_item_pic_iv);
        baseViewHolder.setText(R.id.game_item_name_tv, data.record.getApkName());

        baseViewHolder.setText(R.id.game_item_count_tv, mContext.getString(R.string.lyy_game_people_played, data.popularitVal));
        baseViewHolder.setText(R.id.game_item_label_tv, data.resGameType);

        Glide.with(mContext).load(data.record.getPicUrl())
                .centerCrop()
                .bitmapTransform(new RoundedCornersTransformation(mContext, mContext.getResources().getDimensionPixelSize(R.dimen.round_radius), 0))
                .placeholder(R.drawable.ic_game_pic_default_03)
                .error(R.drawable.ic_game_pic_default_03)
                .into((ImageView) baseViewHolder.getView(R.id.game_item_pic_iv));

        Logger.d("data=" + data.record);

        baseViewHolder.setData(data);
        baseViewHolder.mGamePb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (baseViewHolder.mController != null)
                    baseViewHolder.mController.handleClick(true);
            }
        });

    }

    @Override
    protected HomeOnlyViewHolder createBaseViewHolder(View view) {

        HomeOnlyViewHolder holder = new HomeOnlyViewHolder(view, mContext);
        holder.setIsRecyclable(false);
        return holder;
    }

    public class HomeOnlyViewHolder extends BaseViewHolder {

        @BindView(R.id.game_item_opt_btn)
        @Nullable
        DownloadProgressButton mGamePb;
        private DownloadBtnController mController;

        public HomeOnlyViewHolder(View view, Activity activity) {
            super(view);
            ButterKnife.bind(this, view);
            if (mGamePb == null) return;
            mController = new DownloadBtnController(mGamePb, activity);
        }

        void setData(DownloadItem param) {
            if (mController == null) return;
            mController.setData(param);
        }

    }

}
