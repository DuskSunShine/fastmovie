package com.scy.fastmovie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.scy.fastmovie.R;
import com.scy.fastmovie.activity.MainActivity;
import com.scy.fastmovie.bean.HotFragmentBean;
import com.scy.fastmovie.customviews.MyTextView;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by TimiZhuo on 2016/12/29.
 */
public class HotAdapter extends BaseAdapter {
    private Context context;

    public HotAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<HotFragmentBean.DataBean.HotBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    private List<HotFragmentBean.DataBean.HotBean> data;

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
        ImageButton img;
        TextView tv_film,tv_grade,buy_ticket;
        ImageView img_flag;
        MyTextView tv_detail,tv_session,tv_audience;
        View itemView;
        RelativeLayout layout;

        public ViewHolder(View itemView) {
            this.itemView = itemView;
            img= ((ImageButton) itemView.findViewById(R.id.img));
            tv_film= ((TextView) itemView.findViewById(R.id.tv_film));
            tv_grade= ((TextView) itemView.findViewById(R.id.tv_grade));
            img_flag= ((ImageView) itemView.findViewById(R.id.img_flag));
            tv_detail= ((MyTextView) itemView.findViewById(R.id.tv_detail));
            tv_session= ((MyTextView) itemView.findViewById(R.id.tv_session));
            tv_audience= ((MyTextView) itemView.findViewById(R.id.audience));
            buy_ticket= ((TextView) itemView.findViewById(R.id.buy_ticket));
            layout= ((RelativeLayout) itemView.findViewById(R.id.relativelayout));
        }
    }
    @Override
    public View getView( int position, View convertView, ViewGroup parent) {
        final int p=position;
        ViewHolder viewHolder=null;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.hot_item,parent,false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= ((ViewHolder) convertView.getTag());
        }
        if(data!=null){
            viewHolder.tv_detail.setText(data.get(position).getScm());
            viewHolder.tv_session.setText(data.get(position).getShowInfo());
            viewHolder.tv_film.setText(data.get(position).getNm());

            Timestamp timestamp=Timestamp.valueOf(data.get(position).getRt()+" 00:00:00");
            long time=timestamp.getTime();
            Date date=new Date();
            long curTime=date.getTime();
//            if (data.get(position).getSc()!=0){
                if (curTime-time>0){
                    viewHolder.tv_audience.setText("观众");
                    viewHolder.buy_ticket.setText("购票");
                }else {
                    viewHolder.tv_audience.setText("点映");
                    viewHolder.buy_ticket.setText("预售");
                }
//            }
            String path=data.get(position).getImg();
            String[] split = path.split("/w.h/");
            String imgUrl=split[0]+"/"+split[1];
            Glide.with(context).load(imgUrl)
                    .placeholder(R.mipmap.holder)
                    .thumbnail(0.5f)
                    .skipMemoryCache(true)
                    .into(viewHolder.img);
            if (data.get(position).getSc()==0){
                viewHolder.tv_audience.setText("暂无评分");
                viewHolder.tv_grade.setVisibility(View.INVISIBLE);
            }else {
                viewHolder.tv_grade.setVisibility(View.VISIBLE);
                viewHolder.tv_grade.setText(data.get(position).getSc()+"");
            }
            if (data.get(position).getShowst()==4&&data.get(position).getSc()==0){
//                viewHolder.tv_audience.setText(data.get(position).getWish()+" 人想看");
                viewHolder.tv_audience.setSpecifiedTextsColor(data.get(position).getWish()+" 人想看",data.get(position).getWish()+""
                ,context.getResources().getColor(R.color.yellow));
                viewHolder.tv_grade.setVisibility(View.INVISIBLE);
            }else {
                viewHolder.tv_grade.setVisibility(View.VISIBLE);
            }
            viewHolder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    scall.onRelativeClick(data.get(p).getVideourl());
                }
            });
            viewHolder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    scall.onRelativeClick(data.get(p).getVideourl());
                }
            });
            viewHolder.buy_ticket.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    stcall.onRelativeClick(data.get(p),((TextView)v).getText().toString());
                }
            });
        }
        return convertView;
    }
    public static interface Call{
        public void onRelativeClick(String path);
    }
    public static Call scall=null;
    public static void setOnRelativeClickListener(Call call){
        scall=call;
    }
    public static interface CallText{
        public void onRelativeClick(HotFragmentBean.DataBean.HotBean databean,String flag);
    }
    public static CallText stcall=null;
    public static void setOnRelativeClickListener(CallText call){
        stcall=call;
    }
}
