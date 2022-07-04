package com.zhang.zhangandroid.basecomponent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

import com.zhang.zhangandroid.R;


/**
 * 简介: 手势
 *
 * 检测
 *
 *
 */
public class GestureListenerActivity extends AppCompatActivity {


    private MyGestureListener mgListener;
    private GestureDetector mDetector;
    private final static String TAG = "mmmm";
    private final static int MIN_MOVE = 200;   //最小距离

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_listener);


        //实例化 GestureListener 与 GestureDetector 对象
        mgListener = new MyGestureListener();
        mDetector = new GestureDetector(this, mgListener);//GestureDetector: 识别各种手势
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mDetector.onTouchEvent(event);
    }

    //自定义一个GestureListener,这个是View类下的，别写错哦！！！
    //GestureListener 手势交互的监听接口
    private class MyGestureListener implements GestureDetector.OnGestureListener {

        @Override
        public boolean onDown(MotionEvent motionEvent) {
            Log.d(TAG, "onDown:按下");
            return false;
        }

        @Override
        public void onShowPress(MotionEvent motionEvent) {
            Log.d(TAG, "onShowPress:手指按下一段时间,不过还没到长按");
        }

        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            Log.d(TAG, "onSingleTapUp:手指离开屏幕的一瞬间");
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            Log.d(TAG, "onScroll:在触摸屏上滑动");
            return false;
        }

        @Override
        public void onLongPress(MotionEvent motionEvent) {
            Log.d(TAG, "onLongPress:长按并且没有松开");
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float v, float v1) {
            Log.d(TAG, "onFling:迅速滑动，并松开");
            if(e1.getY() - e2.getY() > MIN_MOVE){
                startActivity(new Intent(GestureListenerActivity.this, GestureListenerActivity.class));
                Toast.makeText(GestureListenerActivity.this, "通过手势启动Activity", Toast.LENGTH_SHORT).show();
            }else if(e1.getY() - e2.getY()  < MIN_MOVE){
                finish();
                Toast.makeText(GestureListenerActivity.this,"通过手势关闭Activity",Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    }

    // SimpleOnGestureListener 是OnGestureListener的子类, 只需要实现自己关系的方法即可
    GestureDetector.SimpleOnGestureListener simpleOnGestureListener = new GestureDetector.SimpleOnGestureListener(){
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    };
}