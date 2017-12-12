package com.hhly.lyygame.presentation.view.shareactivity;

import android.app.Activity;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.xmxu.cf.Callback;
import com.github.xmxu.cf.Result;
import com.github.xmxu.cf.ShareResult;
import com.hhly.lyygame.App;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.db.manager.AccountManager;
import com.hhly.lyygame.presentation.utils.ActivityUtil;
import com.hhly.lyygame.presentation.utils.SharedPrefsFavouriteUtil;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.DialogFactory;
import com.hhly.lyygame.presentation.view.immersive.IImmersiveApply;
import com.hhly.lyygame.presentation.view.pay.PayActivity;
import com.hhly.lyygame.presentation.view.share.ShareContent;
import com.hhly.lyygame.presentation.view.share.ShareDialog;
import com.orhanobut.logger.Logger;
import com.sina.weibo.sdk.api.share.BaseResponse;
import com.sina.weibo.sdk.constant.WBConstants;

import butterknife.BindView;


/**
 * Created by ${HELY} on 17/4/13.
 * 邮箱：heli.lixiong@gmail.com
 */

public class ShareWebFragment extends BaseFragment implements IImmersiveApply {

    @BindView(R.id.pb)
    ProgressBar mProgressBar;
    @BindView(R.id.webView)
    WebView mWebView;

    private ShareContent mShareContent;

    private String mUserId = TextUtils.isEmpty(AccountManager.getInstance().getUserId())? "-1": AccountManager.getInstance().getUserId();
    private String mShare = "0";

