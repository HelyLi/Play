package com.hhly.lyygame.reciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.hhly.lyygame.App;
import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.view.ToastUtil;

/**
 * Created by ${HELY} on 16/12/26.
 * 邮箱：heli.lixiong@gmail.com
 */

public class NetworkConnectChangedReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
            if (activeNetwork != null) {
                if (activeNetwork.isConnected()) {
//                    if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
//                        ToastUtil.showTip(App.getContext(), "当前WiFi连接可用");
//                    } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
//                        ToastUtil.showTip(App.getContext(), "当前移动网络连接可用");
//                    }
//                    ActivityUtil.login();
                } else {
                    ToastUtil.showTip(App.getContext(), R.string.lyy_network_notwork);
                }
            } else {
                ToastUtil.showTip(App.getContext(), R.string.lyy_network_notwork);
            }
        }
    }

}
