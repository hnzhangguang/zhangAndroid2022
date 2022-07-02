package com.zhang.zhangandroid.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.zhang.zhangandroid.R;


public class BlankFragment2 extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_blank1, container, false);


        Button btn_fragment = inflate.findViewById(R.id.btn_fragment);
        TextView textView = inflate.findViewById(R.id.textView_fragment);
        btn_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("wo ye hen hao!");
            }
        });

        return inflate;
    }
}