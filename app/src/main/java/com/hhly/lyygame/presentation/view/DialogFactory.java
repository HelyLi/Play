package com.hhly.lyygame.presentation.view;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;

import com.github.xmxu.cf.Callback;
import com.github.xmxu.cf.ShareResult;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.model.GoodsInfo;
import com.hhly.lyygame.presentation.rxbus.event.OtherEvent;
import com.hhly.lyygame.presentation.view.address.AddressPickupDialog;
import com.hhly.lyygame.presentation.view.info.InfoGenderDialog;
import com.hhly.lyygame.presentation.view.info.InfoGenderSelectDialog;
import com.hhly.lyygame.presentation.view.info.InfoPictureSelectDialog;
import com.hhly.lyygame.presentation.view.main.OtherDialog;
import com.hhly.lyygame.presentation.view.mall.ExchangeResultDialog;
import com.hhly.lyygame.presentation.view.mall.MallOptDialog;
import com.hhly.lyygame.presentation.view.me.ExitLoginDialog;
import com.hhly.lyygame.presentation.view.message.MessageDialog;
import com.hhly.lyygame.presentation.view.pay.bankpay.ValidityTimeSelectDialog;
import com.hhly.lyygame.presentation.view.pay.bankpay.VerifyBankCardDialog;
import com.hhly.lyygame.presentation.view.pay.bankpay.VoucherTypeDialog;
import com.hhly.lyygame.presentation.view.share.ShareContent;
import com.hhly.lyygame.presentation.view.share.ShareDialog;
import com.hhly.lyygame.presentation.view.transfer.TransferDialog;

/**
 * Created by Simon on 2016/11/15.
 */

public class DialogFactory {

    public static ProgressDialog createProgressDialog(Context context) {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage(context.getString(R.string.default_progress_tip));
        return dialog;
    }

    public static Dialog createGender(Context context, InfoGenderDialog.OnClickListener onClickListener) {
        return new InfoGenderDialog(context, onClickListener);
    }

    public static Dialog createGenderSelectDialog(Context context, InfoGenderSelectDialog.OnGenderSelectListener onGenderSelectListener) {
        return new InfoGenderSelectDialog(context, onGenderSelectListener);
    }

    public static BottomSheetDialog createSelectPhoto(Context context, InfoPictureSelectDialog.OnPictureSelectListener pictureSelectListener) {
        return new InfoPictureSelectDialog(context, pictureSelectListener);
    }

    public static BottomSheetDialogFragment createAddressPickup(AddressPickupDialog.OnSelectedListener selectedListener) {
        AddressPickupDialog dialog = new AddressPickupDialog();
        dialog.setSelectedListener(selectedListener);
        return dialog;
    }

    public static Dialog createMallOpt(Context context, @NonNull GoodsInfo goodsInfo, @MallOptDialog.OptType int type, MallOptDialog.MallStateCallback callback) {
        return new MallOptDialog(context, goodsInfo, type, callback);
    }

    public static Dialog createExchangeResult(Context context, int type, String name) {
        return new ExchangeResultDialog.Builder(context).type(type).name(name).build();
    }

    public static Dialog createTransfer(Context context, int type) {
        return new TransferDialog.Builder(context).type(type).build();
    }

    public static Dialog createOther(Context context, OtherEvent otherEvent, OtherDialog.OtherCallback callback){
        return new OtherDialog(context,otherEvent, callback);
    }

    public static Dialog createExit(Context context, ExitLoginDialog.ExitCallback callback){
        return new ExitLoginDialog(context, callback);
    }

    public static Dialog createMsg(Context context, int position, MessageDialog.MsgCallback callback){
        return new MessageDialog(context,position,  callback);
    }

    public static BottomSheetDialog createShare(Context context, int type, ShareContent shareContent) {
        return new ShareDialog.Builder(context).type(type).shareContent(shareContent).build();
    }

    public static ShareDialog createShare(Context context, int type, ShareContent shareContent, Callback<ShareResult> callback) {
        return new ShareDialog.Builder(context).type(type).shareContent(shareContent).callback(callback).build();
    }

    public static VerifyBankCardDialog createVerifyBankCardDialog() {
        return new VerifyBankCardDialog();
    }

    public static VoucherTypeDialog createVoucherTypeDialog() {
        return new VoucherTypeDialog();
    }

    public static ValidityTimeSelectDialog createValidityTimeSelectDialog() {
        return new ValidityTimeSelectDialog();
    }
}
