package com.zhang.zhangandroid;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.zhang.zhangandroid.util.ActivityCollector;
import com.zhang.zhangandroid.util.AppUtil;

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



    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            ActivityCollector.finishAll();
            ActivityManager activityMgr = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception ignored) {
            AppUtil.logZhang(ignored);
        }
    }



}
