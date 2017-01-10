package com.scy.fastmovie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.scy.fastmovie.R;
import com.scy.fastmovie.bean.WaitGlobalAwards;

import java.util.List;

/**
 * Created by TimiZhuo on 2017/1/6.
 */
public class GlobalAdapter extends RecyclerView.Adapter<GlobalAdapter.MyViewHolder> {
    Context context;

    public GlobalAdapter(Context context) {
        this.context = context;
    }
    List<WaitGlobalAwards.DataBean>data;

    public void setData(List<WaitGlobalAwards.DataBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.wait_global_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_title.setText(data.get(position).getFestivalName());
        holder.tv_date.setText(data.get(position).getHeldDate());
        holder.top.setText(data.get(position).getPrizeName());
        holder.tv_film.setText(data.get(position).getMovieName());
        String path = data.get(position).getImg();
        String[] split = path.split("/w.h/");
        Glide.with(context).load(split[0]+"/"+split[1])
                .skipMemoryCache(true)
                .thumbnail(0.5f)
                .placeholder(R.mipmap.holder)
                .into(holder.img);
        if (position==0){
            holder.itemView.setPadding(20,10,10,10);
        }else {
            holder.itemView.setPadding(10,10,10,10);
        }
    }

    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        View itemView;
        LinearLayout btn_title;
        TextView tv_title,tv_date,top,tv_film;
        ImageView img;
        public MyViewHolder(View itemView) {
            super(itemView);
            this.itemView=itemView;
            btn_title=(LinearLayout) itemView.findViewById(R.id.btn_title);
            tv_title= ((TextView) itemView.findViewById(R.id.tv_title));
            tv_date= ((TextView) itemView.findViewById(R.id.tv_date));
            img= ((ImageView) itemView.findViewById(R.id.img));
            top= ((TextView) itemView.findViewById(R.id.top));
            tv_film= ((TextView) itemView.findViewById(R.id.tv_film));
        }
    }
}
