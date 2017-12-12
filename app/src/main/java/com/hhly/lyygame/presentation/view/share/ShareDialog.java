package com.hhly.lyygame.presentation.view.share;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.xmxu.cf.Callback;
import com.github.xmxu.cf.Caller;
import com.github.xmxu.cf.Cuttlefish;
import com.github.xmxu.cf.Result;
import com.github.xmxu.cf.ShareResult;
import com.github.xmxu.cf.qq.QQShareHandler;
import com.github.xmxu.cf.sina.WeiboShareHandler;
import com.github.xmxu.cf.wechat.WechatShareHandler;
import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.utils.DoubleClickUtil;
import com.orhanobut.logger.Logger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 分享
 * Created by Simon on 2016/12/7.
 */

public class ShareDialog extends BottomSheetDialog {

    @BindView(R.id.share_title_tv)
    TextView mShareTitleTv;

    @OnClick({R.id.share_wzone_tv, R.id.share_wechat_tv, R.id.share_weibo_tv, R.id.share_qq_tv, R.id.share_qzone_tv, R.id.cancel_tv})
    public void onClick(View view) {
        if (mShareContent == null)return;
        if (mShareResultCallback == null){
            mShareResultCallback = mDefaultShareResultCallback;
        }
        switch (view.getId()) {
            case R.id.share_wzone_tv:
                if (!DoubleClickUtil.isFastClick())
                onWechatTimelineShare(mShareContent);
                break;
            case R.id.share_wechat_tv:
                if (!DoubleClickUtil.isFastClick())
                onWechatSessionShare(mShareContent);
                break;
            case R.id.share_weibo_tv:
                if (!DoubleClickUtil.isFastClick())
                onWeiboShare(mShareContent);
                break;
            case R.id.share_qq_tv:
                if (!DoubleClickUtil.isFastClick())
                onQQShareFriend(mShareContent);
                break;
            case R.id.share_qzone_tv:
                if (!DoubleClickUtil.isFastClick())
                onQQShareQZone(mShareContent);
                break;
            case R.id.cancel_tv:
                dismiss();
                break;
        }
    }

    @IntDef({TYPE_SHARE, TYPE_INVITE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {
    }

    public static final int TYPE_SHARE = 0;
    public static final int TYPE_INVITE = 1;

    private int mType = TYPE_SHARE;

    private Context mContext;

    private ShareDialog(@NonNull Context context) {
        super(context);
    }

    private ShareDialog(Builder builder) {
        super(builder.context);
        mContext = builder.context;
        mType = builder.type;
        mShareContent = builder.shareContent;
        mShareResultCallback = builder.callback;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_share_layout);
        ButterKnife.bind(this);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        if (mType == TYPE_SHARE) {
            mShareTitleTv.setText(R.string.lyy_share_to_title);
        } else if (mType == TYPE_INVITE) {
            mShareTitleTv.setText(R.string.lyy_invite_friend_title);
        }
    }

    public static class Builder {
        private int type;
        private Context context;
        private ShareContent shareContent;
        private Callback<ShareResult> callback;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder type(int type) {
            this.type = type;
            return this;
        }

        public Builder shareContent(ShareContent shareContent){
            this.shareContent = shareContent;
            return this;
        }

        public Builder callback(Callback<ShareResult> callback){
            this.callback = callback;
            return this;
        }

        public ShareDialog build() {
            return new ShareDialog(this);
        }
    }

    private Caller mCurrentCaller;

    private void onQQShareQZone(ShareContent shareContent) {
        mCurrentCaller = Cuttlefish.with(mContext).share()
                .appName(shareContent.getAppName())
                .title(shareContent.getTitle())
                .description(shareContent.getDescription())
                .content(shareContent.getContent())
                .image(shareContent.getImage())
                .link(shareContent.getLink()).callback(mShareResultCallback).to(QQShareHandler.get(QQShareHandler.QZONE));
    }

    private void onQQShareFriend(ShareContent shareContent) {
        mCurrentCaller = Cuttlefish.with(mContext).share()
                .appName(shareContent.getAppName())
                .title(shareContent.getTitle())
                .description(shareContent.getDescription())
                .content(shareContent.getContent())
                .image(shareContent.getImage())
                .link(shareContent.getLink()).callback(mShareResultCallback).to(QQShareHandler.get(QQShareHandler.QQ));
    }

    private void onWeiboShare(ShareContent shareContent) {
        mCurrentCaller = Cuttlefish.with(mContext).share()
                .appName(shareContent.getAppName())
                .title(shareContent.getTitle())
                .description(shareContent.getDescription())
                .content(shareContent.getContent())
                .image(shareContent.getImage())
                .link(shareContent.getLink()).callback(mShareResultCallback).to(WeiboShareHandler.get());
        Logger.d("mCurrentCaller" + mCurrentCaller.handler());
    }

    /**
     * success
     * @param shareContent
     */
    private void onWechatSessionShare(ShareContent shareContent) {
        mCurrentCaller = Cuttlefish.with(mContext).share()
                .appName(shareContent.getAppName())
                .title(shareContent.getTitle())
                .description(shareContent.getDescription())
                .content(shareContent.getContent())
                .image(shareContent.getImage())
                .link(shareContent.getLink()).callback(mShareResultCallback)
                .to(WechatShareHandler.get());
    }

    /**
     * success
     * @param shareContent
     */
    private void onWechatTimelineShare(ShareContent shareContent) {
        mCurrentCaller = Cuttlefish.with(mContext).share()
                .appName(shareContent.getAppName())
                .title(shareContent.getTitle())
                .description(shareContent.getDescription())
                .content(shareContent.getContent())
                .image(shareContent.getImage())
                .link(shareContent.getLink()).callback(mShareResultCallback)
                .to(WechatShareHandler.get(WechatShareHandler.TIMELINE));
    }

    /**
     *
     * @param intent
     */
    public void onNewIntent(Intent intent){
        Logger.d("dialog.onNewIntent.out");
        if (mCurrentCaller != null){
            Logger.d("mCurrentCaller" + mCurrentCaller.handler());
            mCurrentCaller.handler().onNewIntent(intent);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (mCurrentCaller != null){
            mCurrentCaller.handler().onActivityResult(requestCode, resultCode, data);
        }
    }

    private Callback<ShareResult> mDefaultShareResultCallback = new Callback<ShareResult>() {
        @Override
        public void onFailure(Result result) {
            if (result.getErrorCode() != Result.Code.CANCEL) {
                Toast.makeText(mContext, TextUtils.isEmpty(result.getErrorMsg()) ?  mContext.getString(R.string.lyy_game_share_failure) : result.getErrorMsg(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(mContext, R.string.lyy_game_share_cancel, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onComplete(ShareResult result) {
            dismiss();
            Toast.makeText(mContext, R.string.lyy_game_share_success, Toast.LENGTH_SHORT).show();
        }
    };

    private ShareContent mShareContent;
    private Callback<ShareResult> mShareResultCallback;

}
