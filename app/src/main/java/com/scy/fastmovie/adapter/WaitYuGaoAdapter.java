package com.scy.fastmovie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.scy.fastmovie.R;
import com.scy.fastmovie.bean.WaitYuGaoBean;

import java.util.List;

/**
 * Created by TimiZhuo on 2017/1/9.
 *
 */
public class WaitYuGaoAdapter extends RecyclerView.Adapter<WaitYuGaoAdapter.MyViewHolder> {
    Context context;

    public WaitYuGaoAdapter(Context context) {
        this.context = context;
    }
    List<WaitYuGaoBean.DataBean>data;

    public void setData(List<WaitYuGaoBean.DataBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.yugao, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,int position) {
        if (position==0){
            holder.layout.setPadding(20,10,10,10);
        }else {
            holder.layout.setPadding(10,10,10,10);
        }
        holder.title.setText(data.get(position).getMovieName());
        holder.message.setText(data.get(position).getOriginName());
        Glide.with(context).load(data.get(position).getImg())
                .placeholder(R.mipmap.holder)
                .thumbnail(0.5f)
                .skipMemoryCache(true)
                .into(holder.img);
        final String url = data.get(position).getUrl();
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scall.onRelativeClick(url);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView title,message;
        RelativeLayout layout;
        public MyViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img);
            title= ((TextView) itemView.findViewById(R.id.title));
            message= ((TextView) itemView.findViewById(R.id.message));
            layout= ((RelativeLayout) itemView.findViewById(R.id.layout));
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
