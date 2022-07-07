package com.zhang.zhangandroid.picture;

import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.graphics.drawable.shapes.OvalShape;
import android.widget.TextView;

public class DrawableTest {


    TextView txtShow;

    public void drawable(){

        //
        ColorDrawable drawable = new ColorDrawable(0xffff2200);
        txtShow.setBackground(drawable);


        // 画圆
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        shapeDrawable.getPaint().setColor(Color.BLACK);
        Rect rect = new Rect();
        rect.top = 0;
        rect.left = 0;
        rect.bottom = 50;
        rect.right = 50;
        shapeDrawable.setBounds(rect);
        txtShow.setBackground(shapeDrawable);


        // 画半圆
        shapeDrawable = new ShapeDrawable(new ArcShape(0,180));//ArcShape参数 开始角度startAngle 要画多少角度sweepAngle
        shapeDrawable.getPaint().setColor(Color.BLACK);
        rect = new Rect();
        rect.top = 0;
        rect.left = 0;
        rect.bottom = 50;
        rect.right = 50;
        shapeDrawable.setBounds(rect);
        txtShow.setBackground(shapeDrawable);



    }



}
