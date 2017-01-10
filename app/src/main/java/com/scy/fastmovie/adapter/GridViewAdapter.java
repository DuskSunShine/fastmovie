package com.scy.fastmovie.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.scy.fastmovie.R;
import com.scy.fastmovie.bean.WaitGridBean;

import java.util.List;

/**
 * Created by TimiZhuo on 2017/1/4.
 * 网格布局
 */
public class GridViewAdapter extends BaseAdapter {
    Context context;

    public GridViewAdapter(Context context) {
        this.context = context;
    }
    List<WaitGridBean.DataBean>data;

    public void setData(List<WaitGridBean.DataBean> data) {
        this.data = data;
        Log.e("====","data"+data.size());
        notifyDataSetChanged();
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
    class ViewHolder{
        TextView tv_type,tv_name;
        ImageView img_top,img_buttom;
        public ViewHolder(View itemView) {
            tv_type=((TextView) itemView.findViewById(R.id.type));
            tv_name= ((TextView) itemView.findViewById(R.id.name));
            img_buttom= ((ImageView) itemView.findViewById(R.id.img_buttom));
            img_top= ((ImageView) itemView.findViewById(R.id.img_top));
        }
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.wait_grid_item,parent,false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= ((ViewHolder) convertView.getTag());
        }
        viewHolder.tv_type.setText(data.get(position).getBoardName());
        viewHolder.tv_name.setText(data.get(position).getMovieName());
        String buttomPath = data.get(position).getMovieImgs().get(1);
        String[] split = buttomPath.split("/w.h/");
        Glide.with(context).load(split[0]+"/"+split[1])
                .skipMemoryCache(true)
                .thumbnail(0.5f)
                .placeholder(R.mipmap.holder)
                .into(viewHolder.img_buttom);
        String topPath = data.get(position).getMovieImgs().get(0);
        String[] split1 = topPath.split("/w.h/");
        Glide.with(context).load(split1[0]+"/"+split1[1])
                .skipMemoryCache(true)
                .thumbnail(0.5f)
                .placeholder(R.mipmap.holder)
                .into(viewHolder.img_top);
        if (position==0){
            viewHolder.tv_type.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        }
        if (position==1){
            viewHolder.tv_type.setTextColor(context.getResources().getColor(R.color.colorAccent));
        }
        if (position==2){
            viewHolder.tv_type.setTextColor(context.getResources().getColor(R.color.colorARed));
        }
        if (position==3){
            viewHolder.tv_type.setTextColor(context.getResources().getColor(R.color.yellow));
        }
        return convertView;
    }
}
