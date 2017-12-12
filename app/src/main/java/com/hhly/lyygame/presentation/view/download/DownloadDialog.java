package com.hhly.lyygame.presentation.view.download;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.hhly.lyygame.R;

/**
 * Created by ${HELY} on 17/3/30.
 * 邮箱：heli.lixiong@gmail.com
 */

public class DownloadDialog {

    public static void showNotWifiDownloadDialog(Context context, String size, DialogInterface.OnClickListener negativeListener, DialogInterface.OnClickListener positiveListener) {
        final AlertDialog.Builder builer = new AlertDialog.Builder(context);
        builer.setTitle(R.string.lyy_game_download_title);
        builer.setMessage(context.getString(R.string.lyy_game_download_content, size));
        builer.setNegativeButton(R.string.lyy_game_download_negative, negativeListener);
//        builer.setPositiveButton(R.string.lyy_game_download_positive, positiveListener);
        builer.setCancelable(true);
        builer.create().show();
    }

}
