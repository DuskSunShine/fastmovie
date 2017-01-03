package com.scy.fastmovie.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.scy.fastmovie.R;
import com.scy.fastmovie.adapter.SubjectOfTalk;
import com.scy.fastmovie.fragment.AttentionFragment;
import com.scy.fastmovie.fragment.PublishFragment;
import com.scy.fastmovie.fragment.ReplyFragment;

import java.util.ArrayList;
import java.util.List;

public class SubjiectOfTalkActivity extends AppCompatActivity {

    private TabLayout tablayout;
    private ViewPager view_pager;
    private List<Fragment> lists=new ArrayList<>();
    private List<String> titlestr=new ArrayList<>();

    SubjectOfTalk subjectoftalkadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjiect_of_talk);
        initdata();

    }

    private void initdata() {
        findviewbyid();
        listadddata();
        setadapter();
        setonclicklistener();
    }

    private void setadapter() {
        //tablayout和viewpager建立联系和设置Tab
        tablayout.setTabMode(TabLayout.MODE_FIXED);
        tablayout.addTab(tablayout.newTab().setText(titlestr.get(0)));
        tablayout.addTab(tablayout.newTab().setText(titlestr.get(1)));
        tablayout.addTab(tablayout.newTab().setText(titlestr.get(2)));
        subjectoftalkadapter=new SubjectOfTalk(getSupportFragmentManager(),SubjiectOfTalkActivity.this,lists,titlestr);
        view_pager.setAdapter(subjectoftalkadapter);
        tablayout.setupWithViewPager(view_pager);



    }

    private void listadddata() {
        lists.add(new PublishFragment());
        lists.add(new ReplyFragment());
        lists.add(new AttentionFragment());
        titlestr.add("我发布的");
        titlestr.add("我回复的");
        titlestr.add("我关注的");
    }

    private void setonclicklistener() {

    }

    private void findviewbyid() {
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        view_pager = (ViewPager) findViewById(R.id.view_pager);
    }

    public void back(View view) {
        finish();
    }
}
