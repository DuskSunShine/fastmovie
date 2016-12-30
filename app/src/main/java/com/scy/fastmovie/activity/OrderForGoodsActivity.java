package com.scy.fastmovie.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.scy.fastmovie.R;
import com.scy.fastmovie.adapter.MyOrderAdapter;
import com.scy.fastmovie.fragment.AmbitusShow;
import com.scy.fastmovie.fragment.FilmFragment;

import java.util.ArrayList;
import java.util.List;

public class OrderForGoodsActivity extends AppCompatActivity {

    private TabLayout tab_layout;
    private ViewPager viewpager;
    private List<Fragment> lists=new ArrayList<>();
    private List<String> titlestr=new ArrayList<>();
    MyOrderAdapter myOrderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_for_goods);
initdata();

    }

    private void initdata() {
        findviewbyid();
        adddata();
        setadapter();
    }

    private void setadapter() {
    tab_layout.setTabMode(TabLayout.MODE_FIXED);
        tab_layout.addTab(tab_layout.newTab().setText(titlestr.get(0)));
        tab_layout.addTab(tab_layout.newTab().setText(titlestr.get(1)));
        myOrderAdapter=new MyOrderAdapter(getSupportFragmentManager(),OrderForGoodsActivity.this,lists,titlestr);

        viewpager.setAdapter(myOrderAdapter);
        tab_layout.setupWithViewPager(viewpager);
    }

    private void adddata() {
        lists.add(new FilmFragment());
        lists.add(new AmbitusShow());
        titlestr.add("电影");
        titlestr.add("周边·演出");
    }

    private void findviewbyid() {
        tab_layout = (TabLayout) findViewById(R.id.tab_layout);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
    }

    public void back(View view) {
        finish();
    }
}
