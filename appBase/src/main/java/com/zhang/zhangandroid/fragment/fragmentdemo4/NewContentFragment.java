package com.zhang.zhangandroid.fragment.fragmentdemo4;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhang.zhangandroid.R;

/**
 * Created by Jay on 2015/9/6 0006.
 */
public class NewContentFragment extends Fragment {

    public NewContentFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_content_fragment, container, false);
        TextView txt_content = (TextView) view.findViewById(R.id.txt_content_fragment);
        //getArgument获取传递过来的Bundle对象
        txt_content.setText(getArguments().getString("content"));
        return view;
    }

}
