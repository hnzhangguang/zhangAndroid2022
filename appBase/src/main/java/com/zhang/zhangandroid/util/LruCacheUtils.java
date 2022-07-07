package com.zhang.zhangandroid.util;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import androidx.collection.LruCache;

import com.zhang.zhangandroid.App;

public class LruCacheUtils {


    private static final String TAG = "mmmm";
    ActivityManager activityManager;

    private LruCache<String, Bitmap> mMemoryCache;

    private LruCacheUtils() {
        if (mMemoryCache == null) {
            activityManager = (ActivityManager) App.getApplication().getSystemService(Context.ACTIVITY_SERVICE);
            int memoryClass = activityManager.getMemoryClass();

            Log.e("HEHE", "最大内存：" + activityManager.getMemoryClass());
            mMemoryCache = new LruCache<String, Bitmap>(
                    30) {
                @Override
                protected int sizeOf(String key, Bitmap bitmap) {
                    // 重写此方法来衡量每张图片的大小，默认返回图片数量。
                    return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
                }

                @Override
                protected void entryRemoved(boolean evicted, String key,
                                            Bitmap oldValue, Bitmap newValue) {
                    Log.v("tag", "hard cache is full , push to soft cache");

                }
            };
        }

    }

    public void clearCache() {
        if (mMemoryCache != null) {
            if (mMemoryCache.size() > 0) {
                Log.d("CacheUtils",
                        "mMemoryCache.size() " + mMemoryCache.size());
                mMemoryCache.evictAll();
                Log.d("CacheUtils", "mMemoryCache.size()" + mMemoryCache.size());
            }
            mMemoryCache = null;
        }
    }

    public synchronized void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (mMemoryCache.get(key) == null) {
            if (key != null && bitmap != null)
                mMemoryCache.put(key, bitmap);
        } else
            Log.w(TAG, "the res is aready exits");
    }

    public synchronized Bitmap getBitmapFromMemCache(String key) {
        Bitmap bm = mMemoryCache.get(key);
        if (key != null) {
            return bm;
        }
        return null;
    }

    /**
     * 移除缓存
     *
     * @param key
     */
    public synchronized void removeImageCache(String key) {
        if (key != null) {
            if (mMemoryCache != null) {
                Bitmap bm = mMemoryCache.remove(key);
                if (bm != null)
                    bm.recycle();
            }
        }
    }

}
