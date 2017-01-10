package com.scy.fastmovie.fragment;


import android.content.Context;
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
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.baidu.mapapi.map.MapView;
import com.scy.fastmovie.R;
import com.scy.fastmovie.adapter.FragmentAdapter;
import com.scy.fastmovie.customviews.MyTextView;
import com.scy.fastmovie.interfaces.DataCallBack;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment implements DataCallBack{


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
    private List<String>lists=new ArrayList();
    private MyTextView tv_heard;
    Context context;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

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
        spinner.setAdapter(new ArrayAdapter<String>(context,
                R.layout.spinner_item,R.id.tv,lists));

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
                tv_heard.setText(lists.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinner.setVisibility(View.INVISIBLE);
                linearLayout.setVisibility(View.VISIBLE);
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
        tv_heard = (MyTextView) view.findViewById(R.id.tv_heard);
        data.add(new HotFragment());
        data.add(new WaitFragment());
        data.add(new SeekFragment());
        String[] str={"成都市","自贡市","攀枝花市","泸州市","德阳市","绵阳市","广元市","遂宁市","内江市","乐山市","南充市","眉山市","宜宾市","广安市","达州市","雅安市","巴中市","资阳市","阿坝藏族羌族自治州","甘孜藏族自治州","凉山彝族自治州",
        "都江堰市","彭州市","邛崃市","崇州市","广汉市","什邡市","绵竹市","江油市","峨眉山市","阆中市","华蓥市","万源市","简阳市","西昌市"};
        lists.addAll(Arrays.asList(str));
    }


    @Override
    public void getDataCallBack(String city) {
        if (city!=null){
            tv_heard.setText(city);
        }
    }
}
