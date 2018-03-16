package com.webber.myframeapp.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.webber.myframeapp.R;
import com.webber.myframeapp.contact.HomeContact;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Webber on 2018/3/9.
 * Describe : 首页Fragment
 */
public class HomeFragment extends Fragment implements HomeContact.View {

    @BindView(R.id.homepage_recyclerview)
    RecyclerView homepageRecyclerview;
    @BindView(R.id.homepage_swipe_refresh)
    SwipeRefreshLayout homepageSwipeRefresh;
    private Unbinder mUnbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    /**
     * 初始化数据
     */
    private void initData() {
        homepageSwipeRefresh.setColorSchemeResources(R.color.colorSwipRefreshLayout);
        homepageSwipeRefresh.setSize(SwipeRefreshLayout.DEFAULT);
        homepageSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
        //recyclerViewAdapter = new HomePageRecyclerViewAdapter(getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        homepageRecyclerview.setLayoutManager(layoutManager);
        //homepageRecyclerview.setAdapter(recyclerViewAdapter);
        homepageRecyclerview.setItemAnimator(new DefaultItemAnimator());
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    @Override
    public void refreshView() {

    }

    @Override
    public void showToast(String msg) {

    }
}
