package com.scy.fastmovie.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.scy.fastmovie.bean.DiscoverBean;

import java.util.List;

/**
 * Created by SCY on 2016/12/28 19:46.
 */

public class DiscoverAdapter extends BaseAdapter{
    private Context context;
    private List<DiscoverBean.DataBean.FeedsBean> data;
    public DiscoverAdapter(Context context) {
        this.context = context;
    }
    public void setData(List<DiscoverBean.DataBean.FeedsBean> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
