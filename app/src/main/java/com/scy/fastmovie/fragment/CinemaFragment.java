package com.scy.fastmovie.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.scy.fastmovie.R;
import com.scy.fastmovie.activity.SearchCinemaActivity;
import com.scy.fastmovie.activity.SearchNewsResultActivity;
import com.scy.fastmovie.adapter.CinemaAdapter;
import com.scy.fastmovie.bean.CinemaBean;
import com.scy.fastmovie.db.DbManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 影院
 */
public class CinemaFragment extends Fragment implements PullToRefreshBase.OnRefreshListener2{
    private Context context;
    private View view;
    private EditText search_cinema;
    private PullToRefreshListView cinema_list;
    private DbManager dbManager;
    private int pageNo =1;
    private List<CinemaBean> data;
    private CinemaAdapter cinemaAdapter;
    private List<CinemaBean> cinemaBean;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cinema, container, false);
        initViews();
        initData();
        setListener();
        cinema_list.setMode(PullToRefreshBase.Mode.BOTH);
        cinemaAdapter=new CinemaAdapter(context);
        cinema_list.setRefreshing();
        return view;
    }
    private void initViews(){
        search_cinema= (EditText) view.findViewById(R.id.search_cinema);
        cinema_list= (PullToRefreshListView) view.findViewById(R.id.cinema_list);
    }
    private void setListener(){
        cinema_list.setOnRefreshListener(this);
        search_cinema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, SearchCinemaActivity.class));
                ((AppCompatActivity)context).overridePendingTransition(0, 0);
            }
        });
    }
    private void initData(){
        data=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CinemaBean cinemaBean1=new CinemaBean();
            cinemaBean1.setNm("幸福蓝海国际影城(成都仁和春天光华店)");
            cinemaBean1.setSellPrice("34");
            cinemaBean1.setAddr("青羊区二环路西二段19号仁和春天广场C座5楼");
            cinemaBean1.setDistance("1.9km");
            data.add(cinemaBean1);
            CinemaBean cinemaBean2=new CinemaBean();
            cinemaBean2.setNm("峨影1958电影城");
            cinemaBean2.setSellPrice("29");
            cinemaBean2.setAddr("青羊区清江东路360号（近成温立交桥）");
            cinemaBean2.setDistance("1.9km");
            data.add(cinemaBean2);
            CinemaBean cinemaBean3=new CinemaBean();
            cinemaBean3.setNm("太平洋影城(新城市店)");
            cinemaBean3.setSellPrice("36");
            cinemaBean3.setAddr("青羊区西大街1号新城市广场A栋2楼");
            cinemaBean3.setDistance("2.1km");
            data.add(cinemaBean3);
            CinemaBean cinemaBean4=new CinemaBean();
            cinemaBean4.setNm("太平洋影城(武侯店)");
            cinemaBean4.setSellPrice("34");
            cinemaBean4.setAddr("武侯区武侯祠大街266号华达商城4F");
            cinemaBean4.setDistance("2.3km");
            data.add(cinemaBean4);
            CinemaBean cinemaBean5=new CinemaBean();
            cinemaBean5.setNm("雅图影院(光华店)");
            cinemaBean5.setSellPrice("6.9");
            cinemaBean5.setAddr("青羊区光华村街42号负一楼（乐宾百货一楼）");
            cinemaBean5.setDistance("2.5km");
            data.add(cinemaBean5);
            CinemaBean cinemaBean6=new CinemaBean();
            cinemaBean6.setNm("双楠电影城");
            cinemaBean6.setSellPrice("31");
            cinemaBean6.setAddr("武侯区二环路西一段逸都路6号（双楠伊藤洋华堂6楼）");
            cinemaBean6.setDistance("2.6km");
            data.add(cinemaBean6);
            CinemaBean cinemaBean7=new CinemaBean();
            cinemaBean7.setNm("UME影城(金牛店)");
            cinemaBean7.setSellPrice("24.5");
            cinemaBean7.setAddr("金牛区沙湾路1号汇龙湾广场3楼");
            cinemaBean7.setDistance("2.7km");
            data.add(cinemaBean7);
            CinemaBean cinemaBean8=new CinemaBean();
            cinemaBean8.setNm("橙天嘉禾影城(富力店)");
            cinemaBean8.setSellPrice("24.5");
            cinemaBean8.setAddr("青羊区上西顺城街289富力天汇mall7楼");
            cinemaBean8.setDistance("2.8km");
            data.add(cinemaBean8);
            CinemaBean cinemaBean9=new CinemaBean();
            cinemaBean9.setNm("万达国际影城(财富店)");
            cinemaBean9.setSellPrice("28");
            cinemaBean9.setAddr("锦江区大业路6号财富广场4层D区");
            cinemaBean9.setDistance("3.0km");
            data.add(cinemaBean9);
            CinemaBean cinemaBean10=new CinemaBean();
            cinemaBean10.setNm("百丽宫影城(恒大广场店)");
            cinemaBean10.setSellPrice("33");
            cinemaBean10.setAddr("青羊区提督街99号恒大广场四层（原华置广场）");
            cinemaBean10.setDistance("3.2km");
            data.add(cinemaBean10);
        }
        dbManager=DbManager.getDbManager(context);
        dbManager.insert(data);
        Log.i("=====","insert");
    }
    private void queryData(int pageNo){
        cinemaBean = dbManager.queryByPage(pageNo, 10);
        cinemaAdapter.setData(cinemaBean);
        Log.i("==query",cinemaBean.size()+"");
        cinema_list.onRefreshComplete();
    }
    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        queryData(1);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
       pageNo++;
        queryData(pageNo);
    }
}
