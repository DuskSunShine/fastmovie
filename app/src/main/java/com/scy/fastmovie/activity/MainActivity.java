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
    private MovieFragment fragment_movie;
    private CinemaFragment fragment_cinema;
    private DiscoverFragment fragment_discover;
    private MineFragment fragment_mine;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT>=21){
            getWindow().setStatusBarColor(Color.argb(200,63,81,181));
        }
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        this.fragment_movie = new MovieFragment();
        transaction.add(R.id.layout, this.fragment_movie);
        this.fragment_cinema = new CinemaFragment();
        transaction.add(R.id.layout, this.fragment_cinema);
        this.fragment_discover = new DiscoverFragment();
        transaction.add(R.id.layout, this.fragment_discover);
        this.fragment_mine = new MineFragment();
        transaction.add(R.id.layout, this.fragment_mine);
        transaction.show(this.fragment_movie);
        transaction.hide(this.fragment_cinema);
        transaction.hide(this.fragment_discover);
        transaction.hide(this.fragment_mine);
        transaction.commit();
        initViews();
        setClickListener();
        useViews();

    }

    private void setClickListener() {

        rgb_bottom.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i <rgb_bottom.getChildCount() ; i++) {
                    RadioButton rb = (RadioButton) rgb_bottom.getChildAt(i);
                    if (rb.isChecked()){
                        switch (i){
                            case 0:
                                FragmentTransaction transaction = fragmentManager.beginTransaction();
                                transaction.show(fragment_movie);
                                transaction.hide(fragment_mine);
                                transaction.hide(fragment_discover);
                                transaction.hide(fragment_cinema);
                                transaction.commit();
                                break;
                            case 1:
                                FragmentTransaction transaction2 = fragmentManager.beginTransaction();
                                transaction2.hide(fragment_movie);
                                transaction2.hide(fragment_mine);
                                transaction2.hide(fragment_discover);
                                transaction2.show(fragment_cinema);
                                transaction2.commit();
                                break;
                            case 2:
                                FragmentTransaction transaction3 = fragmentManager.beginTransaction();
                                transaction3.hide(fragment_movie);
                                transaction3.hide(fragment_mine);
                                transaction3.show(fragment_discover);
                                transaction3.hide(fragment_cinema);
                                transaction3.commit();
                                break;
                            case 3:
                                FragmentTransaction transaction4 = fragmentManager.beginTransaction();
                                transaction4.hide(fragment_movie);
                                transaction4.show(fragment_mine);
                                transaction4.hide(fragment_discover);
                                transaction4.hide(fragment_cinema);
                                transaction4.commit();
                                break;
                        }
                    }
                }
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
