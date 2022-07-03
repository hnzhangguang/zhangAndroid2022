package com.zhang.zhangandroid.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.zhang.zhangandroid.R;

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



    /**
     * 简介: 创建桌面快捷方式
     * 功能: 1,
     *       2,
     *  作者: zhangg
     */
    public static void createShortCut(AppCompatActivity activity){
        Intent addShortCut;
        //判断是否需要添加快捷方式
//        if(activity.getIntent().getAction().equals(Intent.ACTION_CREATE_SHORTCUT)){
        if(true){
            addShortCut = new Intent();
            //快捷方式的名称
            addShortCut.putExtra(Intent.EXTRA_SHORTCUT_NAME , "我的快捷方式");
            //显示的图片
            Parcelable icon = Intent.ShortcutIconResource.fromContext(activity, R.mipmap.default_select_photo);
            addShortCut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
            //快捷方式激活的activity，需要执行的intent，自己定义
            addShortCut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent());
            //OK，生成
            activity.setResult(AppCompatActivity.RESULT_OK, addShortCut);
        }else{
            //取消
            activity.setResult(AppCompatActivity.RESULT_CANCELED);
        }
    }



    /**
     * 简介: 获取系统信息
     *  作者: zhangg
     */
    public void getConfiguration(Context context){
        //①获取系统的Configuration对象
        Configuration cfg = context.getResources().getConfiguration();
        //②想查什么查什么
        StringBuffer buffer = new StringBuffer();
        buffer.append("densityDpi:" + cfg.densityDpi + "\n");
        buffer.append("fontScale:" + cfg.fontScale + "\n");
        buffer.append("hardKeyboardHidden:" + cfg.hardKeyboardHidden + "\n");
        buffer.append("keyboard:" + cfg.keyboard + "\n");
        buffer.append("keyboardHidden:" + cfg.keyboardHidden + "\n");
        buffer.append("locale:" + cfg.locale + "\n");
        buffer.append("mcc:" + cfg.mcc + "\n");
        buffer.append("mnc:" + cfg.mnc + "\n");
        buffer.append("navigation:" + cfg.navigation + "\n");
        buffer.append("navigationHidden:" + cfg.navigationHidden + "\n");
        buffer.append("orientation:" + cfg.orientation + "\n");
        buffer.append("screenHeightDp:" + cfg.screenHeightDp + "\n");
        buffer.append("screenWidthDp:" + cfg.screenWidthDp + "\n");
        buffer.append("screenLayout:" + cfg.screenLayout + "\n");
        buffer.append("smallestScreenWidthDp:" + cfg.densityDpi + "\n");
        buffer.append("touchscreen:" + cfg.densityDpi + "\n");
        buffer.append("uiMode:" + cfg.densityDpi + "\n");
    }




}
