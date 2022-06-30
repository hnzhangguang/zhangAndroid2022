package com.zhang.zhangandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.zhang.zhangandroid.base.BaseActivity;
import com.zhang.zhangandroid.basecomponent.ImageViewActivity;

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

        MyAdapter adapter = new MyAdapter(list,this);

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


                // 打开activity
               openActivity(junmString);



            }
        });

    }


    /**
     * 简介: listView适配器
     *  作者: zhangg
     */
    class MyAdapter extends BaseAdapter{

        List<String> datas ; // 数据集
        Context context; // 上下文

        public MyAdapter(List<String> datas, Context context){
            this.datas = datas;
            this.context = context;
        }

        @Override
        public int getCount() {
            return null == datas ? 0: datas.size();
        }

        @Override
        public Object getItem(int i) {
            return datas.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View inflate = getLayoutInflater().inflate(R.layout.mainactivity_list_item, null);
            TextView textView = inflate.findViewById(R.id.activity_list_item);
            textView.setText(getItem(i).toString());
            return inflate;
        }
    }
}