package com.scy.fastmovie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.scy.fastmovie.R;
import com.scy.fastmovie.bean.WantToSeeBean;

import java.util.List;

/**
 * Created by Administrator on 2017/1/6 0006.
 */

public class WantToSeehotAdapter extends RecyclerView.Adapter<WantToSeehotAdapter.Myviewholder> {
    Context context;
    List<WantToSeeBean.DataBean.HotBean> list;

    public WantToSeehotAdapter(Context context, List<WantToSeeBean.DataBean.HotBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.wantwosee_hot_item, parent, false);
        return new Myviewholder(view);
    }

    @Override
    public void onBindViewHolder(Myviewholder holder, int position) {
        String urlpath=list.get(position).getImg().toString();
        String[] patharr=urlpath.split("w.h/");

//        Toast.makeText(context,patharr[0]+patharr[1],Toast.LENGTH_SHORT).show();
        Log.e("地址","==="+patharr[0]+patharr[1]);

        holder.tv_grade.setText(list.get(position).getNm().toString());
        holder.tv_detail.setText(list.get(position).getScm().toString());
        holder.tv_session.setText(list.get(position).getShowInfo().toString());
        Glide.with(context).load(patharr[0]+patharr[1]).placeholder(R.mipmap.adu).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Myviewholder extends RecyclerView.ViewHolder {
        public ImageView img;
        public TextView tv_grade,tv_detail,tv_session;

        public Myviewholder(View itemView) {
            super(itemView);
            img= (ImageView) itemView.findViewById(R.id.img);
           tv_grade= (TextView) itemView.findViewById(R.id.tv_grade);
            tv_detail= (TextView) itemView.findViewById(R.id.tv_detail);
            tv_session= (TextView) itemView.findViewById(R.id.tv_session);
        }
    }
}
