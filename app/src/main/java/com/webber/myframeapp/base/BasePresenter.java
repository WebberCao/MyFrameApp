package com.webber.myframeapp.base;

/**
 * Created by Webber on 2018/3/5.
 * Describe :
 */

public interface BasePresenter<T extends BaseView> {

    void attachView(T view);
    void detachView();

}
