package com.zhang.zhangandroid.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.DrawableRes;

import java.util.ArrayList;
import java.util.List;

/**
 * Android 快捷方式工具类
 * Build.VERSION.SDK_INT >= 25 * API25以上可用
 * setShortLabel 设置短标题
 * setLongLabel  设置长标题
 * setIcon       设置icon
 * setIntent     设置Intent
 * @author renquan
 * @date 2021年12月27日
 *
 *
 *
 * 使用方法:
 *
 * ShortcutUtils.getInstance().addShortcut(this
 *                              , MainActivity2.class
 *                              , liveBundle
 *                              , "live_Id"
 *                              , "看直播"
 *                              , "看直播"
 *                             , R.drawable.live)
 *                     .addShortcut(this
 *                             , MainActivity2.class
 *                             ,vodBundle
 *                             , "vod_Id"
 *                             , "看回放"
 *                             , "看回放"
 *                             , R.drawable.vod)
 *                     .build();
 *
 *
 */
public class ShortcutUtils {
    private static ShortcutUtils shortcutUtils;
    private List<ShortcutInfo> shortcutInfos;
    private Context mContext;

    public static ShortcutUtils getInstance() {
        if (shortcutUtils == null) {
            synchronized (ShortcutUtils.class) {
                if (shortcutUtils == null) {
                    shortcutUtils = new ShortcutUtils();
                }
            }
        }
        return shortcutUtils;
    }

    private ShortcutUtils() {
        shortcutInfos = new ArrayList<>();
    }

    /**
     * 设置Class对象
     * 所有参数不能为空
     * @param context
     * @param cls
     * @param bundle
     * @param shortcutId
     * @param shortLabel
     * @param longLabel
     * @param resId
     * @return
     */
    public ShortcutUtils addShortcut(Context context, Class<?> cls, Bundle bundle, String shortcutId, String shortLabel, String longLabel, @DrawableRes int resId) {
        if (shortcutUtils != null && shortcutInfos != null) {
            if (Build.VERSION.SDK_INT >= 25) {
            	mContext = context;
                Intent intent = new Intent(context, cls);
                intent.putExtra("shortcutArgument", bundle);
                intent.setAction(Intent.ACTION_VIEW);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                ShortcutInfo shortcutInfo = new ShortcutInfo.Builder(context, shortcutId)
                        .setShortLabel(shortLabel)
                        .setLongLabel(longLabel)
                        .setIcon(Icon.createWithResource(context, resId))
                        .setIntent(intent)
                        .build();

                shortcutInfos.add(shortcutInfo);
            }
        }
        return shortcutUtils;
    }


    /**
     * 设置Intent对象
     * 所有参数不能为空
     * @param context
     * @param intent
     * @param shortcutId
     * @param shortLabel
     * @param longLabel
     * @param resId
     * @return
     */
    public ShortcutUtils addShortcut(Context context, Intent intent, String shortcutId, String shortLabel, String longLabel, @DrawableRes int resId) {
        if (Build.VERSION.SDK_INT >= 25) {
            mContext = context;
            if (shortcutUtils != null && shortcutInfos != null) {
                ShortcutInfo shortcutInfo = new ShortcutInfo.Builder(context, shortcutId)
                        .setShortLabel(shortLabel)
                        .setLongLabel(longLabel)
                        .setIcon(Icon.createWithResource(context, resId))
                        .setIntent(intent)
                        .build();

                shortcutInfos.add(shortcutInfo);
            }
        }
        return shortcutUtils;
    }

    /**
     * 构建Shortcuts
     */
    public void build() {
        if (shortcutInfos != null && shortcutInfos.size() > 0 && mContext != null) {
            ShortcutManager systemService = mContext.getSystemService(ShortcutManager.class);
            systemService.setDynamicShortcuts(shortcutInfos);
        }
    }

}

