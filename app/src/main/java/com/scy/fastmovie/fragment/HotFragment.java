package com.scy.fastmovie.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.scy.fastmovie.R;
import com.scy.fastmovie.adapter.HotAdapter;
import com.scy.fastmovie.baseurl.BaseUrl;
import com.scy.fastmovie.bean.HotFragmentBean;
import com.scy.fastmovie.httpapiservice.HttpApiService;

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
    private int total=0;

    public HotFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_hot, container, false);
        initViews();
        DoSomething();

        return view;
    }

    private void DoSomething() {
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        listView.setOnRefreshListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        hotAdapter = new HotAdapter(getContext());
        listView.setAdapter(hotAdapter);
        listView.setRefreshing();
    }

    private void initViews() {
        listView = (PullToRefreshListView) view.findViewById(R.id.list);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        //下拉刷新
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
                        //hotAdapter.setData(hotFragmentBean.getData().getHot());
                        total=hotFragmentBean.getData().getTotal();
                    }
                });


        listView.postDelayed(new Runnable() {
            @Override
            public void run() {
                listView.onRefreshComplete();
            }
        },1000);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        //上拉加载
        listView.postDelayed(new Runnable() {
            @Override
            public void run() {
                listView.onRefreshComplete();
            }
        },1000);
    }
}
