package com.hhly.lyygame.presentation.view.transfer;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.widget.ImageView;
import android.widget.TextView;

import com.hhly.lyygame.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TransferDialog extends Dialog {

    @BindView(R.id.transfer_iv)
    ImageView mTransferIv;
    @BindView(R.id.transfer_tv)
    TextView mTransferTv;

    @IntDef({TYPE_SUCCEED, TYPE_FAILURE})
    @Retention(RetentionPolicy.SOURCE)
    @interface Type {
    }

    public static final int TYPE_SUCCEED = 0;
    public static final int TYPE_FAILURE = 1;

    private int mType;

    private TransferDialog(Context context) {
        super(context);
    }

    private TransferDialog(Builder builder) {
        super(builder.context, R.style.Theme_LyyGame_Dialog_Exchange_Result);
        mType = builder.type;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_transfer_result_layout);
        ButterKnife.bind(this);
        setCanceledOnTouchOutside(true);

        if (mType == TYPE_SUCCEED) {
            mTransferIv.setBackgroundResource(R.drawable.transfer_success);
            mTransferTv.setText(R.string.lyy_exchange_succeed);
        } else {
            mTransferIv.setBackgroundResource(R.drawable.transfer_failure);
            mTransferTv.setText(R.string.lyy_exchange_failure);
        }
    }

    public static class Builder {
        private int type;
        private Context context;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder type(@Type int type) {
            this.type = type;
            return this;
        }

        public TransferDialog build() {
            return new TransferDialog(this);
        }
    }
}
