package com.scy.fastmovie.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.scy.fastmovie.R;
import com.scy.fastmovie.activity.BuyActivity;
import com.scy.fastmovie.activity.HotItemDetaiActivity;
import com.scy.fastmovie.activity.MainActivity;
import com.scy.fastmovie.adapter.BannerAdapter;
import com.scy.fastmovie.adapter.HotAdapter;
import com.scy.fastmovie.baseurl.BaseUrl;
import com.scy.fastmovie.bean.BannerBean;
import com.scy.fastmovie.bean.HotFragmentBean;
import com.scy.fastmovie.bean.MovieDaoBean;
import com.scy.fastmovie.bean.MovieDaoBeanDao;
import com.scy.fastmovie.db.MovieDbManager;
import com.scy.fastmovie.httpapiservice.HttpApiService;
import com.scy.fastmovie.utils.NetWorkUtils;

import java.util.ArrayList;
import java.util.List;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
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
public class HotFragment extends Fragment implements PullToRefreshBase.OnRefreshListener2{


    private View view;
    private PullToRefreshListView listView;
    private HotAdapter hotAdapter;
    int num=0;
    private List<HotFragmentBean.DataBean.HotBean>data=new ArrayList<>();
    private List<HotFragmentBean.DataBean.HotBean>datas=new ArrayList<>();
    private ListView listViews;
    private BannerAdapter bannerAdapter;
    AutoScrollViewPager viewPager;
    Context context;

    public HotFragment() {
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
        data.clear();
        datas.clear();
        view = inflater.inflate(R.layout.fragment_hot, container, false);
        initViews();
        DoSomething();
        setOnClickListeners();
        return view;
    }

    private void setOnClickListeners() {
        HotAdapter.setOnRelativeClickListener(new HotAdapter.Call() {
            @Override
            public void onRelativeClick(String path) {
                Intent intent = new Intent(context, HotItemDetaiActivity.class);
                intent.putExtra("path",path);
                startActivity(intent);
                ((AppCompatActivity)context).overridePendingTransition(0,0);
            }
        });
        HotAdapter.setOnRelativeClickListener(new HotAdapter.CallText() {

            @Override
            public void onRelativeClick(HotFragmentBean.DataBean.HotBean databean, String flag) {
                MovieDaoBeanDao dao = MovieDbManager.getDao(context);
                List<MovieDaoBean> list = dao.queryBuilder().where(MovieDaoBeanDao.Properties.Movie_id.eq(databean.getId())).list();
                if (list.size()!=0){
                    Toast.makeText(context, "该电影已经买过，不可重复购买", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent=new Intent(context,BuyActivity.class);
                intent.putExtra("flag",flag);
                intent.putExtra("movie",databean);
                intent.putExtra("tag","hot");
                startActivity(intent);
                ((AppCompatActivity)context).overridePendingTransition(0,0);
            }
        });
    }

    private void loadBannerData() {
        Retrofit retrofit=new Retrofit.Builder().baseUrl(BaseUrl.MEITUAN)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        HttpApiService httpApiService=retrofit.create(HttpApiService.class);
        Observable<BannerBean> observable = httpApiService.getBannerData("-5369611843509190267", "6a375bce8c66a0dc293860dfa83833ef", "8G68yN/o0WVppRmoKLqsbtTqbsk=", "f3ec892c-12ec-4170-bea2-6fc207c69b46", "1482719732062", "32bcf146c756ecefe7535b95816908e3", "movie",
                "1", "11", "59", "59", "android", "866928026893953", "BM002-G5",
                "30.662588", "104.040702", "7701", "255", "0", "1",
                "/NewsDetailActivity", "738265132", "AmovieBmovieCD100", "866928026893953", "android", "yingyonghui1-dy",
                "7.7.0", "92D0E32E8154E517B48491A8CC0405BBF548E493CDDBD0CC6D5094ACBB73B113", "7.7.0");
        observable.subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BannerBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BannerBean bannerBean) {
                        bannerAdapter.setData(bannerBean.getData());
//                        initDatas();
                    }
                });
    }

    private void DoSomething() {

        listView.setMode(PullToRefreshBase.Mode.BOTH);
        listView.setOnRefreshListener(this);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        View inflate = LayoutInflater.from(context).inflate(R.layout.banner_pager, null);
        viewPager = (AutoScrollViewPager) inflate.findViewById(R.id.viewPager);
        viewPager.setInterval(2000);
        bannerAdapter = new BannerAdapter(context);
        viewPager.setAdapter(bannerAdapter);
        listViews.addHeaderView(inflate);
        viewPager.startAutoScroll();
        hotAdapter = new HotAdapter(context);
        listView.setAdapter(hotAdapter);
        listView.setRefreshing();

    }

    private void initViews() {
        listView = (PullToRefreshListView) view.findViewById(R.id.list);
        listViews = listView.getRefreshableView();
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        //下拉刷新
        if(NetWorkUtils.isConnect(context)){
            num=0;
            data.clear();
            datas.clear();
            initDatas();
            loadBannerData();

        }else {
            listView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    listView.onRefreshComplete();
                }
            }, 1000);
        }
    }


    private void initDatas() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BaseUrl.DISCOVERBASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        HttpApiService httpApiService=retrofit.create(HttpApiService.class);
        Observable<HotFragmentBean> observable = httpApiService.getHotData("-2534977814955072788", "6a375bce8c66a0dc293860dfa83833ef", "gVtzEILPCkn+HM7gau8CxNPSc8w=", "ae0ea14d-8eb9-4343-9972-8e4b78795040",
                "1482719428398", "32bcf146c756ecefe7535b95816908e3", "59", "BM002-G5", "30.662584", "12",
                "104.040636", "7701", "255", "/Welcome", "yCbV9r7zv52i2GREfcA5QzgOxZ4AAAAAVwMAAASv4m3kDleIy_CBlG7s3oBfOURRpnznoBfbFWZP48AFbiQWN4bVxIjbVw-pGe-v6w", "AmovieBmovieCD100",
                "866928026893953", "android", "yingyonghui1-dy", "7.7.0", "92D0E32E8154E517B48491A8CC0405BBF548E493CDDBD0CC6D5094ACBB73B113");
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HotFragmentBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(HotFragmentBean hotFragmentBean) {
                        data.addAll(hotFragmentBean.getData().getHot());
                        for (int i =0; i <data.size()/3 ; i++) {
                            datas.add(data.get(i));
                        }
                        hotAdapter.setData(datas);
                        listView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                listView.onRefreshComplete();
                            }
                        },1000);
                    }
                });

    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        //上拉加载
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
            hotAdapter.setData(datas);
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

    @Override
    public void onPause() {
        super.onPause();
//        viewPager.stopAutoScroll();
    }

    @Override
    public void onResume() {
        super.onResume();
//        viewPager.stopAutoScroll();
    }
}
