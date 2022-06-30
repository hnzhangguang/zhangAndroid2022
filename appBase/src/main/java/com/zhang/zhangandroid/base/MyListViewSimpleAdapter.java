package com.zhang.zhangandroid.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhang.zhangandroid.R;

import java.util.List;

/**
     * 简介: listView适配器
     *  作者: zhangg
     */
public class MyListViewSimpleAdapter extends BaseAdapter {

        List<String> datas ; // 数据集
        Context context; // 上下文

        public MyListViewSimpleAdapter(List<String> datas, Context context){
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
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View inflate =inflater.inflate(R.layout.mainactivity_list_item, null);
            TextView textView = inflate.findViewById(R.id.activity_list_item);
            textView.setText(getItem(i).toString());
            return inflate;
        }
    }