package com.scy.fastmovie.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.Toast;

import com.scy.fastmovie.R;
import com.scy.fastmovie.adapter.DaiPingJiaAdapter;
import com.scy.fastmovie.bean.DaiPingJiaBean;
import com.scy.fastmovie.bean.MovieDaoBean;
import com.scy.fastmovie.bean.MovieDaoBeanDao;
import com.scy.fastmovie.db.MovieDbManager;
import com.scy.fastmovie.interfaces.ShuJu;

import java.util.ArrayList;
import java.util.List;

public class PingJiaDetailActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    DaiPingJiaAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ping_jia_detail);
        ShuJu.activitys.add(this);
        initViews();
        initData();
        loadData();
        setListener();
    }

    private void setListener() {
        DaiPingJiaAdapter.setOnCallBack(new DaiPingJiaAdapter.OnCallBack() {
            @Override
            public void onCall(String path, int flag, DaiPingJiaBean bean, Button btn) {
                if (flag==1){
                    Intent intent = new Intent(PingJiaDetailActivity.this, HotItemDetaiActivity.class);
                    intent.putExtra("path",path);
                    startActivity(intent);
                    overridePendingTransition(0,0);;
                }else if (flag==2){
                    MovieDaoBeanDao dao = MovieDbManager.getDao(PingJiaDetailActivity.this);
                    dao.update(new MovieDaoBean(bean.getId(),bean.getImg(),bean.getName(),bean.getVideo(),bean.getPrice(),"0",bean.getNum(),true,true));
                    loadData();
                    Toast.makeText(PingJiaDetailActivity.this, "评价成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadData() {
        MovieDaoBeanDao dao = MovieDbManager.getDao(this);
        List<MovieDaoBean> list = dao.queryBuilder().where(MovieDaoBeanDao.Properties.Movie_evaluate.eq(false)).list();
        List<DaiPingJiaBean>data=new ArrayList<>();
        for (MovieDaoBean bean:list){
            DaiPingJiaBean jiaBean = new DaiPingJiaBean(bean.getMovie_img(), bean.getMovie_video(), bean.getMovie_name(), bean.getNum(), bean.getMovie_price());
            jiaBean.setId(bean.getMovie_id());
            data.add(jiaBean);
        }
        adapter.setData(data);
        if (data.size()==0){
            Toast.makeText(PingJiaDetailActivity.this, "您没有未评价的电影", Toast.LENGTH_SHORT).show();
        }
    }

    private void initData() {
        adapter=new DaiPingJiaAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    private void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.listview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }
}
