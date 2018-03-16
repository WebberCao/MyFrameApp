package com.webber.myframeapp.contact;


import com.webber.myframeapp.base.BasePresenter;
import com.webber.myframeapp.base.BaseView;

/**
 * Created by Webber on 2018/3/5.
 * Describe :主页连接类（创建mvp模式的话要创建很多个接口,因此创建此连接类,避免类爆炸）
 */
public class MainContact {

    public interface View extends BaseView {
        void showProgress(String apkUrl, String destinationFilePath);   //展示下载进度
        void showChooseDialog(String newVersionName, String versionInfo, String isForceUpadate, String updateUrl);//展示选择更新对话框
    }

    public interface Presenter extends BasePresenter<View> {
        void getVersion();                    //获取最新版本
        void downloadApk(String url);         //下载最新版本
    }

}
