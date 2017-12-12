package com.hhly.lyygame.presentation.view.message;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.hhly.lyygame.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dell on 2017/5/19.
 */

public class MessageDialog extends Dialog {

    @BindView(R.id.msg_other_sure)
    TextView msgOtherSure;

    private int mPosition;

    public MessageDialog(@NonNull Context context, int position, MsgCallback callback) {
        super(context, R.style.Theme_LyyGame_Dialog_Exchange_Result);
        this.mPosition = position;
        msgCallback = callback;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_message_layout);
        ButterKnife.bind(this);
        setCanceledOnTouchOutside(true);
    }

    @OnClick(R.id.msg_other_sure)
    public void onMsgSure(View view){
        if (msgCallback != null){
            msgCallback.msgSure(mPosition);
        }
        dismiss();
    }

    private MsgCallback msgCallback;

    public interface MsgCallback {
        void msgSure(int position);
    }

}
