package com.zhang.zhangandroid.util;

import android.text.TextUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

/**
 * 简介: 字符串工具类
 */
public class StringUtil {

    /**
     * 简介: 压缩string 字符串
     */
    public static String zipString(String data){
        // 对数据进行压缩:
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        try {
            GZIPOutputStream gout = new GZIPOutputStream(bout);
            gout.write(data.getBytes());
            gout.close();
            // 得到压缩后的数据
            byte gdata[] = bout.toByteArray();
            return new String(gdata);
        } catch (IOException e) {
            AppUtil.logZhang(e);
            e.printStackTrace();
        }
        return "";
    }


    // 判空处理
    public static boolean isNull(String dataString){
        if (TextUtils.isEmpty(dataString)){
            return true;
        }
        return false;
    }


}
