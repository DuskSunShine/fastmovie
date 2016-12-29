package com.scy.fastmovie.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.baidu.mapapi.map.MapView;
import com.scy.fastmovie.R;
import com.scy.fastmovie.adapter.FragmentAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {


    private View view;
    private Toolbar toolbar;
    private RadioGroup rgb;
    private RadioButton rb_hot;
    private RadioButton rb_wait;
    private RadioButton rb_seek;
    private ViewPager pager;
    private List<Fragment>data=new ArrayList<>();
    private FragmentAdapter adapter;
    private LinearLayout linearLayout;
    private Spinner spinner;


    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_blank, container, false);
        initViews();
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        setClickListener();
        rb_hot.setChecked(true);
        rb_hot.setBackgroundResource(R.drawable.top_checked_shape);
        rb_wait.setBackgroundResource(R.drawable.top_unchecked_shape);
        rb_seek.setBackgroundResource(R.drawable.top_unchecked_shape);
        spinner.setVisibility(View.INVISIBLE);
        adapter = new FragmentAdapter(getChildFragmentManager(), data);
        pager.setAdapter(adapter);
        return view;
    }

    private void setClickListener() {
        rgb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.hot:
                        rb_hot.setBackgroundResource(R.drawable.top_checked_shape);
                        rb_wait.setBackgroundResource(R.drawable.top_unchecked_shape);
                        rb_seek.setBackgroundResource(R.drawable.top_unchecked_shape);
                        break;
                    case R.id.wait:
                        rb_wait.setBackgroundResource(R.drawable.top_checked_shape);
                        rb_hot.setBackgroundResource(R.drawable.top_unchecked_shape);
                        rb_seek.setBackgroundResource(R.drawable.top_unchecked_shape);
                        break;
                    case R.id.seek:
                        rb_seek.setBackgroundResource(R.drawable.top_checked_shape);
                        rb_wait.setBackgroundResource(R.drawable.top_unchecked_shape);
                        rb_hot.setBackgroundResource(R.drawable.top_unchecked_shape);
                        break;
                }
                for (int i = 0; i <rgb.getChildCount() ; i++) {
                    RadioButton rb = (RadioButton) rgb.getChildAt(i);
                    if (rb.isChecked()){
                        pager.setCurrentItem(i);
                    }
                }
            }
        });
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.INVISIBLE);
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner.setVisibility(View.INVISIBLE);
                linearLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                RadioButton rb = (RadioButton) rgb.getChildAt(position);
                rb.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initViews() {
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        rgb = ((RadioGroup) view.findViewById(R.id.rgb));
        rb_hot = ((RadioButton) view.findViewById(R.id.hot));
        rb_wait = ((RadioButton) view.findViewById(R.id.wait));
        rb_seek = ((RadioButton) view.findViewById(R.id.seek));
        pager = ((ViewPager) view.findViewById(R.id.pager));
        linearLayout = ((LinearLayout) view.findViewById(R.id.linearlaout));
        spinner = ((Spinner) view.findViewById(R.id.spinner));

        data.add(new HotFragment());
        data.add(new WaitFragment());
        data.add(new SeekFragment());
    }


}
