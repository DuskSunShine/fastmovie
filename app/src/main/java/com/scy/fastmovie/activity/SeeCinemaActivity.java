package com.scy.fastmovie.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.scy.fastmovie.R;
import com.scy.fastmovie.adapter.MyViewPagerAdapter;
import com.scy.fastmovie.utils.GalleryPageTransformer;

import java.util.ArrayList;
import java.util.List;

public class SeeCinemaActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{
    private ImageView see_cinema_star;
    private boolean isClicked=false;
    private TextView see_cinema_text,see_text;
    private ViewPager mViewPager;
    private LinearLayout ll_main;
    private List<ImageView> imageViews;
    private List<String> textData;
    private int pagerWidth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_cinema);
        initViews();
        initData();
        mViewPager.setOffscreenPageLimit(3);
        pagerWidth = (int) (getResources().getDisplayMetrics().widthPixels *1.8f / 5.0f );//1.8f / 5.0f
        ViewGroup.LayoutParams lp = mViewPager.getLayoutParams();
        if (lp == null) {
            lp = new ViewGroup.LayoutParams(pagerWidth, ViewGroup.LayoutParams.MATCH_PARENT);
        } else {
            lp.width = pagerWidth;
        }
        mViewPager.setLayoutParams(lp);
        mViewPager.setPageMargin(-50);
        ll_main.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return mViewPager.dispatchTouchEvent(motionEvent);
            }
        });
        mViewPager.setPageTransformer(true, new GalleryPageTransformer());
        mViewPager.setAdapter(new MyViewPagerAdapter(imageViews));
    }
    private void initData(){
        see_cinema_text.setText("幸福蓝海国际影城");
        textData=new ArrayList<>();
        textData.add("134分钟 | 动作 | 菲丽希缇·琼斯,迭戈·鲁纳");
        textData.add("113分钟 | 喜剧 | 肖央,闫妮,小沈阳");
        textData.add("79分钟 | 喜剧 | 贝拉·索恩,沙尔托·科普雷");
        textData.add("91分钟 | 喜剧 | 易烊千玺,陈志荣,彭雨菲");
        textData.add("139分钟 | 剧情 | 安德鲁·加菲尔德");
        textData.add("104分钟 | 剧情 | 马特·达蒙,景甜");
        textData.add("124分钟 | 剧情 | 成龙,黄子韬,房祖名");
        textData.add("128分钟 | 喜剧 | 梁朝伟,金城武,陈奕迅");
        imageViews=new ArrayList<>();
        ImageView imageView1=new ImageView(this);
        Glide.with(this).load(R.drawable.o).thumbnail(0.5f).into(imageView1);
        ImageView imageView2=new ImageView(this);
        Glide.with(this).load(R.drawable.t).thumbnail(0.5f).into(imageView2);
        ImageView imageView3=new ImageView(this);
        Glide.with(this).load(R.drawable.tr).thumbnail(0.5f).into(imageView3);
        ImageView imageView4=new ImageView(this);
        Glide.with(this).load(R.drawable.f).thumbnail(0.5f).into(imageView4);
        ImageView imageView5=new ImageView(this);
        Glide.with(this).load(R.drawable.s).thumbnail(0.5f).into(imageView5);
        ImageView imageView6=new ImageView(this);
        Glide.with(this).load(R.drawable.e).thumbnail(0.5f).into(imageView6);
        ImageView imageView7=new ImageView(this);
        Glide.with(this).load(R.drawable.n).thumbnail(0.5f).into(imageView7);
        ImageView imageView8=new ImageView(this);
        Glide.with(this).load(R.drawable.tt).thumbnail(0.5f).into(imageView8);
        imageViews.add(imageView1);
        imageViews.add(imageView2);
        imageViews.add(imageView3);
        imageViews.add(imageView4);
        imageViews.add(imageView5);
        imageViews.add(imageView6);
        imageViews.add(imageView7);
        imageViews.add(imageView8);
    }

    public void click(View view) {
        if (isClicked){
            see_cinema_star.setImageResource(R.mipmap.star);
        }else{
            see_cinema_star.setImageResource(R.mipmap.clcstart);
        }
        isClicked=!isClicked;
    }
    private void initViews(){
        see_cinema_star= (ImageView) findViewById(R.id.see_cinema_star);
        see_cinema_text= (TextView) findViewById(R.id.see_cinema_text);
        mViewPager= (ViewPager) findViewById(R.id.mViewPager);
        ll_main = (LinearLayout) findViewById(R.id.activity_main);
        see_text= (TextView) findViewById(R.id.see_text);
        mViewPager.addOnPageChangeListener(this);
    }
    
    public void backtwo(View view) {
        finish();
        overridePendingTransition(0,0);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        
    }

    @Override
    public void onPageSelected(int position) {
        see_text.setText(textData.get(position));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
