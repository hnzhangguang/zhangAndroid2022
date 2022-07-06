package com.zhang.zhangandroid.util;

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
            // 得到压缩后的数据
            byte gdata[] = bout.toByteArray();
            return new String(gdata);
        } catch (IOException e) {
            AppUtil.logZhang(e);
            e.printStackTrace();
        }
        return "";
    }

}
