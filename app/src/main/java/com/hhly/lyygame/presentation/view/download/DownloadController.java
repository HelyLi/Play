package com.hhly.lyygame.presentation.view.download;

import android.widget.Button;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import zlc.season.rxdownload2.entity.DownloadEvent;
import zlc.season.rxdownload2.entity.DownloadFlag;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/11/22
 * Time: 15:18
 * FIXME
 */
public class DownloadController {
    private TextView mStatus;
    private Button mAction;

    private DownloadState mState;

    public DownloadController(TextView status, Button action) {
        mStatus = status;
        mAction = action;
        setState(new Normal());
    }

    public void setState(DownloadState state) {
        mState = state;
        mState.setText(mStatus, mAction);
    }

    public void setEvent(DownloadEvent event) {
        int flag = event.getFlag();
        switch (flag) {
            case DownloadFlag.NORMAL:
                Logger.d("NORMAL");
                setState(new Normal());
                break;
            case DownloadFlag.WAITING:
                Logger.d("WAITING");
                setState(new Waiting());
                break;
            case DownloadFlag.STARTED:
                Logger.d("STARTED");
                setState(new Started());
                break;
            case DownloadFlag.PAUSED:
                Logger.d("PAUSED");
                setState(new Paused());
                break;
            case DownloadFlag.COMPLETED:
                Logger.d("COMPLETED");
                setState(new Completed());
                break;
            case DownloadFlag.FAILED:
                Logger.d("FAILED");
                setState(new Failed());
                break;
            case DownloadFlag.DELETED:
                Logger.d("DELETED");
                setState(new Deleted());
                break;
            case DownloadFlag.INSTALLED:
                Logger.d("INSTALLED");
                setState(new Installed());
                break;
        }
    }

    public void handleClick(Callback callback) {
        mState.handleClick(callback);
    }

    public interface Callback {

        void startDownload();

        void pauseDownload();

        void install();

        void open();
    }

    static abstract class DownloadState {

        abstract void setText(TextView status, Button button);

        abstract void handleClick(Callback callback);
    }

    public static class Normal extends DownloadState {

        @Override
        void setText(TextView status, Button button) {

            button.setText("下载");
            status.setText("");
        }

        @Override
        void handleClick(Callback callback) {
            callback.startDownload();
        }
    }

    public static class Waiting extends DownloadState {
        @Override
        void setText(TextView status, Button button) {

            button.setText("等待中");
            status.setText("等待中...");
        }

        @Override
        void handleClick(Callback callback) {
            callback.pauseDownload();
        }
    }

    public static class Started extends DownloadState {
        @Override
        void setText(TextView status, Button button) {
            button.setText("暂停");
            status.setText("下载中...");
        }

        @Override
        void handleClick(Callback callback) {
            callback.pauseDownload();
        }
    }

    public static class Paused extends DownloadState {
        @Override
        void setText(TextView status, Button button) {

            button.setText("继续");
            status.setText("已暂停");
        }

        @Override
        void handleClick(Callback callback) {
            callback.startDownload();
        }
    }

    public static class Failed extends DownloadState {
        @Override
        void setText(TextView status, Button button) {
            button.setText("继续");
            status.setText("下载失败");
        }

        @Override
        void handleClick(Callback callback) {
            callback.startDownload();
        }
    }

    public static class Completed extends DownloadState {
        @Override
        void setText(TextView status, Button button) {
            button.setText("安装");
            status.setText("下载已完成");
        }

        @Override
        void handleClick(Callback callback) {
            callback.install();
        }
    }

    public static class Deleted extends DownloadState {

        @Override
        void setText(TextView status, Button button) {
            button.setText("下载");
            status.setText("下载已取消");
        }

        @Override
        void handleClick(Callback callback) {
            callback.startDownload();
        }
    }

    public static class Installed extends DownloadState {

        @Override
        void setText(TextView status, Button button) {
            button.setText("启动");
            status.setText("已安装");
        }

        @Override
        void handleClick(Callback callback) {
            callback.open();
        }
    }

}
