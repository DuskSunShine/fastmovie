package com.scy.fastmovie.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.scy.fastmovie.R;
import com.scy.fastmovie.activity.HotItemDetaiActivity;
import com.scy.fastmovie.adapter.DaiPingJiaAdapter;
import com.scy.fastmovie.bean.DaiPingJiaBean;
import com.scy.fastmovie.bean.MovieDaoBean;
import com.scy.fastmovie.bean.MovieDaoBeanDao;
import com.scy.fastmovie.db.MovieDbManager;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilmFragment extends Fragment {


    private View view;
    private RecyclerView recyclerView;
    private DaiPingJiaAdapter adapter;

    public FilmFragment() {
        // Required empty public constructor
    }
    Context context;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_film, container, false);
        initViews();
        initData();
        loadData();
        setListener();
        return view;
    }
    private void setListener() {
        DaiPingJiaAdapter.setOnCallBack(new DaiPingJiaAdapter.OnCallBack() {
            @Override
            public void onCall(String path, int flag, DaiPingJiaBean bean, Button btn) {
                if (flag==1){
                    Intent intent = new Intent(context, HotItemDetaiActivity.class);
                    intent.putExtra("path",path);
                    startActivity(intent);
                    ((AppCompatActivity)context).overridePendingTransition(0,0);;
                }else if (flag==2){
                    MovieDaoBeanDao dao = MovieDbManager.getDao(context);
                    List<MovieDaoBean> list = dao.queryBuilder().where(MovieDaoBeanDao.Properties.Movie_evaluate.eq(true),MovieDaoBeanDao.Properties.Movie_id.eq(bean.getId())).list();
                    if (list.size()!=0){
                        Toast.makeText(context, "您已经评价过了", Toast.LENGTH_SHORT).show();
                    }else {
                        dao.update(new MovieDaoBean(bean.getId(),bean.getImg(),bean.getName(),bean.getVideo(),bean.getPrice(),"0",bean.getNum(),true,true));
                        loadData();
                        Toast.makeText(context, "评价成功", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
    private void loadData() {
        MovieDaoBeanDao dao = MovieDbManager.getDao(context);
        List<MovieDaoBean> list = dao.queryBuilder().list();
        List<DaiPingJiaBean>data=new ArrayList<>();
        for (MovieDaoBean bean:list){
            DaiPingJiaBean jiaBean = new DaiPingJiaBean(bean.getMovie_img(), bean.getMovie_video(), bean.getMovie_name(), bean.getNum(), bean.getMovie_price());
            jiaBean.setId(bean.getMovie_id());
            data.add(jiaBean);
        }
        adapter.setData(data);
        if (data.size()==0){
            Toast.makeText(context, "您还没购买任何电影票呢\n请先去购买", Toast.LENGTH_SHORT).show();
        }
    }
    private void initData() {
        adapter =new DaiPingJiaAdapter(context);
        recyclerView.setAdapter(adapter);
    }
    private void initViews() {
        recyclerView = ((RecyclerView) view.findViewById(R.id.listview));
        recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
    }

}
