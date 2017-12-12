package com.hhly.lyygame.presentation.view.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.protocol.update.ADInfoResp;
import com.hhly.lyygame.presentation.utils.RxHelper;
import com.hhly.lyygame.presentation.utils.SharedPrefsStrListUtil;
import com.hhly.lyygame.presentation.view.BaseActivity;
import com.hhly.lyygame.presentation.view.game.GameIntroActivity;
import com.hhly.lyygame.presentation.view.main.MainTabActivity;
import com.hhly.lyygame.presentation.view.widget.SimpleButton;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Simon on 2016/11/18.
 */

public class SplashActivity extends BaseActivity implements SplashContract.View {

    @BindView(R.id.sb_skip)
    SimpleButton mSbSkip;
    @BindView(R.id.login_iv)
    ImageView mLoginIv;

    private boolean mIsSkip = false;

    private ADInfoResp.ADInfo mADInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new SplashPresenter(this);

        if (System.currentTimeMillis() - SharedPrefsStrListUtil.getLongValue(this, "ad_time", 0L) > 60 * 60 * 1000){
            SharedPrefsStrListUtil.putLongValue(this, "ad_time", System.currentTimeMillis());
            updateViews();
        }else {
            mSbSkip.setVisibility(View.GONE);
            onDoSkip();
        }
    }

    @Override
    protected boolean enableFullScreen() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash_layout;
    }

    @Override
    public void showAdvert(ADInfoResp.ADInfo adInfo) {
        mADInfo = adInfo;
        processAD();
    }

    @Override
    public void setPresenter(SplashContract.Presenter presenter) {
        presenter.subscribe();
    }

    @Override
    public boolean isActive() {
        return false;
    }

    protected void updateViews() {
        RxHelper.countdown(5)
                .compose(this.<Integer>bindToLife())
                .subscribe(new Subscriber<Integer>() {

                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        mSbSkip.setText("跳过 " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        onDoSkip();
                    }

                    @Override
                    public void onComplete() {
                        onDoSkip();
                    }
                });

    }

    private void onDoSkip() {
        if (!mIsSkip) {
            mIsSkip = true;

            ActivityCompat.startActivity(SplashActivity.this, MainTabActivity.getCallIntent(getContext()), null);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        // 不响应后退键
        return;
    }

    @OnClick(R.id.sb_skip)
    public void onSkipClick() {
        onDoSkip();
    }

    @OnClick(R.id.login_iv)
    public void onADClick() {
        if (mADInfo == null || TextUtils.isEmpty(mADInfo.getGameId())) return;
        if (!mIsSkip) {
            mIsSkip = true;
            ActivityCompat.startActivity(SplashActivity.this, GameIntroActivity.getCallingIntent(SplashActivity.this, Integer.parseInt(mADInfo.getGameId()), true), null);
            finish();
        }
    }

    private void processAD() {
        if (mADInfo == null) return;
        if (mADInfo.getJumpType() == 1) {
            mLoginIv.setClickable(true);
        }
        Glide.with(this).load(mADInfo.getImgUrl()).fitCenter().into(mLoginIv);
    }

}
