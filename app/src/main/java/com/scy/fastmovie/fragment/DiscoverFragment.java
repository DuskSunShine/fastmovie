package com.scy.fastmovie.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.scy.fastmovie.R;
import com.scy.fastmovie.activity.SearchNewsResultActivity;

/**
 * 发现碎片
 */
public class DiscoverFragment extends Fragment {
    
    private Toolbar discover_toolbar;
    private View view;
    private EditText search_edit;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_discover, container, false);
        initViews();
        setListener();
        //fragment设置toolbar
        ((AppCompatActivity)getActivity()).setSupportActionBar(discover_toolbar);
        return view;
    }

    /**
     * 初始化布局
     */
    private void initViews(){
        discover_toolbar= (Toolbar) view.findViewById(R.id.discover_toolbar);
        search_edit= (EditText) view.findViewById(R.id.search_edit);
    }

    /**
     * 控件监听
     */
    private void setListener(){
        search_edit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    startActivity(new Intent(getActivity(), SearchNewsResultActivity.class));
                }
            }
        });
    }

}
