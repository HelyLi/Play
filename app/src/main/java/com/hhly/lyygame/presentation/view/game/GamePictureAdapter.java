package com.hhly.lyygame.presentation.view.game;

import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.protocol.game.GamePictureInfoResp;

import java.util.ArrayList;

/**
 * 游戏截图适配器
 * Created by Simon on 2016/12/2.
 */

public class GamePictureAdapter extends BaseQuickAdapter<GamePictureInfoResp.GamePicPager.GamePic, BaseViewHolder> {

    public GamePictureAdapter() {
        super(R.layout.game_picture_item_layout, new ArrayList<GamePictureInfoResp.GamePicPager.GamePic>());
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, GamePictureInfoResp.GamePicPager.GamePic gamePic) {
        ImageView imageView = baseViewHolder.getView(R.id.game_pic_iv);
        if (!TextUtils.isEmpty(gamePic.getUrl())) {
            Glide.with(imageView.getContext()).load(gamePic.getUrl()).asBitmap().into(imageView);
        }
    }
}