    public static ShareWebFragment newInstance(ShareContent shareContent) {
        ShareWebFragment fragment = new ShareWebFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ShareWebActivity.EXTRA_SHARE, shareContent);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mShareContent = getArguments().getParcelable(ShareWebActivity.EXTRA_SHARE);
        }
        if (mShareContent.isShowShare()){
            setHasOptionsMenu(true);
        }
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        mToolbarHelper.setTitle(mShareContent.getWebTitle());
        Toolbar toolbar = mToolbarHelper.getToolbar();
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.share_menu) {
                    shareDetail();
                    return true;
                }
                return false;
            }
        });
        initWebView();
    }

    @Override
    protected void fetchData(boolean isLoadMore) {

    }

    private String parseUrl(String url) {
        if (url.startsWith("http") || url.startsWith("https")) {
            return url;
        }
        return "http://" + url;
    }

    private String buildParams(String url, String userId, String share){
        ShareWebBuilder builder = new ShareWebBuilder();
        builder.userId(userId).share(share).buildParams();

        if (!url.endsWith("?")) {
            url += "?";
        }
        return url + builder.buildParams();
    }

    private void initWebView() {

        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setAllowFileAccess(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setPluginState(WebSettings.PluginState.ON);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setDomStorageEnabled(true);

        mWebView.setWebChromeClient(new MWebViewClient());
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
            
            @Override
            public boolean shouldOverrideUrlLoading(final WebView view,final String url) {

//                if (url.contains("weixin://wap/pay")){
//                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//                    getActivity().startActivity(intent);
//                    return true;
//                }


                if (url.contains("playSummer")){
                    processAction(url.substring(ShareWebBuilder.tourUrl.length() + 1, url.length()));
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });

        Logger.d("userid=" + mUserId);

        mWebView.loadUrl(buildParams(parseUrl(mShareContent.getWebUrl()), mUserId, mShare));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_webview_layout;
    }

    @Override
    public boolean applyImmersive() {
        return true;
    }

    @Override
    public boolean applyScroll() {
        return false;
    }

    @Override
    public float initAlpha() {
        return 1.0f;
    }

    private class MWebViewClient extends WebChromeClient {

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            result.confirm();
            return super.onJsAlert(view, url, message, result);
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (mProgressBar != null) {
                mProgressBar.setProgress(newProgress);
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                }
            }
            super.onProgressChanged(view, newProgress);
        }

    }

    private void processAction(String action){
        Logger.d("url.action=" + action);

        String goLogin = "0";
        String goShare = "0";
        String goRecharge = "0";

        String[] infos = action.split("&");
        for (String info : infos) {
            if (info.startsWith("goLogin")) {
                goLogin = info.split("=").length == 2 ? info.split("=")[1] : "";
            } else if (info.startsWith("goShare")) {
                goShare = info.split("=").length == 2 ? info.split("=")[1] : "";
            } else if (info.startsWith("goRecharge")){
                goRecharge = info.split("=").length == 2 ? info.split("=")[1] : "";
            }
        }

        //需要登录
        if (!AccountManager.getInstance().isLogin() && goLogin.equals("1")){
            ActivityUtil.checkLoginAndRequestLogin(getActivity());
        }else if (goShare.equals("1")){
            shareDetail();
        }else if (goRecharge.equals("1")){
            SharedPrefsFavouriteUtil.putBooleanValue(getActivity(), SharedPrefsFavouriteUtil.PAY_COINS, true);
            if (ActivityUtil.checkLoginAndRequestLogin(getActivity())) {
                ActivityCompat.startActivity(getActivity(), PayActivity.getCallingIntent(getActivity()), null);
            }
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            if (mWebView.canGoBack()) {
                mWebView.goBack();
                return true;
            }
        }
        return false;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.share_menu, menu);
    }

    private ShareDialog mShareDialog;

    private void shareDetail(){
        mShareContent.setLink(mShareContent.getLink() + (TextUtils.isEmpty(mUserId)? "": ("?recommender=" + mUserId)));
        if (mShareDialog == null){
            mShareDialog = DialogFactory.createShare(getActivity(), ShareDialog.TYPE_SHARE, mShareContent, mShareResultCallback);
        }
        mShareDialog.show();
    }

    private Callback<ShareResult> mShareResultCallback = new Callback<ShareResult>() {
        @Override
        public void onFailure(Result result) {
            Logger.d("share.onFailure");
            if (result.getErrorCode() != Result.Code.CANCEL) {
                Toast.makeText(App.getContext(), TextUtils.isEmpty(result.getErrorMsg()) ?  getActivity().getString(R.string.lyy_game_share_failure) : result.getErrorMsg(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(App.getContext(), R.string.lyy_game_share_cancel, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onComplete(ShareResult result) {
            Logger.d("share.onComplete");
            if (mShareDialog != null){
                mShareDialog.dismiss();
            }
            mShare = "1";
            mUserId = AccountManager.getInstance().getUserId();
            if (mWebView != null){
                Logger.d("share.url=" + buildParams(ShareWebBuilder.tourUrl, mUserId, mShare));
                mWebView.loadUrl(buildParams(ShareWebBuilder.tourUrl, mUserId, mShare));
            }
            Toast.makeText(getActivity(), R.string.lyy_game_share_success, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Logger.d("onActivityResult.requestCode=" + requestCode + ",resultCode=" + resultCode);
        if (requestCode == 0 ){
            if (resultCode == Activity.RESULT_OK){
                mUserId = AccountManager.getInstance().getUserId();
                if (mWebView != null){

                    Logger.d("url=" + buildParams(parseUrl(mShareContent.getWebUrl()), mUserId, mShare));

                    mWebView.loadUrl(buildParams(ShareWebBuilder.tourUrl, mUserId, mShare));
                }
            }
        }else {
            if (mShareDialog != null){
                Logger.d("onActivityResult");
                mShareDialog.onActivityResult(requestCode,resultCode,data);
            }
        }
    }

    protected void onNewIntent(Intent intent) {
        Logger.d("onNewIntent.OUT.intent=" + intent);
        if (mShareDialog != null && "com.sina.weibo.sdk.action.ACTION_SDK_REQ_ACTIVITY".equals(intent.getAction())){
            Logger.d("onNewIntent.IN");
            mShareDialog.onNewIntent(intent);
            return;
        }
        mUserId = AccountManager.getInstance().getUserId();
        if (mWebView != null){
            mWebView.loadUrl(buildParams(ShareWebBuilder.tourUrl, mUserId, mShare));
        }
    }

    public void onResponse(BaseResponse baseResponse){
        switch (baseResponse.errCode) {
            case WBConstants.ErrorCode.ERR_OK:
                if (mShareResultCallback != null) {
                    mShareResultCallback.onComplete(new ShareResult(Result.Code.OK, "Succeed", null));
                }
                break;
            case WBConstants.ErrorCode.ERR_CANCEL:
                if (mShareResultCallback != null) {
                    mShareResultCallback.onFailure(new Result(Result.Code.CANCEL, "Cancel", null));
                }
                break;
            case WBConstants.ErrorCode.ERR_FAIL:
                if (mShareResultCallback != null) {
                    mShareResultCallback.onFailure(new Result(baseResponse.errCode, baseResponse.errMsg, null));
                }
                break;
        }
    }

}
