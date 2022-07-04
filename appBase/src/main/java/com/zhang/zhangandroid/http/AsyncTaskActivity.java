package com.zhang.zhangandroid.http;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zhang.zhangandroid.R;

public class AsyncTaskActivity extends AppCompatActivity {


    private TextView txttitle;
    private ProgressBar pgbar;
    private Button btnupdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);


        txttitle = (TextView)findViewById(R.id.txttitle);
        pgbar = (ProgressBar)findViewById(R.id.pgbar);
        btnupdate = (Button)findViewById(R.id.btnupdate);

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAsyncTask myTask = new MyAsyncTask(txttitle,pgbar);
                myTask.execute(1000);
            }
        });

    }



}


class DelayOperator {
    //延时操作,用来模拟下载
    public void delay()
    {
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();;
        }
    }
}
