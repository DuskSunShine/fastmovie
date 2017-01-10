package com.scy.fastmovie.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.scy.fastmovie.R;
import com.scy.fastmovie.bean.CinemaBean;
import com.scy.fastmovie.customviews.MyTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCY on 2017/1/9 19:22.
 */

public class CinemaAdapter extends BaseAdapter {
    private Context context;
    private List<CinemaBean> data=new ArrayList<>();
    public void setData(List<CinemaBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public CinemaAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return data==null?0:data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    static class ViewHolder{
        TextView cinema_nm,cinema_addr,cinema_distance;
        MyTextView cinema_sellPrice;
        
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView==null){
            convertView=LayoutInflater.from(context).inflate(R.layout.cinema_item, null);
            viewHolder=new ViewHolder();
            viewHolder.cinema_nm= (TextView) convertView.findViewById(R.id.cinema_nm);
            viewHolder.cinema_addr= (TextView) convertView.findViewById(R.id.cinema_addr);
            viewHolder.cinema_distance= (TextView) convertView.findViewById(R.id.cinema_distance);
            viewHolder.cinema_sellPrice= (MyTextView) convertView.findViewById(R.id.cinema_sellPrice);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.cinema_nm.setText(data.get(position).getNm());
        viewHolder.cinema_sellPrice.setSpecifiedTextsColor(data.get(position).getSellPrice()+"元起"
        ,data.get(position).getSellPrice(), Color.RED);
        viewHolder.cinema_addr.setText(data.get(position).getAddr());
        viewHolder.cinema_distance.setText(data.get(position).getDistance());
        return convertView;
    }
}
