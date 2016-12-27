package com.scy.fastmovie.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.scy.fastmovie.R;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RadioGroup rgb;
    private RadioButton rb_hot;
    private RadioButton rb_wait;
    private RadioButton rb_seek;
    private ViewPager pager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT>=21){
            getWindow().setStatusBarColor(Color.argb(200,63,81,181));
        }
        initViews();
        setClickListener();
        useViews();
    }

    private void setClickListener() {
        rgb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO: 2016/12/27
            }
        });
    }

    private void useViews() {
        setSupportActionBar(toolbar);
    }

    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        rgb = (RadioGroup) findViewById(R.id.rgb);
        rb_hot = (RadioButton) findViewById(R.id.hot);
        rb_wait = (RadioButton) findViewById(R.id.wait);
        rb_seek = (RadioButton) findViewById(R.id.seek);
        pager = (ViewPager) findViewById(R.id.pager);
    }
}
