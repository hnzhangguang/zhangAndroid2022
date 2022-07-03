package com.zhang.zhangandroid;

import android.app.Application;

public class App extends Application {

    private static Application mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication =this;
    }

    public static Application getApplication(){
        return mApplication ;
    }
}
