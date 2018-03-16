package com.webber.myframeapp.presenter;


import com.webber.myframeapp.contact.MainContact;

/**
 * Created by Webber on 2018/3/5.
 * Describe : Presenter的实现类，完成业务逻辑的处理
 */
public class MainPresenter implements MainContact.Presenter {

    private MainContact.View mView;

    @Override
    public void getVersion() {

    }

    @Override
    public void downloadApk(String url) {

    }

    @Override
    public void attachView(MainContact.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
