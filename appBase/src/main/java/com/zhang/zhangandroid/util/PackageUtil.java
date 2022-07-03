package com.zhang.zhangandroid.util;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Environment;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import android.view.WindowManager;

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



    /**
     * 获得状态栏的高度
     */
    public static int getStatusHeight(Context context) {
        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height =
                    Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    /**
     * 根据屏幕宽度与密度计算GridView显示的列数， 最少为三列，并获取Item宽度
     */
    public static int getImageItemWidth(Activity activity) {
        int screenWidth = activity.getResources().getDisplayMetrics().widthPixels;
        int densityDpi = activity.getResources().getDisplayMetrics().densityDpi;
        int cols = screenWidth / densityDpi;
        cols = cols < 3 ? 3 : cols;
        int columnSpace = (int) (2 * activity.getResources().getDisplayMetrics().density);
        return (screenWidth - columnSpace * (cols - 1)) / cols;
    }

    /**
     * 判断SDCard是否可用
     */
    public static boolean existSDCard() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取手机大小（分辨率）
     */
    public static DisplayMetrics getScreenPix(Activity activity) {
        DisplayMetrics displaysMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displaysMetrics);
        return displaysMetrics;
    }

    /**
     * dp转px
     */
    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal,
                context.getResources().getDisplayMetrics());
    }

    /**
     * 判断手机是否含有虚拟按键  99%
     */
    public static boolean hasVirtualNavigationBar(Context context) {
        boolean hasSoftwareKeys = true;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Display d =
                    ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

            DisplayMetrics realDisplayMetrics = new DisplayMetrics();
            d.getRealMetrics(realDisplayMetrics);

            int realHeight = realDisplayMetrics.heightPixels;
            int realWidth = realDisplayMetrics.widthPixels;

            DisplayMetrics displayMetrics = new DisplayMetrics();
            d.getMetrics(displayMetrics);

            int displayHeight = displayMetrics.heightPixels;
            int displayWidth = displayMetrics.widthPixels;

            hasSoftwareKeys = (realWidth - displayWidth) > 0 || (realHeight - displayHeight) > 0;
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            boolean hasMenuKey = ViewConfiguration.get(context).hasPermanentMenuKey();
            boolean hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
            hasSoftwareKeys = !hasMenuKey && !hasBackKey;
        }

        return hasSoftwareKeys;
    }

    /**
     * 获取导航栏高度，有些没有虚拟导航栏的手机也能获取到，建议先判断是否有虚拟按键
     */
    public static int getNavigationBarHeight(Context context) {
        int resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen",
                "android");
        return resourceId > 0 ? context.getResources().getDimensionPixelSize(resourceId) : 0;
    }





}
