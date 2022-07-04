package com.zhang.zhangandroid.basecomponent;

import android.content.Context;
import android.content.DialogInterface;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.zhang.zhangandroid.R;

import java.util.ArrayList;


/**
 * 简介: 识别 ...
 * <p>
 * 除了上面讲解的手势检测外，Android还运行我们将手势进行添加，然后提供了相关的识别API；
 * Android中使用GestureLibrary来代表手势库，提供了GestureLibraries工具类来创建手势库！
 */
public class GestureListenerActivity3 extends AppCompatActivity {


    private GestureOverlayView gesture;
    private GestureLibrary gestureLibrary;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_listener2);


        mContext = GestureListenerActivity3.this;
        gestureLibrary = GestureLibraries.fromFile("mmt/sdcard/mygestures");
        if (gestureLibrary.load()) {
            Toast.makeText(mContext, "手势库加载成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "手势库加载失败", Toast.LENGTH_SHORT).show();
        }

        //获取手势编辑组件后，设置相关参数
        gesture = (GestureOverlayView) findViewById(R.id.gesture);
        gesture.setGestureColor(Color.GREEN);
        gesture.setGestureStrokeWidth(5);
        gesture.addOnGesturePerformedListener(new GestureOverlayView.OnGesturePerformedListener() {
            @Override
            public void onGesturePerformed(GestureOverlayView gestureOverlayView, final Gesture gesture) {
                //识别用户刚绘制的手势
                ArrayList<Prediction> predictions = gestureLibrary.recognize(gesture);
                ArrayList<String> result = new ArrayList<String>();
                //遍历所有找到的Prediction对象
                for (Prediction pred : predictions) {
                    if (pred.score > 2.0) {
                        result.add("与手势【" + pred.name + "】相似度为" + pred.score);
                    }
                }
                if (result.size() > 0) {
                    ArrayAdapter<Object> adapter = new ArrayAdapter<Object>(mContext,
                            android.R.layout.simple_dropdown_item_1line, result.toArray());
                    new AlertDialog.Builder(mContext).setAdapter(adapter, null).setPositiveButton("确定", null).show();
                } else {
                    Toast.makeText(mContext, "无法找到匹配的手势！", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}