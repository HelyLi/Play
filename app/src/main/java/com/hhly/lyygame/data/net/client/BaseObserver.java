package com.hhly.lyygame.data.net.client;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public abstract class BaseObserver<T> implements Observer<T> {

    @Override
    public void onError(Throwable e) {
        hideDialog();
        if(e instanceof ExceptionHandle.ResponeThrowable){
            onError((ExceptionHandle.ResponeThrowable)e);
        } else {
            onError(new ExceptionHandle.ResponeThrowable(e, ExceptionHandle.ERROR.UNKNOWN));
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        showDialog();
    }

    protected abstract void hideDialog();

    protected abstract void showDialog();

    @Override
    public void onComplete() {
        hideDialog();
    }

    public abstract void onError(ExceptionHandle.ResponeThrowable e);

}
