package com.hhly.lyygame.presentation.view.messagedetail;

import android.app.Activity;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
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

import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.protocol.game.NewsDetailsResp;
import com.hhly.lyygame.data.net.protocol.user.MsgInfoResp;
import com.hhly.lyygame.presentation.utils.DateUtils;
import com.hhly.lyygame.presentation.utils.DisplayUtil;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.DialogFactory;
import com.hhly.lyygame.presentation.view.immersive.IImmersiveApply;
import com.hhly.lyygame.presentation.view.share.ShareContent;
import com.hhly.lyygame.presentation.view.share.ShareDialog;

import java.text.ParseException;

import butterknife.BindView;

/**
 * Created by HELY on 16/12/29.
 * 邮箱：heli.lixiong@gmail.com
 */

public class WebDetailFragment extends BaseFragment implements WebDetailContract.View, IImmersiveApply {

    public static final String EXTRA_WEB_ID = "extra_web_id";

    public static final int REFRESH_INFO_REQUEST_CODE = 0;

    public static final String MSG_ID = "extra_msg_id";
    public static final String NOT_ID = "extra_not_id";//公告
    public static final String ACT_ID = "extra_act_id";//活动

    private int mMsgId = 0;
    private int mNotId = 0;
    private int mActId = 0;

    WebDetailContract.Presenter mPresenter;

    @BindView(R.id.pb)
    ProgressBar mProgressBar;
    @BindView(R.id.webView)
    WebView webView;

    private int[] mWebIds;
    private MenuItem mShareMenuItem;

    public static WebDetailFragment newInstance(int[] webIds) {
        WebDetailFragment fragment = new WebDetailFragment();

        Bundle bundle = new Bundle();
        bundle.putIntArray(EXTRA_WEB_ID, webIds);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mWebIds = bundle.getIntArray(EXTRA_WEB_ID);
        }
        postDelay(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < mWebIds.length; i++) {
                    if (mWebIds[i] != 0) {
                        switch (i) {
                            case 0:
                                mMsgId = mWebIds[0];
                                mPresenter.updateMessage(mWebIds[0]);
                                mPresenter.getMessageDetail(mWebIds[0]);
                                break;
                            case 1://公告
                                mNotId = mWebIds[1];
                                mPresenter.getNoticeDetail(mWebIds[1]);
                                break;
                            case 2://活动
                                mActId = mWebIds[2];
                                mPresenter.getActivityDetail(mWebIds[2]);
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }, 500);
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(false);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //        webView.getSettings().setUseWideViewPort(true);
        //        webView.getSettings().setLoadWithOverviewMode(true);
        settings.setDefaultFontSize(18);
        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
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
    }

    private void shareDetail() {
        ShareContent shareContent = new ShareContent();
        shareContent.setAppName(getString(R.string.app_name));
        shareContent.setImage("http://public.13322.com/23c192a0.png");
        shareContent.setLink("http://m.game.13322.com");
        shareContent.setContent("");
//        shareContent.setInviteCode("1233");
//        shareContent.setContent(getString(R.string.lyy_third_invite, "12345"));
//        shareContent.setDescription(getString(R.string.lyy_third_invite, "12345"));
        shareContent.setTitle("玩一下");

        DialogFactory.createShare(getActivity(), ShareDialog.TYPE_SHARE, shareContent).show();
    }

