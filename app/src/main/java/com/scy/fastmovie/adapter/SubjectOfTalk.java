package com.scy.fastmovie.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/12/30 0030.
 */

public class SubjectOfTalk extends FragmentPagerAdapter {
    Context context;
    List<Fragment> list;
    List<String> titlestr;
    public SubjectOfTalk(FragmentManager fm,Context context,List<Fragment> list,List<String> titlestr) {
        super(fm);
        this.context=context;
        this.list=list;
        this.titlestr=titlestr;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return titlestr.size();
    }
      //显示Tab上边的名字
    @Override
    public CharSequence getPageTitle(int position) {
        return titlestr.get(position%titlestr.size());
    }
}
