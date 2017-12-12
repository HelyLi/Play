package com.hhly.lyygame.presentation.view.gamehall.category;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatRatingBar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.downloadutils.DownloadBtnController;
import com.hhly.lyygame.presentation.downloadutils.DownloadItem;
import com.hhly.lyygame.presentation.utils.Utils;
import com.hhly.lyygame.presentation.view.widget.DownloadProgressButton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * 游戏分类列表
 * Created by Simon on 2016/11/24.
 */

public class GameCategoryListAdapter extends BaseQuickAdapter<DownloadItem, GameCategoryListAdapter.GameCategoryListViewHolder> {

    private Activity mContext;

    public GameCategoryListAdapter(Activity activity) {
        super(R.layout.game_category_item_layout, new ArrayList<DownloadItem>());
        mContext = activity;
    }

    @Override
    protected void convert(final GameCategoryListViewHolder baseViewHolder, DownloadItem data) {

        baseViewHolder.addOnClickListener(R.id.game_item_root);

        AppCompatRatingBar hotRatingBar = baseViewHolder.getView(R.id.game_item_hot_rb);
        hotRatingBar.setRating(Utils.getRatingProgress(data.popularitVal));
        baseViewHolder.setText(R.id.game_item_count_tv, mContext.getString(R.string.lyy_game_people_played, data.popularitVal));

        baseViewHolder.setText(R.id.game_item_name_tv, data.record.getApkName());
        baseViewHolder.setImageResource(R.id.game_item_iv, data.resGameTypeId);

        Glide.with(baseViewHolder.itemView.getContext()).load(data.record.getPicUrl())
                .centerCrop()
                .bitmapTransform(new RoundedCornersTransformation(baseViewHolder.itemView.getContext(), baseViewHolder.itemView.getContext().getResources().getDimensionPixelSize(R.dimen.round_radius), 0))
                .placeholder(R.drawable.ic_game_pic_default_01)
                .error(R.drawable.ic_game_pic_default_01)
                .into((ImageView) baseViewHolder.getView(R.id.game_item_pic_iv));

//        baseViewHolder.mGamePb.setState(DownloadProgressButton.NORMAL);

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
    protected GameCategoryListViewHolder createBaseViewHolder(View view) {
//        GameCategoryListViewHolder baseViewHolder = new GameCategoryListViewHolder(view, mContext);
//        baseViewHolder.setIsRecyclable(false);
        return new GameCategoryListViewHolder(view, mContext);
    }

    public class GameCategoryListViewHolder extends BaseViewHolder {

        @BindView(R.id.game_item_opt_btn)
        @Nullable
        DownloadProgressButton mGamePb;
        private DownloadBtnController mController;

        public GameCategoryListViewHolder(View view, Activity activity) {
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


