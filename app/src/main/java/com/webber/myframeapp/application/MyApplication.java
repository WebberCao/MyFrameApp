package com.webber.myframeapp.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.util.LinkedList;

/**
 * Created by Webber on 2018/3/5.
 * Describe : Application
 */
public class MyApplication extends Application {

    public static MyApplication instance;
    private LinkedList<Activity> activities;    //频繁的插入删除操作，LinkedList比ArrayList效率高

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static MyApplication getInstance() {
        return instance;
    }

    /**
     * 添加Activity
     * @param activity
     */
    public void addActivity(Activity activity){
        if(activities==null){
            activities = new LinkedList<>();
        }
        activities.add(activity);
    }

    /**
     * 移出Activity
     * @param activity
     */
    public void removeActivity(Activity activity){
        if(activities!=null){
            activities.remove(activity);
        }
    }

    /**
     * 退出APP
     */
    public void exitApp(){
        if(activities!=null){
            synchronized (activities){
                for (Activity activity : activities){
                    activity.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

}
