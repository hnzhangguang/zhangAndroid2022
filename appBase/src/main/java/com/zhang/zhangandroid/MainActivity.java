package com.zhang.zhangandroid;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.zhang.zhangandroid.base.BaseActivity;
import com.zhang.zhangandroid.base.MyListViewSimpleAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);


        List<String> list = new ArrayList<>();
        list.add("baseComponent");
        list.add("ComplexComponentActivity");
        list.add("AnimationActivity");
        list.add("PersistenceDataActivity");
        list.add("LayoutActivity");

        MyListViewSimpleAdapter adapter = new MyListViewSimpleAdapter(list,this);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object item = adapterView.getAdapter().getItem(i);//"baseComponent"
                String junmString = "com.zhang.zhangandroid.basecomponent.BaseComponentActivity";

                if (item.equals("baseComponent")){
                    junmString = "com.zhang.zhangandroid.basecomponent.BaseComponentActivity";
                }

                if (item.equals("ComplexComponentActivity")){
                    junmString = "com.zhang.zhangandroid.complexcomponent.ComplexComponentActivity";
                }
                if (item.equals("AnimationActivity")){
                    junmString = "com.zhang.zhangandroid.animation.AnimationActivity";
                }
                if (item.equals("PersistenceDataActivity")){
                    junmString = "com.zhang.zhangandroid.persistence.PersistenceDataActivity";
                }
                if (item.equals("LayoutActivity")){
                    junmString = "com.zhang.zhangandroid.layout.LayoutActivity";
                }


                // 打开activity
               openActivity(junmString);



            }
        });

    }



}