package com.zhang.zhangandroid.js;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.zhang.zhangandroid.R;

public class JsBridgeActivity extends AppCompatActivity {


    private WebView wView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js_brige);

        wView = (WebView) findViewById(R.id.wView);
        wView.loadUrl("file:///android_asset/demo1.html");
        WebSettings webSettings = wView.getSettings();
        //①设置WebView允许调用js
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultTextEncodingName("UTF-8");
        //②将object对象暴露给Js,调用addjavascriptInterface
        wView.addJavascriptInterface(new MyObject(JsBridgeActivity.this), "myObj");


        //设置WebChromeClient,处理网页中的各种js事件
//        wView.setWebChromeClient(new MyWebChromeClient());


        // 专门用于异步调用JavaScript方法，并且能够得到一个回调结果。
//        wView.evaluateJavascript(script, new ValueCallback<String>() {
//            @Override
//            public void onReceiveValue(String value) {
//                //TODO
//            }
//        });



    }

    class MyObject {
        private Context context;
        public MyObject(Context context) {
            this.context = context;
        }

        //将显示Toast和对话框的方法暴露给JS脚本调用
        @JavascriptInterface
        public void showToast(String name) {
            Toast.makeText(context, name, Toast.LENGTH_SHORT).show();
        }

        @JavascriptInterface
        public void showDialog() {
            new AlertDialog.Builder(context)
                    .setTitle("联系人列表").setIcon(R.mipmap.ic_launcher)
                    .setItems(new String[]{"基神", "B神", "曹神", "街神", "翔神"}, null)
                    .setPositiveButton("确定", null).create().show();
        }

        @JavascriptInterface
        public void contactlist() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("contactlist()方法执行了！");
                        String json = "{ \"name\":\"菜鸟教程\" , \"url\":\"www.runoob.com\" }"; // json串
                        wView.loadUrl("javascript:alertFun('" + json + "')");
                    } catch (Exception e) {
                        System.out.println("设置数据失败" + e);
                    }
                }
            });
        }


    }


}