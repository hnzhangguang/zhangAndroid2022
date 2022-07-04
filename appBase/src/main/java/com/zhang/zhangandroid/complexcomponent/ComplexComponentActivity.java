package com.zhang.zhangandroid.complexcomponent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zhang.zhangandroid.R;
import com.zhang.zhangandroid.base.BaseActivity;
import com.zhang.zhangandroid.base.MyListViewSimpleAdapter;

import java.util.ArrayList;
import java.util.List;

public class ComplexComponentActivity extends BaseActivity {


    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complex_component);

        listView = findViewById(R.id.listView_complex);


        List<String> list = new ArrayList<>();
        list.add("ViewPagerActivity");
        list.add("RecycleViewActivity");

        MyListViewSimpleAdapter adapter = new MyListViewSimpleAdapter(list,this);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object item = adapterView.getAdapter().getItem(i);//"baseComponent"
                String junmString = "com.zhang.zhangandroid.complexcomponent.ViewPagerActivity";

                if (item.equals("ViewPagerActivity")){
                    junmString = "com.zhang.zhangandroid.complexcomponent.ViewPagerActivity";
                }
                if (item.equals("RecycleViewActivity")){
                    junmString = "com.zhang.zhangandroid.complexcomponent.RecycleViewActivity";
                }





                // 打开activity
                openActivity(junmString);



            }
        });


    }
}