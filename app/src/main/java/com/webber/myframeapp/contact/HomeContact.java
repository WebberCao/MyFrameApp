package com.webber.myframeapp.contact;

import android.view.View;

import com.webber.myframeapp.base.BasePresenter;
import com.webber.myframeapp.base.BaseView;


/**
 * Created by Webber on 2018/3/12.
 * Describe :首页连接类（创建mvp模式的话要创建很多个接口,因此创建此连接类,避免类爆炸）
 */

public class HomeContact {

    public interface View extends BaseView {
        //刷新页面
        void refreshView();

    }

    public interface Presenter extends BasePresenter<View> {
        //加载首页数据
        void loadData();
    }

}
