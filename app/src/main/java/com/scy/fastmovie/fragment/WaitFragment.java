package com.scy.fastmovie.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.scy.fastmovie.R;
import com.scy.fastmovie.activity.BuyActivity;
import com.scy.fastmovie.activity.HotItemDetaiActivity;
import com.scy.fastmovie.adapter.WaitAdapter;
import com.scy.fastmovie.adapter.WaitMostAdapter;
import com.scy.fastmovie.adapter.WaitYuGaoAdapter;
import com.scy.fastmovie.baseurl.BaseUrl;
import com.scy.fastmovie.bean.MovieDaoBean;
import com.scy.fastmovie.bean.MovieDaoBeanDao;
import com.scy.fastmovie.bean.WaitFragmentBean;
import com.scy.fastmovie.bean.WaitMostBean;
import com.scy.fastmovie.bean.WaitYuGaoBean;
import com.scy.fastmovie.db.MovieDbManager;
import com.scy.fastmovie.httpapiservice.HttpApiService;
import com.scy.fastmovie.utils.NetWorkUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class WaitFragment extends Fragment implements PullToRefreshBase.OnRefreshListener2{


    private View view;
    private PullToRefreshListView listView;
    int num=0;
    int i,j,k=0;
    private List<WaitFragmentBean.DataBean.ComingBean> data=new ArrayList<>();
    private List<WaitFragmentBean.DataBean.ComingBean>datas=new ArrayList<>();
    Context context;
    WaitAdapter waitAdapter;
     RecyclerView re_yugao;
    WaitYuGaoAdapter yuGaoAdapter;
    ListView listViews;
    RecyclerView re_most;
    private WaitMostAdapter mostAdapter;

    public WaitFragment() {
        // Required empty public constructor
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        data.clear();
        datas.clear();
        view = inflater.inflate(R.layout.fragment_wait, container, false);
        initViews();
        doSomethings();
        setOnClickListeners();
        listView.setRefreshing();
        return view;
    }



    private void setOnClickListeners() {
        WaitAdapter.setOnRelativeClickListener(new WaitAdapter.Call() {
            @Override
            public void onRelativeClick(String path) {
                Intent intent = new Intent(context, HotItemDetaiActivity.class);
                intent.putExtra("path",path);
                startActivity(intent);
                ((AppCompatActivity)context).overridePendingTransition(0,0);
            }
        });
        WaitAdapter.setOnRelativeClickListener(new WaitAdapter.CallText() {
            @Override
            public void onRelativeClick(WaitFragmentBean.DataBean.ComingBean databean, String flag) {
                MovieDaoBeanDao dao = MovieDbManager.getDao(context);
                List<MovieDaoBean> list = dao.queryBuilder().where(MovieDaoBeanDao.Properties.Movie_id.eq(databean.getId())).list();
                if (list.size()!=0){
                    Toast.makeText(context, "该电影已经买过，不可重复购买", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent=new Intent(context,BuyActivity.class);
                intent.putExtra("flag",flag);
                intent.putExtra("movie",databean);
                intent.putExtra("tag","wait");
                startActivity(intent);
                ((AppCompatActivity)context).overridePendingTransition(0,0);
            }
        });


        WaitYuGaoAdapter.setOnRelativeClickListener(new WaitYuGaoAdapter.Call() {
            @Override
            public void onRelativeClick(String path) {
                Intent intent = new Intent(context, HotItemDetaiActivity.class);
                intent.putExtra("path",path);
                startActivity(intent);
                ((AppCompatActivity)context).overridePendingTransition(0,0);
            }
        });
        WaitMostAdapter.setOnRelativeClickListener(new WaitMostAdapter.Call() {
            @Override
            public void onRelativeClick(String path) {
                Intent intent = new Intent(context, HotItemDetaiActivity.class);
                intent.putExtra("path",path);
                startActivity(intent);
                ((AppCompatActivity)context).overridePendingTransition(0,0);
            }
        });
    }
    private void doSomethings() {
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        listView.setOnRefreshListener(this);

        View inflate = LayoutInflater.from(context).inflate(R.layout.wait_head_layout, null);
        re_yugao = (RecyclerView) inflate.findViewById(R.id.re_yugao);
        re_most = ((RecyclerView) inflate.findViewById(R.id.re_most));
        re_yugao.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        re_most.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));

        listViews = listView.getRefreshableView();

        yuGaoAdapter=new WaitYuGaoAdapter(context);
        re_yugao.setAdapter(yuGaoAdapter);

        mostAdapter = new WaitMostAdapter(context);
        re_most.setAdapter(mostAdapter);

        listViews.addHeaderView(inflate);


        waitAdapter=new WaitAdapter(context);
        listView.setAdapter(waitAdapter);



    }

    private void initDatas() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BaseUrl.DISCOVERBASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        HttpApiService httpApiService=retrofit.create(HttpApiService.class);
        Observable<WaitFragmentBean> observable = httpApiService.getWaitData();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WaitFragmentBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(WaitFragmentBean waitFragmentBean) {
                        data.addAll(waitFragmentBean.getData().getComing());
                        for (int i =0; i <data.size()/3 ; i++) {
                            datas.add(data.get(i));
                        }
                        waitAdapter.setData(datas);
                        i=1;
                    }
                });
    }

    private void initViews() {
        listView = ((PullToRefreshListView) view.findViewById(R.id.list));

    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        //下拉刷新
        if(NetWorkUtils.isConnect(context)){
            num=0;
            data.clear();
            datas.clear();
            initDatas();
            loadDatas();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                       if (i==1&&j==1&&k==1){
                           listView.postDelayed(new Runnable() {
                               @Override
                               public void run() {
                                   listView.onRefreshComplete();
                                   i=0;
                                   j=0;
                                   k=0;
                               }
                           },1000);
                           break;
                       }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }else {
            listView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    listView.onRefreshComplete();
                }
            }, 1000);
        }
    }

    private void loadDatas() {
        Retrofit retrofit1=new Retrofit.Builder()
                .baseUrl(BaseUrl.DISCOVERBASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        HttpApiService httpApiService1 = retrofit1.create(HttpApiService.class);
        Observable<WaitYuGaoBean> observable1= httpApiService1.getYuGaoData();
        observable1.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WaitYuGaoBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("===",e+"");
                    }

                    @Override
                    public void onNext(WaitYuGaoBean waitYuGaoBean) {
                        Log.e("===",waitYuGaoBean.getData().size()+"");
                        yuGaoAdapter.setData(waitYuGaoBean.getData());
                        j=1;
                    }
                });
        Retrofit retrofit2=new Retrofit.Builder()
                .baseUrl(BaseUrl.DISCOVERBASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        HttpApiService httpApiService2 = retrofit2.create(HttpApiService.class);
        Observable<WaitMostBean> observable2= httpApiService2.getMostData();
        observable2.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WaitMostBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(WaitMostBean waitMostBean) {
                        mostAdapter.setData(waitMostBean.getData().getComing());
                        k=1;
                    }
                });
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        if (NetWorkUtils.isConnect(context)){
            if (num==2){
                Toast.makeText(context, "没有新数据了...", Toast.LENGTH_SHORT).show();
                listView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        listView.onRefreshComplete();
                    }
                },1000);
                return;
            }
            num++;
            for (int i = (data.size()/3)*num; i <(data.size()/3)*num+data.size()/3 ; i++) {
                datas.add(data.get(i));
            }
            waitAdapter.setData(datas);
            listView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    listView.onRefreshComplete();
                }
            },1000);
        }else {
            listView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    listView.onRefreshComplete();
                }
            },1000);
        }
    }
}
