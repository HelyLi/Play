package com.hhly.lyygame.presentation.view.pay.bankpay;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.net.protocol.pay.QuickPayApplyReq;
import com.hhly.lyygame.presentation.utils.DateUtils;
import com.hhly.lyygame.presentation.utils.RegexUtils;
import com.hhly.lyygame.presentation.view.DialogFactory;
import com.hhly.lyygame.presentation.view.ToastUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.hhly.lyygame.presentation.view.pay.bankpay.SupportBankFragment.DEPOSIT;

/**
 * 验证银行卡信息adapter
 * Created by dell on 2017/5/13.
 */

public class VerifyBankCardInfoAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    private static final int TYPE_NORMAL = 0X11;
    private static final int TYPE_EXPANDABLE = 0X12;
    private static final int TYPE_INPUT = 0X13;
    private static final int TYPE_HINT = 0X14;
    //可展开的数据
    private List<MultiItemEntity> mExpandableItemList;
    //底部用户输入的数据
    private List<MultiItemEntity> mFooterItemList;

    private QuickPayApplyReq mQuickPayApplyReq;
    //是否是银行卡
    private boolean isBankCard;

    public VerifyBankCardInfoAdapter() {
        super(new ArrayList<MultiItemEntity>());
        addItemType(TYPE_NORMAL, R.layout.verify_bank_card_info_normal_item_layout);
        addItemType(TYPE_EXPANDABLE, R.layout.verify_bank_card_info_expan_item_layout);
        addItemType(TYPE_INPUT, R.layout.verify_bank_card_info_input_item_layout);
        addItemType(TYPE_HINT, R.layout.verify_bank_card_info_hint_item_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        int itemType = item.getItemType();
        switch (itemType) {
            case TYPE_NORMAL:
                final NormalItem normalItem = (NormalItem) item;
                helper.setText(R.id.title_tv, normalItem.getTitle());
                TextView valueTv = helper.getView(R.id.value_tv);
                valueTv.setText(normalItem.getValue());
                if (!TextUtils.isEmpty(normalItem.getHint())) {
                    valueTv.setHint(normalItem.getHint());
                    valueTv.setCompoundDrawablesWithIntrinsicBounds(null, null,
                            ContextCompat.getDrawable(mContext, R.drawable.ic_arrow_right_01), null);
                } else {
                    valueTv.setHint("");
                    valueTv.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                }
                helper.setText(R.id.value_tv, normalItem.getValue());
                if (normalItem.getRunnable() != null) {
                    helper.getView(R.id.content_layout).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            normalItem.getRunnable().run();
                        }
                    });
                }
                break;
            case TYPE_EXPANDABLE:
                final ExpandableItem expansionItem = (ExpandableItem) item;
                TextView expansionTv = helper.getView(R.id.expandable_tv);
                if (expansionItem.isExpansion) {
                    expansionTv.setText(mContext.getString(R.string.lyy_pay_pack_up_info));
                    expansionTv.setCompoundDrawablesWithIntrinsicBounds(null, null,
                            ContextCompat.getDrawable(mContext, R.drawable.ic_arrow_up), null);
                } else {
                    expansionTv.setText(mContext.getString(R.string.lyy_pay_expansion_info));
                    expansionTv.setCompoundDrawablesWithIntrinsicBounds(null, null,
                            ContextCompat.getDrawable(mContext, R.drawable.ic_arrow_down), null);
                }
                helper.getView(R.id.expandable_layout).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        expansionItem.setExpansion(!expansionItem.isExpansion);
                        expandableDetailsInfo(expansionItem.isExpansion);
                    }
                });
                break;
            case TYPE_INPUT:
                InputItem inputItem = (InputItem) item;
                helper.setText(R.id.title_tv, inputItem.getTitle());
                EditText valueEdit = helper.getView(R.id.value_edit);
                valueEdit.setInputType(inputItem.getInputType());
                valueEdit.setHint(inputItem.getValueHint());
                MyTextWatcher myTextWatcher = new MyTextWatcher();
                myTextWatcher.setItem(inputItem);
                valueEdit.addTextChangedListener(myTextWatcher);
                break;
        }
    }

    /**
     * 普通的item
     */
    static class NormalItem implements MultiItemEntity {
        private String mTitle;
        private String mValue;
        private String mHint;
        private Runnable mRunnable;
        private String typeId;

        public NormalItem(String title, String value, String hint) {
            this.mTitle = title;
            this.mValue = value;
            this.mHint = hint;
        }

        public String getTitle() {
            return mTitle;
        }

        public String getValue() {
            return mValue;
        }

        public void setValue(String value) {
            mValue = value;
        }

        public String getHint() {
            return mHint;
        }

        public void setRunnable(Runnable runnable) {
            mRunnable = runnable;
        }

        public Runnable getRunnable() {
            return mRunnable;
        }

        public void setTypeId(String typeId) {
            this.typeId = typeId;
        }

        public String getTypeId() {
            return typeId;
        }

        @Override
        public int getItemType() {
            return TYPE_NORMAL;
        }
    }

    /**
     * 可展开收起的item
     */
    static class ExpandableItem implements MultiItemEntity {
        private boolean isExpansion;

        public ExpandableItem() {
        }

        public void setExpansion(boolean expansion) {
            isExpansion = expansion;
        }

        public boolean isExpansion() {
            return isExpansion;
        }

        @Override
        public int getItemType() {
            return TYPE_EXPANDABLE;
        }
    }

    /**
     * 提醒item
     */
    static class HintItem implements MultiItemEntity {

        @Override
        public int getItemType() {
            return TYPE_HINT;
        }
    }

    /**
     * 带输入的item
     */
    static class InputItem implements MultiItemEntity {
        private String mTitle;
        private String mValue;
        private String mValueHint;
        private int mInputType;

        public InputItem(String title, String value, String valueHint, int inputType) {
            this.mTitle = title;
            this.mValue = value;
            this.mValueHint = valueHint;
            this.mInputType = inputType;
        }

        public String getTitle() {
            return mTitle;
        }

        public String getValue() {
            return mValue;
        }

        public void setValue(String value) {
            mValue = value;
        }

        public String getValueHint() {
            return mValueHint;
        }

        public int getInputType() {
            return mInputType;
        }


        @Override
        public int getItemType() {
            return TYPE_INPUT;
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private InputItem item;

        public void setItem(InputItem item) {
            this.item = item;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            item.setValue(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    /**
     * 更新数据
     *
     * @param context         context
     * @param payApplyReq     数据
     * @param fragmentManager fragmentManager
     */
    public void updateItems(Context context, QuickPayApplyReq payApplyReq,
                            final FragmentManager fragmentManager) {
        if (payApplyReq == null)
            return;
        isBankCard = String.valueOf(DEPOSIT).equals(payApplyReq.getDcType());
        mQuickPayApplyReq = payApplyReq;
        addData(new NormalItem(context.getString(R.string.lyy_pay_order_amount), context.getString(R.string.lyy_game_pay_rmb,
                Integer.parseInt(payApplyReq.getTotalFee())), null));
        initExpandableItemList(context, payApplyReq);
        addData(new ExpandableItem());
        addData(new HintItem());
        initFooterItemList(context, fragmentManager, isBankCard);
    }

    /**
     * 展开或收起详细信息
     *
     * @param isExpansion 是否展开
     */
    private void expandableDetailsInfo(boolean isExpansion) {
        if (isExpansion) {
            getData().addAll(1, mExpandableItemList);
        } else {
            getData().removeAll(mExpandableItemList);
        }
        notifyDataSetChanged();
    }

    /**
     * 初始化可展开的数据
     *
     * @param context     context
     * @param payApplyReq 数据
     */
    private void initExpandableItemList(Context context, QuickPayApplyReq payApplyReq) {
        mExpandableItemList = new ArrayList<>();
        mExpandableItemList.add(new NormalItem(context.getString(R.string.lyy_pay_user_account), payApplyReq.getBuyerId(), null));
        mExpandableItemList.add(new NormalItem(context.getString(R.string.lyy_pay_bank_card), payApplyReq.getBankName(), null));
        mExpandableItemList.add(new NormalItem(context.getString(R.string.lyy_pay_bank_card_number), payApplyReq.getAccNo(), null));
        mExpandableItemList.add(new NormalItem(context.getString(R.string.lyy_pay_trading_time),
                DateUtils.dateToString(new Date(payApplyReq.getTransactionTime()), "yyyy-MM-dd"), null));
        mExpandableItemList.add(new NormalItem(context.getString(R.string.lyy_pay_order_number), payApplyReq.getOutTradeNo(), null));
    }

    /**
     * 初始化底部数据
     *
     * @param context         context
     * @param fragmentManager fragment
     * @param isBankCard      是否是银行卡
     */
    private void initFooterItemList(Context context, final FragmentManager fragmentManager, boolean isBankCard) {
        mFooterItemList = new ArrayList<>();
        mFooterItemList.add(new InputItem(context.getString(R.string.lyy_pay_bank_card_owner), "",
                context.getString(R.string.lyy_pay_bank_card_owner_name), InputType.TYPE_CLASS_TEXT));
        final NormalItem voucherTypeItem = new NormalItem(context.getString(R.string.lyy_pay_voucher_type), context.getString(R.string.lyy_pay_identity_card),
                context.getString(R.string.lyy_pay_select_voucher_type));
        voucherTypeItem.setTypeId("01");
        //        voucherTypeItem.setRunnable(new Runnable() {
        //            @Override
        //            public void run() {
        //                VoucherTypeDialog dialog = DialogFactory.createVoucherTypeDialog();
        //                dialog.show(fragmentManager, "dialog");
        //                dialog.setCallBack(new VoucherTypeDialog.SelectedCallBack() {
        //                    @Override
        //                    public void onSelected(VoucherTypeAdapter.VoucherTypeItem selectedTypeItem) {
        //                        int position = getData().indexOf(voucherTypeItem);
        //                        voucherTypeItem.setValue(selectedTypeItem.getVoucherTypeName());
        //                        voucherTypeItem.setTypeId(selectedTypeItem.getTypeId());
        //                        notifyItemChanged(position);
        //                    }
        //                });
        //            }
        //        });
        mFooterItemList.add(voucherTypeItem);
        mFooterItemList.add(new InputItem(context.getString(R.string.lyy_pay_voucher_num), "",
                context.getString(R.string.lyy_pay_voucher_num_hint), InputType.TYPE_CLASS_TEXT));
        if (!isBankCard) {
            final NormalItem validityItem = new NormalItem(context.getString(R.string.lyy_pay_validity_time),
                    "", context.getString(R.string.lyy_pay_select_validity_time));
            validityItem.setRunnable(new Runnable() {
                @Override
                public void run() {
                    ValidityTimeSelectDialog dialog = DialogFactory.createValidityTimeSelectDialog();
                    dialog.setCallBack(new ValidityTimeSelectDialog.OnSelectedCallBack() {
                        @Override
                        public void onCallBack(String year, String month) {
                            int position = getData().indexOf(validityItem);
                            validityItem.setValue(year + "/" + month);
                            notifyItemChanged(position);
                        }
                    });
                    dialog.show(fragmentManager, "dialog");
                }
            });
            mFooterItemList.add(validityItem);
            mFooterItemList.add(new InputItem(context.getString(R.string.lyy_pay_cvn), "",
                    context.getString(R.string.lyy_pay_input_cvn), InputType.TYPE_CLASS_NUMBER));
        }

        mFooterItemList.add(new InputItem(context.getString(R.string.lyy_pay_phone), "",
                context.getString(R.string.lyy_pay_phone_hint), InputType.TYPE_CLASS_PHONE)
        );
        getData().addAll(mFooterItemList);
    }

    /**
     * 验证输入是否正确
     *
     * @param context context
     * @return true 正确 false 不正确
     */
    public boolean verifyInput(Context context) {
        for (int i = 0; i < mFooterItemList.size(); i++) {
            if (i == 0) {
                InputItem cardOwnerItem = (InputItem) mFooterItemList.get(0);
                if (TextUtils.isEmpty(cardOwnerItem.getValue())) {
                    ToastUtil.showTip(context, context.getString(R.string.lyy_pay_input_bank_card_owner_name));
                    return false;
                }
            } else if (i == 2) {
                NormalItem idTypeItem = (NormalItem) mFooterItemList.get(1);
                if (TextUtils.isEmpty(idTypeItem.getValue())) {
                    ToastUtil.showTip(context, context.getString(R.string.lyy_pay_select_voucher_type));
                    return false;
                }
            } else if (i == 3) {
                InputItem idNumItem = (InputItem) mFooterItemList.get(2);
                if (!RegexUtils.checkIdCard(idNumItem.getValue())) {
                    ToastUtil.showTip(context, context.getString(R.string.lyy_pay_input_voucher_num_hint));
                    return false;
                }
            }
            if (isBankCard && i == 3) {
                InputItem phoneItem = (InputItem) mFooterItemList.get(3);
                if (!RegexUtils.checkPhone(phoneItem.getValue())) {
                    ToastUtil.showTip(context, context.getString(R.string.lyy_pay_input_phone_hint));
                    return false;
                }
            }
            if (!isBankCard) {
                if (i == 3) {
                    NormalItem idTypeItem = (NormalItem) mFooterItemList.get(3);
                    if (TextUtils.isEmpty(idTypeItem.getValue())) {
                        ToastUtil.showTip(context, context.getString(R.string.lyy_pay_select_validity_time));
                        return false;
                    }
                } else if (i == 4) {
                    InputItem phoneItem = (InputItem) mFooterItemList.get(4);
                    if (TextUtils.isEmpty(phoneItem.getValue()) || 3 != phoneItem.getValue().length()) {
                        ToastUtil.showTip(context, context.getString(R.string.lyy_pay_input_cvn_hint));
                        return false;
                    }
                } else if (i == 5) {
                    InputItem phoneItem = (InputItem) mFooterItemList.get(5);
                    if (!RegexUtils.checkMobile(phoneItem.getValue())) {
                        ToastUtil.showTip(context, context.getString(R.string.lyy_pay_input_phone_hint));
                        return false;
                    }
                }
            }

        }
        return true;
    }

    /**
     * 获取验证银行卡信息的请求数据
     *
     * @return
     */
    public QuickPayApplyReq getQuickPayApplyReq(Context context) {
        if (mQuickPayApplyReq == null)
            return null;
        mQuickPayApplyReq.setCurrencyType("CNY");
        mQuickPayApplyReq.setSubject(context.getString(R.string.lyy_pay_name));
        mQuickPayApplyReq.setBody(context.getString(R.string.lyy_pay_name));
        mQuickPayApplyReq.setCustomerNm(((InputItem) mFooterItemList.get(0)).getValue());
        mQuickPayApplyReq.setCertifTp(((NormalItem) mFooterItemList.get(1)).getTypeId());
        mQuickPayApplyReq.setCertify_id(((InputItem) mFooterItemList.get(2)).getValue());
        if (!isBankCard) {
            String validityTime = ((NormalItem) mFooterItemList.get(3)).getValue().substring(2).replace("/", "");
            mQuickPayApplyReq.setExpired(validityTime);
            mQuickPayApplyReq.setCVV2(((InputItem) mFooterItemList.get(4)).getValue());
            mQuickPayApplyReq.setPhoneNo(((InputItem) mFooterItemList.get(5)).getValue());
        } else {
            mQuickPayApplyReq.setPhoneNo(((InputItem) mFooterItemList.get(3)).getValue());
        }
        mQuickPayApplyReq.setExtendParams(mQuickPayApplyReq.getBuyerId() + "|1|31111|2|2|null|null|null");
        return mQuickPayApplyReq;
    }
}
