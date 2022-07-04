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
        list.add("ContentProviderActivity");
        list.add("AnimationActivity");
        list.add("PersistenceDataActivity");
        list.add("LayoutActivity");
        list.add("FragmentActivity");
        list.add("BroadcastReceiverActivity");
        list.add("DefineViewActivity");
        list.add("RxjavaActivity");
        list.add("OnTouchEventActivity");
        list.add("FragmentTabActivity");
        list.add("WeixinFirstPageActivity");
        list.add("PopWindowActivity");
        list.add("AsyncTaskActivity");
        list.add("GestureListenerActivity");
        list.add("ServiceBindeActivity");

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
                if (item.equals("FragmentActivity")){
                    junmString = "com.zhang.zhangandroid.fragment.FragmentActivity2";
                }
                if (item.equals("BroadcastReceiverActivity")){
                    junmString = "com.zhang.zhangandroid.receiver.BroadcastReceiverActivity";
                }
                if (item.equals("RxjavaActivity")){
                    junmString = "com.zhang.zhangandroid.rxjava.RxjavaActivity";
                }
                if (item.equals("ContentProviderActivity")){
                    junmString = "com.zhang.zhangandroid.provider.ContentProviderActivity";
                }
                if (item.equals("DefineViewActivity")){
                    junmString = "com.zhang.zhangandroid.view.DefineViewActivity";
                }
                if (item.equals("OnTouchEventActivity")){
                    junmString = "com.zhang.zhangandroid.basecomponent.OnTouchEventActivity";
                }
                if (item.equals("FragmentTabActivity")){
                    junmString = "com.zhang.zhangandroid.basecomponent.FragmentTabActivity";
                }
                if (item.equals("WeixinFirstPageActivity")){
                    junmString = "com.zhang.zhangandroid.basecomponent.WeixinFirstPageActivity";
                }
                if (item.equals("PopWindowActivity")){
                    junmString = "com.zhang.zhangandroid.basecomponent.PopWindowActivity";
                }
                if (item.equals("AsyncTaskActivity")){
                    junmString = "com.zhang.zhangandroid.http.AsyncTaskActivity";
                }
                if (item.equals("GestureListenerActivity")){
                    junmString = "com.zhang.zhangandroid.basecomponent.GestureListenerActivity";
                }
                if (item.equals("ServiceBindeActivity")){
                    junmString = "com.zhang.zhangandroid.service.ServiceBindeActivity";
                }



                // 打开activity
               openActivity(junmString);



            }
        });

    }



}