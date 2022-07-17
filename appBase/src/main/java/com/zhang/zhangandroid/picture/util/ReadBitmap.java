package com.zhang.zhangandroid.picture.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ReadBitmap {

    public static String DIR = "zhang";

    public void readByte(Context c, String name, int indexInt) {
        byte[] b = null;
        int[] intArrat = c.getResources().getIntArray(indexInt);
        try {
            AssetManager am = null;
            am = c.getAssets();
            InputStream is = am.open(name);
            for (int i = 0; i < intArrat.length; i++) {
                b = new byte[intArrat[i]];
                // 读取数据
                is.read(b);
                saveMyBitmap(Bytes2Bimap(b), DIR + name + i + ".jpg");
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Bitmap Bytes2Bimap(byte[] b) {
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        } else {
            return null;
        }
    }

    /**
     * 简介: 把bitmap存入指定文件
     *  作者: zhangg
     */
    public static boolean saveMyBitmap(Bitmap bmp, String path) {
        File f = new File(path);
        try {
            f.createNewFile();
            FileOutputStream fOut = new FileOutputStream(f);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.flush();
            fOut.close();
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return false;
    }
} 
