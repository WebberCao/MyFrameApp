package com.webber.myframeapp.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.webber.myframeapp.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Webber on 2018/3/9.
 * Describe : 首页Fragment
 */
public class MyFragment extends Fragment {

    private Unbinder mUnbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_my,container,false);
        mUnbinder = ButterKnife.bind(this,view);
        initData();
        return view;
    }

    /**
     * 初始化数据
     */
    private void initData() {
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
