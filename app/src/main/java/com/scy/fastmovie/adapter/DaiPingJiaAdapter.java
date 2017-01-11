package com.scy.fastmovie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.scy.fastmovie.R;
import com.scy.fastmovie.bean.DaiPingJiaBean;
import com.scy.fastmovie.customviews.MyTextView;

import java.util.List;

/**
 * Created by TimiZhuo on 2017/1/11.
 */
public class DaiPingJiaAdapter extends RecyclerView.Adapter<DaiPingJiaAdapter.MyViewHolder> {
    Context context;
    List<DaiPingJiaBean>data;
    public DaiPingJiaAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<DaiPingJiaBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.daipingjia_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Glide.with(context).load(data.get(position).getImg())
                .placeholder(R.mipmap.holder)
                .skipMemoryCache(true)
                .thumbnail(0.5f)
                .into(holder.img);
        holder.name.setText(data.get(position).getName());
        holder.price.setText(data.get(position).getPrice());
        holder.num.setText(data.get(position).getNum()+" 张");
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCallBack.onCall(data.get(position).getVideo(),1,null,null);
            }
        });
        final int i=position;
        final Button btn=holder.btn;
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCallBack.onCall("",2,data.get(i),btn);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        MyTextView name;
        TextView price,num;
        Button btn;
        public MyViewHolder(View itemView) {
            super(itemView);
            img=((ImageView) itemView.findViewById(R.id.img));
            name= ((MyTextView) itemView.findViewById(R.id.name));
            price= ((TextView) itemView.findViewById(R.id.price));
            num= ((TextView) itemView.findViewById(R.id.num));
            btn= ((Button) itemView.findViewById(R.id.btn));
        }
    }
    static OnCallBack onCallBack;
    public static void setOnCallBack(OnCallBack back){
        onCallBack=back;
    }
    public interface OnCallBack{
        void onCall(String path,int flag,DaiPingJiaBean bean,Button btn);
    }
}
