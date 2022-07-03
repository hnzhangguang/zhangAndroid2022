package com.zhang.zhangandroid.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;

import java.util.Collections;
import java.util.List;

public class PackageUtil {


    /**
     * 简介: 根据包名启动应用
     *  作者: zhangg
     */
    public static void startApp(Context context,String packageName){
        PackageManager packageManager = context.getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage(packageName);
        context.startActivity(intent);
    }


    /**
     * @param context     上下文
     * @param packageName 包名
     */
    public void skipOtherApp(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage(packageName);
        context.startActivity(intent);
    }


    /**
     * 简介: 跳转三方应用 + 指定页面（Activity）
     */
    public static void startOtherAppToPage(Context context, String packageName,String pageName){
        Intent intent = new Intent();
        //这里跳转的是淘宝的启动页
//        ComponentName comp = new ComponentName("com.taobao.taobao", "com.taobao.tao.welcome.Welcome");
        ComponentName comp = new ComponentName(packageName, pageName);
        intent.setComponent(comp);
        //为三方的activity新开任务栈
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    /**
     * 通过PackageManager获取手机内所有app的包名和启动页（首个启动Activity的类名）
     * 可根据自己业务需求封装方法的返回体，可以是单app信息，也可以是appList
     */
    public void getAllApp(Context context) {
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> appsInfo = packageManager.queryIntentActivities(intent, 0);
        Collections.sort(appsInfo, new ResolveInfo.DisplayNameComparator(packageManager));
        for (ResolveInfo info : appsInfo) {
            String pkg = info.activityInfo.packageName;
            String cls = info.activityInfo.name;
            Log.e("app_info", "pkg:" + pkg + " —— cls:" + cls);
        }
    }


    /**
     * 获取手机app列表
     */
    public List<ResolveInfo> getAllAppList(Context context) {
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> appsInfo = packageManager.queryIntentActivities(intent, 0);
        Collections.sort(appsInfo, new ResolveInfo.DisplayNameComparator(packageManager));
        return appsInfo;
    }





}
