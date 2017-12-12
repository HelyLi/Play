package com.hhly.lyygame.presentation.view.transfer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.utils.ActivityUtil;
import com.hhly.lyygame.presentation.view.BaseActivity;

/**
 * Created by ${HELY} on 17/4/24.
 * 邮箱：heli.lixiong@gmail.com
 */

public class GameTransferActivity extends BaseActivity {

    public static final int TRANSFER_GAME_OUT_REQUESTCODE = 0x01;
    public static final int TRANSFER_GAME_IN_REQUESTCODE = 0x02;
    public static final int TRANSFER_GAME_COUPON_REQUESTCODE = 0x03;

    public static final String TRANSFER_GAMEID = "transfer_gameId";
    public static final String TRANSFER_GAMENAME = "transfer_gameName";

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, GameTransferActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GameTransferFragment fragment = (GameTransferFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (fragment == null) {
            fragment = GameTransferFragment.newInstance();
            ActivityUtil.addFragment(getSupportFragmentManager(), fragment, R.id.content_container);
        }
        new GameTransferPresenter(fragment);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        GameTransferFragment fragment = (GameTransferFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

}
