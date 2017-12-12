package com.hhly.lyygame.presentation.view.webpay;

import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.ToastUtil;
import com.hhly.lyygame.presentation.view.immersive.IImmersiveApply;

import butterknife.BindView;

/**
 * Created by dell on 2017/6/1.
 */

public class WebPayFragment extends BaseFragment implements IImmersiveApply {

    public static WebPayFragment newInstance(String url, String title) {
        WebPayFragment fragment = new WebPayFragment();
        Bundle bundle = new Bundle();
        bundle.putString(WebPayActivity.PAY_URL, url);
        bundle.putString(WebPayActivity.PAY_TITLE, title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @BindView(R.id.pb)
    ProgressBar mProgressBar;
    @BindView(R.id.webView)
    WebView mWebView;

    private String url;
    private String title;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        url = getArguments().getString(WebPayActivity.PAY_URL);
        title = getArguments().getString(WebPayActivity.PAY_TITLE);
        url = parseUrl(url);
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        mToolbarHelper.setTitle(title);
        initWebView();
    }

    private String parseUrl(String url) {
        if (url.startsWith("http") || url.startsWith("https")) {
            return url;
        }
        return "http://" + url;
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
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if( url.startsWith("http:") || url.startsWith("https:") ) {
                    return false;
                }
                try{
                    if(url.startsWith("weixin://") || url.startsWith("alipays://")|| url.startsWith("alipay://") || url.startsWith("alipayqr://")){
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                        return true;
                    }
                } catch (Exception e){
                    if(url.startsWith("weixin://")){
                        ToastUtil.showTip(getActivity(), getString(R.string.tip_wechat_not_install));
                        getActivity().finish();
                    } else if(url.startsWith("alipay://") || url.startsWith("alipayqr://")){
                        ToastUtil.showTip(getContext(), getString(R.string.tip_alipay_not_install));
                        getActivity().finish();
                    }
                    view.loadUrl(url);
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });

        mWebView.loadUrl(Uri.parse(url).toString());
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

    @Override
    protected void fetchData(boolean isLoadMore) {

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

}
