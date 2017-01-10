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
import com.scy.fastmovie.bean.WaitMostBean;

import java.util.List;

/**
 * Created by TimiZhuo on 2017/1/9.
 *
 */
public class WaitMostAdapter extends RecyclerView.Adapter<WaitMostAdapter.MyViewHolder>{
    Context context;

    public WaitMostAdapter(Context context) {
        this.context = context;
    }
    List<WaitMostBean.DataBean.ComingBean>data;

    public void setData(List<WaitMostBean.DataBean.ComingBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.wait_most_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (position==0){
            holder.layout.setPadding(20,10,10,10);
        }else {
            holder.layout.setPadding(10,10,10,10);
        }
        String time="";
        if (data.get(position).getRt()!=null){
            String[] split = data.get(position).getRt().split("-");
            String time1=split[1].startsWith("0")?split[1].substring(1)+"月":split[1]+"月";
            String time2=split[2]+"日";
            time=time1+time2;
        }else {
            String[] split = data.get(position).getComingTitle().split(" ");
            time=split[0]+split[1];
        }


        holder.time.setText(time);
        holder.name.setText(data.get(position).getNm());
        holder.wish.setText(data.get(position).getWish()+"人想看");
        String[] img = data.get(position).getImg().split("/w.h");
        Glide.with(context).load(img[0]+img[1])
                .placeholder(R.mipmap.holder)
                .thumbnail(0.5f)
                .skipMemoryCache(true)
                .into(holder.img);
        final String videourl = data.get(position).getVideourl();
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scall.onRelativeClick(videourl);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView time,name,wish;
        ImageView img;
        LinearLayout layout;
        public MyViewHolder(View itemView) {
            super(itemView);
            time= ((TextView) itemView.findViewById(R.id.time));
            name= ((TextView) itemView.findViewById(R.id.name));
            wish= ((TextView) itemView.findViewById(R.id.wish));
            img= ((ImageView) itemView.findViewById(R.id.img));
            layout= ((LinearLayout) itemView.findViewById(R.id.layout));
        }
    }
    public  interface Call{
        void onRelativeClick(String path);
    }
    public static Call scall=null;
    public static void setOnRelativeClickListener(Call call){
        scall=call;
    }
}
