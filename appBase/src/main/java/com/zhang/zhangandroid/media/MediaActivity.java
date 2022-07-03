package com.zhang.zhangandroid.media;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;

import com.zhang.zhangandroid.R;

import java.io.PushbackReader;


/**
 * 简介: MediaRecorder 录制视频
 *      MediaPlayer 播放视频
 *      SoundPool 播放音效
 *
 *
 *
 */
public class MediaActivity extends AppCompatActivity {


    private int requestCode  = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);
    }


    // 申请权限
    public void requestPermission(){
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA,Manifest.permission.RECORD_AUDIO},requestCode);
    }
}