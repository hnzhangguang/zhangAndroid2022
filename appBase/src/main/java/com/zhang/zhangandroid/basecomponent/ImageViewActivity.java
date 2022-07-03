package com.zhang.zhangandroid.basecomponent;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import com.zhang.zhangandroid.R;
import com.zhang.zhangandroid.base.BaseActivity;
import com.zhang.zhangandroid.util.PackageUtil;


/**
 * 简介:
 * 常用属性:
 */
public class ImageViewActivity extends BaseActivity {


    private Button addAlpha;
    private Button downAlpha;
    private Button next;
    private ImageView imageView1;
    int[] images = new int[]{ //用数组存储春，夏，秋，冬四张图片
            R.drawable.spring,
            R.drawable.summer,
            R.drawable.autumor,
            R.drawable.winter
    };
    int currentImage = 0; //定义当前默认显示的图片
    int alpha = 255;//定义图片起始透明度

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        addAlpha = (Button) findViewById(R.id.addAlpha);
        downAlpha = (Button) findViewById(R.id.downAlpha);
        next = (Button) findViewById(R.id.next);
        imageView1 = (ImageView) findViewById(R.id.imageView1);


        //对增加图片透明度按钮设置监听事件
        addAlpha.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                if (alpha >= 255) { //255是透明度上线
                    alpha = 255;
                } else { //每点击增加透明度按钮，透明度增加20
                    alpha += 20;
                }
                imageView1.setImageAlpha(alpha); //为图片设置透明度

                PackageUtil.createShortCut(ImageViewActivity.this);
            }
        });

        //对减少透明度按钮设置监听事件
        downAlpha.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                if (alpha <= 0) { //透明度下限
                    alpha = 0;
                } else {
                    alpha -= 20;
                }
                imageView1.setImageAlpha(alpha); //为图片设置透明度
            }
        });

        //对切换图片按钮设置监听事件
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //控制显示下一张图片
                imageView1.setImageResource(images[++currentImage % images.length]);
            }
        });

    }


}