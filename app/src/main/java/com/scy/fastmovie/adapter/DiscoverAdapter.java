package com.scy.fastmovie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.scy.fastmovie.R;
import com.scy.fastmovie.bean.DiscoverBean;
import com.scy.fastmovie.utils.SetTime;

import java.util.ArrayList;
import java.util.List;

/**发现界面--适配器
 * Created by SCY on 2016/12/28 19:46.
 */

public class DiscoverAdapter extends BaseAdapter{
    private Context context;
    private List<DiscoverBean.DataBean.FeedsBean> data=new ArrayList<>();
    private static final int View_TYPECOUNT=2;
    private static final int View_TYPE1=0;
    private static final int View_TYPE3=1;
    private LayoutInflater inflater;
    private List<DiscoverBean.DataBean.FeedsBean.ImagesBean> images=new ArrayList<>();
    
    
    public DiscoverAdapter(Context context) {
        this.context = context;
    }
    public void setData(List<DiscoverBean.DataBean.FeedsBean> data) {
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
    //一张图片的holder
    static class ViewHolder1{
        ImageView discover_img;
        TextView discover_text,discover_time,discover_text2,
                discover_text8,discover_text7;
    }
    //三张图片的holder
    static class ViewHolder3{
        TextView discover_text3,discover_text4,discover_time1
                ,discover_text6,discover_text5;
        ImageView img1,img2,img3;
    }

    @Override
    public int getViewTypeCount() {
        return View_TYPECOUNT;
    }

    @Override
    public int getItemViewType(int position) {
        if (data.get(position).getImages().size()==1||data.get(position).getImages().size()==2){
            return View_TYPE1;
        }else {
            return View_TYPE3;
        }

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder1 viewHolder1=null;
        ViewHolder3 viewHolder3=null;
        int itemViewType = getItemViewType(position);
        if (convertView==null){
            inflater=LayoutInflater.from(context);
            switch (itemViewType){
                case View_TYPE1:
                    convertView=inflater.inflate(R.layout.discover_item_layout,parent,false);
                    viewHolder1=new ViewHolder1();
                    viewHolder1.discover_img= (ImageView) convertView.findViewById(R.id.discover_img);
                    viewHolder1.discover_text= (TextView) convertView.findViewById(R.id.discover_text);
                    viewHolder1.discover_text8= (TextView) convertView.findViewById(R.id.discover_text8);
                    viewHolder1.discover_time= (TextView) convertView.findViewById(R.id.discover_time);
                    viewHolder1.discover_text2= (TextView) convertView.findViewById(R.id.discover_text2);
                    viewHolder1.discover_text7= (TextView) convertView.findViewById(R.id.discover_text7);
                    convertView.setTag(viewHolder1);
                    break;
                case View_TYPE3:
                   convertView= inflater.inflate(R.layout.discover_item_layout2,parent,false);
                    viewHolder3=new ViewHolder3();
                    viewHolder3.discover_text3= (TextView) convertView.findViewById(R.id.discover_text3);
                    viewHolder3.discover_text4= (TextView) convertView.findViewById(R.id.discover_text4);
                    viewHolder3.discover_time1= (TextView) convertView.findViewById(R.id.discover_time1);
                    viewHolder3.discover_text6= (TextView) convertView.findViewById(R.id.discover_text6);
                    viewHolder3.discover_text5= (TextView) convertView.findViewById(R.id.discover_text5);
                    viewHolder3.img1= (ImageView) convertView.findViewById(R.id.img1);
                    viewHolder3.img2= (ImageView) convertView.findViewById(R.id.img2);
                    viewHolder3.img3= (ImageView) convertView.findViewById(R.id.img3);
                    convertView.setTag(viewHolder3);
                    break;
            }
        }else{
            switch (itemViewType){
                case View_TYPE1:
                    viewHolder1= (ViewHolder1) convertView.getTag();
                    break;
                case View_TYPE3:
                    viewHolder3= (ViewHolder3) convertView.getTag();
                    break;
            }
        }
        //设置资源填充数据
        images = data.get(position).getImages();
        switch (itemViewType){
            case View_TYPE1:
                if (data!=null){
                    if (images.size()==1||images.size()==2) {
                        Glide.with(context).load(images.get(0).getUrl())
                                .crossFade()
                                .thumbnail(0.5f)
                                .placeholder(R.mipmap.holder).into(viewHolder1.discover_img);
                        try{
                            if (data.get(position).getUser().getNickName()!=null) {
                                viewHolder1.discover_text.setText(data.get(position).getUser().getNickName());
                            }
                        }catch (Exception e){
                            
                        }
                        SetTime.timeLogic(String.valueOf(data.get(position).getTime()), viewHolder1.discover_time);
                        viewHolder1.discover_text2.setText(data.get(position).getTitle());
                        viewHolder1.discover_text8.setText(String.valueOf(data.get(position).getViewCount()));
                        viewHolder1.discover_text7.setText(String.valueOf(data.get(position).getCommentCount()));
                    }
            }
                break;
            case View_TYPE3:
                if (data!=null) {
                    if (images.size()==3) {
                        viewHolder3.discover_text3.setText(data.get(position).getTitle());
                        try {
                            if (data.get(position).getUser().getNickName()!=null) {
                                viewHolder3.discover_text4.setText(data.get(position).getUser().getNickName());
                            }
                        }catch (Exception e){
                            
                        }
                        SetTime.timeLogic(String.valueOf(data.get(position).getTime()), viewHolder3.discover_time1);
                        viewHolder3.discover_text6.setText(String.valueOf(data.get(position).getViewCount()));
                        viewHolder3.discover_text5.setText(String.valueOf(data.get(position).getCommentCount()));
                        Glide.with(context).load(images.get(0).getUrl())
                                .thumbnail(0.5f)
                                .crossFade()
                                .placeholder(R.mipmap.holder).into(viewHolder3.img1);
                        Glide.with(context).load(images.get(1).getUrl())
                                .thumbnail(0.5f)
                                .crossFade()
                                .placeholder(R.mipmap.holder).into(viewHolder3.img2);
                        Glide.with(context).load(images.get(2).getUrl())
                                .thumbnail(0.5f)
                                .crossFade()
                                .placeholder(R.mipmap.holder).into(viewHolder3.img3);
                    }
                }
                break;
        }

        return convertView;
        
    }
}
