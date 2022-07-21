package com.zhang.zhangandroid.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.zhang.zhangandroid.R;


/**
 * 简介: 实现流程分析：
 *
 * 首先你要弄清楚，这个verts数组存储的是什么？比如 verts[0]和verts1，
 * 这两个相邻的元素其实表示的就是我们第一个点的x坐标和y坐标！
 * 知道这一点，你就知道为什么有21 * 21个点，以及为什么数组长度等于这个值 * 2！ 初始化部分也就懂了！
 *
 * 接着我们再来看看根据触摸事件计算verts数组元素的值的实现：
 * 获得触摸点的x,y坐标，拿这个值去减对应点的x,y只，计算出触摸点和每个坐标点的距离
 * 然后计算所谓的扭曲度：80000 / ((float) (dd * d))；扭曲度 >= 1的，直接让该坐标 点指向这个触摸点，< 1的，
 * 则让各个顶点向触摸点发生偏移，然后再调用invalidate()重绘~ 大概就这样~多思考思考，如果还是不理解就算了~知道有这东西就好！
 */
public class BitmapMeshView extends View {

    //将水平和竖直方向上都划分为20格
    private final int WIDTH = 20;
    private final int HEIGHT = 20;
    private final int COUNT = (WIDTH + 1) * (HEIGHT + 1);  //记录该图片包含21*21个点
    private final float[] verts = new float[COUNT * 2];    //扭曲前21*21个点的坐标
    private final float[] orig = new float[COUNT * 2];    //扭曲后21*21个点的坐标
    private Bitmap mBitmap;
    private float bH,bW;


    public BitmapMeshView(Context context) {
        this(context, null);
    }

    public BitmapMeshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BitmapMeshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.meizi);
        bH = mBitmap.getWidth();
        bW = mBitmap.getHeight();
        int index = 0;
        //初始化orig和verts数组。
        for (int y = 0; y <= HEIGHT; y++)
        {
            float fy = bH * y / HEIGHT;
            for (int x = 0; x <= WIDTH; x++)
            {
                float fx = bW * x / WIDTH;
                orig[index * 2 + 0] = verts[index * 2 + 0] = fx;
                orig[index * 2 + 1] = verts[index * 2 + 1] = fy;
                index += 1;
            }
        }
        //设置背景色
        setBackgroundColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmapMesh(mBitmap, WIDTH, HEIGHT, verts
                , 0, null, 0, null);
    }

    //工具方法，用于根据触摸事件的位置计算verts数组里各元素的值
    private void warp(float cx, float cy)
    {
        for (int i = 0; i < COUNT * 2; i += 2)
        {
            float dx = cx - orig[i + 0];
            float dy = cy - orig[i + 1];
            float dd = dx * dx + dy * dy;
            //计算每个座标点与当前点（cx、cy）之间的距离
            float d = (float)Math.sqrt(dd);
            //计算扭曲度，距离当前点（cx、cy）越远，扭曲度越小
            float pull = 80000 / ((float) (dd * d));
            //对verts数组（保存bitmap上21 * 21个点经过扭曲后的座标）重新赋值
            if (pull >= 1)
            {
                verts[i + 0] = cx;
                verts[i + 1] = cy;
            }
            else
            {
                //控制各顶点向触摸事件发生点偏移
                verts[i + 0] = orig[i + 0] + dx * pull;
                verts[i + 1] = orig[i + 1] + dy * pull;
            }
        }
        //通知View组件重绘
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        //调用warp方法根据触摸屏事件的座标点来扭曲verts数组
        warp(event.getX(), event.getY());
        return true;
    }

}