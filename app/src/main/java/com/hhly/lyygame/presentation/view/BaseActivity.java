package com.hhly.lyygame.presentation.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.hhly.lyygame.App;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.db.manager.AccountManager;
import com.hhly.lyygame.presentation.permission.PermissionListener;
import com.hhly.lyygame.presentation.rxbus.RxBus;
import com.hhly.lyygame.presentation.rxbus.event.OtherEvent;
import com.hhly.lyygame.presentation.utils.ActivityUtil;
import com.hhly.lyygame.presentation.utils.CollectionUtil;
import com.hhly.lyygame.presentation.utils.InputUtil;
import com.hhly.lyygame.presentation.utils.StatisticalUtil;
import com.hhly.lyygame.presentation.view.immersive.IImmersiveApply;
import com.hhly.lyygame.presentation.view.immersive.ToolbarHelper;
import com.hhly.lyygame.presentation.view.main.OtherDialog;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


/**
 * Created by Simon on 2016/11/3.
 */

public abstract class BaseActivity extends RxAppCompatActivity {

    protected Unbinder mUnbinder;
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    protected ToolbarHelper mToolbarHelper;

    private static PermissionListener sPermissionListener;

    private Disposable mRxBusSubscribe;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        App.addActivity(this);

        setContentView(getLayoutId());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);

            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            );
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            }
        }

        mUnbinder = ButterKnife.bind(this);

        if (this instanceof IImmersiveApply) {
            mToolbarHelper = new ToolbarHelper();
            mToolbarHelper.attach((IImmersiveApply) this, getContentView());
        }

        View view = getContentView().findViewById(R.id.toolBar);
        if (view != null) {
            Toolbar toolbar = (Toolbar) view;
            toolbar.setNavigationIcon(R.drawable.ic_navigation_up);
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setTitle("");
                if (enableHomeAsUp()) {
                    actionBar.setDisplayShowHomeEnabled(true);
                    actionBar.setDisplayHomeAsUpEnabled(true);
                } else {
                    actionBar.setDisplayShowHomeEnabled(false);
                    actionBar.setDisplayHomeAsUpEnabled(false);
                }
            }
        }

        mRxBusSubscribe = RxBus.get()
                .toObservable(OtherEvent.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<OtherEvent>() {
                               @Override
                               public void accept(@NonNull OtherEvent otherEvent) throws Exception {
                                   if (BaseActivity.this == App.getTopActivity()){//地址一样即可
                                       DialogFactory.createOther(getContext(), otherEvent, mOtherCallback).show();
                                   }
                               }
                           }
                );
    }

    private OtherDialog.OtherCallback mOtherCallback = new OtherDialog.OtherCallback() {
        @Override
        public void otherSure(int ohterType) {
            if (ohterType == OtherEvent.OTHER_401_TYPE){

                AccountManager.getInstance().clearCurrentUserInfo();
                postDelay(new Runnable() {
                    @Override
                    public void run() {
                        ActivityUtil.checkLoginAndRequestLoginNormal(App.getTopActivity());
                    }
                }, 500);
            }
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            View focusView = null;
            if ((focusView = getCurrentFocus()) != null && enableHideInputSoft((int) ev.getX(), (int) ev.getY())) {
                Rect hitRect = new Rect();
                focusView.getHitRect(hitRect);
                if (!(focusView instanceof EditText) || !hitRect.contains((int) ev.getX(), (int) ev.getY())) {
                    InputUtil.hideInputSoftFromWindowMethod(getContext(), getCurrentFocus());
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    protected boolean enableHideInputSoft(int x, int y) {
        return true;
    }

    protected int getLayoutId() {
        return R.layout.activity_base_layout;
    }

    protected Context getContext() {
        return this;
    }

    protected View getContentView() {
        return getWindow().getDecorView().findViewById(android.R.id.content);
    }

    protected void postDelay(Runnable runnable, long time) {
        mHandler.postDelayed(runnable, time);
    }

    protected boolean enableFullScreen() {
        return false;
    }

    protected boolean enableHomeAsUp() {
        return true;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (enableFullScreen()) {
            if (hasFocus && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                View decorView = getWindow().getDecorView();
                decorView.setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            } else {
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
            }
        }
    }

    protected void setTitleForToolbar(String title) {
        if (mToolbarHelper != null) {
            mToolbarHelper.setTitle(title);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.removeActivity(this);
        if (mToolbarHelper != null) {
            mToolbarHelper.detach();
        }
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        if (!mRxBusSubscribe.isDisposed()){
            mRxBusSubscribe.dispose();;
        }
    }

    /**
     * 权限申请
     *
     * @param permissions
     * @param listener
     */
    @TargetApi(Build.VERSION_CODES.M)
    static public void requestRuntimePermission(String[] permissions, PermissionListener listener) {
        sPermissionListener = listener;
        List<String> list = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(App.getTopActivity(), permission) != PackageManager.PERMISSION_GRANTED) {
                list.add(permission);
            }
        }

        if (CollectionUtil.isEmpty(list) && sPermissionListener != null) {
            sPermissionListener.granted();
            return;
        }

        ActivityCompat.requestPermissions(App.getTopActivity(), (String[]) list.toArray(new String[list.size()]), 0);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 0: {
                if (grantResults.length > 0 && sPermissionListener != null) {
                    List<String> list = new ArrayList<>();
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            list.add(permissions[i]);
                        }
                    }
                    if (CollectionUtil.isEmpty(list)) {
                        sPermissionListener.granted();
                    } else {
                        sPermissionListener.onDenied(list);
                    }
                }
            }
            break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        StatisticalUtil.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        StatisticalUtil.onPause(this);
    }


    public <T> LifecycleTransformer<T> bindToLife() {
        return this.<T>bindToLifecycle();
    }
}
