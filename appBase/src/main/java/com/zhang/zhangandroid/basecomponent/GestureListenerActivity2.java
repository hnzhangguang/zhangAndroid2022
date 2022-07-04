package com.zhang.zhangandroid.basecomponent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.zhang.zhangandroid.R;


/**
 * 简介: 手势添加,识别 ...
 *
 * 除了上面讲解的手势检测外，Android还运行我们将手势进行添加，然后提供了相关的识别API；
 * Android中使用GestureLibrary来代表手势库，提供了GestureLibraries工具类来创建手势库！
 *
 */
public class GestureListenerActivity2 extends AppCompatActivity {


    private EditText editText;
    private GestureOverlayView gesture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_listener2);

        //获取手势编辑组件后，设置相关参数
        gesture = (GestureOverlayView) findViewById(R.id.gesture);
        gesture.setGestureColor(Color.GREEN);
        gesture.setGestureStrokeWidth(5);

        // 添加手势
        gesture.addOnGesturePerformedListener(new GestureOverlayView.OnGesturePerformedListener() {
            @Override
            public void onGesturePerformed(GestureOverlayView gestureOverlayView, final Gesture gesture) {
                View saveDialog = getLayoutInflater().inflate(R.layout.dialog_save_gesture,null,false);
                ImageView img_show = (ImageView) saveDialog.findViewById(R.id.img_show);
                final EditText edit_name = (EditText) saveDialog.findViewById(R.id.edit_name);
                Bitmap bitmap = gesture.toBitmap(128,128,10,0xffff0000);
                img_show.setImageBitmap(bitmap);
                new AlertDialog.Builder(GestureListenerActivity2.this).setView(saveDialog)
                        .setPositiveButton("保存",new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //获取文件对应的手势库
                                GestureLibrary gestureLib = GestureLibraries.fromFile("/mnt/sdcard/mygestures");
                                gestureLib.addGesture(edit_name.getText().toString(),gesture);
                                gestureLib.save();
                            }
                        }).setNegativeButton("取消", null).show();
            }
        });



    }
}