package com.scy.fastmovie.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.scy.fastmovie.R;
import com.scy.fastmovie.bean.BannerBean;

import java.util.List;

/**
 * Created by TimiZhuo on 2016/12/30.
 */
public class BannerAdapter extends PagerAdapter {
    Context context;
    List<BannerBean.DataBean>data;

    public BannerAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<BannerBean.DataBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data==null?0:data.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
        ImageView img = (ImageView) view.findViewById(R.id.img);
        Glide.with(context).load(data.get(position).getImgUrl())
                    .placeholder(R.mipmap.ic_launcher)
                    .into(img);
            container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
