package com.zhang.zhangandroid.basecomponent;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import com.zhang.zhangandroid.R;
import com.zhang.zhangandroid.base.BaseActivity;
import com.zhang.zhangandroid.util.PackageUtil;
import com.zhang.zhangandroid.view.MatrixView;
import com.zhang.zhangandroid.view.RoundImageView;


/**
 * 简介:
 * 常用属性:
 */
public class ImageViewActivity extends BaseActivity implements View.OnClickListener {


    private Button addAlpha;
    private Button downAlpha;
    private Button next;
    private ImageView imageView1,imageView_transition;
    int[] images = new int[]{ //用数组存储春，夏，秋，冬四张图片
            R.drawable.spring,
            R.drawable.summer,
            R.drawable.autumor,
            R.drawable.winter
    };
    int currentImage = 0; //定义当前默认显示的图片
    int alpha = 255;//定义图片起始透明度

    private Button btn_reset;
    private Button btn_left;
    private Button btn_right;
    private Button btn_zoomin;
    private Button btn_zoomout;
    private MatrixView matrixView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        addAlpha = (Button) findViewById(R.id.addAlpha);
        downAlpha = (Button) findViewById(R.id.downAlpha);
        next = (Button) findViewById(R.id.next);
        imageView1 = (ImageView) findViewById(R.id.imageView1);
        imageView_transition = (ImageView) findViewById(R.id.imageView_transition);


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


// 发送邮件
//                Intent intent=new Intent(Intent.ACTION_SEND);
//                String[] tos = {"1@abc.com", "2@abc.com"}; // 收件人
//                String[] ccs = {"3@abc.com", "4@abc.com"}; // 抄送
//                String[] bccs = {"5@abc.com", "6@abc.com"}; // 密送
//                intent.putExtra(Intent.EXTRA_EMAIL, tos);
//                intent.putExtra(Intent.EXTRA_CC, ccs);
//                intent.putExtra(Intent.EXTRA_BCC, bccs);
//                intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
//                intent.putExtra(Intent.EXTRA_TEXT, "Hello");
//                intent.setType("message/rfc822");
//                startActivity(intent);


                // 地图
//                Uri uri = Uri.parse("geo:39.9,116.3");
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                startActivity(intent);






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



//        TransitionDrawable 的使用
        TransitionDrawable td = (TransitionDrawable) imageView_transition.getDrawable();
        td.startTransition(3000);



        RoundImageView img_round = (RoundImageView) findViewById(R.id.img_round);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.meizi);
        img_round.setBitmap(bitmap);



        btn_reset = (Button) findViewById(R.id.btn_reset);
        btn_left = (Button) findViewById(R.id.btn_left);
        btn_right = (Button) findViewById(R.id.btn_right);
        btn_zoomin = (Button) findViewById(R.id.btn_zoomin);
        btn_zoomout = (Button) findViewById(R.id.btn_zoomout);
        matrixView = (MatrixView) findViewById(R.id.myView);


        btn_reset.setOnClickListener(this);
        btn_left.setOnClickListener(this);
        btn_right.setOnClickListener(this);
        btn_zoomin.setOnClickListener(this);
        btn_zoomout.setOnClickListener(this);




    }


    //当Activity得到或者失去焦点的时候，就会回调该方法！
    // 如果我们想监控Activity是否加载完毕，就可以用到这个方法了
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_reset:
                matrixView.setMethod(0);
                break;
            case R.id.btn_left:
                matrixView.setMethod(1);
                break;
            case R.id.btn_right:
                matrixView.setMethod(2);
                break;
            case R.id.btn_zoomin:
                matrixView.setMethod(3);
                break;
            case R.id.btn_zoomout:
                matrixView.setMethod(4);
                break;
        }
    }
}