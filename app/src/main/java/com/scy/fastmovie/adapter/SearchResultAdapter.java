package com.scy.fastmovie.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.scy.fastmovie.R;
import com.scy.fastmovie.bean.SearchResultBean;
import com.scy.fastmovie.customviews.MyTextView;

import java.util.List;

/**
 * Created by SCY on 2017/1/3 15:48.
 */

public class SearchResultAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<SearchResultBean.DataBean.ListBean> data;
    private String keyword;

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public SearchResultAdapter(Context context) {
        this.context = context;
        inflater=LayoutInflater.from(context);
    }

    public void setData(List<SearchResultBean.DataBean.ListBean> data) {
        this.data = data;
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
        TextView search_result_nick
        ,search_result_look,search_result_comment;
        MyTextView search_result_title;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView==null){
            convertView=inflater.inflate(R.layout.search_result_item,null);
            viewHolder=new ViewHolder();
            viewHolder.search_result_comment= (TextView) convertView.findViewById(R.id.search_result_comment);
            viewHolder.search_result_look= (TextView) convertView.findViewById(R.id.search_result_look);
            viewHolder.search_result_nick= (TextView) convertView.findViewById(R.id.search_result_nick);
            viewHolder.search_result_title= (MyTextView) convertView.findViewById(R.id.search_result_title);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        if (data.get(position).getTitle().contains(keyword)){
            viewHolder.search_result_title.setSpecifiedTextsColor(data.get(position).getTitle()
                    ,keyword,Color.RED);
        }
        if (data.get(position).getSource()!=""){
            viewHolder.search_result_nick.setText(data.get(position).getSource());
        }else{
            viewHolder.search_result_nick.setText(data.get(position).getAuthor());
        }
        viewHolder.search_result_look.setText(String.valueOf(data.get(position).getViewCount()));
        viewHolder.search_result_comment.setText(String.valueOf(data.get(position).getCommentCount()));
        return convertView;
    }
}
