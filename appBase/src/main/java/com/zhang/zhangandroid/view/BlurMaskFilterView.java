package com.zhang.zhangandroid.view;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


/**
 * 简介: setMaskFilter(MaskFilter maskfilter)：
 * 设置MaskFilter，可以用不同的MaskFilter实现滤镜的效果，
 * 如滤化，立体等！ 而我们一般不会直接去用这个MaskFilter，而是使用它的两个子类：
 *
 * BlurMaskFilter：指定了一个模糊的样式和半径来处理Paint的边缘。
 *
 * EmbossMaskFilter：指定了光源的方向和环境光强度来添加浮雕效果。
 *
 * 我们可以发现，我们使用这个BlurMaskFilter，无非是， 在构造方法中实例化：
 *
 * BlurMaskFilter(10f,BlurMaskFilter.Blur.NORMAL);
 *
 * 我们可以控制的就是这两个参数：
 *
 * 第一个参数：指定模糊边缘的半径；
 *
 * 第二个参数：指定模糊的风格，可选值有：
 *
 * BlurMaskFilter.Blur.NORMAL：内外模糊
 * BlurMaskFilter.Blur.OUTER：外部模糊
 * BlurMaskFilter.Blur.INNER：内部模糊
 * BlurMaskFilter.Blur.SOLID：内部加粗，外部模糊
 *
 *
 *
 * 在使用MaskFilter的时候要注意，当我们的targetSdkVersion >= 14的时候，MaskFilter 就不会起效果了，
 * 这是因为Android在API 14以上版本都是默认开启硬件加速的，这样充分 利用GPU的特性，使得绘画更加平滑，但是会多消耗一些内存！
 * 好吧，我们把硬件加速关了 就好，可以在不同级别下打开或者关闭硬件加速，一般是关闭~
 *
 *
 */
public class BlurMaskFilterView extends View {

    public BlurMaskFilterView(Context context) {
        super(context);
    }

    public BlurMaskFilterView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BlurMaskFilterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        BlurMaskFilter bmf = null;
        Paint paint=new Paint();
        paint.setAntiAlias(true);          //抗锯齿
        paint.setColor(Color.RED);//画笔颜色
        paint.setStyle(Paint.Style.FILL);  //画笔风格
        paint.setTextSize(68);             //绘制文字大小，单位px
        paint.setStrokeWidth(5);           //画笔粗细

        bmf = new BlurMaskFilter(10f,BlurMaskFilter.Blur.NORMAL);
        paint.setMaskFilter(bmf);
        canvas.drawText("最喜欢看曹神日狗了~", 100, 100, paint);
        bmf = new BlurMaskFilter(10f,BlurMaskFilter.Blur.OUTER);
        paint.setMaskFilter(bmf);
        canvas.drawText("最喜欢看曹神日狗了~", 100, 200, paint);
        bmf = new BlurMaskFilter(10f,BlurMaskFilter.Blur.INNER);
        paint.setMaskFilter(bmf);
        canvas.drawText("最喜欢看曹神日狗了~", 100, 300, paint);
        bmf = new BlurMaskFilter(10f,BlurMaskFilter.Blur.SOLID);
        paint.setMaskFilter(bmf);
        canvas.drawText("最喜欢看曹神日狗了~", 100, 400, paint);
        
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);     //关闭硬件加速
    }
}