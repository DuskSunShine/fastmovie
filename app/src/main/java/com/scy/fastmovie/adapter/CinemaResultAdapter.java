package com.scy.fastmovie.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.scy.fastmovie.R;
import com.scy.fastmovie.bean.SearchCinemaBean;
import com.scy.fastmovie.customviews.MyTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCY on 2017/1/9 15:49.
 */

public class CinemaResultAdapter extends BaseAdapter{
    private Context context;
    private List<SearchCinemaBean.DataBean> data=new ArrayList<>();
    private LayoutInflater inflater;
    private String keyword;

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setData(List<SearchCinemaBean.DataBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public CinemaResultAdapter(Context context) {
        this.context = context;
        inflater=LayoutInflater.from(context);
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
        TextView addr,hallType,distance;
        MyTextView sellPrice,nm;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView==null){
            convertView=inflater.inflate(R.layout.search_cinema_item,null);
            viewHolder=new ViewHolder();
            viewHolder.nm= (MyTextView) convertView.findViewById(R.id.nm);
            viewHolder.sellPrice= (MyTextView) convertView.findViewById(R.id.sellPrice);
            viewHolder.addr= (TextView) convertView.findViewById(R.id.addr);
            viewHolder.hallType= (TextView) convertView.findViewById(R.id.hallType);
            viewHolder.distance= (TextView) convertView.findViewById(R.id.distance);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        if (data.get(position).getNm().contains(keyword)){
            viewHolder.nm.setSpecifiedTextsColor(data.get(position).getNm()
            ,keyword,Color.RED);
            
        }
        viewHolder.sellPrice.setSpecifiedTextsColor(data.get(position).getSellPrice()+"元起",
                data.get(position).getSellPrice(),Color.RED);
        viewHolder.addr.setText(data.get(position).getAddr());
        viewHolder.distance.setText(String.valueOf((data.get(position).getDistance()/10000)).substring(0,3)+"km");
        try{
            viewHolder.hallType.setText(data.get(position).getHallType().get(0));
        }catch (Exception e){
            
        }finally {
            return convertView;
        }
       
    }
}
