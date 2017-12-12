package com.hhly.lyygame.presentation.view.pay;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hhly.lyygame.R;

import java.util.ArrayList;

/**
 * Created by ${HELY} on 17/1/20.
 * 邮箱：heli.lixiong@gmail.com
 */

public class CoinsItemAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    public static final int TYPE_DEFAULT = 0x11;
    public static final int TYPE_CUSTOM = 0x12;
    private MyTextWatcher mTextWatcher;
    private TextView customAmountEdit;

    public CoinsItemAdapter() {
        super(new ArrayList<MultiItemEntity>());
        addItemType(TYPE_DEFAULT, R.layout.lyy_pay_coins_item_layout);
        addItemType(TYPE_CUSTOM, R.layout.lyy_pay_amount_custom_item_layout);
        mTextWatcher = new MyTextWatcher();
    }

    @Override
    protected void convert(BaseViewHolder helper, final MultiItemEntity item) {
        int itemType = item.getItemType();
        switch (itemType) {
            case TYPE_DEFAULT:
                CoinsItemModel coinsItemModel = (CoinsItemModel) item;
                TextView payAmountTv = helper.getView(R.id.pay_amount_tv);
                payAmountTv.setSelected(coinsItemModel.isChecked());
                payAmountTv.setText(mContext.getString(R.string.lyy_game_pay_rmb, coinsItemModel.getRmb()));
                if (coinsItemModel.isChecked()) {
                    if (null != customAmountEdit) {
                        customAmountEdit.setText("");
                        customAmountEdit.clearFocus();
                        customAmountEdit.setCursorVisible(false);
                    }
                }
                break;
            case TYPE_CUSTOM:
                CustomItem customItem = (CustomItem) item;
                customAmountEdit = helper.getView(R.id.pay_other_amount_tv);
                if (customItem.isChecked()) {
                    customAmountEdit.setCursorVisible(true);
                }
                customAmountEdit.setSelected(customItem.isChecked());
                customAmountEdit.addTextChangedListener(mTextWatcher);
                mTextWatcher.setItem(customItem);
                break;
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private CustomItem item;

        public void setItem(CustomItem item) {
            this.item = item;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            item.setAmount(TextUtils.isEmpty(s) ? 0 : Integer.parseInt(s.toString()));
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    public static class CustomItem implements MultiItemEntity, BasePayAmountItem {
        private int amount;
        private boolean isChecked;

        public CustomItem() {

        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getAmount() {
            return amount;
        }

        @Override
        public int getItemType() {
            return TYPE_CUSTOM;
        }

        @Override
        public void setChecked(boolean isChecked) {
            this.isChecked = isChecked;
        }

        public boolean isChecked() {
            return isChecked;
        }

        @Override
        public int getRmb() {
            return amount;
        }
    }

    public interface BasePayAmountItem {
        void setChecked(boolean isChecked);

        int getRmb();
    }
}
