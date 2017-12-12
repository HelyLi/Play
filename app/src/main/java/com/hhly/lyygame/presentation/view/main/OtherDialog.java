package com.hhly.lyygame.presentation.view.main;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hhly.lyygame.R;
import com.hhly.lyygame.presentation.rxbus.event.OtherEvent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dell on 2017/5/18.
 */

public class OtherDialog extends Dialog {

    @BindView(R.id.other_content_tv)
    TextView mOtherContentTv;
    @BindView(R.id.other_sure)
    TextView mOtherSure;
    private OtherEvent mOtherEvent;

    public OtherDialog(Context context, OtherEvent otherEvent, OtherCallback callback) {
        super(context, R.style.Theme_LyyGame_Dialog_Exchange_Result);
        mOtherEvent = otherEvent;
        mOtherCallback = callback;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_other_layout);
        ButterKnife.bind(this);
        setCanceledOnTouchOutside(false);

        if (mOtherEvent.getEventType() == OtherEvent.OTHER_401_TYPE) {
            mOtherContentTv.setText(R.string.lyy_game_other_401);
        } else if (mOtherEvent.getEventType() == OtherEvent.OTHER_402_TYPE) {
            mOtherContentTv.setText(R.string.lyy_game_other_402);
        }
    }

    @OnClick(R.id.other_sure)
    public void onOtherSure(View view){
        dismiss();
        if (mOtherCallback != null){
            mOtherCallback.otherSure(mOtherEvent.getEventType());
        }
    }

    private OtherCallback mOtherCallback;

    public interface OtherCallback {
        void otherSure(int ohterType);
    }


}
