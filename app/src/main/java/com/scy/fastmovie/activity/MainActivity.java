package com.scy.fastmovie.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.scy.fastmovie.R;
import com.scy.fastmovie.fragment.CinemaFragment;
import com.scy.fastmovie.fragment.DiscoverFragment;
import com.scy.fastmovie.fragment.MineFragment;
import com.scy.fastmovie.fragment.MovieFragment;

public class MainActivity extends AppCompatActivity {

    private RadioGroup rgb_bottom;
    private RadioButton rb1;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT>=21){
            getWindow().setStatusBarColor(Color.argb(200,63,81,181));
        }
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        FragmentTransaction fragment_movie = transaction.add(R.id.layout, new MovieFragment());
        FragmentTransaction fragment_cinema = transaction.add(R.id.layout, new CinemaFragment());
        FragmentTransaction fragment_discover = transaction.add(R.id.layout, new DiscoverFragment());
        FragmentTransaction fragment_mine = transaction.add(R.id.layout, new MineFragment());
        initViews();
        setClickListener();
        useViews();

    }

    private void setClickListener() {

        rgb_bottom.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

            }
        });
    }

    private void useViews() {
        rb1.setChecked(true);
    }

    private void initViews() {
        rgb_bottom = (RadioGroup) findViewById(R.id.rgb_bottom);
        rb1 = (RadioButton) findViewById(R.id.rb_1);
    }
}
