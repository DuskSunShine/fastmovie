package com.scy.fastmovie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scy.fastmovie.R;
import com.scy.fastmovie.bean.SeekBean;
import com.scy.fastmovie.interfaces.ShuJu;

import java.util.List;


/**
 * Created by TimiZhuo on 2017/1/4.
 */
public class RecycleViewAdapter extends RecyclerView.Adapter<MyViewHolder> {
    Context context;
    String flag;
    public RecycleViewAdapter(Context context,String flag) {
        this.context = context;
        this.flag=flag;
    }
    List<SeekBean.DataBean>data;

    public void setData(List<SeekBean.DataBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycle_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if (flag.equals(ShuJu.TYPE)){
            return data==null?0:data.get(0).getTagList().size()+1;
        }
        if (flag.equals(ShuJu.AREA)){
            return data==null?0:data.get(1).getTagList().size()+1;
        }
        if (flag.equals(ShuJu.YEAR)){
            return data==null?0:data.get(2).getTagList().size()+1;
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (position==0){
            holder.tv.setText(flag);
            holder.tv.setTextColor(context.getResources().getColor(R.color.gray));
            holder.tv.setBackgroundDrawable(null);
            holder.tv.setClickable(false);
        }else {
            if (flag.equals(ShuJu.TYPE)){
                holder.tv.setText(data.get(0).getTagList().get(position-1).getTagName());
            }
            if (flag.equals(ShuJu.AREA)){
                holder.tv.setText(data.get(1).getTagList().get(position-1).getTagName());
            }
            if (flag.equals(ShuJu.YEAR)){
                holder.tv.setText(data.get(2).getTagList().get(position-1).getTagName());
            }
//            holder.tv.setTextColor(context.getResources().getColor(R.color.black));
            holder.tv.setTextColor(context.getResources().getColor(R.color.black));
            holder.tv.setBackgroundResource(R.drawable.recycle_item_shape);
            holder.tv.setClickable(true);
            holder.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

    }


}
class MyViewHolder extends RecyclerView.ViewHolder{
    TextView tv;
    public MyViewHolder(View itemView) {
        super(itemView);
        this.tv= ((TextView) itemView.findViewById(R.id.tv));
    }
}