package com.hhly.lyygame.data.net.client;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * Created by ${HELY} on 17/4/15.
 * 邮箱：heli.lixiong@gmail.com
 */

public abstract class BaseSubscriber<T> implements Subscriber<T> {

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
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
        showDialog();
    }



    protected abstract void hideDialog();

    protected abstract void showDialog();

    @Override
    public void onComplete() {

    }

    public abstract void onError(ExceptionHandle.ResponeThrowable e);
}
