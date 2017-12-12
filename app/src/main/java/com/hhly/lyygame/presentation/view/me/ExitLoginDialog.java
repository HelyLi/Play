package com.hhly.lyygame.presentation.view.me;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hhly.lyygame.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ExitLoginDialog extends Dialog {

    @BindView(R.id.exit_cancel)
    TextView mExitCancel;
    @BindView(R.id.exit_sure)
    TextView mExitSure;

    public ExitLoginDialog(Context context, ExitCallback callback) {
        super(context, R.style.Theme_LyyGame_Dialog_Exchange_Result);
        mExitCallback = callback;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_exit_login_layout);
        ButterKnife.bind(this);
        setCanceledOnTouchOutside(true);
    }

    @OnClick({R.id.exit_cancel, R.id.exit_sure})
    void onExitClick(View view){
        switch (view.getId()){
            case R.id.exit_cancel:
                break;
            case R.id.exit_sure:
                if (mExitCallback != null){
                    mExitCallback.exitSure();
                }
                break;
        }
        dismiss();
    }

    private ExitCallback mExitCallback;

    public interface ExitCallback{
        void exitSure();
    }

}
