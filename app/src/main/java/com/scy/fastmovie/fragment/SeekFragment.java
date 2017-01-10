package com.scy.fastmovie.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.scy.fastmovie.R;
import com.scy.fastmovie.adapter.GlobalAdapter;
import com.scy.fastmovie.adapter.GridViewAdapter;
import com.scy.fastmovie.adapter.RecycleViewAdapter;
import com.scy.fastmovie.baseurl.BaseUrl;
import com.scy.fastmovie.bean.SeekBean;
import com.scy.fastmovie.bean.WaitGlobalAwards;
import com.scy.fastmovie.bean.WaitGridBean;
import com.scy.fastmovie.httpapiservice.HttpApiService;
import com.scy.fastmovie.interfaces.ShuJu;

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
public class SeekFragment extends Fragment {


    private View view;
    private RecyclerView recycle_area;
    private RecyclerView recycle_type;
    private RecyclerView recycle_year;
    private GridView gridView;
    int recyle=0;
    int grid=0;
    int all=0;
    RecycleViewAdapter typeAdapter;
    RecycleViewAdapter areaAdapter;
    RecycleViewAdapter yearAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private GridViewAdapter gridViewAdapter;
    private RecyclerView global;
    private GlobalAdapter globalAdapter;

    public SeekFragment() {
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
        view = inflater.inflate(R.layout.fragment_seek, container,false);
        initViews();
        initRecycleviews();
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                doSomethings();
            }
        });
        return view;
    }

    private void initRecycleviews() {
        recycle_type.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        recycle_area.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        recycle_year.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        global.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
//        LayoutInflater.from(context).inflate()
        typeAdapter=new RecycleViewAdapter(context,ShuJu.TYPE);
        areaAdapter=new RecycleViewAdapter(context,ShuJu.AREA);
        yearAdapter=new RecycleViewAdapter(context,ShuJu.YEAR);
        globalAdapter = new GlobalAdapter(context);
        recycle_type.setAdapter(typeAdapter);
        recycle_area.setAdapter(areaAdapter);
        recycle_year.setAdapter(yearAdapter);
        global.setAdapter(globalAdapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                doSomethings();
            }
        });
        gridViewAdapter = new GridViewAdapter(context);
        gridView.setAdapter(gridViewAdapter);
    }

    private void doSomethings() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BaseUrl.DISCOVERBASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        HttpApiService httpApiService=retrofit.create(HttpApiService.class);
        Observable<SeekBean>observable=httpApiService.getSeekData(
          "yCbV9r7zv52i2GREfcA5QzgOxZ4AAAAAVwMAAASv4m3kDleIy_CBlG7s3oBfOURRpnznoBfbFWZP48AFbiQWN4bVxIjbVw-pGe-v6w",
          "AmovieBmovieCD100","7701","yingyonghui1-dy","android","7.7.0","866928026893953",
          "59","255","BM002-G5","92D0E32E8154E517B48491A8CC0405BBF548E493CDDBD0CC6D5094ACBB73B113","/Welcome"
        );
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SeekBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SeekBean seekBean) {
                        Log.e("===","==onnext==");
                        typeAdapter.setData(seekBean.getData());
                        areaAdapter.setData(seekBean.getData());
                        yearAdapter.setData(seekBean.getData());
                        recyle=1;
                    }
                });
        Retrofit retrofit1=new Retrofit.Builder()
                .baseUrl(BaseUrl.DISCOVERBASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        HttpApiService httpApiService1=retrofit1.create(HttpApiService.class);
        Observable<WaitGridBean>observable1=httpApiService1.getWaitGridData(
                "AmovieBmovieCD100","7701","yingyonghui1-dy","android","7.7.0","866928026893953",
                "59","255","BM002-G5","92D0E32E8154E517B48491A8CC0405BBF548E493CDDBD0CC6D5094ACBB73B113","/Welcome"
        );
        observable1.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WaitGridBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(WaitGridBean waitGridBean) {
                        Log.e("===","seek");
                        gridViewAdapter.setData(waitGridBean.getData());
                        grid=1;
                    }
                });
        Retrofit retrofit2=new Retrofit.Builder()
                .baseUrl(BaseUrl.DISCOVERBASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        HttpApiService httpApiService2 = retrofit2.create(HttpApiService.class);
        Observable<WaitGlobalAwards> observable2 = httpApiService2.getWaitGlobalAwardsData(
                "AmovieBmovieCD100","7701","yingyonghui1-dy",
                "android","7.7.0","866928026893953","59","255",
                "BM002-G5","92D0E32E8154E517B48491A8CC0405BBF548E493CDDBD0CC6D5094ACBB73B113","/Welcome"
        );
        observable2.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WaitGlobalAwards>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(WaitGlobalAwards waitGlobalAwards) {
                        globalAdapter.setData(waitGlobalAwards.getData());
                        all=1;
                    }
                });
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if (recyle==1&&grid==1&all==1){
                        swipeRefreshLayout.post(new Runnable() {
                            @Override
                            public void run() {
                                swipeRefreshLayout.setRefreshing(false);
                                recyle=0;
                                grid=0;
                                all=0;
                            }
                        });
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
    }


    private void initViews() {
        recycle_type = (RecyclerView) view.findViewById(R.id.recycle_type);
        recycle_area = (RecyclerView) view.findViewById(R.id.recycle_area);
        recycle_year = (RecyclerView) view.findViewById(R.id.recycle_year);
        gridView = (GridView) view.findViewById(R.id.grid);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swip);
        global = (RecyclerView) view.findViewById(R.id.GlobalAwards);
    }

}
