package com.scy.fastmovie.fragment;



import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.scy.fastmovie.R;
import com.scy.fastmovie.activity.SearchNewsResultActivity;
import com.scy.fastmovie.adapter.DiscoverAdapter;
import com.scy.fastmovie.baseurl.BaseUrl;
import com.scy.fastmovie.bean.DiscoverBean;
import com.scy.fastmovie.httpapiservice.HttpApiService;

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
 * 发现碎片
 */
public class DiscoverFragment extends Fragment implements
        PullToRefreshBase.OnRefreshListener2,View.OnClickListener,AdapterView.OnItemClickListener{
    
    private Toolbar discover_toolbar;
    private View view;
    private View head;
    private Button search_edit,btn_top,btn_kuaixun,btn_piaofang;
    private PullToRefreshListView search_list;
    private DiscoverAdapter discoverAdapter;
    private int pageNo=0;
    private Context context;
    private List<DiscoverBean.DataBean.FeedsBean> data=new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_discover, container, false);
        head=inflater.inflate(R.layout.discover_item_head,null);
        initViews();
        //fragment设置toolbar
        ((AppCompatActivity)context).setSupportActionBar(discover_toolbar);
        //添加ListView头
        search_list.getRefreshableView().addHeaderView(head);
        search_list.setMode(PullToRefreshBase.Mode.BOTH);
        search_list.setOnRefreshListener(this);
        
        discoverAdapter=new DiscoverAdapter(context);
        search_list.setAdapter(discoverAdapter);
        
        search_list.setRefreshing();
        
        return view;
    }

    /**
     * 初始化布局
     */
    private void initViews(){
        discover_toolbar= (Toolbar) view.findViewById(R.id.discover_toolbar);
        search_edit= (Button) view.findViewById(R.id.search_edit);
        search_list= (PullToRefreshListView) view.findViewById(R.id.search_list);
        btn_top= (Button) head.findViewById(R.id.btn_top);
        btn_kuaixun= (Button) head.findViewById(R.id.btn_kuaixun);
        btn_piaofang= (Button) head.findViewById(R.id.btn_piaofang);
    }

    /**
     * 加载数据
     */
    private void initData(int pageNo){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BaseUrl.DISCOVERBASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        HttpApiService httpApiService=retrofit.create(HttpApiService.class);
        Observable<DiscoverBean> observable = httpApiService.getDiscoverData(pageNo+"","10");
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DiscoverBean>() {
                    @Override
                    public void onCompleted() {
                        
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DiscoverBean discoverBean) {
                        Log.i("====onnext====","=="+discoverBean.getData().getFeeds());
                        if (discoverBean!=null){
                            List<DiscoverBean.DataBean.FeedsBean> list = discoverBean.getData().getFeeds();
                            data.addAll(list);
                            discoverAdapter.setData(data);
                            discoverAdapter.notifyDataSetChanged();
                            search_list.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    search_list.onRefreshComplete();
                                }
                            },2000);
                        }
                    }
                });
    }
    //下拉刷新
    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        data.clear();
        initData(pageNo);
    }
    //上拉加载
    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        pageNo+=10;
        initData(pageNo);
    }
    
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_edit:
                startActivity(new Intent(context, SearchNewsResultActivity.class));
                break;
            case R.id.btn_top:
                Toast.makeText(context, "top", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_kuaixun:
                Toast.makeText(context, "kuai", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_piaofang:
                Toast.makeText(context, "piao", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(context, position, Toast.LENGTH_SHORT).show();
    }
}
