package com.hhly.lyygame.presentation.view.web;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.view.BaseActivity;
import com.hhly.lyygame.presentation.view.immersive.IImmersiveApply;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * webview
 * Created by Simon on 2016/12/7.
 */

public class WebLocationActivity extends BaseActivity implements IImmersiveApply {

    private static final int REQUEST_CODE = 1001;

    //用户协议 http://game.13322.com/agreement/
    public static final String USER_AGREEMENT = "http://game.13322.com/agreement/";
    //快捷支付协议 本地/
    public static final String PAYMENT_AGREEMENT = "http://game.13322.com/payment/";
    //兑换协议
    public static final String EX_AGREEMENT = "http://game.13322.com/xuzhi.htm";

    public static final String EXTRA_URL = "extra_url";
    public static final String EXTRA_TITLE = "extra_title";

    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.pb)
    ProgressBar pb;

    private String mUrl;

    public static Intent getCallingIntent(Context context, String url, String title) {
        Intent intent = new Intent(context, WebLocationActivity.class);
        intent.putExtra(WebLocationActivity.EXTRA_URL, url);
        intent.putExtra(WebLocationActivity.EXTRA_TITLE, title);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUnbinder = ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent != null) {
            mUrl = intent.getStringExtra(EXTRA_URL);
            String title = intent.getStringExtra(EXTRA_TITLE);
            if (!TextUtils.isEmpty(title)) {
                setTitleForToolbar(title);
            }
        }

        if (TextUtils.isEmpty(mUrl)) {
            finish();
            return;
        }

        mUrl = parseUrl(mUrl);

        initWebView();
    }

    private String parseUrl(String url) {
        if (url.startsWith("http") || url.startsWith("https")) {
            return url;
        }
        return "http://" + url;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_webview_layout;
    }

    private void initWebView() {

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setAllowFileAccess(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setPluginState(WebSettings.PluginState.ON);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setDomStorageEnabled(true);

        webView.setWebChromeClient(new MWebViewClient());

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

        });

        if (mUrl.equals(USER_AGREEMENT)) {
            webView.loadUrl("file:///android_asset/user_protocol.html");
        } else if (mUrl.equals(PAYMENT_AGREEMENT)) {
            webView.loadUrl("file:///android_asset/payment_agreement.html");
        } else {
            webView.loadUrl(Uri.parse(mUrl).toString());
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            if (webView.canGoBack()) {
                webView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
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
            return true;
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (pb != null) {
                pb.setProgress(newProgress);
                if (newProgress == 100) {
                    pb.setVisibility(View.GONE);
                }
            }
            super.onProgressChanged(view, newProgress);
        }
    }

}
