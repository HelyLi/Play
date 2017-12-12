package com.hhly.lyygame.presentation.view.mall;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hhly.lyygame.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Simon on 2016/12/7.
 */

public class ExchangeResultDialog extends Dialog {

    @IntDef({TYPE_SUCCEED, TYPE_FAILURE})
    @Retention(RetentionPolicy.SOURCE)
    @interface Type{}

    public static final int TYPE_SUCCEED = 0;
    public static final int TYPE_FAILURE = 1;
    
    @BindView(R.id.exchange_status_tv)
    TextView mExchangeStatusTv;
    @BindView(R.id.gift_name_tv)
    TextView mGiftNameTv;
    @BindView(R.id.exchange_dialog_root)
    LinearLayout mExchangeDialogRoot;

    private int mType;
    private String mName;

    private ExchangeResultDialog(Context context) {
        super(context);
    }

    private ExchangeResultDialog(Builder builder) {
        super(builder.context, R.style.Theme_LyyGame_Dialog_Exchange_Result);
        mType = builder.type;
        mName = builder.name;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_exchange_result_layout);
        ButterKnife.bind(this);

        mGiftNameTv.setText(mName);

        if (mType == TYPE_SUCCEED) {
            mExchangeDialogRoot.setBackgroundResource(R.drawable.bg_exchange_succeed);
            mExchangeStatusTv.setText(R.string.lyy_exchange_succeed);
        } else {
            mExchangeDialogRoot.setBackgroundResource(R.drawable.bg_exchange_failure);
            mExchangeStatusTv.setText(R.string.lyy_exchange_failure);
        }

    }

    @OnClick(R.id.confirm_btn)
    public void onClick() {
        dismiss();
    }

    public static class Builder {
        private int type;
        private String name;
        private Context context;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder type(@Type int type) {
            this.type = type;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public ExchangeResultDialog build() {
            return new ExchangeResultDialog(this);
        }
    }
}
