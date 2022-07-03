package com.zhang.zhangandroid.http;

import androidx.annotation.NonNull;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class OkHttpUtils {
    private static final String BASE_URL = "http://192.168.10.104:8080/"; //地址
    private static OkHttpClient client = new OkHttpClient();
    private static JSONObject responseJSONObject;

    /**
     * 因为OkHttp自带cookie效果，在构造方法中进行初始化，使其生效
     */
    public OkHttpUtils() {
        final Map<String, List<Cookie>> cookieStore = new HashMap<>();
        client = new OkHttpClient.Builder().cookieJar(new CookieJar() {
            @Override
            public void saveFromResponse(@NonNull HttpUrl httpUrl, @NonNull List<Cookie> list) {
                cookieStore.put(httpUrl.host(), list);
            }

            @NonNull
            @Override
            public List<Cookie> loadForRequest(@NonNull HttpUrl httpUrl) {
                List<Cookie> cookies = cookieStore.get(httpUrl.host());
                return cookies == null ? new ArrayList<>() : cookies;
            }
        }).build();
    }

    /**
     * get请求，这里没加线程控制，如果有需要可以加一下
     */
    public static JSONObject get(String url) {
        Request request = new Request.Builder().url(getAbsoluteUrl(url)).build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            responseJSONObject = dealResponse(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseJSONObject;
    }

    /**
     * post请求，参数是通过map键值对形式传递过来的
     */
    public static JSONObject post(String url, Map<String, Object> value) {
        FormBody formBody = dealFormBody(value);
        Request request = new Request.Builder().url(getAbsoluteUrl(url)).post(formBody).build();
        Response response;
        try {
            response = client.newCall(request).execute();
            responseJSONObject = dealResponse(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseJSONObject;
    }


    /**
     * 将请求返回结果封装到ResponseBody
     */
    public static JSONObject dealResponse(String result) {
//        ResponseBody responseBody = new ResponseBody();
        JSONObject json = JSONObject.parseObject(result);
//        responseBody.setCode(json.get("code") + "");
//        responseBody.setResult(json.get("data"));
        return json;
    }

    /*
    处理请求参数
    */
    public static FormBody dealFormBody(Map<String, Object> maps) {
        FormBody.Builder builder = new FormBody.Builder();
        Set<String> keySet = maps.keySet();
        for (Iterator<String> iterator = keySet.iterator(); iterator.hasNext(); ) {
            String key = iterator.next();
            builder.add(key, maps.get(key) + "");
        }
        return builder.build();
    }

    /*
    将url拼接起来，封装成完整url
    */
    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}