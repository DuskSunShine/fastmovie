package com.scy.fastmovie.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.scy.fastmovie.R;
import com.scy.fastmovie.adapter.WaitAdapter;
import com.scy.fastmovie.baseurl.BaseUrl;
import com.scy.fastmovie.bean.HotFragmentBean;
import com.scy.fastmovie.bean.WaitFragmentBean;
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
    private List<WaitFragmentBean.DataBean.ComingBean> data=new ArrayList<>();
    private List<WaitFragmentBean.DataBean.ComingBean>datas=new ArrayList<>();
    Context context;
    WaitAdapter waitAdapter;
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
        view = inflater.inflate(R.layout.fragment_wait, container, false);
        initViews();
        doSomethings();
        return view;
    }

    private void doSomethings() {
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        listView.setOnRefreshListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        waitAdapter=new WaitAdapter(context);
        listView.setAdapter(waitAdapter);
        listView.setRefreshing();
    }

    private void initDatas() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BaseUrl.DISCOVERBASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        HttpApiService httpApiService=retrofit.create(HttpApiService.class);
        Observable<WaitFragmentBean> observable = httpApiService.getWaitData("59", "12", "yCbV9r7zv52i2GREfcA5QzgOxZ4AAAAAVwMAAASv4m3kDleIy_CBlG7s3oBfOURRpnznoBfbFWZP48AFbiQWN4bVxIjbVw-pGe-v6w", "AmovieBmovieCD100",
                "7701", "yingyonghui1-dy", "android", "7.7.0", "866928026893953", "255",
                "BM002-G5", "92D0E32E8154E517B48491A8CC0405BBF548E493CDDBD0CC6D5094ACBB73B113", "30.662599", "104.040563", "6432106778393259332", "/Welcome",
                "6a375bce8c66a0dc293860dfa83833ef", "1482737690056", "32bcf146c756ecefe7535b95816908e3", "91c75473-da60-4e01-9deb-f83edbc2584f", "jqkeyC7AD4/r8s4JrSk+/jdGd9c=");
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
                        //hotAdapter.setData(hotFragmentBean.getData().getHot());
//                        total=hotFragmentBean.getData().getTotal();
                        data.addAll(waitFragmentBean.getData().getComing());
                        for (int i =0; i <data.size()/3 ; i++) {
                            datas.add(data.get(i));
                        }
                        waitAdapter.setData(datas);
                        listView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                listView.onRefreshComplete();
                            }
                        },1000);
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

        }else {
            listView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    listView.onRefreshComplete();
                }
            }, 1000);
        }
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        if (NetWorkUtils.isConnect(getContext())){
            if (num==2){
                Toast.makeText(getContext(), "没有新数据了...", Toast.LENGTH_SHORT).show();
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
