package com.webber.myframeapp.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


import com.webber.myframeapp.application.MyApplication;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Webber on 2018/3/5.
 * Describe : Activity基类
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {

    protected Activity mContent;
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContent = this;
        createPresenter();
        if(mPresenter!=null){
            mPresenter.attachView(this);
        }
        MyApplication.getInstance().addActivity(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter!=null){
            mPresenter.detachView();
        }
        MyApplication.getInstance().removeActivity(this);
    }

    protected abstract void createPresenter();
}
