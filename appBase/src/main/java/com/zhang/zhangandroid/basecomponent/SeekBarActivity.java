package com.zhang.zhangandroid.basecomponent;

import android.content.Context;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.zhang.zhangandroid.R;
import com.zhang.zhangandroid.base.BaseActivity;

/**
 * 简介: seekbar 的使用
 * 作者: zhangg
 */
public class SeekBarActivity extends BaseActivity {

    private SeekBar sb_normal, seekbar_second;
    private TextView txt_cur;
    private ScrollView scrollView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seekbar);


        bindViews(this);


    }


    private void bindViews(Context mContext) {
        sb_normal = (SeekBar) findViewById(R.id.sb_normal);
        seekbar_second = (SeekBar) findViewById(R.id.seekbar_second);
        txt_cur = (TextView) findViewById(R.id.txt_cur);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        sb_normal.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txt_cur.setText("当前进度值:" + progress + "  / 100 ");
                seekbar_second.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(mContext, "触碰SeekBar", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(mContext, "放开SeekBar", Toast.LENGTH_SHORT).show();
            }
        });


    }


}