    @Override
    protected void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
            return;
        }

        super.onBackPressed();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    protected void fetchData(boolean isLoadMore) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_webview_layout;
    }

    @Override
    public void setPresenter(WebDetailContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.share_menu, menu);
        mShareMenuItem = menu.findItem(R.id.share_menu);
        if (mShareMenuItem != null) {
            mShareMenuItem.setVisible(false);
        }
    }

    @Override
    public void showMessageDetail(MsgInfoResp.MessageDetailBean detailMsg) {

        if (detailMsg != null) {
            StringBuilder webData = new StringBuilder(codePrefixOne);
            webData.append("*{color:" + "#123456;font-size:")
                    .append(DisplayUtil.dip2px(getContext(), 14))
                    .append("px;}body{word-wrap:break-word;font-family:Arial}")
                    .append(codePrefixTwo);

            webData.append("<h1>").append(detailMsg.getTitle()).append("</h1>");
            long date = detailMsg.getTime();
            try {
                String dateStr = DateUtils.longToString(date, "yyyy-MM-dd HH:mm");
                webData.append("<h2>").append(dateStr).append("</h2>");
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (!TextUtils.isEmpty(detailMsg.getContent())) {
                webData.append(detailMsg.getContent());
            } else if (!TextUtils.isEmpty(detailMsg.getSynopsis())) {
                webData.append(detailMsg.getSynopsis());
            }
            webData.append(codeSubfix);
            if (webView != null) {
                webView.loadDataWithBaseURL(null, webData.toString(), "text/html", "utf-8", null);
            }
        }


    }

    //    private boolean updateMsg = false;

    //    public boolean getUpadteMegState() {
    //        return updateMsg;
    //    }

    @Override
    public void updateMessageSuccess() {
        //        updateMsg = true;

    }

    @Override
    public void showActivityDetail(NewsDetailsResp.NewsNotice newsNotice) {
        if (newsNotice == null)
            return;
        if (webView != null)
            if (newsNotice.getTitleHref() != null) {
                webView.loadUrl(newsNotice.getTitleHref());
                return;
            }
        webView.loadDataWithBaseURL(null, parseNoticeDetailWebData(newsNotice), "text/html", "utf-8", null);
    }

    @Override
    public void showNoticeDetail(NewsDetailsResp.NewsNotice newsNotice) {
        if (newsNotice == null)
            return;
        if (webView != null) {
            if (newsNotice.getTitleHref() != null) {
                webView.loadUrl(newsNotice.getTitleHref());
                return;
            }
            webView.loadDataWithBaseURL(null, parseNoticeDetailWebData(newsNotice), "text/html", "utf-8", null);
        }
    }

    private String parseNoticeDetailWebData(NewsDetailsResp.NewsNotice newsNotice) {
        StringBuilder webData = new StringBuilder(codePrefixOne);
        webData.append("*{color: #123456;font-size:")
                .append(DisplayUtil.dip2px(getContext(), 14))
                .append("px;}body{word-wrap:break-word;font-family:Arial}")
                .append(codePrefixTwo);

        webData.append("<h1>").append(newsNotice.getTitle()).append("</h1>");
        long date = newsNotice.getCreatorTime();
        try {
            String dateStr = DateUtils.longToString(date, "yyyy-MM-dd HH:mm");
            webData.append("<h2>").append(dateStr).append("</h2>");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        webData.append(newsNotice.getContent());
        webData.append(codeSubfix);
        return webData.toString();
    }

    private String codePrefixOne = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">" +
            "<html>" +
            "<head>" +
            "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=\">" +
            "<style type=\"text/css\">";

    private String codePrefixTwo = "</style>" + "</head>" + "<body></body>" + "</html>";

    private String codeSubfix = "</body></html>";

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
            if (mProgressBar != null) {
                mProgressBar.setProgress(newProgress);
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                }
            }
            super.onProgressChanged(view, newProgress);
        }
    }

    public void finish() {

        Intent intent = new Intent();
        Bundle bundle = new Bundle();

        if (mMsgId != 0) {
            bundle.putInt(WebDetailFragment.MSG_ID, mMsgId);
        } else if (mNotId != 0) {
            bundle.putInt(WebDetailFragment.NOT_ID, mMsgId);
        } else if (mActId != 0) {
            bundle.putInt(WebDetailFragment.ACT_ID, mMsgId);
        }

        intent.putExtras(bundle);
        getActivity().setResult(Activity.RESULT_OK, intent);

    }

}
