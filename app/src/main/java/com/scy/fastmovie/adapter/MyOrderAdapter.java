package com.scy.fastmovie.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/12/30 0030.
 */

public class MyOrderAdapter extends FragmentPagerAdapter {
    List<Fragment> lists;
    List<String> titlestr;
    Context context;
    public MyOrderAdapter(FragmentManager fm,Context context,List<Fragment> lists,List<String> titlestr) {
        super(fm);
        this.lists=lists;
        this.titlestr=titlestr;
        this.context=context;
    }

    @Override
    public Fragment getItem(int position) {
        return lists.get(position);
    }

    @Override
    public int getCount() {
        return titlestr.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titlestr.get(position%titlestr.size());
    }
}
