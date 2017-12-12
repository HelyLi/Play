package com.hhly.lyygame.presentation.view;


import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * Created by Simon on 2016/11/18.
 */

public interface BaseView<T> {

    void setPresenter(T presenter);

    boolean isActive();

    <T> LifecycleTransformer<T> bindToLife();
}
