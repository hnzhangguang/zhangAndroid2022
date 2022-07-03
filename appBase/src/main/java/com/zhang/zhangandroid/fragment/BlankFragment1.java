package com.zhang.zhangandroid.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.zhang.zhangandroid.R;


public class BlankFragment1 extends Fragment {

    public static final String ARG_TEXT ="msg";
    private String  mTextString;

    public BlankFragment1(){

    }


    public static BlankFragment1 newInstance(String param1){
        BlankFragment1 fragment = new BlankFragment1();
        Bundle args = new Bundle();
        args.putString(ARG_TEXT,param1);

        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mTextString = getArguments().getString(ARG_TEXT);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_blank1, container, false);


        Button btn_fragment = inflate.findViewById(R.id.btn_fragment);
        TextView textView = inflate.findViewById(R.id.textView_fragment);

        TextView text = inflate.findViewById(R.id.text);
        text.setText(mTextString);

        btn_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("wo hen hao!");
            }
        });
        btn_fragment.setTextColor(getResources().getColor(R.color.colorAccent));

        return inflate;
    }
}